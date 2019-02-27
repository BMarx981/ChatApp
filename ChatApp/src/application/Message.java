package application;

import java.util.ArrayList;
import java.util.Date;

public class Message {

    private String content;
    private String sender;
    private String email;
    private Date received;

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final String getSender() {
        return sender;
    }

    public final void setSender(String sender) {
        this.sender = sender;
    }
    
    public final String getEmail() {
    	return this.email;
    }
    
    public final void setEmail(String email) {
    	this.email = email;
    }

    public final Date getReceived() {
        return received;
    }

    public final void setReceived(final Date received) {
        this.received = received;
    }
    
    public String getMessageAsString(String text) {
    	Jsonizer json = new Jsonizer(email, sender, new ArrayList<String>());
    	return json.getJsonAsString(text);
    }
}
