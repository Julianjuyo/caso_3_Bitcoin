public class GeneradorSalesRecursivo {

    public static void main(String[] args) {
        char[] salesChars = new char[] {'a','a','a'};
        //calcularSales(0, salesChars);

        char[] Caracteres = new char[] {'`','`','`'};


        iteradorFila(3,Caracteres);
    }


    public static void iteradorFila(int numeroCaracteres ,char[] pCaracteres){

        for (int i = 0; i < numeroCaracteres+1; i++) {
            String a ="";
            a+= pCaracteres[1];
            if(i==1)
                System.out.println(a);



            pCaracteres[1]++;

        }





    }

    // - - - - - - -
    public static void calcularSales(int i, char[] sales){

        for (int k = 0; k < 1; k++) {

            if(i!=sales.length-1) {
                calcularSales(i + 1, sales);
                for (int j = i + 1; j < (sales.length - 1); j++) {
                    sales[j] = 'a';
                }
            }
            String sal = "";
            if(k==0){
                for(int s = i; s < sales.length; s++){
                    sal += sales[s];
                }
                System.out.println("1:"+sal);
            }
            sales[i]++;
            sal = "";
            for(int s = i; s < sales.length; s++){
                sal += sales[s];
            }
            System.out.println("2: "+sal);
        }
    }
}
