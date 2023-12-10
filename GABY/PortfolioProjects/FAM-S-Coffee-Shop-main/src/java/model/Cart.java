package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Producto> productos;
    private Usuario usuario;

    public Cart(ArrayList<Producto> productos, Usuario usuario) {
        this.productos = productos;
        this.usuario = usuario;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public static String toArrayJSon(ArrayList<Cart> carts) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(carts);

        return resp;
    }
    
}
