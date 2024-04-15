package AnalisisNúmerico;

import java.util.ArrayList;
import java.util.List;

public class SumListNumeros {
    public static int sumarNumerosNaturales(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + sumarNumerosNaturales(n - 1);
        }
    }

    public static List<Integer> fibonacci(int n) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(0);
        sequence.add(1);

        for (int i = 2; i < n; i++) {
            int nextNumber = sequence.get(i - 1) + sequence.get(i - 2);
            sequence.add(nextNumber);
        }

        return sequence;
    }

    public static List<Integer> generarRango(int n) {
        return fibonacci(n);
    }
}