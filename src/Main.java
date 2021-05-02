import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n"+"Por favor ingrese numero de hilos de ejecución que desea crear," +
                           "el algoritmo que desea utilizar (SHA-512 o SHA-256), " +
                           "la cantidad de ceros que desea que tenga al comienzo el hash que se buscará y " +
                           "la cadena inicial con la que se hará la prueba on maximo 32 caracteres");


        System.out.println("Los datos deben ser ingresados separados por comas");
        System.out.println("Por ejemplo: 4,SHA-512,24,esta es la cadena inicial");
        InputStreamReader is= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        String line = null;
        try {
            line = br.readLine();
            String[] split = line.split(",");

            int numeroDeThreads = Integer.parseInt(split[0]);
            String algoritmo = split[1];
            int cantidadDeCeros = Integer.parseInt(split[2]);
            String cadena = split[3];
            
            if(!(algoritmo.equals("SHA-256")||algoritmo.equals("SHA-512"))){
                throw new Exception("El algoritmo ingresado no es válido");
            }
            
            if(cadena.length()>32){
                throw new Exception("Error la cadena tiene mas de 32 caracteres");
            }


            long startTime = System.currentTimeMillis();
            Estado estado = new Estado();
            Buzon buzonDeCadenas = new Buzon(estado);

            for (int i = 0; i < numeroDeThreads ; i++) {
                BuscadorDeHash thread = new BuscadorDeHash(cantidadDeCeros,cadena,algoritmo,i,buzonDeCadenas,estado,startTime);
                thread.start();
            }
            GeneradorSales generadorDeSales = new GeneradorSales(buzonDeCadenas,estado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
