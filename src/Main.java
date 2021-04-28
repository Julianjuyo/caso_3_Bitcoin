import java.io.*;

public class Main {

    public static void escribirEnArchvio(int cantidadDeCeros, String cadena, BufferedWriter bw, String algoritmo){
        try {

            bw.write("\n" +"La cadena usada es:" + cadena + "\n");
            System.out.println("\n" +"La cadena usada es:" + cadena + "\n");
            bw.write("\nEl algoritmo usado es: " + algoritmo + "\n");
            System.out.println("\nEl algoritmo usado es: " + algoritmo + "\n");

            long startTime = System.currentTimeMillis();
            System.out.println("Prueba con "+cantidadDeCeros+" ceros");
            bw.write("\n"+"Prueba con "+cantidadDeCeros+" ceros"+"\n");
            String v = new Buscador(new Hash(algoritmo), bw).buscarCombinacionParaQueElHashCumplaLaCondicion(cantidadDeCeros, cadena);
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("La cadena (v) encontrada es: " + v);
            System.out.println("Se tardó: "+ endTime +" milisegundos"+"\n");
            bw.write("La cadena (v) encontrada es: " + v + "\n");
            bw.write("Se tardó: "+ endTime +" milisegundos"+"\n");
            System.out.println("------------------------------------------------------------------------");
            bw.write("------------------------------------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n"+"Por favor ingrese el algoritmo que desea utilizar (SHA-512 o SHA-256) y la cadena inicial con la que se hará la prueba con maximo 32 caracteres");
        System.out.println("Los datos deben ser ingresados separados por comas");
        System.out.println("Por ejemplo: SHA-512,esta es la cadena inicial");
        InputStreamReader is= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        String line = null;
        try {
            line = br.readLine();
            String[] split = line.split(",");

            String algoritmo = split[0];
            String cadena = split[1];

            //Se verifica que los datos ingrsados cumplan con las condiciones
            if(!(algoritmo.equals("SHA-256")||algoritmo.equals("SHA-512")))
                throw new Exception("El algoritmo ingresado no es válido");

            if(cadena.length()>32)
                throw new Exception("Error la cadena tiene mas de 32 caracteres");

            String pathNuevoArchvio;
            String sSistemaOperativo = System.getProperty("os.name");

            for (int i = 20; i <=36 ; i=i+4) {

                //Se verifica el sistema operativo donde se corre
                if(sSistemaOperativo.equals("Mac OS X"))
                    pathNuevoArchvio ="/Users/julianoliveros/Desktop/Pruebas-"+i+"-"+algoritmo+"-conCadena-"+cadena+".txt";
                else
                    pathNuevoArchvio ="home/ubuntu/caso_3_Bitcoin/docs/Pruebas-"+i+"-"+algoritmo+"-conCadena-"+cadena+".txt";

                BufferedWriter bw = new BufferedWriter(new FileWriter(pathNuevoArchvio));
                escribirEnArchvio(i,  cadena,  bw,  algoritmo);
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}