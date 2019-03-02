package application;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Jsonizer {

	private String userName;
	private ArrayList<String> receiver;
	
	public Jsonizer() {}
	
	public Jsonizer(String sender, ArrayList<String> receiver) {
		this.userName = sender;
		this.receiver = receiver;
	}
	
	public JsonObject getJsonObject(String text) {
		JsonObject obj = new JsonObject();
		obj.addProperty("message", text);
		obj.addProperty("username", userName);
		JsonArray rec = new JsonArray();
		for (String name : receiver) {
			rec.add(name);
		}
		obj.add("receivers", rec);
		
		return obj;
	}
	
	public String getJsonAsString(String text) {
		JsonObject obj = getJsonObject(text);
		return obj.toString();
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
