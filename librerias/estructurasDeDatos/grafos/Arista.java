package librerias.estructurasDeDatos.grafos;

/** Clase Arista: representa una arista de un grafo.<br> 
 *  
 *  @version Mayo 2018
 */
 
public class Arista implements Comparable<Arista> {
    
    // UNA Arista TIENE UN vertice origen y UN vertice destino:
    /*COMPLETAR*/
    // UNA Arista TIENE UN peso:
    /*COMPLETAR*/

    protected int origen, destino;
    protected double peso;
    
    /** Crea una arista (v, w) con peso p.
      *
      * @param v  Vertice origen
      * @param w  Vertice destino
      * @param p  Peso
     */
    public Arista(int v, int w, double p) {
        // COMPLETAR
        this.origen = v;
        this.destino = w;
        this.peso = p;
    }

    /** Devuelve el vertice origen de una arista.
      *
      * @return int vertice origen
     */
    public int getOrigen() {    
        // COMPLETAR
        return this.origen;
    }
    
    /** Devuelve el vertice destino de una arista.
      *
      * @return int vertice destino
     */
    public int getDestino() {  
        // COMPLETAR
        return this.destino;
    }
    
    /** Devuelve el peso de una arista.
      *
      * @return double Peso de la arista
     */
    public double getPeso() {
        // COMPLETAR
        return this.peso;
    }
    
    /** Devuelve un String que representa una arista
      * en formato (origen, destino, peso)
      *
      * @return  String que representa la arista
     */
    public String toString() {
        // COMPLETAR
        return "("+ origen +", " + destino + ", " + peso + ")";
    }

    public int compareTo(Arista v){
        if(peso > v.getPeso()){ return 1; }
        else if(peso < v.getPeso()){ return -1; }
        else{return 0;}
    }
}