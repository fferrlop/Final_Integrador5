package AnalisisNúmerico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SumListNumeros {
    public static int sumarNumerosNaturales(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + sumarNumerosNaturales(n - 1);
        }

    }

    public static List<Integer> generarRango(int start, int end) {
        List<Integer> range = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            range.add(sumarNumerosNaturales(i));
        }
        return range;
    }
}
