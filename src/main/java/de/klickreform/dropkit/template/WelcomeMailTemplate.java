package de.klickreform.dropkit.template;

/**
 * Simple Template Model for Welcome Mails.
 *
 * @author Benjamin Bestmann
 */
public class WelcomeMailTemplate {

    private String name;
    private String email;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
