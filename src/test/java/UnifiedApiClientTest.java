import org.example.Authentication.model.ObtainBoxRegKeyResponse;
import org.example.domain.model.GenerateUserDomainNameResponse;
import org.example.register.model.RegisterClientResponse;
import org.example.register.model.RegisterDeviceResponse;
import org.example.register.model.RegisterUserResponse;
import org.junit.Test;
import org.junit.Assert;
import org.example.Client.UnifiedApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class UnifiedApiClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnifiedApiClientTest.class);
    private static final String boxUUID = "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed";
    private static final String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";

    @Test
    public void testRegisterDevice() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");
        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);

        LOGGER.info("Registered device with Box UUID: {}", response.getBoxUUID());
        LOGGER.info("Registered device with clientId: {}", response.getNetworkClient().getClientId());
        LOGGER.info("Registered device with secretKey: {}", response.getNetworkClient().getSecretKey());
    }

    @Test
    public void testRegisterClient() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";
        String clientType = "client_auth";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        RegisterClientResponse response = client.registerClient(boxUUID, userId, clientUUID, clientType, reqId, boxRegKey);

        LOGGER.info("Registered client with Client UUID: {}", response.getClientUUID());
        LOGGER.info("Registered client with Client Type: {}", response.getClientType());
    }

    @Test
    public void testRegisterUser() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");

        String userId = "1";
        String subdomain = "";
        String userType = "user_admin";
        String clientUUID = "5d5af871790b4922bca935f08109a531";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        RegisterUserResponse response = client.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);

        LOGGER.info("Registered user with Box UUID: {}", response.getBoxUUID());
        LOGGER.info("Registered user with User ID: {}", response.getUserId());
        LOGGER.info("Registered user with User Domain: {}", response.getUserDomain());
        LOGGER.info("Registered user with User Type: {}", response.getUserType());
        LOGGER.info("Registered user with Client UUID: {}", response.getClientUUID());
    }

    @Test
    public void testGenerateUserDomainName() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");
        String effectiveTime = "7";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        GenerateUserDomainNameResponse response = client.generateUserDomainName(boxUUID, effectiveTime, reqId, boxRegKey);

        LOGGER.info("Generated user domain for Box UUID: {}", response.getBoxUUID());
        LOGGER.info("Generated user domain Subdomain: {}", response.getSubdomain());
        LOGGER.info("Generated user domain ExpiresAt: {}", response.getExpiresAt());
    }
    @Test
    public void testDeleteDevice() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteDevice(boxUUID, reqId, boxRegKey);

        LOGGER.info("Deleted device with Box UUID: {}", boxUUID);
    }

    @Test
    public void testDeleteUser() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteUser(boxUUID, userId, reqId, boxRegKey);

        LOGGER.info("Deleted user with User ID: {}", userId);
    }

    @Test
    public void testDeleteClient() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space");
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, "sign", reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteClient(boxUUID, userId, clientUUID, reqId, boxRegKey);

        LOGGER.info("Deleted client with Client UUID: {}", clientUUID);
    }
}
