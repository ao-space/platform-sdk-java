package org.example;

import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.register.model.RegisterUserResponse;
import org.example.tutorial.Authentication.BoxRegKeyServiceClient;
import org.example.tutorial.Register.RegisterDeviceServiceManager;
import org.example.tutorial.Register.RegisterUserServiceClient;
import org.example.tutorial.Register.RegisterUserServiceManager;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RegisterDeviceServiceManager deviceRegistrationManager = new RegisterDeviceServiceManager("https://ao.space", "api-key");
        List<String> serviceIds = Arrays.asList("10001"); // replace with actual service IDs
        deviceRegistrationManager.registerDevice(
                "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed",
                "e9993fc787d94b6c886cbaa340f9c0f4",
                serviceIds,
                "sign"
        );
    }
}