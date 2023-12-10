package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionesDAO implements ITablas {
    private MotorSQL motorcito;

    public DireccionesDAO() {
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
                exportar(json_exportar, "Direcciones");
                break;
            case "IMPORTAR":
                String json_importar = traducir();
                JSONArray jsonArray = jsonToSQL(json_importar, "Direcciones", "direccion");
                insertar(jsonArray);
                break;
        }
        motorcito.desconectar();
    }

    @Override
    public String traducir() throws SQLException, JSONException {
        JSONArray jsonArray = new JSONArray();

        ResultSet resultados = motorcito.consultar("SELECT * FROM dir_envio ORDER BY id_direccion");
        while (resultados.next()) {
            JSONObject direccionJson = new JSONObject();

            int id_direccion = resultados.getInt("id_direccion");
            String nombre = resultados.getString("nombre");

            direccionJson.put("id_direccion", id_direccion);
            direccionJson.put("nombre", nombre);

            jsonArray.put(direccionJson);
        }

        return jsonArray.toString();
    }

    @Override
    public void insertar(JSONArray jsonArray) throws SQLException, JSONException {
        String sentencia = "";
        boolean datos_validos = true;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject direccionJson = jsonArray.getJSONObject(i);

            // Obtenemos y asignamos a variables los datos del JSON que nos pasen
            String id_direccion = direccionJson.optString("id_direccion", "");
            String nombre = direccionJson.getString("nombre");

            // Comprobamos los datos
            if (!id_direccion.isEmpty()) { // Comprobamos que, si están intentando insertar un ID, no existe ya en la base de datos.
                if (comprobarId(id_direccion) == 0) {
                    datos_validos = false;
                }
            }
            if (comprobarNombre(nombre) == 0) { // Comprobamos que el nombre ni exista ni esté vacío.
                datos_validos = false;
            }

            // Si los datos son válidos, ejecutamos la inserción.
            if (datos_validos && id_direccion.isEmpty()) {
                // Generamos y mostramos la sentencia de inserción sin incluir el ID.
                sentencia = "INSERT INTO dir_envio (nombre) VALUES ('" + nombre + "')";
                System.out.println(sentencia);
                // Ejecutamos la sentencia al servidor.
                motorcito.modificar(sentencia);
            } else if (datos_validos && !id_direccion.isEmpty()) {
                // Generamos y mostramos la sentencia de inserción sin incluir el ID.
                sentencia = "INSERT INTO dir_envio (id_direccion, nombre) VALUES ('" + id_direccion + "', '" + nombre + "')";
                System.out.println(sentencia);
                // Ejecutamos la sentencia al servidor.
                motorcito.modificar(sentencia);
            } else {
                System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
            }
        }
    }

    public int comprobarNombre(String nombre) throws SQLException {
        int valido = 1;
        if (nombre.equals("")) {
            valido = 0;
            System.out.println("El campo 'nombre' NO puede estar vacío.");
        } else {
            try {
                ResultSet resultados = motorcito.consultar("SELECT nombre FROM dir_envio");
                while (resultados.next()) {
                    // Obtenemos los datos del servidor de bases de datos.
                    String Nombre = resultados.getString("nombre");
                    // Introducimos los datos en el JSON.
                    if (Nombre.equals(nombre)) {
                        valido = 0;
                        System.out.println("La calle " + nombre + " ya está registrada.");
                    }
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return valido;
    }

    public int comprobarId(String id_direccion) throws SQLException {
        int valido = 1;
        int id = Integer.parseInt(id_direccion);
        try {
            ResultSet resultados = motorcito.consultar("SELECT id_direccion FROM dir_envio");
            while (resultados.next()) {
                // Obtenemos los datos del servidor de bases de datos.
                int idDireccion = resultados.getInt("id_direccion");
                // Introducimos los datos en el JSON.
                if (idDireccion == id) {
                    valido = 0;
                    System.out.println("La ID_Direccion " + idDireccion + " ya está registrada.");
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return valido;
    }
}
