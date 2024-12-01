import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    private final Jugador[] jugadores;
    private int turnoActual;
    Controlador controlador;
    private final Scanner scanner = new Scanner(System.in);
    private Tablero tableroAuxiliar;
    private final PalabraExtractor palabraExtractor;

    public Juego(Tablero tablero, Jugador[] jugadores) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.tableroAuxiliar = new Tablero(this.tablero);;
        this.turnoActual = 0;
        this.controlador = new Controlador(tableroAuxiliar);
        this.palabraExtractor = new PalabraExtractor();
    }

    public Juego(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
        this.tablero = new Tablero();
        this.tableroAuxiliar = new Tablero(this.tablero);
        this.controlador = new Controlador(tableroAuxiliar);
        this.palabraExtractor = new PalabraExtractor();
    }

    public void iniciarNuevaPartida() {
        turnoActual = new Random().nextInt(jugadores.length);
        Jugador jugadorActual = jugadores[turnoActual];
        boolean primeraJugada = true;
        boolean primeraFichaPuesta = false;
        ArrayList<int[]> indiceFichasPuestas;
        boolean palabraCompleta;
        boolean turnoPasado = false;


        System.out.println("¡Nueva partida iniciada!");
        System.out.println("Empieza el jugador: "+ jugadorActual.getAlias());
        BolsaFichas bolsaFichas = new BolsaFichas();


        while (!partidaTerminada(jugadorActual, bolsaFichas)) {

            this.controlador = new Controlador(tableroAuxiliar);
            rellenarFichasJugadores(jugadores, bolsaFichas);
            jugadorActual = jugadores[turnoActual];
            Ficha[] atrilCopia = jugadorActual.clonarFichas();
            palabraCompleta = false;
            indiceFichasPuestas = new ArrayList<>();


            System.out.println("\nTurno de: " + jugadorActual.getAlias());
            System.out.println("Puntos: "+ jugadorActual.getScore());

            if (!primeraJugada) {
                tablero.mostrarTablero();
                mostrarFichasEIndices(jugadorActual);
            }

            if (primeraJugada) {
                tablero.mostrarTablero();
                System.out.println("Debe colocar la primera Ficha en el centro del tablero");
                System.out.println("Utilice las teclas ( W A S D ) para moverse en el tablero, debe presionar ENTER despues de usar cada letra");
                System.out.println("La casilla en donde se encuentra actualmente se pondra de color amarillo");
                System.out.println("Para colocar una Ficha presione la letra P y luego ENTER");
                System.out.println("Estas son sus fichas, para utilizarlas debe usar los inidices que se ven debajo");
                mostrarFichasEIndices(jugadorActual);
                indiceFichasPuestas.add(ponerPrimeraFicha(jugadorActual));
                tableroAuxiliar.mostrarTablero();
                mostrarFichasEIndices(jugadorActual);
                if (indiceFichasPuestas.getFirst() != null){
                    primeraFichaPuesta= true;
                }
            }

            while (!palabraCompleta && primeraFichaPuesta) {
                System.out.println("Presione cualquier tecla y luego Enter para poner otra Ficha");
                System.out.println("Presione L para verificar su palabra");

                if (!primeraJugada) {
                    System.out.println("Presione 9 para pasar su turno");
                }

                String option = scanner.nextLine().toLowerCase();

                if (option.equals("l")) {
                    palabraCompleta = true;

                } else if (!option.isEmpty() && !option.equals("9")) {
                    System.out.println("Utilice (W A S D) para seleccionar la posicion donde quiere poner la ficha");
                    System.out.println("La ficha debe estar adyacente a otra");

                    indiceFichasPuestas.add(ponerFicha(jugadorActual));
                    tableroAuxiliar.mostrarTablero();
                    mostrarFichasEIndices(jugadorActual);

                } else if (option.equals("9") && !primeraJugada) {
                    palabraCompleta = true;
                    turnoPasado = true;

                } else{
                    System.out.println("Entrada vacía, esperando otra entrada.");
                }
            }

            if (turnoPasado){
                System.out.println("Usted ha pasado su turno!");
                jugadorActual.setFichas(atrilCopia);
                tableroAuxiliar = new Tablero(this.tablero);
                turnoActual = (turnoActual + 1) % jugadores.length;
                continue;
            }


            if(!indiceFichasPuestas.isEmpty()) {
                if (!verificarIndicesValidos(indiceFichasPuestas)) {
                    System.out.println("Usted hizo una jugada invalida!");
                    jugadorActual.setFichas(atrilCopia);
                    tableroAuxiliar = new Tablero(this.tablero);

                } else if (palabraExtractor.verificarPalabrasFormadas(indiceFichasPuestas, tableroAuxiliar, jugadorActual)) {
                    System.out.println("Las palabras que has puesto es valida!");
                    turnoActual = (turnoActual + 1) % jugadores.length;
                    primeraJugada = false;
                    tablero = new Tablero(this.tableroAuxiliar);

                }else{
                    System.out.println("La palabra que puso no es una palabra valida!");
                    jugadorActual.setFichas(atrilCopia);
                    if (primeraJugada) {
                        primeraFichaPuesta = false;
                    }
                    tableroAuxiliar = new Tablero(this.tablero);
                }

            }else{
                System.out.println("No ha ingresado una palabra!");
                jugadorActual.setFichas(atrilCopia);
                tableroAuxiliar = new Tablero(this.tablero);
            }


        }
        mostrarResultadoFinal();
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
                    jugador.jugarPrimeraFicha(tableroAuxiliar, indice);
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
        for (Ficha ficha : jugador.getFichas()) {
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
        return jugadorActual.fichasIsEmpty() && bolsaFichas.getListaFichas().isEmpty();
    }

    private void mostrarResultadoFinal() {
        System.out.println("La partida ha terminado y el ganador es");
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
        }
    }
}
