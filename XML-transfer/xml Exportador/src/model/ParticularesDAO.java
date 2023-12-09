package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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


    public void ejecutar(String accion_controller) throws SQLException, ParserConfigurationException, IOException, SAXException {
        motorcito.conectar();
        switch (accion_controller) {
            case "MOSTRAR":
                String xml_mostrar = traducir();
                mostrarXML(xml_mostrar);
                break;
            case "EXPORTAR":
                String xml_exportar = traducir();
                exportar(xml_exportar, "Particulares");
                break;
            case "IMPORTAR":
                String xml_importar = traducir();
                NodeList lista = xmlToSQL(xml_importar, "Particulares", "particulares");
                insertar(lista);
                break;
        }
        motorcito.desconectar();
    }


    @Override
    public String traducir() throws SQLException {
        String xml = "";
        xml += "<lista_particulares>\n";

        ResultSet resultados = motorcito.consultar("SELECT * FROM particulares");
        while (resultados.next()) {

            int id_particular = resultados.getInt("id_particular");
            String nombre = resultados.getString("nombre");
            String telefono = resultados.getString("telefono");
            String dni = resultados.getString("dni");
            String direccion = resultados.getString("direccion");

            xml += "\t<particular>\n";
            xml += "\t\t<id_articulo>" + id_particular + "</id_articulo>\n";
            xml += "\t\t<nombre>" + nombre + "</nombre>\n";
            xml += "\t\t<telefono>" + telefono + "</telefono>\n";
            xml += "\t\t<dni>" + dni + "</dni>\n";
            xml += "\t\t<direccion>" + direccion + "</direccion>\n";
            xml += "\t</particular>\n";
        }

        xml += "</lista_particulares>";
        return xml;
    }

    @Override
    public void insertar(NodeList lista) throws SQLException {
        String sentencia = "";
        boolean datos_validos = true;
        // Iterar sobre la lista de "particular"

        for (int i = 0; i < lista.getLength(); i++) {
            Node particularNode = lista.item(i);
            if (particularNode.getNodeType() == Node.ELEMENT_NODE) {
                Element particular = (Element) particularNode;

                //Obtener los datos.
                Node idNode = particular.getElementsByTagName("id_particular").item(0);
                String id_particular = (idNode != null) ? idNode.getTextContent() : "";
                String nombre = particular.getElementsByTagName("nombre").item(0).getTextContent();
                String telefono = particular.getElementsByTagName("telefono").item(0).getTextContent();
                String dni = particular.getElementsByTagName("dni").item(0).getTextContent();
                String direccion = particular.getElementsByTagName("direccion").item(0).getTextContent();
                //Comprobaciones de datos.
                if (comprobarDni(dni) == 0) { //Comprobamos que el DNI no exista.
                    datos_validos = false;
                } else if (idNode != null) { //Comprobamos que, si estan intentando insertar un ID, no existe ya en la base de datos.
                    if (comprobarId(id_particular) == 0) {
                        datos_validos = false;
                    }
                }
                //Si los datos son válidos, ejecutamos la inserción.
                if (datos_validos && idNode == null) {
                    //Generamos y mostramos la sentencia de inserción sin incluir la ID.
                    sentencia = "INSERT INTO particulares (nombre, dni, telefono, direccion) VALUES ('" + nombre + "', '" + dni + "', '" + telefono + "', '" + direccion + "')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorcito.modificar(sentencia);
                } else if (datos_validos && idNode != null) {
                    //Generamos y mostramos la sentencia de inserción.
                    sentencia = "INSERT INTO particulares (id_particular, nombre, dni, telefono, direccion) VALUES ('" + id_particular + "', '" + nombre + "', '" + dni + "', '" + telefono + "', '" + direccion + "')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorcito.modificar(sentencia);
                } else {
                    System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
                }

            }


        }

    }

    public int comprobarDni(String dni){
        int valido = 1;
        try{
            ResultSet resultados = motorcito.consultar("SELECT dni FROM particulares");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                String Dni = resultados.getString("dni");
                //Introducimos los datos en el xml.
                if(Dni.equals(dni)){
                    valido = 0;
                    System.out.println("El DNI " + dni + " ya está registrado.");
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return valido;
    }
    public int comprobarId(String id_particular){
        int valido = 1;
        int id = Integer.parseInt(id_particular);
        try{
            ResultSet resultados = motorcito.consultar("SELECT id_particular FROM particulares");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int idParticular = resultados.getInt("id_particular");
                //Introducimos los datos en el xml.
                if(idParticular == id){
                    valido = 0;
                    System.out.println("La ID_Particular " + idParticular + " ya está registrada.");
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return valido;
    }




}