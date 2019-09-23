import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.samsung.gilsoo.cm.article.parser.ArticleParseInfo;
import com.samsung.gilsoo.parser.Parsers;

public class ParseTest {

	@Test
	public void test() throws JsonProcessingException {
		List<ArticleParseInfo> articles = Lists.newArrayList();
		
		ArticleParseInfo article1 = new ArticleParseInfo();
		article1.setCompany("joongang");
		article1.setTitle("#article_title");
		article1.setContent("#article_body");
		article1.setRssUrl("https://rss.joins.com/joins_it_list.xml");
		
		ArticleParseInfo article2 = new ArticleParseInfo();
		article2.setCompany("hangyul");
		article2.setTitle(".article-head .title");
		article2.setContent(".article-text .text");
		article2.setRssUrl("http://www.hani.co.kr/rss/science/");
		
		ArticleParseInfo article3 = new ArticleParseInfo();
		article3.setCompany("kynghyang");
		article3.setTitle("#articleTtitle");
		article3.setContent(".art_body");
		article3.setRssUrl("http://www.khan.co.kr/rss/rssdata/it_news.xml");
		
		ArticleParseInfo article4 = new ArticleParseInfo();
		article4.setCompany("donga");
		article4.setTitle(".article_title .title");
		article4.setContent(".article_view .article_txt");
		article4.setRssUrl("http://rss.donga.com/science.xml");
		
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
		articles.add(article4);
		
		System.out.println(Parsers.toJson(articles));
	}

}
