package model;

import java.sql.*;
import java.util.ResourceBundle;

public class MotorSQL {

    private String url = "jdbc:mysql://localhost:3306/particulares";
    private String user = "root";
    private String pass = "";

    private Statement st;
    private Connection conn;
    private ResultSet rs;

    public void conectar() throws SQLException {
        conn = DriverManager.getConnection(url,user,pass);
        st = conn.createStatement();
    }

    public ResultSet consultar(String sentenciaSQL) throws SQLException {
        rs = st.executeQuery(sentenciaSQL);
        return rs;
    }

    public void modificar(String sentenciaSQL) throws SQLException {
        st.executeUpdate(sentenciaSQL);
    }

    public void desconectar() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
