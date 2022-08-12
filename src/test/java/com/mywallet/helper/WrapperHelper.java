package com.mywallet.helper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WrapperHelper {

	private ObjectMapper objectMapper;

	public WrapperHelper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public String wrapper(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	public <T> T unwrapper(String json, Class<T> unwrapperClass) {
		try {
			return objectMapper.readValue(json, unwrapperClass);
		} catch (JsonProcessingException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	public <T> List<T> unwrapperList(String json, Class<T> unwrapperClass) {
		try {
			return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, unwrapperClass));
		} catch (JsonProcessingException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

}
