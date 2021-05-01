
import java.util.ArrayList;

public class Buzon {

    private ArrayList<String> lista;
    Object lleno, vacio;
    private int capacidad;
    private Estado estado;


    /**
     *
     */
    public Buzon(Estado estado) {
        lista = new ArrayList<String>();
        this.estado= estado;
        this.capacidad = 3000000;
        vacio = new Object();
        lleno = new Object();
        lista.add("a");
    }

    public String sacarCadena() {
        boolean continuar = true;
        String cadenaRetirada = null;
        while(continuar) {
            synchronized (this) {
                if (lista.size() > 0) {
                    cadenaRetirada = lista.remove(0);
                    //System.out.println("Sacar: "+lista.size());
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
        return cadenaRetirada;
    }

    public void almacenarCadena(String cadena) {
        //System.out.println("Cadena: " + cadena);
        boolean continuar = true;
        while(continuar) {
            synchronized (this) {
                if (lista.size() < capacidad) {
                        lista.add(cadena);
                        //System.out.println("almacenar: "+lista.size());
                        continuar = false;

                }
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
}
