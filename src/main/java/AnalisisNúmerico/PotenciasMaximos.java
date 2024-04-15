package AnalisisNúmerico;

import java.util.List;

public class PotenciasMaximos {
    public static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return base * power(base, exponent - 1);
    }

    public static int maximo(List<Integer> numeros) {
        int maximo = numeros.get(0);
        for (int numero : numeros) {
            if (numero > maximo) {
                maximo = numero;
            }
        }
        return maximo;
    }
}