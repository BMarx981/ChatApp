package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private SimpleStringProperty msg = new SimpleStringProperty();
    private SimpleStringProperty user = new SimpleStringProperty();
    
    public Message() {}
    public Message(String message, String userName) {
    	msg = new SimpleStringProperty(message);
    	user = new SimpleStringProperty(userName);
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
