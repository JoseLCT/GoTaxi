package dao;

import conexion.Conexion;
import modelos.Pasajero;
import modelos.Vehiculo;
import modelos.Viaje;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SolicitudViajeDao {

    private Connection connection;

    public SolicitudViajeDao() {
        connection = Conexion.conectar().getConnection();
    }

    public DefaultTableModel getSolicitudesDisponbles(Vehiculo vehiculo) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM get_solicitudes('" + vehiculo.getTipo() + "')");
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnas = rsmd.getColumnCount();

            modelo.addColumn("MONTO");
            modelo.addColumn("ORIGEN");
            modelo.addColumn("DESTINO");
            modelo.addColumn("PASAJERO");
            modelo.addColumn("VALORACION PROMEDIO");

            while (rs.next()) {
                Object[] fila = new Object[columnas];

                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                modelo.addRow(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public boolean solicitudAceptada(Pasajero p) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT solicitud_aceptada('" + p.getUsuario() + "')");
            while (rs.next()) {
                if (rs.getBoolean(1)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(Viaje v) throws SQLException {
        String usuario = v.getUsuario();
        String origen = v.getOrigen();
        String destino = v.getDestino();
        String tipo_vehiculo = v.getTipo_vehiculo();
        int monto = v.getMonto();
        int creditos = v.getCreditos();

        CallableStatement stmt = connection.prepareCall("CALL mk_solicitud_viaje('" + usuario + "', '" + origen + "', '" + destino + "','" + tipo_vehiculo + "'," + monto + "," + creditos + ")");
        stmt.execute();
    }

    public void delete(Pasajero p) throws SQLException {
        CallableStatement stmt = connection.prepareCall("CALL dlt_solicitud_viaje('" + p.getUsuario() + "')");
        stmt.execute();
    }
}
