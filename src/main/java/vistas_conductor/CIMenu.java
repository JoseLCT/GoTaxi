package vistas_conductor;

import conexion.Conexion;
import dao.ConductorDao;
import dao.PasajeroDao;
import dao.SolicitudViajeDao;
import dao.ViajeDao;
import modelos.Conductor;
import modelos.Pasajero;
import modelos.Vehiculo;
import modelos.Viaje;
import vistas_generales.Interfaz;
import vistas_generales.Ventana;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.sql.SQLException;

public class CIMenu extends JPanel implements Interfaz {

    private Conexion conexion;

    private Dimension tamano;
    private Conductor conductor;
    private Vehiculo vehiculo;

    private JLabel lb_logo_saldo;
    private JLabel lb_cantidad_saldo;

    private JLabel lb_viajes_disponibles;

    private JTable tbl_solicitudes;
    private JScrollPane scroll;

    private JButton btn_menu;
    private JButton btn_historial;
    private JButton btn_vehiculo;
    private JButton btn_perfil;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public CIMenu(Dimension tamano) {
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
        lb_logo_saldo = new JLabel();
        lb_cantidad_saldo = new JLabel();
        lb_viajes_disponibles = new JLabel("VIAJES DISPONIBLES", SwingConstants.CENTER);

        lb_logo_saldo.setBounds(10, 10, 25, 25);
        lb_cantidad_saldo.setBounds(45, 10, 60, 25);
        lb_viajes_disponibles.setBounds(tamano.width / 2 - 150, 100, 300, 40);

        BufferedImage bimg_logo_saldo = null;
        Image img_logo_saldo;
        ImageIcon icon_logo_saldo;

        try {
            bimg_logo_saldo = ImageIO.read(new File("Imagenes/saldo.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_logo_saldo = bimg_logo_saldo.getScaledInstance(lb_logo_saldo.getWidth(), lb_logo_saldo.getHeight(), Image.SCALE_SMOOTH);
        icon_logo_saldo = new ImageIcon(img_logo_saldo);
        lb_logo_saldo.setIcon(icon_logo_saldo);

        lb_viajes_disponibles.setFont(new Font("poppins", Font.PLAIN, 25));

        add(lb_logo_saldo);
        add(lb_cantidad_saldo);
        add(lb_viajes_disponibles);

        //tabla
        tbl_solicitudes = new JTable();
        scroll = new JScrollPane(tbl_solicitudes);

        scroll.setBounds(50, 200, 500, 550);

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

        btn_historial.addActionListener(e -> {
            notificador.firePropertyChange("cimenu_cihistorial", null, null);
        });
        btn_vehiculo.addActionListener(e -> {
            notificador.firePropertyChange("cimenu_civehiculo", null, null);
        });
        btn_perfil.addActionListener(e -> {
            notificador.firePropertyChange("cimenu_ciperfil", null, null);
        });
        tbl_solicitudes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViajeDao viajeDao = new ViajeDao();
                Pasajero pasajero = new Pasajero();

                int fila = tbl_solicitudes.getSelectedRow();
                pasajero.setUsuario(tbl_solicitudes.getValueAt(fila, 3).toString());

                try {
                    viajeDao.insert(conductor, pasajero);
                } catch (SQLException error) {
                    String[] msg_error = error.getMessage().split("\n");
                    JOptionPane.showMessageDialog(null, msg_error[0]);
                    return;
                }

                notificador.firePropertyChange("cimenu_civiaje", null, null);
            }
        });
    }

    public void escucharSolicitudes() {
        conexion.changeListener("solicitud_viaje", this);
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
        ConductorDao conductorDao = new ConductorDao();

        try {
            conductorDao.select(conductor);
        } catch (Exception e) {
            String[] msg_error = e.getMessage().split("\n");
            JOptionPane.showMessageDialog(null, msg_error[0]);
            return;
        }

        lb_cantidad_saldo.setText(conductor.getSaldo() + "");

        SolicitudViajeDao solicitudViajeDao = new SolicitudViajeDao();
        tbl_solicitudes.setModel(solicitudViajeDao.getSolicitudesDisponbles(vehiculo));
    }

    @Override
    public String getTipo() {
        return "cimenu";
    }
}
