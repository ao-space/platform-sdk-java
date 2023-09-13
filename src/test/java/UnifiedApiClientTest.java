import lombok.extern.slf4j.Slf4j;
import org.example.authentication.model.ObtainBoxRegKeyResponse;
import org.example.migration.model.*;
import org.example.domain.model.GenerateUserDomainNameResponse;
import org.example.domain.model.ModifyUserDomainNameResponse;
import org.example.register.model.RegisterClientResponse;
import org.example.register.model.RegisterDeviceResponse;
import org.example.register.model.RegisterUserResponse;
import org.junit.Test;
import org.junit.Assert;
import org.example.client.UnifiedApiClient;

import java.util.Arrays;
import java.util.List;


@Slf4j
public class UnifiedApiClientTest {
    private static final String boxUUID = "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed";
    private static final String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";

    @Test
    public void testRegisterDevice() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);

        log.info("Registered device with Box UUID: {}", response.getBoxUUID());
        log.info("Registered device with clientId: {}", response.getNetworkClient().getClientId());
        log.info("Registered device with secretKey: {}", response.getNetworkClient().getSecretKey());
        log.info("BoxRegKey: {}", boxRegKey);
    }
    @Test
    public void testRegisterUser() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");

        String userId = "1";
        String subdomain = "";
        String userType = "user_admin";
        String clientUUID = "5d5af871790b4922bca935f08109a531";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.updateAvailableApis();
        RegisterUserResponse response = client.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);

        log.info("Registered user with Box UUID: {}", response.getBoxUUID());
        log.info("Registered user with User ID: {}", response.getUserId());
        log.info("Registered user with User Domain: {}", response.getUserDomain());
        log.info("Registered user with User Type: {}", response.getUserType());
        log.info("Registered user with Client UUID: {}", response.getClientUUID());
    }
    @Test
    public void testRegisterClient() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";
        String clientType = "client_auth";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        RegisterClientResponse response = client.registerClient(boxUUID, userId, clientUUID, clientType, reqId, boxRegKey);

        log.info("Registered client with Client UUID: {}", response.getClientUUID());
        log.info("Registered client with Client Type: {}", response.getClientType());
    }


    @Test
    public void testGenerateUserDomainName() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String effectiveTime = "7";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        GenerateUserDomainNameResponse response = client.generateUserDomainName(boxUUID, effectiveTime, reqId, boxRegKey);

        log.info("Generated user domain for Box UUID: {}", response.getBoxUUID());
        log.info("Generated user domain Subdomain: {}", response.getSubdomain());
        log.info("Generated user domain ExpiresAt: {}", response.getExpiresAt());
    }
    @Test
    public void testDeleteDevice() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteDevice(boxUUID, reqId, boxRegKey);

        log.info("Deleted device with Box UUID: {}", boxUUID);
    }

    @Test
    public void testDeleteUser() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteUser(boxUUID, userId, reqId, boxRegKey);

        log.info("Deleted user with User ID: {}", userId);
    }

    @Test
    public void testDeleteClient() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteClient(boxUUID, userId, clientUUID, reqId, boxRegKey);

        log.info("Deleted client with Client UUID: {}", clientUUID);
    }
    @Test
    public void testModifyUserDomainName() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String subdomain = "eqx441zw";

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ModifyUserDomainNameResponse response = client.modifyUserDomainName(boxUUID, userId, subdomain, reqId, boxRegKey);

        log.info("Modified user domain name with Error: {}", response.getError());
        log.info("Modified user domain name with User ID: {}", response.getUserId());
        log.info("Modified user domain name with new Subdomain: {}", response.getSubdomain());
        log.info("Modified user domain name with Recommends: {}", response.getRecommends());
    }
    @Test
    public void testMigrateSpacePlatform() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space", null);
        String networkClientId = "5d5af871790b4922bca935f08109a531";
        List<String> serviceIds = Arrays.asList("10001");
        List<UserMigrationInfo> userInfos = Arrays.asList(
                new UserMigrationInfo("1", "eqx441zw", "user_admin", Arrays.asList(new ClientMigrationInfo("5d5af871790b4922bca935f08109a531", "client_auth")))
                // Add more users if needed
        );

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        SpacePlatformMigrationResponse response = client.migrateSpacePlatform(boxUUID, networkClientId, userInfos, reqId, boxRegKey);

        log.info("Migrated space platform with Box UUID: {}", response.getBoxUUID());
        log.info("Migrated space platform with Network Client ID: {}", response.getNetworkClient().getClientId());
        // Add more assertions and logging as needed
    }
    @Test
    public void testMigrateSpacePlatformOut() throws Exception {
        UnifiedApiClient client = new UnifiedApiClient("https://ao.space", null);
        List<String> serviceIds = Arrays.asList("10001");
        List<UserDomainRouteInfo> userDomainRouteInfos = Arrays.asList(
                new UserDomainRouteInfo("1", "imkpm39v.ao.space")
        );

        ObtainBoxRegKeyResponse obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        String boxRegKey = obtainBoxRegKeyResponse.getTokenResults().get(0).getBoxRegKey();

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        SpacePlatformMigrationOutResponse response = client.migrateSpacePlatformOut(boxUUID, userDomainRouteInfos, reqId, boxRegKey);

        log.info("Migrated space platform out with Box UUID: {}", response.getBoxUUID());
        log.info("Migrated space platform out with User Domain Route Infos: {}", response.getUserDomainRouteInfos());
        // Add more assertions and logging as needed
    }
}
