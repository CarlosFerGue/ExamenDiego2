package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public interface ITablas {
    ////////////////////////////////////////////FUNCIONES DINÁMICAS////////////////////////////////////////////
    String traducir();
    void insertar(NodeList lista);
    /////////////////////////////////////////FUNCIONES PREDETERMINADAS/////////////////////////////////////////
    default void mostrar(String xml_m){
        System.out.println(xml_m);
    }
    default void exportar(String xml_e, String tabla){
        try {
            // Crear un objeto File para el archivo XML
            File archivo = new File("Archivos/" + tabla +"Exportados.xml");
            // Crear un FileWriter para escribir en el archivo
            FileWriter writer = new FileWriter(archivo);
            // Escribir el contenido en el archivo
            writer.write(xml_e);
            // Cerrar el FileWriter
            writer.close();
            System.out.println("¡El archivo " + tabla + "Exportados.xml ha sido creado!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    default NodeList xmlTOsql(String xml_i, String tabla, String item){
        NodeList listaDatos = null;
        try {
            // Parsear el archivo XML.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //Indicamos el archivo que vamos a procesar.
            System.out.println("Leyendo el archivo 'Archivos/" + tabla + "Importar.xml'");
            Document doc = dBuilder.parse("Archivos/" + tabla + "Importar.xml");
            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos "dato".
            listaDatos = doc.getElementsByTagName(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Devolvemos la lista de datos para procesarlos en cada DAO.
        return listaDatos;
    }

}
