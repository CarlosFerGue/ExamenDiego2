package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticuloDAO implements DAO<Articulo, Integer> {
    private MotorSQL motorSql;

    public ArticuloDAO() {
        motorSql = new MotorSQL();
    }


    public ArrayList<Articulo> findAllProductos(String id_usuario) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos WHERE id_usuario <> '" + id_usuario + "'";
        int id_usuarioInt = Integer.parseInt(id_usuario);

        try {
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Articulo articulo = new Articulo(
                        id_usuarioInt,
                        rs.getInt("id_producto"),
                        rs.getString("marca_producto"),
                        rs.getString("estado"),
                        rs.getString("fecha_subida_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("imagen_producto"),
                        rs.getString("precio_producto"),
                        rs.getString("valoracion")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }

    public ArrayList<Articulo> historico(String id_usuario) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos WHERE id_usuario_comprado = '" + id_usuario + "'";
        int id_usuarioInt = Integer.parseInt(id_usuario);

        try {
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Articulo articulo = new Articulo(
                        id_usuarioInt,
                        rs.getInt("id_producto"),
                        rs.getString("marca_producto"),
                        rs.getString("estado"),
                        rs.getString("fecha_subida_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("imagen_producto"),
                        rs.getString("precio_producto"),
                        rs.getString("valoracion")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }


    public ArrayList<Articulo> estado(String estado, int id_usuario) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos WHERE estado"+ estado +"' AND id_usuario <> '" + id_usuario + "';";


        try {
            motorSql.conectar();
            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Articulo articulo = new Articulo(
                        rs.getInt("id_producto"),
                        rs.getString("marca_producto"),
                        rs.getString("estado"),
                        rs.getString("fecha_subida_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("imagen_producto"),
                        rs.getString("precio_producto"),
                        rs.getString("valoracion")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }

    public ArrayList<Articulo> palabra(String palabra, int id_usuario) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
//        String sql = "SELECT * FROM articulos WHERE estado"+ palabra +"' AND id_usuario <> '" + id_usuario + "';";
        String sql = "SELECT * FROM articulos WHERE id_usuario <> '" + id_usuario + "' AND nombre_producto LIKE '%" + palabra + "%';";
//        SELECT * FROM articulos WHERE id_usuario = 1 AND nombre_producto LIKE '%aba%';

        try {
            motorSql.conectar();
            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Articulo articulo = new Articulo(
                        rs.getInt("id_producto"),
                        rs.getString("marca_producto"),
                        rs.getString("estado"),
                        rs.getString("fecha_subida_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("imagen_producto"),
                        rs.getString("precio_producto"),
                        rs.getString("valoracion")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }

    //Update del comprador
    public int comprar(Articulo entity) throws SQLException {
        int resp = 0;
        try {
            motorSql.conectar();
            String sql = "UPDATE `articulos` SET `estado` = 'NoActive', id_usuario_comprado = '" + entity.getId_comprador() + "' " + "WHERE `id_producto` = '" + entity.getId_producto() + "';";
            resp = motorSql.modificar(sql);
            System.out.println(sql);

        } catch (Exception e) {

            System.out.println("No se compro con exito");
        } finally {
            motorSql.desconectar();
        }
        if (resp > 0) {
            System.out.println("Compra realizada con exito.");
        }
        return resp;
    }


    //Update de la valoracion
    @Override
    public int update(Articulo entity) throws SQLException {
        int resp = 0;
        try {
            motorSql.conectar();
            String sql = "UPDATE `articulos` SET `valoracion` = '" + entity.getValoracion() + "' " + "WHERE `id_producto` = '" + entity.getId_producto() + "';";
            resp = motorSql.modificar(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println("No se actualizo con exito");
        } finally {
            motorSql.desconectar();
        }
        if (resp > 0) {
            System.out.println("Valoracion actualizada con exito.");
        }
        return resp;
    }

    @Override
    public ArrayList<Articulo> findAll(Articulo entity) throws SQLException {
        return null;
    }

    @Override
    public int add(Articulo entity) throws SQLException {
        int resp = 0;
        try {
            motorSql.conectar();
            String sql = "INSERT INTO `articulos`(`marca_producto`, `precio_producto`, `imagen_producto`, `nombre_producto`, `descripcion_producto`, `fecha_subida_producto`, `estado`, `id_usuario`) VALUES ('"
                    + entity.getMarca() + "', '"
                    + entity.getPrecio() + "', '"
                    + entity.getImagen() + "', '"
                    + entity.getNombre() + "', '"
                    + entity.getDescripcion() + "', '"
                    + entity.getFecha() + "', '"
                    + "Activo" + "', '"
                    + entity.getId_usuario() + "')";
            resp = motorSql.modificar(sql);
            System.out.println(sql);
            //http://localhost:8080/Controller?ACTION=PRODUCTOS.DAR_ALTA&NOMBRE=sa&MARCA=se&ID=1&IMAGEN=s&DESCRIPCION=sasa&FECHA=23&ESTADO=bIEN&PRECIO=12
        } catch (Exception e) {
            System.out.println("No se inserto con exito");
        } finally {
            motorSql.desconectar();
        }
        if (resp > 0) {
            System.out.println("Articulo insertado con exito.");
        }
        return resp;
    }



    public ArrayList<Articulo> productosUsuario(String id_usuario) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulos WHERE id_usuario='" + id_usuario + "'";

        try {
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Articulo articulo = new Articulo(
                        rs.getInt("id_producto"),
                        rs.getString("marca_producto"),
                        rs.getString("estado"),
                        rs.getString("fecha_subida_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("imagen_producto"),
                        rs.getString("precio_producto"),
                        rs.getString("valoracion")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }

    public ArrayList<Articulo> upload(String tipo) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS WHERE TIPO='" + tipo + "'";
        try {
            //1º)
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {
                Articulo articulo = new Articulo(
                        rs.getInt("ID"),
                        rs.getString("MARCA"),
                        rs.getString("PRECIO"),
                        rs.getString("IMAGEN"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getString("FECHA"),
                        rs.getString("ESTADO"),
                        rs.getString("VALORACION")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }

    public ArrayList<Articulo> find10(Articulo entity) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM articulos\n" +
                "ORDER BY valoracion DESC\n" +
                "LIMIT 10;\n";
        try {
            motorSql.conectar();

            System.out.println(sql);
            ResultSet rs = motorSql.consultar(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Articulo articulo = new Articulo(
                        rs.getInt("id_producto"),
                        rs.getString("marca_producto"),
                        rs.getString("estado"),
                        rs.getString("fecha_subida_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("imagen_producto"),
                        rs.getString("precio_producto"),
                        rs.getString("valoracion")
                );
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            motorSql.desconectar();
        }
        return articulos;
    }



    @Override
    public int delete(Integer e) {
        return 0;
    }


}