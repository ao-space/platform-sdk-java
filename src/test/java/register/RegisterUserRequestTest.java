package register;

import org.example.Client.Client;
import org.example.register.model.RegisterUserRequest;
import org.example.register.model.RegisterUserResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RegisterUserRequestTest {

    @Mock
    private Client mockClient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUserRequest() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setBoxUUID("123456789");
        request.setUserId("user1");
        request.setUserType("user_admin");
        request.setClientUUID("c1");

        RegisterUserResponse expectedResponse = new RegisterUserResponse();
        expectedResponse.setBoxUUID("123456789");
        expectedResponse.setUserId("user1");
        expectedResponse.setUserType("user_admin");
        expectedResponse.setClientUUID("c1");

        when(mockClient.sendRequest(anyString(), any(HttpMethod.class), any(RequestCallback.class),
                any(ResponseExtractor.class))).thenReturn(expectedResponse);

        RegisterUserResponse actualResponse = mockClient.sendRequest("/v2/platform/boxes/{box_uuid}/users", HttpMethod.POST, null, null);

        assertEquals(expectedResponse.getBoxUUID(), actualResponse.getBoxUUID());
        assertEquals(expectedResponse.getUserId(), actualResponse.getUserId());
        assertEquals(expectedResponse.getUserType(), actualResponse.getUserType());
        assertEquals(expectedResponse.getClientUUID(), actualResponse.getClientUUID());
    }
}
