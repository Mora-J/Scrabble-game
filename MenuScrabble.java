import java.util.Random;
import java.util.Scanner;

public class MenuScrabble {
    private final Scanner scanner;
    private boolean salir;

    public MenuScrabble() {
        scanner = new Scanner(System.in);
        salir = false;
    }

    private String menuPrincipal() {
                return """
        =======================================
                  Menú de Opciones
        =======================================
        [1]- Jugar una partida nueva
        [2]- Continuar la partida anterior
        [3]- Ver Score de los jugadores
        [0]- Salir
        =======================================
        Selecciona una opción:\s
        """;
    }

    private Jugador ingresarUsuario(int tipoJugador) {
        System.out.print("Ingrese el usuario del jugador " + tipoJugador + ": " );
        String nombre = scanner.next();
        return new Jugador(nombre);
    }

    public void mostrarMenu() {

        while (!salir) {
            limpiarPantalla();
            System.out.println(menuPrincipal());
            String opcion = scanner.next();

            switch (opcion) {
                case "1":
                    jugarNuevaPartida();
                    break;
                case "2":
                    continuarPartidaAnterior();
                    break;
                case "3":
                    verScoreJugadores();
                    break;
                case "0":
                    salir = true;
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    private void jugarNuevaPartida() {
    }

    private void continuarPartidaAnterior() {
        // Implementa la lógica para continuar la partida anterior aquí
        System.out.println("Continuando la partida anterior...");
    }

    private void verScoreJugadores() {
        // Implementa la lógica para ver el score de los jugadores aquí
        System.out.println("Mostrando el score de los jugadores...");
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
