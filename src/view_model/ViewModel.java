package view_model;

import model.SimulatorModel;
import java.util.Observer;
import java.util.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel extends Observable implements Observer {

	SimulatorModel model;
	public StringProperty simulatorIP;
	public StringProperty simulatorPort;
	public DoubleProperty throttle, rudder, aileron, elevator;

	public ViewModel(SimulatorModel model) {

		this.model = model;

		simulatorIP = new SimpleStringProperty();
		simulatorPort = new SimpleStringProperty();

		throttle = new SimpleDoubleProperty();
		rudder = new SimpleDoubleProperty();
		aileron = new SimpleDoubleProperty();
		elevator = new SimpleDoubleProperty();

		// when these values change, change the model values as well.
		throttle.addListener((o, old, newVal) -> model.setThrottle(newVal.doubleValue()));
		rudder.addListener((o, old, newVal) -> model.setRudder(newVal.doubleValue()));
		aileron.addListener((o, old, newVal) -> model.setAileron(newVal.doubleValue()));
		elevator.addListener((o, old, newVal) -> model.setElevator(newVal.doubleValue()));
	}

	public void connectToFlightGear() {
		model.connectToSimulator(simulatorIP.get(), Integer.parseInt(simulatorPort.get()));
	}

	@Override
	public void update(Observable o, Object arg) {
	}
}