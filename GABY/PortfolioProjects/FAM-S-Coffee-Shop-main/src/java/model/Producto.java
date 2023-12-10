package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Producto {
    private int id, stock, valoracion;
    private String nombre, descripcion, url, tipo;
    private float precio;

    public Producto(int id, int stock, String nombre, String descripcion, String url, float precio, String tipo, int valoracion) {
        this.id = id;
        this.stock = stock;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.precio = precio;
        this.tipo = tipo;
        this.valoracion = valoracion;
    }
    
    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
    
    public static String toArrayJSon(ArrayList<Producto> productos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(productos);

        return resp;
    }
    
}





/*
    DROP TABLE PRODUCTOS;

CREATE TABLE PRODUCTOS(
	ID_PRODUCTO INT,
	NOMBRE VARCHAR(70),
        DESCRIPCION VARCHAR(200),
        URL VARCHAR(200),
	PRECIO FLOAT NOT NULL,
	STOCK INT,
	TIPO VARCHAR(20),
        VALORACION INT,
        PRIMARY KEY(ID_PRODUCTO)
    );
    
    
INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (1, 'DeLonghi Dedica Espresso Machine', 'Espresso machine with elegant and compact design.', 'IMG/PRODUCTS/cm2.png', 199.99, 10, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (2, 'Bialetti Moka Express Italian Coffee Maker', 'Classic aluminum coffee maker for preparing intense coffee.', 'IMG/PRODUCTS/cm4.png', 24.99, 20, 'Cafetera', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (3, 'Cafetera de goteo Philips HD7462/20', 'Cafetera de goteo con capacidad para 10-15 tazas.', 'IMG/PRODUCTS/cm6.png', 39.99, 15, 'Cafetera', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (4, 'Cafetera Nespresso Essenza Mini', 'Cafetera de cápsulas con diseño compacto y sistema de calentamiento rápido.', 'IMG/PRODUCTS/cm7.png', 89.99, 30, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (5, 'Cafetera italiana Oroley', 'Cafetera clásica de aluminio para preparar café con sabor intenso.', 'IMG/PRODUCTS/cm8.png', 14.99, 25, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (6, 'Cafetera de cápsulas Tassimo Bosch', 'Cafetera de cápsulas con sistema de preparación inteligente.', 'IMG/PRODUCTS/cm9.png', 99.99, 5, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (7, 'Cafetera italiana Monix', 'Cafetera clásica de aluminio para preparar café con sabor intenso.', 'IMG/PRODUCTS/cm10.png', 12.99, 40, 'Cafetera', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (8, 'Cafetera de goteo Aicook', 'Cafetera de goteo programable con pantalla LCD.', 'IMG/PRODUCTS/cm11.png', 49.99, 10, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (9, 'Cafetera de cápsulas Dolce Gusto', 'Cafetera de cápsulas con sistema de alta presión para preparar bebidas con espuma.', 'IMG/PRODUCTS/cm12.png', 79.99, 15, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (10, 'Capsulas Nespresso Intenso', 'Capsulas de café con sabor intenso y notas a chocolate.', 'IMG/PRODUCTS/cc2.png', 0.45, 50, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (11, 'Capsulas Nescafé Dolce Gusto Cappuccino', 'Capsulas de café con leche y espuma de leche.', 'IMG/PRODUCTS/cc3.png', 0.36, 30, 'Capsula', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (12, 'Capsulas Lavazza Espresso', 'Capsulas de café espresso con aroma intenso y crema dorada.', 'IMG/PRODUCTS/cc4.png', 0.35, 20, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (13, 'Capsulas Tassimo LOR Espresso Fortissimo', 'Capsulas de café espresso con sabor intenso y notas de cacao.', 'IMG/PRODUCTS/cc6.png', 0.39, 40, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (14, 'Capsulas Senseo Strong', 'Capsulas de café con sabor fuerte y cremoso.', 'IMG/PRODUCTS/cc7.png', 0.25, 25, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (15, 'Capsulas LOR Lungo Profondo', 'Capsulas de café Lungo con sabor intenso y notas a madera.', 'IMG/PRODUCTS/cc8.png', 0.35, 30, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (16, 'Capsulas Nespresso Decaffeinato', 'Capsulas de café descafeinado con sabor intenso y notas a cereales.', 'IMG/PRODUCTS/cc9.png', 0.45, 20, 'Capsula', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (17, 'Capsulas Starbucks Caramel Macchiato', 'Capsulas de café con leche y sabor a caramelo.', 'IMG/PRODUCTS/cc11.png', 0.6, 15, 'Capsula', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (18, 'Capsulas Tassimo Milka', 'Capsulas de chocolate caliente con sabor suave y cremoso.', 'IMG/PRODUCTS/cc12.png', 0.39, 25, 'Capsula', 4);

SELECT * FROM PRODUCTOS WHERE TIPO='Capsula';
    */


