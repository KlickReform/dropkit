package de.klickreform.dropkit.email;

import java.util.Arrays;
import java.util.List;

/**
 * POJO to represent Emails.
 *
 * @author Benjamin Bestmann
 */
public class Email {

    private String from;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String contents;

    public Email() { }

    public Email(String to, String contents) {
        this.to = Arrays.asList(to.split(","));
        this.contents = contents;
    }

    public Email(String from, String to, String contents) {
        this.from = from;
        this.to = Arrays.asList(to.split(","));
        this.contents = contents;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
