import java.awt.event.InputEvent;
import java.util.Scanner;


public class Home {

    public static void main(String[] args) {
        String x = "0";
        switch (x) {
            case "0":
                
                Scanner scanner = new Scanner(System.in);
                System.out.println("================================================================================================");
                System.out.println("1) Cargar los archivos txt de la carpeta 'txt files' a tipo Fasta en la carpeta 'Fasta files'.");
                System.out.println("2) Eliminar las secuencias repetidas.");
                System.out.println("3) Realizar la traducción a proteinas de una cadena de nucleotidos.");
                System.Out.println("================================================================================================");
                System.out.println("Eliga una opción:");

                x = scanner.nextLine();

            case 1:
            ;
            case 2:
            case 4:
                break;
            default:
            System.out.println("Opción no valida, intente otra opción.");

        }
    }

    }
}
