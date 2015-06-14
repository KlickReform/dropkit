package de.klickreform.dropkit.utils;

/**
 * Bogus classes to represent different views to be used with Jackson's @JsonView.
 * More information: http://wiki.fasterxml.com/JacksonJsonViews
 *
 * @author Benjamin Bestmann
 */
public class Views {

    public static class Public {
        // View for Public Information
    }

    public static class Private extends Public {
        // View for Private Information
    }

}
