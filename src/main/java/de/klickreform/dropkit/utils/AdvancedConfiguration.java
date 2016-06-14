package de.klickreform.dropkit.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Advanced Configuartion Options for Dropwizard APIs.
 *
 * @author Benjamin Bestmann
 */
public class AdvancedConfiguration {

    private boolean signupRestriction;
    private String signupIp;
    private String secret;

    @JsonProperty
    public boolean isSignupRestriction() {
        return signupRestriction;
    }

    public void setSignupRestriction(boolean signupRestriction) {
        this.signupRestriction = signupRestriction;
    }

    @JsonProperty
    public String getSignupIp() {
        return signupIp;
    }

    public void setSignupIp(String signupIp) {
        this.signupIp = signupIp;
    }

    @JsonProperty
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
