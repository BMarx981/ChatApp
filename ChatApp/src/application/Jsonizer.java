package application;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Jsonizer {

	private String email;
	private String userName;
	private ArrayList<String> receiver;
	
	public Jsonizer() {}
	
	public Jsonizer(String email, String sender, ArrayList<String> receiver) {
		this.email = email;
		this.userName = sender;
		this.receiver = receiver;
	}
	
	public JsonObject getJsonObject(String text) {
		JsonObject obj = new JsonObject();
		obj.addProperty("message", text);
		obj.addProperty("email", email);
		obj.addProperty("username", userName);
		JsonArray rec = new JsonArray();
		for (String name : receiver) {
			rec.add(name);
		}
		obj.add("receivers", rec);
		
		return obj;
	}
	
	public Jsonizer setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public Jsonizer setReceivers(ArrayList<String> receivers) {
		this.receiver = receivers;
		return this;
	}
	
	public Jsonizer setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
}
