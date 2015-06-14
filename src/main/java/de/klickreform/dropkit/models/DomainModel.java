package de.klickreform.dropkit.models;

import java.util.Date;

/**
 * Entity that represents a concept from the application domain. Every DomainModel is
 * represented by a unique string identifier.
 *
 * @author Benjamin Bestmann
 */
public interface DomainModel {

    public String getId();

    public void setId(String id);

    public Date getCreated();

    public Date getUpdated();

}
