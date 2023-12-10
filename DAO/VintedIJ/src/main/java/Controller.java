import action.ArticuloAction;
import action.UsuarioAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class  Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");

        switch (arrayAction[0]) {
            case "PRODUCTOS":
                try {
                        out.print(new ArticuloAction().execute(request, response));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "USUARIO":
                try {
                    out.print(new UsuarioAction().execute(request, response));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
//            case "CART":
//                out.print(new CartAction().execute(request, response));
//                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}