package org.example.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.authentication.model.ObtainBoxRegKeyRequest;
import org.example.authentication.model.ObtainBoxRegKeyResponse;
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
import java.util.List;

public class UnifiedApiClient {

    private final String host;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static Logger logger = LoggerFactory.getLogger(UnifiedApiClient.class);

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
    }

    public ObtainBoxRegKeyResponse obtainBoxRegKey(String boxUUID, List<String> serviceIds, String sign, String reqId) throws Exception {
        if (!isApiAvailable("POST", "auth/box_reg_keys")) {
            throw new Exception("API not available: POST auth/box_reg_keys");
        }
        ObtainBoxRegKeyRequest request = new ObtainBoxRegKeyRequest();
        request.setBoxUUID(boxUUID);
        request.setServiceIds(serviceIds);
        request.setSign(sign);

        return sendRequest("/v2/platform/auth/box_reg_keys", "POST", reqId, request, ObtainBoxRegKeyResponse.class, null,"obtainBoxRegKey");
    }

    public RegisterDeviceResponse registerDevice(String boxUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes")) {
            throw new Exception("API not available: POST boxes");
        }
        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setBoxUUID(boxUUID);

        return sendRequest("/v2/platform/boxes", "POST", reqId, request, RegisterDeviceResponse.class, boxRegKey,"registerDevice");
    }

    public RegisterUserResponse registerUser(String boxUUID, String userId, String subdomain, String userType, String clientUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/users")) {
            throw new Exception("API not available: POST boxes/users");
        }
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUserId(userId);
        request.setSubdomain(subdomain);
        request.setUserType(userType);
        request.setClientUUID(clientUUID);

        return sendRequest("/v2/platform/boxes/" + boxUUID + "/users", "POST", reqId, request, RegisterUserResponse.class, boxRegKey,"registerUser");
    }

    public void deleteDevice(String boxUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("DELETE", "boxes")) {
            throw new Exception("API not available: DELETE boxes");
        }
        sendRequest("/v2/platform/boxes/" + boxUUID, "DELETE", reqId, null, Void.class, boxRegKey,"deleteDevice");
    }

    public void deleteUser(String boxUUID, String userId, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("DELETE", "boxes/users")) {
            throw new Exception("API not available: DELETE boxes/users");
        }
        sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId, "DELETE", reqId, null, Void.class, boxRegKey,"deleteUser");
    }

    public void deleteClient(String boxUUID, String userId, String clientUUID, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("DELETE", "boxes/users/clients")) {
            throw new Exception("API not available: DELETE boxes/users/clients");
        }
        sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId + "/clients/" + clientUUID, "DELETE", reqId, null, Void.class, boxRegKey,"deleteClient");
    }

    public RegisterClientResponse registerClient(String boxUUID, String userId, String clientUUID, String clientType, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/users/clients")) {
            throw new Exception("API not available: POST boxes/users/clients");
        }
        RegisterClientRequest request = new RegisterClientRequest();
        request.setClientUUID(clientUUID);
        request.setClientType(clientType);

        return sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId + "/clients", "POST", reqId, request, RegisterClientResponse.class, boxRegKey,"registerClient");
    }

    public SpacePlatformMigrationResponse migrateSpacePlatform(String boxUUID, String networkClientId, List<UserMigrationInfo> userInfos, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/migration")) {
            throw new Exception("API not available: POST boxes/migration");
        }
        SpacePlatformMigrationRequest request = new SpacePlatformMigrationRequest();
        request.setNetworkClientId(networkClientId);
        request.setUserInfos(userInfos);

        return sendRequest("/v2/platform/boxes/" + boxUUID + "/migration", "POST", reqId, request, SpacePlatformMigrationResponse.class, boxRegKey,"migrateSpacePlatform");
    }
    public SpacePlatformMigrationOutResponse migrateSpacePlatformOut(String boxUUID, List<UserDomainRouteInfo> userDomainRouteInfos, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/route")) {
            throw new Exception("API not available: POST boxes/route");
        }
        SpacePlatformMigrationOutRequest request = new SpacePlatformMigrationOutRequest();
        request.setUserDomainRouteInfos(userDomainRouteInfos);

        return sendRequest("/v2/platform/boxes/" + boxUUID + "/route", "POST", reqId, request, SpacePlatformMigrationOutResponse.class, boxRegKey,"migrateSpacePlatformOut");
    }

    public GenerateUserDomainNameResponse generateUserDomainName(String boxUUID, String effectiveTime, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/subdomains")) {
            throw new Exception("API not available: POST boxes/subdomains");
        }
        GenerateUserDomainNameRequest request = new GenerateUserDomainNameRequest();
        request.setEffectiveTime(effectiveTime);

        return sendRequest("/v2/platform/boxes/" + boxUUID + "/subdomains", "POST", reqId, request, GenerateUserDomainNameResponse.class, boxRegKey,"generateUserDomainName");
    }

    public ModifyUserDomainNameResponse modifyUserDomainName(String boxUUID, String userId, String subdomain, String reqId, String boxRegKey) throws Exception {
        if (!isApiAvailable("POST", "boxes/users/subdomains")) {
            throw new Exception("API not available: POST boxes/users/subdomains");
        }
        ModifyUserDomainNameRequest request = new ModifyUserDomainNameRequest();
        request.setSubdomain(subdomain);

        return sendRequest("/v2/platform/boxes/" + boxUUID + "/users/" + userId + "/subdomains", "POST", reqId, request, ModifyUserDomainNameResponse.class, boxRegKey,"modifyUserDomainName");
    }
    private boolean isApiAvailable(String method, String briefUri) throws Exception {
        String path = "/v2/platform/ability";
        HttpResponse<String> httpResponse = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(host + path))
                .header("Content-Type", "application/json")
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() != 200) {
            throw new Exception("Failed to fetch available APIs. Status code: " + httpResponse.statusCode());
        }

        String responseBody = httpResponse.body();
        // Parse the response and check if the desired API is listed
        JsonNode rootNode = objectMapper.readTree(responseBody);
        JsonNode platformApisNode = rootNode.get("platformApis");
        if (platformApisNode.isArray()) {
            for (JsonNode apiNode : platformApisNode) {
                if (method.equalsIgnoreCase(apiNode.get("method").asText()) &&
                        briefUri.equalsIgnoreCase(apiNode.get("briefUri").asText())) {
                    return true;
                }
            }
        }
        return false;
    }

    private <T> T sendRequest(String path, String method, String reqId, Object requestObject, Class<T> responseClass, String boxRegKey, String publicFunctionName) throws Exception {
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

        if (httpResponse.statusCode() != 200) {
            throw new Exception("Request failed with status code: " + httpResponse.statusCode());
        }

        return objectMapper.readValue(httpResponse.body(), responseClass);
    }
}
