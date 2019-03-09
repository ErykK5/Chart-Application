package com.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class DataItem {

	private final Property<Number> xProperty = new SimpleObjectProperty<Number>(this, "L/d");
	private final Property<Number> yProperty = new SimpleObjectProperty<Number>(this, "Ra/Rm");

	public DataItem(final Number x, final Number y) {
		xProperty.setValue(x);
		yProperty.setValue(y);
	}

	public Property<Number> xProperty() {
		return xProperty;
	}

	public Property<Number> yProperty() {
		return yProperty;
	}

	public Number getX() {
		return xProperty.getValue();
	}

	public Number getY() {
		return yProperty.getValue();
	}

	public void setX(final Number x) {
		xProperty.setValue(x);
	}

	public void setY(final Number v) {
		yProperty.setValue(v);
	}
}