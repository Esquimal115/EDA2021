package librerias.estructurasDeDatos.lineales;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class LEGListaConPIOrdenada<E> extends LEGListaConPI<E> implements Comparable<NodoLEG>{

    protected NodoLEG<E> nuevo = new NodoLEG(null);

    public LEGListaConPIOrdenada(){
        super();
    }

    @Override
    public void insertar (E e){

        this.nuevo = new NodoLEG(e);
        NodoLEG<E> aux;

        this.talla++;
        inicio();
        this.nuevo.siguiente = this.ant.siguiente; //Añadimos nuevo en la primera posición

        // Si no hay nada inseratado, lo añadimos
        if (this.esVacia()){

            this.ant.siguiente = nuevo;
            this.ant = nuevo;
            this.ult = nuevo;


        // si el numero que le pasamos es menor que el que compara de la lista. Este else if, lo podemos omitir.
        // el elemento nuevo siempre se encola al principio.
        }else if(this.compareTo(this.ant.siguiente) == -1) {

            this.pri.siguiente = nuevo;


        }else{

            // mientras el último puntero no sobrepase null y el numero a comparar no sea mayor que nuevo de siguiente siguiente
            while (this.ant.siguiente != null && this.compareTo(this.ant.siguiente) == 1){
                this.ant = this.ant.siguiente;
            }

            // Intercambia los nodos si debemos de insertar el nuevo nodo entre 2 existentes

            aux = ant.siguiente;
            ant.siguiente = nuevo;
            nuevo.siguiente = aux;

            // Esta condicion comprueba si se ha insertado un elemento en la ultima posicion, si ha ocurrido tendremos que mover
            // el puntero una posición a la derecha, para que el siguiente nodo sea null y al que apunte sea el último.
            if (this.ult.siguiente != null){
                this.ult = this.ult.siguiente;
            }
        }
    }

    // Funcion compareTo para 2 nodos
    @Override
    public int compareTo(NodoLEG o) {
        if(Integer.parseInt(nuevo.dato.toString()) < Integer.parseInt(o.dato.toString())){
            return -1;

        }else if (Integer.parseInt(nuevo.dato.toString()) > Integer.parseInt(o.dato.toString())){
            return 1;

        }else{
            return 0;
        }
    }
}


