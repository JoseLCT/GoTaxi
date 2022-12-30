package vistas_conductor;

import com.toedter.calendar.JDateChooser;
import dao.ConductorDao;
import dao.PasajeroDao;
import dao.UsuarioDao;
import modelos.Conductor;
import modelos.Pasajero;
import vistas_generales.Interfaz;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CIPerfil extends JPanel implements Interfaz {

    private Dimension tamano;
    private Conductor conductor;
    private Conductor conductor_aux;

    private JLabel lb_logo_valoracion;
    private JLabel lb_valoracion;

    private JLabel lb_perfil;

    private JButton btn_cambiar_pasajero;

    private JLabel lb_usuario;
    private JTextField txt_usuario;
    private JLabel lb_sombra_usuario;

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

    private JButton btn_editar;
    private JButton btn_guardar;
    private JButton btn_cancelar;

    private JButton btn_menu;
    private JButton btn_historial;
    private JButton btn_vehiculo;
    private JButton btn_perfil;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public CIPerfil(Dimension tamano) {
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
        lb_logo_valoracion = new JLabel();
        lb_valoracion = new JLabel();
        lb_perfil = new JLabel("PERFIL", SwingConstants.CENTER);
        btn_cambiar_pasajero = new JButton();

        lb_logo_valoracion.setBounds(10, 10, 25, 25);
        lb_valoracion.setBounds(40, 10, 50, 25);
        lb_perfil.setBounds(tamano.width / 2 - 150, 70, 300, 40);
        btn_cambiar_pasajero.setBounds(tamano.width - 55, 15, 40, 40);

        BufferedImage bimg_logo_valoracion = null;
        BufferedImage bimg_cambiar_pasajero = null;

        Image img_logo_valoracion;
        Image img_cambiar_pasajero;

        ImageIcon icon_logo_valoracion;
        ImageIcon icon_cambiar_pasajero;

        try {
            bimg_logo_valoracion = ImageIO.read(new File("Imagenes/valoracion.png"));
            bimg_cambiar_pasajero = ImageIO.read(new File("Imagenes/pasajero.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_logo_valoracion = bimg_logo_valoracion.getScaledInstance(lb_logo_valoracion.getWidth(), lb_logo_valoracion.getHeight(), Image.SCALE_SMOOTH);
        img_cambiar_pasajero = bimg_cambiar_pasajero.getScaledInstance(btn_cambiar_pasajero.getWidth(), btn_cambiar_pasajero.getHeight(), Image.SCALE_SMOOTH);

        icon_logo_valoracion = new ImageIcon(img_logo_valoracion);
        icon_cambiar_pasajero = new ImageIcon(img_cambiar_pasajero);

        lb_logo_valoracion.setIcon(icon_logo_valoracion);
        btn_cambiar_pasajero.setIcon(icon_cambiar_pasajero);

        btn_cambiar_pasajero.setBorder(null);
        btn_cambiar_pasajero.setContentAreaFilled(false);

        lb_valoracion.setVerticalAlignment(SwingConstants.CENTER);
        lb_perfil.setFont(new Font("poppins", Font.PLAIN, 25));

        add(lb_logo_valoracion);
        add(lb_valoracion);
        add(lb_perfil);
        add(btn_cambiar_pasajero);

        //medio
        lb_usuario = new JLabel("Usuario");
        txt_usuario = new JTextField();
        lb_sombra_usuario = new JLabel();

        lb_nombre = new JLabel("Nombre");
        txt_nombre = new JTextField();
        lb_sombra_nombre = new JLabel();

        lb_appaterno = new JLabel("Apellido paterno");
        txt_appaterno = new JTextField();
        lb_sombra_appaterno = new JLabel();

        lb_apmaterno = new JLabel("Apellido materno");
        txt_apmaterno = new JTextField();
        lb_sombra_apmaterno = new JLabel();

        lb_ci = new JLabel("CI");
        txt_ci = new JTextField();
        lb_sombra_ci = new JLabel();

        lb_email = new JLabel("E-MAIL");
        txt_email = new JTextField();
        lb_sombra_email = new JLabel();

        lb_telefono = new JLabel("Nro. telefonico");
        txt_telefono = new JTextField();
        lb_sombra_telefono = new JLabel();

        lb_fecha = new JLabel("Fecha de nacimiento");
        fecha = new JDateChooser();
        lb_sombra_fecha = new JLabel();
        formato_fecha = new SimpleDateFormat("yyyy-MM-dd");

        lb_usuario.setBounds(125, 200, 150, 25);
        txt_usuario.setBounds(300, 200, 200, 25);
        lb_sombra_usuario.setBounds(305, 205, 200, 25);

        lb_nombre.setBounds(125, 260, 150, 25);
        txt_nombre.setBounds(300, 260, 200, 25);
        lb_sombra_nombre.setBounds(305, 265, 200, 25);

        lb_appaterno.setBounds(125, 320, 150, 25);
        txt_appaterno.setBounds(300, 320, 200, 25);
        lb_sombra_appaterno.setBounds(305, 325, 200, 25);

        lb_apmaterno.setBounds(125, 380, 150, 25);
        txt_apmaterno.setBounds(300, 380, 200, 25);
        lb_sombra_apmaterno.setBounds(305, 385, 200, 25);

        lb_ci.setBounds(125, 440, 150, 25);
        txt_ci.setBounds(300, 440, 200, 25);
        lb_sombra_ci.setBounds(305, 445, 200, 25);

        lb_email.setBounds(125, 500, 150, 25);
        txt_email.setBounds(300, 500, 200, 25);
        lb_sombra_email.setBounds(305, 505, 200, 25);

        lb_telefono.setBounds(125, 560, 150, 25);
        txt_telefono.setBounds(300, 560, 200, 25);
        lb_sombra_telefono.setBounds(305, 565, 200, 25);

        lb_fecha.setBounds(125, 620, 150, 25);
        fecha.setBounds(300, 620, 200, 25);
        lb_sombra_fecha.setBounds(305, 625, 200, 25);

        lb_sombra_usuario.setOpaque(true);
        lb_sombra_nombre.setOpaque(true);
        lb_sombra_appaterno.setOpaque(true);
        lb_sombra_apmaterno.setOpaque(true);
        lb_sombra_ci.setOpaque(true);
        lb_sombra_email.setOpaque(true);
        lb_sombra_telefono.setOpaque(true);
        lb_sombra_fecha.setOpaque(true);
        lb_sombra_usuario.setBackground(Color.gray);
        lb_sombra_nombre.setBackground(Color.gray);
        lb_sombra_appaterno.setBackground(Color.gray);
        lb_sombra_apmaterno.setBackground(Color.gray);
        lb_sombra_ci.setBackground(Color.gray);
        lb_sombra_email.setBackground(Color.gray);
        lb_sombra_telefono.setBackground(Color.gray);
        lb_sombra_fecha.setBackground(Color.gray);

        txt_usuario.setBackground(Color.darkGray);
        txt_nombre.setBackground(Color.darkGray);
        txt_appaterno.setBackground(Color.darkGray);
        txt_apmaterno.setBackground(Color.darkGray);
        txt_ci.setBackground(Color.darkGray);
        txt_email.setBackground(Color.darkGray);
        txt_telefono.setBackground(Color.darkGray);
        fecha.setBackground(Color.darkGray);
        txt_usuario.setBorder(null);
        txt_nombre.setBorder(null);
        txt_appaterno.setBorder(null);
        txt_apmaterno.setBorder(null);
        txt_ci.setBorder(null);
        txt_email.setBorder(null);
        txt_telefono.setBorder(null);
        fecha.setBorder(null);
        txt_usuario.setForeground(Color.lightGray);
        txt_nombre.setForeground(Color.lightGray);
        txt_appaterno.setForeground(Color.lightGray);
        txt_apmaterno.setForeground(Color.lightGray);
        txt_ci.setForeground(Color.lightGray);
        txt_email.setForeground(Color.lightGray);
        txt_telefono.setForeground(Color.lightGray);
        fecha.setForeground(Color.lightGray);
        txt_usuario.setEnabled(false);
        txt_nombre.setEnabled(false);
        txt_appaterno.setEnabled(false);
        txt_apmaterno.setEnabled(false);
        txt_ci.setEnabled(false);
        txt_email.setEnabled(false);
        txt_telefono.setEnabled(false);
        fecha.setEnabled(false);

        add(lb_usuario);
        add(txt_usuario);
        add(lb_sombra_usuario);
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
        btn_editar = new JButton();
        btn_guardar = new JButton();
        btn_cancelar = new JButton();

        btn_menu = new JButton();
        btn_historial = new JButton();
        btn_vehiculo = new JButton();
        btn_perfil = new JButton();

        lb_fondo_inferior = new JLabel();

        btn_editar.setBounds(tamano.width / 2 - 45, 700, 90, 50);
        btn_guardar.setBounds(tamano.width / 2 - 45, 700, 90, 50);
        btn_cancelar.setBounds(tamano.width - 100, 700, 90, 50);

        btn_menu.setBounds(tamano.width / 2 - 165, tamano.height - 85, 30, 30);
        btn_historial.setBounds(tamano.width / 2 - 65, tamano.height - 85, 30, 30);
        btn_vehiculo.setBounds(tamano.width / 2 + 35, tamano.height - 85, 30, 30);
        btn_perfil.setBounds(tamano.width / 2 + 135, tamano.height - 85, 30, 30);

        lb_fondo_inferior.setBounds(0, tamano.height - 100, tamano.width, 100);

        BufferedImage bimg_editar = null;
        BufferedImage bimg_guardar = null;
        BufferedImage bimg_cancelar = null;

        BufferedImage bimg_menu = null;
        BufferedImage bimg_historial = null;
        BufferedImage bimg_vehiculo = null;
        BufferedImage bimg_perfil = null;

        Image img_editar;
        Image img_guardar;
        Image img_cancelar;

        Image img_menu;
        Image img_historial;
        Image img_vehiculo;
        Image img_perfil;

        ImageIcon icon_editar;
        ImageIcon icon_guardar;
        ImageIcon icon_cancelar;

        ImageIcon icon_menu;
        ImageIcon icon_historial;
        ImageIcon icon_vehiculo;
        ImageIcon icon_perfil;

        try {
            bimg_editar = ImageIO.read(new File("Imagenes/editar.png"));
            bimg_guardar = ImageIO.read(new File("Imagenes/guardar.png"));
            bimg_cancelar = ImageIO.read(new File("Imagenes/cancelar.png"));

            bimg_menu = ImageIO.read(new File("Imagenes/menu.png"));
            bimg_historial = ImageIO.read(new File("Imagenes/historial.png"));
            bimg_vehiculo = ImageIO.read(new File("Imagenes/vehiculo.png"));
            bimg_perfil = ImageIO.read(new File("Imagenes/cuenta.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_editar = bimg_editar.getScaledInstance(btn_editar.getWidth(), btn_editar.getHeight(), Image.SCALE_SMOOTH);
        img_guardar = bimg_guardar.getScaledInstance(btn_guardar.getWidth(), btn_guardar.getHeight(), Image.SCALE_SMOOTH);
        img_cancelar = bimg_cancelar.getScaledInstance(btn_cancelar.getWidth(), btn_cancelar.getHeight(), Image.SCALE_SMOOTH);

        img_menu = bimg_menu.getScaledInstance(btn_menu.getWidth(), btn_menu.getHeight(), Image.SCALE_SMOOTH);
        img_historial = bimg_historial.getScaledInstance(btn_historial.getWidth(), btn_historial.getHeight(), Image.SCALE_SMOOTH);
        img_vehiculo = bimg_vehiculo.getScaledInstance(btn_vehiculo.getWidth(), btn_vehiculo.getHeight(), Image.SCALE_SMOOTH);
        img_perfil = bimg_perfil.getScaledInstance(btn_perfil.getWidth(), btn_perfil.getHeight(), Image.SCALE_SMOOTH);

        icon_editar = new ImageIcon(img_editar);
        icon_guardar = new ImageIcon(img_guardar);
        icon_cancelar = new ImageIcon(img_cancelar);

        icon_menu = new ImageIcon(img_menu);
        icon_historial = new ImageIcon(img_historial);
        icon_vehiculo = new ImageIcon(img_vehiculo);
        icon_perfil = new ImageIcon(img_perfil);

        btn_editar.setIcon(icon_editar);
        btn_guardar.setIcon(icon_guardar);
        btn_cancelar.setIcon(icon_cancelar);

        btn_menu.setIcon(icon_menu);
        btn_historial.setIcon(icon_historial);
        btn_vehiculo.setIcon(icon_vehiculo);
        btn_perfil.setIcon(icon_perfil);

        btn_editar.setBorder(null);
        btn_guardar.setBorder(null);
        btn_cancelar.setBorder(null);

        btn_menu.setBorder(null);
        btn_historial.setBorder(null);
        btn_vehiculo.setBorder(null);
        btn_perfil.setBorder(null);

        btn_editar.setContentAreaFilled(false);
        btn_guardar.setContentAreaFilled(false);
        btn_cancelar.setContentAreaFilled(false);

        btn_menu.setContentAreaFilled(false);
        btn_historial.setContentAreaFilled(false);
        btn_vehiculo.setContentAreaFilled(false);
        btn_perfil.setContentAreaFilled(false);

        lb_fondo_inferior.setOpaque(true);
        lb_fondo_inferior.setBackground(Color.black);

        btn_guardar.setVisible(false);
        btn_cancelar.setVisible(false);

        add(btn_editar);
        add(btn_guardar);
        add(btn_cancelar);

        add(btn_menu);
        add(btn_historial);
        add(btn_vehiculo);
        add(btn_perfil);
        add(lb_fondo_inferior);

        btn_cambiar_pasajero.addActionListener(e -> {
            ConductorDao conductorDao = new ConductorDao();
            conductorDao.cerrarSesion(conductor.getUsuario());
            notificador.firePropertyChange("ciperfil_piperfil", null, null);
        });

        btn_editar.addActionListener(e -> {
            txt_usuario.setEnabled(true);
            txt_nombre.setEnabled(true);
            txt_appaterno.setEnabled(true);
            txt_apmaterno.setEnabled(true);
            txt_email.setEnabled(true);
            txt_telefono.setEnabled(true);

            btn_editar.setVisible(false);
            btn_guardar.setVisible(true);
            btn_cancelar.setVisible(true);
        });
        btn_guardar.addActionListener(e -> {
            UsuarioDao usuarioDao = new UsuarioDao();
            ConductorDao conductorDao = new ConductorDao();

            String usuario = txt_usuario.getText();
            String nombre = txt_nombre.getText();
            String appaterno = txt_appaterno.getText();
            String apmaterno = txt_apmaterno.getText();
            String email = txt_email.getText();
            String telefono = txt_telefono.getText();

            conductor_aux = new Conductor();
            conductor_aux.setUsuario(usuario);
            conductor_aux.setNombre(nombre);
            conductor_aux.setAppaterno(appaterno);
            conductor_aux.setApmaterno(apmaterno);
            conductor_aux.setEmail(email);
            conductor_aux.setTelefono(telefono);

            try {
                usuarioDao.actualizarDatos(conductor_aux, conductor.getUsuario());
                conductorDao.select(conductor);
            } catch (Exception error) {
                String[] msg_error = error.getMessage().split("\n");
                JOptionPane.showMessageDialog(null, msg_error[0]);
                return;
            }

            txt_usuario.setEnabled(false);
            txt_nombre.setEnabled(false);
            txt_appaterno.setEnabled(false);
            txt_apmaterno.setEnabled(false);
            txt_ci.setEnabled(false);
            txt_email.setEnabled(false);
            txt_telefono.setEnabled(false);
            fecha.setEnabled(false);

            btn_guardar.setVisible(false);
            btn_cancelar.setVisible(false);
            btn_editar.setVisible(true);
        });
        btn_cancelar.addActionListener(e -> {
            txt_usuario.setEnabled(false);
            txt_nombre.setEnabled(false);
            txt_appaterno.setEnabled(false);
            txt_apmaterno.setEnabled(false);
            txt_email.setEnabled(false);
            txt_telefono.setEnabled(false);

            btn_guardar.setVisible(false);
            btn_cancelar.setVisible(false);
            btn_editar.setVisible(true);

            actualizarDatos();
        });

        btn_menu.addActionListener(e -> {
            notificador.firePropertyChange("ciperfil_cimenu", null, null);
        });
        btn_historial.addActionListener(e -> {
            notificador.firePropertyChange("ciperfil_cihistorial", null, null);
        });
        btn_vehiculo.addActionListener(e -> {
            notificador.firePropertyChange("ciperfil_civehiculo", null, null);
        });
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    @Override
    public void actualizarDatos() {
        ConductorDao conductorDao = new ConductorDao();

        try {
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            double valoracion = conductorDao.valoracionPromedio(conductor);
            valoracion = Double.parseDouble(df.format(valoracion));

            lb_valoracion.setText(valoracion + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt_usuario.setText(conductor.getUsuario());
        txt_nombre.setText(conductor.getNombre());
        txt_appaterno.setText(conductor.getAppaterno());
        txt_apmaterno.setText(conductor.getApmaterno());
        txt_ci.setText(conductor.getCi());
        txt_email.setText(conductor.getEmail());
        txt_telefono.setText(conductor.getTelefono());
        fecha.setDate(conductor.getFecha_nacimiento());
    }

    @Override
    public String getTipo() {
        return "ciperfil";
    }
}
