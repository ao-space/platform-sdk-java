package org.example.register.model;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;

public class RegisterDeviceRequest extends RpcAcsRequest<RegisterDeviceResponse> {

    private String boxUUID;

    public RegisterDeviceRequest() {
        super("AO-Space", "v2", "RegisterDevice", "ao-space");
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

    @Override
    public Class<RegisterDeviceResponse> getResponseClass() {
        return RegisterDeviceResponse.class;
    }
}
