package vistas_generales;

import dao.ConductorDao;
import dao.PasajeroDao;
import dao.UsuarioDao;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class Login extends JPanel {

    private Dimension tamano;

    private JLabel lb_logo;

    private JLabel lb_usuario;
    private JTextField txt_usuario;
    private JLabel lb_sombra_usuario;

    private JLabel lb_contrasena;
    private JPasswordField txt_contrasena;
    private JLabel lb_sombra_contrasena;

    private JButton btn_siguiente;

    private JLabel lb_registro;
    private JButton btn_registro;

    private PropertyChangeSupport notificador;

    public Login(Dimension tamano){
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void addObserver(Ventana ventana){
        notificador.addPropertyChangeListener(ventana);
    }

    private void init(){
        setLayout(null);
        setBackground(new Color(255, 160, 0));

        //superior
        lb_logo = new JLabel();

        lb_logo.setBounds(tamano.width / 2 - 100, 100, 200, 200);

        BufferedImage bimg_logo = null;
        Image img_logo;
        ImageIcon icon_logo;

        try {
            bimg_logo = ImageIO.read(new File("Imagenes/logo.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_logo = bimg_logo.getScaledInstance(lb_logo.getWidth(), lb_logo.getHeight(), Image.SCALE_SMOOTH);
        icon_logo = new ImageIcon(img_logo);
        lb_logo.setIcon(icon_logo);
        add(lb_logo);

        //medio
        lb_usuario = new JLabel("USUARIO");
        txt_usuario = new JTextField();
        lb_sombra_usuario = new JLabel();

        lb_contrasena = new JLabel("CONTRASEÑA");
        txt_contrasena = new JPasswordField();
        lb_sombra_contrasena = new JLabel();

        btn_siguiente = new JButton();

        lb_usuario.setBounds(150, 400, 60, 25);
        txt_usuario.setBounds(150, 435, 300, 25);
        lb_sombra_usuario.setBounds(155, 440, 300, 25);

        lb_contrasena.setBounds(150, 500, 90, 25);
        txt_contrasena.setBounds(150, 535, 300, 25);
        lb_sombra_contrasena.setBounds(155, 540, 300, 25);

        btn_siguiente.setBounds(tamano.width / 2 - 45, 650, 90, 50);

        lb_sombra_usuario.setOpaque(true);
        lb_sombra_contrasena.setOpaque(true);
        lb_sombra_usuario.setBackground(Color.gray);
        lb_sombra_contrasena.setBackground(Color.gray);

        txt_usuario.setBackground(Color.darkGray);
        txt_contrasena.setBackground(Color.darkGray);
        txt_usuario.setBorder(null);
        txt_contrasena.setBorder(null);
        txt_usuario.setForeground(Color.lightGray);
        txt_contrasena.setForeground(Color.lightGray);

        BufferedImage bimg_siguiente = null;
        Image img_siguiente;
        ImageIcon icon_siguiente;

        try {
            bimg_siguiente = ImageIO.read(new File("Imagenes/siguiente.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_siguiente = bimg_siguiente.getScaledInstance(btn_siguiente.getWidth(), btn_siguiente.getHeight(), Image.SCALE_SMOOTH);
        icon_siguiente = new ImageIcon(img_siguiente);

        btn_siguiente.setIcon(icon_siguiente);
        btn_siguiente.setBorder(null);
        btn_siguiente.setContentAreaFilled(false);

        add(lb_usuario);
        add(txt_usuario);
        add(lb_sombra_usuario);
        add(lb_contrasena);
        add(txt_contrasena);
        add(lb_sombra_contrasena);
        add(btn_siguiente);

        //inferior
        lb_registro = new JLabel("No tienes cuenta?");
        btn_registro = new JButton("Registrate");

        lb_registro.setBounds(tamano.width / 2 - 95, tamano.height - 65, 115, 25);
        btn_registro.setBounds(tamano.width / 2 + 15, tamano.height - 65, 80, 25);

        btn_registro.setBorder(null);
        btn_registro.setContentAreaFilled(false);

        add(lb_registro);
        add(btn_registro);

        btn_siguiente.addActionListener(e -> {
            UsuarioDao usuarioDao = new UsuarioDao();
            ConductorDao conductorDao = new ConductorDao();

            String usuario = txt_usuario.getText();
            char[] pass = txt_contrasena.getPassword();
            String password = new String(pass);

            if (!usuarioDao.iniciarSesion(usuario, password)) {
                //usuario no registrado o contrasena inconrrecta
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
                return;
            }

            if (conductorDao.sesionActiva(usuario)){
                //el usuario está activo como conductor, se debe mostrar el menu del conductor
                notificador.firePropertyChange("login_cimenu", 0, txt_usuario.getText());
            } else {
                //el usuario es pasajero
                notificador.firePropertyChange("login_pimenu", 0, txt_usuario.getText());
            }
        });
        btn_registro.addActionListener(e -> {
            notificador.firePropertyChange("login_rusuario", null, null);
        });
    }
}
