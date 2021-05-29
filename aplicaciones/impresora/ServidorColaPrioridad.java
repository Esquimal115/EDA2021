package aplicaciones.impresora;

import librerias.estructurasDeDatos.jerarquicos.MonticuloBinarioR0;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class ServidorColaPrioridad implements ServidorDeImpresion {
    private ColaPrioridad<Trabajo> cP = new MonticuloBinarioR0();

    public ServidorColaPrioridad() {
    }

    public void insertar(Trabajo t) {
        this.cP.insertar(t);
    }

    public boolean hayTrabajos() {
        return !this.cP.esVacia();
    }

    public Trabajo getTrabajo() {
        return !this.cP.esVacia() ? (Trabajo)this.cP.recuperarMin() : null;
    }

    public int imprimirTrabajo() {
        if (!this.cP.esVacia()) {
            Trabajo t = (Trabajo)this.cP.eliminarMin();
            int tiempoImpresion = 60 * t.getNumPaginas() / 30;
            return tiempoImpresion;
        } else {
            return -1;
        }
    }
}