package delete;

import org.example.delete.model.DeleteUserRequest;
import org.example.delete.model.DeleteUserResponse;
import org.example.tutorial.delete.DeleteUserServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteUserRequestTest {

    @Mock
    private DeleteUserServiceClient mockClient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteUserRequest() throws Exception {
        String boxUuid = "123456789";
        String userId = "987654321";

        // Verify that the deleteUser method is called with correct parameters
        mockClient.deleteUser(boxUuid, userId, "request_id", "box_reg_key");

        verify(mockClient, times(1)).deleteUser(boxUuid, userId, "request_id", "box_reg_key");
    }
}
