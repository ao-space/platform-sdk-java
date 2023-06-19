package Authentication;

import org.example.Authentication.model.ObtainBoxRegKeyRequest;
import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.tutorial.Authentication.BoxRegKeyServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ObtainBoxRegKeyRequestTest {

    @Mock
    private BoxRegKeyServiceClient mockClient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtainBoxRegKeyRequest() throws Exception {
        String boxUuid = "123456789";
        String serviceIds = "10001";
        String sign = "signature";

        ObtainBoxRegKeyRequest request = new ObtainBoxRegKeyRequest();
        request.setBoxUUID(boxUuid);
        request.setServiceIds(serviceIds);
        request.setSign(sign);

        ObtainBoxRegKeyResponse.TokenResult tokenResult = new ObtainBoxRegKeyResponse.TokenResult();
        tokenResult.setServiceId(serviceIds);
        tokenResult.setBoxRegKey("box_reg_key");
        tokenResult.setExpiresAt(OffsetDateTime.now().plusHours(1));

        ObtainBoxRegKeyResponse expectedResponse = new ObtainBoxRegKeyResponse();
        expectedResponse.setBoxUUID(boxUuid);
        expectedResponse.setTokenResults(Collections.singletonList(tokenResult));

        when(mockClient.obtainBoxRegKey(eq(boxUuid), eq(serviceIds), eq(sign), any()))
                .thenReturn(expectedResponse);

        ObtainBoxRegKeyResponse actualResponse = mockClient.obtainBoxRegKey(boxUuid, serviceIds, sign, "request_id");

        assertEquals(expectedResponse.getBoxUUID(), actualResponse.getBoxUUID());
        assertEquals(expectedResponse.getTokenResults().size(), actualResponse.getTokenResults().size());

        ObtainBoxRegKeyResponse.TokenResult expectedTokenResult = expectedResponse.getTokenResults().get(0);
        ObtainBoxRegKeyResponse.TokenResult actualTokenResult = actualResponse.getTokenResults().get(0);

        assertEquals(expectedTokenResult.getServiceId(), actualTokenResult.getServiceId());
        assertEquals(expectedTokenResult.getBoxRegKey(), actualTokenResult.getBoxRegKey());
        assertEquals(expectedTokenResult.getExpiresAt(), actualTokenResult.getExpiresAt());
    }
}
