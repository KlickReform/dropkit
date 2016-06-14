package de.klickreform.dropkit.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Factory to create instances of HandleBarsTemplateService from the Dropwizard Configuration.
 *
 * @author Benjamin Bestmann
 */
public class HandlebarsFactory {

    private String path;
    private String fileType;

    @NotEmpty
    @JsonProperty
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @NotEmpty
    @JsonProperty
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public HandlebarsTemplateService buildClient() {
        HandlebarsTemplateService hbs = new HandlebarsTemplateService(path,fileType);
        return hbs;
    }
}
