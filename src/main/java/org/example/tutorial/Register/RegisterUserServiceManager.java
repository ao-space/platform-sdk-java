package org.example.tutorial.Register;

import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.register.model.RegisterUserResponse;
import org.example.tutorial.Authentication.BoxRegKeyServiceClient;

import java.util.List;

public class RegisterUserServiceManager {
    private String host;
    private String apiKey;

    public RegisterUserServiceManager(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    public void registerUser(String boxUUID, String userId, String subdomain,
                             String userType, String clientUUID, List<String> serviceIds,
                             String sign, String reqId) {
        RegisterUserServiceClient registerUserServiceClient = new RegisterUserServiceClient(host, apiKey);
        BoxRegKeyServiceClient boxRegKeyServiceClient = new BoxRegKeyServiceClient(host, apiKey);

        try {
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

//    public static void main(String[] args) {
//        UserRegistrationManager userRegistrationManager = new UserRegistrationManager("http://localhost:8080", "api-key");
//        List<String> serviceIds = Arrays.asList("10001"); // replace with actual service IDs
//        userRegistrationManager.registerUser("364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed",
//                "user-id",
//                "subdomain",
//                "user_admin",
//                "client-uuid",
//                serviceIds,
//                "sign",
//                "e9993fc787d94b6c886cbaa340f9c0f4");
//    }


