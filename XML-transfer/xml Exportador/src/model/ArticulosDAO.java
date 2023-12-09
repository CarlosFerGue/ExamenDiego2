package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticulosDAO implements ITablas{
    private MotorSQL motorcito;


    public ArticulosDAO() {
        this.motorcito = new MotorSQL();
    }


    public void ejecutar(String accion_controller) throws SQLException, ParserConfigurationException, IOException, SAXException {
        motorcito.conectar();
        switch (accion_controller){
            case "MOSTRAR":
                String xml_mostrar = traducir();
                mostrarXML(xml_mostrar);
                break;
            case "EXPORTAR":
                String xml_exportar = traducir();
                exportar(xml_exportar,"Articulos");
                break;
            case "IMPORTAR":
                String xml_importar = traducir();
                NodeList lista = xmlToSQL(xml_importar,"Articulos","articulo");
                insertar(lista);
                break;
        }
        motorcito.desconectar();
    }


    @Override
    public String traducir() throws SQLException {
        String xml = "";
        xml += "<lista_articulos>\n";

        ResultSet resultados = motorcito.consultar("SELECT * FROM articulos");
        while (resultados.next()){

            //Asignamos los datos de la tabla a variables
            int id_articulo = resultados.getInt("id_articulo");
            String nombre = resultados.getString("nombre");
            int precio = resultados.getInt("precio");
            int stock = resultados.getInt("stock");

            //Introducimos esos datos en un xml
            xml += "\t<articulo>\n";
            xml += "\t\t<id_articulo>" + id_articulo + "</id_articulo>\n";
            xml += "\t\t<nombre>" + nombre + "</nombre>\n";
            xml += "\t\t<precio>" + precio + "</precio>\n";
            xml += "\t\t<stock>" + stock + "</stock>\n";
            xml += "\t</articulo>\n";
        }
        xml += "</lista_articulos>";

        return xml;
    }

    @Override
    public void insertar(NodeList lista) throws SQLException {
        String sentencia="";
        boolean datos_validos = true;

        for (int i = 0; i < lista.getLength(); i++) {
            Node particularNode = lista.item(i);
            if (particularNode.getNodeType() == Node.ELEMENT_NODE) {
                Element articulo = (Element) particularNode;

                //Obtenemos y asgnamos a varibales los datos del xml que nos pasen
                Node idNode = articulo.getElementsByTagName("id_articulo").item(0);
                String id_articulo = (idNode != null) ? idNode.getTextContent() : "";
                String nombre =  articulo.getElementsByTagName("nombre").item(0).getTextContent();
                String precio = articulo.getElementsByTagName("precio").item(0).getTextContent();
                String stock = articulo.getElementsByTagName("stock").item(0).getTextContent();

                //Comprobamos los datos
                if (comprobarNombre(nombre) == 0) { //Comprobamos nombre
                    datos_validos = false;
                } else if (idNode != null) {
                    if (comprobarId(id_articulo) == 0) { //Comprobamos id
                        datos_validos = false;
                    }
                }

                //Si los datos son validos los introducimos
                if (datos_validos && idNode == null) {
                    //Creamos el insert de SQL
                    sentencia = "INSERT INTO articulos (nombre, precio, stock) VALUES ('"+nombre+"', '"+precio+"', '"+stock+"')";
                    System.out.println(sentencia);
                    //Y lo ejecutamos
                    motorcito.modificar(sentencia);
                } else if (datos_validos && idNode != null) {
                    sentencia = "INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES ('"+id_articulo+"', '"+nombre+"', '"+precio+"', '"+stock+"')";
                    System.out.println(sentencia);
                    motorcito.modificar(sentencia);
                }else {
                    System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
                }


            }
        }
    }


    public int comprobarNombre(String nombre) throws SQLException {
        int valido = 1;

        ResultSet resultados = motorcito.consultar("SELECT nombre FROM articulos");
        while (resultados.next()){
            //Obtenemos los datos de la Base de datos
            String nombreBD = resultados.getString("nombre");
            //Comprobamos si es igual al que intentan meter
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
        while (resultados.next()){
            String idBD = resultados.getString("id_articulo");
            if (idBD.equals(id_articulo)) {
                valido = 0;
                System.out.println("La ID_Articulo " + idBD + " ya está registrada.");
            }
        }
        return valido;
    }

}
