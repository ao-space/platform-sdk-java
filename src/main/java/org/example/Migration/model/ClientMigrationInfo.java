package org.example.Migration.model;

public class ClientMigrationInfo {
    private String clientUUID;
    private String clientType;

    public ClientMigrationInfo(String clientUUID, String clientType) {
        this.clientUUID = clientUUID;
        this.clientType = clientType;
    }
// getters and setters

    public String getClientUUID() {
        return clientUUID;
    }

    public void setClientUUID(String clientUUID) {
        this.clientUUID = clientUUID;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    // similar to other classes, validate inputs in setters if needed.
}
