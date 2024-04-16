package GestionInformacionCientifica;

public class Fecha implements Comparable<Fecha> {
    private int dia;
    private int mes;
    private int año;

    public Fecha(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.año = ano;
    }

    @Override
    public int compareTo(Fecha o) {
        if (año != o.año) {
            return año - o.año;
        } else if (mes != o.mes) {
            return mes - o.mes;
        } else {
            return dia - o.dia;
        }
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + año;
    }
}