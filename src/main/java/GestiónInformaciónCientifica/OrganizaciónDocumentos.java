package GestiónInformaciónCientifica;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class OrganizaciónDocumentos {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/GestiónInformaciónCientifica/notas.txt"));
            quickSort(lines, 0, lines.size() - 1);
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/GestiónInformaciónCientifica/notasOrdenadas.txt"));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quickSort(List<String> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(List<String> list, int low, int high) {
        String pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return (i + 1);
    }
}