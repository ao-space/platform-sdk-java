package org.example.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.authentication.model.ObtainBoxRegKeyRequest;
import org.example.authentication.model.ObtainBoxRegKeyResponse;
import org.example.domain.errorHandle.ApiResponse;
import org.example.domain.errorHandle.ErrorClass;
import org.example.migration.model.*;
import org.example.domain.model.GenerateUserDomainNameRequest;
import org.example.domain.model.GenerateUserDomainNameResponse;
import org.example.domain.model.ModifyUserDomainNameRequest;
import org.example.domain.model.ModifyUserDomainNameResponse;
import org.example.register.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UnifiedApiClient {

    private final String host;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static Logger logger = LoggerFactory.getLogger(UnifiedApiClient.class);

    // Scheduled executor for periodic updates
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Set<ApiInfo> availableApis = new HashSet<>();

    public UnifiedApiClient(String host, Logger customLogger) {
        this.host = host;

        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        if (customLogger != null) {
            logger = customLogger;
        } else {
            logger = LoggerFactory.getLogger(UnifiedApiClient.class);
        }
        // Synchronously update the available APIs during initialization
        updateAvailableApis();
        // Start the periodic update of available APIs
        scheduler.scheduleAtFixedRate(this::updateAvailableApis, 1, 1, TimeUnit.HOURS); // Update every hour
    }
    public void updateAvailableApis() {
        try {
            String path = "/v2/platform/ability";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(host + path))
                    .header("Content-Type", "application/json")
                    .header("Request-Id", "e9993fc787d94b6c886cbaa340f9c0f4")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            logger.info("HTTP Response Status: {}", httpResponse.statusCode());
            logger.info("HTTP Response Body: {}", httpResponse.body());

            if (httpResponse.statusCode() == 200) {
                String responseBody = httpResponse.body();
                JsonNode rootNode = objectMapper.readTree(responseBody);
                JsonNode platformApisNode = rootNode.get("platformApis");
                if (platformApisNode.isArray()) {
                    availableApis.clear(); // Clear the existing APIs
                    for (JsonNode apiNode : platformApisNode) {
                        // Convert the method to uppercase before adding to the set
                        availableApis.add(new ApiInfo(apiNode.get("method").asText().toUpperCase(), apiNode.get("briefUri").asText()));
                    }
                }
                // Logging the updated APIs
                logger.info("Updated APIs: {}", availableApis);
            }
        } catch (Exception e) {
            logger.error("Failed to update available APIs", e);
        }
    }

    public <T> ApiResponse<T> obtainBoxRegKey(String boxUUID, List<String> serviceIds, String reqId) throws Exception {
        // Logging the state of availableApis right before the check
        logger.info("Checking API availability. Current APIs: {}", availableApis);

        if (!isApiAvailable("POST", "auth/box_reg_keys")) {
            throw new Exception("API not available: POST auth/box_reg_keys");
        }
        ObtainBoxRegKeyRequest request = new ObtainBoxRegKeyRequest();
        request.setBoxUUID(boxUUID);
        request.setServiceIds(serviceIds);

        return (ApiResponse<T>) sendRequest("/v2/platform/auth/box_reg_keys", "POST", reqId, request, ObtainBoxRegKeyResponse.class, null);
    }

    public <T> ApiResponse<T> registerDevice(String boxUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes")) {
            throw new Exception("API not available: POST boxes");
        }
        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setBoxUUID(boxUUID);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes", "POST", reqId, request, RegisterDeviceResponse.class, boxRegKey);
    }

    public <T> ApiResponse<T> registerUser(String boxUUID, String userId, String subdomain, String userType, String clientUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/{box_uuid}/users")) {
            throw new Exception("API not available: POST boxes/{box_uuid}/users");
        }
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserId(userId);
        request.setSubdomain(subdomain);
        request.setUserType(userType);
        request.setClientUUID(clientUUID);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes/" + boxUUID + "/users", "POST", reqId, request, RegisterUserResponse.class, boxRegKey);
    }

    public void deleteDevice(String boxUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("DELETE", "boxes/{box_uuid}")) {
            throw new Exception("API not available: DELETE boxes/{box_uuid}");
        }
        sendRequest("/v2/platform/boxes/" + boxUUID, "DELETE", reqId, null, Void.class, boxRegKey);
    }

    public void deleteUser(String boxUUID, String userId, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("DELETE", "boxes/{box_uuid}/users/{user_id}")) {
            throw new Exception("API not available: DELETE boxes/{box_uuid}/users/{user_id}");
        }
        sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId, "DELETE", reqId, null, Void.class, boxRegKey);
    }

    public void deleteClient(String boxUUID, String userId, String clientUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("DELETE", "boxes/{box_uuid}/users/{user_id}/clients/{client_uuid}")) {
            throw new Exception("API not available: DELETE boxes/{box_uuid}/users/{user_id}/clients/{client_uuid}");
        }
        sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId + "/clients/" + clientUUID, "DELETE", reqId, null, Void.class, boxRegKey);
    }

    public <T> ApiResponse<T> registerClient(String boxUUID, String userId, String clientUUID, String clientType, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/{box_uuid}/users/{user_id}/clients")) {
            throw new Exception("API not available: POST boxes/{box_uuid}/users/{user_id}/clients");
        }
        RegisterClientRequest request = new RegisterClientRequest();
        request.setClientUUID(clientUUID);
        request.setClientType(clientType);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId + "/clients", "POST", reqId, request, RegisterClientResponse.class, boxRegKey);
    }

    public <T> ApiResponse<T> migrateSpacePlatform(String boxUUID, String networkClientId, List<UserMigrationInfo> userInfos, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/{box_uuid}/migration")) {
            throw new Exception("API not available: POST boxes/{box_uuid}/migration");
        }
        SpacePlatformMigrationRequest request = new SpacePlatformMigrationRequest();
        request.setNetworkClientId(networkClientId);
        request.setUserInfos(userInfos);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes/" + boxUUID + "/migration", "POST", reqId, request, SpacePlatformMigrationResponse.class, boxRegKey);
    }
    public <T> ApiResponse<T> migrateSpacePlatformOut(String boxUUID, List<UserDomainRouteInfo> userDomainRouteInfos, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/{box_uuid}/route")) {
            throw new Exception("API not available: POST boxes/{box_uuid}/route");
        }
        SpacePlatformMigrationOutRequest request = new SpacePlatformMigrationOutRequest();
        request.setUserDomainRouteInfos(userDomainRouteInfos);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes/" + boxUUID + "/route", "POST", reqId, request, SpacePlatformMigrationOutResponse.class, boxRegKey);
    }

    public <T> ApiResponse<T> generateUserDomainName(String boxUUID, String effectiveTime, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/{box_uuid}/subdomains")) {
            throw new Exception("API not available: POST boxes/{box_uuid}/subdomains");
        }
        GenerateUserDomainNameRequest request = new GenerateUserDomainNameRequest();
        request.setEffectiveTime(effectiveTime);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes/" + boxUUID + "/subdomains", "POST", reqId, request, GenerateUserDomainNameResponse.class, boxRegKey);
    }

    public <T> ApiResponse<T> modifyUserDomainName(String boxUUID, String userId, String subdomain, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("PUT", "boxes/{box_uuid}/users/{user_id}/subdomain")) {
            throw new Exception("API not available: PUT boxes/{box_uuid}/users/{user_id}/subdomain");
        }
        ModifyUserDomainNameRequest request = new ModifyUserDomainNameRequest();
        request.setSubdomain(subdomain);

        return (ApiResponse<T>) sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId + "/subdomain", "PUT", reqId, request, ModifyUserDomainNameResponse.class, boxRegKey);
    }
    private boolean isApiAvailable(String method, String briefUri) {
        // Convert both the method and briefUri to uppercase before checking its availability
        logger.info("Available APIs: {}", availableApis);
        return availableApis.contains(new ApiInfo(method.toUpperCase(), briefUri));
    }

    private <T> ApiResponse<T> sendRequest(String path, String method, String reqId, Object requestObject, Class<T> responseClass, String boxRegKey) throws Exception {
        String requestBody = requestObject == null ? "" : objectMapper.writeValueAsString(requestObject);

        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(host + path))
                .header("Content-Type", "application/json")
                .header("Request-Id", reqId);

        if (boxRegKey != null) {
            httpRequestBuilder.header("Box-Reg-Key", boxRegKey);
        }

        switch (method) {
            case "POST":
                httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(requestBody));
                break;
            case "PUT":
                httpRequestBuilder.PUT(HttpRequest.BodyPublishers.ofString(requestBody));
                break;
            case "DELETE":
                httpRequestBuilder.DELETE();
                break;
            case "GET":
                httpRequestBuilder.GET();
                break;
        }

        HttpResponse<String> httpResponse = httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());

        String logTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logger.info("Time: {}, Request: Method: {}, Path: {}, Request Id: {}, Request Body: {}, BoxRegKey: {}", logTime, method, path, reqId, requestBody, boxRegKey);
        logger.info("Time: {}, Response: Status Code: {}, Response Body: {}", logTime, httpResponse.statusCode(), httpResponse.body());
        if(httpResponse.statusCode() == 400){
            ErrorClass error = objectMapper.readValue(httpResponse.body(), ErrorClass.class);
            ApiResponse<T> response = new ApiResponse<>();
            response.setError(error);
            return response;
        }
        if (httpResponse.statusCode() != 200 && httpResponse.statusCode() != 204 && httpResponse.statusCode() != 302){
            throw new Exception("Request failed with status code: " + httpResponse.statusCode());
        }
        if (httpResponse.statusCode() == 204) {
            return null;
        }
        T responseData = objectMapper.readValue(httpResponse.body(), responseClass);
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(responseData);
        return response;
    }
    // Inner class to represent an API's method and brief URI
    private static class ApiInfo {
        private final String method;
        private final String briefUri;

        public ApiInfo(String method, String briefUri) {
            this.method = method;
            this.briefUri = briefUri;
        }

        @Override
        public boolean equals(Object o) {
            // Logging inside the equals method
            logger.info("Checking equality for ApiInfo: {}", o);

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ApiInfo apiInfo = (ApiInfo) o;
            return Objects.equals(method, apiInfo.method) &&
                    Objects.equals(briefUri, apiInfo.briefUri);
        }

        @Override
        public int hashCode() {
            // Logging inside the hashCode method
            //logger.info("Generating hashCode for ApiInfo: method={}, briefUri={}", method, briefUri);

            return Objects.hash(method, briefUri);
        }
    }
}
