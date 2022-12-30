package vistas_pasajero;

import dao.PasajeroDao;
import modelos.Pasajero;
import vistas_generales.Interfaz;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class PIHistorial extends JPanel implements Interfaz {

    private Dimension tamano;
    private Pasajero pasajero;

    private JLabel lb_historial;

    private JTable tbl_historial;
    private JScrollPane scroll;

    private JButton btn_menu;
    private JButton btn_historial;
    private JButton btn_perfil;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public PIHistorial(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void addObserver(Ventana ventana){
        notificador.addPropertyChangeListener(ventana);
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(255, 160, 0));

        //superior
        lb_historial = new JLabel("HISTORIAL", SwingConstants.CENTER);
        lb_historial.setBounds(tamano.width / 2 - 150, 100, 300, 40);
        lb_historial.setFont(new Font("poppins", Font.PLAIN, 25));
        add(lb_historial);

        //medio
        tbl_historial = new JTable();
        scroll = new JScrollPane(tbl_historial);

        scroll.setBounds(50, 200, 500, 550);

        add(scroll);

        //inferior
        btn_menu = new JButton();
        btn_historial = new JButton();
        btn_perfil = new JButton();

        lb_fondo_inferior = new JLabel();

        btn_menu.setBounds(tamano.width / 2 - 130, tamano.height - 85, 30, 30);
        btn_historial.setBounds(tamano.width / 2 - 15, tamano.height - 85, 30, 30);
        btn_perfil.setBounds(tamano.width / 2 + 85, tamano.height - 85, 30, 30);

        lb_fondo_inferior.setBounds(0, tamano.height - 100, tamano.width, 100);

        BufferedImage bimg_menu = null;
        BufferedImage bimg_historial = null;
        BufferedImage bimg_perfil = null;

        Image img_menu;
        Image img_historial;
        Image img_perfil;

        ImageIcon icon_menu;
        ImageIcon icon_historial;
        ImageIcon icon_perfil;

        try {
            bimg_menu = ImageIO.read(new File("Imagenes/menu.png"));
            bimg_historial = ImageIO.read(new File("Imagenes/historial.png"));
            bimg_perfil = ImageIO.read(new File("Imagenes/cuenta.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_menu = bimg_menu.getScaledInstance(btn_menu.getWidth(), btn_menu.getHeight(), Image.SCALE_SMOOTH);
        img_historial = bimg_historial.getScaledInstance(btn_historial.getWidth(), btn_historial.getHeight(), Image.SCALE_SMOOTH);
        img_perfil = bimg_perfil.getScaledInstance(btn_perfil.getWidth(), btn_perfil.getHeight(), Image.SCALE_SMOOTH);

        icon_menu = new ImageIcon(img_menu);
        icon_historial = new ImageIcon(img_historial);
        icon_perfil = new ImageIcon(img_perfil);

        btn_menu.setIcon(icon_menu);
        btn_historial.setIcon(icon_historial);
        btn_perfil.setIcon(icon_perfil);

        btn_menu.setBorder(null);
        btn_historial.setBorder(null);
        btn_perfil.setBorder(null);

        btn_menu.setContentAreaFilled(false);
        btn_historial.setContentAreaFilled(false);
        btn_perfil.setContentAreaFilled(false);

        lb_fondo_inferior.setOpaque(true);
        lb_fondo_inferior.setBackground(Color.black);

        add(btn_menu);
        add(btn_historial);
        add(btn_perfil);
        add(lb_fondo_inferior);

        btn_menu.addActionListener(e -> {
            notificador.firePropertyChange("pihistorial_pimenu", null, null);
        });
        btn_perfil.addActionListener(e -> {
            notificador.firePropertyChange("pihistorial_piperfil", null, null);
        });
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    @Override
    public void actualizarDatos() {
        PasajeroDao pasajeroDao = new PasajeroDao();
        tbl_historial.setModel(pasajeroDao.getHistorial(pasajero.getUsuario()));
    }

    @Override
    public String getTipo() {
        return "pihistorial";
    }
}
