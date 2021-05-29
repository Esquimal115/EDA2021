//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package librerias.estructurasDeDatos.lineales;

class NodoLEG<E> {
    protected E dato;
    protected NodoLEG<E> siguiente;

    NodoLEG(E e, NodoLEG<E> s) {
        this.dato = e;
        this.siguiente = s;
    }

    NodoLEG(E e) {
        this(e, (NodoLEG)null);
    }
}