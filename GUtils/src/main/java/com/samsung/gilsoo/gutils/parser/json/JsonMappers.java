package com.samsung.gilsoo.gutils.parser.json;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMappers {
	private ObjectMapper mapper;
	
	public JsonMappers() {
		this.mapper = new ObjectMapper();
	}
	
	public <T> T fromJson(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(content, type);
	}
	
	public <T> List<T> fromJsons(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(content, listType(mapper, type));
	}
	
	public <T> String toJson(T object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
	
	public <T> JavaType listType(ObjectMapper mapper, Class<T> cls) {
        return mapper.getTypeFactory().constructCollectionType(List.class, cls);
    }
}
