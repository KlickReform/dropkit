package de.klickreform.dropkit.mongo;

import com.fasterxml.jackson.annotation.JsonView;
import de.klickreform.dropkit.models.DomainModel;
import de.klickreform.dropkit.utils.Views;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

import java.util.Date;

/**
 * DomainModel implementation using Morphia as a MongoDB persistence framework.
 *
 * @author Benjamin Bestmann
 */
@Entity
public abstract class MorphiaDomainModel implements DomainModel {

    @Id
    private ObjectId id = new ObjectId();
    private Date created;
    private Date updated;

    @Override
    public String getId() {
        return id.toString();
    }

    @Override
    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    @Override
    @JsonView(Views.Private.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    @JsonView(Views.Private.class)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @PrePersist
    public void prePersist() {
        // Update dates before entity gets persisted
        this.created = (this.created == null) ? new Date() : this.created;
        this.updated = (this.updated == null) ? this.created : new Date();
    }

}
