package dao;

import conexion.Conexion;
import modelos.Usuario;

import java.sql.*;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        connection = Conexion.conectar().getConnection();
    }

    public boolean iniciarSesion(String usuario, String pass) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT iniciar_sesion('" + usuario + "', '" + pass + "')");
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

    public boolean usuarioDisponible(String usuario) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usuario_disponible('" + usuario + "')");
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

    public void actualizarDatos(Usuario datos, String usuario_anterior) throws SQLException {
        String usuario = datos.getUsuario();
        String email = datos.getEmail();
        String telefono = datos.getTelefono();
        String nombre = datos.getNombre();
        String appaterno = datos.getAppaterno();
        String apmaterno = datos.getApmaterno();

        CallableStatement stmt = connection.prepareCall("CALL actualizar_datos('" + usuario_anterior + "', '" + usuario + "', '" + email + "', '" + telefono + "', '" + nombre + "', '" + appaterno + "', '" + apmaterno + "')");
        stmt.execute();
    }
}
