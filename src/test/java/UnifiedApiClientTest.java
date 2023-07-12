import org.junit.Test;
import org.junit.Assert;

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
            // Here you could assert more response data if necessary.
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error occurred in registering device: " + e.getMessage());
        } finally {
            client.shutdown();
        }
    }
}
