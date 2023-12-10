package model;

import java.sql.*;

public class MotorSQL {

    private String url = "jdbc:mysql://localhost:3306/vinted2";
    private String user = "root";
    private String pass = ""; //1234

    private Statement st;
    private Connection conn;
    private ResultSet rs;

    public void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,pass);
        st = conn.createStatement();
    }

    public ResultSet consultar(String sentenciaSQL) throws SQLException {
        rs = st.executeQuery(sentenciaSQL);
        return rs;
    }

    public int modificar(String sentenciaSQL) throws SQLException {
        int resp = 0;
        resp = st.executeUpdate(sentenciaSQL);
        return resp;
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
