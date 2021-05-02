import java.security.*;
import java.math.BigInteger;


public class Hash {

    //Digite el algoritmo
    // SHA-256-64 caracteres
    // SHA-512-128 caracteres
    private static String algoritmoHashImplementado;


    public Hash(String pAlgoritmoHashImplementado ){

        this.algoritmoHashImplementado=pAlgoritmoHashImplementado;
    }


    public String calcularHash(String PTextoACifrar) {
        int caracteres;

        if (algoritmoHashImplementado.equals("SHA-256"))
            caracteres = 64;
        else
            caracteres = 128;

        try {
            MessageDigest encriptador = MessageDigest.getInstance(algoritmoHashImplementado);
            byte[] textoCifrado = encriptador.digest(PTextoACifrar.getBytes());

            BigInteger no = new BigInteger(1, textoCifrado);

            String hashtext = no.toString(16);

            while (hashtext.length() < caracteres) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

/*    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        Hash a = new Hash("SHA-512");
        System.out.println(a.calcularHash("cadena inicial acbalgsrj"));
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime);

        startTime = System.currentTimeMillis();
        a = new Hash("SHA-256");
        System.out.println(a.calcularHash("cadena inicial acbalgsrj"));
        endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime);
    }*/

}
