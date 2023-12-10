package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketsDAO implements ITablas{
    //////////////////////////////////////////ATRIBUTOS//////////////////////////////////////////
    private MotorSQL motorSQL;

    /////////////////////////////////////////CONSTRUCTORES////////////////////////////////////////
    public TicketsDAO(){
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
                exportar(xml_e,"Tickets");
                break;
            case "IMPORTAR":
                String xml_i = traducir();
                NodeList lista = xmlTOsql(xml_i,"Tickets", "ticket");
                insertar(lista);
                break;
        }
        motorSQL.desconectar();
    }

    @Override
    public String traducir() {
        String xml = "";
        xml += "<lista_tickets>\n";
        try{
            ResultSet resultados = motorSQL.consultar("SELECT * FROM tickets");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int id_ticket = resultados.getInt("id_ticket");
                Date fecha = resultados.getDate("fecha");
                String titular = resultados.getString("titular");
                String num_tarjeta = resultados.getString("num_tarjeta");
                String tipo_tarjeta = resultados.getString("tipo_tarjeta");
                //Introducimos los datos en el xml.
                xml += "\t<ticket>\n";
                xml += "\t\t<id_ticket>" + id_ticket + "</id_ticket>\n";
                xml += "\t\t<fecha>" + fecha + "</fecha>\n";
                xml += "\t\t<titular>" + titular + "</titular>\n";
                xml += "\t\t<num_tarjeta>" + num_tarjeta + "</num_tarjeta>\n";
                xml += "\t\t<tipo_tarjeta>" + tipo_tarjeta + "</tipo_tarjeta>\n";
                xml += "\t</ticket>\n";
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        xml += "</lista_tickets>";
        //Devolvemos la cadena XML.
        return xml;
    }

    @Override
    public void insertar(NodeList listaTickets) {
        String sentencia="";
        boolean datos_validos = true;
        // Iterar sobre la lista de "particular".
        for (int i = 0; i < listaTickets.getLength(); i++) {
            Node ticketNode = listaTickets.item(i);
            if (ticketNode.getNodeType() == Node.ELEMENT_NODE) {
                Element ticket = (Element) ticketNode;
                // Obtener los datos.
                Node idNode = ticket.getElementsByTagName("id_ticket").item(0);
                String id_ticket = (idNode != null) ? idNode.getTextContent() : "";
                String fecha = ticket.getElementsByTagName("fecha").item(0).getTextContent();
                Node titularNode = ticket.getElementsByTagName("titular").item(0);
                String titular = (titularNode != null) ? titularNode.getTextContent() : "";
                Node numNode = ticket.getElementsByTagName("num_tarjeta").item(0);
                String num_tarjeta = (numNode != null) ? numNode.getTextContent() : "";
                Node tipoNode = ticket.getElementsByTagName("tipo_tarjeta").item(0);
                String tipo_tarjeta = (tipoNode != null) ? tipoNode.getTextContent() : "";

                //Comprobaciones de datos.
                if (idNode != null) { //Comprobamos que, si estan intentando insertar un ID, no existe ya en la base de datos.
                    if (comprobarId(id_ticket) == 0) {
                        datos_validos = false;
                    }
                }

                //Si los datos son válidos, ejecutamos la inserción.
                if (datos_validos && idNode == null) {
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO tickets (fecha, titular, num_tarjeta, tipo_tarjeta) VALUES ('"+fecha+"', '"+titular+"', '"+num_tarjeta+"', '"+tipo_tarjeta+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorSQL.modificar(sentencia);
                }else if(datos_validos && idNode != null){
                    //Generamos y mostramos la sentencia de inserción sin incluir el ID.
                    sentencia = "INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES ('"+id_ticket+"', '"+fecha+"', '"+titular+"', '"+num_tarjeta+"', '"+tipo_tarjeta+"')";
                    System.out.println(sentencia);
                    //Ejecutamos la sentencia al servidor.
                    motorSQL.modificar(sentencia);
                }else {
                    System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
                }

            }
        }
    }

    public int comprobarId(String id_ticket){
        int valido = 1;
        int id = Integer.parseInt(id_ticket);
        try{
            ResultSet resultados = motorSQL.consultar("SELECT id_ticket FROM tickets");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int idTicket = resultados.getInt("id_ticket");
                //Introducimos los datos en el xml.
                if(idTicket == id){
                    valido = 0;
                    System.out.println("La ID_Ticket " + idTicket + " ya está registrada.");
                }
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return valido;
    }
}
