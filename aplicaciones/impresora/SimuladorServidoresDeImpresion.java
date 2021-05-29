package aplicaciones.impresora;

import java.text.DecimalFormat;
import java.util.Random;

public class SimuladorServidoresDeImpresion {
    private static String[] titulos = new String[]{"El hobbit - J.R.R. Tolkien", "El nombre del viento - P. Rothfuss", "Portico - F. Pohl", "Mundo Anillo - L. Niven", "Soy leyenda - R. Matheson", "Tropas del espacio - R.A. Heinlein", "El libro de los portales - L. Gallego", "Neverwhere - N. Gaiman", "Rescate en el tiempo - M. Crichton", "Mision de gravedad - H. Clement", "Cita con Rama - A.C. Clarke", "Inferno - D. Brown"};
    private static double tiempoMedioEspera;

    public SimuladorServidoresDeImpresion() {
    }

    public static void main(String[] args) {
        if (testTrabajo() && testServidorDeImpresion()) {
            Trabajo[] tsEnEspera = generarTrabajos();
            double tMS = simularImpresion(tsEnEspera, new ServidorCola());
            System.out.println("SIMULACION CON COLA: Tiempo medio de espera = " + doubleToString(tMS) + " seg.");
            System.out.println();
            tMS = simularImpresion(tsEnEspera, new ServidorColaPrioridad());
            System.out.println("SIMULACION CON COLA DE PRIORIDAD: Tiempo medio de espera = " + doubleToString(tMS) + " seg.");
        }

    }

    private static Trabajo[] generarTrabajos() {
        Trabajo[] res = new Trabajo[titulos.length];
        Random rnd = new Random();
        int entraAlServidor = 0;

        for(int i = 0; i < res.length; ++i) {
            int numPag = rnd.nextInt(90) + 10;
            entraAlServidor += rnd.nextInt(60);
            res[i] = new Trabajo(titulos[i], numPag, entraAlServidor);
        }

        return res;
    }

    private static double simularImpresion(Trabajo[] tsEnEspera, ServidorDeImpresion s) {
        int hora = 0;
        int i = 0;
        tiempoMedioEspera = 0.0D;

        while(i < tsEnEspera.length) {
            if (s.hayTrabajos()) {
                hora += imprimirTrabajo(s, hora);
            } else {
                hora = tsEnEspera[i].getEntraAlServidor();
            }

            while(i < tsEnEspera.length && tsEnEspera[i].getEntraAlServidor() <= hora) {
                s.insertar(tsEnEspera[i]);
                ++i;
            }
        }

        while(s.hayTrabajos()) {
            hora += imprimirTrabajo(s, hora);
        }

        return tiempoMedioEspera / (double)tsEnEspera.length;
    }

    private static int imprimirTrabajo(ServidorDeImpresion s, int hI) {
        Trabajo t = s.getTrabajo();
        int duracion = s.imprimirTrabajo();
        int horaFinImpresion = hI + duracion;
        int espera = hI - t.getEntraAlServidor();
        System.out.println("[" + horaFinImpresion + "] " + t.toString() + " (" + espera + " seg. de espera)");
        tiempoMedioEspera += (double)espera;
        return duracion;
    }

    private static String doubleToString(double n) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(n);
    }

    private static boolean testTrabajo() {
        try {
            Trabajo d1 = new Trabajo("D1", 10, 0);
            Trabajo d2 = new Trabajo("D2", 50, 1);
            Trabajo d3 = new Trabajo("D3", 10, 2);
            if (d1.compareTo(d2) <= 0 && d1.compareTo(d3) == 0 && d2.compareTo(d3) >= 0) {
                return true;
            } else {
                throw new Exception("Error en el metodo compareTo");
            }
        } catch (Exception var3) {
            System.out.println("Error en la clase Trabajo: " + var3.getMessage());
            return false;
        }
    }

    private static boolean testServidorDeImpresion() {
        try {
            Trabajo[] tsEnEspera = new Trabajo[]{new Trabajo("D1", 25, 0), new Trabajo("D2", 50, 1), new Trabajo("D3", 10, 2)};
            Trabajo[] res = new Trabajo[]{tsEnEspera[2], tsEnEspera[0], tsEnEspera[1]};
            int[] duraciones = new int[]{20, 50, 100};
            ServidorDeImpresion s = new ServidorColaPrioridad();
            if (s.hayTrabajos()) {
                throw new Exception("Error en el metodo hayTrabajos");
            } else {
                int i;
                for(i = 0; i < tsEnEspera.length; ++i) {
                    s.insertar(tsEnEspera[i]);
                }

                if (!s.hayTrabajos()) {
                    throw new Exception("Error en el metodo hayTrabajos");
                } else {
                    for(i = 0; i < res.length; ++i) {
                        if (s.getTrabajo() != res[i]) {
                            throw new Exception("Error en el metodo getTrabajo");
                        }

                        int duracion = s.imprimirTrabajo();
                        if (duracion != duraciones[i]) {
                            throw new Exception("Error en el metodo imprimirTrabajo");
                        }
                    }

                    return true;
                }
            }
        } catch (Exception var6) {
            System.out.println("Error en la clase ServidorColaPrioridad: " + var6.getMessage());
            return false;
        }
    }
}