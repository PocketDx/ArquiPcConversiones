package com.mycompany.convertidorunidades.sistemas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField txtValor;

    @FXML
    private ComboBox<String> cmbUnidadInicial;

    @FXML
    private ComboBox<String> cmbUnidadConvertir;

    @FXML
    private TextArea txtResultado;

    @FXML
    private TextField txtBytes1;

    @FXML
    private TextField txtKilobytes;

    @FXML
    private TextField txtMegabytes;

    @FXML
    private TextField txtGigabytes;

    @FXML
    private TextField txtTerabytes;

    @FXML
    private TextField txtPetabytes;

    @FXML
    private TextField txtExabytes;

    @FXML
    private TextField txtZetabytes;

    @FXML
    private TextField txtBytes2;
    
    @FXML
    private TextField txtKibibytes;

    @FXML
    private TextField txtMebibytes;

    @FXML
    private TextField txtGibibytes;

    @FXML
    private TextField txtTebibytes;

    @FXML
    private TextField txtPebibytes;

    @FXML
    private TextField txtExbibytes;

    @FXML
    private TextField txtZebibytes;


    public void initialize() {
        cmbUnidadInicial.getItems().addAll(
            // SI (Base 10)
            "Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes", 
            "Petabytes", "Exabytes", "Zetabytes",

            // IEC (Base 1024)
            "Bytes", "Kibibytes", "Mebibytes", "Gibibytes", "Tebibytes", 
            "Pebibytes", "Exbibytes", "Zebibytes"
        );

        cmbUnidadConvertir.getItems().addAll(
            "Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes", 
            "Petabytes", "Exabytes", "Zetabytes",

            "Bytes", "Kibibytes", "Mebibytes", "Gibibytes", "Tebibytes", 
            "Pebibytes", "Exbibytes", "Zebibytes"
        );
    }
    
    private double convertirDesdeBytes(double bytes, String unidad) {
    switch (unidad) {
        case "Bytes": return bytes;
        case "Kilobytes": return bytes / 1_000;
        case "Megabytes": return bytes / Math.pow(10, 6);
        case "Gigabytes": return bytes / Math.pow(10, 9);
        case "Terabytes": return bytes / Math.pow(10, 12);
        case "Petabytes": return bytes / Math.pow(10, 15);
        case "Exabytes": return bytes / Math.pow(10, 18);
        case "Zetabytes": return bytes / Math.pow(10, 21);

        case "Kibibytes": return bytes / 1024;
        case "Mebibytes": return bytes / Math.pow(1024, 2);
        case "Gibibytes": return bytes / Math.pow(1024, 3);
        case "Tebibytes": return bytes / Math.pow(1024, 4);
        case "Pebibytes": return bytes / Math.pow(1024, 5);
        case "Exbibytes": return bytes / Math.pow(1024, 6);
        case "Zebibytes": return bytes / Math.pow(1024, 7);

        default: return bytes;
    }
}


    @FXML
private void calcular() {
    try {
        double valor = Double.parseDouble(txtValor.getText());
        String unidadInicial = cmbUnidadInicial.getValue();
        String unidadConvertir = cmbUnidadConvertir.getValue();

        if (unidadInicial == null || unidadConvertir == null) {
            txtResultado.setText("Seleccione ambas unidades.");
            return;
        }

        // Convertir el valor a bytes como base
        double valorEnBytes = convertirABytes(valor, unidadInicial);

        // Convertir los bytes a la unidad de destino
        double resultadoFinal = convertirDesdeBytes(valorEnBytes, unidadConvertir);

        // Mostrar el resultado específico en txtResultado
        txtResultado.setText(String.format("%.8f %s", resultadoFinal, unidadConvertir));

        // Actualizar los campos SI e IEC
        actualizarCamposSI(valorEnBytes);
        actualizarCamposIEC(valorEnBytes);

    } catch (NumberFormatException e) {
        txtResultado.setText("Ingrese un valor numérico válido.");
    }
}

    private double convertirABytes(double valor, String unidad) {
        switch (unidad) {
            // SI (Base 10)
            case "Bytes": return valor;
            case "Kilobytes": return valor * 1_000;
            case "Megabytes": return valor * Math.pow(10, 6);
            case "Gigabytes": return valor * Math.pow(10, 9);
            case "Terabytes": return valor * Math.pow(10, 12);
            case "Petabytes": return valor * Math.pow(10, 15);
            case "Exabytes": return valor * Math.pow(10, 18);
            case "Zetabytes": return valor * Math.pow(10, 21);

            // IEC (Base 1024)
            case "Kibibytes": return valor * 1024;
            case "Mebibytes": return valor * Math.pow(1024, 2);
            case "Gibibytes": return valor * Math.pow(1024, 3);
            case "Tebibytes": return valor * Math.pow(1024, 4);
            case "Pebibytes": return valor * Math.pow(1024, 5);
            case "Exbibytes": return valor * Math.pow(1024, 6);
            case "Zebibytes": return valor * Math.pow(1024, 7);

            default: return valor;
        }
    }

    // Actualizar campos en el Sistema Internacional (SI) - Base 10
    private void actualizarCamposSI(double bytes) {
        txtBytes1.setText(String.valueOf(bytes));
        txtKilobytes.setText(String.valueOf(bytes / 1_000));
        txtMegabytes.setText(String.valueOf(bytes / 1_000_000));
        txtGigabytes.setText(String.valueOf(bytes / 1_000_000_000));
        txtTerabytes.setText(String.valueOf(bytes / Math.pow(10, 12)));
        txtPetabytes.setText(String.valueOf(bytes / Math.pow(10, 15)));
        txtExabytes.setText(String.valueOf(bytes / Math.pow(10, 18)));
        txtZetabytes.setText(String.valueOf(bytes / Math.pow(10, 21)));
    }

    // Actualizar campos en el Sistema IEC (Base 2 - 1024)
private void actualizarCamposIEC(double bytes) {
    txtBytes2.setText(String.valueOf(bytes));
    txtKibibytes.setText(String.valueOf(bytes / 1024));
    txtMebibytes.setText(String.valueOf(bytes / Math.pow(1024, 2)));
    txtGibibytes.setText(String.valueOf(bytes / Math.pow(1024, 3)));
    txtTebibytes.setText(String.valueOf(bytes / Math.pow(1024, 4)));
    txtPebibytes.setText(String.valueOf(bytes / Math.pow(1024, 5)));
    txtExbibytes.setText(String.valueOf(bytes / Math.pow(1024, 6)));
    txtZebibytes.setText(String.valueOf(bytes / Math.pow(1024, 7)));
}
}
