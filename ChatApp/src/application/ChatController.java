package application;

import java.net.URL;
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
	
	WebSocketHandler wsh;
	Jsonizer json = new Jsonizer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 wsh = new WebSocketHandler();
	}

	public void enterPressed(ActionEvent e) {
		wsh.sendMessage(textfield.getText());
		StringBuilder sb = new StringBuilder(ta.getText());
		sb.append(textfield.getText()).append("\n");
		ta.setText(sb.toString());
		textfield.setText("");
	}
}
