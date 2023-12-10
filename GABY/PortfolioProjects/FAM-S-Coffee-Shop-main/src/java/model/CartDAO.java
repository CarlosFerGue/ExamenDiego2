package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO implements DAO<Cart, Integer>{

    @Override
    public int add(Cart entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Cart entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cart> findAll(Cart entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Producto> filterType(String tipo) {
        /*ArrayList<Producto> productos = new ArrayList<>();
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
        return productos;*/
        return new ArrayList<>();
    }
    
}
