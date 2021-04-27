import java.util.Vector;
public class Buscador {
    private Hash hash;

    public Buscador(Hash hash){
        this.hash = hash;
    }

    /**
     * Esta función se encarga de iterar entre todas las posibles combinaciones de caracteres del alfabeto hasta máximo 7 caraacteres
     * por combinación, para concatenarlo con la cadena inicial, encontrar el hash de esa nueva cadena formada por la combinación
     * de caracteres y la cadena inicial, y verificar si cumple con la condición de la cantidadde ceros necesarios al inicio del
     * hash.
     * @param cantidadDeCeros condición que indica la cantidad de ceros que tiene que tener el hash al comienzo de la cadena para ser considerado válido
     * @param cadenaInicial cadena ininical que se va a concatenar con las combinaciones de caracteres para encontrar el hash.
     */
    public String buscarCombinacionParaQueElHashCumplaLaCondicion(int cantidadDeCeros, String cadenaInicial){

        String ceros = "";

        for(int i = 0; i < cantidadDeCeros/4; i++){
            ceros += "0";
        }

        //TODO Esta lista debe ser el abecedario completo. Está con 4 caracteres para poder hacer pruebas
        String[] abecedario = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};


        String[] combinacionesEncontradasDeTamañoEspecifico = abecedario;
        for(int i = 0; i < combinacionesEncontradasDeTamañoEspecifico.length; i++)
        {
            String hashActual = hash.calcularHash(combinacionesEncontradasDeTamañoEspecifico[i] + cadenaInicial);

            if(hashActual.startsWith(ceros)){
                System.out.println(hashActual);
                return combinacionesEncontradasDeTamañoEspecifico[i];
            }
        }

        int cantidadCombinacionesEncontradasDeTamañoEspecifico = combinacionesEncontradasDeTamañoEspecifico.length;

        // Iterar entre todos los posibles tamaños de combinaciones
        //El 7 es porque según el enunciado, es el tamaño máximo posible que va a tener el v, es decir, la combinación buscada.
        for(int z = 0; z < 7 - 1; z++)
        {
            // Almacena todas las combinaciones del tamaño que estemos mirando actualmente ¿(z)?
            Vector<String> vectorTemporal = new Vector<String>();

            // Iterar el array
            for(int i = 0; i < abecedario.length; i++) {
                for (int k = 0; k < combinacionesEncontradasDeTamañoEspecifico.length; k++) {
                    // Generar todas las combinaciones de longitud z
                    String nuevaCombinacion = combinacionesEncontradasDeTamañoEspecifico[k] + abecedario[i];
                    String cadenaConcatenada = nuevaCombinacion + cadenaInicial;
                    String hashActual = hash.calcularHash(cadenaConcatenada);
                    //System.out.println(hashActual);
                    if (hashActual.startsWith(ceros)) {
                        System.out.println(hashActual);
                        return nuevaCombinacion;
                    }
                    vectorTemporal.add(nuevaCombinacion);
                    cantidadCombinacionesEncontradasDeTamañoEspecifico += 1;
                }
            }
            // Replace all combinations of length z - 1
            // with all combinations of length z
            combinacionesEncontradasDeTamañoEspecifico = vectorTemporal.toArray(new String[vectorTemporal.size()]);;
        }
        return "-1";
    }
}

