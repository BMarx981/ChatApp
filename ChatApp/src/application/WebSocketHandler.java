package application;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.google.gson.JsonObject;

@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class WebSocketHandler {
	CountDownLatch latch = new CountDownLatch(1);
	private ChatController controller;
	
	private Session session;
	
	public WebSocketHandler(ChatController controller){
		this.controller = controller;
		String dest = "ws://127.0.0.1:8081";
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(dest));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println("Connected to server!@");
	}
	
	@OnClose
	public void onClose(CloseReason reason, Session session) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}

	@OnMessage
	public void sendMessage(String str) {
		try {
			session.getBasicRemote().sendText(str);
//			StringBuilder sb = new StringBuilder(controller.ta.getText());
//			controller.ta.setText(sb.append(getTextMessage(str)).append("\n").toString());
			
		} catch(Exception io) {
			io.printStackTrace();
		}
	}
	
	private String getTextMessage(String message) {
		Jsonizer json = new Jsonizer("b.marx981@gmail.com", "bmarx", controller.getReceivers());
		return  json.getJsonObject(message).get("message").getAsString();
	}
}
