package de.klickreform.dropkit.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Factory to create instances of MailgunEmailService from the Dropwizard Configuration.
 *
 * @author Benjamin Bestmann
 */
public class MailgunFactory {

    private String key;
    private String domain;


    @NotEmpty
    @JsonProperty
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NotEmpty
    @JsonProperty
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public MailgunEmailService buildClient() {
        MailgunEmailService emailService = new MailgunEmailService(key,domain);
        return emailService;
    }

}
