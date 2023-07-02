package org.example.delete.model;

import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.MethodType;

public class DeleteUserRequest extends RpcAcsRequest<DeleteUserResponse> {

    public DeleteUserRequest() {
        super("AO-Space", "v2", "DeleteUser", "ao-space");
        setMethod(MethodType.DELETE);
    }

    @Override
    public Class<DeleteUserResponse> getResponseClass() {
        return DeleteUserResponse.class;
    }
}