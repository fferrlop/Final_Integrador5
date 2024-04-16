package AnalisisGenomico;

import java.util.ArrayList;
import java.util.List;

public class CalculoCombinaciones {
    public static List<String> calcularCombinaciones(String dna) throws Exception {
        if (!dna.matches("[AGTC]*")) {
            throw new Exception("El patrón de ADN contiene un carácter no permitido");
        }

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            throw new Exception("El patrón de ADN debe contener 'ATG'");
        }

        List<String> combinaciones = new ArrayList<>();
        for (int i = startIndex; i <= dna.length() - 3; i += 3) {
            String sub = dna.substring(i, i + 3);
            combinaciones.add(sub);
        }
        return combinaciones;
    }
}