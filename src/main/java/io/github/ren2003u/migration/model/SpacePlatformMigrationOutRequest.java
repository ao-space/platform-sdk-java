package io.github.ren2003u.migration.model;

import java.util.List;

public class SpacePlatformMigrationOutRequest {
    private List<UserDomainRouteInfo> userDomainRouteInfos;

    // getters and setters

    public List<UserDomainRouteInfo> getUserDomainRouteInfos() {
        return userDomainRouteInfos;
    }

    public void setUserDomainRouteInfos(List<UserDomainRouteInfo> userDomainRouteInfos) {
        this.userDomainRouteInfos = userDomainRouteInfos;
    }
}
