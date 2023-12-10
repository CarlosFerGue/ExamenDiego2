package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticularesDAO implements ITablas {
    private MotorSQL motorcito;

    public ParticularesDAO() {
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
                exportar(json_exportar, "Particulares");
                break;
            case "IMPORTAR":
                String json_importar = traducir();
                JSONArray jsonArray = jsonToSQL(json_importar, "Particulares", "particulares");
                insertar(jsonArray);
                break;
        }
        motorcito.desconectar();
    }

    @Override
    public String traducir() throws SQLException, JSONException {
        JSONArray jsonArray = new JSONArray();

        ResultSet resultados = motorcito.consultar("SELECT * FROM particulares");
        while (resultados.next()) {
            JSONObject particularJson = new JSONObject();

            int id_particular = resultados.getInt("id_particular");
            String nombre = resultados.getString("nombre");
            String telefono = resultados.getString("telefono");
            String dni = resultados.getString("dni");
            String direccion = resultados.getString("direccion");

            particularJson.put("id_particular", id_particular);
            particularJson.put("nombre", nombre);
            particularJson.put("telefono", telefono);
            particularJson.put("dni", dni);
            particularJson.put("direccion", direccion);

            jsonArray.put(particularJson);
        }

        return jsonArray.toString();
    }

    @Override
    public void insertar(JSONArray jsonArray) throws SQLException, JSONException {
        String sentencia = "";
        boolean datos_validos = true;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject particularJson = jsonArray.getJSONObject(i);

            // Obtener los datos y asignar a variables.
            String id_particular = particularJson.optString("id_particular", "");
            String nombre = particularJson.getString("nombre");
            String telefono = particularJson.getString("telefono");
            String dni = particularJson.getString("dni");
            String direccion = particularJson.getString("direccion");

            // Comprobaciones de datos.
            if (comprobarDni(dni) == 0) {
                datos_validos = false;
            } else if (!id_particular.isEmpty()) {
                if (comprobarId(id_particular) == 0) {
                    datos_validos = false;
                }
            }

            // Si los datos son válidos, ejecutamos la inserción.
            if (datos_validos && id_particular.isEmpty()) {
                sentencia = "INSERT INTO particulares (nombre, dni, telefono, direccion) VALUES ('" + nombre + "', '" + dni + "', '" + telefono + "', '" + direccion + "')";
                System.out.println(sentencia);
                motorcito.modificar(sentencia);
            } else if (datos_validos && !id_particular.isEmpty()) {
                sentencia = "INSERT INTO particulares (id_particular, nombre, dni, telefono, direccion) VALUES ('" + id_particular + "', '" + nombre + "', '" + dni + "', '" + telefono + "', '" + direccion + "')";
                System.out.println(sentencia);
                motorcito.modificar(sentencia);
            } else {
                System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
            }
        }
    }

    public int comprobarDni(String dni) {
        int valido = 1;
        try {
            ResultSet resultados = motorcito.consultar("SELECT dni FROM particulares");
            while (resultados.next()) {
                String Dni = resultados.getString("dni");
                if (Dni.equals(dni)) {
                    valido = 0;
                    System.out.println("El DNI " + dni + " ya está registrado.");
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return valido;
    }

    public int comprobarId(String id_particular) {
        int valido = 1;
        int id = Integer.parseInt(id_particular);
        try {
            ResultSet resultados = motorcito.consultar("SELECT id_particular FROM particulares");
            while (resultados.next()) {
                int idParticular = resultados.getInt("id_particular");
                if (idParticular == id) {
                    valido = 0;
                    System.out.println("La ID_Particular " + idParticular + " ya está registrada.");
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return valido;
    }
}
