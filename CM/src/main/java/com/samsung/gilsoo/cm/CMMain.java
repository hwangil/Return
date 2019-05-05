package com.samsung.gilsoo.cm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.io.Resources;
import com.samsung.gilsoo.cm.article.parser.ArticleInfo;
import com.samsung.gilsoo.cm.rss.model.Rss;
import com.samsung.gilsoo.parser.Parsers;

public class CMMain {
	public static void main(String[] args) throws IOException, URISyntaxException {
		CloseableHttpClient client = HttpClients.createDefault();

		URL url = Resources.getResource("article_info.json");
		String articleContent = new String(Files.readAllBytes(Paths.get(url.toURI())));
		List<ArticleInfo> articles = Parsers.fromJsons(articleContent, ArticleInfo.class);
		
		articles.stream().forEach(article -> {
			System.out.println(article.getCompany());
			try {
				CloseableHttpResponse response = client.execute(new HttpGet(article.getRssUrl()));
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(Parsers.xmlToJson(content));
				Rss rss = Parsers.fromJson(Parsers.xmlToJson(content), Rss.class);
				rss.getChannel().getItems().stream().forEach(x -> {
						try {
							System.out.println(">>>>>>> "+x.getLink());
							Document doc = Jsoup.connect(x.getLink()).get();
							System.out.println(doc.select(article.getTitle()).text());
							System.out.println(doc.select(article.getContent()).text()+"\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
				
				});
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		
		
		
			

	}
}
