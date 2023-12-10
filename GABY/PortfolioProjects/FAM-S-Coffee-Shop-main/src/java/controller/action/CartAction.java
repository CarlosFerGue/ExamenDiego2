package controller.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartDAO;

public class CartAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FILTER":
                cadDestino = findByFilter(request, response);
                break;
            case "ADD":
                add(request, response);
                break;
        }
        return cadDestino;
    }
    
    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        CartDAO cartDAO = new CartDAO();
        ArrayList<Cart> carts = cartDAO.findAll(null);
        return Cart.toArrayJSon(carts);
    }
    
    private String findByFilter(HttpServletRequest request, HttpServletResponse response) {
        CartDAO cartDAO = new CartDAO();
        String tipo = request.getParameter("FILTRO");
        //ArrayList<Cart> carts = cartDAO.filterType(tipo);
        //return Cart.toArrayJSon(carts);
        return "asd";
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        String idString = request.getParameter("ID");
        int id = Integer.parseInt(idString);
        //Usuario usuario = new Usuario(id, nombre, correo, contrasena, "usuario");
        //UsuarioDAO usuarioDAO = new UsuarioDAO();
        //usuarioDAO.add(usuario);
    }
    
}
