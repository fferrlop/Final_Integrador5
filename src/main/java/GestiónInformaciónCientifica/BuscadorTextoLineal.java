package GestiónInformaciónCientifica;

import java.util.ArrayList;
import java.util.List;

public class BuscadorTextoLineal {

    public static List<Integer> buscar(String texto, String palabra) {
        List<Integer> indices = new ArrayList<>();
        String[] palabras = texto.split("\\s+");

        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].equals(palabra)) {
                indices.add(i);
            }
        }

        return indices;
    }
}