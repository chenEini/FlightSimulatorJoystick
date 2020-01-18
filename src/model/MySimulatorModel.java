package model;

import java.util.Observable;

public class MySimulatorModel extends Observable implements SimulatorModel {

	public void connectToFlightGear(String ip, int port) {
		// connect to flight gear
	}

	@Override
	public void setThrottle(double v) {
		setChanged();
		notifyObservers();
		System.out.println("throttle " + v);
	}

	@Override
	public void setRudder(double v) {
		setChanged();
		notifyObservers();
		System.out.println("rudder " + v);
	}

	@Override
	public void setAileron(double v) {
		setChanged();
		notifyObservers();
		System.out.println("aileron " + v);
	}

	@Override
	public void setElevator(double v) {
		setChanged();
		notifyObservers();
		System.out.println("elevator " + v);
	}
}