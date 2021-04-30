
import java.util.ArrayList;

public class Buzon {


    private ArrayList<String> lista;
    Object lleno, vacio;


    /**
     *
     */
    public Buzon() {
        lista = new ArrayList<String>();
        vacio = new Object();
        lleno = new Object();
    }

    /**
     *
     */
    public void almacenarCadena(String cadenaConSalAlmacenada) {

        boolean continuar = true;
        while(continuar) {
            synchronized (this) {

                    lista.add(cadenaConSalAlmacenada);
                    continuar = false;
            }
            if (continuar) {
                Thread.yield();
            }
        }
        synchronized (vacio) {
            try {
                vacio.notify();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @return
     */
    public String sacarCadena() {
        boolean continuar = true;
        String CadenaRetirada = null;
        while(continuar) {
            synchronized (this) {
                if (lista.size() > 0) {
                    CadenaRetirada = lista.remove(0);
                    continuar = false;
                }
            }
            if (continuar) {
                synchronized (vacio) {
                    try {
                        vacio.wait();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        synchronized (lleno) {
            try {
                lleno.notify();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return CadenaRetirada;
    }

}
