package de.klickreform.dropkit.models;

/**
 * Interface to represent any authenticated entity. Implementations need to provide a unique String
 * which can be used to identify and authenticate the Principal.
 *
 * @author Benjamin Bestmann
 */
public interface Principal {

    public String getIdentifier();

}
