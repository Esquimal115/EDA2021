//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aplicaciones.primitiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class TestPrimitiva {
    private static final int TALLA = 2500;

    public TestPrimitiva() {
    }

    public static void main(String [] args) {
        boolean ok = mostrar(testListaOrdenada()) && mostrar(testNumeroPrimitiva()) && mostrar(testPosicionDe()) && mostrar(testApuestaPrimitiva());
        if (ok) {
            System.out.println("CORRECTO.");
        } else {
            System.out.println("SE ENCONTRARON ERRORES.");
        }

    }

    private static boolean mostrar(boolean ok) {
        if (ok) {
            System.out.println("\tCorrecto");
        } else {
            System.out.println("\tError");
        }

        return ok;
    }

    private static boolean testListaOrdenada() {
        System.out.println("Comprobando la clase LEGListaConPIOrdenada... ");
        ListaConPI<Integer> lista = new LEGListaConPIOrdenada();
        ArrayList<Integer> v = new ArrayList();
        Random r = new Random();

        int i;
        for(i = 1; i <= 2500; ++i) {
            int n = r.nextInt();
            v.add(n);
            lista.insertar(n);
        }

        Collections.sort(v);
        if (lista.talla() != v.size()) {
            return false;
        } else {
            i = 0;
            lista.inicio();

            while(!lista.esFin()) {
                if (!((Integer)v.get(i)).equals(lista.recuperar())) {
                    return false;
                }

                lista.siguiente();
                ++i;
            }

            return true;
        }
    }

    private static boolean testNumeroPrimitiva() {
        System.out.println("Comprobando la clase NumeroPrimitiva... ");

        for(int i = 1; i <= 2500; ++i) {
            NumeroPrimitiva a = new NumeroPrimitiva();
            NumeroPrimitiva b = new NumeroPrimitiva();
            if (a.toString().equals(b.toString())) {
                if (!a.equals(b)) {
                    return false;
                }

                if (a.compareTo(b) != 0) {
                    return false;
                }
            } else {
                if (a.equals(b)) {
                    return false;
                }

                int x = Integer.parseInt(a.toString());
                int y = Integer.parseInt(b.toString());
                if (x < y) {
                    if (a.compareTo(b) >= 0) {
                        return false;
                    }
                } else if (a.compareTo(b) <= 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean testPosicionDe() {
        System.out.println("Comprobando posicionDe y toString de la clase ApuestaPrimitiva... ");
        return testPosicionDe(false) && testPosicionDe(true);
    }

    private static boolean testPosicionDe(boolean ordenada) {
        ApuestaPrimitiva a = new ApuestaPrimitiva(ordenada);
        ArrayList c = obtenerCombinacion(a);

        try {
            for(int i = 0; i <= 2500; ++i) {
                NumeroPrimitiva np = new NumeroPrimitiva();
                Integer n = Integer.parseInt(np.toString());
                int pos = a.posicionDe(np);
                if (pos == -1) {
                    if (c.contains(n)) {
                        return false;
                    }
                } else {
                    int posc = c.indexOf(n);
                    if (pos != posc) {
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception var8) {
            return false;
        }
    }

    private static boolean testApuestaPrimitiva() {
        System.out.println("Comprobando el constructor de la clase ApuestaPrimitiva... ");
        return testApuestaPrimitiva(false) && testApuestaPrimitiva(true);
    }

    private static boolean testApuestaPrimitiva(boolean ordenada) {
        for(int i = 0; i <= 2500; ++i) {
            ApuestaPrimitiva a = new ApuestaPrimitiva(ordenada);
            ArrayList<Integer> c = obtenerCombinacion(a);
            if (c.size() != 6) {
                return false;
            }

            boolean[] v = new boolean[49];
            int prev = -1;

            for(int j = 0; j < c.size(); ++j) {
                int n = (Integer)c.get(j) - 1;
                if (v[n]) {
                    return false;
                }

                v[n] = true;
                if (ordenada && n < prev) {
                    return false;
                }

                prev = n;
            }
        }

        return true;
    }

    private static ArrayList<Integer> obtenerCombinacion(ApuestaPrimitiva a) {
        ArrayList<Integer> c = new ArrayList();
        String[] nums = a.toString().split(",");

        for(int i = 0; i < nums.length; ++i) {
            c.add(Integer.parseInt(nums[i].trim()));
        }

        return c;
    }
}
