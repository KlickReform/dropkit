package de.klickreform.dropkit.template;

/**
 * Simple Template Model for Reset Password Mails.
 *
 * @author Benjamin Bestmann
 */
public class ResetPasswordMailTemplate {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
