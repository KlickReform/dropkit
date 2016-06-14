package de.klickreform.dropkit.request;

/**
 * Interface for Request objetcs.
 *
 * @author Benjamin Bestmann
 */
public interface Request {

    public String getKey();
    public String getSubject();
    public String getPayload();
    public boolean isValid();

}
