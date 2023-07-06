package org.example.register.model;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;

public class RegisterDeviceRequest {

    private String boxUUID;

    public String getBoxUUID() {
        return this.boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        if (boxUUID == null || boxUUID.trim().isEmpty()) {
            throw new IllegalArgumentException("Box UUID cannot be null or empty.");
        }
        this.boxUUID = boxUUID;

    }

}
