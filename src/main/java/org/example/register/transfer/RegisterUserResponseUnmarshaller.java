package org.example.register.transfer;

import com.aliyuncs.transform.UnmarshallerContext;
import org.example.register.model.RegisterUserResponse;

public class RegisterUserResponseUnmarshaller {
    public static RegisterUserResponse unmarshall(RegisterUserResponse registerUserResponse, UnmarshallerContext _ctx) {
        registerUserResponse.setBoxUUID(_ctx.stringValue("RegisterUserResponse.boxUUID"));
        registerUserResponse.setUserId(_ctx.stringValue("RegisterUserResponse.userId"));
        registerUserResponse.setUserDomain(_ctx.stringValue("RegisterUserResponse.userDomain"));
        registerUserResponse.setUserType(_ctx.stringValue("RegisterUserResponse.userType"));
        registerUserResponse.setClientUUID(_ctx.stringValue("RegisterUserResponse.clientUUID"));

        return registerUserResponse;
    }
}