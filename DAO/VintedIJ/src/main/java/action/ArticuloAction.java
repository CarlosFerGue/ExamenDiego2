package action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Articulo;
import model.ArticuloDAO;

public class ArticuloAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL": //Este es un findall en el que no te salen los productos de ti mismo.
                cadDestino = findAll(request, response);
                break;
            case "FILTER_USUARIO": //Los que has subido tu como usuario
                cadDestino = productosUsuario(request, response);
                break;
            case "HISTORICO": //Los que has comprado tu como usuario
                cadDestino = historico(request, response);
                break;
            case "DAR_ALTA": //Subes tus productos
                darAlta(request, response);
                break;
            case "PUNTUAR":
                puntuar(request, response);
                break;
            case "TOP10": //Top 10 productos con mas reseñas
                cadDestino = find10(request, response);
                break;
            case "ESTADO":
                cadDestino = estado(request, response);
                break;
            case "PALABRA":
                cadDestino = palabra(request, response);
                break;
            case "COMPRAR":
                cadDestino = String.valueOf(comprar(request, response));
                break;

        }
        return cadDestino;
    }


    private int comprar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArticuloDAO articuloDAO = new ArticuloDAO();

        String idArticulo = request.getParameter("ID_ARTICULO");
        int idIntArticulo = Integer.parseInt(idArticulo);
        String idUsuario = request.getParameter("ID_USUARIO");
        int idIntUsuario = Integer.parseInt(idUsuario);


        Articulo articulo = new Articulo(idIntArticulo, idIntUsuario);
        ArticuloDAO ariticuloDAO = new ArticuloDAO();
        return ariticuloDAO.comprar(articulo);
    }

    private String historico(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArticuloDAO articuloDAO = new ArticuloDAO();
        String id_usuario = request.getParameter("ID");
        ArrayList<Articulo> articulos = articuloDAO.historico(id_usuario);
        return Articulo.toArrayJSon(articulos);
    }


    private String palabra(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String idUsuario = request.getParameter("ID");
        int idInt = Integer.parseInt(idUsuario);
        String palabra = request.getParameter("PALABRA");

        ArticuloDAO articuloDAO = new ArticuloDAO();
        ArrayList<Articulo> articulos = articuloDAO.palabra(palabra, idInt);
        return Articulo.toArrayJSon(articulos);
    }

    private String estado(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String estado2;
        String idUsuario = request.getParameter("ID");
        int idInt = Integer.parseInt(idUsuario);
        String estado = request.getParameter("ESTADO");

        if (estado.equals("Active")) {
            estado2 = "='Active";
        } else {
            estado2 = "!='Active";
        }

        ArticuloDAO articuloDAO = new ArticuloDAO();
        ArrayList<Articulo> articulos = articuloDAO.estado(estado2, idInt);
        return Articulo.toArrayJSon(articulos);
    }

    private int puntuar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArticuloDAO articuloDAO = new ArticuloDAO();

        String idArticulo = request.getParameter("ID");
        int idInt = Integer.parseInt(idArticulo);
        String valoracion = request.getParameter("VALORACION");


        Articulo articulo = new Articulo(idInt, valoracion);
        ArticuloDAO ariticuloDAO = new ArticuloDAO();
        System.out.println(ariticuloDAO.update(articulo));
        return ariticuloDAO.update(articulo);
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArticuloDAO articuloDAO = new ArticuloDAO();
        String id_usuario = request.getParameter("ID");
        ArrayList<Articulo> articulos = articuloDAO.findAllProductos(id_usuario);
        return Articulo.toArrayJSon(articulos);
    }


    private String productosUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArticuloDAO articuloDAO = new ArticuloDAO();
        String id_usuario = request.getParameter("ID");
        ArrayList<Articulo> articulos = articuloDAO.productosUsuario(id_usuario);
        return Articulo.toArrayJSon(articulos);
    }


    //http://localhost:8080/Controller?ACTION=PRODUCTOS.DAR_ALTA&NOMBRE=sa&MARCA=se&ID=53&PRECIO=12&IMAGEN=s&DESCRIPCION=sasa&FECHA=23&ESTADO=bIEN
    private int darAlta(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String idUsuario = request.getParameter("ID");
        int idInt = Integer.parseInt(idUsuario);
        String color = request.getParameter("COLOR");
        String precio = request.getParameter("PRECIO");
        String nombre = request.getParameter("NOMBRE");
        String marca = request.getParameter("MARCA");
        String descripcion = request.getParameter("DESCRIPCION");


        Articulo articulo = new Articulo(idInt, color, precio, nombre, marca, descripcion, "Active");
        ArticuloDAO ariticuloDAO = new ArticuloDAO();
        return ariticuloDAO.add(articulo);
    }

    private String find10(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ArticuloDAO articuloDAO = new ArticuloDAO();
        ArrayList<Articulo> articulos = articuloDAO.find10(null);
        return Articulo.toArrayJSon(articulos);
    }
}
