package com.hernaval.judomanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.hernaval.judomanager.repository.rest.event.ActivityEventHandler;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {

    @Bean
    ActivityEventHandler activityEventHandler() {
		return new ActivityEventHandler();
	}
}
