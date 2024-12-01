import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private int turnoActual;
    private Jugador jugadorActual;
    private boolean primeraJugada;
    private boolean primeraFichaPuesta;
    private boolean jugadaCompleta;
    private boolean turnoPasado;
    private boolean palabraCancelada;
    private ArrayList<int[]> indiceFichasPuestas;
    private final BolsaFichas bolsaFichas;
    private Controlador controlador;
    private Tablero tablero;
    private Tablero tableroAuxiliar;
    private final Jugador[] jugadores;
    private transient Scanner scanner = new Scanner(System.in);
    private final PalabraExtractor palabraExtractor = new PalabraExtractor();
    private boolean partidaTerminada = false;

    public void reInicializarScanner(){
        scanner = new Scanner(System.in);
    }

    public Juego(Jugador[] jugadores, Tablero tablero) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.tableroAuxiliar = new Tablero(tablero);
        this.turnoActual = 0;
        this.jugadorActual = null;
        this.primeraJugada = true;
        this.primeraFichaPuesta = false;
        this.jugadaCompleta = false;
        this.turnoPasado = false;
        this.palabraCancelada = false;
        this.indiceFichasPuestas = new ArrayList<>();
        this.bolsaFichas = new BolsaFichas();
        this.controlador = new Controlador(tableroAuxiliar);
    }

    public Juego(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.tableroAuxiliar = new Tablero(tablero);
        this.turnoActual = 0;
        this.jugadorActual = null;
        this.primeraJugada = true;
        this.primeraFichaPuesta = false;
        this.jugadaCompleta = false;
        this.turnoPasado = false;
        this.palabraCancelada = false;
        this.indiceFichasPuestas = new ArrayList<>();
        this.bolsaFichas = new BolsaFichas();
        this.controlador = new Controlador(tableroAuxiliar);
    }

    public void iniciarNuevaPartida() {
        inicializarNuevoJuego();
        while (!partidaTerminada(jugadores[turnoActual], bolsaFichas)) {
            gestionarTurno();
        }
        mostrarResultadoFinal();
    }

    public void continuarPartida() {
        inicializarJuegoAnterior();
        while (!partidaTerminada(jugadores[turnoActual], bolsaFichas)) {
            gestionarTurno();
        }
        mostrarResultadoFinal();
    }

    private void inicializarJuegoAnterior() {
        jugadorActual = jugadores[turnoActual];
        indiceFichasPuestas = new ArrayList<>();

        System.out.println("¡Continua la partida anterior!");
        System.out.println("Le toca al jugador: " + jugadorActual.getAlias());
    }

    private void inicializarNuevoJuego() {
        turnoActual = new Random().nextInt(jugadores.length);
        jugadorActual = jugadores[turnoActual];
        primeraJugada = true;
        primeraFichaPuesta = false;
        jugadaCompleta = false;
        turnoPasado = false;
        palabraCancelada = false;
        indiceFichasPuestas = new ArrayList<>();

        System.out.println("¡Nueva partida iniciada!");
        System.out.println("Empieza el jugador: " + jugadorActual.getAlias());
    }

    private void gestionarTurno() {
        controlador = new Controlador(tableroAuxiliar);
        rellenarFichasJugadores(jugadores, bolsaFichas);
        jugadorActual = jugadores[turnoActual];
        Ficha[] atrilCopia = jugadorActual.clonarFichas();
        jugadaCompleta = false;
        turnoPasado = false;
        palabraCancelada = false;
        indiceFichasPuestas = new ArrayList<>();

        System.out.println("\nTurno de: " + jugadorActual.getAlias());
        System.out.println("Puntos: " + jugadorActual.getScore());

        if (primeraJugada) {
            manejarPrimeraJugada();
        } else {
            manejarJugadaRegular();
        }

        manejarFinTurno(atrilCopia);
    }

    private void manejarPrimeraJugada() {
        tablero.mostrarTablero();
        System.out.println("Debe colocar la primera Ficha en el centro del tablero.");
        System.out.println("Utilice las teclas (W A S D) para moverse en el tablero, debe presionar ENTER después de usar cada letra.");
        System.out.println("Para colocar una Ficha presione la letra P y luego ENTER.");
        mostrarFichasEIndices(jugadorActual);

        indiceFichasPuestas.add(ponerPrimeraFicha(jugadorActual));
        tableroAuxiliar.mostrarTablero();
        mostrarFichasEIndices(jugadorActual);

        if (indiceFichasPuestas.getFirst() != null) {
            primeraFichaPuesta = true;
        }

        gestionarEntradaUsuario();
    }

    private void manejarJugadaRegular() {
        tablero.mostrarTablero();
        mostrarFichasEIndices(jugadorActual);
        gestionarEntradaUsuario();
    }

    private void gestionarEntradaUsuario() {
        while (!jugadaCompleta && primeraFichaPuesta) {
            System.out.println("Presione cualquier tecla y luego Enter para poner otra Ficha.");
            System.out.println("Presione L para verificar su palabra.");
            System.out.println("Presione C para CANCELAR su palabra.");

            if (!primeraJugada) {
                System.out.println("Presione 9 para pasar su turno");
            }

            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "l":
                    jugadaCompleta = true;
                    break;
                case "9":
                    if (!primeraJugada) {
                        confirmarPasarTurno();
                    }
                    break;
                case "c":
                    jugadaCompleta = true;
                    palabraCancelada = true;
                    break;
                default:
                    if (!option.isEmpty()) {
                        System.out.println("Utilice (W A S D) para seleccionar la posición donde quiere poner la ficha.");
                        System.out.println("La ficha debe estar adyacente a otra.");
                        indiceFichasPuestas.add(ponerFicha(jugadorActual));
                        tableroAuxiliar.mostrarTablero();
                        mostrarFichasEIndices(jugadorActual);
                    } else {
                        System.out.println("Entrada vacía, esperando otra entrada.");
                    }
                    break;
            }
        }
    }

    private void confirmarPasarTurno() {
        System.out.println("Estas seguro de querer pasar turno? Y/N");
        while (true) {
            String option2 = scanner.nextLine().toLowerCase();
            if (option2.equals("y")) {
                jugadaCompleta = true;
                turnoPasado = true;
                break;
            } else if (option2.equals("n")) {
                System.out.println("Usted NO ha saltado su turno");
                break;
            } else {
                System.out.println("Responda Y/N (SI o NO)");
            }
        }
    }

    private void manejarFinTurno(Ficha[] atrilCopia) {
        if (turnoPasado) {
            System.out.println("Usted ha pasado su turno!");
            jugadorActual.setFichas(atrilCopia);
            tableroAuxiliar = new Tablero(this.tablero);
            turnoActual = (turnoActual + 1) % jugadores.length;
            return;
        } else if (palabraCancelada) {
            System.out.println("Usted cancelo su palabra, coloque otra!");
            jugadorActual.setFichas(atrilCopia);
            if (primeraJugada) {
                primeraFichaPuesta = false;
            }
            tableroAuxiliar = new Tablero(this.tablero);
            return;
        }

        if (!indiceFichasPuestas.isEmpty()) {
            if (!verificarIndicesValidos(indiceFichasPuestas)) {
                System.out.println("Usted hizo una jugada inválida!");
                jugadorActual.setFichas(atrilCopia);
                tableroAuxiliar = new Tablero(this.tablero);
            } else if (palabraExtractor.verificarPalabrasFormadas(indiceFichasPuestas, tableroAuxiliar, jugadorActual)) {
                turnoActual = (turnoActual + 1) % jugadores.length;
                primeraJugada = false;
                tablero = new Tablero(this.tableroAuxiliar);
            } else {
                System.out.println("La palabra que puso no es válida!");
                jugadorActual.setFichas(atrilCopia);
                if (primeraJugada) {
                    primeraFichaPuesta = false;
                }
                tableroAuxiliar = new Tablero(this.tablero);
            }
        } else {
            System.out.println("No ha ingresado una palabra!");
            jugadorActual.setFichas(atrilCopia);
            tableroAuxiliar = new Tablero(this.tablero);
        }
    }

    private boolean verificarIndicesValidos(ArrayList<int[]> indices) {
        indices.removeIf(Objects::isNull);
        if (indices.isEmpty()){
            return false;
        }

        int i = indices.getFirst()[0];
        int j = indices.getFirst()[1];

        for(int[] indice : indices) {
            if (indice[0] != i && indice[1] != j) {
                return false;
            }
        }
        return true;
    }

    private int[] ponerPrimeraFicha(Jugador jugador){
        int i = tablero.getFilaCentral();
        int j = tablero.getColumnaCentral();

        while(true){
            try {
                int key = controlador.capturarEntrada();

                if (key == 'p' || key == 'P') {
                    System.out.println("¡Has presionado 'P'! Escoge que Ficha poner...");
                    int indice = obtenerEntradaNumerica();
                    if (indice == 9){
                        return null;
                    }
                    if (!jugador.jugarPrimeraFicha(tableroAuxiliar, indice)) {
                        return null;
                    }
                    break;
                }else{
                    System.out.println("Presiona 'P' para poder escoger una ficha");
                }

            } catch (IOException e) {
                System.out.println("Error al capturar entrada");
            }

        }
        return new int[]{i, j};

    }

    private int[] ponerFicha(Jugador jugador){
        int i = tablero.getFilaCentral();
        int j = tablero.getColumnaCentral();

        while(true){
            try {
                int key = controlador.capturarEntrada();

                if (key == 'p' || key == 'P') {
                    System.out.println("¡Has presionado 'P'! Escoge que Ficha poner...");
                    int indice = obtenerEntradaNumerica();
                    tableroAuxiliar.desresaltarPosicion(i, j);

                    if (indice == 9){
                        return null;
                    }

                    if (!jugador.jugarFichas(tableroAuxiliar, i , j, indice)) {
                        return null;
                    }
                    break;

                } else if (key != 'w' && key != 'W' && key != 'S' && key != 's' && key != 'a' && key != 'A' && key != 'd' && key != 'D') {
                    System.out.println("Presione alguna de estas teclas para moverse W A S D, presione P para escoger que ficha poner");
                }

                // Mueve el jugador y actualiza la posición
                int[] nuevaPosicion = controlador.moverJugador(i, j, key);
                i = nuevaPosicion[0];
                j = nuevaPosicion[1];

                tableroAuxiliar.mostrarTablero();
                mostrarFichasEIndices(jugador);

            } catch (IOException e) {
                System.out.println("Error al capturar entrada");
            }

        }
        return new int[]{i, j};
    }

    public int obtenerEntradaNumerica() {
        int opcion = -1;
        boolean entradaValida = false;

        System.out.println("Ingresa el indice de la ficha que quieres jugar (0 a 6):");
        System.out.println("Ingresa 9 para cancelar");
        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion >= 0 && opcion <= 6) {
                    entradaValida = true;
                } else if (opcion == 9) {
                    return opcion;
                }else {
                    System.out.println("Entrada invalida! Ingresa un indice valido (0 a 6):");
                }
            } else {
                System.out.println("Entrada invalida! Ingresa un numero entero:");
                scanner.next();
            }
        }
        return opcion;
    }

    private void mostrarFichasEIndices(Jugador jugador){
        jugador.mostrarFichas();
        int contadorIndices = 0;
        StringBuilder indices = new StringBuilder();
        for (Ficha _ : jugador.getFichas()) {
            indices.append("[").append(contadorIndices).append(" ]");
            contadorIndices++;
        }
        System.out.println(indices);
    }

    private void rellenarFichasJugadores(Jugador[] jugadores, BolsaFichas bolsaFichas){
        for(Jugador jugador : jugadores){
            jugador.rellenarFichas(bolsaFichas);
        }
    }

    private boolean partidaTerminada(Jugador jugadorActual, BolsaFichas bolsaFichas) {
        if (jugadorActual.fichasIsEmpty() && bolsaFichas.getListaFichas().isEmpty()){
        partidaTerminada = true;
        return true;
        }else{
            return false;
        }
    }

    private void mostrarResultadoFinal() {
        System.out.println("La partida ha terminado y el ganador es:");
        int mayor = 0;
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if(jugador.getScore() > mayor){
                mayor = jugador.getScore();
                ganador = jugador;
            }
        }
        if (ganador != null) {
            System.out.println(ganador.getAlias());
            System.out.println("Con " + ganador.getScore() + "puntos !!!");
        }
    }

    public boolean isPartidaTerminada() {
        return partidaTerminada;
    }
}

