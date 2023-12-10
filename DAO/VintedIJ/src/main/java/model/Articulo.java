package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Articulo {
    private int id_producto, id_usuario, id_comprador;

    private String marca, precio, imagen, nombre, descripcion, fecha, estado, valoracion, color;

    public Articulo(int id_usuario, String marca, String precio, String nombre, String descripcion, String estado, String color) {
        this.id_usuario = id_usuario;
        this.marca = marca;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.color = color;
    }

    public Articulo(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public Articulo(int id_producto, int id_comprador) {
        this.id_producto = id_producto;
        this.id_comprador = id_comprador;
    }

    public Articulo(String estado) {
        this.estado = estado;
    }

    public Articulo(int id_producto, String valoracion) {
        this.id_producto = id_producto;
        this.valoracion = valoracion;
    }

    public Articulo(int id_usuario, int id_producto,  String marca, String precio, String imagen, String nombre, String descripcion, String fecha, String estado, String valoracion) {
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Articulo(int id_usuario, String marca, String precio, String imagen, String nombre, String descripcion, String fecha, String estado, String valoracion) {
        this.id_usuario = id_usuario;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public static String toArrayJSon(ArrayList<Articulo> articulos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(articulos);

        return resp;
    }

}