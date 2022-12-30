package vistas_generales;

import conexion.Conexion;
import dao.ConductorDao;
import dao.PasajeroDao;
import dao.VehiculoDao;
import modelos.Conductor;
import modelos.Pasajero;

import modelos.Vehiculo;
import modelos.Viaje;
import org.json.JSONObject;
import vistas_conductor.*;
import vistas_pasajero.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Ventana extends JFrame implements PropertyChangeListener {

    private Pasajero pasajero;
    private Conductor conductor;
    private Vehiculo vehiculo;
    private Viaje viaje;

    private Login login;
    private RegistroUsuario registroUsuario;
    private RegistroDatos registroDatos;

    private PIMenu piMenu;
    private PIViaje piViaje;
    private PIHistorial piHistorial;
    private PIPerfil piPerfil;

    private CIRegistro ciRegistro;
    private CIMenu ciMenu;
    private CIViaje ciViaje;
    private CIHistorial ciHistorial;
    private CIVehiculo ciVehiculo;
    private CIPerfil ciPerfil;

    public Ventana() {
        init();
    }

    private void init() {
        Thread hilo = new Thread(Conexion.conectar());
        hilo.start();


        setLayout(new BorderLayout());
        setSize(600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pasajero = new Pasajero();
        conductor = new Conductor();
        vehiculo = new Vehiculo();
        viaje = new Viaje();

        login = new Login(getSize());
        registroUsuario = new RegistroUsuario(getSize());
        registroDatos = new RegistroDatos(getSize());

        piMenu = new PIMenu(getSize());
        piViaje = new PIViaje(getSize());
        piHistorial = new PIHistorial(getSize());
        piPerfil = new PIPerfil(getSize());

        ciRegistro = new CIRegistro(getSize());
        ciMenu = new CIMenu(getSize());
        ciViaje = new CIViaje(getSize());
        ciHistorial = new CIHistorial(getSize());
        ciVehiculo = new CIVehiculo(getSize());
        ciPerfil = new CIPerfil(getSize());

        login.addObserver(this);
        registroUsuario.addObserver(this);
        registroDatos.addObserver(this);

        piMenu.addObserver(this);
        piViaje.addObserver(this);
        piHistorial.addObserver(this);
        piPerfil.addObserver(this);

        ciRegistro.addObserver(this);
        ciMenu.addObserver(this);
        ciViaje.addObserver(this);
        ciHistorial.addObserver(this);
        ciVehiculo.addObserver(this);
        ciPerfil.addObserver(this);

        piMenu.setPasajero(pasajero);
        piMenu.setViaje(viaje);

        piViaje.setViaje(viaje);
        piViaje.setPasajero(pasajero);

        piHistorial.setPasajero(pasajero);
        piPerfil.setPasajero(pasajero);

        ciRegistro.setPasajero(pasajero);
        ciRegistro.setConductor(conductor);
        ciRegistro.setVehiculo(vehiculo);

        ciMenu.setConductor(conductor);
        ciMenu.setVehiculo(vehiculo);

        ciViaje.setConductor(conductor);
        ciHistorial.setConductor(conductor);
        ciVehiculo.setVehiculo(vehiculo);
        ciPerfil.setConductor(conductor);

        ciRegistro.addObserver(this);
        ciMenu.addObserver(this);
        ciHistorial.addObserver(this);
        ciVehiculo.addObserver(this);
        ciPerfil.addObserver(this);

        ciMenu.addObserver(this);
        ciHistorial.addObserver(this);
        ciVehiculo.addObserver(this);
        ciPerfil.addObserver(this);

        add(login);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String tipo = evt.getPropertyName();
        switch (tipo) {
            case "login_pimenu":
                login.setVisible(false);
                add(piMenu);
                piMenu.setVisible(true);

                iniciarPasajero(evt.getNewValue().toString());
                piMenu.actualizarDatos();
                break;
            case "login_cimenu":
                login.setVisible(false);
                add(ciMenu);
                ciMenu.setVisible(true);

                iniciarConductor(evt.getNewValue().toString());
                iniciarVehiculo();

                ciMenu.actualizarDatos();
                ciMenu.escucharSolicitudes();
                break;
            case "login_rusuario":
                login.setVisible(false);
                add(registroUsuario);
                registroUsuario.setVisible(true);
                break;
            case "rusuario_login":
                //cierra el panel registroUsuario y vuelve al login
                registroUsuario.setVisible(false);
                login.setVisible(true);
                remove(registroUsuario);
                break;
            case "rusuario_rdatos":
                JSONObject datos = new JSONObject(evt.getNewValue().toString());
                registroDatos.setUsuario(datos.getString("usuario"));
                registroDatos.setContrasena(datos.getString("contrasena"));

                registroUsuario.setVisible(false);
                add(registroDatos);
                registroDatos.setVisible(true);
                remove(registroUsuario);
                break;
            case "rdatos_rusuario":
                //cierra el panel registroDatos y vuelve a registroUsuario
                registroDatos.setVisible(false);
                add(registroUsuario);
                registroUsuario.setVisible(true);
                remove(registroDatos);
                break;
            case "rdatos_login":
                //el registro fue exitoso, se cierra el panel registroDatps y vuelve a login
                registroDatos.setVisible(false);
                login.setVisible(true);
                remove(registroDatos);
                break;
            case "pimenu_piviaje":
                piMenu.setVisible(false);
                add(piViaje);
                piViaje.setVisible(true);
                remove(piMenu);

                piViaje.escucharViaje();
                piViaje.actualizarSolicitud();
                break;
            case "pimenu_pihistorial":
                piMenu.setVisible(false);
                add(piHistorial);
                piHistorial.setVisible(true);
                remove(piMenu);

                piHistorial.actualizarDatos();
                break;
            case "pimenu_piperfil":
                piMenu.setVisible(false);
                add(piPerfil);
                piPerfil.setVisible(true);
                remove(piMenu);

                piPerfil.actualizarDatos();
                break;
            case "piviaje_pimenu":
                piViaje.setVisible(false);
                add(piMenu);
                piMenu.setVisible(true);
                remove(piViaje);

                piMenu.actualizarDatos();
                break;
            case "pihistorial_pimenu":
                piHistorial.setVisible(false);
                add(piMenu);
                piMenu.setVisible(true);
                remove(piHistorial);

                piMenu.actualizarDatos();
                break;
            case "pihistorial_piperfil":
                piHistorial.setVisible(false);
                add(piPerfil);
                piPerfil.setVisible(true);
                remove(piHistorial);

                piPerfil.actualizarDatos();
                break;
            case "piperfil_ciperfil":
                piPerfil.setVisible(false);
                add(ciPerfil);
                ciPerfil.setVisible(true);
                remove(piPerfil);

                iniciarConductor(pasajero.getUsuario());
                iniciarVehiculo();
                ciPerfil.actualizarDatos();
                break;
            case "piperfil_ciregistro":
                piPerfil.setVisible(false);
                add(ciRegistro);
                ciRegistro.setVisible(true);
                remove(piPerfil);
                break;
            case "piperfil_pimenu":
                piPerfil.setVisible(false);
                add(piMenu);
                piMenu.setVisible(true);
                remove(piPerfil);

                piMenu.actualizarDatos();
                break;
            case "piperfil_pihistorial":
                piPerfil.setVisible(false);
                add(piHistorial);
                piHistorial.setVisible(true);
                remove(piPerfil);

                piHistorial.actualizarDatos();
                break;
            case "ciregistro_piperfil":
                ciRegistro.setVisible(false);
                add(piPerfil);
                piPerfil.setVisible(true);
                remove(ciRegistro);
                break;
            case "ciregistro_cimenu":
                ciRegistro.setVisible(false);
                add(ciMenu);
                ciMenu.setVisible(true);
                remove(ciRegistro);

                iniciarConductor(pasajero.getUsuario());
                iniciarVehiculo();
                ciMenu.actualizarDatos();
                ciMenu.escucharSolicitudes();
                break;
            case "cimenu_civiaje":
                ciMenu.setVisible(false);
                add(ciViaje);
                ciViaje.setVisible(true);
                remove(ciMenu);

                ciViaje.actualizarDatos();
                break;
            case "cimenu_cihistorial":
                ciMenu.setVisible(false);
                add(ciHistorial);
                ciHistorial.setVisible(true);
                remove(ciMenu);

                ciHistorial.actualizarDatos();
                break;
            case "cimenu_civehiculo":
                ciMenu.setVisible(false);
                add(ciVehiculo);
                ciVehiculo.setVisible(true);
                remove(ciMenu);

                ciVehiculo.actualizarDatos();
                break;
            case "cimenu_ciperfil":
                ciMenu.setVisible(false);
                add(ciPerfil);
                ciPerfil.setVisible(true);
                remove(ciMenu);

                ciPerfil.actualizarDatos();
                break;
            case "civiaje_cimenu":
                ciViaje.setVisible(false);
                add(ciMenu);
                ciMenu.setVisible(true);
                remove(ciViaje);

                ciMenu.escucharSolicitudes();
                ciMenu.actualizarDatos();
                break;
            case "cihistorial_cimenu":
                ciHistorial.setVisible(false);
                add(ciMenu);
                ciMenu.setVisible(true);
                remove(ciHistorial);

                ciMenu.actualizarDatos();
                break;
            case "cihistorial_civehiculo":
                ciHistorial.setVisible(false);
                add(ciVehiculo);
                ciVehiculo.setVisible(true);
                remove(ciHistorial);

                ciVehiculo.actualizarDatos();
                break;
            case "cihistorial_ciperfil":
                ciHistorial.setVisible(false);
                add(ciPerfil);
                ciPerfil.setVisible(true);
                remove(ciHistorial);

                ciPerfil.actualizarDatos();
                break;
            case "civehiculo_cimenu":
                ciVehiculo.setVisible(false);
                add(ciMenu);
                ciMenu.setVisible(true);
                remove(ciVehiculo);

                ciMenu.actualizarDatos();
                break;
            case "civehiculo_cihistorial":
                ciVehiculo.setVisible(false);
                add(ciHistorial);
                ciHistorial.setVisible(true);
                remove(ciVehiculo);

                ciHistorial.actualizarDatos();
                break;
            case "civehiculo_ciperfil":
                ciVehiculo.setVisible(false);
                add(ciPerfil);
                ciPerfil.setVisible(true);
                remove(ciVehiculo);

                ciPerfil.actualizarDatos();
                break;
            case "ciperfil_piperfil":
                ciPerfil.setVisible(false);
                add(piPerfil);
                piPerfil.setVisible(true);
                remove(ciPerfil);

                iniciarPasajero(conductor.getUsuario());
                piPerfil.actualizarDatos();
                break;
            case "ciperfil_cimenu":
                ciPerfil.setVisible(false);
                add(ciMenu);
                ciMenu.setVisible(true);
                remove(ciPerfil);

                ciMenu.actualizarDatos();
                break;
            case "ciperfil_cihistorial":
                ciPerfil.setVisible(false);
                add(ciHistorial);
                ciHistorial.setVisible(true);
                remove(ciPerfil);

                ciHistorial.actualizarDatos();
                break;
            case "ciperfil_civehiculo":
                ciPerfil.setVisible(false);
                add(ciVehiculo);
                ciVehiculo.setVisible(true);
                remove(ciPerfil);

                ciVehiculo.actualizarDatos();
                break;
        }
    }

    private void iniciarPasajero(String usuario){
        PasajeroDao pasajeroDao = new PasajeroDao();
        try {
            pasajero.setUsuario(usuario);
            pasajeroDao.select(pasajero);
        } catch (Exception e) {
            String[] msg_error = e.getMessage().split("\n");
            JOptionPane.showMessageDialog(null, msg_error[0]);
        }
    }

    private void iniciarConductor(String usuario){
        ConductorDao conductorDao = new ConductorDao();
        try {
            conductor.setUsuario(usuario);
            conductorDao.select(conductor);
        } catch (Exception e) {
            String[] msg_error = e.getMessage().split("\n");
            JOptionPane.showMessageDialog(null, msg_error[0]);
        }
    }

    private void iniciarVehiculo(){
        VehiculoDao vehiculoDao = new VehiculoDao();
        try {
            vehiculoDao.select(conductor, vehiculo);
        } catch (Exception e) {
            String[] msg_error = e.getMessage().split("\n");
            JOptionPane.showMessageDialog(null, msg_error[0]);
        }
    }
}









