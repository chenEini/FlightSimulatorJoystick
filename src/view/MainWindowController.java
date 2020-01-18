package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import view_model.ViewModel;

public class MainWindowController {

	ViewModel vm;
	
	@FXML
	Button openConnectPopup;
	
	@FXML
	TextField connectionIp;
	@FXML
	TextField connectionPort;

	@FXML
	Slider throttle;
	@FXML
	Slider rudder;

	public void setViewModel(ViewModel vm) {

		this.vm = vm;

		vm.throttle.bind(throttle.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
	}
}