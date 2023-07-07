package org.example.tutorial.Register;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.Authentication.model.ObtainBoxRegKeyRequest;
import org.example.register.model.RegisterUserRequest;
import org.example.register.model.RegisterUserResponse;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RegisterUserServiceClient {

    private String host;
    private String apiKey;

    public RegisterUserServiceClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    public RegisterUserResponse registerUser(String boxUUID, String userId, String subdomain, String userType, String clientUUID, String reqId, String boxRegKey) throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setBoxUUID(boxUUID);
        request.setUserId(userId);
        request.setSubdomain(subdomain);
        request.setUserType(userType);
        request.setClientUUID(clientUUID);

        // Convert the RegisterUserRequest object to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        // Configure the ObjectMapper to only include non-null and non-empty properties during serialization
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String requestBody = objectMapper.writeValueAsString(request);

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(host + "/v2/platform/boxes/" + boxUUID + "/users"))
                        .header("Content-Type", "application/json")
                        .header("Api-Key", apiKey)
                        .header("Request-Id", reqId)
                        .header("Box-Reg-Key", boxRegKey)
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build(),
                HttpResponse.BodyHandlers.ofString());

        // Handle error response from the server
        if (httpResponse.statusCode() != 200) {
            throw new Exception("Error response from the server: " + httpResponse.body());
        }

        RegisterUserResponse response = new RegisterUserResponse();

        return response;
    }
}
