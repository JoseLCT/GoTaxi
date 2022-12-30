package vistas_generales;

import dao.PasajeroDao;
import dao.UsuarioDao;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class RegistroUsuario extends JPanel {

    private Dimension tamano;

    private JButton btn_salir;
    private JLabel lb_registro;

    private JLabel lb_usuario;
    private JTextField txt_usuario;
    private JLabel lb_sombra_usuario;

    private JLabel lb_contrasena;
    private JPasswordField txt_contrasena;
    private JLabel lb_sombra_contrasena;

    private JLabel lb_confirmar_contrasena;
    private JPasswordField txt_confirmar_contrasena;
    private JLabel lb_sombra_confirmar_contrasena;

    private JButton btn_siguiente;

    private PropertyChangeSupport notificador;

    public RegistroUsuario(Dimension tamano) {
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
        btn_salir = new JButton();
        lb_registro = new JLabel("REGISTRO", SwingConstants.CENTER);

        btn_salir.setBounds(20, 20, 15, 15);
        lb_registro.setBounds(tamano.width / 2 - 75, 100, 150, 40);

        BufferedImage bimg_salir = null;
        Image img_salir;
        ImageIcon icon_salir;

        try {
            bimg_salir = ImageIO.read(new File("Imagenes/x.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_salir = bimg_salir.getScaledInstance(btn_salir.getWidth(), btn_salir.getHeight(), Image.SCALE_SMOOTH);
        icon_salir = new ImageIcon(img_salir);

        btn_salir.setIcon(icon_salir);

        btn_salir.setBorder(null);
        btn_salir.setContentAreaFilled(false);

        lb_registro.setFont(new Font("poppins", Font.PLAIN, 25));

        add(btn_salir);
        add(lb_registro);

        //medio
        lb_usuario = new JLabel("USUARIO*");
        txt_usuario = new JTextField();
        lb_sombra_usuario = new JLabel();

        lb_contrasena = new JLabel("CONTRASEÑA*");
        txt_contrasena = new JPasswordField();
        lb_sombra_contrasena = new JLabel();

        lb_confirmar_contrasena = new JLabel("CONFIRMAR CONTRASEÑA*");
        txt_confirmar_contrasena = new JPasswordField();
        lb_sombra_confirmar_contrasena = new JLabel();

        lb_usuario.setBounds(150, 260, 70, 25);
        txt_usuario.setBounds(150, 295, 300, 25);
        lb_sombra_usuario.setBounds(155, 300, 300, 25);

        lb_contrasena.setBounds(150, 360, 100, 25);
        txt_contrasena.setBounds(150, 395, 300, 25);
        lb_sombra_contrasena.setBounds(155, 400, 300, 25);

        lb_confirmar_contrasena.setBounds(150, 460, 180, 25);
        txt_confirmar_contrasena.setBounds(150, 495, 300, 25);
        lb_sombra_confirmar_contrasena.setBounds(155, 500, 300, 25);

        lb_sombra_usuario.setOpaque(true);
        lb_sombra_contrasena.setOpaque(true);
        lb_sombra_confirmar_contrasena.setOpaque(true);
        lb_sombra_usuario.setBackground(Color.gray);
        lb_sombra_contrasena.setBackground(Color.gray);
        lb_sombra_confirmar_contrasena.setBackground(Color.gray);

        txt_usuario.setBackground(Color.darkGray);
        txt_contrasena.setBackground(Color.darkGray);
        txt_confirmar_contrasena.setBackground(Color.darkGray);
        txt_usuario.setBorder(null);
        txt_contrasena.setBorder(null);
        txt_confirmar_contrasena.setBorder(null);
        txt_usuario.setForeground(Color.lightGray);
        txt_contrasena.setForeground(Color.lightGray);
        txt_confirmar_contrasena.setForeground(Color.lightGray);

        add(lb_usuario);
        add(txt_usuario);
        add(lb_sombra_usuario);
        add(lb_contrasena);
        add(txt_contrasena);
        add(lb_sombra_contrasena);
        add(lb_confirmar_contrasena);
        add(txt_confirmar_contrasena);
        add(lb_sombra_confirmar_contrasena);

        //inferior
        btn_siguiente = new JButton();

        btn_siguiente.setBounds(tamano.width / 2 - 45, 650, 90, 50);

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

        add(btn_siguiente);

        //listeners
        btn_salir.addActionListener(e -> {
            notificador.firePropertyChange("rusuario_login", null, null);
            limpiarCampos();
        });

        btn_siguiente.addActionListener(e -> {
            UsuarioDao usuarioDao = new UsuarioDao();
            String usuario = txt_usuario.getText();
            char[] char_contrasena = txt_contrasena.getPassword();
            char[] char_confirmar_contrasena = txt_confirmar_contrasena.getPassword();

            String contrasena = new String(char_contrasena);
            String confirmar_contrasena = new String(char_confirmar_contrasena);

            usuario = usuario.trim();
            contrasena = contrasena.trim();
            confirmar_contrasena = confirmar_contrasena.trim();

            if (usuario.length() == 0 || contrasena.length() == 0 || confirmar_contrasena.length() == 0) {
                //no se rellenaron  los campos obligatorios
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                return;
            }

            if (!usuarioDao.usuarioDisponible(txt_usuario.getText())) {
                //nombre de usuario no disponible
                JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible");
                return;
            }

            if (!contrasena.equals(confirmar_contrasena)) {
                //las contrasenas no coinciden
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                return;
            }

            //si todos los datos están correctos se envia el json
            JSONObject datos = new JSONObject();
            datos.put("usuario", usuario);
            datos.put("contrasena", contrasena);
            notificador.firePropertyChange("rusuario_rdatos", null, datos);
            limpiarCampos();
        });
    }

    private void limpiarCampos() {
        txt_usuario.setText(null);
        txt_contrasena.setText(null);
        txt_confirmar_contrasena.setText(null);
    }
}
