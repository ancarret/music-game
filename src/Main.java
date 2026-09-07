import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Instrument instrument;
        Player p;

        System.out.println("Bienvenido al mejor juego de simulador de carrera musical");
        System.out.println();
        System.out.println("1. Crear nuevo personaje");
        System.out.println("Pulse cualquier otro boton para salir");

        String option = scanner.nextLine();
        if(option.equals("1")){
            System.out.print("Elige un nombre para tu personaje: ");
            String name = scanner.nextLine();

            System.out.println("Elige un instrumento incial: ");
            System.out.println("1. Guitarra");
            System.out.println("2. Piano");
            String input_instrument = scanner.nextLine();

            if(input_instrument.equals("1")){
                instrument = Instrument.GUITAR;
            } else {
                instrument = Instrument.PIANO;
            }
            p = new Player(name, instrument);
            p.printSummary();
        } else {
            System.exit(0);
        }
    }
}