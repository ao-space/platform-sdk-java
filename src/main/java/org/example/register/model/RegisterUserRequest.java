package org.example.register.model;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;

public class RegisterUserRequest extends RpcAcsRequest<RegisterUserResponse> {
    private String boxUUID;
    private String userId;
    private String subdomain;
    private String userType;
    private String clientUUID;

    public RegisterUserRequest() {
        super("AO-Space", "v2", "RegisterUser", "ao-space");
        setMethod(MethodType.POST);
    }

    public String getBoxUUID() {
        return this.boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        if (boxUUID == null || boxUUID.trim().isEmpty()) {
            throw new IllegalArgumentException("Box UUID cannot be null or empty.");
        }
        this.boxUUID = boxUUID;
        putBodyParameter("boxUUID", boxUUID);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        this.userId = userId;
        putBodyParameter("userId", userId);
    }

    public String getSubdomain() {
        return this.subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
        if (subdomain != null) {
            putBodyParameter("subdomain", subdomain);
        }
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
        if (userType != null) {
            putBodyParameter("userType", userType);
        }
    }

    public String getClientUUID() {
        return this.clientUUID;
    }

    public void setClientUUID(String clientUUID) {
        this.clientUUID = clientUUID;
        if (clientUUID != null) {
            putBodyParameter("clientUUID", clientUUID);
        }
    }

    @Override
    public Class<RegisterUserResponse> getResponseClass() {
        return RegisterUserResponse.class;
    }
}