package com.samsung.gilsoo.cm.rss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rss {
	@JsonProperty("channel")
	Channel channel;

	public Channel getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "Rss [channel=" + channel + "]";
	}
}
