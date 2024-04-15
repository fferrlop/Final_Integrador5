package GestiónInformaciónCientifica;

import java.io.*;
import java.util.*;

public class OrganizaciónDocumentos {

    public static void quickSort(String[] arr, int left, int right) {
        int l, r;
        String s;
        while (right > left) {
            l = left;
            r = right;
            s = arr[left];
            while (l < r) {
                while (arr[r].compareTo(s) > 0 && l < r) {
                    r--;
                }
                if (l < r) {
                    arr[l++] = arr[r];
                }
                while (arr[l].compareTo(s) <= 0 && l < r) {
                    l++;
                }
                if (l < r) {
                    arr[r--] = arr[l];
                }
            }
            arr[l] = s;
            quickSort(arr, left, l - 1);
            left = l + 1;
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("notas.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            String[] array = lines.toArray(new String[0]);
            quickSort(array, 0, array.length - 1);

            BufferedWriter writer = new BufferedWriter(new FileWriter("notas_ordenadas.txt"));
            for (String sortedLine : array) {
                writer.write(sortedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}