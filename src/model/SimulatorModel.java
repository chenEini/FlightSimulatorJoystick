package model;

public interface SimulatorModel {

	void connectToSimulator(String ip, int port);

	void setThrottle(double v);

	void setRudder(double v);

	void setAileron(double v);

	void setElevator(double v);
}