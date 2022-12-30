package vistas_pasajero;

import dao.PasajeroDao;
import dao.SolicitudViajeDao;
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

public class PIMenu extends JPanel implements Interfaz {

    private Dimension tamano;
    private Pasajero pasajero;
    private Viaje viaje;

    private JLabel lb_logo_go;
    private JLabel lb_logo_creditos;
    private JLabel lb_cantidad_creditos;

    private JLabel lb_tipo_vehiculo;
    private JLabel lb_vehiculo;
    private JButton btn_retroceder;
    private JButton btn_avanzar;
    private int auxiliar;

    private JLabel lb_origen;
    private JTextField txt_origen;
    private JLabel lb_sombra_origen;

    private JLabel lb_destino;
    private JTextField txt_destino;
    private JLabel lb_sombra_destino;

    private JLabel lb_monto;
    private JTextField txt_monto;
    private JLabel lb_sombra_monto;

    private JLabel lb_creditos;
    private JTextField txt_creditos;
    private JLabel lb_sombra_creditos;

    private JButton btn_solicitar;

    private JButton btn_menu;
    private JButton btn_historial;
    private JButton btn_perfil;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public PIMenu(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        viaje = new Viaje();
        init();
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(255, 160, 0));

        //superior
        lb_logo_go = new JLabel();
        lb_logo_creditos = new JLabel();
        lb_cantidad_creditos = new JLabel();
        auxiliar = 1;

        lb_tipo_vehiculo = new JLabel("ESTANDAR", SwingConstants.CENTER);
        lb_vehiculo = new JLabel();
        btn_retroceder = new JButton();
        btn_avanzar = new JButton();

        lb_logo_go.setBounds(tamano.width / 2 - 20, 10, 40, 40);
        lb_logo_creditos.setBounds(10, 10, 25, 25);
        lb_cantidad_creditos.setBounds(45, 10, 60, 25);

        lb_tipo_vehiculo.setBounds(tamano.width / 2 - 40, 100, 80, 25);
        lb_vehiculo.setBounds(tamano.width / 2 - 100, 125, 200, 200);
        btn_retroceder.setBounds(tamano.width / 2 - 160, 210, 30, 30);
        btn_avanzar.setBounds(tamano.width / 2 + 130, 210, 30, 30);

        BufferedImage bimg_logo_go = null;
        BufferedImage bimg_logo_creditos = null;
        BufferedImage bimg_vehiculo = null;
        BufferedImage bimg_retroceder = null;
        BufferedImage bimg_avanzar = null;

        Image img_logo_go;
        Image img_logo_creditos;
        Image img_vehiculo;
        Image img_retroceder;
        Image img_avanzar;

        ImageIcon icon_logo_go;
        ImageIcon icon_logo_creditos;
        ImageIcon icon_vehiculo;
        ImageIcon icon_retroceder;
        ImageIcon icon_avanzar;

