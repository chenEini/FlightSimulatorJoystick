package view_model;

import java.util.Observer;
import java.util.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.SimulatorModel;

public class ViewModel extends Observable implements Observer {

	SimulatorModel model;
	public DoubleProperty throttle, rudder, aileron, elevator;

	public ViewModel(SimulatorModel model) {
		this.model = model;

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

	@Override
	public void update(Observable o, Object arg) {
	}
}