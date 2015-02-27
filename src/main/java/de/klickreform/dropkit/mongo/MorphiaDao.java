package de.klickreform.dropkit.mongo;

import com.mongodb.MongoException;
import de.klickreform.dropkit.dao.AbstractDao;
import de.klickreform.dropkit.exception.DuplicateEntryException;
import de.klickreform.dropkit.exception.NotFoundException;
import io.dropwizard.util.Generics;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Map;

/**
 * Abstract Data Access Object (DAO) to work with Morphia Datastore.
 *
 * @author Benjamin Bestmann
 * @since 03.11.2014
 */
public class MorphiaDao<E> implements AbstractDao<E> {

    protected Datastore datastore;
    private final Class<?> entityClass;

    public MorphiaDao(Datastore datastore) {
        this.datastore = datastore;
        this.entityClass = Generics.getTypeParameter(getClass());
    }

    @Override
    public List<E> findAll() {
        return (List<E>)datastore.find(entityClass).asList();
    }

    @Override
    public E findById(String id) throws NotFoundException {
        try {
            ObjectId objectId = new ObjectId(id);
            return (E)datastore.get(entityClass, objectId);
        } catch(IllegalArgumentException e) {
            throw new NotFoundException("No entity found with id " + id);
        }
    }

    @Override
    public String create(E entity) throws DuplicateEntryException {
        try {
            Key<E> key = datastore.save(entity);
            return key.getId().toString();
        } catch(MongoException.DuplicateKey e) {
            throw new DuplicateEntryException("An entry already exists");
        }

    }

    @Override
    public void delete(E entity) {
        datastore.delete(entity);
    }

    /**
     * Utility method that will create a set of update statements from a Map<String,Object>
     *
     * @param ops The morphia UpdateOperations Object that the statements will be added to
     * @param updateData The Map<String,Object> that contains the fields to be updated
     * @return The UpdateOperations Object containing all update statements created from the Map
     */
    public UpdateOperations<E> prepareUpdateStatement(UpdateOperations<E> ops, Map<String,Object> updateData) {
        // Iterate over the map of values that should be added to the UpdateOperations
        for(Map.Entry<String,Object> entry : updateData.entrySet()) {
            if((entry.getKey() != null) && entry.getValue() != null) {
                // If key and value are not null, create and add update statement to UpdateOperations
                ops.set(entry.getKey(),entry.getValue());
            }
        }
        // Finally return the UpdateOperations containing all update statements
        return ops;
    }

}
