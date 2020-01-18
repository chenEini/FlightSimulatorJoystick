package view;

import javafx.scene.control.Slider;
import javafx.fxml.FXML;
import view_model.ViewModel;

public class MainWindowController {

	ViewModel vm;

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