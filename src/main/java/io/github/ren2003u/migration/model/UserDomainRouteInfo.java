package io.github.ren2003u.migration.model;

public class UserDomainRouteInfo {
    private String userId;
    private String userDomainRedirect;

    public UserDomainRouteInfo(String userId, String userDomainRedirect) {
        this.userId = userId;
        this.userDomainRedirect = userDomainRedirect;
    }
    public UserDomainRouteInfo() {
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDomainRedirect() {
        return userDomainRedirect;
    }

    public void setUserDomainRedirect(String userDomainRedirect) {
        this.userDomainRedirect = userDomainRedirect;
    }
}
