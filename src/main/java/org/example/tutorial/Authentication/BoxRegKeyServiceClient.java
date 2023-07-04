package org.example.tutorial.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Authentication.model.ObtainBoxRegKeyRequest;
import org.example.Authentication.model.ObtainBoxRegKeyResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class BoxRegKeyServiceClient {

    private String host;
    private String apiKey;

    public BoxRegKeyServiceClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    public ObtainBoxRegKeyResponse obtainBoxRegKey(String boxUUID, List<String> serviceIds, String sign, String reqId) throws Exception {
        ObtainBoxRegKeyRequest request = new ObtainBoxRegKeyRequest();
        request.setBoxUUID(boxUUID);
        request.setServiceIds(serviceIds);
        request.setSign(sign);

        // Convert the request object to a JSON string
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String requestBody = objectMapper.writeValueAsString(request);

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(host + "/v2/platform/auth/box_reg_keys"))
                        .header("Content-Type", "application/json")
                        .header("Api-Key", apiKey)
                        .header("Request-Id", reqId)
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build(),
                HttpResponse.BodyHandlers.ofString());

        // Handle error response from the server
        if (httpResponse.statusCode() != 200) {
            throw new Exception("Error response from the server: " + httpResponse.body());
        }

        // Convert the HTTP response body from a JSON string to an ObtainBoxRegKeyResponse object
        ObtainBoxRegKeyResponse response = objectMapper.readValue(httpResponse.body(), ObtainBoxRegKeyResponse.class);
        return response;
    }
}
