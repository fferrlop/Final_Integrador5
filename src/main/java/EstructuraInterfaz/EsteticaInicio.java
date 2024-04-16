package EstructuraInterfaz;

import javax.swing.*;
import java.awt.*;

public class EsteticaInicio {

    public static void configurarBoton(JButton boton) {
        //Tamaño
        boton.setPreferredSize(new Dimension(200, 50));

        //fuente y color
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);

        //Fondo color
        boton.setBackground(Color.BLUE);

        //posición
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static void configurarPanelConBotones(JPanel panel, JButton... botones) {
        // Panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // mover botones hacia abajo
        panel.add(Box.createRigidArea(new Dimension(0, 250)));

        // botones en el panel
        for (JButton boton : botones) {
            panel.add(boton);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Añadir espacio fijo entre los botones
        }

        // eliminar el espacio al final
        panel.remove(panel.getComponentCount() - 1);

        panel.add(Box.createVerticalGlue());
    }
}