import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
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
		
		// create && update
		IndexRequest indexRequest = new IndexRequest().index("elastic_study").type("doc_study").id("1").source(content, XContentType.JSON);
		try {
			IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println(indexResponse.getResult());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// get && isExist
		GetRequest getRequest = new GetRequest().index("elastic_study").type("doc_study").id("1");
		try {
			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
			boolean isExist = client.exists(getRequest, RequestOptions.DEFAULT);
			System.out.println(getResponse.getSourceAsString());
			System.out.println(isExist);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// delete
		DeleteRequest deleteRequest = new DeleteRequest().index("elastic_study").type("doc_study").id("2");
		try {
			DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
			System.out.println(deleteResponse.getResult());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//delete index
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest().indices(new String[] {"hangyul"});
		try {
			AcknowledgedResponse acknowledgeResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
			System.out.println(acknowledgeResponse.isAcknowledged());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
