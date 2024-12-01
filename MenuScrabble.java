import java.util.Scanner;

public class MenuScrabble {
    private final Scanner scanner;
    private boolean salir;
    private Jugador[] jugadores;

    public MenuScrabble() {
        scanner = new Scanner(System.in);
        salir = false;
        this.jugadores = new Jugador[]{ingresarUsuario(1), ingresarUsuario(2)};
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
        Juego nuevoJuego = new Juego(jugadores);
        nuevoJuego.iniciarNuevaPartida();
    }

    private void continuarPartidaAnterior() {
        System.out.println("Continuando la partida anterior...");
        Juego continuarJuego = new Juego(jugadores);
        continuarJuego = JsonUtil.cargarPartidaPendiente(continuarJuego.getClaveJugadores());
        try {
            if (!continuarJuego.isPartidaTerminada()) {
                continuarJuego.reInicializarScanner();
                continuarJuego.continuarPartida();
            }else{
                System.out.println("Estos jugadores no tienen partidas pendientes, la Partida fue Terminada, porfavor inicien una nueva partida");
            }
        }catch (NullPointerException e) {
            System.out.println("Estos jugadores no tienen una partida guardada o su partida fue eliminada, porfavor inicien una nueva partida");
        }

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
