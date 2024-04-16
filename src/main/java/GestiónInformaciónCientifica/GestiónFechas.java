package GestiónInformaciónCientifica;

import java.util.ArrayList;
import java.util.Collections;

public class GestiónFechas {
    private ArrayList<Fecha> fechas;

    public GestiónFechas() {
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