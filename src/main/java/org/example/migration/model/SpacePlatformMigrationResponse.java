package org.example.migration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.register.model.RegisterDeviceResponse;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpacePlatformMigrationResponse {
    private String boxUUID;
    private RegisterDeviceResponse.NetworkClient networkClient;  // Assuming NetworkClient is already defined as per your response schema.
    private List<UserMigrationInfo> userInfos;

    // getters and setters

    public String getBoxUUID() {
        return boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        this.boxUUID = boxUUID;
    }

    public RegisterDeviceResponse.NetworkClient getNetworkClient() {
        return networkClient;
    }

    public void setNetworkClient(RegisterDeviceResponse.NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public List<UserMigrationInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserMigrationInfo> userInfos) {
        this.userInfos = userInfos;
    }

    // similar to other classes, validate inputs in setters if needed.
}
