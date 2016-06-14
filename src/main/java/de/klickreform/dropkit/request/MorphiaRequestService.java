package de.klickreform.dropkit.request;

import java.util.UUID;

/**
 * RequestService implementation based on Morphia.
 *
 * @author Benjamin Bestmann
 */
public class MorphiaRequestService implements RequestService {

    private MorphiaRequestDao requestDao;

    public MorphiaRequestService(MorphiaRequestDao requestDao) {
        this.requestDao = requestDao;
    }

    public String createRequest(String subject, String payload) {
        String key = UUID.randomUUID().toString().replaceAll("-","");
        MorphiaRequest request = new MorphiaRequest(key,subject,payload);
        requestDao.create(request);
        return key;
    }

    public Request findRequest(String key) {
        return requestDao.findByKey(key);
    }

    public void invalidateRequest(String key) {
        requestDao.invalidateRequest(key);
    }
}
