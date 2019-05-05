package com.samsung.gilsoo.cm.rss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	@JsonProperty("title")
	String title;
	@JsonProperty("link")
	String link;
	@JsonProperty("description")
	String description;
	
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "Item [title=" + title + ", link=" + link + ", description=" + description + "]";
	}
	
	
	
}
