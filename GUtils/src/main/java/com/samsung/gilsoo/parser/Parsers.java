package com.samsung.gilsoo.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.samsung.gilsoo.gutils.parser.json.JsonMappers;
import com.samsung.gilsoo.gutils.parser.xml.XmlMappers;

public class Parsers {
	
	public static <T> T fromJson(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return new JsonMappers().fromJson(content, type);
	}
	
	public static <T> List<T> fromJsons(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return new JsonMappers().fromJsons(content, type);
	}
	
	public static <T> String toJson(T object) throws JsonProcessingException {
		return new JsonMappers().toJson(object);
	}
	
	public static <T> T fromXml(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return new XmlMappers().fromXml(content, type);
	}
	
	public static <T> String toXml(T object) throws JsonProcessingException {
		return new XmlMappers().toXml(object);
	}
	
	public static String xmlToJson(String content) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return new JsonMappers().toJson(new XmlMappers().fromXml(content, Map.class));
	}
	
	public static String jsonToXml(String content) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return new XmlMappers().toXml(new JsonMappers().fromJson(content, Map.class));
	}

	
	public <F, T> T convertType(F from, T to) {
		return null;
	}
}
