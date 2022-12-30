package dao;

import conexion.Conexion;
import modelos.Pasajero;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;

public class PasajeroDao {

    private Connection connection;

    public PasajeroDao() {
        connection = Conexion.conectar().getConnection();
    }

    public double valoracionPromedio(Pasajero p) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT get_valoracion_pasajero('" + p.getUsuario() + "')");
        while (rs.next()) {
            return rs.getDouble(1);
        }
        return 0;
    }


    public DefaultTableModel getHistorial(String usuario) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM historial_pasajero('" + usuario + "')");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnas = resultSetMetaData.getColumnCount();

            modelo.addColumn("VIAJE");
            modelo.addColumn("ORIGEN");
            modelo.addColumn("DESTINO");
            modelo.addColumn("CONDUCTOR");
            modelo.addColumn("VEHICULO");
            modelo.addColumn("PLACA");
            modelo.addColumn("MONTO");
            modelo.addColumn("CREDITOS UTILIZADOS");

            while (resultSet.next()) {
                Object[] fila = new Object[columnas];

                for (int i = 0; i < columnas; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insert(Pasajero pasajero, String contrasena) throws SQLException {
        SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");

        String usuario = pasajero.getUsuario();
        String ci = pasajero.getCi();
        String email = pasajero.getEmail();
        String telefono = pasajero.getTelefono();
        String nombre = pasajero.getNombre();
        String appaterno = pasajero.getAppaterno();
        String apmaterno = pasajero.getApmaterno();
        String fecha_nacimiento = formato_fecha.format(pasajero.getFecha_nacimiento());

        CallableStatement stmt = connection.prepareCall("CALL mk_pasajero('" + usuario + "', '" + contrasena + "', '" + ci + "','" + email + "','" + telefono + "','" + nombre + "','" + appaterno + "','" + apmaterno + "','" + fecha_nacimiento + "')");
        stmt.execute();
    }


    public void select(Pasajero pasajero) throws Exception {
        SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM get_datos_pasajero('" + pasajero.getUsuario() + "')");
        ResultSetMetaData resultSetMetaData = rs.getMetaData();

        int columnas = resultSetMetaData.getColumnCount();

        while (rs.next()) {
            Object[] fila = new Object[columnas];

            for (int i = 0; i < columnas; i++) {
                fila[i] = rs.getObject(i + 1);
            }

            pasajero.setCi(fila[0].toString());
            pasajero.setEmail(fila[1].toString());
            pasajero.setTelefono(fila[2].toString());
            pasajero.setNombre(fila[3].toString());
            pasajero.setAppaterno(fila[4].toString());
            pasajero.setApmaterno(fila[5].toString());
            pasajero.setFecha_nacimiento(formato_fecha.parse(fila[6].toString()));
            pasajero.setCreditos(Integer.parseInt(fila[7].toString()));
        }
    }
}
