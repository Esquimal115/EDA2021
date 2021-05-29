//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package librerias.estructurasDeDatos.jerarquicos;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TestMonticuloBinarioR0 {
    public TestMonticuloBinarioR0() {
    }

    private static void checkTalla(MonticuloBinarioR0<Integer> m, int talla) throws Exception {
        Class<?> c = m.getClass();
        Field f = c.getDeclaredField("talla");
        if (f == null) {
            throw new Exception("Atributo talla no encontrado");
        } else {
            f.setAccessible(true);
            Integer mTalla = (Integer)f.get(m);
            if (mTalla != talla) {
                throw new Exception("La talla no se actualiza correctamente");
            }
        }
    }

    private static void checkDuplicar(MonticuloBinarioR0<Integer> m, int talla) throws Exception {
        Class<?> c = m.getClass();
        Field f1 = c.getDeclaredField("elArray");
        if (f1 == null) {
            throw new Exception("Atributo elArray no encontrado");
        } else {
            f1.setAccessible(true);
            Object[] a = (Object[])f1.get(m);
            Field f2 = c.getDeclaredField("talla");
            if (f2 == null) {
                throw new Exception("Atributo talla no encontrado");
            } else {
                f2.setAccessible(true);
                Integer mTalla = (Integer)f2.get(m);
                if (a.length != 10 && mTalla < talla) {
                    throw new Exception("La capacidad de elArray se duplica ANTES de lo debido");
                } else if (a[0] == null) {
                    throw new Exception("La raiz se copia en la posicion 1 de elArray");
                }
            }
        }
    }

    private static void checkArray(MonticuloBinarioR0<Integer> m, Integer[] v, String error) throws Exception {
        Class<?> c = m.getClass();
        Field f = c.getDeclaredField("elArray");
        if (f == null) {
            throw new Exception("Atributo elArray no encontrado");
        } else {
            f.setAccessible(true);
            Object[] a = (Object[])f.get(m);
            if (a.length < v.length) {
                throw new Exception(error + ": Un elemento, al menos, no cabe en elArray");
            } else {
                for(int i = 0; i < v.length; ++i) {
                    if (!a[i].equals(v[i])) {
                        throw new Exception(error + ": Un elemento, al menos, en posicion incorrecta de elArray");
                    }
                }

            }
        }
    }

    private static void testInsertar(MonticuloBinarioR0<Integer> m) throws Exception {
        Integer[] v = new Integer[]{3, 5, 8, 1, 4, 2, 10, 9, 7, 6, 0};
        Integer[] res = new Integer[]{0, 1, 2, 5, 3, 8, 10, 9, 7, 6, 4};

        String mensaje;
        try {
            for(int i = 0; i < v.length; ++i) {
                m.insertar(v[i]);
                checkDuplicar(m, v.length);
            }
        } catch (IndexOutOfBoundsException var7) {
            mensaje = var7.toString();

            try {
                m.duplicarArray();
                throw new Exception("Error al insertar: " + mensaje);
            } catch (Exception var6) {
                throw new Exception("Error al duplicarArray: se copia en nuevo desde la posicion 1 de elArray");
            }
        } catch (Exception var8) {
            mensaje = var8.toString();
            if (mensaje.indexOf("elArray") != -1) {
                mensaje = mensaje.substring(21);
            }

            if (mensaje.indexOf("posicion 1") != -1) {
                throw new Exception("Error al duplicarArray: " + mensaje);
            }

            throw new Exception("Error al insertar: " + mensaje);
        }

        checkTalla(m, v.length);
        checkArray(m, res, "Error al insertar");
    }

    private static void testEliminar(MonticuloBinarioR0<Integer> m) throws Exception {
        Integer[] res = new Integer[]{5, 7, 6, 9, 10, 8};

        try {
            for(int i = 0; i < 5; ++i) {
                if ((Integer)m.recuperarMin() != i) {
                    throw new Exception("Al recuperarMin, NO se recupera elArray[0]\n");
                }

                if ((Integer)m.eliminarMin() != i) {
                    throw new Exception("Al hundir, bien NO se hunde elArray[0], bien NO se hunde correctamente\n");
                }
            }
        } catch (Exception var4) {
            String mensaje = var4.toString();
            if (mensaje.indexOf("elArray") != -1) {
                mensaje = mensaje.substring(21);
            }

            throw new Exception("Error al eliminarMin: " + mensaje);
        }

        checkTalla(m, 6);
        checkArray(m, res, "Error al eliminarMin");
    }

    private static MonticuloBinarioR0<Integer> iniciarMonticulo() {
        MonticuloBinarioR0<Integer> m = new MonticuloBinarioR0();
        Class c = m.getClass();

        try {
            Field fA = c.getDeclaredField("elArray");
            Field fT = c.getDeclaredField("talla");
            fA.setAccessible(true);
            fT.setAccessible(true);
            Integer[] a = new Integer[]{0, 1, 2, 5, 3, 8, 10, 9, 7, 4, 6};
            fA.set(m, a);
            fT.set(m, 11);
            return m;
        } catch (NoSuchFieldException var5) {
            return null;
        } catch (IllegalAccessException var6) {
            return null;
        }
    }

    private static MonticuloBinarioR0<Integer> iniciarNoMonticulo() {
        MonticuloBinarioR0<Integer> m = new MonticuloBinarioR0();
        Class c = m.getClass();

        try {
            Field fA = c.getDeclaredField("elArray");
            Field fT = c.getDeclaredField("talla");
            fA.setAccessible(true);
            fT.setAccessible(true);
            Integer[] a = new Integer[]{8, 7, 10, 5, 6, 4, 1, 9, 2, 3};
            fA.set(m, a);
            fT.set(m, 10);
            return m;
        } catch (NoSuchFieldException var5) {
            return null;
        } catch (IllegalAccessException var6) {
            return null;
        }
    }

    private static boolean testHundir() throws Exception {
        MonticuloBinarioR0 m = iniciarNoMonticulo();

        try {
            for(int i = m.talla / 2 - 1; i >= 0; --i) {
                m.hundir(i);
            }

            Integer[] res = new Integer[]{1, 2, 4, 5, 3, 8, 10, 9, 7, 6};
            return Arrays.equals(m.elArray, res);
        } catch (Exception var2) {
            throw new Exception("Error al hundir.");
        }
    }

    private static boolean testMonticulo() {
        try {
            MonticuloBinarioR0<Integer> m = new MonticuloBinarioR0();
            testInsertar(m);
            boolean ok = testHundir();
            if (!ok) {
                throw new Exception("Error al hundir... Recuerda que el ultimo nodo esta en talla - 1");
            } else {
                m = iniciarMonticulo();
                testEliminar(m);
                if (m.esVacia()) {
                    throw new Exception("Error en esVacia");
                } else {
                    Integer[] res = new Integer[]{5, 6, 7, 8, 9, 10};

                    for(int i = 0; i < 6; ++i) {
                        if (m.recuperarMin() != res[i]) {
                            throw new Exception("Al recuperarMin, NO se recupera elArray[0]");
                        }

                        if (m.eliminarMin() != res[i]) {
                            throw new Exception("Al hundir, bien NO se hunde elArray[0], bien NO se hunde correctamente");
                        }
                    }

                    if (!m.esVacia()) {
                        throw new Exception("Error en esVacia");
                    } else {
                        return true;
                    }
                }
            }
        } catch (Exception var4) {
            System.out.println("Error en la clase MonticuloBinarioR0 - " + var4.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        if (testMonticulo()) {
            System.out.println("Test correcto!");
        }

    }
}