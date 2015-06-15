package de.klickreform.dropkit.storage;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Factory to create instances of AmazonS3Factory from the Dropwizard Configuration.
 *
 * @author Benjamin Bestmann
 */
public class AmazonS3Factory {

    private String region;
    private String bucketName;
    private String tempDirectory;

    @NotEmpty
    @JsonProperty
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @NotEmpty
    @JsonProperty
    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @NotEmpty
    @JsonProperty
    public String getTempDirectory() {
        return tempDirectory;
    }

    public void setTempDirectory(String tempDirectory) {
        this.tempDirectory = tempDirectory;
    }

    public AmazonS3 buildClient() {
        AmazonS3 s3 = new AmazonS3Client();
        s3.setRegion(Region.getRegion(Regions.fromName(this.region)));
        return s3;
    }

}
