/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conversor754;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author
 * Dairo Rodriguez Gomez - 1151358
 * Yoset Piedrahita Ramirez - 1152390
 */

public class Convertir extends JFrame {

    // Constructor del JFrame
    public Convertir() {
        setTitle("Conversor IEEE 754");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Usamos un JTabbedPane para separar las dos funciones
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para convertir de Decimal a IEEE 754
        JPanel decimalToIEEEPanel = createDecimalToIEEEPanel();
        tabbedPane.addTab("Decimal a IEEE754", decimalToIEEEPanel);

        // Panel para convertir de IEEE 754 a Decimal
        JPanel ieeeToDecimalPanel = createIEEEToDecimalPanel();
        tabbedPane.addTab("IEEE754 a Decimal", ieeeToDecimalPanel);

        add(tabbedPane);
    }

    /**
     * Crea el panel para convertir un número decimal a su representación IEEE 754.
     */
    private JPanel createDecimalToIEEEPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Etiqueta y campo de entrada para el número decimal
        JLabel lblDecimal = new JLabel("Ingrese número decimal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblDecimal, gbc);
        
        JTextField txtDecimal = new JTextField(20);
        gbc.gridx = 1;
        panel.add(txtDecimal, gbc);
        
        // Radio buttons para seleccionar precisión (32 bits o 64 bits)
        JLabel lblPrecision = new JLabel("Precisión:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPrecision, gbc);
        
        JRadioButton rb32 = new JRadioButton("32 bits (float)");
        JRadioButton rb64 = new JRadioButton("64 bits (double)");
        rb32.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rb32);
        group.add(rb64);
        
        JPanel precisionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        precisionPanel.add(rb32);
        precisionPanel.add(Box.createHorizontalStrut(10));
        precisionPanel.add(rb64);
        
        gbc.gridx = 1;
        panel.add(precisionPanel, gbc);
        
        // Botón para realizar la conversión
        JButton btnConvert = new JButton("Convertir");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(btnConvert, gbc);
        
        // Área de texto para mostrar resultados
        JTextArea txtResult = new JTextArea(6, 60);
        txtResult.setEditable(false);
        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtResult);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);
        
        // Acción del botón "Convertir"
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String decimalStr = txtDecimal.getText().trim();
                if (decimalStr.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Por favor, ingrese un número decimal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String binaryString, sign, exponent, mantissa;
                    if (rb32.isSelected()) {
                        // Convertir a precisión simple (32 bits)
                        float value = Float.parseFloat(decimalStr);
                        int bits = Float.floatToIntBits(value);
                        binaryString = String.format("%32s", Integer.toBinaryString(bits)).replace(' ', '0');
                        sign = binaryString.substring(0, 1);
                        exponent = binaryString.substring(1, 9);
                        mantissa = binaryString.substring(9);
                        txtResult.setText("Representación IEEE 754 (32 bits):\n" +
                                "Completo: " + binaryString + "\n" +
                                "Signo   : " + sign + "\n" +
                                "Exponente: " + exponent + "\n" +
                                "Mantisa : " + mantissa);
                    } else {
                        // Convertir a precisión doble (64 bits)
                        double value = Double.parseDouble(decimalStr);
                        long bits = Double.doubleToLongBits(value);
                        binaryString = String.format("%64s", Long.toBinaryString(bits)).replace(' ', '0');
                        sign = binaryString.substring(0, 1);
                        exponent = binaryString.substring(1, 12);
                        mantissa = binaryString.substring(12);
                        txtResult.setText("Representación IEEE 754 (64 bits):\n" +
                                "Completo: " + binaryString + "\n" +
                                "Signo   : " + sign + "\n" +
                                "Exponente: " + exponent + "\n" +
                                "Mantisa : " + mantissa);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Entrada inválida. Asegúrese de ingresar un número decimal correcto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        return panel;
    }

    /**
     * Crea el panel para convertir una representación IEEE 754 a número decimal.
     */
    private JPanel createIEEEToDecimalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Etiqueta y campo de entrada para la cadena IEEE 754
        JLabel lblIEEE = new JLabel("Ingrese cadena IEEE 754 (32 o 64 bits):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblIEEE, gbc);
        
        JTextField txtIEEE = new JTextField(30);
        gbc.gridx = 1;
        panel.add(txtIEEE, gbc);
        
        // Botón para realizar la conversión
        JButton btnConvert = new JButton("Convertir");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnConvert, gbc);
        
        // Área de texto para mostrar resultados
        JTextArea txtResult = new JTextArea(6, 60);
        txtResult.setEditable(false);
        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtResult);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);
        
        // Acción del botón "Convertir"
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ieeeStr = txtIEEE.getText().trim();
                // Validar longitud: debe ser 32 o 64 bits
                if (!(ieeeStr.length() == 32 || ieeeStr.length() == 64) || !ieeeStr.matches("[01]+")) {
                    JOptionPane.showMessageDialog(panel, "La cadena debe contener solo dígitos binarios y tener 32 o 64 bits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String sign, exponent, mantissa;
                    if (ieeeStr.length() == 32) {
                        // Interpretar cadena de 32 bits para precisión simple
                        int bits = (int) Long.parseLong(ieeeStr, 2);
                        float value = Float.intBitsToFloat(bits);
                        sign = ieeeStr.substring(0, 1);
                        exponent = ieeeStr.substring(1, 9);
                        mantissa = ieeeStr.substring(9);
                        txtResult.setText("Número en precisión simple (32 bits):\n" +
                                "Decimal : " + value + "\n" +
                                "Signo   : " + sign + "\n" +
                                "Exponente: " + exponent + "\n" +
                                "Mantisa : " + mantissa);
                    } else {
                        // Interpretar cadena de 64 bits para precisión doble
                        long bits = Long.parseLong(ieeeStr, 2);
                        double value = Double.longBitsToDouble(bits);
                        sign = ieeeStr.substring(0, 1);
                        exponent = ieeeStr.substring(1, 12);
                        mantissa = ieeeStr.substring(12);
                        txtResult.setText("Número en precisión doble (64 bits):\n" +
                                "Decimal : " + value + "\n" +
                                "Signo   : " + sign + "\n" +
                                "Exponente: " + exponent + "\n" +
                                "Mantisa : " + mantissa);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Error al procesar la cadena. Verifique que sea una representación binaria correcta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        return panel;
    }

    // Método principal que lanza la interfaz gráfica
    public static void main(String[] args) {
        // Asegurarse de que la creación de la GUI se realice en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Convertir frame = new Convertir();
                frame.setVisible(true);
            }
        });
    }
}
