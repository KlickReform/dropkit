package de.klickreform.dropkit.request;

import de.klickreform.dropkit.mongo.MorphiaDomainModel;
import org.mongodb.morphia.annotations.Entity;


/**
 * Implementation of Requests based on Morphia.
 *
 * @author Benjamin Bestmann
 */
@Entity
public class MorphiaRequest extends MorphiaDomainModel implements Request {

    private String key;
    private String subject;
    private String payload;
    private boolean valid;


    public MorphiaRequest() { }

    public MorphiaRequest(String key, String subject, String payload) {
        this.key = key;
        this.subject = subject;
        this.payload = payload;
        this.valid = true;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
