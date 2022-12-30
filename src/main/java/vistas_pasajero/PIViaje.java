package vistas_pasajero;

import conexion.Conexion;
import dao.*;
import modelos.Conductor;
import modelos.Pasajero;
import modelos.Vehiculo;
import modelos.Viaje;
import vistas_generales.Interfaz;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.sql.SQLException;

public class PIViaje extends JPanel implements Interfaz {

    private Conexion conexion;
    private Dimension tamano;
    private Viaje viaje;
    private Pasajero pasajero;
    private Conductor conductor;
    private Vehiculo vehiculo;

    private JLabel lb_titulo;
    private JLabel lb_buscando_viaje;
    private JLabel lb_viaje_progreso;

    private JLabel lb_origen;
    private JLabel lb_txtOrigen;
    private JLabel lb_sombra_origen;

    private JLabel lb_destino;
    private JLabel lb_txtDestino;
    private JLabel lb_sombra_destino;

    private JLabel lb_monto;
    private JLabel lb_txtMonto;
    private JLabel lb_sombra_monto;

    private JLabel lb_conductor;
    private JLabel lb_txtConductor;
    private JLabel lb_sombra_conductor;

    private JLabel lb_vehiculo;
    private JLabel lb_txtVehiculo;
    private JLabel lb_sombra_vehiculo;

    private JLabel lb_placa;
    private JLabel lb_txtPlaca;
    private JLabel lb_sombra_placa;

    private JLabel lb_valoracion;
    private JLabel lb_txtValoracion;
    private JLabel lb_sombra_valoracion;

    private JButton btn_cancelar;

    private PropertyChangeSupport notificador;

    public PIViaje(Dimension tamano) {
        conexion = Conexion.conectar();
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(255, 160, 0));

        //superior
        lb_titulo = new JLabel("BUSCANDO CONDUCTOR", SwingConstants.CENTER);
        lb_buscando_viaje = new JLabel();
        lb_viaje_progreso = new JLabel();

        lb_titulo.setBounds(tamano.width / 2 - 200, 75, 400, 40);
        lb_buscando_viaje.setBounds(tamano.width / 2 - 100, 200, 200, 200);
        lb_viaje_progreso.setBounds(tamano.width / 2 - 80, 170, 160, 160);

        BufferedImage bimg_buscando_viaje = null;
        BufferedImage bimg_viaje_progreso = null;

        Image img_buscando_viaje;
        Image img_viaje_progreso;

        ImageIcon icon_buscando_viaje;
        ImageIcon icon_viaje_progreso;

