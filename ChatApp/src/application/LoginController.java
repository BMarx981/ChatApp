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
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML TextField userNameField = new TextField();
	@FXML PasswordField passwordField = new PasswordField();
	@FXML Button loginButton = new Button();
	
	private String userName = new String();
	private String password = new String();
	
	public void userNameEntered(ActionEvent e) {
		userName = userNameField.getText();
	}
	
	public void passwordEntered(ActionEvent e) {
		password = passwordField.getText();
	}
	
	public void loginPressed(ActionEvent e) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/ChatController.fxml"));
			Parent r = loader.load();
			r.getStylesheets().add("application/application.css");
			Scene next = new Scene(r);
			ChatController cc = new ChatController();
			cc.setUserName(userName);
			
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
