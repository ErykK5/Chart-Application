package com.data;

import java.util.List;
import java.util.SortedMap;

import com.data.formats.CsvDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataItemDao {

	public static List<DataItem> importFromFile(String filePath, FileFormat fileFormat) {
		if (filePath != null) {
				SortedMap<Number, Number> csvData = CsvDao.importCsv(filePath);
				return createDataItems(csvData);
		}
		return FXCollections.observableArrayList();
	}

	private static List<DataItem> createDataItems(SortedMap<Number, Number> data) {

		ObservableList<DataItem> items = FXCollections.observableArrayList();
		for (Number x : data.keySet()) {
			Number y = data.get(x);
			DataItem item = new DataItem(x, y);
			items.add(item);
		}

		return items;
	}

	public enum FileFormat {
		CSV("csv");

		private final String extension;

		FileFormat(String extension) {
			this.extension = extension;
		}

	}

}
