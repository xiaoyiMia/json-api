package org.ting.jsonapi;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.ting.jsonapi.context.Article;
import org.ting.jsonapi.context.Comment;
import org.ting.jsonapi.context.NotFoundError;
import org.ting.jsonapi.context.Person;
import org.ting.jsonapi.converter.JsonConverter;
import org.ting.jsonapi.exception.JsonApiException;
import org.ting.jsonapi.response.JsonResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertTest {

	 @Test
	 public void errorConvertTest() throws JsonProcessingException, NoSuchFieldException, SecurityException, ClassNotFoundException, JsonApiException {
	 NotFoundError notFound = new NotFoundError();
	 JsonResponse response = JsonConverter.convert(notFound);
	
	 System.out.println(new ObjectMapper().writeValueAsString(response));
	 }
	
	 @Test
	 public void articleConvertTest() throws NoSuchFieldException,
	 SecurityException, JsonProcessingException, ClassNotFoundException, JsonApiException {
	 Article article = new Article();
	 article.setId(1);
	 article.setTitle("article 1");
	 article.setAuthorId(1);
	
	 JsonResponse response = JsonConverter.convert(article);
	
	 ObjectMapper objMapper = new ObjectMapper();
	 objMapper.setSerializationInclusion(Include.NON_NULL);
	
	 String jsonString = objMapper.writeValueAsString(response);
	 System.out.println(jsonString);
	 }
	
	 @Test
	 public void articleWithAuthorConvertTest() throws NoSuchFieldException,
	 SecurityException, JsonProcessingException, ClassNotFoundException, JsonApiException {
	 Article article = new Article();
	 article.setId(1);
	 article.setTitle("article 1");
	 article.setAuthorId(1);
	
	 Person author = new Person();
	 author.setFirstName("ting");
	 author.setLastName("wang");
	 author.setId(1);
	 article.setAuthor(author);
	
	 JsonResponse response = JsonConverter.convert(article);
	
	 ObjectMapper objMapper = new ObjectMapper();
	 objMapper.setSerializationInclusion(Include.NON_NULL);
	
	 String jsonString = objMapper.writeValueAsString(response);
	 System.out.println(jsonString);
	 }

	@Test
	public void articleWithAuthorAndCommentsConvertTest()
			throws NoSuchFieldException, SecurityException, JsonProcessingException, ClassNotFoundException, JsonApiException {
		Article article = new Article();
		article.setId(1);
		article.setTitle("article 1");
		article.setAuthorId(1);

		Person author = new Person();
		author.setFirstName("ting");
		author.setLastName("wang");
		author.setId(1);
		article.setAuthor(author);

		List<Comment> comments = new ArrayList<Comment>();
		for (int i = 1; i < 5; i++) {
			Comment comment = new Comment();
			comment.setId(i);
			comment.setAuthor(author);
			comment.setAuthorId(author.getId());
			comment.setBody("comment " + i);
			comments.add(comment);
		}
		article.setComments(comments);

		JsonResponse response = JsonConverter.convert(article);

		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setSerializationInclusion(Include.NON_NULL);

		String jsonString = objMapper.writeValueAsString(response);
		System.out.println(jsonString);
	}

	@Test
	public void articleListConvertTest()
			throws NoSuchFieldException, SecurityException, ClassNotFoundException, JsonProcessingException, JsonApiException {
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		article.setId(1);
		article.setTitle("article 1");

		Person author = new Person();
		author.setFirstName("ting");
		author.setLastName("wang");
		author.setId(1);

		article.setAuthor(author);
		article.setAuthorId(author.getId());
		List<Comment> comments = new ArrayList<Comment>();
		for (int i = 1; i < 5; i++) {
			Comment comment = new Comment();
			comment.setId(i);
			comment.setAuthor(author);
			comment.setAuthorId(author.getId());
			comment.setBody("comment " + i);
			comments.add(comment);
		}
		article.setComments(comments);
		articles.add(article);

		Article article1 = new Article();
		article1.setId(2);
		article1.setTitle("article 2");

		Person author1 = new Person();
		author1.setFirstName("chenyu");
		author1.setLastName("hua");
		author1.setId(2);

		article1.setAuthor(author1);
		article1.setAuthorId(author1.getId());
		comments = new ArrayList<Comment>();
		for (int i = 5; i < 7; i++) {
			Comment comment = new Comment();
			comment.setId(i);
			comment.setAuthor(author);
			comment.setAuthorId(author.getId());
			comment.setBody("comment " + i);
			comments.add(comment);
		}
		for (int i = 7; i < 10; i++) {
			Comment comment = new Comment();
			comment.setId(i);
			comment.setAuthor(author1);
			comment.setAuthorId(author1.getId());
			comment.setBody("comment " + i);
			comments.add(comment);
		}
		article1.setComments(comments);
		articles.add(article1);

		JsonResponse response = JsonConverter.convert(articles);

		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setSerializationInclusion(Include.NON_NULL);

		String jsonString = objMapper.writeValueAsString(response);
		System.out.println(jsonString);
	}
}
