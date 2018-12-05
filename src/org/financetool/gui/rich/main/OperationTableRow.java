package org.financetool.gui.rich.main;

import javafx.beans.property.SimpleStringProperty;
import org.financetool.domain.Operation;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class OperationTableRow {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private final SimpleStringProperty date;
    private final SimpleStringProperty value;
    private final SimpleStringProperty description;

    public OperationTableRow(Operation operation) {
        this.date = new SimpleStringProperty(DATE_FORMAT.format(operation.getDate()));
        this.value = new SimpleStringProperty(currencyFormat(operation.getValue()));
        this.description = new SimpleStringProperty(operation.getDescription());
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
