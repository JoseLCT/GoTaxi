package dao;

import conexion.Conexion;
import modelos.Conductor;
import modelos.Vehiculo;

import java.sql.*;

public class VehiculoDao {

    private Connection connection;

    public VehiculoDao(){
        connection = Conexion.conectar().getConnection();
    }

    public boolean placaDisponible(String placa) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT placa_disponible('" + placa + "')");
            while (rs.next()) {
                if (rs.getBoolean(1)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void select(Conductor conductor, Vehiculo vehiculo) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM get_datos_vehiculo('" + conductor.getUsuario() + "')");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnas = resultSetMetaData.getColumnCount();

        while (resultSet.next()) {
            Object[] fila = new Object[columnas];

            for (int i = 0; i < columnas; i++) {
                fila[i] = resultSet.getObject(i + 1);
            }

            vehiculo.setPlaca(fila[0].toString());
            vehiculo.setModelo(fila[1].toString());
            vehiculo.setColor(fila[2].toString());
            vehiculo.setTipo(fila[3].toString());

        }
    }
}
