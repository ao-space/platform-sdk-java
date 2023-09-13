package org.example.migration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
public class SpacePlatformMigrationOutResponse {
    private String boxUUID;
    private List<UserDomainRouteInfo> userDomainRouteInfos;

    // getters and setters

    public String getBoxUUID() {
        return boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        this.boxUUID = boxUUID;
    }

    public List<UserDomainRouteInfo> getUserDomainRouteInfos() {
        return userDomainRouteInfos;
    }

    public void setUserDomainRouteInfos(List<UserDomainRouteInfo> userDomainRouteInfos) {
        this.userDomainRouteInfos = userDomainRouteInfos;
    }
}
