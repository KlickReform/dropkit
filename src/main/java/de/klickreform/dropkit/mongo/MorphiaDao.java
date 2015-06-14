package de.klickreform.dropkit.mongo;

import com.mongodb.MongoException;
import de.klickreform.dropkit.dao.Dao;
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
 */
public abstract class MorphiaDao<E extends MorphiaDomainModel, K extends String> implements Dao<E,K> {

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
    public E findById(K id) throws NotFoundException {
        try {
            ObjectId objectId = new ObjectId(id);
            if((E)datastore.get(entityClass, objectId) == null) {
                throw new NotFoundException("No entity found with id " + id);
            }
            return (E)datastore.get(entityClass, objectId);
        } catch(IllegalArgumentException e) {
            throw new NotFoundException("No entity found with id " + id);
        }
    }

    @Override
    public String create(E entity) throws DuplicateEntryException {
        try {
            if(datastore.get(entity) != null) {
                // If there is already an entity with this Key
                throw new DuplicateEntryException("Can't create entity. Entity already exists.");
            }
            Key<E> key = datastore.save(entity);
            return key.getId().toString();
        } catch(MongoException.DuplicateKey e) {
            // If any unique field with this value already exists, throw DuplicateEntryException
            throw new DuplicateEntryException("Duplicate Field Value: " + e.getMessage());
        }
    }

    @Override
    public String createOrUpdate(E entity) {
        try {
            Key<E> key = datastore.save(entity);
            return key.getId().toString();
        } catch(MongoException.DuplicateKey e) {
            // If any unique field with this value already exists, throw DuplicateEntryException
            throw new DuplicateEntryException("Duplicate Field Value: " + e.getMessage());
        }
    }

    @Override
    public String update(E entity) throws NotFoundException, DuplicateEntryException {
        try {
            if(datastore.get(entity) == null) {
                // If there is no entity with this Key
                throw new NotFoundException("Could not update. Entity already exists.");
            }
            Key<E> key = datastore.save(entity);
            return key.getId().toString();
        } catch(MongoException.DuplicateKey e) {
            // If any unique field with this value already exists, throw DuplicateEntryException
            throw new DuplicateEntryException("Duplicate Field Value: " + e.getMessage());
        }
    }

    @Override
    public void delete(E entity) {
        datastore.delete(entity);
    }

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
