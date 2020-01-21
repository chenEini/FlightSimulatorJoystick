package model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interpreter.FlightSimulatorInterpreter;

public class MySimulatorModel implements SimulatorModel {

	private FlightSimulatorInterpreter interpreter;
	private ExecutorService es;

	public MySimulatorModel() {
		this.interpreter = new FlightSimulatorInterpreter();
		es = Executors.newFixedThreadPool(5);
	}

	@Override
	public void openDataServer(int port, int frequency) {
		es.execute(() -> interpreter.interpret(new String[] { "openDataServer " + port + " " + frequency }));
	}

	@Override
	public void connect(String ip, int port) {
		es.execute(() -> interpreter.interpret(new String[] { "connect " + ip + " " + port }));
	}

	@Override
	public void disconnect() {
		es.execute(() -> interpreter.interpret(new String[] { "disconnect" }));
	}

	@Override
	public void runScript(String[] script) {
		es.execute(() -> interpreter.interpret(script));
	}

	@Override
	public void setThrottle(double v) {
		es.execute(() -> interpreter.interpret(new String[] { "throttle = " + v }));
	}

	@Override
	public void setRudder(double v) {
		es.execute(() -> interpreter.interpret(new String[] { "rudder = " + v }));
	}

	@Override
	public void setAileron(double v) {
		es.execute(() -> interpreter.interpret(new String[] { "aileron = " + v }));
	}

	@Override
	public void setElevator(double v) {
		es.execute(() -> interpreter.interpret(new String[] { "elevator = " + v }));
	}
}