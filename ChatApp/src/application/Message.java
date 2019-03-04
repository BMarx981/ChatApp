package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private String content;
    private String sender;
    private Date received;
    private final SimpleStringProperty msg = new SimpleStringProperty();
    private final SimpleStringProperty user = new SimpleStringProperty();

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

    public final Date getReceived() {
        return received;
    }

    public final void setReceived(final Date received) {
        this.received = received;
    }
    
    public StringProperty msgProperty() { return msg; }
    public String getMessage() { return msg.get(); }
    public void setMessage(String name) { msg.set(name); }
    
    public StringProperty userProperty() { return user; }
    public String getUser() { return user.get(); }
    public void setUser(String user) { this.user.set(user); }
    
    public String getMessageAsString(String text) {
    	Jsonizer json = new Jsonizer(user.get(), new ArrayList<String>());
    	return json.getJsonAsString(text);
    }
}
