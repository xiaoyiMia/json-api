package org.ting.jsonapi.converter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ting.jsonapi.ExceptionResponse;
import org.ting.jsonapi.Page;
import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiRelation;
import org.ting.jsonapi.annotations.JsonApiRelationId;
import org.ting.jsonapi.annotations.JsonApiResource;
import org.ting.jsonapi.exception.ClassCannotConvertException;
import org.ting.jsonapi.exception.IllegalAnnotationException;
import org.ting.jsonapi.exception.JsonApiException;
import org.ting.jsonapi.response.ArrayResourceResponse;
import org.ting.jsonapi.response.ErrorSource;
import org.ting.jsonapi.response.JsonError;
import org.ting.jsonapi.response.JsonResponse;
import org.ting.jsonapi.response.Link;
import org.ting.jsonapi.response.Relationship;
import org.ting.jsonapi.response.Relationships;
import org.ting.jsonapi.response.Resource;
import org.ting.jsonapi.response.ResourceLinkage;
import org.ting.jsonapi.response.SingleRelationship;
import org.ting.jsonapi.response.SingleResourceResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	@SuppressWarnings("unchecked")
	public static JsonResponse convert(Object object)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, JsonApiException {
		if (object == null) {
			return null;
		} else if (object instanceof ExceptionResponse) {
			return convertExceptionResponse((ExceptionResponse) object);
		} else if (object instanceof Exception) {
			return convertGeneralException((Exception) object);
		} else if (object instanceof Page) {
			return convertResponsePage((Page<?>) object);
		} else if (object instanceof Collection) {
			Collection<?> objectCollection = (Collection<?>) object;
			if (!objectCollection.isEmpty() && (objectCollection.iterator().next()) instanceof ExceptionResponse) {
				return convertExceptionResponse((Collection<ExceptionResponse>) objectCollection);
			} else {
				return convertResponseCollection(objectCollection);
			}
		} else {
			return convertSingleResponseObject(object);
		}
	}

	public static JsonResponse convertGeneralException(Exception exception) {
		JsonError jsonError = JsonError.builder().detail(exception.getMessage()).build();

		SingleResourceResponse jsonResponse = new SingleResourceResponse();
		jsonResponse.addError(jsonError);
		return jsonResponse;
	}

	/**
	 * Convert a error object to JsonApi formatted response object
	 * 
	 * @param error
	 *            An object extends ErrorResponse
	 * @return
	 */
	public static JsonResponse convertExceptionResponse(ExceptionResponse error) {
		JsonError jsonError = convertError(error);

		SingleResourceResponse jsonResponse = new SingleResourceResponse();
		jsonResponse.addError(jsonError);
		return jsonResponse;
	}

	/**
	 * Convert errors object to JsonApi formatted response object
	 * 
	 * @param errors
	 *            A collection of object that extends ErrorResponse
	 * @return
	 */
	public static JsonResponse convertExceptionResponse(Collection<ExceptionResponse> errors) {
		List<JsonError> jsonErrors = new ArrayList<JsonError>();
		for (ExceptionResponse error : errors) {
			jsonErrors.add(convertError(error));
		}

		SingleResourceResponse jsonResponse = new SingleResourceResponse();
		jsonResponse.setErrors(jsonErrors);
		return jsonResponse;
	}

	private static JsonError convertError(ExceptionResponse error) {
		ErrorSource errorSource = null;
		if (error._getInvalidPayloadAttribute() != null || error._getInvalidQueryParameter() != null) {
			errorSource = ErrorSource.builder()
					.pointer(error._getInvalidPayloadAttribute() == null ? null : error._getInvalidPayloadAttribute())
					.parameter(error._getInvalidQueryParameter()).build();
		}

		Link links = error._getAboutLink() == null ? null : Link.builder().about(error._getAboutLink()).build();
		return new JsonError.JsonErrorBuilder().code(error._getDetailCode()).detail(error._getErrorDetail())
				.id(error._getId()).links(links).meta(error._getMeta()).source(errorSource)
				.status(error._getStatusCode()).title(error._getErrorTitle()).build();
	}

	private static JsonResponse convertSingleResponseObject(Object object)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, JsonApiException {
		SingleResourceResponse response = new SingleResourceResponse();

		Set<Resource> includes = new HashSet<Resource>();
		response.setData(convertResponseObject(object, includes));

		if (!includes.isEmpty()) {
			response.setIncludes(includes);
		}
		return response;
	}

	private static JsonResponse convertResponseCollection(Collection<?> objectCollection)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, JsonApiException {
		ArrayResourceResponse response = new ArrayResourceResponse();

		Set<Resource> dataSet = new HashSet<Resource>();
		Set<Resource> includes = new HashSet<Resource>();
		for (Object object : objectCollection) {
			dataSet.add(convertResponseObject(object, includes));
		}

		if (!dataSet.isEmpty()) {
			response.setData(dataSet);
		}
		if (!includes.isEmpty()) {
			response.setIncludes(includes);
		}
		return response;
	}

	private static JsonResponse convertResponsePage(Page<?> objectPage)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, JsonApiException {
		ArrayResourceResponse response = new ArrayResourceResponse();

		Set<Resource> dataSet = new HashSet<Resource>();
		Set<Resource> includes = new HashSet<Resource>();
		for (Object object : objectPage.getContents()) {
			dataSet.add(convertResponseObject(object, includes));
		}

		if (!dataSet.isEmpty()) {
			response.setData(dataSet);
		}
		if (!includes.isEmpty()) {
			response.setIncludes(includes);
		}

		response.setMeta(generatePageMeta(objectPage));
		return response;
	}

	private static Map<String, Object> generatePageMeta(Page<?> objectPage) {
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("total", objectPage.getTotalElements());
		meta.put("current", objectPage.getCurrentElements());
		return meta;
	}

	@SuppressWarnings("unchecked")
	private static Resource convertResponseObject(Object object, Set<Resource> includes)
			throws NoSuchFieldException, SecurityException, JsonApiException, ClassNotFoundException {
		Class<?> resourceClass = object.getClass();
		if (!resourceClass.isAnnotationPresent(JsonApiResource.class)) {
			throw new ClassCannotConvertException("Missing @JsonApiResource annotation");
		}

		Map<String, Object> attributeMap = new ObjectMapper().convertValue(object, Map.class);
		return generateResource(attributeMap, resourceClass, includes, null);
	}

	/**
	 * Convert a given attribute map to Resource data
	 * 
	 * @param attributeMap
	 *            The given map contains all attributes information
	 * @param resourceClass
	 *            The original class type of the attributes information
	 * @param includes
	 *            The collection used to fulfill detail information of converting.
	 *            It will be used as 'includes' object in the converted response.
	 *            response object
	 * @param relatedRelationship
	 *            The upper relationship object that related to this resource.
	 *            Relationship object generated by this resource will be add into
	 *            this one if not contained.
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws JsonApiException
	 */
	@SuppressWarnings("unchecked")
	private static Resource generateResource(Map<String, Object> attributeMap, Class<?> resourceClass,
			Set<Resource> includes, Relationships relatedRelationship)
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, JsonApiException {

		// Generate Data
		Resource data = new Resource();

		String resourceType = null;
		String resourceId = null;

		// Set up "type"
		if (resourceClass.isAnnotationPresent(JsonApiResource.class)) {
			JsonApiResource annotation = resourceClass.getAnnotation(JsonApiResource.class);
			resourceType = annotation.type();
		} else {
			throw new ClassCannotConvertException(
					"Class " + resourceClass.getSimpleName() + " missing @JsonApiResource annotation");
		}

		// Fields
		Map<String, Relationship> relationshipMap = new HashMap<String, Relationship>();
		Set<String> attributeKeyToRemove = new HashSet<String>();

		for (Entry<String, Object> entry : attributeMap.entrySet()) {
			String fieldKey = entry.getKey();
			Object fieldValue = entry.getValue();
			Field field = resourceClass.getDeclaredField(fieldKey);

			if (field.isAnnotationPresent(JsonApiId.class)) {
				// Setup "id"
				resourceId = fieldValue.toString();
				attributeKeyToRemove.add(fieldKey);
			} else if (field.isAnnotationPresent(JsonApiRelationId.class)) {
				if (fieldValue != null && (fieldValue instanceof Collection || fieldValue instanceof Map)) {
					throw new IllegalAnnotationException("@JsonApiRelationId cannot be used on a collection or map");
				} else if (fieldValue != null) {
					// Setup single relationship
					JsonApiRelationId relationIdAnnotation = field.getAnnotation(JsonApiRelationId.class);
					SingleRelationship relationship = new SingleRelationship();
					relationship.setData(new ResourceLinkage(fieldValue.toString(), relationIdAnnotation.type()));
					relationshipMap.put(relationIdAnnotation.attributeName(), relationship);
				}
				attributeKeyToRemove.add(fieldKey);
			} else if (field.isAnnotationPresent(JsonApiRelation.class)) {
				// Setup one-to-many relationships and includes
				if (fieldValue instanceof Collection) {
					Relationships relationship = new Relationships();
					relationshipMap.put(fieldKey, relationship);
					Collection<?> valueCollection = (Collection<?>) fieldValue;

					if (valueCollection.size() > 0) {
						relationship.setData(new HashSet<ResourceLinkage>());
					}
					for (Object value : valueCollection) {
						ParameterizedType type = (ParameterizedType) field.getGenericType();
						includes.add(generateResource((Map<String, Object>) value,
								(Class<?>) type.getActualTypeArguments()[0], includes, relationship));
					}
				} else if (fieldValue instanceof Map) {
					includes.add(generateResource((Map<String, Object>) fieldValue, field.getType(), includes, null));
				} else if(fieldValue instanceof String){
					throw new IllegalAnnotationException(
							"@JsonApiRelation cannot be used on an object mapped to simple string");
				}
				attributeKeyToRemove.add(fieldKey);
			}

		}
		attributeMap.keySet().removeAll(attributeKeyToRemove);
		if (!relationshipMap.isEmpty()) {
			data.setRelationships(relationshipMap);
		}
		data.setType(resourceType);

		if (resourceId == null) {
			throw new ClassCannotConvertException(
					"Class " + resourceClass.getSimpleName() + " missing @JsonApiId annotated attribute");
		}
		data.setId(resourceId);
		data.setAttributes(attributeMap);
		if (relatedRelationship != null) {
			relatedRelationship.getData().add(new ResourceLinkage(resourceId, resourceType));
		}

		return data;
	}

}
