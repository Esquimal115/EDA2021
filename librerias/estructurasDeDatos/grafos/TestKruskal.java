package librerias.estructurasDeDatos.grafos;

import java.util.Arrays;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinarioR0;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;

public class TestKruskal {
    private static final int NUMV = 7;

    public TestKruskal() {
    }

    public static void main(String[] args) {
        if (validar("Validando la clase Arista para Kruskal", testArista())) {
            if (validar("Validando Kruskal para un Grafo NO Conexo", testKruskalNoConexo())) {
                if (validar("Validando Kruskal para un Grafo Conexo", testKruskal())) {
                    ;
                }
            }
        }
    }

    public static boolean validar(String test, String res) {
        System.out.print(test + "... ");
        if (res == null) {
            System.out.println("OK!\n");
            return true;
        } else {
            System.out.println("ERROR");
            System.out.println("* " + res);
            return false;
        }
    }

    public static String testArista() {
        double sumaPesos = 0.0D;
        ColaPrioridad<Arista> cP = new MonticuloBinarioR0();
        Arista[] datos = new Arista[]{new Arista(0, 2, 12.5D), new Arista(1, 3, 6.5D), new Arista(2, 3, 4.3D), new Arista(0, 3, 14.3D), new Arista(0, 1, 6.2D)};
        String a1 = datos[1].toString();
        String a1OK = "(1, 3, 6.5)";
        if (!a1.equals(a1OK)) {
            return "PORQUE el metodo toString() de tu clase Arista NO es correcto...\n  Comprueba primero que sobrescribe al de Object y, luego, que el\n  formato del String resultante es el especificado!!";
        } else {
            for(int i = 0; i < datos.length; ++i) {
                cP.insertar(datos[i]);
            }

            int[] orden = new int[]{2, 4, 1, 0, 3};
            int i = 0;

            while(i < datos.length) {
                Arista a = (Arista)cP.eliminarMin();
                sumaPesos += a.getPeso();
                Arista s = datos[orden[i]];
                if (a.getOrigen() != a.getDestino() && s.getOrigen() != s.getDestino()) {
                    if (a.getOrigen() == s.getOrigen() && a.getDestino() == s.getDestino() && a.getPeso() == s.getPeso()) {
                        ++i;
                        continue;
                    }

                    return "PORQUE las aristas no se comparan adecuadamente...\n  Comprueba el metodo compareTo de tu clase Arista!!";
                }

                return "PORQUE los vertices origen y destino de una arista NO pueden coincidir...\n  Comprueba los metodos getOrigen y getDestino de tu clase Arista!!";
            }

            if (Math.abs(sumaPesos - 43.8D) >= 1.0E-8D) {
                return "PORQUE los pesos de las aristas no son correctos...\n  Comprueba los metodos consultores de tu clase Arista!!";
            } else {
                return null;
            }
        }
    }

    public static String testKruskalNoConexo() {
        GrafoNoDirigido g = new GrafoNoDirigido(7);
        g.insertarArista(2, 3, 4.0D);
        g.insertarArista(4, 5, 4.0D);
        g.insertarArista(0, 1, 6.0D);
        g.insertarArista(1, 3, 6.0D);
        g.insertarArista(0, 2, 12.0D);
        g.insertarArista(0, 3, 14.0D);
        g.insertarArista(5, 6, 15.0D);
        g.insertarArista(4, 6, 20.0D);
        return g.kruskal() == null ? null : "Tu metodo kruskal NO devuelve null para un Grafo como, por ejemplo,\n" + g.toString() + "\n";
    }

    public static String testKruskal() {
        GrafoNoDirigido g = new GrafoNoDirigido(7);
        g.insertarArista(2, 3, 4.0D);
        g.insertarArista(4, 5, 4.0D);
        g.insertarArista(0, 1, 6.0D);
        g.insertarArista(1, 3, 6.0D);
        g.insertarArista(3, 4, 9.0D);
        g.insertarArista(0, 2, 12.0D);
        g.insertarArista(2, 4, 12.0D);
        g.insertarArista(3, 5, 12.0D);
        g.insertarArista(0, 3, 14.0D);
        g.insertarArista(5, 6, 15.0D);
        g.insertarArista(1, 5, 20.0D);
        g.insertarArista(4, 6, 20.0D);
        String traza = "Tu metodo NO obtiene el MST correcto para un Grafo como, por ejemplo,\n" + g.toString() + "\n";
        Arista[] sol = new Arista[]{new Arista(2, 3, 4.0D), new Arista(5, 4, 4.0D), new Arista(1, 0, 6.0D), new Arista(1, 3, 6.0D), new Arista(4, 3, 9.0D), new Arista(5, 6, 15.0D)};
        Arista[] mST = g.kruskal();
        boolean okNumA = mST != null && mST.length == 6;
        if (okNumA) {
            String comparativa = " Arista\t   de tu ST\t   del MST correcto\n";
            int numAristasOK = 0;
            double coste = 0.0D;

            for(int i = 0; i < mST.length; ++i) {
                Arista a = mST[i];
                Arista s = sol[i];
                if (a.getOrigen() == s.getOrigen() && a.getDestino() == s.getDestino() && a.getPeso() == s.getPeso()) {
                    ++numAristasOK;
                    coste += mST[i].getPeso();
                }

                comparativa = comparativa + "   " + i + "\t   " + a.toString() + "\t   " + s.toString() + "\n";
            }

            double diff = Math.abs(coste - 44.0D);
            boolean okCoste = diff < 1.0E-8D;
            if (numAristasOK == 6) {
                if (okCoste) {
                    return null;
                } else {
                    return traza + "PORQUE su coste NO es el correcto...\n\n\tSi sus Aristas son:\n" + Arrays.toString(mST) + "\tsu coste deberia ser 44.0 PERO, sin embargo es " + coste + "\n";
                }
            } else {
                return traza + "PORQUE algunas de sus Aristas NO son correctas:\n" + comparativa + "\n";
            }
        } else {
            return mST == null ? "PORQUE devuelves un conjunto de aristas NULL...\n  Comprueba las Aristas que insertas en la Cola de Prioridad!!" : "PORQUE devuelves un conjunto de " + mST.length + " aristas(!!), y debiera tener |V| - 1 = 6";
        }
    }
}