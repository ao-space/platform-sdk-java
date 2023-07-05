package org.example.register.transfer;

import com.aliyuncs.transform.UnmarshallerContext;
import org.example.register.model.RegisterDeviceResponse;

public class RegisterDeviceResponseUnmarshaller {

    public static RegisterDeviceResponse unmarshall(RegisterDeviceResponse registerDeviceResponse, UnmarshallerContext _ctx) {
        registerDeviceResponse.setBoxUUID(_ctx.stringValue("RegisterDeviceResponse.boxUUID"));
        RegisterDeviceResponse.NetworkClient networkClient = new RegisterDeviceResponse.NetworkClient();
        networkClient.setClientId(_ctx.stringValue("RegisterDeviceResponse.NetworkClient.clientId"));
        networkClient.setSecretKey(_ctx.stringValue("RegisterDeviceResponse.NetworkClient.secretKey"));
        registerDeviceResponse.setNetworkClient(networkClient);
        return registerDeviceResponse;
    }
}
