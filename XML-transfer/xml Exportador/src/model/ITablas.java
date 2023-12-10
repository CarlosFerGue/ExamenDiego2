package model;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public interface ITablas {
    String traducir() throws SQLException;

    void insertar(NodeList lista) throws SQLException;

    //Default se aplica igual a todas las clases que la implementen,
    //en lugar de que se creen funciones vacias como las de
    //traducir e insertar

    default void mostrarXML(String xml_mostrar) {
        System.out.println(xml_mostrar);
    }

    default void exportar(String xml_exportar, String tabla) {
        try {
            //Creamos un archivo donde guardar el xml
            File archivo = new File("C://Users//charl//OneDrive//Escritorio//XML//XML-transfer//" + tabla + "Exportados.xml");

            //Creamos un FileWriter para escribir en ese archivo
            FileWriter escritor = new FileWriter(archivo);

            //Escribimos en el archivo
            escritor.write(xml_exportar);

            //Cerramos el FileWriter
            escritor.close();
            System.out.println("¡El archivo " + tabla + "Exportados.xml ha sido creado!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default NodeList xmlToSQL(String xml_importar, String tabla, String item) throws ParserConfigurationException, IOException, SAXException {
        NodeList listaDatos = null;

        //Pasar el arcihvo a XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        //Indicamos que archivo vamos a procesar
        System.out.println("Leyendo el archivo 'Archivos/" + tabla + "Importar.xml'");

        //Aqui le cambias la ruta de donde importas
        Document documento = dbBuilder.parse("C://Users//charl//OneDrive//Escritorio/" + tabla + "Importar.xml");
        documento.getDocumentElement().normalize();

        //Obtenemos la lista de nodos "dato"
        listaDatos = documento.getElementsByTagName(item);

        //Devolvemos la lista
        return listaDatos;
    }
}
