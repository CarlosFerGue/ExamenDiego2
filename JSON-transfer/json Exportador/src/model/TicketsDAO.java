package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketsDAO implements ITablas {

    private MotorSQL motorcito;

    public TicketsDAO() {
        this.motorcito = new MotorSQL();
    }

    public void ejecutar(String accion_controller) throws SQLException, ParserConfigurationException, IOException, SAXException, JSONException {
        motorcito.conectar();
        switch (accion_controller) {
            case "MOSTRAR":
                String json_mostrar = traducir();
                mostrarJSON(json_mostrar);
                break;
            case "EXPORTAR":
                String json_exportar = traducir();
                exportar(json_exportar, "Tickets");
                break;
            case "IMPORTAR":
                String json_importar = traducir();
                JSONArray jsonArray = jsonToSQL(json_importar, "Tickets", "ticket");
                insertar(jsonArray);
                break;
        }
        motorcito.desconectar();
    }

    @Override
    public String traducir() throws SQLException, JSONException {
        JSONArray jsonArray = new JSONArray();
        ResultSet resultados = motorcito.consultar("SELECT * FROM tickets");

        while (resultados.next()) {
            JSONObject ticketJson = new JSONObject();
            int id_ticket = resultados.getInt("id_ticket");
            Date fecha = resultados.getDate("fecha");
            String titular = resultados.getString("titular");
            String num_tarjeta = resultados.getString("num_tarjeta");
            String tipo_tarjeta = resultados.getString("tipo_tarjeta");

            ticketJson.put("id_ticket", id_ticket);
            ticketJson.put("fecha", fecha.toString());
            ticketJson.put("titular", titular);
            ticketJson.put("num_tarjeta", num_tarjeta);
            ticketJson.put("tipo_tarjeta", tipo_tarjeta);

            jsonArray.put(ticketJson);
        }

        return jsonArray.toString();
    }

    @Override
    public void insertar(JSONArray jsonArray) throws SQLException, JSONException {
        String sentencia = "";
        boolean datos_validos = true;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject ticketJson = jsonArray.getJSONObject(i);
            String id_ticket = ticketJson.optString("id_ticket", "");
            String fecha = ticketJson.getString("fecha");
            String titular = ticketJson.getString("titular");
            String num_tarjeta = ticketJson.getString("num_tarjeta");
            String tipo_tarjeta = ticketJson.getString("tipo_tarjeta");

            if (!id_ticket.isEmpty() && comprobarId(id_ticket) == 0) {
                datos_validos = false;
            }

            if (datos_validos && id_ticket.isEmpty()) {
                sentencia = "INSERT INTO tickets (fecha, titular, num_tarjeta, tipo_tarjeta) VALUES ('" + fecha + "', '" + titular + "', '" + num_tarjeta + "', '" + tipo_tarjeta + "')";
                System.out.println(sentencia);
                motorcito.modificar(sentencia);
            } else if (datos_validos && !id_ticket.isEmpty()) {
                sentencia = "INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES ('" + id_ticket + "', '" + fecha + "', '" + titular + "', '" + num_tarjeta + "', '" + tipo_tarjeta + "')";
                System.out.println(sentencia);
                motorcito.modificar(sentencia);
            } else {
                System.out.println("Los datos no son válidos. La inserción se ha cancelado.");
            }
        }
    }

    public int comprobarId(String id_ticket) {
        int valido = 1;
        int id = Integer.parseInt(id_ticket);
        try {
            ResultSet resultados = motorcito.consultar("SELECT id_ticket FROM tickets");
            while (resultados.next()) {
                int idTicket = resultados.getInt("id_ticket");
                if (idTicket == id) {
                    valido = 0;
                    System.out.println("La ID_Ticket " + idTicket + " ya está registrada.");
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return valido;
    }
}
