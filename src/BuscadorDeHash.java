

public class BuscadorDeHash extends Thread {

    private Hash hash;
    private static int cantidadDeCeros;
    private static String cadenaInicial;
    public static Buzon buzonCadenas;
    private static Boolean yaSeEncontroCadena;
    private int id;


    public BuscadorDeHash(int pCantidadDeCeros,String pCadenaInicial, String pAlgoritmoHashImplementado, int pId, Buzon pBuzonCadenas){

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

            int tamanoBuzo = buzonCadenas.getLista().size();
            System.out.println(tamanoBuzo);



            if(tamanoBuzo>0){
                String cadenaObtenida = buzonCadenas.sacarCadena();
                String hashActual = hash.calcularHash(cadenaInicial+cadenaObtenida);
                System.out.println(id+"-"+hashActual);

                if(hashActual.startsWith(ceros) && !hashActual.startsWith(cerosMasUno) ){
                    System.out.println("El hash encontrado que cumple la condición es: " + hashActual);
                    yaSeEncontroCadena= true;
                }
            }


        }

    }
}
