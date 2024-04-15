package EstructuraInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import AnalisisGenómico.ConteoGenes;
import AnalisisGenómico.CalculoCombinaciones;
import AnalisisNúmerico.SumListNumeros;

public class InterfazUsuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejercicios");
        frame.setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        JPanel startPanel = new JPanel();
        JButton buttonGenes = new JButton("Contador de Genes");
        buttonGenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Genes");
            }
        });
        startPanel.add(buttonGenes);

        JButton buttonNumerico = new JButton("Análisis Numérico");
        buttonNumerico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Numerico");
            }
        });
        startPanel.add(buttonNumerico);

        JPanel genesPanel = new JPanel();
        JTextField dnaField = new JTextField(20);
        JLabel genesResultLabel = new JLabel();
        JLabel combinationsResultLabel = new JLabel();
        JButton analyzeButton = new JButton("Analizar");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dna = dnaField.getText();
                    int numGenes = ConteoGenes.contarGenes(dna);
                    List<String> combinaciones = CalculoCombinaciones.calcularCombinaciones(dna);
                    genesResultLabel.setText("Número de genes: " + numGenes);
                    combinationsResultLabel.setText("Combinaciones: " + combinaciones);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        genesPanel.add(new JLabel("Introduce el patrón de ADN:"));
        genesPanel.add(dnaField);
        genesPanel.add(analyzeButton);
        genesPanel.add(genesResultLabel);
        genesPanel.add(combinationsResultLabel);

        JPanel numericoPanel = new JPanel();
        JTextField numberField = new JTextField(20);
        JLabel sumResultLabel = new JLabel();
        JButton analyzeNumberButton = new JButton("Analizar");
        analyzeNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(numberField.getText());
                    int sum = SumListNumeros.sumarNumerosNaturales(number);
                    sumResultLabel.setText("Suma de números naturales hasta " + number + ": " + sum);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        numericoPanel.add(new JLabel("Introduce un número:"));
        numericoPanel.add(numberField);
        numericoPanel.add(analyzeNumberButton);
        numericoPanel.add(sumResultLabel);

        JTextField rangeStartField = new JTextField(20);
        JTextField rangeEndField = new JTextField(20);
        JTextArea rangeResultArea = new JTextArea(5, 20);
        rangeResultArea.setEditable(false);
        JButton generateRangeButton = new JButton("Generar Rango");
        generateRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int end = Integer.parseInt(rangeEndField.getText());
                    List<Long> range = SumListNumeros.generarRango(end);
                    rangeResultArea.setText(range.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        analyzeNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(numberField.getText());
                    int sum = SumListNumeros.sumarNumerosNaturales(number);
                    sumResultLabel.setText("Suma de números naturales hasta " + number + ": " + sum);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        numericoPanel.add(new JLabel("Introduce el inicio del rango:"));
        numericoPanel.add(rangeStartField);
        numericoPanel.add(new JLabel("Introduce el fin del rango:"));
        numericoPanel.add(rangeEndField);
        numericoPanel.add(generateRangeButton);
        numericoPanel.add(new JLabel("Números en el rango:"));
        numericoPanel.add(rangeResultArea);

        mainPanel.add(startPanel, "Inicio");
        mainPanel.add(genesPanel, "Genes");
        mainPanel.add(numericoPanel, "Numerico");

        cardLayout.show(mainPanel, "Inicio");

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}