public class GeneradorSales {

    public final Buzon buzonCadenas;
    private final Estado estado;


    char[] salesChars = new char[] {'a','a','a','a','a','a','a'};

    private int maximoIndiceAlQueMeHeDevuelto = salesChars.length-1;

    public GeneradorSales(Buzon pBuzonCadenas, Estado estado){
        this.estado = estado;
        this.buzonCadenas= pBuzonCadenas;
        calcularSales(0,salesChars);
    }


    public void calcularSales(int i, char[] sales){
        for (int k = 0; k < 26 & !estado.darYaEncontreUnHashQueSirve() ; k++) {

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
                        guardarSalesEnBuzon(sales);
                        calcularSales(i + 1, sales);
                        ponerLasSiguientesCasillasEnA(i, sales);
                    }
                }
            }
            if(sales[i]!='z'){
                sales[i]++;
                guardarSalesEnBuzon(sales);
            }
        }
    }
    public void ponerLasSiguientesCasillasEnA(int i, char[] sales){
        for (int j = i + 1; j < sales.length; j++) {
            sales[j] = 'a';
        }
    }

    public void guardarSalesEnBuzon(char[] sales){
        StringBuilder sal = new StringBuilder();
        for(int s = maximoIndiceAlQueMeHeDevuelto; s < sales.length; s++){
            sal.append(sales[s]);
        }
        buzonCadenas.almacenarCadena(sal.toString());
    }
}
