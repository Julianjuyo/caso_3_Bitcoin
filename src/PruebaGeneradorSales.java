public class PruebaGeneradorSales {
    
    public static void generarSales(){
        
        //Este for se encarga de sacar todas las combinaciones posibles de longitud i
        for(int i = 7; i>=1; i--){
            //Por ahora, esta clase solamente cambia los valores dentro de los arrays sales, falta hacer los sysouts en los
            //puntos convenientes para saber qué está pasando en cada paso del algoritmo.
            char[] sales = new char[i];
            for(int j = 0; j < i; j++){
                sales[j] = 'a';
            }
            calcularTodasLasCombinacioesDeTamanioDado(i, sales);
        }
    }
    
    //Saca todas las combinaciones de tamaño k
    private static void calcularTodasLasCombinacioesDeTamanioDado(int k, char[] sales) {
        for(int j = k-1; j < 26; j++){
            sales[j]++;
        }
        //En este punto tengo {a,a,a,a,z}
        
        //Acá tengo que volver b el anterior y todos los siguientes volverlos a.
        for(int i = k-2; i >= 0; i--){
            //sales[i]=b;
            sales[i] = 'b';
            //En este punto tengo, por ejemplo {a,a,b,z,z}
            
            //En este for vuelvo as todas las casillas siguientes, porque acabo de convertir el anterior en b
            for(int j = i+1; j<sales.length; j++){
                sales[j] = 'a';
            }
            //En este punto tengo, por ejemplo {a,a,b,a,a}
            combinarCaracteresDesdeElIndiceDadoEnAdelante(i, sales);
            //Al final, acá, tengo, por ejemplo, {a,a,z,z,z}
        }
    }
    
    private static void combinarCaracteresDesdeElIndiceDadoEnAdelante(int i, char[] sales) {
        //En este punto tengo, por ejemplo {a,a,b,a,a}
        //TODO
        //Al final, acá, tengo, por ejemplo, {a,a,z,z,z}
    }
}
