package EstructuraInterfaz;

import java.awt.Color;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeParseException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import AnalisisGenomico.ConteoGenes;
import AnalisisGenomico.CalculoCombinaciones;
import AnalisisNumerico.PotenciasMaximos;
import AnalisisNumerico.SumListNumeros;
import GestionInformacionCientifica.BuscadorTextoBinario;
import GestionInformacionCientifica.BuscadorTextoLineal;
import GestionInformacionCientifica.OrganizacionDocumentos;
import OrganizacionQuicksort.Barras;
import OrganizacionQuicksort.GraficoBarras;
import OrganizacionQuicksort.Quicksort;

public class InterfazUsuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejercicios");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(new Color(0,0,0,0)); // Color transparente





        //Botones que aparecerán al abrir el programa
        JButton buttonNumerico = new JButton("Análisis Numérico");
        EsteticaInicio.configurarBoton(buttonNumerico);

        JButton buttonGenes = new JButton("Contador de Genes");
        EsteticaInicio.configurarBoton(buttonGenes);

        JButton buttonInformacionInicio = new JButton("Gestión de Información");
        EsteticaInicio.configurarBoton(buttonInformacionInicio);

        JButton buttonQuicksort = new JButton("Quicksort");
        EsteticaInicio.configurarBoton(buttonQuicksort);

        // Crear un JLayeredPane para superponer los componentes
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        layeredPane.setPreferredSize(new Dimension(800, 600)); // Ajustar al tamaño deseado

// Crear el JLabel para la imagen de fondo
        JLabel background = new JLabel(new ImageIcon("src/main/resources/Fondo.png"));
        background.setSize(background.getPreferredSize()); // Ajustar al tamaño de la imagen

// Crear el JPanel para los botones
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setOpaque(false); // Hacerlo transparente para que se vea la imagen de fondo
        buttonPanel.setSize(buttonPanel.getPreferredSize()); // Ajustar al tamaño necesario

// Añadir los botones al panel
        EsteticaInicio.configurarPanelConBotones(buttonPanel, buttonGenes, buttonNumerico, buttonInformacionInicio, buttonQuicksort);

