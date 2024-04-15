package EstructuraInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        genesPanel.add(new JLabel("Aquí va el análisis de genes"));


        JPanel numericoPanel = new JPanel();
        numericoPanel.add(new JLabel("Aquí va el análisis numérico"));


        mainPanel.add(startPanel, "Inicio");
        mainPanel.add(genesPanel, "Genes");
        mainPanel.add(numericoPanel, "Numerico");


        cardLayout.show(mainPanel, "Inicio");

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}