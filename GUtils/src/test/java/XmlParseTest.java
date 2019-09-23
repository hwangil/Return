import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.samsung.gilsoo.parser.xml.XmlMappers;


public class XmlParseTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		String contents = 
				"<vnfd>\r\n" + 	
				"	<vnfcs>\r\n" + 
				"		<vnfc>vnfc1</vnfc>\r\n" + 
				"		<vnfc>vnfc2</vnfc>\r\n" + 
				"	</vnfcs>\r\n" + 
				"	<config_param>\r\n" + 
				"		<param_id>cdata_contents</param_id>\r\n" + 
				"		<value><![CDATA[gilsoo.hwanng <sdds> <sdasd> test]]></value>\r\n" + 
				"	</config_param>	\r\n" + 
				"</vnfd>";
		XmlMappers mapper = new XmlMappers();
		Map map = mapper.fromXml(contents, Map.class);
				
		System.out.println(map.toString());
		
		((Map)map.get("config_param")).put("value", "<![CDATA[gilsoo.hwanng <sdds> <sdasd> test]]>");
		System.out.println(map.toString());
		String xml = mapper.toXml(map);
		System.out.println(xml);
		xml = xml.replaceAll("&lt;","<");
		xml = xml.replaceAll("&gt;",">");
		System.out.println(xml);
		System.out.println(mapper.fromXml(xml, Map.class));
	}


}
