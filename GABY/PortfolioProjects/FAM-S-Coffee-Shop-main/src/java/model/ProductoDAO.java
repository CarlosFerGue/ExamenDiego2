package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO implements DAO<Producto, Integer> {
    private MotorSQL motorSql;

    public ProductoDAO() {
        motorSql = new MotorSQL();
    }

    @Override
    public ArrayList<Producto> findAll(Producto entity) {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS";

        try {
            //1º) 
            motorSql.connect();
            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Producto producto = new Producto(
                        rs.getInt("ID_PRODUCTO"),
                        rs.getInt("STOCK"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getString("URL"),
                        rs.getFloat("PRECIO"),
                        rs.getString("TIPO"),
                        rs.getInt("VALORACION")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return productos;
    }


    public ArrayList<Producto> filterType(String tipo) {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS WHERE TIPO='" + tipo +"'";
        try {
            //1º) 
            motorSql.connect();

            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

             while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Producto producto = new Producto(
                        rs.getInt("ID_PRODUCTO"),
                        rs.getInt("STOCK"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getString("URL"),
                        rs.getFloat("PRECIO"),
                        rs.getString("TIPO"),
                        rs.getInt("VALORACION")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return productos;
    }

    /*
    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList lstProductos = productoDAO.findAll(null);
        System.out.println(lstProductos.toString());
    }*/

    @Override
    public int add(Producto entity) {
        return 0;
    }

    @Override
    public int delete(Integer e) { 
        return 0;
    }

    @Override
    public int update(Producto entity) {
        return 0;
    }
}

