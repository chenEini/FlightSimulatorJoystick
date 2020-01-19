package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view_model.ViewModel;

public class MainWindowController {

	ViewModel vm;

	@FXML
	Button openConnectPopup;

	@FXML
	TextField simulatorIP;
	@FXML
	TextField simulatorPort;

	@FXML
	Slider throttle;
	@FXML
	Slider rudder;
	
	@FXML
	private Slider rudderSlider;
	@FXML
	private Slider throttleSlider;
	@FXML
	private Circle joystick;
	@FXML
	private Circle frameCircle;

	public void setViewModel(ViewModel vm) {

		this.vm = vm;

		vm.throttle.bind(throttle.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
		vm.simulatorIP.bind(simulatorIP.textProperty());
		vm.simulatorPort.bind(simulatorPort.textProperty());
	}

	@FXML
	private void openConnectPopup(ActionEvent event) throws IOException {
		FXMLLoader fxl = new FXMLLoader();
		fxl.setController(this);

		Parent connectPopup = (Parent) fxl.load(getClass().getResource("ConnectPopup.fxml").openStream());

		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(connectPopup));
		stage.show();
	}
	
}