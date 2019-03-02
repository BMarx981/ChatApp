package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController implements Initializable {

	@FXML
	TextArea tf = new TextArea();
	@FXML
	TextField textfield = new TextField();
	@FXML
	ListView<String> listView = new ListView<String>();
	
	private String user;
	private ArrayList<String> receivers = new ArrayList<String>();
	
	WebSocketHandler wsh;
	Jsonizer json = new Jsonizer();
	ObservableList<String> receiversList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 wsh = new WebSocketHandler(this);
		 receivers.add("usersFriend1");
		 receivers.add("usersFriend2");
		 receiversList.addAll(receivers);
		 listView.setItems(receiversList);
		 json.setUserName(user).setReceivers(receivers);
	}

	public void enterPressed(ActionEvent e) {
		Message msg = new Message();
		msg.setSender(user);
		msg.setContent(textfield.getText());
		wsh.writeMessage(msg.getMessageAsString(textfield.getText()));
		textfield.setText("");
	}
	
	public ArrayList<String> getReceivers() {
		return receivers;
	}
	
	public void addTextToArea(String text, String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.append(text).append("\n");
		tf.setText(sb.toString());
	}
	
	public void setUserName(String name) {
		this.user = name;
	}
	
	public String getUserName() {
		return user;
	}
}
