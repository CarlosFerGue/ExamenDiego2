import model.ArticulosDAO;
import model.DireccionesDAO;
import model.ParticularesDAO;
import model.TicketsDAO;

public class Controller {
    public static void ejecutar(String tabla, String accion){
        switch(tabla){
            case "ARTICULOS":
                new ArticulosDAO().ejecutar(accion);
                break;
            case "PARTICULARES":
                new ParticularesDAO().ejecutar(accion);
                break;
            case "TICKETS":
                new TicketsDAO().ejecutar(accion);
                break;
            case "DIRECCIONES":
                new DireccionesDAO().ejecutar(accion);
                break;
        }
    }
}
