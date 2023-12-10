package model;

import java.sql.*;

public class MotorORACLE {

    // Configuración para Oracle
    private String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private String user = "tu_usuario_oracle";
    private String pass = "tu_contraseña_oracle";

    private Statement st;
    private Connection conn;
    private ResultSet rs;

    public void conectar() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, pass);
            st = conn.createStatement();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador de Oracle", e);
        }
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



//Motor de FAM coffee shop
//
//package model;
//
//        import java.sql.Connection;
//        import java.sql.DriverManager;
//        import java.sql.ResultSet;
//        import java.sql.SQLException;
//        import java.sql.Statement;
//
//public class MotorSQL {
//
//    private Connection conn;
//    private Statement st;
//    private ResultSet rs;
//
//    private static final String URL = "jdbc:derby://localhost:1527/fam";
//    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
//    private static final String USER = "fam";
//    private static final String PASS = "fam";
//
//    public void connect() {
//        try {
//            conn = DriverManager.getConnection(URL, USER, PASS);
//            st = conn.createStatement();
//        }
//        catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    public int execute(String sql) {
//        int resp = 0;
//        try {
//            resp = st.executeUpdate(sql);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return resp;
//    }
//
//    public ResultSet executeQuery(String sql) {
//        try {
//            rs = st.executeQuery(sql);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return rs;
//    }
//
//    public void disconnect() {
//        try {
//            if (rs != null) {
//                rs.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException ex) {
//        }
//    }
//}
