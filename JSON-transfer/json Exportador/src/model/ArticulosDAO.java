package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticulosDAO implements ITablas {
    private MotorSQL motorcito;

    public ArticulosDAO() {
        this.motorcito = new MotorSQL();
    }

    public void ejecutar(String accion_controller) throws SQLException, ParserConfigurationException, IOException, SAXException, JSONException {
        motorcito.conectar();
        switch (accion_controller) {
            case "MOSTRAR":
                String json_mostrar = traducir();
                mostrarJSON(json_mostrar);
                break;
            case "EXPORTAR":
                String json_exportar = traducir();
                exportar(json_exportar, "Articulos");
                break;
            case "IMPORTAR":
                String json_importar = traducir();
                JSONArray jsonArray = jsonToSQL(json_importar, "Articulos", "articulo");
                insertar(jsonArray);
                break;
        }
        motorcito.desconectar();
    }

    @Override
    public String traducir() throws SQLException, JSONException {
        JSONArray jsonArray = new JSONArray();

        ResultSet resultados = motorcito.consultar("SELECT * FROM articulos");
        while (resultados.next()) {
            JSONObject articuloJson = new JSONObject();
            // Asignamos los datos de la tabla a variables
            int id_articulo = resultados.getInt("id_articulo");
            String nombre = resultados.getString("nombre");
            int precio = resultados.getInt("precio");
            int stock = resultados.getInt("stock");

            // Introducimos esos datos en un JSON
            articuloJson.put("id_articulo", id_articulo);
            articuloJson.put("nombre", nombre);
            articuloJson.put("precio", precio);
            articuloJson.put("stock", stock);

            jsonArray.put(articuloJson);
        }

        return jsonArray.toString();
    }

    @Override
    public void insertar(JSONArray jsonArray) throws SQLException, JSONException {
        String sentencia = "";
        boolean datos_validos = true;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject articuloJson = jsonArray.getJSONObject(i);

            // Obtenemos y asignamos a variables los datos del JSON que nos pasen
            String id_articulo = articuloJson.optString("id_articulo", "");
            String nombre = articuloJson.getString("nombre");
            String precio = articuloJson.getString("precio");
            String stock = articuloJson.getString("stock");

            // Comprobamos los datos
            if (comprobarNombre(nombre) == 0) { // Comprobamos nombre
                datos_validos = false;
            } else if (!id_articulo.isEmpty()) {
                if (comprobarId(id_articulo) == 0) { // Comprobamos id
                    datos_validos = false;
                }
            }

            // Si los datos son válidos los introducimos
            if (datos_validos && id_articulo.isEmpty()) {
                // Creamos el insert de SQL
                sentencia = "INSERT INTO articulos (nombre, precio, stock) VALUES ('" + nombre + "', '" + precio + "', '" + stock + "')";
                System.out.println(sentencia);
                // Y lo ejecutamos
                motorcito.modificar(sentencia);
            } else if (datos_validos && !id_articulo.isEmpty()) {
                sentencia = "INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES ('" + id_articulo + "', '" + nombre + "', '" + precio + "', '" + stock + "')";
                System.out.println(sentencia);
                motorcito.modificar(sentencia);
            } else {
                System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
            }
        }
    }

    public int comprobarNombre(String nombre) throws SQLException {
        int valido = 1;

        ResultSet resultados = motorcito.consultar("SELECT nombre FROM articulos");
        while (resultados.next()) {
            // Obtenemos los datos de la Base de datos
            String nombreBD = resultados.getString("nombre");
            // Comprobamos si es igual al que intentan meter
            if (nombreBD.equals(nombre)) {
                valido = 0;
                System.out.println("El nombre de producto '" + nombre + "' ya está registrado.");
            }
        }
        return valido;
    }

    public int comprobarId(String id_articulo) throws SQLException {
        int valido = 1;

        ResultSet resultados = motorcito.consultar("SELECT id_articulo FROM articulos");
        while (resultados.next()) {
            String idBD = resultados.getString("id_articulo");
            if (idBD.equals(id_articulo)) {
                valido = 0;
                System.out.println("La ID_Articulo " + idBD + " ya está registrada.");
            }
        }
        return valido;
    }
}
