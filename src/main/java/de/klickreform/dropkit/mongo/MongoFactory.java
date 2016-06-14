package de.klickreform.dropkit.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoClient;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import java.net.UnknownHostException;

/**
 * Factory to create instances of MongoClient from the Dropwizard Configuration.
 *
 * @author Benjamin Bestmann
 */
public class MongoFactory {

    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
    private MongoClient mongoClient;

    @NotEmpty
    @JsonProperty
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @NotEmpty
    @JsonProperty
    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @JsonProperty
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MongoClient buildClient(Environment environment) throws UnknownHostException {
        if(this.mongoClient != null) {
            // If there is an existing MongoClient, return it
            return mongoClient;
        }
        // Create a new MongoClient
        final MongoClient client = new MongoClient(getHost(), getPort());
        // Bind MongoClient to application lifecycle using a Managed Object
        environment.lifecycle().manage(new Managed() {
            public void start() throws Exception {
                // nothing here
            }
            public void stop() throws Exception {
                // Close Mongo Session when application stops
                client.close();
            }
        });
        this.mongoClient = client;
        return client;
    }

}
