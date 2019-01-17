package application;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.JsonObject;

public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	@Override
    public String encode(final Message message) throws EncodeException {
		JsonObject obj = new JsonObject();
		obj.addProperty("message", message.getContent());
		obj.addProperty("sender", message.getSender());
        return obj.getAsString();
    }

}