        try {
            bimg_logo_go = ImageIO.read(new File("Imagenes/go.png"));
            bimg_logo_creditos = ImageIO.read(new File("Imagenes/creditos.png"));
            bimg_vehiculo = ImageIO.read(new File("Imagenes/estandar.png"));
            bimg_retroceder = ImageIO.read(new File("Imagenes/retroceder.png"));
            bimg_avanzar = ImageIO.read(new File("Imagenes/avanzar.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_logo_go = bimg_logo_go.getScaledInstance(lb_logo_go.getWidth(), lb_logo_go.getHeight(), Image.SCALE_SMOOTH);
        img_logo_creditos = bimg_logo_creditos.getScaledInstance(lb_logo_creditos.getWidth(), lb_logo_creditos.getHeight(), Image.SCALE_SMOOTH);
        img_vehiculo = bimg_vehiculo.getScaledInstance(lb_vehiculo.getWidth(), lb_vehiculo.getHeight(), Image.SCALE_SMOOTH);
        img_retroceder = bimg_retroceder.getScaledInstance(btn_retroceder.getWidth(), btn_retroceder.getHeight(), Image.SCALE_SMOOTH);
        img_avanzar = bimg_avanzar.getScaledInstance(btn_avanzar.getWidth(), btn_avanzar.getHeight(), Image.SCALE_SMOOTH);

        icon_logo_go = new ImageIcon(img_logo_go);
        icon_logo_creditos = new ImageIcon(img_logo_creditos);
        icon_vehiculo = new ImageIcon(img_vehiculo);
        icon_retroceder = new ImageIcon(img_retroceder);
        icon_avanzar = new ImageIcon(img_avanzar);

        lb_logo_go.setIcon(icon_logo_go);
        lb_logo_creditos.setIcon(icon_logo_creditos);
        lb_vehiculo.setIcon(icon_vehiculo);
        btn_retroceder.setIcon(icon_retroceder);
        btn_avanzar.setIcon(icon_avanzar);

        btn_retroceder.setBorder(null);
        btn_avanzar.setBorder(null);

        btn_retroceder.setContentAreaFilled(false);
        btn_avanzar.setContentAreaFilled(false);

        add(lb_logo_go);
        add(lb_logo_creditos);
        add(lb_cantidad_creditos);
        add(lb_tipo_vehiculo);
        add(lb_vehiculo);
        add(btn_retroceder);
        add(btn_avanzar);

        //medio
        lb_origen = new JLabel("Donde te encuentras?");
        txt_origen = new JTextField();
        lb_sombra_origen = new JLabel();

        lb_destino = new JLabel("A donde te diriges?");
        txt_destino = new JTextField();
        lb_sombra_destino = new JLabel();

        lb_monto = new JLabel("Monto a ofertar");
        txt_monto = new JTextField();
        lb_sombra_monto = new JLabel();

        lb_creditos = new JLabel("Usar creditos");
        txt_creditos = new JTextField();
        lb_sombra_creditos = new JLabel();

        lb_origen.setBounds(100, 375, 150, 25);
        txt_origen.setBounds(100, 400, 400, 25);
        lb_sombra_origen.setBounds(105, 405, 400, 25);

        lb_destino.setBounds(100, 460, 125, 25);
        txt_destino.setBounds(100, 485, 400, 25);
        lb_sombra_destino.setBounds(105, 490, 400, 25);

        lb_monto.setBounds(100, 545, 100, 25);
        txt_monto.setBounds(210, 545, 50, 25);
        lb_sombra_monto.setBounds(215, 550, 50, 25);

        lb_creditos.setBounds(100, 605, 100, 25);
        txt_creditos.setBounds(210, 605, 50, 25);
        lb_sombra_creditos.setBounds(215, 610, 50, 25);

        lb_sombra_origen.setOpaque(true);
        lb_sombra_destino.setOpaque(true);
        lb_sombra_monto.setOpaque(true);
        lb_sombra_creditos.setOpaque(true);
        lb_sombra_origen.setBackground(Color.gray);
        lb_sombra_destino.setBackground(Color.gray);
        lb_sombra_monto.setBackground(Color.gray);
        lb_sombra_creditos.setBackground(Color.gray);

        txt_origen.setBackground(Color.darkGray);
        txt_destino.setBackground(Color.darkGray);
        txt_monto.setBackground(Color.darkGray);
        txt_creditos.setBackground(Color.darkGray);
        txt_origen.setBorder(null);
        txt_destino.setBorder(null);
        txt_monto.setBorder(null);
        txt_creditos.setBorder(null);
        txt_origen.setForeground(Color.lightGray);
        txt_destino.setForeground(Color.lightGray);
        txt_monto.setForeground(Color.lightGray);
        txt_creditos.setForeground(Color.lightGray);

        txt_monto.setHorizontalAlignment(SwingConstants.CENTER);
        txt_creditos.setHorizontalAlignment(SwingConstants.CENTER);

        lb_creditos.setVisible(false);
        txt_creditos.setVisible(false);
        lb_sombra_creditos.setVisible(false);

        add(lb_origen);
        add(txt_origen);
        add(lb_sombra_origen);
        add(lb_destino);
        add(txt_destino);
        add(lb_sombra_destino);
        add(lb_monto);
        add(txt_monto);
        add(lb_sombra_monto);
        add(lb_creditos);
        add(txt_creditos);
        add(lb_sombra_creditos);

        //inferior
        btn_solicitar = new JButton();
        btn_menu = new JButton();
        btn_historial = new JButton();
        btn_perfil = new JButton();

        lb_fondo_inferior = new JLabel();

        btn_solicitar.setBounds(tamano.width / 2 - 43, 680, 85, 50);
        btn_menu.setBounds(tamano.width / 2 - 130, tamano.height - 85, 30, 30);
        btn_historial.setBounds(tamano.width / 2 - 15, tamano.height - 85, 30, 30);
        btn_perfil.setBounds(tamano.width / 2 + 85, tamano.height - 85, 30, 30);

        lb_fondo_inferior.setBounds(0, tamano.height - 100, tamano.width, 100);

        BufferedImage bimg_solicitar = null;
        BufferedImage bimg_menu = null;
        BufferedImage bimg_historial = null;
        BufferedImage bimg_perfil = null;

        Image img_solicitar;
        Image img_menu;
        Image img_historial;
        Image img_perfil;

        ImageIcon icon_solicitar;
        ImageIcon icon_menu;
        ImageIcon icon_historial;
        ImageIcon icon_perfil;

        try {
            bimg_solicitar = ImageIO.read(new File("Imagenes/solicitar.png"));
            bimg_menu = ImageIO.read(new File("Imagenes/menu.png"));
            bimg_historial = ImageIO.read(new File("Imagenes/historial.png"));
            bimg_perfil = ImageIO.read(new File("Imagenes/cuenta.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_solicitar = bimg_solicitar.getScaledInstance(btn_solicitar.getWidth(), btn_solicitar.getHeight(), Image.SCALE_SMOOTH);
        img_menu = bimg_menu.getScaledInstance(btn_menu.getWidth(), btn_menu.getHeight(), Image.SCALE_SMOOTH);
        img_historial = bimg_historial.getScaledInstance(btn_historial.getWidth(), btn_historial.getHeight(), Image.SCALE_SMOOTH);
        img_perfil = bimg_perfil.getScaledInstance(btn_perfil.getWidth(), btn_perfil.getHeight(), Image.SCALE_SMOOTH);

        icon_solicitar = new ImageIcon(img_solicitar);
        icon_menu = new ImageIcon(img_menu);
        icon_historial = new ImageIcon(img_historial);
        icon_perfil = new ImageIcon(img_perfil);

        btn_solicitar.setIcon(icon_solicitar);
        btn_menu.setIcon(icon_menu);
        btn_historial.setIcon(icon_historial);
        btn_perfil.setIcon(icon_perfil);

        btn_solicitar.setBorder(null);
        btn_menu.setBorder(null);
        btn_historial.setBorder(null);
        btn_perfil.setBorder(null);

        btn_solicitar.setContentAreaFilled(false);
        btn_menu.setContentAreaFilled(false);
        btn_historial.setContentAreaFilled(false);
        btn_perfil.setContentAreaFilled(false);

        lb_fondo_inferior.setOpaque(true);
        lb_fondo_inferior.setBackground(Color.black);

        add(btn_solicitar);
        add(btn_menu);
        add(btn_historial);
        add(btn_perfil);
        add(lb_fondo_inferior);

        btn_retroceder.addActionListener(e -> {
            auxiliar--;
            if (auxiliar == 0) auxiliar = 3;
            actualizarVehiculo();
        });
        btn_avanzar.addActionListener(e -> {
            auxiliar++;
            if (auxiliar == 4) auxiliar = 1;
            actualizarVehiculo();
        });

        btn_solicitar.addActionListener(e -> {
            SolicitudViajeDao solicitudViajeDao = new SolicitudViajeDao();

            String origen = txt_origen.getText().trim();
            String destino = txt_destino.getText().trim();
            String tipo = lb_tipo_vehiculo.getText();
            int monto = 0;
            int creditos = 0;

            if (origen.length() == 0 || destino.length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe rellenar los campos obligatorios");
                return;
            }
            try {
                monto = Integer.parseInt(txt_monto.getText());
                if (txt_creditos.getText().trim().length() != 0) {
                    creditos = Integer.parseInt(txt_creditos.getText());
                }
                if (monto <= 0 || creditos < 0) {
                    JOptionPane.showMessageDialog(null, "Monto inválido");
                    return;
                }
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un monto válido");
                return;
            }

            viaje.setUsuario(pasajero.getUsuario());
            viaje.setOrigen(origen);
            viaje.setDestino(destino);
            viaje.setTipo_vehiculo(tipo);
            viaje.setMonto(monto);
            viaje.setCreditos(creditos);

            try {
                solicitudViajeDao.insert(viaje);
            } catch (SQLException error) {
                String[] msg_error = error.getMessage().split("\n");
                JOptionPane.showMessageDialog(null, msg_error[0]);
                return;
            }

            notificador.firePropertyChange("pimenu_piviaje", null, null);
            limpiarCampos();
        });

        btn_historial.addActionListener(e -> {
            notificador.firePropertyChange("pimenu_pihistorial", null, null);
        });
        btn_perfil.addActionListener(e -> {
            notificador.firePropertyChange("pimenu_piperfil", null, null);
        });
    }

    /**
     * Actualiza la imagen y el texto del JLabel
     */
    private void actualizarVehiculo() {
        StringBuilder ubicacion = new StringBuilder();
        ubicacion.append("Imagenes/");

        switch (auxiliar) {
            case 1:
                ubicacion.append("estandar.png");
                lb_tipo_vehiculo.setText("ESTANDAR");
                break;
            case 2:
                ubicacion.append("comfort.png");
                lb_tipo_vehiculo.setText("COMFORT");
                break;
            case 3:
                ubicacion.append("premium.png");
                lb_tipo_vehiculo.setText("PREMIUM");
                break;
        }

        BufferedImage bimg_vehiculo = null;
        Image img_vehiculo;
        ImageIcon icon_vehiculo;

        try {
            bimg_vehiculo = ImageIO.read(new File(ubicacion.toString()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_vehiculo = bimg_vehiculo.getScaledInstance(lb_vehiculo.getWidth(), lb_vehiculo.getHeight(), Image.SCALE_SMOOTH);
        icon_vehiculo = new ImageIcon(img_vehiculo);

        lb_vehiculo.setIcon(icon_vehiculo);
    }

    private void limpiarCampos(){
        txt_origen.setText(null);
        txt_destino.setText(null);
        txt_monto.setText(null);
        txt_creditos.setText(null);
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    @Override
    public void actualizarDatos() {
        PasajeroDao pasajeroDao = new PasajeroDao();
        try {
            pasajeroDao.select(pasajero);
        } catch (Exception e) {
            String[] msg_error = e.getMessage().split("\n");
            JOptionPane.showMessageDialog(null, msg_error[0]);
            return;
        }
        lb_cantidad_creditos.setText(pasajero.getCreditos() + "");
        if (pasajero.getCreditos() != 0) {
            lb_creditos.setVisible(true);
            txt_creditos.setVisible(true);
            lb_sombra_creditos.setVisible(true);
        }
    }

    @Override
    public String getTipo() {
        return "pimenu";
    }
}
