package model;

public interface SimulatorModel {

	void connect(String ip, int port);

	void disconnect();

	void setThrottle(double v);

	void setRudder(double v);

	void setAileron(double v);

	void setElevator(double v);
}