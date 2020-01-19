package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view_model.ViewModel;

public class MainWindowController {

	ViewModel vm;

	@FXML
	Button openConnectPopup;
	@FXML
	Button connect;

	@FXML
	TextField simulatorIP;
	@FXML
	TextField simulatorPort;

	@FXML
	Slider throttle;
	@FXML
	Slider rudder;

	@FXML
	Circle joystick;
	@FXML
	Circle frameCircle;

	public void setViewModel(ViewModel vm) {
		this.vm = vm;

		vm.throttle.bind(throttle.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
	}

	@FXML
	private void openConnectPopup(ActionEvent event) throws IOException {
		FXMLLoader fxl = new FXMLLoader();
		fxl.setController(this);

		BorderPane connectPopup = fxl.load(getClass().getResource("ConnectPopup.fxml").openStream());

		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(connectPopup));
		stage.show();
	}

	@FXML
	private void connectToFlightGear(ActionEvent event) throws IOException {
		String ip = simulatorIP.getText();
		String port = simulatorPort.getText();

		vm.connectToFlightGear(ip, port);

		Stage stage = (Stage) connect.getScene().getWindow();
		stage.close();
	}
}