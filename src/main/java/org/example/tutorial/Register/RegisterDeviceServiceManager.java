package org.example.tutorial.Register;

import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.register.model.RegisterDeviceResponse;
import org.example.tutorial.Authentication.BoxRegKeyServiceClient;

import java.util.Arrays;
import java.util.List;

public class RegisterDeviceServiceManager {
    private String host;
    private String apiKey;

    public RegisterDeviceServiceManager(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    public void registerDevice(String boxUUID, String reqId, List<String> serviceIds, String sign) {
        RegisterDeviceServiceClient registerDeviceServiceClient = new RegisterDeviceServiceClient(host, apiKey);
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
                // Call the registerDevice method and get the response
                RegisterDeviceResponse response = registerDeviceServiceClient.registerDevice(boxUUID, reqId, boxRegKey);

                // use the response object
                System.out.println("Box UUID: " + response.getBoxUUID());
                System.out.println("Client ID: " + response.getNetworkClient().getClientId());
                System.out.println("Secret Key: " + response.getNetworkClient().getSecretKey());
            } else {
                System.out.println("BoxRegKey not obtained");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//    public static void main(String[] args) {
//        RegisterDeviceServiceManager deviceRegistrationManager = new RegisterDeviceServiceManager("https://ao.space", "api-key");
//        List<String> serviceIds = Arrays.asList("10001"); // replace with actual service IDs
//        deviceRegistrationManager.registerDevice(
//                "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed",
//                "e9993fc787d94b6c886cbaa340f9c0f4",
//                serviceIds,
//                "sign"
//        );
//    }
