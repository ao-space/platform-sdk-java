package io.github.ren2003u.register.model;

public class RegisterDeviceResponse {

    private String boxUUID;
    private NetworkClient networkClient;

    public String getBoxUUID() {
        return this.boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        this.boxUUID = boxUUID;
    }

    public NetworkClient getNetworkClient() {
        return this.networkClient;
    }

    public void setNetworkClient(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }



    public static class NetworkClient {
        private String clientId;
        private String secretKey;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
