package org.example.tutorial.Register;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.register.model.RegisterDeviceRequest;
import org.example.register.model.RegisterDeviceResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RegisterDeviceServiceClient {

    private String host;
    private String apiKey;

    public RegisterDeviceServiceClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    public RegisterDeviceResponse registerDevice(String boxUUID, String reqId, String boxRegKey) throws Exception {
        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setBoxUUID(boxUUID);

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        String requestBody = objectMapper.writeValueAsString(request);

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(host + "/v2/platform/boxes"))
                        .header("Content-Type", "application/json")
                        .header("Api-Key", apiKey)
                        .header("Request-Id", reqId)
                        .header("Box-Reg-Key", boxRegKey)
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build(),
                HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() != 200) {
            throw new Exception("Error response from the server: " + httpResponse.body());
        }

        RegisterDeviceResponse response = objectMapper.readValue(httpResponse.body(), RegisterDeviceResponse.class);
        return response;
    }
}
