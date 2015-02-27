package de.klickreform.dropkit.mongo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Abstract POJO to represent Models based on Morphia.
 *
 * @author Benjamin Bestmann
 */
@Entity
public abstract class MorphiaModel {

    @Id
    private ObjectId id;
    private Date created;
    private Date updated;

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
