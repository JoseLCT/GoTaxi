package vistas_conductor;

import dao.ConductorDao;
import modelos.Conductor;
import vistas_generales.Interfaz;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class CIHistorial extends JPanel implements Interfaz {

    private Dimension tamano;
    private Conductor conductor;

    private JLabel lb_historial;
    private JTable tbl_historial;
    private JScrollPane scroll;

    private JButton btn_menu;
    private JButton btn_historial;
    private JButton btn_vehiculo;
    private JButton btn_perfil;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public CIHistorial(Dimension tamano) {
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

        //superior & medio
        lb_historial = new JLabel("HISTORIAL", SwingConstants.CENTER);
        tbl_historial = new JTable();
        scroll = new JScrollPane(tbl_historial);

        lb_historial.setBounds(tamano.width / 2 - 150, 100, 300, 40);
        scroll.setBounds(50, 200, 500, 550);

        lb_historial.setFont(new Font("poppins", Font.PLAIN, 25));

        add(lb_historial);
        add(scroll);

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
            notificador.firePropertyChange("cihistorial_cimenu", null, null);
        });
        btn_vehiculo.addActionListener(e -> {
            notificador.firePropertyChange("cihistorial_civehiculo", null, null);
        });
        btn_perfil.addActionListener(e -> {
            notificador.firePropertyChange("cihistorial_ciperfil", null, null);
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
        tbl_historial.setModel(conductorDao.getHistorial(conductor.getUsuario()));
    }

    @Override
    public String getTipo() {
        return "cihistorial";
    }
}
