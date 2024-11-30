import java.io.IOException;
import java.util.ArrayList;
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
        this.tableroAuxiliar = tablero;
        this.turnoActual = 0;
        this.controlador = new Controlador(tableroAuxiliar);
        this.palabraExtractor = new PalabraExtractor();
    }

    public Juego(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
        this.tablero = new Tablero();
        this.tableroAuxiliar = this.tablero;
        this.controlador = new Controlador(tableroAuxiliar);
        this.palabraExtractor = new PalabraExtractor();
    }

    public void iniciarNuevaPartida() {
        turnoActual = new Random().nextInt(jugadores.length);
        Jugador jugadorActual = jugadores[turnoActual];
        boolean primeraJugada = true;
        ArrayList<int[]> indiceFichasPuestas;
        boolean palabraCompleta;



        System.out.println("¡Nueva partida iniciada!");
        System.out.println("Empieza el jugador: "+ jugadorActual.getAlias());
        BolsaFichas bolsaFichas = new BolsaFichas();
        rellenarFichasJugadores(jugadores, bolsaFichas);


        while (!partidaTerminada()) {
            if (true) {
                palabraCompleta = false;
                indiceFichasPuestas = new ArrayList<>();
                jugadorActual = jugadores[turnoActual];
                Ficha[] atrilCopia = jugadorActual.getFichas().clone();

                if (primeraJugada) {

                    tablero.mostrarTablero();
                    System.out.println("Debe colocar la primera Ficha en el centro del tablero");
                    System.out.println("Utilice las teclas ( W S D A ) para moverse en el tablero, debe presionar ENTER despues de usar cada letra");
                    System.out.println("La casilla en donde se encuentra actualmente se pondra de color amarillo");
                    System.out.println("Para colocar una Ficha presione la letra P y luego ENTER");
                    mostrarFichasEIndices(jugadorActual);
                    ponerPrimeraFicha(jugadorActual);
                    System.out.println("Estas son sus fichas, para utilizarlas debe usar los inidices que se ven debajo");
                    indiceFichasPuestas.add(ponerPrimeraFicha(jugadorActual));
                    tableroAuxiliar.mostrarTablero();
                    mostrarFichasEIndices(jugadorActual);
                    System.out.println("Para colocar otra ficha presione cualquier tecla y luego ENTER");
                    System.out.println("Cuando termine de colocar las fichas y formo una PALABRA presione L y luego ENTER");
                }

                while(!palabraCompleta) {
                    indiceFichasPuestas.add(ponerFicha(jugadorActual));
                    tableroAuxiliar.mostrarTablero();
                    String option = scanner.nextLine();
                    System.out.println("Presione cualquier tecla para poner otra Ficha o Presione L para verificar su palabra");
                    if (option.equals("L")) {
                        palabraCompleta = true;
                    }
                }
                if (verificarIndicesValidos(indiceFichasPuestas)) {
                    if (palabraExtractor.verificarPalabrasFormadas(indiceFichasPuestas, tableroAuxiliar, jugadorActual)){
                        System.out.println("La palabra que has puesto es valida!");
                        turnoActual = (turnoActual + 1) % jugadores.length;
                        primeraJugada = false;
                        tablero = tableroAuxiliar;
                        continue;
                    }
                    System.out.println("La palabra que puso no es una palabra valida!");
                }
                System.out.println("Usted hizo una jugada invalida!");
                jugadorActual.setFichas(atrilCopia);
                tableroAuxiliar = tablero;

            }
        }

        // Mostrar resultado final
        mostrarResultadoFinal();
    }

    private boolean verificarIndicesValidos(ArrayList<int[]> indices) {
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
        int i = tablero.getFilaCentral(); int j = tablero.getColumnaCentral();

        while(true){
            try {
                int key = controlador.capturarEntrada();

                if (key == 'p') {
                    System.out.println("¡Has presionado 'P'! Escoge que Ficha poner...");
                    int indice = obtenerEntradaNumerica();
                    jugador.jugarPrimeraFicha(tableroAuxiliar, indice);
                    break;
                }

            } catch (IOException e) {
                System.out.println("Error al capturar entrada");
            }

        }
        return new int[]{i, j};
    }

    private int[] ponerFicha(Jugador jugador){
        int i = tablero.getFilaCentral(); int j = tablero.getColumnaCentral();

        while(true){
            try {
                int key = controlador.capturarEntrada();

                if (key == 'p') {
                    System.out.println("¡Has presionado 'P'! Escoge que Ficha poner...");
                    int indice = obtenerEntradaNumerica();
                    jugador.jugarFichas(tableroAuxiliar, i , j, indice);
                    break;
                }

                // Mueve el jugador y actualiza la posición
                int[] nuevaPosicion = controlador.moverJugador(i, j, key);
                i = nuevaPosicion[0];
                j = nuevaPosicion[1];

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

        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 0 && opcion <= 6) {
                    entradaValida = true;
                } else {
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
            indices.append("[").append(contadorIndices).append(" ] ");
            contadorIndices++;
        }
        System.out.println(indices);
    }

    private void rellenarFichasJugadores(Jugador[] jugadores, BolsaFichas bolsaFichas){
        for(Jugador jugador : jugadores){
            jugador.rellenarFichas(bolsaFichas);
        }
    }

    private boolean partidaTerminada() {
        // Implementa la lógica para determinar si la partida ha terminado
        return false; // Placeholder
    }

    private void mostrarResultadoFinal() {
        // Implementa la lógica para mostrar el resultado final de la partida
        System.out.println("La partida ha terminado.");
    }
}
