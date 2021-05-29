//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Map;

public class TablaHash<C, V> implements Map<C, V> {
    public static final double FC_ESTANDAR = 0.75D;
    public static final boolean REHASHING = true;
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    protected int talla;
    private int numRH;

    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % this.elArray.length;
        if (indiceHash < 0) {
            indiceHash += this.elArray.length;
        }

        return indiceHash;
    }

    public TablaHash(int tallaMaximaEstimada) {
        int capacidad = (int)((double)tallaMaximaEstimada / 0.75D);
        capacidad = siguientePrimo(capacidad);
        this.elArray = new LEGListaConPI[capacidad];

        for(int i = 0; i < this.elArray.length; ++i) {
            this.elArray[i] = new LEGListaConPI();
        }

        this.talla = 0;
        this.numRH = 0;
    }

    public static final int siguientePrimo(int n) {
        int aux = n;
        if (n % 2 == 0) {
            aux = n + 1;
        }

        while(!esPrimo(aux)) {
            aux += 2;
        }

        return aux;
    }

    protected static final boolean esPrimo(int n) {
        for(int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public final double factorCarga() {
        return (double)this.talla / (double)this.elArray.length;
    }

    public boolean esVacio() {
        return this.talla == 0;
    }

    public int talla() {
        return this.talla;
    }

    public int numeroDeRH() {
        return this.numRH;
    }

    public ListaConPI<C> claves() {
        ListaConPI<C> res = new LEGListaConPI();

        for(int i = 0; i < this.elArray.length; ++i) {
            ListaConPI<EntradaHash<C, V>> l = this.elArray[i];
            l.inicio();

            while(!l.esFin()) {
                EntradaHash<C, V> e = (EntradaHash)l.recuperar();
                res.insertar(e.clave);
                l.siguiente();
            }
        }

        return res;
    }

    public final String toString() {
        StringBuilder res = new StringBuilder();
        ListaConPI[] var2 = this.elArray;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ListaConPI<EntradaHash<C, V>> l = var2[var4];
            l.inicio();

            while(!l.esFin()) {
                res.append(l.recuperar() + "\n");
                l.siguiente();
            }
        }

        return res.toString();
    }

    public V recuperar(C c) {
        int pos = this.indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = this.elArray[pos];
        V valor = null;
        l.inicio();

        while(!l.esFin() && !((EntradaHash)l.recuperar()).clave.equals(c)) {
            l.siguiente();
        }

        if (!l.esFin()) {
            valor = (V) ((EntradaHash)l.recuperar()).valor;
        }

        return valor;
    }

    public V eliminar(C c) {
        int pos = this.indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = this.elArray[pos];
        V valor = null;
        l.inicio();

        while(!l.esFin() && !((EntradaHash)l.recuperar()).clave.equals(c)) {
            l.siguiente();
        }

        if (!l.esFin()) {
            valor = (V) ((EntradaHash)l.recuperar()).valor;
            l.eliminar();
            --this.talla;
        }

        return valor;
    }

    public V insertar(C c, V v) {
        this.indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = this.elArray[this.indiceHash(c)];
        V antiguoValor = null;
        l.inicio();

        while(!l.esFin() && !((EntradaHash)l.recuperar()).clave.equals(c)) {
            l.siguiente();
        }

        if (l.esFin()) {
            l.insertar(new EntradaHash(c, v));
            ++this.talla;
            if (this.factorCarga() > 0.75D && REHASHING) {
                numRH++;
                rehashing();
            }
        } else {
            antiguoValor = (V) ((EntradaHash)l.recuperar()).valor;
            ((EntradaHash)l.recuperar()).valor = v;
        }

        return antiguoValor;
    }

    protected final void rehashing() {
        int nuevaCapacidad = siguientePrimo(this.elArray.length * 2);
        ListaConPI<EntradaHash<C, V>>[] elArrayAntiguo = this.elArray;
        this.elArray = new LEGListaConPI[nuevaCapacidad];

        for(int i = 0; i < this.elArray.length; i++) {
            this.elArray[i] = new LEGListaConPI();
        }
        this.talla = 0;

        for(int i = 0; i < elArrayAntiguo.length; ++i) {
            ListaConPI<EntradaHash<C, V>> list = elArrayAntiguo[i];
            list.inicio();

            while(!list.esFin()) {
                EntradaHash<C, V> entrada = list.recuperar();
                this.insertar(entrada.clave, entrada.valor);
                list.siguiente();
            }
        }

    }

    public final double desviacionTipica() {
        double med = this.factorCarga();
        double res = 0.0D;

        for(int i = 0; i < this.elArray.length; ++i) {
            double d = (double)this.elArray[i].talla() - med;
            res += d * d;
        }

        return Math.sqrt(res / (double)this.elArray.length);
    }

    public final double costeMLocalizar() {
        int colisiones = 0;

        for(int i = 0; i < this.elArray.length; ++i) {
            colisiones += this.elArray[i].talla() * (1 + this.elArray[i].talla()) / 2;
        }

        return (double)colisiones / (double)this.talla;
    }

    public String histograma() {
        String res = "";
        int[] histo = new int[10];

        int i;
        for(i = 0; i < this.elArray.length; ++i) {
            int longCubeta = this.elArray[i].talla();
            int var10003;
            if (longCubeta < 9) {
                var10003 = histo[longCubeta]++;
            } else {
                var10003 = histo[9];
                int var10000 = histo[9];
                histo[9] = var10003 + 1;
            }
        }

        for(i = 0; i < histo.length; ++i) {
            res = res + i + "\t" + histo[i] + "\n";
        }

        return res;
    }
}
