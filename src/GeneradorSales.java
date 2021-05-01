public class GeneradorSales {
    static char[] salesChars = new char[] {'a','a','a'};
    static int maximoIndiceAlQueMeHeDevuelto = salesChars.length-1;
    public static void main(String[] args) {
        System.out.println('a');
        calcularSales(0, salesChars);
    }
    
    // - - - - - - -
    public static void calcularSales(int i, char[] sales){
        for (int k = 0; k < 26; k++) {
            if(i!=sales.length-1) {
                calcularSales(i + 1, sales);
                ponerLasSiguientesCasillasEnA(i, sales);
                if(sales[i] == 'a' && i < maximoIndiceAlQueMeHeDevuelto){
                    boolean deboCorrerIndiceMaximo = true;
                    for(int x = i; x >= 0 && deboCorrerIndiceMaximo; x--){
                        deboCorrerIndiceMaximo = sales[x] == 'a';
                    }
                    if(deboCorrerIndiceMaximo){
                        maximoIndiceAlQueMeHeDevuelto--;
                        imprimirSales(sales);
                        calcularSales(i + 1, sales);
                        ponerLasSiguientesCasillasEnA(i, sales);
                    }
                }
            }
            if(sales[i]!='z'){
                sales[i]++;
                imprimirSales(sales);
            }
        }
    }
    public static void ponerLasSiguientesCasillasEnA(int i, char[] sales){
        for (int j = i + 1; j < sales.length; j++) {
            sales[j] = 'a';
        }
    }
    public static void imprimirSales(char[] sales){
        StringBuilder sal = new StringBuilder();
        for(int s = maximoIndiceAlQueMeHeDevuelto; s < sales.length; s++){
            sal.append(sales[s]);
        }
        System.out.println(sal);
    }
}
