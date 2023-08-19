package org.example.migration.model;

import java.util.List;

public class UserMigrationInfo {
    private String userId;
    private String userDomain;
    private String userType;
    private List<ClientMigrationInfo> clientInfos;

    public UserMigrationInfo(String userId, String userDomain, String userType, List<ClientMigrationInfo> clientInfos) {
        this.userId = userId;
        this.userDomain = userDomain;
        this.userType = userType;
        this.clientInfos = clientInfos;
    }

    // getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<ClientMigrationInfo> getClientInfos() {
        return clientInfos;
    }

    public void setClientInfos(List<ClientMigrationInfo> clientInfos) {
        this.clientInfos = clientInfos;
    }

    // similar to other classes, validate inputs in setters if needed.
}
