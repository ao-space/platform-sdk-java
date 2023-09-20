package io.github.ren2003u.migration.model;

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
