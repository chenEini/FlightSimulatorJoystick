package model;

import java.io.PrintWriter;
import java.net.Socket;

public class MySimulatorModel implements SimulatorModel {

	private static Socket simulator = null;
	private static PrintWriter out = null;

	@Override
	public void connect(String ip, int port) {
		try {
			simulator = new Socket(ip, port);
			out = new PrintWriter(simulator.getOutputStream());
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
	public void setThrottle(double v) {
		out.println("set /controls/engines/engine/throttle " + v);

		System.out.println("throttle " + v); // for test only
	}

	@Override
	public void setRudder(double v) {
		out.println("set /controls/flight/rudder " + v);

		System.out.println("rudder " + v); // for test only
	}

	@Override
	public void setAileron(double v) {
		out.println("set /controls/flight/aileron " + v);

		System.out.println("aileron " + v); // for test only
	}

	@Override
	public void setElevator(double v) {
		out.println("set /controls/flight/elevator " + v);

		System.out.println("elevator " + v); // for test only
	}
}