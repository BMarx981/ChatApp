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
import com.google.gson.JsonParser;

@ClientEndpoint(decoders= {MessageDecoder.class})
public class WebSocketHandler {
	CountDownLatch latch = new CountDownLatch(1);
	private ChatController controller;
	
	private Session session;
	
	public WebSocketHandler(ChatController controller){
		this.controller = controller;
		String dest = "ws://127.0.0.1:8080/";
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
		System.out.println("Connected to server!");
	}
	
	@OnClose
	public void onClose(CloseReason reason, Session session) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Closing WebSocket due to " + reason.getReasonPhrase());
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}

	@OnMessage
	public void receiveMessage(String str) {
		controller.addTextToTable(new Message(extractMessage(str), extractUser(str)));
	}
	
	public void writeMessage(String str) {
		try {
			session.getBasicRemote().sendText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Session getSession() {
		return this.session;
	}
	
	private String extractMessage(String msg) {
		JsonParser jp = new JsonParser();
		JsonObject main = jp.parse(msg).getAsJsonObject();
		return main.get("message").getAsString();
	}
	
	private String extractUser(String msg) {
		JsonParser jp = new JsonParser();
		JsonObject main = jp.parse(msg).getAsJsonObject();
		return main.get("username").getAsString();
	}
}
