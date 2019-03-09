package com;

import java.net.URL;

import com.data.DataToCSV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainViewer extends Application {

	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

		DataToCSV dataToCSV = new DataToCSV(); //Only for first use of application
		dataToCSV.start();

		primaryStage.setTitle("JavaFX");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);


		final URL location = ChartController.class.getResource("Chart.fxml");
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(location);
		final Parent root = loader.load(location.openStream());

		final Scene scene = new Scene(root, 1200, 700);

		final String fancyChartCss = "css/chart.css";

		scene.getStylesheets().addAll(fancyChartCss);
		primaryStage.setScene(scene);
		primaryStage.show();

	}



}
