public class Main {
    public static void main(String[] args) {



        long startTime = System.currentTimeMillis();
        String v = new Buscador(new Hash("SHA-512")).buscarCombinacionParaQueElHashCumplaLaCondicion(32, "cadena inicial abc");
        long endTime = System.currentTimeMillis() - startTime;
        endTime= (endTime/60000);
        System.out.println("Se tardo:"+endTime+" minutos"+"\n");

        System.out.println(v);

        
    }
}