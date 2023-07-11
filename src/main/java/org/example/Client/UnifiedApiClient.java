package org.example.Client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Authentication.model.ObtainBoxRegKeyRequest;
import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.register.model.RegisterDeviceRequest;
import org.example.register.model.RegisterDeviceResponse;
import org.example.register.model.RegisterUserRequest;
import org.example.register.model.RegisterUserResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UnifiedApiClient {

    private final String host;
    private final String apiKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;

    public UnifiedApiClient(String host, String apiKey, int poolSize) {
        this.host = host;
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public Future<ObtainBoxRegKeyResponse> obtainBoxRegKey(String boxUUID, List<String> serviceIds, String sign, String reqId) {
        ObtainBoxRegKeyRequest request = new ObtainBoxRegKeyRequest();
        request.setBoxUUID(boxUUID);
        request.setServiceIds(serviceIds);
        request.setSign(sign);

        return executorService.submit(() -> sendRequest("/v2/platform/auth/box_reg_keys", "POST", reqId, request, ObtainBoxRegKeyResponse.class, null));
    }

    public Future<RegisterDeviceResponse> registerDevice(String boxUUID, String reqId, String boxRegKey) {
        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setBoxUUID(boxUUID);

        return executorService.submit(() -> sendRequest("/v2/platform/boxes", "POST", reqId, request, RegisterDeviceResponse.class, boxRegKey));
    }
    public Future<RegisterUserResponse> registerUser(String boxUUID, String userId, String subdomain, String userType, String clientUUID, String reqId, String boxRegKey) {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserId(userId);
        request.setSubdomain(subdomain);
        request.setUserType(userType);
        request.setClientUUID(clientUUID);

        return executorService.submit(() -> sendRequest("/v2/platform/boxes/" + boxUUID + "/users", "POST", reqId, request, RegisterUserResponse.class, boxRegKey));
    }

    public Future<Void> deleteDevice(String boxUUID, String reqId, String boxRegKey) {
        return executorService.submit(() -> {
            sendRequest("/v2/platform/boxes/" + boxUUID, "DELETE", reqId, null, Void.class, boxRegKey);
            return null;
        });
    }

    private <T> T sendRequest(String path, String method, String reqId, Object requestObject, Class<T> responseClass, String boxRegKey) throws Exception {
        String requestBody = objectMapper.writeValueAsString(requestObject);

        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(host + path))
                .header("Content-Type", "application/json")
                .header("Api-Key", apiKey)
                .header("Request-Id", reqId);

        if (boxRegKey != null) {
            httpRequestBuilder.header("Box-Reg-Key", boxRegKey);
        }

        if (method.equals("POST")) {
            httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(requestBody));
        } else if (method.equals("GET")) {
            httpRequestBuilder.GET();
        } else if (method.equals("DELETE")) {
            httpRequestBuilder.DELETE();
        }

        HttpResponse<String> httpResponse = httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() != 200) {
            throw new Exception("Error response from the server: " + httpResponse.body());
        }

        return objectMapper.readValue(httpResponse.body(), responseClass);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
