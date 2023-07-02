package org.example.tutorial.delete;

import org.example.delete.model.DeleteUserRequest;
import org.example.delete.model.DeleteUserResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteUserServiceClient {
    private String host;
    private String apiKey;

    public DeleteUserServiceClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    public DeleteUserResponse deleteUser(String boxUUID, String userId, String reqId, String boxRegKey) throws Exception {
        DeleteUserRequest request = new DeleteUserRequest();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                        .uri(URI.create(host + "/v2/platform/boxes/" + boxUUID + "/users/" + userId))
                        .header("Content-Type", "application/json")
                        .header("Api-Key", apiKey)
                        .header("Request-Id", reqId)
                        .header("Box-Reg-Key", boxRegKey)
                        .method("DELETE", HttpRequest.BodyPublishers.noBody())
                        .build(),
                HttpResponse.BodyHandlers.ofString());

        // Handle error response from the server
        if (httpResponse.statusCode() != 204) {
            throw new Exception("Error response from the server: " + httpResponse.body());
        }

        DeleteUserResponse response = new DeleteUserResponse();
        return response;
    }
}
