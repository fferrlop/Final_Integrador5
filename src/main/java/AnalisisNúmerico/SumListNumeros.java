package AnalisisNúmerico;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SumListNumeros {
    public static int sumarNumerosNaturales(int n) {
        if (n < 0) {
            JOptionPane.showMessageDialog(null, "El número no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        if (n <= 0) {
            return 0;
        } else {
            return n + sumarNumerosNaturales(n - 1);
        }
    }

    public static List<Long> fibonacci(int n) {
        if (n < 0) {
            JOptionPane.showMessageDialog(null, "El número no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }

        List<Long> sequence = new ArrayList<>();
        sequence.add(0L);
        sequence.add(1L);

        for (int i = 2; i < n; i++) {
            long nextNumber = sequence.get(i - 1) + sequence.get(i - 2);
            sequence.add(nextNumber);
        }

        return sequence;
    }

    public static List<Long> generarRango(int n) {
        return fibonacci(n);
    }
}