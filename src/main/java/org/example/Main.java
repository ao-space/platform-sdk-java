package org.example;

import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.register.model.RegisterUserResponse;
import org.example.tutorial.Authentication.BoxRegKeyServiceClient;
import org.example.tutorial.Register.RegisterUserServiceClient;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Replace these with your actual host and API key
        String host = "http://localhost:8080";
        String apiKey = "api-key";

        // Create an instance of UserServiceClient and BoxRegKeyServiceClient
        RegisterUserServiceClient registerUserServiceClient = new RegisterUserServiceClient(host, apiKey);
        BoxRegKeyServiceClient boxRegKeyServiceClient = new BoxRegKeyServiceClient(host, apiKey);

        try {
            // Define your request parameters
            String boxUUID = "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed";
            String userId = "user-id";
            String subdomain = "subdomain";
            String userType = "user_admin"; // or "user_member"
            String clientUUID = "client-uuid";
            List<String> serviceIds = Arrays.asList("10001", "10002"); // replace with actual service IDs
            String sign = "sign"; // replace with actual sign
            String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";

            // Call the obtainBoxRegKey method to get a valid boxRegKey
            ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = boxRegKeyServiceClient.obtainBoxRegKey(boxUUID, serviceIds, sign, reqId);
            String boxRegKey = null;

            // Check if tokenResults are not empty
            if (!obtainBoxRegKeyResponse.getTokenResults().isEmpty()) {
                boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();
            }

            if (boxRegKey != null) {
                // Call the registerUser method and get the response
                RegisterUserResponse response = registerUserServiceClient.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);

                // use the response object
                System.out.println("Box UUID: " + response.getBoxUUID());
                System.out.println("User ID: " + response.getUserId());
                System.out.println("User Domain: " + response.getUserDomain());
                System.out.println("User Type: " + response.getUserType());
                System.out.println("Client UUID: " + response.getClientUUID());
            } else {
                System.out.println("BoxRegKey not obtained");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}