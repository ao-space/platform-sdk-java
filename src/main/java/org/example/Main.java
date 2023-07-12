package org.example;

import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.Client.UnifiedApiClient;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space", "api-key", 10);

        List<String> serviceIds = Arrays.asList("10001");
        String boxUUID = "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed";
        String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";
        String sign = "sign";

        Future<ObtainBoxRegKeyResponse> future = client.obtainBoxRegKey(boxUUID, serviceIds, sign, reqId);
        try {
            ObtainBoxRegKeyResponse response = future.get();  // Blocks until the API response is available
            System.out.println("Box UUID: " + response.getBoxUUID());
            for (ObtainBoxRegKeyResponse.TokenResult tokenResult : response.getTokenResults()) {
                System.out.println("Service Id: " + tokenResult.getServiceId());
                System.out.println("Box Reg Key: " + tokenResult.getBoxRegKey());
                System.out.println("Expires At: " + tokenResult.getExpiresAt());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            client.shutdown();
        }
    }

}