package com.mywallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mywallet.helper.WrapperHelper;

@TestConfiguration
public class WrapperConfiguration {

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
    public WrapperHelper wrapperUtils() {
        return new WrapperHelper(objectMapper);
    }
}
