package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionesDAO implements ITablas{
    //////////////////////////////////////////ATRIBUTOS//////////////////////////////////////////
    private MotorSQL motorSQL;

    /////////////////////////////////////////CONSTRUCTORES////////////////////////////////////////
    public DireccionesDAO(){
        this.motorSQL = new MotorSQL();
    }

    ////////////////////////////////////////////MÉTODOS///////////////////////////////////////////
    public void ejecutar(String accion){
        motorSQL.conectar();
        switch(accion){
            case "MOSTRAR":
                String xml_m = traducir();
                mostrar(xml_m);
                break;
            case "EXPORTAR":
                String xml_e = traducir();
                exportar(xml_e,"Direcciones");
                break;
            case "IMPORTAR":
                String xml_i = traducir();
                NodeList lista = xmlTOsql(xml_i,"Direcciones", "direccion");
                insertar(lista);
                break;
        }
        motorSQL.desconectar();
    }

    @Override
    public String traducir() {
        String xml = "";
        xml += "<lista_direcciones>\n";
        try{
            ResultSet resultados = motorSQL.consultar("SELECT * FROM dir_envio ORDER BY id_direccion");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int id_direccion = resultados.getInt("id_direccion");
                String nombre = resultados.getString("nombre");
                //Introducimos los datos en el xml.
                xml += "\t<direccion>\n";
                xml += "\t\t<id_direccion>" + id_direccion + "</id_direccion>\n";
                xml += "\t\t<nombre>" + nombre + "</nombre>\n";
                xml += "\t</direccion>\n";
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        xml += "</lista_direcciones>";
        //Devolvemos la cadena XML.
        return xml;
    }

    @Override
    public void insertar(NodeList listaDirecciones) {
        String sentencia="";
        boolean datos_validos = true;
        // Iterar sobre la lista de "particular".
        for (int i = 0; i < listaDirecciones.getLength(); i++) {
            Node direccionNode = listaDirecciones.item(i);
            if (direccionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element direccion = (Element) direccionNode;
                // Obtener los datos.
                Node idNode = direccion.getElementsByTagName("id_direccion").item(0);
                String id_direccion = (idNode != null) ? idNode.getTextContent() : "";
                Node nombreNode = direccion.getElementsByTagName("nombre").item(0);
                String nombre = (nombreNode != null) ? nombreNode.getTextContent() : "";

                //Comprobaciones de datos.
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
                    motorSQL.modificar(sentencia);
                }else if(datos_validos && idNode != null){
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO dir_envio (id_direccion, nombre) VALUES ('"+id_direccion+"', '"+nombre+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorSQL.modificar(sentencia);
                }else {
                    System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
                }

            }
        }
    }

    public int comprobarId(String id_direccion){
        int valido = 1;
        int id = Integer.parseInt(id_direccion);
        try{
            ResultSet resultados = motorSQL.consultar("SELECT id_direccion FROM dir_envio");
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

    public int comprobarNombre(String nombre){
        int valido = 1;
        if(nombre.equals("")){
            valido = 0;
            System.out.println("El campo 'nombre' NO puede estar vacío.");
        } else {
            try{
                ResultSet resultados = motorSQL.consultar("SELECT nombre FROM dir_envio");
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
}
