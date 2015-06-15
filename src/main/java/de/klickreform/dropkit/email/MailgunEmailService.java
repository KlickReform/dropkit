package de.klickreform.dropkit.email;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import de.klickreform.dropkit.exception.EmailException;

import javax.ws.rs.core.MediaType;

/**
 * Created by benjamin on 15.06.15.
 */
public class MailgunEmailService implements EmailService {

    private String key;
    private String domain;
    private static final String BASE_URL = "https://api.mailgun.net/v3";
    private static final String USERNAME = "api";


    public MailgunEmailService(String key, String domain) {
        this.key = key;
        this.domain = domain;
    }

    public void send(Email email) {
        // Create client and setup authentication
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(USERNAME,key));
        // Build Request Data from Email
        WebResource webResource = client.resource(BASE_URL + "/" + domain + "/messages");
        MultivaluedMapImpl mailData = new MultivaluedMapImpl();
        mailData.add("from",email.getFrom());
        mailData.add("to", email.getTo().get(0));
        mailData.add("subject", email.getSubject());
        if(containsHtml(email.getContents())) {
            mailData.add("html", email.getContents());
        } else {
            mailData.add("text", email.getContents());
        }
        // Send request as multipart/form-data
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, mailData);
        if (response.getStatus() != 200) {
            throw new EmailException("Could not send Email. Status: " + response.getStatus());
        }
    }

    private boolean containsHtml(String contents) {
        return contents.matches(".*\\<[^>]+>.*");
    }

}
