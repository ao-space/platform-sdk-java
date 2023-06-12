package org.example;

import org.example.register.model.RegisterUserResponse;
import org.example.tutorial.UserServiceClient;

public class Main {
    public static void main(String[] args) {
        // Replace these with your actual host and API key
        String host = "http://localhost:8080";
        String apiKey = "your-api-key";

        // Create an instance of UserServiceClient
        UserServiceClient userServiceClient = new UserServiceClient(host, apiKey);

        try {
            // Define your request parameters
            String boxUUID = "your-box-uuid";
            String userId = "your-user-id";
            String subdomain = "your-subdomain";
            String userType = "user_admin"; // or "user_member"
            String clientUUID = "your-client-uuid";

            // Call the registerUser method and get the response
            String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";
            String boxRegKey = "brk_YVj29IJAD3";
            RegisterUserResponse response = userServiceClient.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);

            // Now you can use the response object
            System.out.println("Box UUID: " + response.getBoxUUID());
            System.out.println("User ID: " + response.getUserId());
            System.out.println("User Domain: " + response.getUserDomain());
            System.out.println("User Type: " + response.getUserType());
            System.out.println("Client UUID: " + response.getClientUUID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}