package org.example.migration.model;

import java.util.List;

public class SpacePlatformMigrationRequest {
    private String networkClientId;
    private List<UserMigrationInfo> userInfos;

    // getters and setters

    public String getNetworkClientId() {
        return networkClientId;
    }

    public void setNetworkClientId(String networkClientId) {
        this.networkClientId = networkClientId;
    }

    public List<UserMigrationInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserMigrationInfo> userInfos) {
        this.userInfos = userInfos;
    }

    // similar to other classes, validate inputs in setters if needed.
}
