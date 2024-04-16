package EstructuraInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import AnalisisGenómico.ConteoGenes;
import AnalisisGenómico.CalculoCombinaciones;
import AnalisisNúmerico.PotenciasMaximos;
import AnalisisNúmerico.SumListNumeros;
import GestiónInformaciónCientifica.OrganizaciónDocumentos;

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

        JButton buttonInformacionInicio = new JButton("Gestión de Información");
        buttonInformacionInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Informacion");
            }
        });
        startPanel.add(buttonInformacionInicio);

        JPanel informacionPanel = new JPanel();

        JButton buttonOrganizacion = new JButton("Organización de Documentos");
        buttonOrganizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Organizacion");
            }
        });
        informacionPanel.add(buttonOrganizacion);

        JButton searchButton = new JButton("Buscar en texto");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Busqueda");
            }
        });
        informacionPanel.add(searchButton);

        JPanel organizacionPanel = new JPanel();
        JTextArea textArea = new JTextArea(5, 20);
        organizacionPanel.add(new JScrollPane(textArea));

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("src/main/java/ArchivosTexto/notas.txt");
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JTextArea sortedTextArea = new JTextArea(10, 30);
        sortedTextArea.setEditable(false);
        organizacionPanel.add(new JScrollPane(sortedTextArea));
        JButton sortButton = new JButton("Ordenar alfabéticamente");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/notas.txt"));
                    OrganizaciónDocumentos.quickSort(lines, 0, lines.size() - 1);
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/ArchivosTexto/notasOrdenadas.txt"));
                    StringBuilder sortedText = new StringBuilder();
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                        sortedText.append(line).append("\n");
                    }
                    writer.close();
                    sortedTextArea.setText(sortedText.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        organizacionPanel.add(saveButton);
        organizacionPanel.add(sortButton);


        JPanel busquedaPanel = new JPanel();
        JTextArea busquedaTextArea = new JTextArea(20, 18);
        busquedaTextArea.setEditable(false);
        busquedaPanel.add(new JScrollPane(busquedaTextArea));

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/Texto.txt"));
            StringBuilder text = new StringBuilder();
            for (String line : lines) {
                text.append(line).append("\n");
            }
            busquedaTextArea.setText(text.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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

        mainPanel.add(startPanel, "Inicio");
        mainPanel.add(numericoPanel, "Numerico");
        mainPanel.add(genesPanel, "Genes");
        mainPanel.add(informacionPanel, "Informacion");
        mainPanel.add(organizacionPanel, "Organizacion");
        mainPanel.add(busquedaPanel, "Busqueda");

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