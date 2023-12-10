package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.NodeList;

import java.sql.ResultSet;
import java.sql.SQLException;

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
                String json_m = traducir();
                mostrar(json_m);
                break;
            case "EXPORTAR":
                String json_e = traducir();
                exportar(json_e,"Articulos");
                break;
            case "IMPORTAR":
                String json_i = leerArchivo("Articulos");
                insertarManija(json_i);
                break;
        }
        motorSQL.desconectar();
    }

    @Override
    public String traducir() {
        int posicion = 1;
        String json = "[\n";
        try{
            ResultSet resultados = motorSQL.consultar("SELECT * FROM articulos");
            while(resultados.next()){
                //Obtenemos los datos del servidor de bases de datos.
                int id_articulo = resultados.getInt("id_articulo");
                String nombre = resultados.getString("nombre");
                int precio = resultados.getInt("precio");
                int stock = resultados.getInt("stock");
                //Introducimos los datos en el json.
                //En caso de que sea el primer item en añadir al JSON, no añadiremos la coma para separar los items.
                if (posicion == 1){
                    json += "\t{\n";
                } if (posicion != 1){
                    json += "\t,{\n";
                }
                json += "\t\t\"id_articulo\":" + id_articulo + ",\n";
                json += "\t\t\"nombre\":\"" + nombre + "\",\n";
                json += "\t\t\"precio\":" + precio + ",\n";
                json += "\t\t\"stock\":" + stock + "\n";
                json += "\t}\n";
                //La posición deja de ser 1.
                posicion += 1;
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        json += "]";
        //Devolvemos la cadena json.
        return json;
    }

    @Override
    public void insertar(String json_i) {
        //Nos conectamos al servidor de bases de datos antes de procesar las inserciones.
        motorSQL.conectar();
        System.out.println("Realizando las inserciónes de ARTÍCULOS en el servidor de BBDD.");
        try {
            //Crear un ObjectMapper.
            ObjectMapper objectMapper = new ObjectMapper();
            //Convertir el JSON en un array de JsonNode.
            JsonNode arrayNode = objectMapper.readTree(json_i);
            //Iteramos sobre cada objeto en el array.
            for (JsonNode jsonNode : arrayNode) {
                //Obtenemos los datos del objeto actual.
                String nombre = jsonNode.get("nombre").asText();
                int precio = jsonNode.get("precio").asInt();
                int stock = jsonNode.get("stock").asInt();

                //Creamos la sentencia SQL de inserción y la ejecutamos.
                String SQL = "INSERT INTO articulos (nombre, precio, stock) VALUES ('"+nombre+"', "+precio+", "+stock+")";
                motorSQL.modificar(SQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Nos desconectamos tras realizar todas las inserciones.
        motorSQL.desconectar();
        System.out.println("Inserciones finalizadas.");
    }

    public void insertarManija(String json_i){
        //Nos conectamos al servidor de BBDD.
        motorSQL.conectar();
        System.out.println("Conectando con el servidor de BBDD.");
        //Eliminamos los corchetes que encierran los objetos.
        json_i = json_i.substring(1,json_i.length()-1);
        //Los valores impares guardan los clientes, ya que en los pares se quedarán las comas que los separan.
        String[] listadoArticulos = json_i.split("\\{|\\}");
        //Recorremos los articulos.
        for(int i=1 ; i<listadoArticulos.length ; i+=2){
            //Obtenemos los valores de cada objeto, siendo nuevamente los impares los que contienen el valor.
            String[] valores = listadoArticulos[i].split(",|:");
            //Recorremos todos valores para eliminar las comillas en aquellos que contengan texto.
            for(int j=1 ; j<valores.length ; j+=2){
                valores[j] = valores[j].replace("\"","");
                //System.out.println(valores[j]+ j);
            }
            //Una vez obtenidos y configurados los valores, creamos la sentencia y realizamos la inserción.
            String SQL = "INSERT INTO articulos (nombre, precio, stock) VALUES ('"+valores[1]+"', "+valores[3]+", "+valores[5]+")";
            System.out.println("Realizando inserción: "+SQL);
            motorSQL.modificar(SQL);
        }
        //Nos desconectamos al servidor de BBDD.
        motorSQL.conectar();
        System.out.println("Desconectando del servidor de BBDD.");
    }
}