        try {
            bimg_buscando_viaje = ImageIO.read(new File("Imagenes/lupa.png"));
            bimg_viaje_progreso = ImageIO.read(new File("Imagenes/progreso.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_buscando_viaje = bimg_buscando_viaje.getScaledInstance(lb_buscando_viaje.getWidth(), lb_buscando_viaje.getHeight(), Image.SCALE_SMOOTH);
        img_viaje_progreso = bimg_viaje_progreso.getScaledInstance(lb_viaje_progreso.getWidth(), lb_viaje_progreso.getHeight(), Image.SCALE_SMOOTH);

        icon_buscando_viaje = new ImageIcon(img_buscando_viaje);
        icon_viaje_progreso = new ImageIcon(img_viaje_progreso);

        lb_buscando_viaje.setIcon(icon_buscando_viaje);
        lb_viaje_progreso.setIcon(icon_viaje_progreso);

        lb_titulo.setFont(new Font("poppins", Font.PLAIN, 25));

        lb_viaje_progreso.setVisible(false);

        add(lb_titulo);
        add(lb_buscando_viaje);
        add(lb_viaje_progreso);

        //medio
        lb_origen = new JLabel("ORIGEN");
        lb_txtOrigen = new JLabel();
        lb_sombra_origen = new JLabel();

        lb_destino = new JLabel("DESTINO");
        lb_txtDestino = new JLabel();
        lb_sombra_destino = new JLabel();

        lb_monto = new JLabel("MONTO");
        lb_txtMonto = new JLabel();
        lb_sombra_monto = new JLabel();

        lb_conductor = new JLabel("CONDUCTOR");
        lb_txtConductor = new JLabel();
        lb_sombra_conductor = new JLabel();

        lb_vehiculo = new JLabel("VEHICULO");
        lb_txtVehiculo = new JLabel();
        lb_sombra_vehiculo = new JLabel();

        lb_placa = new JLabel("PLACA");
        lb_txtPlaca = new JLabel();
        lb_sombra_placa = new JLabel();

        lb_valoracion = new JLabel("VALORACION");
        lb_txtValoracion = new JLabel();
        lb_sombra_valoracion = new JLabel();

        lb_origen.setBounds(100, 500, 90, 25);
        lb_txtOrigen.setBounds(200, 500, 300, 25);
        lb_sombra_origen.setBounds(205, 505, 300, 25);

        lb_destino.setBounds(100, 550, 90, 25);
        lb_txtDestino.setBounds(200, 550, 300, 25);
        lb_sombra_destino.setBounds(205, 555, 300, 25);

        lb_monto.setBounds(100, 600, 90, 25);
        lb_txtMonto.setBounds(200, 600, 50, 25);
        lb_sombra_monto.setBounds(205, 605, 50, 25);

        lb_conductor.setBounds(100, 600, 90, 25);
        lb_txtConductor.setBounds(200, 600, 300, 25);
        lb_sombra_conductor.setBounds(205, 605, 300, 25);

        lb_vehiculo.setBounds(100, 650, 90, 25);
        lb_txtVehiculo.setBounds(200, 650, 300, 25);
        lb_sombra_vehiculo.setBounds(205, 655, 300, 25);

        lb_placa.setBounds(100, 700, 90, 25);
        lb_txtPlaca.setBounds(200, 700, 300, 25);
        lb_sombra_placa.setBounds(205, 705, 300, 25);

        lb_valoracion.setBounds(100, 750, 90, 25);
        lb_txtValoracion.setBounds(200, 750, 50, 25);
        lb_sombra_valoracion.setBounds(205, 755, 50, 25);

        lb_sombra_origen.setOpaque(true);
        lb_sombra_destino.setOpaque(true);
        lb_sombra_monto.setOpaque(true);
        lb_sombra_conductor.setOpaque(true);
        lb_sombra_vehiculo.setOpaque(true);
        lb_sombra_placa.setOpaque(true);
        lb_sombra_placa.setOpaque(true);
        lb_sombra_valoracion.setOpaque(true);
        lb_sombra_origen.setBackground(Color.gray);
        lb_sombra_destino.setBackground(Color.gray);
        lb_sombra_monto.setBackground(Color.gray);
        lb_sombra_conductor.setBackground(Color.gray);
        lb_sombra_vehiculo.setBackground(Color.gray);
        lb_sombra_placa.setBackground(Color.gray);
        lb_sombra_placa.setBackground(Color.gray);
        lb_sombra_valoracion.setBackground(Color.gray);

        lb_txtOrigen.setOpaque(true);
        lb_txtDestino.setOpaque(true);
        lb_txtMonto.setOpaque(true);
        lb_txtConductor.setOpaque(true);
        lb_txtVehiculo.setOpaque(true);
        lb_txtPlaca.setOpaque(true);
        lb_txtValoracion.setOpaque(true);
        lb_txtOrigen.setBackground(Color.darkGray);
        lb_txtDestino.setBackground(Color.darkGray);
        lb_txtMonto.setBackground(Color.darkGray);
        lb_txtConductor.setBackground(Color.darkGray);
        lb_txtVehiculo.setBackground(Color.darkGray);
        lb_txtPlaca.setBackground(Color.darkGray);
        lb_txtValoracion.setBackground(Color.darkGray);
        lb_txtOrigen.setForeground(Color.lightGray);
        lb_txtDestino.setForeground(Color.lightGray);
        lb_txtMonto.setForeground(Color.lightGray);
        lb_txtConductor.setForeground(Color.lightGray);
        lb_txtVehiculo.setForeground(Color.lightGray);
        lb_txtPlaca.setForeground(Color.lightGray);
        lb_txtValoracion.setForeground(Color.lightGray);

        lb_txtOrigen.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtDestino.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtMonto.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtConductor.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtPlaca.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtValoracion.setHorizontalAlignment(SwingConstants.CENTER);

        lb_conductor.setVisible(false);
        lb_txtConductor.setVisible(false);
        lb_sombra_conductor.setVisible(false);
        lb_vehiculo.setVisible(false);
        lb_txtVehiculo.setVisible(false);
        lb_sombra_vehiculo.setVisible(false);
        lb_placa.setVisible(false);
        lb_txtPlaca.setVisible(false);
        lb_sombra_placa.setVisible(false);
        lb_valoracion.setVisible(false);
        lb_txtValoracion.setVisible(false);
        lb_sombra_valoracion.setVisible(false);

        add(lb_origen);
        add(lb_txtOrigen);
        add(lb_sombra_origen);
        add(lb_destino);
        add(lb_txtDestino);
        add(lb_sombra_destino);
        add(lb_monto);
        add(lb_txtMonto);
        add(lb_sombra_monto);

        add(lb_conductor);
        add(lb_txtConductor);
        add(lb_sombra_conductor);
        add(lb_vehiculo);
        add(lb_txtVehiculo);
        add(lb_sombra_vehiculo);
        add(lb_placa);
        add(lb_txtPlaca);
        add(lb_sombra_placa);
        add(lb_valoracion);
        add(lb_txtValoracion);
        add(lb_sombra_valoracion);

        //inferior
        btn_cancelar = new JButton();

        btn_cancelar.setBounds(tamano.width / 2 - 45, 700, 90, 50);

        BufferedImage bimg_cancelar = null;
        Image img_cancelar;
        ImageIcon icon_cancelar;

        try {
            bimg_cancelar = ImageIO.read(new File("Imagenes/cancelar.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_cancelar = bimg_cancelar.getScaledInstance(btn_cancelar.getWidth(), btn_cancelar.getHeight(), Image.SCALE_SMOOTH);
        icon_cancelar = new ImageIcon(img_cancelar);
        btn_cancelar.setIcon(icon_cancelar);

        btn_cancelar.setBorder(null);
        btn_cancelar.setContentAreaFilled(false);

        add(btn_cancelar);

        btn_cancelar.addActionListener(e -> {
            SolicitudViajeDao solicitudViajeDao = new SolicitudViajeDao();
            try {
                solicitudViajeDao.delete(pasajero);
            } catch (SQLException error) {
                String[] msg_error = error.getMessage().split("\n");
                JOptionPane.showMessageDialog(null, msg_error[0]);
                return;
            }
            notificador.firePropertyChange("piviaje_pimenu", null, null);
        });
    }

    /**
     * Actualiza los labels del origen, destino y monto
     */
    public void actualizarSolicitud() {
        lb_txtOrigen.setText(viaje.getOrigen());
        lb_txtDestino.setText(viaje.getDestino());
        lb_txtMonto.setText(viaje.getMonto() + "");
    }

    public void escucharViaje() {
        conexion.changeListener("viaje", this);
    }

    public void limpiarCampos(){
        lb_titulo.setText("BUSCANDO CONDUCTOR");

        lb_origen.setBounds(100, 500, 90, 25);
        lb_txtOrigen.setBounds(200, 500, 300, 25);
        lb_sombra_origen.setBounds(205, 505, 300, 25);

        lb_destino.setBounds(100, 550, 90, 25);
        lb_txtDestino.setBounds(200, 550, 300, 25);
        lb_sombra_destino.setBounds(205, 555, 300, 25);

        lb_monto.setBounds(100, 600, 90, 25);
        lb_txtMonto.setBounds(200, 600, 50, 25);
        lb_sombra_monto.setBounds(205, 605, 50, 25);

        btn_cancelar.setVisible(true);
        lb_viaje_progreso.setVisible(false);
        lb_buscando_viaje.setVisible(true);

        lb_conductor.setVisible(false);
        lb_txtConductor.setVisible(false);
        lb_sombra_conductor.setVisible(false);
        lb_vehiculo.setVisible(false);
        lb_txtVehiculo.setVisible(false);
        lb_sombra_vehiculo.setVisible(false);
        lb_placa.setVisible(false);
        lb_txtPlaca.setVisible(false);
        lb_sombra_placa.setVisible(false);
        lb_valoracion.setVisible(false);
        lb_txtValoracion.setVisible(false);
        lb_sombra_valoracion.setVisible(false);
    }

    @Override
    public void actualizarDatos() {
        SolicitudViajeDao solicitudViajeDao = new SolicitudViajeDao();
        ViajeDao viajeDao = new ViajeDao();
        ConductorDao conductorDao = new ConductorDao();
        VehiculoDao vehiculoDao = new VehiculoDao();
        lb_titulo.setText("VIAJE EN PROGRESO");
        btn_cancelar.setVisible(false);

        //verifica si la solicitud aceptada es la solicitud del pasajero
        if (!solicitudViajeDao.solicitudAceptada(pasajero)) return;

        //verifica si el viaje finalizó para que le aparezca la opción de valorar al conductor
        if (conexion.getListener().equals("viaje_finalizado")) {
            JOptionPane.showMessageDialog(null, "Viaje finalizado. Gracias por tu preferencia!");
            int valoracion = 0;

            while (valoracion < 1 || valoracion > 5) {
                try {
                    valoracion = Integer.parseInt(JOptionPane.showInputDialog("Valorar conductor (1-5)"));
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Valoración no válida");
                }
            }
            try {
                viajeDao.valorarConductor(pasajero, valoracion);
            } catch (SQLException error) {
                String[] msg_error = error.getMessage().split("\n");
                JOptionPane.showMessageDialog(null, msg_error[0]);
            }
            notificador.firePropertyChange("piviaje_pimenu", null, null);
            limpiarCampos();
            return;
        }

        //una vez el viaje fue aceptado se cambia el listener para que escuche si el conductor finaliza el viaje
        conexion.changeListener("viaje_finalizado", this);

        JOptionPane.showMessageDialog(null, "Disfruta tu viaje!");

        conductor = new Conductor();
        vehiculo = new Vehiculo();

        viajeDao.select(viaje, pasajero, pasajero, conductor);

        String nombre_conductor;
        String modelo_vehiculo;
        String placa_vehiculo;
        double valoracion_conductor = 0;

        try {
            conductorDao.select(conductor);
            vehiculoDao.select(conductor, vehiculo);
            valoracion_conductor = conductorDao.valoracionPromedio(conductor);
            System.out.println(conductor.getUsuario());
            System.out.println(valoracion_conductor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        nombre_conductor = conductor.getNombre();
        modelo_vehiculo = vehiculo.getModelo();
        placa_vehiculo = vehiculo.getPlaca();

        lb_txtConductor.setText(nombre_conductor);
        lb_txtVehiculo.setText(modelo_vehiculo);
        lb_txtPlaca.setText(placa_vehiculo);
        lb_txtValoracion.setText(valoracion_conductor + "");

        lb_buscando_viaje.setVisible(false);
        lb_viaje_progreso.setVisible(true);

        lb_origen.setBounds(100, 400, 90, 25);
        lb_txtOrigen.setBounds(200, 400, 300, 25);
        lb_sombra_origen.setBounds(205, 405, 300, 25);

        lb_destino.setBounds(100, 450, 90, 25);
        lb_txtDestino.setBounds(200, 450, 300, 25);
        lb_sombra_destino.setBounds(205, 455, 300, 25);

        lb_monto.setBounds(100, 500, 90, 25);
        lb_txtMonto.setBounds(200, 500, 50, 25);
        lb_sombra_monto.setBounds(205, 505, 50, 25);

        lb_conductor.setVisible(true);
        lb_txtConductor.setVisible(true);
        lb_sombra_conductor.setVisible(true);
        lb_vehiculo.setVisible(true);
        lb_txtVehiculo.setVisible(true);
        lb_sombra_vehiculo.setVisible(true);
        lb_placa.setVisible(true);
        lb_txtPlaca.setVisible(true);
        lb_sombra_placa.setVisible(true);
        lb_valoracion.setVisible(true);
        lb_txtValoracion.setVisible(true);
        lb_sombra_valoracion.setVisible(true);
    }

    @Override
    public String getTipo() {
        return "piviaje";
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
}
