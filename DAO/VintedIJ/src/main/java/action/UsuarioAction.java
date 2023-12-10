package action;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import model.Usuario;
import model.UsuarioDAO;

public class UsuarioAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                //http://localhost:8080/Controller?ACTION=LOGIN.FIND_ALL
                cadDestino = findAll(request, response);
                break;
            case "LOGIN":
                //http://localhost:8080/Controller?ACTION=LOGIN.LOGIN&EMAIL=&PASS=
                cadDestino = login(request);
                break;
            case "REGISTER":
                add(request, response);
                break;
            case"TOP10":
                cadDestino = find10(request,response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> usuarios = usuarioDAO.findAll(null);
        return Usuario.toArrayJSon(usuarios);
    }

    public String login(HttpServletRequest req) throws SQLException {
        String json = "";
        String email = req.getParameter("EMAIL");
        String password = req.getParameter("PASS");
        Usuario usuario = new Usuario(email, password);
        MyLoginData myloginData = new MyLoginData();
        ArrayList<Usuario> lstUsuario = new UsuarioDAO().login(usuario);

        Gson gson = new Gson();
        if (!lstUsuario.isEmpty()) {
            Usuario loggedInUser = lstUsuario.get(0);
            myloginData.setUsuario(loggedInUser);
            myloginData.setIdUsuario(loggedInUser.getId()); // Asegúrate de que exista un método getId() en la clase Usuario
            System.out.println(Usuario.toArrayJSon(lstUsuario));
            System.out.println(loggedInUser);
            return gson.toJson(myloginData);
        } else {
            // Manejar el caso en el que no se encontró un usuario válido
            // Puedes devolver un mensaje de error o algo adecuado a tu aplicación.
            return gson.toJson("Usuario no válido");
        }
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String nombre = request.getParameter("NOMBRE"); //en verde el nombre de la URL
        String correo = request.getParameter("CORREO");
        String contrasena = request.getParameter("CONTRASENA");
        String idString = request.getParameter("ID");
        String apellido1 = request.getParameter("APELLIDO1");
        String apellido2 = request.getParameter("APELLIDO2");
        String user = request.getParameter("USER");
        int id = Integer.parseInt(idString);
        Usuario usuario = new Usuario(id, nombre, correo, contrasena, apellido1, apellido2, user);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.add(usuario);
    }

    private String find10(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> usuarios = usuarioDAO.find10(null);
        return Usuario.toArrayJSon(usuarios);
    }
}

