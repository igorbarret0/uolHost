package com.uolHost.uolHost_backend_challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class Client {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

}
