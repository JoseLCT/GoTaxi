package modelos;

public class Conductor extends Usuario {

    private int saldo;

    public Conductor(String usuario) {
        super(usuario);
    }

    public Conductor(){

    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
