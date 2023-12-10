package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ArticulosDAO implements ITablas{
    //////////////////////////////////////////ATRIBUTOS//////////////////////////////////////////
    private MotorSQL motorSQL;

    /////////////////////////////////////////CONSTRUCTORES////////////////////////////////////////
    public ArticulosDAO(){
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
                exportar(xml_e,"Articulos");
                break;
            case "IMPORTAR":
                String xml_i = traducir();
                NodeList lista = xmlTOsql(xml_i,"Articulos", "articulo");
                insertar(lista);
                break;
        }
        motorSQL.desconectar();
    }


    @Override
    public String traducir() {
        String xml = "";
        xml += "<lista_articulos>\n";
        try{
            ResultSet resultados = motorSQL.consultar("SELECT * FROM articulos");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int id_articulo = resultados.getInt("id_articulo");
                String nombre = resultados.getString("nombre");
                int precio = resultados.getInt("precio");
                int stock = resultados.getInt("stock");
                //Introducimos los datos en el xml.
                xml += "\t<articulo>\n";
                xml += "\t\t<id_articulo>" + id_articulo + "</id_articulo>\n";
                xml += "\t\t<nombre>" + nombre + "</nombre>\n";
                xml += "\t\t<precio>" + precio + "</precio>\n";
                xml += "\t\t<stock>" + stock + "</stock>\n";
                xml += "\t</articulo>\n";
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        xml += "</lista_articulos>";
        //Devolvemos la cadena XML.
        return xml;
    }

    @Override
    public void insertar(NodeList listaArticulos){
        String sentencia="";
        boolean datos_validos = true;
        // Iterar sobre la lista de "particular".
        for (int i = 0; i < listaArticulos.getLength(); i++) {
            Node particularNode = listaArticulos.item(i);
            if (particularNode.getNodeType() == Node.ELEMENT_NODE) {
                Element articulo = (Element) particularNode;
                // Obtener los datos.
                Node idNode = articulo.getElementsByTagName("id_articulo").item(0);
                String id_articulo = (idNode != null) ? idNode.getTextContent() : "";
                String nombre = articulo.getElementsByTagName("nombre").item(0).getTextContent();
                String precio = articulo.getElementsByTagName("precio").item(0).getTextContent();
                String stock = articulo.getElementsByTagName("stock").item(0).getTextContent();

                //Comprobaciones de datos.
                if(comprobarNombre(nombre)==0){ //Comprobamos que el DNI no exista.
                    datos_validos = false;
                } else if (idNode != null) { //Comprobamos que, si estan intentando insertar un ID, no existe ya en la base de datos.
                    if (comprobarId(id_articulo) == 0) {
                        datos_validos = false;
                    }
                }

                //Si los datos son válidos, ejecutamos la inserción.
                if (datos_validos && idNode == null) {
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO articulos (nombre, precio, stock) VALUES ('"+nombre+"', '"+precio+"', '"+stock+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorSQL.modificar(sentencia);
                }else if(datos_validos && idNode != null){
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES ('"+id_articulo+"', '"+nombre+"', '"+precio+"', '"+stock+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorSQL.modificar(sentencia);
                }else {
                    System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
                }

            }
        }
    }

    public int comprobarNombre(String nombre){
        int valido = 1;
        try{
            ResultSet resultados = motorSQL.consultar("SELECT nombre FROM articulos");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                String Nombre = resultados.getString("nombre");
                //Introducimos los datos en el xml.
                if(Nombre.equals(nombre)){
                    valido = 0;
                    System.out.println("El nombre de producto '" + nombre + "' ya está registrado.");
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return valido;
    }

    public int comprobarId(String id_articulo){
        int valido = 1;
        int id = Integer.parseInt(id_articulo);
        try{
            ResultSet resultados = motorSQL.consultar("SELECT id_articulo FROM articulos");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int idArticulo = resultados.getInt("id_articulo");
                //Introducimos los datos en el xml.
                if(idArticulo == id){
                    valido = 0;
                    System.out.println("La ID_Articulo " + idArticulo + " ya está registrada.");
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return valido;
    }
}
