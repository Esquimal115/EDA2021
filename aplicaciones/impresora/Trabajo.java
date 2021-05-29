package aplicaciones.impresora;

public class Trabajo implements Comparable<Trabajo> {
    private String titulo;
    private int numPaginas;
    private int entraAlServidor;

    public Trabajo(String t, int n, int e) {
        this.titulo = t;
        this.numPaginas = n;
        this.entraAlServidor = e;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public int getNumPaginas() {
        return this.numPaginas;
    }

    public int getEntraAlServidor() {
        return this.entraAlServidor;
    }

    public int compareTo(Trabajo otro) {
        if (this.numPaginas < otro.getNumPaginas()) {
            return -1;
        } else {
            return this.numPaginas > otro.getNumPaginas() ? 1 : 0;
        }
    }

    public String toString() {
        return this.titulo + " (" + this.numPaginas + " pag.) Entra al servidor: " + this.entraAlServidor;
    }
}