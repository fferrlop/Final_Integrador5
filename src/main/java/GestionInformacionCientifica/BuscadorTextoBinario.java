package GestionInformacionCientifica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BuscadorTextoBinario {

    public static int buscar(String texto, String palabra) {
        String[] palabras = texto.split("\\s+");
        Map<String, Integer> indices = new HashMap<>();
        for (int i = 0; i < palabras.length; i++) {
            indices.put(palabras[i], texto.indexOf(palabras[i]));
        }
        Arrays.sort(palabras);
        int index = binarySearch(palabras, palabra, 0, palabras.length - 1);
        return index != -1 ? indices.get(palabras[index]) : -1;
    }

    private static int binarySearch(String[] array, String key, int left, int right) {
        if (right < left) {
            return -1;
        }

        int median = (left + right) >>> 1;
        int comp = key.compareTo(array[median]);

        if (comp == 0) {
            return median;
        } else if (comp < 0) {
            return binarySearch(array, key, left, median - 1);
        } else {
            return binarySearch(array, key, median + 1, right);
        }
    }
}