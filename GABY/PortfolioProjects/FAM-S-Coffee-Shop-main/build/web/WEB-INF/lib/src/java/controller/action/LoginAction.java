package controller.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;
import model.ProductoDAO;
import model.Usuario;
import model.UsuarioDAO;

public class LoginAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "LOGIN_USUARIO":
                cadDestino = findAllUsers(request, response);
                break;
            case "LOGIN_STAFF":
                cadDestino = findAllStaff(request, response);
                break;
            case "REGISTER":
                add(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAllUsers(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> usuarios = usuarioDAO.findAll(null);
        return Usuario.toArrayJSon(usuarios);
    }

    private String findAllStaff(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> usuarios = usuarioDAO.findAll(null);
        return Usuario.toArrayJSon(usuarios);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        Usuario usuario = new Usuario(nombre, correo, contrasena, "usuario");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.add(usuario);
    }
}

/*
    private String findByFilter(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO productoDAO = new ProductoDAO();
        String tipo = request.getParameter("FILTRO");
        ArrayList<Producto> productos = productoDAO.filterType(tipo);
        return Producto.toArrayJSon(productos);
    }*/
