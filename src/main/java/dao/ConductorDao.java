package dao;

import conexion.Conexion;
import modelos.Conductor;
import modelos.Vehiculo;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ConductorDao {

    private Connection connection;

    public ConductorDao() {
        connection = Conexion.conectar().getConnection();
    }

    public boolean esConductor(String usuario) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT es_conductor('" + usuario + "')");
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

    public boolean sesionActiva(String usuario) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT conductor_activo('" + usuario + "')");
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

    public void iniciarSesion(String usuario) {
        try {
            CallableStatement stmt = connection.prepareCall("CALL iniciar_sesion_conductor('" + usuario + "')");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarSesion(String usuario) {
        try {
            CallableStatement stmt = connection.prepareCall("CALL cerrar_sesion_conductor('" + usuario + "')");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getHistorial(String usuario) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            Connection connection = Conexion.conectar().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM historial_conductor('" + usuario + "')");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnas = resultSetMetaData.getColumnCount();

            modelo.addColumn("VIAJE");
            modelo.addColumn("ORIGEN");
            modelo.addColumn("DESTINO");
            modelo.addColumn("PASAJERO");
            modelo.addColumn("MONTO");

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

    public double valoracionPromedio(Conductor conductor) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT get_valoracion_conductor('" + conductor.getUsuario() + "')");
        while (rs.next()) {
            return rs.getDouble(1);
        }
        return 0;
    }

    public void select(Conductor conductor) throws Exception {
        SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM get_datos_conductor('" + conductor.getUsuario() + "')");
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnas = rsmd.getColumnCount();

        while (rs.next()) {
            Object[] fila = new Object[columnas];

            for (int i = 0; i < columnas; i++) {
                fila[i] = rs.getObject(i + 1);
            }

            conductor.setCi(fila[0].toString());
            conductor.setEmail(fila[1].toString());
            conductor.setTelefono(fila[2].toString());
            conductor.setNombre(fila[3].toString());
            conductor.setAppaterno(fila[4].toString());
            conductor.setApmaterno(fila[5].toString());
            conductor.setFecha_nacimiento(formato_fecha.parse(fila[6].toString()));
            conductor.setSaldo(Integer.parseInt(fila[7].toString()));
        }
    }

    public void insert(Conductor conductor, Vehiculo vehiculo) throws SQLException {
        Conexion conexion = Conexion.conectar();
        Connection connection = conexion.getConnection();

        String usuario = conductor.getUsuario();
        String placa = vehiculo.getPlaca();
        String modelo = vehiculo.getModelo();
        String color = vehiculo.getColor();
        String tipo = vehiculo.getTipo();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CALL mk_conductor('" + usuario + "', '" + placa + "', '" + modelo + "','" + color + "','" + tipo + "')");
    }
}
