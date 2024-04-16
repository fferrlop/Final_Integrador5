package OrganizacionQuicksort;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraficoBarras extends JPanel {
    private List<Barras> bars;

    public GraficoBarras(List<Barras> bars) {
        this.bars = bars;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bars != null) {
            int width = getWidth() / bars.size();
            for (int i = 0; i < bars.size(); i++) {
                Barras bar = bars.get(i);
                int x = i * width;
                int y = getHeight() - bar.getHeight();
                g.fillRect(x, y, width, bar.getHeight());

                // Dibujar el valor de la barra
                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial", Font.BOLD, 20)); // Ajustar la fuente
                g.drawString(String.valueOf(bar.getValue()), x + width / 2, y);
                g.setColor(Color.LIGHT_GRAY); // Cambiar el color de nuevo para las barras
            }
        }
    }

    public void setBars(List<Barras> bars) {
        this.bars = bars;
        repaint();
    }
}