package de.klickreform.dropkit.request;

/**
 * Interface for RequestService
 *
 * @author Benjamin Bestmann
 */
public interface RequestService {

    public String createRequest(String subject, String payload);

    public Request findRequest(String key);

    public void invalidateRequest(String key);

}
