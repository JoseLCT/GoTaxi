package vistas_conductor;

import dao.*;
import modelos.Conductor;
import modelos.Pasajero;
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

public class CIViaje extends JPanel implements Interfaz {

    private Dimension tamano;
    private Viaje viaje;
    private Conductor conductor;
    private Pasajero pasajero;

    private JLabel lb_titulo;
    private JLabel lb_viaje_progreso;

    private JLabel lb_origen;
    private JLabel lb_txtOrigen;
    private JLabel lb_sombra_origen;

    private JLabel lb_destino;
    private JLabel lb_txtDestino;
    private JLabel lb_sombra_destino;

    private JLabel lb_pasajero;
    private JLabel lb_txtPasajero;
    private JLabel lb_sombra_pasajero;

    private JLabel lb_monto;
    private JLabel lb_txtMonto;
    private JLabel lb_sombra_monto;

    private JButton btn_finalizar;

    private PropertyChangeSupport notificador;

    public CIViaje(Dimension tamano) {
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
        lb_titulo = new JLabel("VIAJE EN PROGRESO", SwingConstants.CENTER);
        lb_viaje_progreso = new JLabel();

        lb_titulo.setBounds(tamano.width / 2 - 200, 75, 400, 40);
        lb_viaje_progreso.setBounds(tamano.width / 2 - 80, 170, 160, 160);

        BufferedImage bimg_viaje_progreso = null;
        Image img_viaje_progreso;
        ImageIcon icon_viaje_progreso;

        try {
            bimg_viaje_progreso = ImageIO.read(new File("Imagenes/progreso.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_viaje_progreso = bimg_viaje_progreso.getScaledInstance(lb_viaje_progreso.getWidth(), lb_viaje_progreso.getHeight(), Image.SCALE_SMOOTH);
        icon_viaje_progreso = new ImageIcon(img_viaje_progreso);
        lb_viaje_progreso.setIcon(icon_viaje_progreso);

        lb_titulo.setFont(new Font("poppins", Font.PLAIN, 25));

        add(lb_titulo);
        add(lb_viaje_progreso);

        //medio
        lb_origen = new JLabel("ORIGEN");
        lb_txtOrigen = new JLabel();
        lb_sombra_origen = new JLabel();

        lb_destino = new JLabel("DESTINO");
        lb_txtDestino = new JLabel();
        lb_sombra_destino = new JLabel();

        lb_pasajero = new JLabel("PASAJERO");
        lb_txtPasajero = new JLabel();
        lb_sombra_pasajero = new JLabel();

        lb_monto = new JLabel("MONTO");
        lb_txtMonto = new JLabel();
        lb_sombra_monto = new JLabel();

        lb_origen.setBounds(100, 450, 90, 25);
        lb_txtOrigen.setBounds(200, 450, 300, 25);
        lb_sombra_origen.setBounds(205, 455, 300, 25);

        lb_destino.setBounds(100, 500, 90, 25);
        lb_txtDestino.setBounds(200, 500, 300, 25);
        lb_sombra_destino.setBounds(205, 505, 300, 25);

        lb_pasajero.setBounds(100, 550, 90, 25);
        lb_txtPasajero.setBounds(200, 550, 300, 25);
        lb_sombra_pasajero.setBounds(205, 555, 300, 25);

        lb_monto.setBounds(100, 600, 90, 25);
        lb_txtMonto.setBounds(200, 600, 50, 25);
        lb_sombra_monto.setBounds(205, 605, 50, 25);

        lb_sombra_origen.setOpaque(true);
        lb_sombra_destino.setOpaque(true);
        lb_sombra_pasajero.setOpaque(true);
        lb_sombra_monto.setOpaque(true);
        lb_sombra_origen.setBackground(Color.gray);
        lb_sombra_destino.setBackground(Color.gray);
        lb_sombra_pasajero.setBackground(Color.gray);
        lb_sombra_monto.setBackground(Color.gray);

        lb_txtOrigen.setOpaque(true);
        lb_txtDestino.setOpaque(true);
        lb_txtPasajero.setOpaque(true);
        lb_txtMonto.setOpaque(true);
        lb_txtOrigen.setBackground(Color.darkGray);
        lb_txtDestino.setBackground(Color.darkGray);
        lb_txtPasajero.setBackground(Color.darkGray);
        lb_txtMonto.setBackground(Color.darkGray);
        lb_txtOrigen.setForeground(Color.lightGray);
        lb_txtDestino.setForeground(Color.lightGray);
        lb_txtPasajero.setForeground(Color.lightGray);
        lb_txtMonto.setForeground(Color.lightGray);

        lb_txtOrigen.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtDestino.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtPasajero.setHorizontalAlignment(SwingConstants.CENTER);
        lb_txtMonto.setHorizontalAlignment(SwingConstants.CENTER);

        add(lb_origen);
        add(lb_txtOrigen);
        add(lb_sombra_origen);
        add(lb_destino);
        add(lb_txtDestino);
        add(lb_sombra_destino);
        add(lb_pasajero);
        add(lb_txtPasajero);
        add(lb_sombra_pasajero);
        add(lb_monto);
        add(lb_txtMonto);
        add(lb_sombra_monto);

        //inferior
        btn_finalizar = new JButton();

        btn_finalizar.setBounds(tamano.width / 2 - 45, 670, 90, 50);

        BufferedImage bimg_finalizar = null;
        Image img_finalizar;
        ImageIcon icon_finalizar;

        try {
            bimg_finalizar = ImageIO.read(new File("Imagenes/finalizar.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_finalizar = bimg_finalizar.getScaledInstance(btn_finalizar.getWidth(), btn_finalizar.getHeight(), Image.SCALE_SMOOTH);
        icon_finalizar = new ImageIcon(img_finalizar);

        btn_finalizar.setIcon(icon_finalizar);

        add(btn_finalizar);

        //listeners
        btn_finalizar.addActionListener(e -> {
            ViajeDao viajeDao = new ViajeDao();
            int valoracion = 0;

            while (valoracion < 1 || valoracion > 5) {
                try {
                    valoracion = Integer.parseInt(JOptionPane.showInputDialog("Valorar pasajero (1-5)"));
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Valoración no válida");
                }
            }
            try {
                viajeDao.finalizarViaje(conductor, valoracion);
            } catch (SQLException error) {
                String[] msg_error = error.getMessage().split("\n");
                JOptionPane.showMessageDialog(null, msg_error[0]);
            }
            notificador.firePropertyChange("civiaje_cimenu", null, null);
        });
    }

    @Override
    public void actualizarDatos() {
        ViajeDao viajeDao = new ViajeDao();
        PasajeroDao pasajeroDao = new PasajeroDao();

        viaje = new Viaje();
        pasajero = new Pasajero();

        viajeDao.select(viaje, conductor, pasajero, conductor);

        String origen;
        String destino;
        String nombre_pasajero;
        int monto;

        try {
            pasajeroDao.select(pasajero);
        } catch (Exception e) {
            String[] msg_error = e.getMessage().split("\n");
            JOptionPane.showMessageDialog(null, msg_error[0]);
            return;
        }

        origen = viaje.getOrigen();
        destino = viaje.getDestino();
        nombre_pasajero = pasajero.getNombre() + " " + pasajero.getAppaterno();
        monto = viaje.getMonto();

        lb_txtOrigen.setText(origen);
        lb_txtDestino.setText(destino);
        lb_txtPasajero.setText(nombre_pasajero );
        lb_txtMonto.setText(monto + "");
    }

    @Override
    public String getTipo() {
        return "civiaje";
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
