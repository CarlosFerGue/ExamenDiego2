import Model.ArticulosDAO;

public class Controller {
    public static void ejecutar(String tabla, String accion){
        switch(tabla){
            case "ARTICULOS":
                new ArticulosDAO().ejecutar(accion);
                break;
        }
    }
}
