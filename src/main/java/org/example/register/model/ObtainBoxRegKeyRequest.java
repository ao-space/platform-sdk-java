package org.example.register.model;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;

public class ObtainBoxRegKeyRequest extends RpcAcsRequest<ObtainBoxRegKeyResponse> {

    // Field for Box UUID
    private String boxUUID;

    // Field for Service IDs
    private String serviceIds;

    // Field for Sign
    private String sign;

    /**
     * Default constructor.
     */
    public ObtainBoxRegKeyRequest() {
        super("AO-Space", "v2", "ObtainBoxRegKey", "ao-space");
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

    public String getServiceIds() {
        return this.serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        if (serviceIds == null || serviceIds.trim().isEmpty()) {
            throw new IllegalArgumentException("Service IDs cannot be null or empty.");
        }
        this.serviceIds = serviceIds;
        putBodyParameter("serviceIds", serviceIds);
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
        if(sign != null){
            putBodyParameter("sign", sign);
        }
    }

    @Override
    public Class<ObtainBoxRegKeyResponse> getResponseClass() {
        return ObtainBoxRegKeyResponse.class;
    }
}
