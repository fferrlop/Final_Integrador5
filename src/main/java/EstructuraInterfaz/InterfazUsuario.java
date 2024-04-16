package EstructuraInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import AnalisisGenómico.ConteoGenes;
import AnalisisGenómico.CalculoCombinaciones;
import AnalisisNúmerico.PotenciasMaximos;
import AnalisisNúmerico.SumListNumeros;

public class InterfazUsuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejercicios");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        JPanel startPanel = new JPanel();

        JButton buttonNumerico = new JButton("Análisis Numérico");
        buttonNumerico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Numerico");
            }
        });
        startPanel.add(buttonNumerico);

        JButton buttonGenes = new JButton("Contador de Genes");
        buttonGenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Genes");
            }
        });
        startPanel.add(buttonGenes);

        JButton buttonInformacion = new JButton("Gestión de Información");
        buttonInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Informacion");
            }
        });
        startPanel.add(buttonInformacion);

        JPanel numericoPanel = new JPanel();
        JTextField numberField = new JTextField(20);
        JLabel sumResultLabel = new JLabel();
        JButton analyzeNumberButton = new JButton("Analizar");
        analyzeNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(numberField.getText());
                    if (number < 0) {
                        sumResultLabel.setText("");
                        return;
                    }
                    int sum = SumListNumeros.sumarNumerosNaturales(number);
                    sumResultLabel.setText("Suma de números naturales hasta " + number + ": " + sum);
                } catch (NumberFormatException ex) {
                    sumResultLabel.setText("");
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
                    int start = Integer.parseInt(rangeStartField.getText());
                    int end = Integer.parseInt(rangeEndField.getText());
                    if (start < 0 || end < 0) {
                        JOptionPane.showMessageDialog(frame, "El inicio o el fin del rango no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        List<Long> range = SumListNumeros.generarRango(start, end);
                        rangeResultArea.setText(range.toString());
                    }
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

        JTextField baseField = new JTextField(20);
        JTextField exponentField = new JTextField(20);
        JLabel powerResultLabel = new JLabel();
        JButton calculatePowerButton = new JButton("Calcular Potencia");
        calculatePowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int base = Integer.parseInt(baseField.getText());
                    int exponent = Integer.parseInt(exponentField.getText());
                    int result = PotenciasMaximos.power(base, exponent);
                    powerResultLabel.setText("Resultado: " + result);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        numericoPanel.add(new JLabel("Introduce la base:"));
        numericoPanel.add(baseField);
        numericoPanel.add(new JLabel("Introduce el exponente:"));
        numericoPanel.add(exponentField);
        numericoPanel.add(calculatePowerButton);
        numericoPanel.add(powerResultLabel);

        JTextField numbersField = new JTextField(20);
        JLabel maxResultLabel = new JLabel();
        JButton findMaxButton = new JButton("Encontrar Máximo");
        findMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Integer> numbers = Arrays.stream(numbersField.getText().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    int max = PotenciasMaximos.maximo(numbers);
                    maxResultLabel.setText("Máximo: " + max);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, introduce números válidos separados por comas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        numericoPanel.add(new JLabel("Introduce los números (separados por comas):"));
        numericoPanel.add(numbersField);
        numericoPanel.add(findMaxButton);
        numericoPanel.add(maxResultLabel);

//Aqui empieza Organización de Información

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

        JPanel informacionPanel = new JPanel();
        JButton buttonOrganizacion = new JButton("Organización de Documentos");
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setVisible(false);
        JButton saveButton = new JButton("Guardar");
        saveButton.setVisible(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/GestiónInformaciónCientifica/notas.txt"));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonOrganizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setVisible(true);
                saveButton.setVisible(true);
                buttonOrganizacion.setVisible(false);
            }
        });
        informacionPanel.add(buttonOrganizacion);
        informacionPanel.add(textArea);
        informacionPanel.add(saveButton);

        JButton sortButton = new JButton("Ordenar alfabéticamente");
        JTextArea sortedTextArea = new JTextArea(5, 20);
        sortedTextArea.setEditable(false);
        sortedTextArea.setVisible(false);
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("src/main/java/GestiónInformaciónCientifica/notasOrdenadas.txt"));
                    String line;
                    StringBuilder sortedText = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sortedText.append(line).append("\n");
                    }
                    reader.close();
                    sortedTextArea.setText(sortedText.toString());
                    sortedTextArea.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        informacionPanel.add(sortButton);
        informacionPanel.add(sortedTextArea);

        mainPanel.add(informacionPanel, "Informacion");

        mainPanel.add(startPanel, "Inicio");
        mainPanel.add(numericoPanel, "Numerico");
        mainPanel.add(genesPanel, "Genes");


        cardLayout.show(mainPanel, "Inicio");

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Inicio");
            }
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}