package de.klickreform.dropkit.mongo;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;

/**
 * Dropwizard HealthCheck to test if the MongoDB instance is reachable.
 *
 * @author Benjamin Bestmann
 */
public class MongoHealthCheck extends HealthCheck {

    private final MongoClient mongoClient;

    public MongoHealthCheck(MongoClient mongoClient) {
        super();
        this.mongoClient = mongoClient;
    }

    @Override
    protected Result check() throws Exception {
        try {
            // Try to reach the system database in order to check if mongo server is alive
            mongoClient.getDB("system").getStats();
            return Result.healthy();
        } catch(MongoClientException e) {
            return Result.unhealthy("There is a problem with the mongo instance: " + e.getMessage());
        }
    }

}
