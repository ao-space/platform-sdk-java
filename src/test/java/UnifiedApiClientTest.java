import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.register.model.RegisterClientResponse;
import org.example.register.model.RegisterDeviceResponse;
import org.junit.Test;
import org.junit.Assert;
import org.example.Client.UnifiedApiClient;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UnifiedApiClientTest {
    private static final String boxUUID = "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed";
    private static final String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";

    @Test
    public void testRegisterDevice() {
        // Create an instance of UnifiedApiClient
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space", "api-key", 10);
        List<String> serviceIds = Arrays.asList("10001");

        // Call obtainBoxRegKey to get boxRegKey
        Future<ObtainBoxRegKeyResponse> futureObtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = "";
        try {
            ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = futureObtainBoxRegKeyResponse.get();  // Blocks until the API response is available
            for (ObtainBoxRegKeyResponse.TokenResult tokenResult : obtainBoxRegKeyResponse.getTokenResults()) {
                boxRegKey = tokenResult.getBoxRegKey();  // Fetching the boxRegKey
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred in obtaining box reg key: " + e.getMessage());
        }

        // Ensure we have a boxRegKey before we proceed to register device
        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        // Register the device with the obtained boxRegKey
        Future<RegisterDeviceResponse> future = client.registerDevice(boxUUID, reqId, boxRegKey);
        try {
            RegisterDeviceResponse response = future.get();  // Blocks until the API response is available
            System.out.println("Registered device with Box UUID: " + response.getBoxUUID());
            System.out.println("Registered device with clientId: " + response.getNetworkClient().getClientId());
            System.out.println("Registered device with secretKey: " + response.getNetworkClient().getSecretKey());
            // Here you could assert more response data if necessary.
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred in registering device: " + e.getMessage());
        } finally {
            client.shutdown();
        }
    }
    @Test
    public void testRegisterClient() {
        // Create an instance of UnifiedApiClient
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space", "api-key", 10);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";
        String clientType = "client_auth";

        // Call obtainBoxRegKey to get boxRegKey
        Future<ObtainBoxRegKeyResponse> futureObtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = "";
        try {
            ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = futureObtainBoxRegKeyResponse.get();  // Blocks until the API response is available
            for (ObtainBoxRegKeyResponse.TokenResult tokenResult : obtainBoxRegKeyResponse.getTokenResults()) {
                boxRegKey = tokenResult.getBoxRegKey();  // Fetching the boxRegKey
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred in obtaining box reg key: " + e.getMessage());
        }

        // Ensure we have a boxRegKey before we proceed to register client
        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        // Register the client with the obtained boxRegKey
        Future<RegisterClientResponse> future = client.registerClient(boxUUID, userId, clientUUID, clientType, reqId, boxRegKey);
        try {
            RegisterClientResponse response = future.get();  // Blocks until the API response is available
            System.out.println("Registered client with Client UUID: " + response.getClientUUID());
            System.out.println("Registered client with Client Type: " + response.getClientType());
            // Here you could assert more response data if necessary.
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred in registering client: " + e.getMessage());
        } finally {
            client.shutdown();
        }
    }
}
