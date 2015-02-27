package de.klickreform.dropkit.storage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for the integration of storage services, that can be used to store an retrieve files,
 * i.e. from cloud storage providers.
 *
 * @author Benjamin Bestmann
 */
public interface StorageService {

    public String store(InputStream inputStream, String path, String fileName) throws IOException;

}
