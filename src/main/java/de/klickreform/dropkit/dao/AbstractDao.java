package de.klickreform.dropkit.dao;

import de.klickreform.dropkit.exception.DuplicateEntryException;
import de.klickreform.dropkit.exception.NotFoundException;

import java.util.Collection;

/**
 * Abstract Data Access Object to provide basic CRUD operations for a
 * Data Access Layer.
 *
 * @author Benjamin Bestmann
 */
public interface AbstractDao<E> {

    public Collection<E> findAll();

    public E findById(String id) throws NotFoundException;

    public String create(E entity) throws DuplicateEntryException;

    public void delete(E entity) throws NotFoundException;

}
