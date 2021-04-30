

public class BuscadorDeHash extends Thread {

    private Hash hash;
    private static int cantidadDeCeros;
    private static String cadenaInicial;
    public static Buzon buzonCadenas;
    private static Boolean yaSeEncontroCadena;
    private int id;


    public BuscadorDeHash(int pCantidadDeCeros,String pCadenaInicial, String pAlgoritmoHashImplementado, int pId){

        this.hash = new Hash(pAlgoritmoHashImplementado);
        this.cadenaInicial= pCadenaInicial;
        this.cantidadDeCeros= cantidadDeCeros;
        this.yaSeEncontroCadena=false;
        this.id=pId;

    }


    public void run() {

        String ceros = "";
        String cerosMasUno = "0";

        for(int i = 0; i < cantidadDeCeros/4; i++){
            ceros += "0";
            cerosMasUno += "0";
        }

        while(yaSeEncontroCadena){

            String hashActual = hash.calcularHash(cadenaInicial+buzonCadenas.sacarCadena());


            if(hashActual.startsWith(ceros) && !hashActual.startsWith(cerosMasUno) ){
                System.out.println("El hash encontrado que cumple la condición es: " + hashActual);
                yaSeEncontroCadena= true;
            }


        }

    }
}
