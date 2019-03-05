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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ChatController implements Initializable {

	@FXML
	TableView<Message> tv = new TableView<Message>();
	@FXML
	TableColumn<Message, String> msgCol = new TableColumn<Message, String>();
	@FXML
	TableColumn<Message, String> infoCol = new TableColumn<Message, String>();
	@FXML
	TextField textfield = new TextField();
	@FXML
	ListView<String> listView = new ListView<String>();
	@FXML 
	public ObservableList<Message> messages = FXCollections.observableArrayList();
	
	public String user;
	private ArrayList<String> receivers = new ArrayList<String>();
	ObservableList<String> receiversList = FXCollections.observableArrayList();
	
	WebSocketHandler wsh;
	Jsonizer json = new Jsonizer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 wsh = new WebSocketHandler(this);
		 tv.setItems(messages);
		 msgCol.setCellValueFactory(cellData -> cellData.getValue().msgProperty());
		 infoCol.setCellValueFactory(cellData -> cellData.getValue().userProperty());
		 receivers.add("usersFriend1");
		 receivers.add("usersFriend2");
		 receiversList.addAll(receivers);
		 listView.setItems(receiversList);
		 json.setUserName(user).setReceivers(receivers);
	}

	public void enterPressed(ActionEvent e) {
		Message msg = new Message();
		msg.setUser(user);
		msg.setMessage(textfield.getText());
		wsh.writeMessage(msg.getMessageAsString(textfield.getText()));
		textfield.setText("");
	}
	
	public ArrayList<String> getReceivers() {
		return receivers;
	}
	
	public void addTextToTable(Message m) {
		messages.add(m);
	}
	
	public void setUserName(String name) {
		this.user = name;
	}
	
	public String getUserName() {
		return user;
	}
}
