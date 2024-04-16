package GestionInformacionCientifica;

import java.util.ArrayList;
import java.util.Collections;

public class GestionFechas {
    private ArrayList<Fecha> fechas;

    public GestionFechas() {
        fechas = new ArrayList<>();
    }

    public void agregarFecha(Fecha fecha) {
        fechas.add(fecha);
    }

    public void listarFechas() {
        Collections.sort(fechas);
        for (Fecha fecha : fechas) {
            System.out.println(fecha);
        }
    }
}