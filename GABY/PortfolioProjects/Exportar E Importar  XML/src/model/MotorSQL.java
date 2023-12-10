package model;

import javax.xml.transform.Result;
import java.sql.*;

public class MotorSQL {
    //////////////////////////////////////////ATRIBUTOS//////////////////////////////////////////
    private String url = "jdbc:mysql://localhost:3307/particulares";
    private String user = "root";
    private String password = "asir2021";

    private Statement st;
    private Connection conn;
    private ResultSet rs;

    ////////////////////////////////////////////MÉTODOS///////////////////////////////////////////
    //Conexión con la base de datos.
    public void conectar(){
        try{
            conn = DriverManager.getConnection(url,user,password);
            st = conn.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error en la conexión al servidor de bases de datos.");
        }
    }

    //Mostramos por consola una sentencia.
    public ResultSet consultar(String sentencia){
        try{
            rs = st.executeQuery(sentencia);
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error en la obtención de datos de la base de datos.");
        }
        return rs;
    }

    //Ejecutamos una sentencia de modificación de base de datos().
    public void modificar(String sentencia){
        try{
            st.executeUpdate(sentencia);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Cerramos la conexión con la base de datos.
    public void desconectar(){
        try{
            if(rs != null){
                rs.close();
            }
            if(st != null){
                st.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch(SQLException ex){
            ex.getMessage();
            System.out.println("Error en la desconexión del servidor de bases de datos.");
        }
    }

}