// Añadir la imagen de fondo y el panel de botones al JLayeredPane
        layeredPane.add(background, BorderLayout.CENTER); // La imagen de fondo ocupa todo el espacio disponible
        layeredPane.setLayer(background, 1); // La imagen de fondo está en la capa 1 (detrás)
        layeredPane.add(buttonPanel, BorderLayout.CENTER); // Los botones se superponen a la imagen de fondo
        layeredPane.setLayer(buttonPanel, 2); // El panel de botones está en la capa 2 (delante)

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ajustar el tamaño del JLabel para que coincida con el tamaño del JLayeredPane
                Image originalImage = new ImageIcon("src/main/resources/Fondo.png").getImage();
                Image scaledImage = originalImage.getScaledInstance(layeredPane.getWidth(), layeredPane.getHeight(), Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setSize(layeredPane.getSize());
            }
        });

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
// Añadir el JLayeredPane al panel principal
        startPanel.add(layeredPane);

        mainPanel.add(startPanel, "Inicio");



        // Inicio de la sección de análisis de genes
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

        mainPanel.add(genesPanel, "Genes");
        // Fin de la sección de análisis de genes

        // Inicio de la sección de análisis numérico
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

        mainPanel.add(numericoPanel, "Numerico");
        // Fin de la sección de análisis numérico

        // Inicio de la sección de gestión de la información

        buttonInformacionInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Informacion");
            }
        });


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
                    OrganizacionDocumentos.quickSort(lines, 0, lines.size() - 1);
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

        JTextArea busquedaTextArea1 = new JTextArea(20, 18);
        busquedaTextArea1.setEditable(false);
        busquedaPanel.add(new JScrollPane(busquedaTextArea1));

        JTextArea busquedaTextArea2 = new JTextArea(20, 18);
        busquedaTextArea2.setEditable(false);
        busquedaPanel.add(new JScrollPane(busquedaTextArea2));

        JTextField searchField = new JTextField(20);
        busquedaPanel.add(new JLabel("Introduce la palabra a buscar:"));
        busquedaPanel.add(searchField);

        JLabel countLinearLabel = new JLabel();
        JLabel countBinaryLabel = new JLabel();

        busquedaPanel.add(countLinearLabel);

        JButton busquedaLinearButton = new JButton("Buscar");
        busquedaLinearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = searchField.getText();
                String text = busquedaTextArea1.getText();

                List<Integer> indices = BuscadorTextoLineal.buscar(text, word);
                countLinearLabel.setText("La palabra '" + word + "' se repite " + indices.size() + " veces.");

                Highlighter highlighter = busquedaTextArea1.getHighlighter();
                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
                highlighter.removeAllHighlights();

                int index = 0;
                while ((index = text.indexOf(word, index)) != -1) {
                    try {
                        highlighter.addHighlight(index, index + word.length(), painter);
                        index += word.length();
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        busquedaPanel.add(busquedaLinearButton);


        try {
            List<String> lines1 = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/Texto.txt"));
            StringBuilder text1 = new StringBuilder();
            for (String line : lines1) {
                text1.append(line).append("\n");
            }
            busquedaTextArea1.setText(text1.toString());

            List<String> lines2 = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/texto2.txt"));
            StringBuilder text2 = new StringBuilder();
            for (String line : lines2) {
                text2.append(line).append("\n");
            }
            busquedaTextArea2.setText(text2.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //AQUI EMPIEza
        JTextField busquedaBinarioButton = new JTextField(20);
        busquedaPanel.add(new JLabel("Introduce la palabra a buscar (binario):"));
        busquedaPanel.add(busquedaBinarioButton);

        JButton busquedaBinariaButton = new JButton("Buscar (binario)");
        busquedaBinariaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = busquedaBinarioButton.getText();
                String text = busquedaTextArea2.getText();

                Highlighter highlighter = busquedaTextArea2.getHighlighter();
                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                highlighter.removeAllHighlights();

                int originalIndex = 0;
                int index = 0;
                while ((index = BuscadorTextoBinario.buscar(text, word)) != -1) {
                    try {
                        highlighter.addHighlight(originalIndex + index, originalIndex + index + word.length(), painter);
                        originalIndex += index + word.length();
                        text = text.substring(index + word.length());
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }

                if (highlighter.getHighlights().length > 0) {
                    countBinaryLabel.setText("La palabra '" + word + "' se encontró " + highlighter.getHighlights().length + " veces.");
                } else {
                    countBinaryLabel.setText("La palabra '" + word + "' no se encontró.");
                }
            }
        });
        busquedaPanel.add(busquedaBinariaButton);
        busquedaPanel.add(countBinaryLabel);


        JPanel fechasPanel = new JPanel();

        JButton gestionFechasButton = new JButton("Gestión de Fechas");
        gestionFechasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Fechas");
            }
        });
        informacionPanel.add(gestionFechasButton);

        JTextField fechaField = new JTextField(20);
        fechasPanel.add(new JLabel("Introduce la fecha (dd/mm/aaaa):"));
        fechasPanel.add(fechaField);

        JButton guardarFechaButton = new JButton("Guardar Fecha");
        guardarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = fechaField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    LocalDate.parse(fecha, formatter);
                    try {
                        FileWriter writer = new FileWriter("src/main/java/ArchivosTexto/Fechas.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        bufferedWriter.write(fecha);
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "La fecha no sigue el formato dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fechasPanel.add(guardarFechaButton);
        informacionPanel.add(gestionFechasButton);

        mainPanel.add(fechasPanel, "Fechas");

        JTextArea fechasArea = new JTextArea(10, 15);
        fechasArea.setEditable(false);
        JScrollPane fechasScrollPane = new JScrollPane(fechasArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fechasPanel.add(fechasScrollPane);

        int delay = 1000; //milisegundos
        new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    List<String> fechas = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/Fechas.txt"));
                    String fechasStr = String.join("\n", fechas);
                    fechasArea.setText(fechasStr);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        JTextField fechaBorrarField = new JTextField(20);
        fechasPanel.add(new JLabel("Introduce la fecha a borrar (dd/mm/aaaa):"));
        fechasPanel.add(fechaBorrarField);

        JButton borrarFechaButton = new JButton("Borrar Fecha");
        borrarFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaBorrar = fechaBorrarField.getText();
                try {
                    List<String> fechas = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/Fechas.txt"));
                    fechas = fechas.stream()
                            .filter(fecha -> !fecha.equals(fechaBorrar))
                            .collect(Collectors.toList());
                    Files.write(Paths.get("src/main/java/ArchivosTexto/Fechas.txt"), fechas);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fechasPanel.add(borrarFechaButton);

        JButton ordenarFechasButton = new JButton("Ordenar Fechas");
        ordenarFechasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<String> fechas = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/Fechas.txt"));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    List<LocalDate> fechasOrdenadas = fechas.stream()
                            .map(fecha -> LocalDate.parse(fecha, formatter))
                            .sorted(Comparator.reverseOrder())
                            .collect(Collectors.toList());
                    List<String> fechasOrdenadasStr = fechasOrdenadas.stream()
                            .map(fecha -> fecha.format(formatter))
                            .collect(Collectors.toList());
                    Files.write(Paths.get("src/main/java/ArchivosTexto/FechasOrdenadas.txt"), fechasOrdenadasStr);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fechasPanel.add(ordenarFechasButton);

        JTextArea fechasOrdenadasArea = new JTextArea(10, 15);
        fechasOrdenadasArea.setEditable(false);
        JScrollPane fechasOrdenadasScrollPane = new JScrollPane(fechasOrdenadasArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fechasPanel.add(fechasOrdenadasScrollPane);

        int delay2 = 1000; //milliseconds
        new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    List<String> fechasOrdenadas = Files.readAllLines(Paths.get("src/main/java/ArchivosTexto/FechasOrdenadas.txt"));
                    String fechasOrdenadasStr = String.join("\n", fechasOrdenadas);
                    fechasOrdenadasArea.setText(fechasOrdenadasStr);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();






    // Fin de la sección de gestión de la información

    // Algoritmo de quicksort



        GraficoBarras barrasChart = new GraficoBarras(null);
        frame.add(barrasChart, BorderLayout.CENTER);

        // 10, 20, 10, 4, 20, 1, 9, 2, 1, 3, 7, 17, 6, 15, 3

        buttonQuicksort.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Introduce los números que quieres ordenar, separados por comas");
            String[] numberStrings = input.split(",");
            Integer[] data = new Integer[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                try {
                    data[i] = Integer.parseInt(numberStrings[i].trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, introduce solo números separados por comas", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Quicksort quicksort = new Quicksort();
            JFrame barFrame = new JFrame("Gráfico de barras");
            barFrame.setSize(800, 600);
            GraficoBarras barChart = new GraficoBarras(null);
            barFrame.add(barChart);
            barFrame.setVisible(true);

            List<List<Barras>> steps = new ArrayList<>();
            quicksort.sort(data, bars -> {
                steps.add(new ArrayList<>(bars));
            });

            new Thread(() -> {
                for (List<Barras> step : steps) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    SwingUtilities.invokeLater(() -> {
                        barChart.setBars(step);
                        barChart.repaint();
                        barFrame.revalidate();
                    });
                }
            }).start();
        });




    //Fin de quicksort

        mainPanel.add(informacionPanel, "Informacion");
        mainPanel.add(organizacionPanel, "Organizacion");
        mainPanel.add(busquedaPanel, "Busqueda");
        buttonGenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Genes");
            }
        });

        buttonNumerico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Numerico");
            }
        });

        buttonInformacionInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Informacion");
            }
        });

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Inicio");
            }
        });

        //personalizar "Volver"
        backButton.setText("Back"); // Cambia texto
        backButton.setForeground(Color.BLUE); // Color
        backButton.setFont(new Font("Arial", Font.BOLD, 18)); // modelo

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        Color customColor = new Color(55, 68, 78);

        mainPanel.setBackground(customColor);
        informacionPanel.setBackground(customColor);
        organizacionPanel.setBackground(customColor);
        busquedaPanel.setBackground(customColor);
        fechasPanel.setBackground(customColor);
        genesPanel.setBackground(customColor);
        numericoPanel.setBackground(customColor);

    }
}