package org.ting.jsonapi.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface JsonApiRelationId {

	/**
	 * type name of the object this id related to
	 * @return
	 */
	public String type();
	/**
	 * name of the attribute this id related to
	 * @return
	 */
	public String attributeName();
}
