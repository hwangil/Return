package com.samsung.gilsoo.cm.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gilsoo {
	@Autowired
	Body body;
	
	public Body getBody() {
		return body;
	}
}
