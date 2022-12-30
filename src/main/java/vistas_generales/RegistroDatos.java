package vistas_generales;

import com.toedter.calendar.JDateChooser;
import dao.PasajeroDao;
import modelos.Pasajero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroDatos extends JPanel {

    private Dimension tamano;
    private String usuario;
    private String contrasena;

    private JButton btn_volver;
    private JLabel lb_datos_personales;

    private JLabel lb_nombre;
    private JTextField txt_nombre;
    private JLabel lb_sombra_nombre;

    private JLabel lb_appaterno;
    private JTextField txt_appaterno;
    private JLabel lb_sombra_appaterno;

    private JLabel lb_apmaterno;
    private JTextField txt_apmaterno;
    private JLabel lb_sombra_apmaterno;

    private JLabel lb_ci;
    private JTextField txt_ci;
    private JLabel lb_sombra_ci;

    private JLabel lb_email;
    private JTextField txt_email;
    private JLabel lb_sombra_email;

    private JLabel lb_telefono;
    private JTextField txt_telefono;
    private JLabel lb_sombra_telefono;

    private JLabel lb_fecha;
    private JDateChooser fecha;
    private JLabel lb_sombra_fecha;
    private SimpleDateFormat formato_fecha;

    private JButton btn_registrar;

    private PropertyChangeSupport notificador;

    public RegistroDatos(Dimension tamano) {
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
        lb_datos_personales = new JLabel("DATOS PERSONALES", SwingConstants.CENTER);

        btn_volver.setBounds(20, 20, 25, 25);
        lb_datos_personales.setBounds(tamano.width / 2 - 150, 100, 300, 40);

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

        lb_datos_personales.setFont(new Font("poppins", Font.PLAIN, 25));

        add(btn_volver);
        add(lb_datos_personales);

        //medio
        lb_nombre = new JLabel("Nombre*");
        txt_nombre = new JTextField();
        lb_sombra_nombre = new JLabel();

        lb_appaterno = new JLabel("Apellido paterno*");
        txt_appaterno = new JTextField();
        lb_sombra_appaterno = new JLabel();

        lb_apmaterno = new JLabel("Apellido materno");
        txt_apmaterno = new JTextField();
        lb_sombra_apmaterno = new JLabel();

        lb_ci = new JLabel("CI*");
        txt_ci = new JTextField();
        lb_sombra_ci = new JLabel();

        lb_email = new JLabel("E-MAIL*");
        txt_email = new JTextField();
        lb_sombra_email = new JLabel();

        lb_telefono = new JLabel("Nro. telefonico*");
        txt_telefono = new JTextField();
        lb_sombra_telefono = new JLabel();

        lb_fecha = new JLabel("Fecha de nacimiento*");
        fecha = new JDateChooser();
        lb_sombra_fecha = new JLabel();
        formato_fecha = new SimpleDateFormat("yyyy-MM-dd");

        lb_nombre.setBounds(125, 250, 150, 25);
        txt_nombre.setBounds(300, 250, 200, 25);
        lb_sombra_nombre.setBounds(305, 255, 200, 25);

        lb_appaterno.setBounds(125, 310, 150, 25);
        txt_appaterno.setBounds(300, 310, 200, 25);
        lb_sombra_appaterno.setBounds(305, 315, 200, 25);

        lb_apmaterno.setBounds(125, 370, 150, 25);
        txt_apmaterno.setBounds(300, 370, 200, 25);
        lb_sombra_apmaterno.setBounds(305, 375, 200, 25);

        lb_ci.setBounds(125, 430, 150, 25);
        txt_ci.setBounds(300, 430, 200, 25);
        lb_sombra_ci.setBounds(305, 435, 200, 25);

        lb_email.setBounds(125, 490, 150, 25);
        txt_email.setBounds(300, 490, 200, 25);
        lb_sombra_email.setBounds(305, 495, 200, 25);

        lb_telefono.setBounds(125, 550, 150, 25);
        txt_telefono.setBounds(300, 550, 200, 25);
        lb_sombra_telefono.setBounds(305, 555, 200, 25);

        lb_fecha.setBounds(125, 610, 150, 25);
        fecha.setBounds(300, 610, 200, 25);
        lb_sombra_fecha.setBounds(305, 615, 200, 25);

        lb_sombra_nombre.setOpaque(true);
        lb_sombra_appaterno.setOpaque(true);
        lb_sombra_apmaterno.setOpaque(true);
        lb_sombra_ci.setOpaque(true);
        lb_sombra_email.setOpaque(true);
        lb_sombra_telefono.setOpaque(true);
        lb_sombra_fecha.setOpaque(true);
        lb_sombra_nombre.setBackground(Color.gray);
        lb_sombra_appaterno.setBackground(Color.gray);
        lb_sombra_apmaterno.setBackground(Color.gray);
        lb_sombra_ci.setBackground(Color.gray);
        lb_sombra_email.setBackground(Color.gray);
        lb_sombra_telefono.setBackground(Color.gray);
        lb_sombra_fecha.setBackground(Color.gray);

        txt_nombre.setBackground(Color.darkGray);
        txt_appaterno.setBackground(Color.darkGray);
        txt_apmaterno.setBackground(Color.darkGray);
        txt_ci.setBackground(Color.darkGray);
        txt_email.setBackground(Color.darkGray);
        txt_telefono.setBackground(Color.darkGray);
        fecha.setBackground(Color.darkGray);
        txt_nombre.setBorder(null);
        txt_appaterno.setBorder(null);
        txt_apmaterno.setBorder(null);
        txt_ci.setBorder(null);
        txt_email.setBorder(null);
        txt_telefono.setBorder(null);
        fecha.setBorder(null);
        txt_nombre.setForeground(Color.lightGray);
        txt_appaterno.setForeground(Color.lightGray);
        txt_apmaterno.setForeground(Color.lightGray);
        txt_ci.setForeground(Color.lightGray);
        txt_email.setForeground(Color.lightGray);
        txt_telefono.setForeground(Color.lightGray);
        fecha.setForeground(Color.lightGray);

        add(lb_nombre);
        add(txt_nombre);
        add(lb_sombra_nombre);
        add(lb_appaterno);
        add(txt_appaterno);
        add(lb_sombra_appaterno);
        add(lb_apmaterno);
        add(txt_apmaterno);
        add(lb_sombra_apmaterno);
        add(lb_ci);
        add(txt_ci);
        add(lb_sombra_ci);
        add(lb_email);
        add(txt_email);
        add(lb_sombra_email);
        add(lb_telefono);
        add(txt_telefono);
        add(lb_sombra_telefono);
        add(lb_fecha);
        add(fecha);
        add(lb_sombra_fecha);

        //inferior
        btn_registrar = new JButton();

        btn_registrar.setBounds(tamano.width / 2 - 45, 700, 90, 50);

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
            notificador.firePropertyChange("rdatos_rusuario", null, null);
            limpiarCampos();
        });

        btn_registrar.addActionListener(e -> {
            PasajeroDao pasajeroDao = new PasajeroDao();
            Pasajero pasajero = new Pasajero(usuario);

            String ci = txt_ci.getText();
            String email = txt_email.getText();
            String telefono = txt_telefono.getText();
            String nombre = txt_nombre.getText();
            String appaterno = txt_appaterno.getText();
            String apmaterno = txt_apmaterno.getText();
            Date fecha_ncimiento = fecha.getDate();

            ci = ci.trim();
            email = email.trim();
            telefono = telefono.trim();
            nombre = nombre.trim();
            appaterno = appaterno.trim();
            apmaterno = apmaterno.trim();

            if (ci.length() == 0 || email.length() == 0 || telefono.length() == 0 || nombre.length() == 0 || appaterno.length() == 0 || fecha_ncimiento == null) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                return;
            }

            pasajero.setCi(ci);
            pasajero.setEmail(email);
            pasajero.setTelefono(telefono);
            pasajero.setNombre(nombre);
            pasajero.setAppaterno(appaterno);
            pasajero.setApmaterno(apmaterno);
            pasajero.setFecha_nacimiento(fecha_ncimiento);

            try {
                //si el insert fue exitoso entonces se muestra el panel login
                pasajeroDao.insert(pasajero, contrasena);
                notificador.firePropertyChange("rdatos_login", null, null);
                limpiarCampos();
            } catch (SQLException error) {
                String[] msg_error = error.getMessage().split("\n");
                JOptionPane.showMessageDialog(null, msg_error[0]);
            }
        });
    }

    private void limpiarCampos() {
        txt_nombre.setText(null);
        txt_appaterno.setText(null);
        txt_apmaterno.setText(null);
        txt_ci.setText(null);
        txt_email.setText(null);
        fecha.setDate(null);
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
