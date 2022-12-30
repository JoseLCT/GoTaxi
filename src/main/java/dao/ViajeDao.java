package dao;

import conexion.Conexion;
import modelos.Conductor;
import modelos.Pasajero;
import modelos.Usuario;
import modelos.Viaje;

import java.sql.*;

public class ViajeDao {

    private Connection connection;

    public ViajeDao() {
        connection = Conexion.conectar().getConnection();
    }

    public void finalizarViaje(Conductor c, int valoracion) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CALL finalizar_viaje('" + c.getUsuario() + "', " + valoracion + ")");
    }

    public void valorarConductor(Pasajero p, int valoracion) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CALL ins_valoracion('" + p.getUsuario() + "', " + valoracion + ")");
    }

    public void insert(Conductor conductor, Pasajero pasajero) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CALL mk_viaje('" + conductor.getUsuario() + "', '" + pasajero.getUsuario() + "')");
    }

    public void select(Viaje v, Usuario u, Pasajero p, Conductor c) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM get_datos_viaje('" + u.getUsuario() + "')");
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];

                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                v.setOrigen(fila[0].toString());
                v.setDestino(fila[1].toString());
                v.setMonto(Integer.parseInt(fila[2].toString()));
                c.setUsuario(fila[3].toString());
                p.setUsuario(fila[4].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
