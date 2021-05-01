public class Estado {

    private boolean yaEncontreUnHashQueSirve;
    private String hashResultado;
    private String sal;

    public Estado(){
        yaEncontreUnHashQueSirve = false;
    }

    public synchronized boolean darYaEncontreUnHashQueSirve(){
        return this.yaEncontreUnHashQueSirve;
    }

    public synchronized String darHashResultado(){
        return this.hashResultado;
    }

    public synchronized String darSal(){
        return this.sal;
    }

    public synchronized void encontrarHash(String sal, String hashResultado){
        this.sal = sal;
        this.hashResultado = hashResultado;
        this.yaEncontreUnHashQueSirve = true;
    }
}
