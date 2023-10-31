package io.github.ren2003u.domain.model;

public class GenerateUserDomainNameRequest {

    // Field for effective time
    private String effectiveTime;

    public String getEffectiveTime() {
        return this.effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        if (effectiveTime == null || effectiveTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Effective Time cannot be null or empty.");
        }
        this.effectiveTime = effectiveTime;
    }
}
