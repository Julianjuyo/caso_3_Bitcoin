

public class BuscadorDeHash extends Thread {

    private Hash hash;
    private Estado estado;
    private static int cantidadDeCeros;
    private static String cadenaInicial;
    public static Buzon buzonCadenas;
    private static Boolean yaSeEncontroCadena;
    private int id;


    public BuscadorDeHash(int pCantidadDeCeros,String pCadenaInicial, String pAlgoritmoHashImplementado, int pId, Buzon pBuzonCadenas,Estado pEstado){

        this.estado = pEstado;
        this.hash = new Hash(pAlgoritmoHashImplementado);
        this.cadenaInicial= pCadenaInicial;
        this.cantidadDeCeros= pCantidadDeCeros;
        this.yaSeEncontroCadena=false;
        this.id=pId;
        this.buzonCadenas = pBuzonCadenas;
    }

    public synchronized Boolean darEstado(){
        return yaSeEncontroCadena;
    }


    public void run() {

        String ceros = "";
        String cerosMasUno = "0";

        for(int i = 0; i < cantidadDeCeros/4; i++){
            ceros += "0";
            cerosMasUno += "0";
        }

        System.out.println("entro a thread" +id);

        while(!yaSeEncontroCadena){

            String cadenaObtenida = buzonCadenas.sacarCadena();
            String hashActual = hash.calcularHash(cadenaInicial+cadenaObtenida);

            System.out.println("El thread:"+id+"-" + cadenaObtenida);

            if(hashActual.startsWith(ceros) && !hashActual.startsWith(cerosMasUno) ){

                System.out.println("El thread:"+id+" Encontrado que cumple la condición es: " + hashActual);
                yaSeEncontroCadena= true;
                estado.encontrarHash(cadenaObtenida,hashActual);
            }

        }
    }
}