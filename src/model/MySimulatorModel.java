package model;

import java.net.Socket;
import java.io.PrintWriter;
import interpreter.FlightSimulatorInterpreter;

public class MySimulatorModel implements SimulatorModel {

	private static Socket simulator = null;
	private static PrintWriter out = null;

	private FlightSimulatorInterpreter interpreter;

	public MySimulatorModel() {
		this.interpreter = new FlightSimulatorInterpreter();
	}

	@Override
	public void connect(String ip, int port) {
		try {
			simulator = new Socket(ip, port);
			out = new PrintWriter(simulator.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		try {
			if (out != null)
				out.close();
			if (simulator != null)
				simulator.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void runScript(String[] script) {
		int result = interpreter.interpret(script);
		System.out.println(result); // for test only
	}

	@Override
	public void setThrottle(double v) {
		if (out != null)
			out.println("set controls/engines/engine/throttle " + v);
	}

	@Override
	public void setRudder(double v) {
		if (out != null)
			out.println("set controls/flight/rudder " + v);
	}

	@Override
	public void setAileron(double v) {
		if (out != null)
			out.println("set controls/flight/aileron " + v);
	}

	@Override
	public void setElevator(double v) {
		if (out != null)
			out.println("set controls/flight/elevator " + v);
	}
}