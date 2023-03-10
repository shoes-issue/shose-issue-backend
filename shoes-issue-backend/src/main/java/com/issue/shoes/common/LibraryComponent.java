package com.issue.shoes.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration //여기는 자동
public class LibraryComponent {

	//어노테이션을 이용해 수동으로 등록하고 있음
	@Bean
	public Gson gson() {
		return new Gson();
	}
	
}
