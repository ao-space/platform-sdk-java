package org.example.register.transfer;

import com.aliyuncs.transform.UnmarshallerContext;
import org.example.register.model.ObtainBoxRegKeyResponse;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObtainBoxRegKeyResponseUnmarshaller {

    public static ObtainBoxRegKeyResponse unmarshall(ObtainBoxRegKeyResponse obtainBoxRegKeyResponse, UnmarshallerContext _ctx) {
        obtainBoxRegKeyResponse.setBoxUUID(_ctx.stringValue("ObtainBoxRegKeyResponse.boxUUID"));

        List<ObtainBoxRegKeyResponse.TokenResult> tokenResults = new ArrayList<>();
        int i = 0;
        while (_ctx.stringValue("ObtainBoxRegKeyResponse.TokenResult." + i + ".serviceId") != null) {
            ObtainBoxRegKeyResponse.TokenResult tokenResult = new ObtainBoxRegKeyResponse.TokenResult();
            tokenResult.setServiceId(_ctx.stringValue("ObtainBoxRegKeyResponse.TokenResult." + i + ".serviceId"));
            tokenResult.setBoxRegKey(_ctx.stringValue("ObtainBoxRegKeyResponse.TokenResult." + i + ".boxRegKey"));
            tokenResult.setExpiresAt(OffsetDateTime.parse(_ctx.stringValue("ObtainBoxRegKeyResponse.TokenResult." + i + ".expiresAt")));
            tokenResults.add(tokenResult);
            i++;
        }
        obtainBoxRegKeyResponse.setTokenResults(tokenResults);
        return obtainBoxRegKeyResponse;
    }
}
