import model.ArticulosDAO;
import model.DireccionesDAO;
import model.ParticularesDAO;
import model.TicketsDAO;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    public static void ejecutar(String tabla, String action) throws SQLException, ParserConfigurationException, IOException, SAXException {
        switch (tabla){
            case "ARTICULOS":
                new ArticulosDAO().ejecutar(action);
                break;
            case "PARTICULARES":
                new ParticularesDAO().ejecutar(action);
                break;
            case "TICKETS":
                new TicketsDAO().ejecutar(action);
                break;
            case "DIRECCIONES":
                new DireccionesDAO().ejecutar(action);
                break;
        }
    }
}
