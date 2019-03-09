package com;

import java.util.ArrayList;
import java.util.List;

import com.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class ChartController {

	private static final int NUMBER_OF_DATA_SETS = 38;

	private static int site = 0;

	private final List<ObservableList<DataItem>> ALL = new ArrayList<>();

	@FXML
	private VBox chartBox;
	@FXML
	private Label dd;
	@FXML
	private Label rirm;
	@FXML
	private Button Top;
	@FXML
	private Button Down;
	@FXML
	private VBox All;

	private LineChart<Number, Number> chart;

	public void initialize() {
		initAll();
		loadData();
		createChart();
		setSettings();
	}

	private void setSettings() {
		dd.setText("1.0");
		rirm.setText("-");
		Down.setDisable(true);
		All.setStyle("-fx-background-color: #000000;");
	}

	private void initAll() {
		for (int i = 0; i < NUMBER_OF_DATA_SETS; i++) {
			final ObservableList<DataItem> dataItems = FXCollections.observableArrayList();
			ALL.add(dataItems);
		}
	}

	private void addDataItem() {
		for (int i = 0; i < ALL.size(); i++) {
			ObservableList<DataItem> dataItems = ALL.get(i);
			setData(dataItems, i);
		}

	}

	private void loadData() {
		for (int j = 0; j < NUMBER_OF_DATA_SETS; j++) {
			String filePath =".\\resources\\data\\graphs10\\graph" + (j+1) + ".csv";
			ALL.get(j).setAll(DataItemDao.importFromFile(filePath, DataItemDao.FileFormat.CSV));
		}
	}

	private void setData(List<DataItem> items, int index) {
		Series<Number, Number> series = chart.getData().get(index);
		clear(series);

		for (DataItem dataItem : items) {
			final Data<Number, Number> data = new XYChart.Data<Number, Number>();
			data.YValueProperty().bind(dataItem.yProperty());
			data.XValueProperty().bind(dataItem.xProperty());
			series.getData().add(data);
		}
	}

	private void clear(Series<Number, Number> series) {
		for (Data<Number, Number> data : series.getData()) {
			data.XValueProperty().unbind();
			data.YValueProperty().unbind();
		}
		series.getData().clear();
	}

	private void createChart() {

		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickLabelFormatter(new StringConverter<Number>() {
			@Override
			public String toString(Number object) {
				if( Float.valueOf(object.toString()) == 1.0f ) {
					return "10^1";
				}
				if( Float.valueOf(object.toString()) == 0.0f ) {
					return "10^0";
				}
				if( Float.valueOf(object.toString()) == 2.0f ) {
					return "10^2";
				}
				if( Float.valueOf(object.toString()) == -1.0f ) {
					return "10^-1";
				}
				return null;
			}

			@Override
			public Number fromString(String string) {
				return null;
			}
		});
		NumberAxis yAxis = new NumberAxis();
		yAxis.setTickLabelFormatter(new StringConverter<Number>() {
			@Override
			public String toString(Number object) {
				if( Float.valueOf(object.toString()) == 1.0f ) {
					return "10^1";
				}
				if( Float.valueOf(object.toString()) == 0.0f ) {
					return "10^0";
				}
				if( Float.valueOf(object.toString()) == 2.0f ) {
					return "10^2";
				}
				if( Float.valueOf(object.toString()) == -1.0f ) {
					return "10^-1";
				}
				if( Float.valueOf(object.toString()) == 3.0f ) {
					return "10^3";
				}
				if( Float.valueOf(object.toString()) == 4.0f ) {
					return "10^4";
				}
				return null;
			}

			@Override
			public Number fromString(String string) {
				return null;
			}
		});
		chart = new LineChart<>(xAxis, yAxis);
		xAxis.setLabel("L/d");
		yAxis.setLabel("Ra/Rm");

		chart.setCreateSymbols(false);
		final List<XYChart.Series<Number, Number>> seriesList = createChartSeries();
		chart.getData().addAll(seriesList);

		StackPane chartPane = new StackPane(chart);

		VBox.setVgrow(chartPane, Priority.ALWAYS);

		chartBox.getChildren().add(0, chartPane);
		addDataItem();
	}

	private List<XYChart.Series<Number, Number>> createChartSeries() {

		final List<XYChart.Series<Number, Number>> seriesList = new ArrayList<>();
		float[] arr = {
				0.10f, 0.20f, 0.30f, 0.40f, 0.50f, 0.75f, 1.00f, 1.25f, 1.50f, 1.75f, 2.00f, 2.50f, 3.00f, 4.00f, 5.00f, 7.50f,
				10.00f, 15.00f, 20.00f, 25.00f, 30.00f, 40.00f, 50.00f, 75.00f, 100.00f, 150.00f, 200.00f, 250.00f, 300.00f,
				400.00f, 500.00f, 750.00f, 1000.00f, 2000.00f, 3000.00f, 5000.00f, 7500.00f, 10000.00f
		};
		for (int i = 0; i < NUMBER_OF_DATA_SETS; i++) {
			final XYChart.Series<Number, Number> series = new XYChart.Series<>();
			String tmp = String.valueOf(arr[i]);
			series.setName(tmp);
			seriesList.add(series);
		}
		return seriesList;
	}

	public void goTop(ActionEvent actionEvent) {

		if ( site == 571 ) Top.setDisable(true);
		Down.setDisable(false);
		int site2 = site % 38+1;
		int site3 = site / 38;
		site++;
		float[] Dd = { 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 10.0f, 12.0f, 14.0f, 16.0f, 24.0f, 32.0f };
		float[] rirmArr = { 0.10f, 0.20f, 0.30f, 0.40f, 0.50f, 0.75f, 1.00f, 1.25f, 1.50f, 1.75f, 2.00f, 2.50f, 3.00f, 4.00f, 5.00f, 7.50f,
				10.00f, 15.00f, 20.00f, 25.00f, 30.00f, 40.00f, 50.00f, 75.00f, 100.00f, 150.00f, 200.00f, 250.00f, 300.00f,
				400.00f, 500.00f, 750.00f, 1000.00f, 2000.00f, 3000.00f, 5000.00f, 7500.00f, 10000.00f
		};
		dd.setText(String.valueOf(Dd[site3+1]));
		if( site2 != 0 )
			rirm.setText(String.valueOf(rirmArr[site2-1]));
		chart.setData(FXCollections.observableArrayList());

		for (int i = 0; i < NUMBER_OF_DATA_SETS; i++) {
			final ObservableList<DataItem> dataItems = FXCollections.observableArrayList();
			ALL.add(dataItems);
		}

		for (int j = 0; j < NUMBER_OF_DATA_SETS; j++) {
			String filePath =".\\resources\\data\\graphs" + (int)(Dd[(site3+1)]*10) + "\\rirm" + (site2) + "\\graph" + (j+1) + ".csv";
			ALL.get(j).setAll(DataItemDao.importFromFile(filePath, DataItemDao.FileFormat.CSV));
		}

		final List<XYChart.Series<Number, Number>> seriesList = createChartSeries();
		chart.getData().addAll(seriesList);

		for( int i = 0; i < 38; i++ ) {

			ObservableList<DataItem> dataItems = ALL.get(i);
			setData(dataItems,i);
		}

	}

	public void goDown(ActionEvent actionEvent) {
		Top.setDisable(false);
		if( site > 1 ) {
			site--;
		}
		else if( site == 1 ){
			site--;
			dd.setText("1.0");
			Down.setDisable(true);
			rirm.setText("-");
			chart.setData(FXCollections.observableArrayList());

			for (int i = 0; i < NUMBER_OF_DATA_SETS; i++) {
				final ObservableList<DataItem> dataItems = FXCollections.observableArrayList();
				ALL.add(dataItems);
			}

			for (int j = 0; j < NUMBER_OF_DATA_SETS; j++) {
				String filePath =".\\resources\\data\\graphs10\\graph" + (j+1) + ".csv";
				ALL.get(j).setAll(DataItemDao.importFromFile(filePath, DataItemDao.FileFormat.CSV));
			}

			final List<XYChart.Series<Number, Number>> seriesList = createChartSeries();
			chart.getData().addAll(seriesList);

			for( int i = 0; i < 38; i++ ) {

				ObservableList<DataItem> dataItems = ALL.get(i);
				setData(dataItems,i);
			}
			return;
		}
		int site2 = site % 38;
		if( site2 == 0 ) site2 = 38;
		int site3 = site / 38;
        if( site == 38 ) site3--;
		float[] Dd = { 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 10.0f, 12.0f, 14.0f, 16.0f, 24.0f, 32.0f };
		float[] rirmArr = { 0.10f, 0.20f, 0.30f, 0.40f, 0.50f, 0.75f, 1.00f, 1.25f, 1.50f, 1.75f, 2.00f, 2.50f, 3.00f, 4.00f, 5.00f, 7.50f,
				10.00f, 15.00f, 20.00f, 25.00f, 30.00f, 40.00f, 50.00f, 75.00f, 100.00f, 150.00f, 200.00f, 250.00f, 300.00f,
				400.00f, 500.00f, 750.00f, 1000.00f, 2000.00f, 3000.00f, 5000.00f, 7500.00f, 10000.00f
		};

		dd.setText(String.valueOf(Dd[site3+1]));
		rirm.setText(String.valueOf(rirmArr[site2-1]));
		chart.setData(FXCollections.observableArrayList());

		for (int i = 0; i < NUMBER_OF_DATA_SETS; i++) {
			final ObservableList<DataItem> dataItems = FXCollections.observableArrayList();
			ALL.add(dataItems);
		}

		for (int j = 0; j < NUMBER_OF_DATA_SETS; j++) {

			String filePath =".\\resources\\data\\graphs" + (int)(Dd[(site3+1)]*10) + "\\rirm" + (site2) + "\\graph" + (j+1) + ".csv";
			ALL.get(j).setAll(DataItemDao.importFromFile(filePath, DataItemDao.FileFormat.CSV));
		}

		final List<XYChart.Series<Number, Number>> seriesList = createChartSeries();
		chart.getData().addAll(seriesList);

		for( int i = 0; i < 38; i++ ) {

			ObservableList<DataItem> dataItems = ALL.get(i);
			setData(dataItems,i);
		}
	}
}