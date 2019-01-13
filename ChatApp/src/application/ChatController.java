package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController implements Initializable {

	@FXML
	TextArea ta = new TextArea();
	@FXML
	TextField textfield = new TextField();
	@FXML
	ListView<String> list = new ListView<String>();
	
	private String user;
	private String email;
	private ArrayList<String> receivers;
	private ArrayList<String> groups;
	
	WebSocketHandler wsh;
	Jsonizer json = new Jsonizer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 wsh = new WebSocketHandler();
		 user = "bmarx";
		 email = "b.marx981@gmail.com";
		 receivers = new ArrayList<String>();
		 receivers.add("ravlvania");
		 receivers.add("felixMarx");
		 json.setEmail(email).setUserName(user).setReceivers(receivers);
	}

	public void enterPressed(ActionEvent e) {
		String jsonText = json.getJsonObject(textfield.getText()).toString();
		wsh.sendMessage(jsonText);
		StringBuilder sb = new StringBuilder(ta.getText());
		sb.append(textfield.getText()).append("\n");
		ta.setText(sb.toString());
		textfield.setText("");
	}
}
