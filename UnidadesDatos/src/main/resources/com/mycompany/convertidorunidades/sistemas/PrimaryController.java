package com.mycompany.convertidorunidades.sistemas;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrimaryController {

    @FXML
    private TextField inputValue;

    @FXML
    private ComboBox<String> unitInitial;

    @FXML
    private ComboBox<String> unitConvert;

    @FXML
    private CheckBox showAllConversions;

    @FXML
    private TextArea resultArea;

    private final ObservableList<String> siUnits = FXCollections.observableArrayList("Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes", "Petabytes", "Exabytes", "Zetabytes");
    private final ObservableList<String> iecUnits = FXCollections.observableArrayList("Bytes", "Kibibytes", "Mebibytes", "Gibibytes", "Tebibytes", "Pebibytes", "Exbibytes", "Zebibytes");

    @FXML
    public void initialize() {
        unitInitial.setItems(siUnits);
        unitConvert.setItems(siUnits);
    }

    @FXML
    private void convert() {
        String inputText = inputValue.getText();

        try {
            double value = Double.parseDouble(inputText);
            String initialUnit = unitInitial.getValue();
            String targetUnit = unitConvert.getValue();

            if (showAllConversions.isSelected()) {
                showAllConversions(value);
            } else {
                if (initialUnit == null || targetUnit == null) {
                    resultArea.setText("Selecciona las unidades.");
                    return;
                }
                double result = convertUnits(value, initialUnit, targetUnit);
                resultArea.setText(String.format("%.5f %s", result, targetUnit));
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Ingresa un valor numérico válido.");
        }
    }

    private double convertUnits(double value, String fromUnit, String toUnit) {
        int fromIndex = siUnits.indexOf(fromUnit);
        int toIndex = siUnits.indexOf(toUnit);

        if (fromIndex == -1 || toIndex == -1) {
            fromIndex = iecUnits.indexOf(fromUnit);
            toIndex = iecUnits.indexOf(toUnit);
            return value * Math.pow(1024, fromIndex - toIndex);
        } else {
            return value * Math.pow(1000, fromIndex - toIndex);
        }
    }

    private void showAllConversions(double value) {
        StringBuilder resultText = new StringBuilder("Conversión en SI:\n");
        for (String unit : siUnits) {
            double result = convertUnits(value, unitInitial.getValue(), unit);
            resultText.append(String.format("%.5f %s\n", result, unit));
        }

        resultText.append("\nConversión en IEC:\n");
        for (String unit : iecUnits) {
            double result = convertUnits(value, unitInitial.getValue(), unit);
            resultText.append(String.format("%.5f %s\n", result, unit));
        }

        resultArea.setText(resultText.toString());
    }
}
