package org.example.Client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class Client {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public Client(String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
    }

    public <T> T sendRequest(String endpoint, HttpMethod method, RequestCallback requestCallback,
                             ResponseExtractor<T> responseExtractor) {
        return restTemplate.execute(serverUrl + endpoint, method, requestCallback, responseExtractor);
    }
}
