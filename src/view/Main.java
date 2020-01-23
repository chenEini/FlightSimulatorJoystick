package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view_model.ViewModel;

import model.MySimulatorModel;
import model.SimulatorModel;

public class Main extends Application {
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;

		SimulatorModel simModel = new MySimulatorModel(); // Model

		ViewModel simViewModel = new ViewModel(simModel); // ViewModel

		FXMLLoader fxl = new FXMLLoader();

		try {
			BorderPane root = fxl.load(getClass().getResource("MainWindow.fxml").openStream());

			MainWindowController windowController = fxl.getController(); // View

			windowController.setViewModel(simViewModel);

			Scene scene = new Scene(root, 340, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setOnCloseRequest(E -> {
				simViewModel.disconnectFromFlightGear();
			});

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}