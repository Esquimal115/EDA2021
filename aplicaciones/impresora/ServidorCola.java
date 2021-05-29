package aplicaciones.impresora;

import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.Cola;

public class ServidorCola implements ServidorDeImpresion {
    private Cola<Trabajo> c = new ArrayCola();

    public ServidorCola() {
    }

    public void insertar(Trabajo t) {
        this.c.encolar(t);
    }

    public boolean hayTrabajos() {
        return !this.c.esVacia();
    }

    public Trabajo getTrabajo() {
        return (Trabajo)this.c.primero();
    }

    public int imprimirTrabajo() {
        Trabajo t = (Trabajo)this.c.desencolar();
        int tiempoImpresion = (int)Math.round(60.0D * (double)t.getNumPaginas() / 30.0D);
        return tiempoImpresion;
    }
}