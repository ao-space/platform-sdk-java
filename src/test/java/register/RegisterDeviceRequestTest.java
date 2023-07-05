package register;

import org.example.register.model.RegisterDeviceRequest;
import org.example.register.model.RegisterDeviceResponse;
import org.example.tutorial.Register.RegisterDeviceServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RegisterDeviceRequestTest {

    @Mock
    private RegisterDeviceServiceClient mockClient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterDeviceRequest() throws Exception {
        String boxUuid = "123456789";

        RegisterDeviceRequest request = new RegisterDeviceRequest();
        request.setBoxUUID(boxUuid);

        RegisterDeviceResponse.NetworkClient networkClient = new RegisterDeviceResponse.NetworkClient();
        networkClient.setClientId("clientId");
        networkClient.setSecretKey("secretKey");

        RegisterDeviceResponse expectedResponse = new RegisterDeviceResponse();
        expectedResponse.setBoxUUID(boxUuid);
        expectedResponse.setNetworkClient(networkClient);

        when(mockClient.registerDevice(eq(boxUuid), any(), any()))
                .thenReturn(expectedResponse);

        RegisterDeviceResponse actualResponse = mockClient.registerDevice(boxUuid, "request_id", "box_reg_key");

        assertEquals(expectedResponse.getBoxUUID(), actualResponse.getBoxUUID());
        assertEquals(expectedResponse.getNetworkClient().getClientId(), actualResponse.getNetworkClient().getClientId());
        assertEquals(expectedResponse.getNetworkClient().getSecretKey(), actualResponse.getNetworkClient().getSecretKey());
    }
}
