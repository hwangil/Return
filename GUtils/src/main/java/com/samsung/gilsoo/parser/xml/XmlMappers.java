package com.samsung.gilsoo.parser.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlMappers {
	private XmlMapper mapper;

	public XmlMappers() {
		mapper = new XmlMapper();
		mapper.registerModule(new SimpleModule().addDeserializer(Object.class, new FixedUntypedObjectDeserializer()));
	}

	public <T> T fromXml(String content, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(content, type);
	}
	
	public <T> String toXml(T object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
	
	@SuppressWarnings({ "deprecation", "serial" })
	private class FixedUntypedObjectDeserializer extends UntypedObjectDeserializer {

		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected Object mapObject(JsonParser p, DeserializationContext ctxt) throws IOException {
			String firstKey;

			JsonToken t = p.getCurrentToken();

			if (t == JsonToken.START_OBJECT) {
				firstKey = p.nextFieldName();
			} else if (t == JsonToken.FIELD_NAME) {
				firstKey = p.getCurrentName();
			} else {
				if (t != JsonToken.END_OBJECT) {
					throw ctxt.mappingException(handledType(), p.getCurrentToken());
				}
				firstKey = null;
			}

			// empty map might work; but caller may want to modify... so better
			// just give small modifiable
			LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>(2);
			if (firstKey == null)
				return resultMap;

			p.nextToken();
			resultMap.put(firstKey, deserialize(p, ctxt));

			// 03-Aug-2016, jpvarandas: handle next objects and create an array
			Set<String> listKeys = new LinkedHashSet<>();

			String nextKey;
			while ((nextKey = p.nextFieldName()) != null) {
				p.nextToken();
				if (resultMap.containsKey(nextKey)) {
					Object listObject = resultMap.get(nextKey);

					if (!(listObject instanceof List)) {
						listObject = new ArrayList<>();
						((List) listObject).add(resultMap.get(nextKey));

						resultMap.put(nextKey, listObject);
					}

					((List) listObject).add(deserialize(p, ctxt));

					listKeys.add(nextKey);

				} else {
					resultMap.put(nextKey, deserialize(p, ctxt));

				}
			}
			return resultMap;
		}
	}
}
