package vistas_conductor;

import modelos.Conductor;
import modelos.Vehiculo;
import vistas_generales.Interfaz;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class CIVehiculo extends JPanel implements Interfaz {

    private Dimension tamano;
    private Conductor conductor;
    private Vehiculo vehiculo;

    private JLabel lb_vehiculo;

    private JLabel lb_modelo;
    private JLabel lb_txtModelo;
    private JLabel lb_sombra_modelo;

    private JLabel lb_placa;
    private JLabel lb_txtPlaca;
    private JLabel lb_sombra_placa;

    private JLabel lb_color;
    private JLabel lb_txtColor;
    private JLabel lb_sombra_color;

    private JLabel lb_tipo;
    private JLabel lb_txtTipo;
    private JLabel lb_sombra_tipo;

    private JButton btn_menu;
    private JButton btn_historial;
    private JButton btn_vehiculo;
    private JButton btn_perfil;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public CIVehiculo(Dimension tamano){
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    private void init(){
        setLayout(null);
        setBackground(new Color(255, 160, 0));

        //superior
        lb_vehiculo = new JLabel("VEHICULO", SwingConstants.CENTER);

        lb_vehiculo.setBounds(tamano.width / 2 - 150, 100, 300, 40);

        lb_vehiculo.setFont(new Font("poppins", Font.PLAIN, 25));

        add(lb_vehiculo);

        //medio
        lb_modelo = new JLabel("MODELO");
        lb_txtModelo = new JLabel();
        lb_sombra_modelo = new JLabel();

        lb_placa = new JLabel("PLACA");
        lb_txtPlaca = new JLabel();
        lb_sombra_placa = new JLabel();

        lb_color = new JLabel("COLOR");
        lb_txtColor = new JLabel();
        lb_sombra_color = new JLabel();

        lb_tipo = new JLabel("TIPO");
        lb_txtTipo = new JLabel();
        lb_sombra_tipo = new JLabel();

        lb_modelo.setBounds(125, 300, 150, 25);
        lb_txtModelo.setBounds(300, 300, 200, 25);
        lb_sombra_modelo.setBounds(305, 305, 200, 25);

        lb_placa.setBounds(125, 360, 150, 25);
        lb_txtPlaca.setBounds(300, 360, 200, 25);
        lb_sombra_placa.setBounds(305, 365, 200, 25);

        lb_color.setBounds(125, 420, 150, 25);
        lb_txtColor.setBounds(300, 420, 200, 25);
        lb_sombra_color.setBounds(305, 425, 200, 25);

        lb_tipo.setBounds(125, 480, 150, 25);
        lb_txtTipo.setBounds(300, 480, 200, 25);
        lb_sombra_tipo.setBounds(305, 485, 200, 25);

        lb_sombra_modelo.setOpaque(true);
        lb_sombra_placa.setOpaque(true);
        lb_sombra_color.setOpaque(true);
        lb_sombra_tipo.setOpaque(true);
        lb_sombra_modelo.setBackground(Color.gray);
        lb_sombra_placa.setBackground(Color.gray);
        lb_sombra_color.setBackground(Color.gray);
        lb_sombra_tipo.setBackground(Color.gray);

        lb_txtModelo.setOpaque(true);
        lb_txtPlaca.setOpaque(true);
        lb_txtColor.setOpaque(true);
        lb_txtTipo.setOpaque(true);
        lb_txtModelo.setBackground(Color.darkGray);
        lb_txtPlaca.setBackground(Color.darkGray);
        lb_txtColor.setBackground(Color.darkGray);
        lb_txtTipo.setBackground(Color.darkGray);
        lb_txtModelo.setForeground(Color.lightGray);
        lb_txtPlaca.setForeground(Color.lightGray);
        lb_txtColor.setForeground(Color.lightGray);
        lb_txtTipo.setForeground(Color.lightGray);

        add(lb_modelo);
        add(lb_txtModelo);
        add(lb_sombra_modelo);
        add(lb_placa);
        add(lb_txtPlaca);
        add(lb_sombra_placa);
        add(lb_color);
        add(lb_txtColor);
        add(lb_sombra_color);
        add(lb_tipo);
        add(lb_txtTipo);
        add(lb_sombra_tipo);

        //inferior
        btn_menu = new JButton();
        btn_historial = new JButton();
        btn_vehiculo = new JButton();
        btn_perfil = new JButton();

        lb_fondo_inferior = new JLabel();

        btn_menu.setBounds(tamano.width / 2 - 165, tamano.height - 85, 30, 30);
        btn_historial.setBounds(tamano.width / 2 - 65, tamano.height - 85, 30, 30);
        btn_vehiculo.setBounds(tamano.width / 2 + 35, tamano.height - 85, 30, 30);
        btn_perfil.setBounds(tamano.width / 2 + 135, tamano.height - 85, 30, 30);

        lb_fondo_inferior.setBounds(0, tamano.height - 100, tamano.width, 100);

        BufferedImage bimg_menu = null;
        BufferedImage bimg_historial = null;
        BufferedImage bimg_vehiculo = null;
        BufferedImage bimg_perfil = null;

        Image img_menu;
        Image img_historial;
        Image img_vehiculo;
        Image img_perfil;

        ImageIcon icon_menu;
        ImageIcon icon_historial;
        ImageIcon icon_vehiculo;
        ImageIcon icon_perfil;

        try {
            bimg_menu = ImageIO.read(new File("Imagenes/menu.png"));
            bimg_historial = ImageIO.read(new File("Imagenes/historial.png"));
            bimg_vehiculo = ImageIO.read(new File("Imagenes/vehiculo.png"));
            bimg_perfil = ImageIO.read(new File("Imagenes/cuenta.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_menu = bimg_menu.getScaledInstance(btn_menu.getWidth(), btn_menu.getHeight(), Image.SCALE_SMOOTH);
        img_historial = bimg_historial.getScaledInstance(btn_historial.getWidth(), btn_historial.getHeight(), Image.SCALE_SMOOTH);
        img_vehiculo = bimg_vehiculo.getScaledInstance(btn_vehiculo.getWidth(), btn_vehiculo.getHeight(), Image.SCALE_SMOOTH);
        img_perfil = bimg_perfil.getScaledInstance(btn_perfil.getWidth(), btn_perfil.getHeight(), Image.SCALE_SMOOTH);

        icon_menu = new ImageIcon(img_menu);
        icon_historial = new ImageIcon(img_historial);
        icon_vehiculo = new ImageIcon(img_vehiculo);
        icon_perfil = new ImageIcon(img_perfil);

        btn_menu.setIcon(icon_menu);
        btn_historial.setIcon(icon_historial);
        btn_vehiculo.setIcon(icon_vehiculo);
        btn_perfil.setIcon(icon_perfil);

        btn_menu.setBorder(null);
        btn_historial.setBorder(null);
        btn_vehiculo.setBorder(null);
        btn_perfil.setBorder(null);

        btn_menu.setContentAreaFilled(false);
        btn_historial.setContentAreaFilled(false);
        btn_vehiculo.setContentAreaFilled(false);
        btn_perfil.setContentAreaFilled(false);

        lb_fondo_inferior.setOpaque(true);
        lb_fondo_inferior.setBackground(Color.black);

        add(btn_menu);
        add(btn_historial);
        add(btn_vehiculo);
        add(btn_perfil);
        add(lb_fondo_inferior);

        btn_menu.addActionListener(e -> {
            notificador.firePropertyChange("civehiculo_cimenu", null, null);
        });
        btn_historial.addActionListener(e -> {
            notificador.firePropertyChange("civehiculo_cihistorial", null, null);
        });
        btn_perfil.addActionListener(e -> {
            notificador.firePropertyChange("civehiculo_ciperfil", null, null);
        });
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

    @Override
    public void actualizarDatos() {
        lb_txtModelo.setText(vehiculo.getModelo());
        lb_txtPlaca.setText(vehiculo.getPlaca());
        lb_txtColor.setText(vehiculo.getColor());
        lb_txtTipo.setText(vehiculo.getTipo());
    }

    @Override
    public String getTipo() {
        return "civehiculo";
    }
}
