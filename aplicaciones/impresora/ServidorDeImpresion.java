package aplicaciones.impresora;

public interface ServidorDeImpresion {
    int PAGINAS_POR_MINUTO = 30;

    void insertar(Trabajo var1);

    boolean hayTrabajos();

    Trabajo getTrabajo();

    int imprimirTrabajo();
}