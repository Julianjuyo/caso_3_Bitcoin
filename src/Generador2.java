public class Generador2 {

    public static Buzon buzonCadenas;

    static char[] salesChars = new char[] {'a','a','a'};
    static int maximoIndiceAlQueMeHeDevuelto = salesChars.length-1;

    public static void main(String[] args) {
        calcularSales(0, salesChars);
    }

    public Generador2(Buzon pBuzonCadenas){
        this.buzonCadenas= pBuzonCadenas;
    }

    // - - - - - - -
    public static void calcularSales(int i, char[] sales){
        for (int k = 0; k < 25; k++) {

            if(i!=sales.length-1) {
                calcularSales(i + 1, sales);
                for (int j = i + 1; j < sales.length; j++) {
                    sales[j] = 'a';
                }
                if(sales[i] == 'a'){
                    boolean deboCorrerIndiceMaximo = true;
                    for(int x = i; x >= 0 && deboCorrerIndiceMaximo; x--){
                        deboCorrerIndiceMaximo = sales[x] == 'a';
                    }
                    if(deboCorrerIndiceMaximo){
                        maximoIndiceAlQueMeHeDevuelto--;
                    }
                }
            }
            if(k==0) {
                String sal = "";
                for (int s = maximoIndiceAlQueMeHeDevuelto; s < sales.length; s++) {
                    sal += sales[s];
                }
                System.out.println(sal);
            }
            sales[i]++;

            String sal = "";
            for(int s = maximoIndiceAlQueMeHeDevuelto; s < sales.length; s++){
                sal += sales[s];
            }
            System.out.println(sal);
        }
    }

    public String comenzarEjecucion(int pCantidadDeCeros,String pCadenaInicial, String pAlgoritmoHashImplementado){

        String sal ="";
        int numeroDeThreads=4;


        for (int i = 0; i < numeroDeThreads ; i++) {
            new BuscadorDeHash(pCantidadDeCeros,pCadenaInicial,pAlgoritmoHashImplementado,i);

        }

        calcularSales(0,salesChars);

        

        return sal;

    }

}
