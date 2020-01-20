package model;

import interpreter.FlightSimulatorInterpreter;

public class MySimulatorModel implements SimulatorModel {

	private FlightSimulatorInterpreter interpreter;

	public MySimulatorModel() {
		this.interpreter = new FlightSimulatorInterpreter();
	}

	@Override
	public void openDataServer(int port, int frequency) {
		int result = interpreter.interpret(new String[] { "openDataServer " + port + " " + frequency });
		System.out.println(result); // for test only
	}

	@Override
	public void connect(String ip, int port) {
		int result = interpreter.interpret(new String[] { "connect " + ip + " " + port });
		System.out.println(result); // for test only
	}

	@Override
	public void disconnect() {
		int result = interpreter.interpret(new String[] { "disconnect" });
		System.out.println(result); // for test only
	}

	@Override
	public void runScript(String[] script) {
		int result = interpreter.interpret(script);
		System.out.println(result); // for test only
	}

	@Override
	public void setThrottle(double v) {
		int result = interpreter.interpret(new String[] { "throttle = " + v });
		System.out.println(result); // for test only
	}

	@Override
	public void setRudder(double v) {
		int result = interpreter.interpret(new String[] { "rudder = " + v });
		System.out.println(result); // for test only
	}

	@Override
	public void setAileron(double v) {
		int result = interpreter.interpret(new String[] { "aileron = " + v });
		System.out.println(result); // for test only
	}

	@Override
	public void setElevator(double v) {
		int result = interpreter.interpret(new String[] { "elevator = " + v });
		System.out.println(result); // for test only
	}
}