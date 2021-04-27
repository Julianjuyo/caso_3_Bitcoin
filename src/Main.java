public class Main {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        String v = new Buscador(new Hash("SHA-512")).buscarCombinacionParaQueElHashCumplaLaCondicion(4, "hola");
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Se tardo:"+endTime+" milisegundos"+"\n");

        System.out.println(v);

        
    }
}