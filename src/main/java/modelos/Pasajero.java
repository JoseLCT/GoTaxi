package modelos;

public class Pasajero extends Usuario{

    private int creditos;

    public Pasajero(String usuario) {
        super(usuario);
    }

    public Pasajero(){

    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
