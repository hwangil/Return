package com.samsung.gilsoo.cm.article.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleInfo {
	@JsonProperty("company")
	String company;
	@JsonProperty("title")
	String title;
	@JsonProperty("content")
	String content;
}
