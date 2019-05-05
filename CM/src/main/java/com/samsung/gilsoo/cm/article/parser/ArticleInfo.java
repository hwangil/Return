package com.samsung.gilsoo.cm.article.parser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleInfo {
	@JsonProperty("company")
	String company;
	@JsonProperty("title")
	String title;
	@JsonProperty("content")
	String content;
	@JsonProperty("rss_url")
	String rssUrl;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRssUrl() {
		return rssUrl;
	}

	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	@Override
	public String toString() {
		return "ArticleInfo [company=" + company + ", title=" + title + ", content=" + content + ", rssUrl=" + rssUrl
				+ "]";
	}

}
