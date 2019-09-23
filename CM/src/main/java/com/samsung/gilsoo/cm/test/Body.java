package com.samsung.gilsoo.cm.test;

import org.springframework.stereotype.Component;

@Component
public class Body {
	String height = "177";
	String weight = "72";
	
	public void printBodyInfo() {
		System.out.println(String.format("Height : %s, Weight : %s", height, weight));
	}
	public String getHeight() { 
		return height;
	}
	
	public String getWeight() {
		return weight;
	}
}
