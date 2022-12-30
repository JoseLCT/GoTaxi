package vistas_conductor;

import dao.ConductorDao;
import dao.VehiculoDao;
import modelos.Conductor;
import modelos.Pasajero;
import modelos.Vehiculo;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.sql.SQLException;

public class CIRegistro extends JPanel {

    private Dimension tamano;
    private Pasajero pasajero;
    private Conductor conductor;
    private Vehiculo vehiculo;

    private JButton btn_volver;
    private JLabel lb_registro_vehiculo;

    private JLabel lb_placa;
    private JTextField txt_placa;
    private JLabel lb_sombra_placa;

    private JLabel lb_modelo;
    private JTextField txt_modelo;
    private JLabel lb_sombra_modelo;

    private JLabel lb_color;
    private JTextField txt_color;
    private JLabel lb_sombra_color;

    private JLabel lb_tipo;
    private JComboBox jcbox_tipo;

    private JButton btn_registrar;

    private PropertyChangeSupport notificador;

    public CIRegistro(Dimension tamano) {
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
        btn_volver = new JButton();
        lb_registro_vehiculo = new JLabel("REGISTRO DE VEHICULO", SwingConstants.CENTER);

        btn_volver.setBounds(20, 20, 25, 25);
        lb_registro_vehiculo.setBounds(tamano.width / 2 - 150, 100, 300, 40);

        BufferedImage bimg_volver = null;
        Image img_volver;
        ImageIcon icon_volver;

        try {
            bimg_volver = ImageIO.read(new File("Imagenes/volver.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_volver = bimg_volver.getScaledInstance(btn_volver.getWidth(), btn_volver.getHeight(), Image.SCALE_SMOOTH);
        icon_volver = new ImageIcon(img_volver);

        btn_volver.setIcon(icon_volver);

        btn_volver.setBorder(null);
        btn_volver.setContentAreaFilled(false);

        lb_registro_vehiculo.setFont(new Font("poppins", Font.PLAIN, 25));

        add(btn_volver);
        add(lb_registro_vehiculo);

        //medio
        lb_modelo = new JLabel("MODELO");
        txt_modelo = new JTextField();
        lb_sombra_modelo = new JLabel();

        lb_placa = new JLabel("PLACA");
        txt_placa = new JTextField();
        lb_sombra_placa = new JLabel();

        lb_color = new JLabel("COLOR");
        txt_color = new JTextField();
        lb_sombra_color = new JLabel();

        lb_tipo = new JLabel("TIPO");
        jcbox_tipo = new JComboBox();

        lb_modelo.setBounds(125, 300, 60, 25);
        txt_modelo.setBounds(300, 300, 200, 25);
        lb_sombra_modelo.setBounds(305, 305, 200, 25);

        lb_placa.setBounds(125, 370, 60, 25);
        txt_placa.setBounds(300, 370, 200, 25);
        lb_sombra_placa.setBounds(305, 375, 200, 25);

        lb_color.setBounds(125, 440, 60, 25);
        txt_color.setBounds(300, 440, 200, 25);
        lb_sombra_color.setBounds(305, 445, 200, 25);

        lb_tipo.setBounds(125, 510, 60, 25);
        jcbox_tipo.setBounds(300, 510, 200, 25);

        lb_sombra_modelo.setOpaque(true);
        lb_sombra_placa.setOpaque(true);
        lb_sombra_color.setOpaque(true);
        lb_sombra_modelo.setBackground(Color.gray);
        lb_sombra_placa.setBackground(Color.gray);
        lb_sombra_color.setBackground(Color.gray);

        txt_modelo.setBackground(Color.darkGray);
        txt_placa.setBackground(Color.darkGray);
        txt_color.setBackground(Color.darkGray);
        txt_modelo.setBorder(null);
        txt_placa.setBorder(null);
        txt_color.setBorder(null);
        txt_modelo.setForeground(Color.lightGray);
        txt_placa.setForeground(Color.lightGray);
        txt_color.setForeground(Color.lightGray);

        jcbox_tipo.addItem("ESTANDAR");
        jcbox_tipo.addItem("COMFORT");
        jcbox_tipo.addItem("PREMIUM");

        add(lb_modelo);
        add(txt_modelo);
        add(lb_sombra_modelo);
        add(lb_placa);
        add(txt_placa);
        add(lb_sombra_placa);
        add(lb_color);
        add(txt_color);
        add(lb_sombra_color);
        add(lb_tipo);
        add(jcbox_tipo);

        //inferior
        btn_registrar = new JButton();

        btn_registrar.setBounds(tamano.width / 2 - 45, 650, 90, 50);

        BufferedImage bimg_registrar = null;
        Image img_registrar;
        ImageIcon icon_registrar;

        try {
            bimg_registrar = ImageIO.read(new File("Imagenes/registrar.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_registrar = bimg_registrar.getScaledInstance(btn_registrar.getWidth(), btn_registrar.getHeight(), Image.SCALE_SMOOTH);
        icon_registrar = new ImageIcon(img_registrar);

        btn_registrar.setIcon(icon_registrar);

        btn_registrar.setBorder(null);
        btn_registrar.setContentAreaFilled(false);

        add(btn_registrar);

        btn_volver.addActionListener(e -> {
            notificador.firePropertyChange("ciregistro_piperfil", null, null);
            limpiarCampos();
        });
        btn_registrar.addActionListener(e -> {
            VehiculoDao vehiculoDao = new VehiculoDao();
            ConductorDao conductorDao = new ConductorDao();

            String modelo = txt_modelo.getText();
            String placa = txt_placa.getText();
            String color = txt_color.getText();
            String tipo = jcbox_tipo.getSelectedItem().toString();

            modelo = modelo.trim();
            placa = placa.trim();
            color = color.trim();

            if (modelo.length() == 0 || placa.length() == 0 || color.length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                return;
            }
            if (!vehiculoDao.placaDisponible(placa)) {
                JOptionPane.showMessageDialog(null, "Número de placa no disponible");
                return;
            }

            vehiculo.setModelo(modelo);
            vehiculo.setPlaca(placa);
            vehiculo.setColor(color);
            vehiculo.setTipo(tipo);

            conductor.setUsuario(pasajero.getUsuario());

            try {
                conductorDao.insert(conductor, vehiculo);
                notificador.firePropertyChange("ciregistro_cimenu", null, null);
                limpiarCampos();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        });
    }

    private void limpiarCampos(){
        txt_modelo.setText(null);
        txt_placa.setText(null);
        txt_color.setText(null);
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}