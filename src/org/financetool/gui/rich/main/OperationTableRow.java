package org.financetool.gui.rich.main;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.financetool.domain.Operation;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OperationTableRow {

    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ObjectProperty<LocalDate> date;
    private final SimpleStringProperty value;
    private final SimpleStringProperty description;

    public OperationTableRow(Operation operation) {
        this.date = new SimpleObjectProperty(operation.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        this.value = new SimpleStringProperty(currencyFormat(operation.getValue()));
        this.description = new SimpleStringProperty(operation.getDescription());
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance(new Locale("nl", "NL")).format(n);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public String getDateAsString() {
        return DATE_FORMAT.format(this.getDate());
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
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
