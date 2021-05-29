package aplicaciones.primitiva;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/** 
 * ApuestaPrimitiva: representa una apuesta aleatoria de La Primitiva, 
 * o combinacion de 6 numeros distintos entre el 1 y el 49 elegidos  
 * de forma aleatoria.
 * 
 * @version Febrero 2019
 */

public class ApuestaPrimitiva {
    
    // Una Primitiva TIENE UNA Lista con PI que almacena
    // una combinacion de 6 numeros de La Primitiva
    private ListaConPI<NumeroPrimitiva> combinacion;
    private LEGListaConPIOrdenada <NumeroPrimitiva> combinacionOrdenada;
    
    /**
     * Crea una ApuestaPrimitiva, o una combinacion de  
     * seis numeros aleatorios con valores distintos y   
     * en el intervalo [1, 49].
     * 
     * @param ordenada Un boolean que indica si la combinacion,  
     *                 sus 6 numeros, debe estar ordenada Asc.
     *                 (true) o no (false).           
     */
    public ApuestaPrimitiva(boolean ordenada) {

        combinacionOrdenada = new LEGListaConPIOrdenada<>();
        combinacion = new LEGListaConPI<>();

        boolean encontrado;

        for (int i = 0; i<250; i++){
            NumeroPrimitiva num = new NumeroPrimitiva();

            //Si queremos que la apuesta esté ordenada
            if(ordenada){
                this.combinacion = combinacionOrdenada;
                encontrado = false;
                if(combinacionOrdenada.esVacia()){
                    combinacionOrdenada.insertar(num);
                }else if (combinacionOrdenada.talla() < 6){
                    combinacionOrdenada.inicio();
                    while (!combinacionOrdenada.esFin() ){
                        if(combinacionOrdenada.recuperar().equals(num)){
                            encontrado = true;
                        }
                        combinacionOrdenada.siguiente();
                    }
                    if(!encontrado){
                        combinacionOrdenada.insertar(num);
                    }
                }else{
                    break;
                }

                // Si queremos que la apuesta salga en orden aleatorio
            }else{
                encontrado = false;
                if(combinacion.esVacia()){
                    combinacion.insertar(num);
                }else if (this.combinacion.talla() < 6){
                    this.combinacion.inicio();
                    while (!this.combinacion.esFin() ){
                        if(this.combinacion.recuperar().equals(num)){
                            encontrado = true;
                        }
                        this.combinacion.siguiente();
                    }
                    if(!encontrado){
                        this.combinacion.insertar(num);
                    }
                }else{
                    break;
                }

            }
        }
    }
    
    /**
     * Devuelve la posicion del numero n en una ApuestaPrimitiva, 
     * o -1 si n no forma parte de la combinacion. 
     * IMPORTANTE: se asume que el primer elemento de una combinacion 
     * esta en su posicion 0 y el ultimo en la 5.
     * 
     * @param n un int
     * @return  la posicion de n en una combinacion, un valor int
     *          en el intervalo [0, 5] si n esta en la combinacion      
     *          o -1 en caso contrario
     */
    protected int posicionDe(NumeroPrimitiva n) {

        int cont = 0;
        this.combinacion.inicio();
        for (int i = 0; i< 6; i++){
            if (this.combinacion.recuperar().equals(n)){
                return cont;
            }
            this.combinacion.siguiente();
            cont++;
        }
        return -1;
        
    }
    
    /**
     * Devuelve el String que representa una ApuestaPrimitiva en el formato
     * texto que muestra el siguiente ejemplo: "16, 25, 28, 49, 9, 20"
     * 
     * @return el String con la ApuestaPrimitiva en el formato texto dado. 
     */
    public String toString() {

        StringBuilder ans = new StringBuilder();
        ans.append("");
        this.combinacion.inicio();
        for (int i = 0; i < 5; i++){
            ans.append(this.combinacion.recuperar().toString() + ", ");
            this.combinacion.siguiente();
        }
        ans.append(this.combinacion.recuperar().toString() + "");
        return ans.toString();
    }
        
}

