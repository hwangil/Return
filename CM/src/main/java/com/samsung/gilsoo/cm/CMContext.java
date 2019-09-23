
package com.samsung.gilsoo.cm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class CMContext {
	private static ApplicationContext context = new AnnotationConfigApplicationContext(CMConfig.class);  
	public static ApplicationContext getContext() {
		return context;
	}
}
