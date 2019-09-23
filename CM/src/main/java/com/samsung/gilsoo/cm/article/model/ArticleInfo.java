package com.samsung.gilsoo.cm.article.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleInfo {
	@JsonProperty("company")
	String company;
	@JsonProperty("title")
	String title;
	@JsonProperty("content")
	String content;
	@JsonProperty("link")
	String link;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ArticleInfo [company=" + company + ", title=" + title + ", content=" + content + ", link=" + link + "]";
	}

}
