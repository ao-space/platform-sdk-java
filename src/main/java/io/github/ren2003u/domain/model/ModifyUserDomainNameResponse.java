package io.github.ren2003u.domain.model;

import java.util.List;

public class ModifyUserDomainNameResponse {

    private boolean success;
    private String boxUUID;
    private String userId;
    private String subdomain;
    private String code;
    private String error;
    private List<String> recommends;

    public List<String> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<String> recommends) {
        this.recommends = recommends;
    }
// Getters and setters with encapsulation

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBoxUUID() {
        return boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        this.boxUUID = boxUUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
