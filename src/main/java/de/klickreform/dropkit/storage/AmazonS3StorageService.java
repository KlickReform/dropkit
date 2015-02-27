package de.klickreform.dropkit.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import java.io.*;
import java.util.UUID;

/**
 * StorageService to store an retrieve files using AmazonS3.
 *
 * @author Benjamin Bestmann
 */
public class AmazonS3StorageService implements StorageService {

    private AmazonS3 s3;
    private String bucketName;
    private String tempDirectory;

    public AmazonS3StorageService(AmazonS3 s3, String bucketName, String tempDirectory) {
        this.s3 = s3;
        this.bucketName = bucketName;
        this.tempDirectory = tempDirectory;
    }

    @Override
    public String store(InputStream uploadedInputStream, String path, String fileName) throws IOException {
        File file = new File(tempDirectory + UUID.randomUUID().toString());
        // Save InputStream to file in the specified temp directory
        OutputStream outputStream = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        outputStream = new FileOutputStream(file);
        while ((read = uploadedInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.flush();
        outputStream.close();
        // Use S3 object to store the file in the specified bucket
        PutObjectRequest request = new PutObjectRequest(bucketName, path + "/" + fileName, file);
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(request);
        // Delete the temp file from the system after upload
        file.delete();
        // Return the URL of the created object
        return getObjectUrl(path + "/" + fileName);
    }

    public String getTempDirectory() {
        return this.tempDirectory;
    }

    private String getObjectUrl(String objectPath) {
        return "https://s3." + s3.getBucketLocation(bucketName) + ".amazonaws.com/" + bucketName + "/" + objectPath;
    }

}
