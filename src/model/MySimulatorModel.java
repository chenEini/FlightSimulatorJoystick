package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MySimulatorModel implements SimulatorModel {

	Socket simulator = null;
	PrintWriter out = null;

	@Override
	public void connect(String ip, int port) {
		try {
			simulator = new Socket(ip, port);
			out = new PrintWriter(simulator.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
			connect(ip, port);
		}
	}

	@Override
	public void disconnect() {
		try {
			if (simulator != null)
				simulator.close();
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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