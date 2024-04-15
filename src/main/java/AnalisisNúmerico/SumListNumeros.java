package AnalisisNúmerico;

public class SumListNumeros {
    public static int sumarNumerosNaturales(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + sumarNumerosNaturales(n - 1);
        }
    }
}