import javax.swing.*;
import java.io.*;


public class BuscadorDeHash extends Thread {

    private Hash hash;
    private Estado estado;
    private int cantidadDeCeros;
    private String cadenaInicial;
    public Buzon buzonCadenas;
    private String algoritmoHashImplementado;
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
        this.algoritmoHashImplementado = pAlgoritmoHashImplementado;
    }

    public void escribirEnArchvio(String sal, String hash ,long endTime){
        try {

            String pathNuevoArchvio;
            String sSistemaOperativo = System.getProperty("os.name");

            //Se verifica el sistema operativo donde se corre
            if(sSistemaOperativo.equals("Mac OS X"))
                pathNuevoArchvio ="/Users/julianoliveros/Desktop/Pruebas-"+id+"-"+algoritmoHashImplementado+"-conCadena-"+cadenaInicial+".txt";
            else if(sSistemaOperativo.equals("Windows 10"))
                pathNuevoArchvio ="C:\\Pruebas-"+id+"-"+algoritmoHashImplementado+"-conCadena-"+cadenaInicial+".txt";
            else
                pathNuevoArchvio ="/home/ubuntu/caso_3_Bitcoin/docs/Pruebas-"+id+"-"+algoritmoHashImplementado+"-conCadena-"+cadenaInicial+".txt";


            BufferedWriter bw = new BufferedWriter(new FileWriter(pathNuevoArchvio));

            System.out.println("\n" +"Thread: " + this.id + ". La cadena usada es:" + cadenaInicial + "\n");
            bw.write("\n" +"Thread: " + this.id + ". La cadena usada es:" + cadenaInicial + "\n");

            System.out.println("\n" +"Thread: " + this.id + ". El algoritmo usado es: " + algoritmoHashImplementado + "\n");
            bw.write("\n" +"Thread: " + this.id + ". El algoritmo usado es: " + algoritmoHashImplementado + "\n");

            System.out.println("\n" +"Thread: " + this.id + ". Prueba con "+cantidadDeCeros+" ceros");
            bw.write("\n" +"Thread: " + this.id + ". Prueba con "+cantidadDeCeros+" ceros"+"\n");

            System.out.println("\n" +"Thread: " + this.id + ". La Sal ( cadena v) encontrada es: " + sal);
            bw.write("\n" +"Thread: " + this.id + ". La Sal ( cadena v) encontrada es: " + sal + "\n");

            System.out.println("\n" +"Thread: " + this.id + ". El Hash encontrado fue: " + hash);
            bw.write("\n" +"Thread: " + this.id + ". El Hash encontrado fue:" + hash+ "\n");

            System.out.println("\n" +"Thread: " + this.id + ". Se tardó: "+ endTime +" milisegundos"+"\n");
            bw.write("\n" +"Thread: " + this.id + ". Se tardó: "+ endTime +" milisegundos"+"\n");

            System.out.println("------------------------------------------------------------------------");
            bw.write("------------------------------------------------------------------------");

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

                long endTime = System.currentTimeMillis() - startTime;

                escribirEnArchvio(cadenaObtenida,hashActual,endTime);

                estado.encontrarHash(cadenaObtenida,hashActual);
            }
        }
    }
}