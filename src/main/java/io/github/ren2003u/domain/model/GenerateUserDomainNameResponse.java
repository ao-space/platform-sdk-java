package io.github.ren2003u.domain.model;

import java.time.OffsetDateTime;

public class GenerateUserDomainNameResponse {

    private String boxUUID;
    private String subdomain;
    private OffsetDateTime expiresAt;

    public String getBoxUUID() {
        return this.boxUUID;
    }

    public void setBoxUUID(String boxUUID) {
        this.boxUUID = boxUUID;
    }

    public String getSubdomain() {
        return this.subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public OffsetDateTime getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}