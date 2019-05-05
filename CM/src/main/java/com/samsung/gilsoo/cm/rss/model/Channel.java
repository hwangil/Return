
package com.samsung.gilsoo.cm.rss.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
	@JsonProperty("title")
	String title;
	@JsonProperty("link")
	String link;
	@JsonProperty("language")
	String language;
	@JsonProperty("description")
	String description;
	@JsonProperty("lastBuildDate")
	String lastBuildDate;
	@JsonProperty("item")
	List<Item> items;
	
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getLanguage() {
		return language;
	}
	public String getDescription() {
		return description;
	}
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public List<Item> getItems() {
		return items;
	}
	
	@Override
	public String toString() {
		return "Channel [title=" + title + ", link=" + link + ", language=" + language + ", description=" + description
				+ ", lastBuildDate=" + lastBuildDate + ", items=" + items + "]";
	}

}
