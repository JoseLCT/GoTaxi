package conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import vistas_generales.Interfaz;

public class Conexion implements Runnable {

    private String url;
    private String username;
    private String password;
    private Connection connection;

    private Interfaz interfaz;
    private static Conexion singleton;
    private String listener;

    private Conexion() {
        url = "jdbc:postgresql://localhost:5432/gotaxi";
        username = "postgres";
        password = "postgres";
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("conectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Conexion conectar() {
        if (singleton == null) {
            singleton = new Conexion();
        }
        return singleton;
    }

    public Connection getConnection() {
        return connection;
    }

    public void changeListener(String tabla, Interfaz interfaz) {
        try {
            Statement statement = connection.createStatement();
            if (listener != null) {
                statement.execute("UNLISTEN " + listener);
            }
            statement.execute("LISTEN " + tabla);
            listener = tabla;
            this.interfaz = interfaz;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                rs.close();
                stmt.close();

                PGNotification notifications[] = ((PGConnection) connection).getNotifications();

                if (notifications != null && interfaz != null) {

                    for (PGNotification notification : notifications) {
                        String tipo_notificacion = notification.getParameter();

                        if (tipo_notificacion.equals("nueva_solicitud") && interfaz.getTipo().equals("cimenu")) {
                            interfaz.actualizarDatos();
                        } else if (tipo_notificacion.equals("solicitud_eliminada") && interfaz.getTipo().equals("cimenu")) {
                            interfaz.actualizarDatos();
                        } else if (tipo_notificacion.equals("nuevo_viaje") && interfaz.getTipo().equals("piviaje")) {
                            interfaz.actualizarDatos();
                        } else if (tipo_notificacion.equals("viaje_finalizado") && interfaz.getTipo().equals("piviaje")) {
                            interfaz.actualizarDatos();
                        }
                    }
                }
                Thread.sleep(500);
            } catch (SQLException | InterruptedException sqle) {
                System.out.println("ERROR");
            }
        }
    }

    public String getListener() {
        return listener;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }
}
