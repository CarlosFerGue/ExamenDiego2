//package model;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.SQLException;
//
//public interface ITablas {
//    String traducir() throws SQLException, JSONException;
//
//    void insertar(JSONArray jsonArray) throws SQLException, JSONException;
//
//    default void mostrarJSON(String json_mostrar) {
//        System.out.println(json_mostrar);
//    }
//
//
//    default void exportar(String json_exportar, String tabla) {
//        try {
//            // Creamos un archivo donde guardar el JSON
//            File archivo = new File("C://Users//charl//OneDrive//Escritorio//ExamenDiego2//ExamenDiego2//JSON-transfer//" + tabla + "Exportados.json");
//
//            //C:\Users\charl\OneDrive\Escritorio\ExamenDiego2\ExamenDiego2\JSON-transfer
//
//            // Creamos un FileWriter para escribir en ese archivo
//            FileWriter escritor = new FileWriter(archivo);
//
//            // Escribimos en el archivo
//            escritor.write(json_exportar);
//
//            // Cerramos el FileWriter
//            escritor.close();
//            System.out.println("¡El archivo " + tabla + "Exportados.json ha sido creado!");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    default JSONArray jsonToSQL(String json_importar, String tabla, String item) throws IOException, JSONException {
//        JSONArray jsonArray = null;
//
//        // Leemos el archivo JSON
//        String jsonContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("C://Users//charl//OneDrive//Escritorio//ExamenDiego2//ExamenDiego2//JSON-transfer//"
//                + tabla + "Importar.json")));
//
//        // Creamos un objeto JSON a partir del contenido del archivo
//        JSONObject jsonObject = new JSONObject(jsonContent);
//
//        // Obtenemos la lista de objetos correspondiente al item
//        jsonArray = jsonObject.getJSONArray(item);
//
//        // Devolvemos la lista
//        return jsonArray;
//    }
//}

package model;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public interface ITablas {
    String traducir() throws SQLException, JSONException;

    void insertar(JSONArray jsonArray) throws SQLException, JSONException;

    default void mostrarJSON(String json_mostrar) {
        System.out.println(json_mostrar);
    }

    default void exportar(String json_exportar, String tabla) {
        try {
            // Creamos un archivo donde guardar el JSON
            File archivo = new File("C://Users//charl//OneDrive//Escritorio//ExamenDiego2//ExamenDiego2//JSON-transfer//" + tabla + "Exportados.json");

            // Creamos un FileWriter para escribir en ese archivo
            FileWriter escritor = new FileWriter(archivo);

            // Escribimos en el archivo
            escritor.write(json_exportar);

            // Cerramos el FileWriter
            escritor.close();
            System.out.println("¡El archivo " + tabla + "Exportados.json ha sido creado!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default JSONArray jsonToSQL(String json_importar, String tabla, String item) throws IOException, JSONException {
        JSONArray jsonArray = null;

        // Creamos un objeto JSON a partir de la cadena JSON
        jsonArray = new JSONArray(json_importar);

        // Devolvemos la lista
        return jsonArray;
    }
}