/*BASE ACTUALIZADA PAPA

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (1, 'DeLonghi Dedica', 'Espresso machine with elegant and compact design.', 'IMG/PRODUCTS/cm2.png', 199.99, 10, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (2, 'Bialetti Italian', 'Classic aluminum coffee maker for preparing intense coffee.', 'IMG/PRODUCTS/cm4.png', 24.99, 20, 'Cafetera', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (3, 'Philips HD7462/20', 'Drip coffee maker with capacity for 10-15 cups.', 'IMG/PRODUCTS/cm6.png', 39.99, 15, 'Cafetera', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (4, 'Nespresso Essenza', 'Capsule coffee machine with compact design and rapid heating system.', 'IMG/PRODUCTS/cm7.png', 89.99, 30, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (5, 'Oroley Italian', 'Classic aluminum coffee maker for preparing intense coffee.', 'IMG/PRODUCTS/cm8.png', 14.99, 25, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (6, 'Tassimo Bosch', 'Capsule coffee machine with intelligent brewing system.', 'IMG/PRODUCTS/cm9.png', 99.99, 5, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (7, 'Monix Italian', 'Classic aluminum coffee maker for preparing intense coffee.', 'IMG/PRODUCTS/cm10.png', 12.99, 40, 'Cafetera', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (8, 'Aicook Drip', 'Programmable drip coffee maker with LCD display.', 'IMG/PRODUCTS/cm11.png', 49.99, 10, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (9, 'Dolce Gusto', 'Capsule coffee machine with high-pressure system.', 'IMG/PRODUCTS/cm12.png', 79.99, 15, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (10, 'Nespresso Intenso', 'Coffee capsules with intense flavor and notes of chocolate.', 'IMG/PRODUCTS/cc2.png', 0.45, 50, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (11, 'Nescafé Dolce Gusto', 'Coffee capsules with milk and milk foam.', 'IMG/PRODUCTS/cc3.png', 0.36, 30, 'Capsula', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (12, 'Lavazza Espresso', 'Espresso coffee capsules with intense aroma and golden crema.', 'IMG/PRODUCTS/cc4.png', 0.35, 20, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (13, 'Tassimo LOR Espresso', 'Espresso coffee capsules with intense flavor and hints of cocoa.', 'IMG/PRODUCTS/cc6.png', 0.39, 40, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (14, 'Senseo Strong', 'Coffee capsules with strong and creamy flavor.', 'IMG/PRODUCTS/cc7.png', 0.25, 25, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (15, 'LOR Lungo Profondo', 'Lungo coffee capsules with intense flavor and hints of wood.', 'IMG/PRODUCTS/cc8.png', 0.35, 30, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (16, 'Nespresso DES', 'Decaffeinated coffee capsules with intense flavor and cereal notes.', 'IMG/PRODUCTS/cc9.png', 0.45, 20, 'Capsula', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (17, 'Starbucks Caramel', 'Coffee capsules with milk and caramel flavor.', 'IMG/PRODUCTS/cc11.png', 0.6, 15, 'Capsula', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (18, 'Tassimo Milka', 'Hot chocolate capsules with smooth and creamy flavor.', 'IMG/PRODUCTS/cc12.png', 0.39, 25, 'Capsula', 4);

*/