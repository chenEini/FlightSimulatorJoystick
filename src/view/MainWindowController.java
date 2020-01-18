package view;

import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import view_model.ViewModel;

public class MainWindowController implements Observer {

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

	@Override
	public void update(Observable o, Object arg) {
	}
}