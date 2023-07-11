package org.example.domain.model;

public class ModifyUserDomainNameRequest {

    private String subdomain;

    public String getSubdomain() {
        return this.subdomain;
    }

    public void setSubdomain(String subdomain) {
        if (subdomain == null || subdomain.trim().isEmpty()) {
            throw new IllegalArgumentException("Subdomain cannot be null or empty.");
        }
        this.subdomain = subdomain;
    }
}
