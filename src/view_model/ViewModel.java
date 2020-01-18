package view_model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Model;

public class ViewModel {
	
	Model m;
	public DoubleProperty throttle,rudder,aileron,elevator;
	
	public ViewModel(Model m) {
		this.m=m;
		throttle=new SimpleDoubleProperty();
		rudder=new SimpleDoubleProperty();
		aileron=new SimpleDoubleProperty();
		elevator=new SimpleDoubleProperty();

		// when these values change, change the model values as well.
		throttle.addListener((o,old,nw)->m.setThrottle(nw.doubleValue()));
		rudder.addListener((o,old,nw)->m.setRudder(nw.doubleValue()));
		aileron.addListener((o,old,nw)->m.setAileron(nw.doubleValue()));
		elevator.addListener((o,old,nw)->m.setElevator(nw.doubleValue()));
		// when the model changes values it sends FlightGear the associated commands
	}
}