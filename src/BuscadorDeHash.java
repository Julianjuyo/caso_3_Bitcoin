

public class BuscadorDeHash extends Thread {

    private Hash hash;
    private Estado estado;
    private int cantidadDeCeros;
    private String cadenaInicial;
    public Buzon buzonCadenas;
    private int id;
    private long startTime;


    public BuscadorDeHash(int pCantidadDeCeros,String pCadenaInicial, String pAlgoritmoHashImplementado, int pId, Buzon pBuzonCadenas,Estado pEstado,long startTime){

        this.estado = pEstado;
        this.hash = new Hash(pAlgoritmoHashImplementado);
        this.cadenaInicial= pCadenaInicial;
        this.cantidadDeCeros= pCantidadDeCeros;
        this.startTime= startTime;
        this.id=pId;
        this.buzonCadenas = pBuzonCadenas;
    }

    public void run() {

        String ceros = "";
        String cerosMasUno = "0";

        for(int i = 0; i < cantidadDeCeros/4; i++){
            ceros += "0";
            cerosMasUno += "0";
        }

        //System.out.println("entro a thread" +id);

        while(!estado.darYaEncontreUnHashQueSirve()){

            String cadenaObtenida = buzonCadenas.sacarCadena();
            String hashActual = hash.calcularHash(cadenaInicial+cadenaObtenida);
            //System.out.println("El thread:"+id+"-" + cadenaObtenida);

            if(hashActual.startsWith(ceros) && !hashActual.startsWith(cerosMasUno) ){
                System.out.println("El thread:"+id+" Encontrado que cumple la condición es: " + hashActual);
                System.out.println("La cadena (v) encontrada es: " + cadenaObtenida);
                System.out.println("La cadena usada es: " + cadenaInicial);
                long endTime = System.currentTimeMillis() - startTime;
                System.out.println("Se tardó: "+ endTime +" milisegundos"+"\n");
                estado.encontrarHash(cadenaObtenida,hashActual);
            }
        }
    }
}