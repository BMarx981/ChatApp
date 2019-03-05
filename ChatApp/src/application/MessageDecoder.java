package application;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public void init(EndpointConfig config) {}

	@Override
	public void destroy() {}
	
	@Override
	public Message decode(String textMessage) throws DecodeException {
		Message message = new Message();
		JsonParser jp = new JsonParser();
        JsonObject jsonObject = jp.parse(textMessage).getAsJsonObject();
        message.setMessage(jsonObject.get("message").getAsString());
        message.setUser(jsonObject.get("username").getAsString());
        return message;
	}
	
	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
