package de.klickreform.dropkit.request;

import de.klickreform.dropkit.exception.NotFoundException;
import de.klickreform.dropkit.mongo.MorphiaDao;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * RequestDao for Morphia implementations.
 *
 * @author Benjamin Bestmann
 */
public class MorphiaRequestDao extends MorphiaDao<MorphiaRequest,String> {

    public MorphiaRequestDao(Datastore datastore) {
        super(datastore);
    }

    public MorphiaRequest findByKey(String key) throws NotFoundException {
        Query<MorphiaRequest> query = datastore.createQuery(MorphiaRequest.class);
        query.criteria("key").equal(key);
        MorphiaRequest result = query.get();
        if(result == null) {
            throw new NotFoundException("Request not found: " + key);
        } else {
            return result;
        }
    }

    public void invalidateRequest(String key) throws NotFoundException {
        Query<MorphiaRequest> query = datastore.createQuery(MorphiaRequest.class);
        query.criteria("key").equal(key);
        UpdateOperations<MorphiaRequest> ops = this.datastore.createUpdateOperations(MorphiaRequest.class);
        // Create update Data and put it into a Map
        Map<String,Object> updateData = new HashMap<String,Object>();
        updateData.put("valid", false);
        updateData.put("updated", new Date());
        // Create update statements from the Map and put them into the UpdateOperations Object
        this.prepareUpdateStatement(ops,updateData);
        // Perform the update using the MongoDB Datastore
        datastore.update(query,ops);
    }

}
