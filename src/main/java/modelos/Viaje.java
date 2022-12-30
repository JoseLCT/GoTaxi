package modelos;

public class Viaje {

    private String usuario;
    private String origen;
    private String destino;
    private String tipo_vehiculo;
    private int monto;
    private int creditos;

    public Viaje(String usuario, String origen, String destino, String tipo_vehiculo, int monto, int creditos) {
        this.usuario = usuario;
        this.origen = origen;
        this.destino = destino;
        this.tipo_vehiculo = tipo_vehiculo;
        this.monto = monto;
        this.creditos = creditos;
    }

    public Viaje(){

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
