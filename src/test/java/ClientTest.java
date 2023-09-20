import io.github.ren2003u.migration.model.*;
import lombok.extern.slf4j.Slf4j;
import io.github.ren2003u.authentication.model.ObtainBoxRegKeyResponse;
import io.github.ren2003u.domain.errorHandle.ApiResponse;
import io.github.ren2003u.domain.model.GenerateUserDomainNameResponse;
import io.github.ren2003u.domain.model.ModifyUserDomainNameResponse;
import io.github.ren2003u.register.model.RegisterClientResponse;
import io.github.ren2003u.register.model.RegisterDeviceResponse;
import io.github.ren2003u.register.model.RegisterUserResponse;
import org.junit.Test;
import org.junit.Assert;
import io.github.ren2003u.client.Client;

import java.util.Arrays;
import java.util.List;


@Slf4j
public class ClientTest {
    private static final String boxUUID = "364b553c01dabb2764b2f2c0e721c1e860e308b1c7daed2671507d21434060ed";
    private static final String reqId = "e9993fc787d94b6c886cbaa340f9c0f4";

    @Test
    public void testRegisterDevice() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ApiResponse<RegisterDeviceResponse> response = client.registerDevice(boxUUID, reqId, boxRegKey);

        if (response.getData() != null) {
            // Handle the successful response
            log.info("Registered device with Box UUID: {}", response.getData().getBoxUUID());
            log.info("Registered device with clientId: {}", response.getData().getNetworkClient().getClientId());
            log.info("Registered device with secretKey: {}", response.getData().getNetworkClient().getSecretKey());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }
        log.info("BoxRegKey: {}", boxRegKey);
    }
    @Test
    public void testRegisterUser() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");

        String userId = "1";
        String subdomain = "";
        String userType = "user_admin";
        String clientUUID = "5d5af871790b4922bca935f08109a531";
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.updateAvailableApis();
        ApiResponse<RegisterUserResponse> response = client.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);
        if (response.getData() != null) {
            // Handle the successful response
            log.info("Registered user with Box UUID: {}", response.getData().getBoxUUID());
            log.info("Registered user with User ID: {}", response.getData().getUserId());
            log.info("Registered user with User Domain: {}", response.getData().getUserDomain());
            log.info("Registered user with User Type: {}", response.getData().getUserType());
            log.info("Registered user with Client UUID: {}", response.getData().getClientUUID());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }
    }
    @Test
    public void testRegisterClient() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";
        String clientType = "client_auth";
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ApiResponse<RegisterClientResponse> response = client.registerClient(boxUUID, userId, clientUUID, clientType, reqId, boxRegKey);

        if (response.getData() != null) {
            // Handle the successful response
            log.info("Registered client with Client UUID: {}", response.getData().getClientUUID());
            log.info("Registered client with Client Type: {}", response.getData().getClientType());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }
    }


    @Test
    public void testGenerateUserDomainName() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String effectiveTime = "7";
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ApiResponse<GenerateUserDomainNameResponse> response = client.generateUserDomainName(boxUUID, effectiveTime, reqId, boxRegKey);
        if (response.getData() != null) {
            // Handle the successful response
            log.info("Generated user domain for Box UUID: {}", response.getData().getBoxUUID());
            log.info("Generated user domain Subdomain: {}", response.getData().getSubdomain());
            log.info("Generated user domain ExpiresAt: {}", response.getData().getExpiresAt());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }
    }
    @Test
    public void testModifyUserDomainName() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String subdomain = "eqx441zw";
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ApiResponse<ModifyUserDomainNameResponse> response = client.modifyUserDomainName(boxUUID, userId, subdomain, reqId, boxRegKey);
        if (response.getData() != null) {
            // Handle the successful response
            log.info("Modified user domain name with User ID: {}", response.getData().getUserId());
            log.info("Modified user domain name with new Subdomain: {}", response.getData().getSubdomain());
            log.info("Modified user domain name with Recommends: {}", response.getData().getRecommends());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }

    }
    @Test
    public void testDeleteDevice() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteDevice(boxUUID, reqId, boxRegKey);

        log.info("Deleted device with Box UUID: {}", boxUUID);
    }

    @Test
    public void testDeleteUser() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteUser(boxUUID, userId, reqId, boxRegKey);

        log.info("Deleted user with User ID: {}", userId);
    }

    @Test
    public void testDeleteClient() throws Exception {
        Client client = new Client("https://ao.space",null);
        List<String> serviceIds = Arrays.asList("10001");
        String userId = "1";
        String clientUUID = "5d5af871790b4922bca935f08109a531";
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        client.deleteClient(boxUUID, userId, clientUUID, reqId, boxRegKey);

        log.info("Deleted client with Client UUID: {}", clientUUID);
    }
    @Test
    public void testMigrateSpacePlatform() throws Exception {
        Client client = new Client("https://ao.space", null);
        String networkClientId = "5d5af871790b4922bca935f08109a531";
        List<String> serviceIds = Arrays.asList("10001");
        List<UserMigrationInfo> userInfos = Arrays.asList(
                new UserMigrationInfo("1", "eqx441zw", "user_admin", Arrays.asList(new ClientMigrationInfo("5d5af871790b4922bca935f08109a531", "client_auth")))
                // Add more users if needed
        );
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ApiResponse<SpacePlatformMigrationResponse> response = client.migrateSpacePlatform(boxUUID, networkClientId, userInfos, reqId, boxRegKey);
        if (response.getData() != null) {
            // Handle the successful response
            log.info("Migrated space platform with Box UUID: {}", response.getData().getBoxUUID());
            log.info("Migrated space platform with Network Client ID: {}", response.getData().getNetworkClient().getClientId());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }
        // Add more assertions and logging as needed
    }
    @Test
    public void testMigrateSpacePlatformOut() throws Exception {
        Client client = new Client("https://ao.space", null);
        List<String> serviceIds = Arrays.asList("10001");
        List<UserDomainRouteInfo> userDomainRouteInfos = Arrays.asList(
                new UserDomainRouteInfo("1", "imkpm39v.ao.space")
        );
        String boxRegKey = "";

        ApiResponse<ObtainBoxRegKeyResponse> obtainBoxRegKeyResponse = client.obtainBoxRegKey(boxUUID, serviceIds, reqId);
        if (obtainBoxRegKeyResponse.getData() != null) {
            boxRegKey = obtainBoxRegKeyResponse.getData().getTokenResults().get(0).getBoxRegKey();
        }

        Assert.assertNotNull("boxRegKey should not be null", boxRegKey);

        ApiResponse<SpacePlatformMigrationOutResponse> response = client.migrateSpacePlatformOut(boxUUID, userDomainRouteInfos, reqId, boxRegKey);
        if (response.getData() != null) {
            // Handle the successful response
            log.info("Migrated space platform out with Box UUID: {}", response.getData().getBoxUUID());
            log.info("Migrated space platform out with User Domain Route Infos: {}", response.getData().getUserDomainRouteInfos());
        }else if (response.getError() != null) {
            // Handle or assert the error
            log.info("Error code: {}",response.getError().getCode());
            log.info("Error message: {}",response.getError().getMessage());
        }
        // Add more assertions and logging as needed
    }
}
