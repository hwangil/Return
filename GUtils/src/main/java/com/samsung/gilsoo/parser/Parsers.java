package com.samsung.gilsoo.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.samsung.gilsoo.gutils.parser.json.JsonMappers;
import com.samsung.gilsoo.gutils.parser.xml.XmlMappers;

public class Parsers {
	
	// json
	public static <T> T fromJson(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return new JsonMappers().fromJson(content, type);
	}
	
	public static <T> T fromJson(String content, Class<T> type, boolean isSingleAsArr) throws JsonParseException, JsonMappingException, IOException {
		return isSingleAsArr ? new JsonMappers().fromJson(content, type, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
				: fromJson(content, type);
	}
	
	public static <T> List<T> fromJsons(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return new JsonMappers().fromJsons(content, type);
	}
	
	public static <T> List<T> fromJsons(String content, Class<T> type, boolean isSingleAsArr) throws JsonParseException, JsonMappingException, IOException {
		return isSingleAsArr ? new JsonMappers().fromJsons(content, type, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
				: fromJsons(content, type);
	}
	
	public static <T> String toJson(T object) throws JsonProcessingException {
		return new JsonMappers().toJson(object);
	}
	
	public static <T> String toJson(T object, boolean isSingleAsArr) throws JsonProcessingException {
		return isSingleAsArr ? new JsonMappers().toJson(object, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
				: toJson(object);
	}
	
	// xml
	public static <T> T fromXml(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return new XmlMappers().fromXml(content, type);
	}
	
	public static <T> String toXml(T object) throws JsonProcessingException {
		return new XmlMappers().toXml(object);
	}
	
	// json <-> xml
	public static String xmlToJson(String content) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return new JsonMappers().toJson(new XmlMappers().fromXml(content, Map.class));
	}
	
	public static String xmlToJson(String content, boolean isSingleAsArr) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return isSingleAsArr ? new JsonMappers().toJson(new XmlMappers().fromXml(content, Map.class), DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
				: new JsonMappers().toJson(new XmlMappers().fromXml(content, Map.class));
	}
	
	public static String jsonToXml(String content) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return new XmlMappers().toXml(new JsonMappers().fromJson(content, Map.class));
	}
	
	public static String jsonToXml(String content, boolean isSingleAsArr) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return isSingleAsArr ? new XmlMappers().toXml(new JsonMappers().fromJson(content, Map.class, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
				: new XmlMappers().toXml(new JsonMappers().fromJson(content, Map.class));
	}
	
	public <F, T> T convertType(F from, T to) {
		return null;
	}
}
