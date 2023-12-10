package controller.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;
import model.ProductoDAO;

public class ProductoAction implements IAction {

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
            case "PRICE":
                cadDestino = findByPrice(request, response);
        }
        return cadDestino;
    }
    
    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> productos = productoDAO.findAll(null);
        return Producto.toArrayJSon(productos);
    }

    private String findByFilter(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO productoDAO = new ProductoDAO();
        String tipo = request.getParameter("FILTRO");
        ArrayList<Producto> productos = productoDAO.filterType(tipo);
        return Producto.toArrayJSon(productos);
    }
    
    private String findByPrice(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO productoDAO = new ProductoDAO();
        String tipo = request.getParameter("RANGO");
        ArrayList<Producto> productos = productoDAO.filterType(tipo);
        return Producto.toArrayJSon(productos);
    }
}
