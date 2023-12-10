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
VALUES (1, 'Cafetera DeLonghi Dedica', 'Cafetera espresso con diseño elegante y compacto.', 'https://www.delonghi.com/es-es/productos/maquinas-de-cafe/cafeteras-espresso/dedica-ec-685bk-0132106148', 199.99, 10, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (2, 'Cafetera italiana Bialetti Moka Express', 'Cafetera clásica de aluminio para preparar café con sabor intenso.', 'https://www.bialetti.it/es_es/moka-express.html', 24.99, 20, 'Cafetera', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (3, 'Cafetera de goteo Philips HD7462/20', 'Cafetera de goteo con capacidad para 10-15 tazas.', 'https://www.philips.es/c-p/HD7462_20/cafe-de-goteo', 39.99, 15, 'Cafetera', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (4, 'Cafetera Nespresso Essenza Mini', 'Cafetera de cápsulas con diseño compacto y sistema de calentamiento rápido.', 'https://www.nespresso.com/es/es/order/machines/essenza-mini-blanca', 89.99, 30, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (5, 'Cafetera italiana Oroley', 'Cafetera clásica de aluminio para preparar café con sabor intenso.', 'https://www.oroley.es/cafeteras/cafetera-italiana/', 14.99, 25, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (6, 'Cafetera de cápsulas Tassimo Bosch', 'Cafetera de cápsulas con sistema de preparación inteligente.', 'https://www.bosch-home.es/productos/cafe-y-desayuno/cafeteras-y-teteras/cafeteras-multibebidas/CMV636BS1', 99.99, 5, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (7, 'Cafetera italiana Monix', 'Cafetera clásica de aluminio para preparar café con sabor intenso.', 'https://www.monix.com/cafeteras/cafe-italiano', 12.99, 40, 'Cafetera', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (8, 'Cafetera de goteo Aicook', 'Cafetera de goteo programable con pantalla LCD.', 'https://www.aicook.cc/collections/coffee-makers/products/aicook-coffee-maker', 49.99, 10, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (9, 'Cafetera de cápsulas Dolce Gusto', 'Cafetera de cápsulas con sistema de alta presión para preparar bebidas con espuma.', 'https://www.dolce-gusto.es/multibebidas', 79.99, 15, 'Cafetera', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (10, 'Capsulas Nespresso Intenso', 'Capsulas de café con sabor intenso y notas a chocolate.', 'https://www.nespresso.com/es/es/order/capsules/intenso', 0.45, 50, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (11, 'Capsulas Nescafé Dolce Gusto Cappuccino', 'Capsulas de café con leche y espuma de leche.', 'https://www.dolce-gusto.es/capsulas/cappuccino', 0.36, 30, 'Capsula', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (12, 'Capsulas Lavazza Espresso', 'Capsulas de café espresso con aroma intenso y crema dorada.', 'https://www.lavazza.es/es_ES/tienda/capsulas-de-cafe-espresso.html', 0.35, 20, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (13, 'Capsulas Tassimo LOR Espresso Fortissimo', 'Capsulas de café espresso con sabor intenso y notas de cacao.', 'https://www.tassimo.es/bebidas/lor-espresso-fortissimo', 0.39, 40, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (14, 'Capsulas Senseo Strong', 'Capsulas de café con sabor fuerte y cremoso.', 'https://www.senseo.es/tiendas/capsulas/capsulas-strong.html', 0.25, 25, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (15, 'Capsulas LOR Lungo Profondo', 'Capsulas de café Lungo con sabor intenso y notas a madera.', 'https://www.lor.es/bebidas/lungo-profondo', 0.35, 30, 'Capsula', 4);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (16, 'Capsulas Nespresso Decaffeinato', 'Capsulas de café descafeinado con sabor intenso y notas a cereales.', 'https://www.nespresso.com/es/es/order/capsules/decaffeinato', 0.45, 20, 'Capsula', 3);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (17, 'Capsulas Starbucks Caramel Macchiato', 'Capsulas de café con leche y sabor a caramelo.', 'https://www.starbucksathome.es/productos/bebidas-en-capsulas/caramel-macchiato', 0.6, 15, 'Capsula', 5);

INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, DESCRIPCION, URL, PRECIO, STOCK, TIPO, VALORACION)
VALUES (18, 'Capsulas Tassimo Milka', 'Capsulas de chocolate caliente con sabor suave y cremoso.', 'https://www.tassimo.es/bebidas/milka', 0.39, 25, 'Capsula', 4);
    

SELECT * FROM PRODUCTOS WHERE TIPO='Capsula';
    */