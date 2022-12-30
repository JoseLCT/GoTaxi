package modelos;

public class Vehiculo {

    private String placa;
    private String modelo;
    private String color;
    private String tipo;

    public Vehiculo(String placa, String modelo, String color, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
    }

    public Vehiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
