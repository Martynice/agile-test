package com.agile.test.service;

import com.agile.test.dto.AuthorizationDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiService(ObjectMapper mapper, CloseableHttpClient httpClient) {
        this.objectMapper = mapper;
        this.httpClient = httpClient;
    }

    public AuthorizationDto getAuthorizationToken() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url = "http://interview.agileengine.com/auth";
        HttpPost request = new HttpPost(url);
        try {
            request.setEntity(new StringEntity("{\"apiKey\" : \"23567b218376f79d9415\"}"));
            request.addHeader("Content-Type", "application/json");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Couldn't connect to URL " + url, e);
        }
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return objectMapper.readValue(response.getEntity().getContent(), AuthorizationDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Can not send POST request to " + url, e);
        }
    }
}
