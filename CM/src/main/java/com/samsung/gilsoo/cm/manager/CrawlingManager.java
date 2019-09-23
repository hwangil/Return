package com.samsung.gilsoo.cm.manager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.samsung.gilsoo.cm.article.model.ArticleInfo;
import com.samsung.gilsoo.cm.article.parser.ArticleParseInfo;
import com.samsung.gilsoo.cm.rss.model.Rss;
import com.samsung.gilsoo.parser.Parsers;

@Component
public class CrawlingManager implements Manager {
	
	@Override
	public void managing() throws Exception{
		crawling();
	}
	
	private void crawling() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		System.out.println(String.format(">> [%s] Crawling Job is started", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		CloseableHttpClient client = HttpClients.createDefault();
		URL url = Resources.getResource("article_parse_info.json");
		String articleContent = new String(Files.readAllBytes(Paths.get(url.toURI())));
		List<ArticleParseInfo> articleParsInfos = Parsers.fromJsons(articleContent, ArticleParseInfo.class, true);
		Map<String, List<ArticleInfo>> articleMap = Maps.newHashMap(); 
		articleParsInfos.stream().forEach(articleParseInfo -> {
			articleMap.put(articleParseInfo.getCompany(), Lists.newArrayList());
			try {
				CloseableHttpResponse response = client.execute(new HttpGet(articleParseInfo.getRssUrl()));
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(Parsers.xmlToJson(content));
				Rss rss = Parsers.fromJson(Parsers.xmlToJson(content), Rss.class, true);
				rss.getChannel().getItems().stream().forEach(x -> {
						try {
							ArticleInfo articleInfo = new ArticleInfo();
							articleInfo.setLink(x.getLink());
							Document doc = Jsoup.connect(x.getLink()).get();
							articleInfo.setTitle(doc.select(articleParseInfo.getTitle()).text());
							articleInfo.setContent(doc.select(articleParseInfo.getContent()).text());
							articleInfo.setCompany(articleParseInfo.getCompany());
							if(articleInfo.getContent() != null && !articleInfo.getContent().isEmpty()) {
								articleMap.get(articleParseInfo.getCompany()).add(articleInfo);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
				});
				response.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		client.close();
		
		RestHighLevelClient elasticClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		articleMap.forEach((key, value) -> {
			System.out.println(key);
			System.out.println(value.size());
			value.stream().forEach(article -> {
				try {
					IndexRequest indexRequest = new IndexRequest().index(key).type(key).id(article.getTitle())
							.source(Parsers.toJson(article), XContentType.JSON);
					IndexResponse indexResponse = elasticClient.index(indexRequest, RequestOptions.DEFAULT);
					if(indexResponse.getResult() == Result.CREATED) {
						System.out.println(indexResponse.getResult());
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
		});
		
		elasticClient.close();
	}
}
