package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionesDAO implements ITablas{

    private MotorSQL motorcito;


    public DireccionesDAO() {
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
                exportar(xml_exportar,"Direcciones");
                break;
            case "IMPORTAR":
                String xml_importar = traducir();
                NodeList lista = xmlToSQL(xml_importar,"Direcciones","direccion");
                insertar(lista);
                break;
        }
        motorcito.desconectar();
    }


    @Override
    public String traducir() throws SQLException {
        String xml = "";
        xml += "<lista_direcciones>\n";

        ResultSet resultados = motorcito.consultar("SELECT * FROM dir_envio ORDER BY id_direccion");
        while (resultados.next()){


            int id_direccion = resultados.getInt("id_direccion");
            String nombre = resultados.getString("nombre");

            xml += "\t<direccion>\n";
            xml += "\t\t<id_direccion>" + id_direccion + "</id_direccion>\n";
            xml += "\t\t<nombre>" + nombre + "</nombre>\n";
            xml += "\t</direccion>\n";
        }
        xml += "</lista_direcciones>";

        return xml;
    }

    @Override
    public void insertar(NodeList lista) throws SQLException {
        String sentencia="";
        boolean datos_validos = true;

        for (int i = 0; i < lista.getLength(); i++) {
            Node direccionesNode = lista.item(i);
            if (direccionesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element direccion = (Element) direccionesNode;

                //Obtenemos y asgnamos a varibales los datos del xml que nos pasen
                Node idNode = direccion.getElementsByTagName("id_direccion").item(0);
                String id_direccion = (idNode != null) ? idNode.getTextContent() : "";
                Node nombreNode = direccion.getElementsByTagName("nombre").item(0);
                String nombre = (nombreNode != null) ? nombreNode.getTextContent() : "";

                //Comprobamos los datos
                if (idNode != null) { //Comprobamos que, si estan intentando insertar un ID, no existe ya en la base de datos.
                    if (comprobarId(id_direccion) == 0) {
                        datos_validos = false;
                    }
                }
                if(comprobarNombre(nombre) == 0){ //Comprobamos que el nombre ni exista ni esté vació.
                    datos_validos = false;
                }

                //Si los datos son válidos, ejecutamos la inserción.
                if (datos_validos && idNode == null) {
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO dir_envio (nombre) VALUES ('"+nombre+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorcito.modificar(sentencia);
                }else if(datos_validos && idNode != null){
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO dir_envio (id_direccion, nombre) VALUES ('"+id_direccion+"', '"+nombre+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorcito.modificar(sentencia);
                }else {
                    System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
                }


            }
        }
    }


    public int comprobarNombre(String nombre) throws SQLException {
        int valido = 1;
        if(nombre.equals("")){
            valido = 0;
            System.out.println("El campo 'nombre' NO puede estar vacío.");
        } else {
            try{
                ResultSet resultados = motorcito.consultar("SELECT nombre FROM dir_envio");
                while(resultados.next()){
                    //Obtenemos los datos del servidor de bases de datos.
                    String Nombre = resultados.getString("nombre");
                    //Introducimos los datos en el xml.
                    if (Nombre.equals(nombre)){
                        valido = 0;
                        System.out.println("La calle " + nombre + " ya está registrada.");
                    }
                }
            }catch(SQLException ex){
                ex.getMessage();
            }
        }
        return valido;
    }

    public int comprobarId(String id_direccion) throws SQLException {
        int valido = 1;
        int id = Integer.parseInt(id_direccion);
        try{
            ResultSet resultados = motorcito.consultar("SELECT id_direccion FROM dir_envio");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int idDireccion = resultados.getInt("id_direccion");
                //Introducimos los datos en el xml.
                if(idDireccion == id){
                    valido = 0;
                    System.out.println("La ID_Direccion " + idDireccion + " ya está registrada.");
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return valido;
    }
}
