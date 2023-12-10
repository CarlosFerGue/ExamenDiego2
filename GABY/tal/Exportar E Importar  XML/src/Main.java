import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Establecemos las variables de las que dependerá la ejecución.
        String accion = "";
        String tabla = "";
        //Mostramos al usuario el menu con las acciones que puede llevar a cabo.
        System.out.println("¿Que acción desea llevar a cabo?");
        System.out.println("1 - Mostrar datos en estructura XML.");
        System.out.println("2 - Exportar datos a un archivo XML.");
        System.out.println("3 - Importar datos de un archivo XML.");
        //El usuario introduce la opción que quiere ejecutar.
        String s_accion = new Scanner(System.in).nextLine();
        switch(s_accion){
            case "1":
                accion = "MOSTRAR";
                break;
            case "2":
                accion = "EXPORTAR";
                break;
            case "3":
                accion = "IMPORTAR";
                break;
        }
        System.out.println("¿Que tabla deseas " + accion + "?");
        System.out.println("1 - Artículos.");
        System.out.println("2 - Particulares.");
        System.out.println("3 - Tickets.");
        System.out.println("4 - Direcciones.");
        //El usuario introduce la opción que quiere ejecutar.
        String s_tabla = new Scanner(System.in).nextLine();
        switch(s_tabla){
            case "1":
                tabla = "ARTICULOS";
                break;
            case "2":
                tabla = "PARTICULARES";
                break;
            case "3":
                tabla = "TICKETS";
                break;
            case "4":
                tabla = "DIRECCIONES";
                break;
        }
        //Ejecutamos la acción.
        Controller.ejecutar(tabla,accion);
    }
}