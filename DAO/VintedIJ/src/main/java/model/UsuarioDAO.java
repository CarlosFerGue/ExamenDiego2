package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO implements DAO<Usuario, Integer> {

    private MotorSQL motorSql;

    public UsuarioDAO() {
        motorSql = new MotorSQL();
    }

    @Override
    public ArrayList<Usuario> findAll(Usuario entity) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try {
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("apellido_1"),
                        rs.getString("apellido_2"),
                        rs.getString("usuario")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return usuarios;
    }


    public ArrayList<Usuario> login(Usuario entity) throws SQLException {
        ArrayList<Usuario> usuariosArray = new ArrayList<>();
        String sql = "SELECT id_usuario FROM usuario\n" +
                "WHERE email = '" + entity.getEmail() + "' AND password = '" + entity.getContrasena() + "';";
        try {
            motorSql.conectar();
            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario")
                );
                usuariosArray.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return usuariosArray;
    }

    @Override
    public int add(Usuario entity) throws SQLException {
        int resp = 0;
        try {
            motorSql.conectar();
            String sql = "INSERT INTO usuario VALUES" + "("
                    + entity.getId() + ", '"
                    + entity.getNombre() + "', '"
                    + entity.getEmail() + "', '"
                    + entity.getContrasena() + "', '"
                    + entity.getApellido1() + "', '"
                    + entity.getApellido2() + "', '"
                    + entity.getUser() + "')";
            resp = motorSql.modificar(sql);
        } catch (Exception e) {
            System.out.println("No se inserto con exito");
        } finally {
            motorSql.desconectar();
        }
        if (resp > 0) {
            System.out.println("Usuario insertado con exito.");
        }
        return resp;
    }

    public ArrayList<Usuario> find10(Usuario entity) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM usuario\n" +
                "ORDER BY numero_ventas DESC\n" +
                "LIMIT 10;\n";
        try {
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("apellido_1"),
                        rs.getString("apellido_2"),
                        rs.getString("usuario")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return usuarios;
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
