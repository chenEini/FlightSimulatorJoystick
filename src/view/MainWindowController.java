package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view_model.ViewModel;

public class MainWindowController {

	ViewModel vm;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	DoubleProperty aileron = new SimpleDoubleProperty();
	DoubleProperty elevator = new SimpleDoubleProperty();

	@FXML
	Button openConnectPopup;
	@FXML
	Button connect;
	@FXML
	Label connectErrorMsg;

	@FXML
	TextField simulatorIP;
	@FXML
	TextField simulatorPort;

	@FXML
	Button loadScript;
	@FXML
	Button runScript;
	@FXML
	TextArea simulatorScript;

	@FXML
	Slider throttle;
	@FXML
	Slider rudder;

	@FXML
	Circle joystick;
	@FXML
	Circle frameCircle;

	public void setViewModel(ViewModel vm) {
		this.vm = vm;

		vm.throttle.bind(throttle.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
		vm.aileron.bind(aileron);
		vm.elevator.bind(elevator);
	}

	@FXML
	private void openConnectPopup(ActionEvent event) throws IOException {
		FXMLLoader fxl = new FXMLLoader();
		fxl.setController(this);

		BorderPane connectPopup = fxl.load(getClass().getResource("ConnectPopup.fxml").openStream());

		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setScene(new Scene(connectPopup));
		stage.show();
	}

	@FXML
	private void connectToFlightGear(ActionEvent event) throws IOException {
		String ip = simulatorIP.getText();
		String port = simulatorPort.getText();

		if (ip.matches("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$") && port.matches("^(\\d{1,4})")) {

			vm.connectToFlightGear(ip, port);

			Stage stage = (Stage) connect.getScene().getWindow();
			stage.close();

		} else {
			connectErrorMsg.setText("Invalid IP or port, please try again");
		}
	}

	@FXML
	private void loadScript(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fc.getExtensionFilters().add(extFilter);

		File file = fc.showOpenDialog(null);

		String script = "";
		String line;
		if (file != null) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((line = br.readLine()) != null) {
					script = script + line + "\n";
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			simulatorScript.setText(script);
		}
	}

	@FXML
	private void runScript(ActionEvent event) throws IOException {
		if (simulatorScript.getText().length() != 0) {
			String[] script = simulatorScript.getText().split("\\r?\\n|\\r");
			vm.runScript(script);
		}
	}

	@FXML
	private void joystickPressed(MouseEvent me) {
		orgSceneX = me.getSceneX();
		orgSceneY = me.getSceneY();
		orgTranslateX = ((Circle) (me.getSource())).getTranslateX();
		orgTranslateY = ((Circle) (me.getSource())).getTranslateY();
	}

	@FXML
	private void joystickDragged(MouseEvent me) {
		double offsetX = me.getSceneX() - orgSceneX;
		double offsetY = me.getSceneY() - orgSceneY;
		double newTranslateX = orgTranslateX + offsetX;
		double newTranslateY = orgTranslateY + offsetY;
		double joystickCenterX = frameCircle.getTranslateX() + frameCircle.getRadius() - joystick.getRadius();
		double joystickCenterY = frameCircle.getTranslateY() - frameCircle.getRadius() - joystick.getRadius();
		double frameRadius = frameCircle.getRadius();
		double maxX = joystickCenterX + frameRadius;
		double maxY = joystickCenterY - frameRadius;
		double minX = joystickCenterX - frameRadius;
		double minY = joystickCenterY + frameRadius;
		double distance = Math
				.sqrt(Math.pow(newTranslateX - joystickCenterX, 2) + Math.pow(newTranslateY - joystickCenterY, 2));

		if (distance > frameRadius) {
			joystickReleased(me);
		} else {
			((Circle) (me.getSource())).setTranslateX(newTranslateX);
			((Circle) (me.getSource())).setTranslateY(newTranslateY);

			// normalize to range [-1,1]
			double normalX = Math.round(((((newTranslateX - minX) / (maxX - minX)) * 2) - 1) * 100.00) / 100.00;
			// normalize to range [-1,1]
			double normalY = Math.round(((((newTranslateY - minY) / (maxY - minY)) * 2) - 1) * 100.00) / 100.00;

			// send command only if manual mode is selected
			aileron.set(normalX);
			elevator.set(normalY);
		}
	}

	@FXML
	private void joystickReleased(MouseEvent me) {
		((Circle) (me.getSource()))
				.setTranslateX(frameCircle.getTranslateX() + frameCircle.getRadius() - joystick.getRadius());
		((Circle) (me.getSource()))
				.setTranslateY(frameCircle.getTranslateY() - frameCircle.getRadius() - joystick.getRadius());

		aileron.set(0.0);
		elevator.set(0.0);
	}
}