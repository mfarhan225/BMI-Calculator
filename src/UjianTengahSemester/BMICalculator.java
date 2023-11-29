/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UjianTengahSemester;

/**
 *
 * @author MUHAMMAD FARHAN
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BMICalculator extends JFrame {

    private JTextField weightField, heightField;
    private JComboBox<String> unitComboBox;
    private JLabel resultLabel;

    public BMICalculator() {
        setTitle("BMI KalKulator");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel unitLabel = new JLabel("Satuan Unit:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(unitLabel, gbc);

        String[] units = {"kg", "lbs"};
        unitComboBox = new JComboBox<>(units);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(unitComboBox, gbc);
        
        JLabel weightLabel = new JLabel("Berat Badan:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(weightLabel, gbc);

        weightField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        panel.add(weightField, gbc);

        JLabel heightLabel = new JLabel("Tinggi Badan:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(heightLabel, gbc);

        heightField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        panel.add(heightField, gbc);

        JButton calculateButton = new JButton("Mulai Hitung BMI");
        calculateButton.addActionListener((ActionEvent e) -> {
            calculateBMI();
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3; // Adjust gridwidth to cover both text fields
        panel.add(calculateButton, gbc);

        resultLabel = new JLabel("BMI Anda Adalah: ");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3; // Adjust gridwidth to cover both text fields
        panel.add(resultLabel, gbc);
    }

    private void calculateBMI() {
        try {
            String unit = (String) unitComboBox.getSelectedItem();
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            double bmi;
            switch (unit) {
                case "kg" -> bmi = (100 * 100 * weight) / (height * height);
                case "lbs" -> bmi = 703 * (weight / (height * height));
                default -> {
                    showError("Silakan pilih satuan berat Anda (kg/lbs)");
                    return;
                }
            }

            String bmiCategory = getBMICategory(bmi);
            resultLabel.setText("<html><body><b>BMI Anda Adalah:</b> " + String.format("%.2f", bmi) + "<br><b>Kategori:</b> " + bmiCategory + "</body></html>");
        } catch (NumberFormatException ex) {
            showError("Silakan masukkan nilai numerik yang valid untuk berat dan tinggi badan Anda");
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Kekurangan Berat Badan";
        } else if (bmi < 24.9) {
            return "Berat Badan Normal";
        } else if (bmi < 29.9) {
            return "Kelebihan Berat Badan";
        } else {
            return "Obesitas";
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new BMICalculator();
    }
}
