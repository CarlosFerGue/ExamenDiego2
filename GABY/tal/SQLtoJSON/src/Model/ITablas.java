package Model;


import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface ITablas {
    ////////////////////////////////////////////FUNCIONES DINÁMICAS////////////////////////////////////////////
    String traducir();
    void insertar(String json);
    /////////////////////////////////////////FUNCIONES PREDETERMINADAS/////////////////////////////////////////
    default void mostrar(String json_m){
        System.out.println(json_m);
    }
    default void exportar(String json_e, String tabla){
        try {
            // Crear un objeto File para el archivo json
            File archivo = new File("Archivos/" + tabla +"Exportados.json");
            // Crear un FileWriter para escribir en el archivo
            FileWriter writer = new FileWriter(archivo);
            // Escribir el contenido en el archivo
            writer.write(json_e);
            // Cerrar el FileWriter
            writer.close();
            System.out.println("¡El archivo " + tabla + "Exportados.json ha sido creado!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    default String leerArchivo(String tabla) {
        //Indicamos cuál es el archivo que vamos a procesar.
        String rutaArchivo = "Archivos/" + tabla + "Importar.json";
        System.out.println("Leyendo el archivo 'Archivos/" + tabla + "Importar.json'");
        //Creamos la cadena donde se va a guardar el contenido.
        String cadenaJson = "";
        try {
            // Lee el contenido del archivo y lo guarda en un String
            cadenaJson = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            //System.out.println(cadenaJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Devolvemos la cadena a la función.
        return cadenaJson;
    }
}
