import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ElasticTest {

	@Test
	public void test() throws JsonProcessingException {
		String content = "{\"name\" : \"gilsoo22\"}";
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		IndexRequest request = new IndexRequest("test","doc","2").source(content, XContentType.JSON);
		try {
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);
			System.out.println(response.getIndex());
			System.out.println(response.getType());
			System.out.println(response.getResult());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
