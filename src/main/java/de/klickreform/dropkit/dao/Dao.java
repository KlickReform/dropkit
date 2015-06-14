package de.klickreform.dropkit.dao;

import de.klickreform.dropkit.exception.DuplicateEntryException;
import de.klickreform.dropkit.exception.NotFoundException;
import de.klickreform.dropkit.models.DomainModel;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface for Data Access Object (DAO) implementations that provide basic CRUD operations for a
 * Data Access Layer.
 *
 * @author Benjamin Bestmann
 */
public interface Dao<E extends DomainModel,K extends Serializable> {

    public Collection<E> findAll();

    public E findById(K id) throws NotFoundException;

    public String create(E entity) throws DuplicateEntryException;

    public String createOrUpdate(E entity);

    public String update(E entity) throws NotFoundException, DuplicateEntryException;

    public void delete(E entity) throws NotFoundException;

}
