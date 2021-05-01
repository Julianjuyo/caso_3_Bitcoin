
import java.util.ArrayList;

public class Buzon {


    private ArrayList<String> lista;
    Object lleno, vacio;


    /**
     *
     */
    public Buzon() {
        setLista(new ArrayList<String>());
        vacio = new Object();
        lleno = new Object();
    }

    /**
     *
     */
    public void almacenarCadena(String cadenaConSalAlmacenada) {

        //System.out.println("Entro a Almacenar Cadena "+ getLista().size());

        boolean continuar = true;
        while(continuar) {
            synchronized (this) {

                System.out.println("en la bolsa ENTRADA "+cadenaConSalAlmacenada);
                getLista().add(cadenaConSalAlmacenada);
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

        System.out.println("Entro a sacar cadena "+ getLista().size());

        boolean continuar = true;
        String CadenaRetirada = null;
        while(continuar) {
            synchronized (this) {
                if (getLista().size() > 0) {

                    CadenaRetirada = getLista().remove(0);
                    System.out.println("en la bolsa SALIDA"+CadenaRetirada);
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


    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }
}
