import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;

public class SimpleTest {

	@Test
	public void test() throws JsonProcessingException {
		List<Simple> list = Lists.newArrayList(new Simple("voma", "kk"), new Simple("voma", "gilsoo"), new Simple("veca", "Ss"));
		List<String> key = Lists.newArrayList();
		
		list.stream().filter(x -> !key.contains(x.getType())).map( x ->{ 
			x.getType();
			key.add(x.getType());
			return x;
			});
		
		
	}

}

class Simple {
	String type;
	String name;
	
	public Simple(String string, String string2) {
		super();
		this.type = string;
		this.name = string2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Simple [type=" + type + ", name=" + name + "]";
	}

	
	
	
}
