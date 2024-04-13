package EstructuraInterfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import AnalisisGenómico.ConteoGenes;

public class InterfazUsuario {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejercicios");
        frame.setSize(800, 600);

        JButton button = new JButton("Contador de Genes");
        button.setPreferredSize(new Dimension(150, 50));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dna = JOptionPane.showInputDialog("Introduce el patrón de ADN");
                    int numGenes = ConteoGenes.contarGenes(dna); // Updated this line
                    JOptionPane.showMessageDialog(null, "Número de genes: " + numGenes);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(button);
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}