package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginButton.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER && loginButton.isFocused()) {
				loginPressed(null);
			}
		});
		
		userNameField.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
				 setUserName();
			}
		});
		
		passwordField.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
				 passwordEntered(null);
			}
		});
	}
	
	@FXML TextField userNameField = new TextField();
	@FXML PasswordField passwordField = new PasswordField();
	@FXML Button loginButton = new Button();
	
	private String userName = new String();
	private String password = new String();
	
	private void setUserName() {
		this.userName = userNameField.getText();
	}
	public void userNameEntered(ActionEvent e) {
		userName = userNameField.getText();
		passwordField.requestFocus();
	}
	
	public void passwordEntered(ActionEvent e) {
		password = passwordField.getText();
		loginButton.requestFocus();
	}
	
	public void loginPressed(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ChatScene.fxml"));
			Parent r = loader.load();
			ChatController cc = loader.<ChatController>getController();
			cc.setUserName(userName);
			r.getStylesheets().add("application/application.css");
			Scene next = new Scene(r);
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.setScene(next);
			stage.setTitle("Chattr");
			stage.show();
		} catch (Exception ex) {
			System.out.println("scene fail");
			ex.printStackTrace();
		}
	}
}
