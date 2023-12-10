package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO implements DAO<Usuario, Integer>{
    
    private MotorSQL motorSql;
    
    public UsuarioDAO() {
        motorSql = new MotorSQL();
    }

    @Override
    public ArrayList<Usuario> findAll(Usuario entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ArrayList<Usuario> findAllUsers(Usuario entity) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS WHERE TIPO = 'usuario'";
        try {
            motorSql.connect();
            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Usuario usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOMBRE"),
                        rs.getString("EMAIL"),
                        rs.getString("CONTRASENA"),
                        rs.getString("TIPO")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return usuarios;
    }
    
    public ArrayList<Usuario> findAllStaff(Usuario entity) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS WHERE TIPO = 'staff'";
        try { 
            motorSql.connect();
            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Usuario usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOMBRE"),
                        rs.getString("EMAIL"),
                        rs.getString("CONTRASENA"),
                        rs.getString("TIPO")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return usuarios;
    }
    
    @Override
    public int add(Usuario entity) {
        int resp = 0;
        try {
            motorSql.connect();
            String sql = "INSERT INTO `usuario` (`Nombre`, `Email`, `Contrasena`, `Tipo`) VALUES " + "('"
                    + entity.getNombre() + "', "
                    + entity.getEmail() + "', "
                    + entity.getContrasena() + "', "
                    + entity.getTipo() + "');";
            resp = motorSql.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        if (resp > 0) {
            System.out.println("Usuario insertado con exito.");
        }
        return resp;
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Usuario entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
