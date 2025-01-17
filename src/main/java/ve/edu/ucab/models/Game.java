package ve.edu.ucab.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Representa un juego de mesa donde los jugadores colocan fichas en un tablero para formar palabras.
 */
public class Game {

    /**
     * Array de jugadores del juego.
     */
    private Jugador[] jugadores;

    /**
     * Bolsa de fichas para el juego.
     */
    private final BolsaFichas bolsaFichas;

    /**
     * Tablero del juego.
     */
    private final Board board;

    /**
     * Índice del turno actual.
     */
    private int turnoActual;

    /**
     * Jugador que tiene el turno actual.
     */
    private Jugador jugadorActual;

    /**
     * Lista de índices de fichas que han sido puestas en el tablero.
     */
    private ArrayList<int[]> indiceFichasPuestas;


    /**
     * Extractor de palabras formado por las fichas en el tablero.
     */
    private final PalabraExtractor palabraExtractor = new PalabraExtractor();

    /**
     * Indica si es la primera jugada del juego.
     */
    private boolean esPrimeraJugada;

    /**
     * Contador de pases de turno consecutivos.
     */
    private int contadorPases = 0;

    /**
     * Indica si el juego ha finalizado.
     */
    private boolean estaFinalizada = false;

    /**
     * Constructor que inicializa un juego con dos jugadores.
     */
    public Game() {
        this.indiceFichasPuestas = new ArrayList<>();

        jugadores = new Jugador[2];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Jugador();
        }

        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = new Random().nextInt(2);
        jugadorActual = jugadores[turnoActual];
        esPrimeraJugada = true;
    }

    /**
     * Constructor que inicializa un juego con un array de jugadores.
     *
     * @param jugadores Array de jugadores que participarán en el juego.
     */
    public Game(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.indiceFichasPuestas = new ArrayList<>();
        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = new Random().nextInt(2);
        jugadorActual = jugadores[turnoActual];
        esPrimeraJugada = true;

    }

    /**
     * Actualiza el atril de cada jugador rellenando las fichas desde la bolsa.
     */
    public void actualizarAtrilJugadores() {
        for(Jugador j : jugadores) {
            j.rellenarFichas(bolsaFichas);
        }
    }

    /**
     * Coloca una ficha en la posición especificada del tablero.
     *
     * @param i     Índice de la fila en el tablero.
     * @param j     Índice de la columna en el tablero.
     * @param ficha La ficha a colocar.
     */
    public void ponerFicha(int i, int j, Ficha ficha) {
        this.board.getCasillas()[i][j].setFicha(ficha);
        indiceFichasPuestas.add(new int[]{i, j});
    }

    /**
     * Elimina una ficha de la posición especificada del tablero.
     *
     * @param i Índice de la fila en el tablero.
     * @param j Índice de la columna en el tablero.
     */
    public void quitarFicha(int i, int j) {
        Ficha ficha = board.getCasillas()[i][j].getFicha();
        jugadorActual.addToAtril(ficha);
        board.getCasillas()[i][j].setFicha(new Ficha());
        removerIndiceFichas(i, j);
    }

    /**
     * Elimina un índice de la lista de fichas puestas.
     *
     * @param i Índice de la fila en el tablero.
     * @param j Índice de la columna en el tablero.
     */
    private void removerIndiceFichas(int i, int j) {
        for (int k = 0; k < indiceFichasPuestas.size(); k++) {
            int[] indices = indiceFichasPuestas.get(k);
            if (indices[0] == i && indices[1] == j) {
                indiceFichasPuestas.remove(k);
                break;
            }
        }
    }

    /**
     * Confirma si la jugada es válida y procede al siguiente turno si es así.
     *
     * @return true si la jugada es válida, false en caso contrario.
     */
    public boolean confirmarJugada(){
        if (indiceFichasPuestas.isEmpty()) {
            System.out.println("Usted hizo una jugada inválida!");
            return false;
        } else if (verificarPosicionValida(indiceFichasPuestas) && palabraExtractor.verificarPalabrasFormadas(indiceFichasPuestas, board, jugadorActual)) {
            siguienteTurno();
            return true;
        }else{
            System.out.println("Usted hizo una jugada inválida!");
            return false;
        }
    }

    /**
     * Pasa al siguiente turno.
     */
    private void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.length;
        jugadorActual = jugadores[turnoActual];
        fijarCasillas(indiceFichasPuestas);
        indiceFichasPuestas = new ArrayList<>();
        contadorPases = 0;
        if(esPrimeraJugada) {
            esPrimeraJugada = false;
        }
    }

    /**
     * Pasa el turno actual sin realizar ninguna jugada.
     * Guarda la partida pendiente en un archivo JSON.
     */
    public void pasarTurno(){
        JsonUtil.guardarPartidaPendiente(this);
        if (!esPrimeraJugada) {
            turnoActual = (turnoActual + 1) % jugadores.length;
            jugadorActual = jugadores[turnoActual];
            contadorPases++;
        }
    }

    /**
     * Fija las casillas en sus posiciones después de colocar fichas.
     *
     * @param indices Lista de índices de las casillas a fijar.
     */
    private void fijarCasillas(ArrayList<int[]> indices){
        for (int[] indice : indices) {
            board.getCasillas()[indice[0]][indice[1]].setMovable(false);
        }
    }

    /**
     * Verifica si el juego ha terminado.
     *
     * @return true si el juego ha terminado, false en caso contrario.
     */
    public boolean esPartidaTerminada() {
       if((jugadorActual.atrilIsEmpty() && bolsaFichas.isEmpty()) || contadorPases == 3){
           estaFinalizada = true;
           return true;
       } else {
           return false;
       }
    }

    /**
     * Calcula y retorna el índice del jugador ganador.
     *
     * @return El índice del jugador ganador.
     */
    public int calcularGanador() {
        int mayor = 0;
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if(jugador.getScoreInGame() > mayor){
                mayor = jugador.getScoreInGame();
                ganador = jugador;
            }

        }
        return Arrays.asList(jugadores).indexOf(ganador);
    }

    /**
     * Verifica si la posición de las fichas puestas es válida.
     *
     * @param indices Lista de índices de las fichas puestas.
     * @return true si la posición es válida, false en caso contrario.
     */
    private boolean verificarPosicionValida(ArrayList<int[]> indices) {
        return verificarIndicesValidos(indices) && (verificarFichasConectadas(indices) || verificarPrimeraJugada(indices));
    }

    /**
     * Verifica si es la primera jugada y si se ha puesto una ficha en el centro del tablero.
     *
     * @param indices Lista de índices de las fichas puestas.
     * @return true si es la primera jugada y una ficha está en el centro, false en caso contrario.
     */
    private boolean verificarPrimeraJugada(ArrayList<int[]> indices) {
        if (esPrimeraJugada) {
            for (int[] indice : indices) {
                if (indice[0] == 7 && indice[1] == 7) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica si todos los índices de las fichas puestas son válidos.
     *
     * @param indices Lista de índices de las fichas puestas.
     * @return true si todos los índices son válidos, false en caso contrario.
     */
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

    /**
     * Verifica si las fichas puestas están conectadas con otras fichas en el tablero.
     *
     * @param indices Lista de índices de las fichas puestas.
     * @return true si las fichas están conectadas con otras fichas en el tablero, false en caso contrario.
     */
    private boolean verificarFichasConectadas(ArrayList<int[]> indices) {
        indices.removeIf(Objects::isNull);
        if (indices.isEmpty()) {
            return false;
        }

        for (int[] indice : indices) {
            int fila = indice[0];
            int columna = indice[1];

            // Verificar casillas adyacentes
            if ((fila > 0 && !board.getCasillas()[fila - 1][columna].isMovable()) ||
                    (fila < 14 && !board.getCasillas()[fila + 1][columna].isMovable()) ||
                    (columna > 0 && !board.getCasillas()[fila][columna - 1].isMovable()) ||
                    (columna < 14 && !board.getCasillas()[fila][columna + 1].isMovable())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la clave única de los jugadores basada en sus alias.
     *
     * @return La clave de los jugadores.
     */
    public String getClaveJugadores() {
        return Arrays.stream(jugadores).map(Jugador::getAlias).sorted().collect(Collectors.joining("_"));
    }

    /**
     * Obtiene los jugadores del juego.
     *
     * @return Array de jugadores.
     */
    public Jugador[] getJugadores() {
        return jugadores;
    }

    /**
     * Establece los jugadores del juego.
     *
     * @param jugadores Array de jugadores.
     */
    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Obtiene el tablero del juego.
     *
     * @return El tablero.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Obtiene la bolsa de fichas del juego.
     *
     * @return La bolsa de fichas.
     */
    public BolsaFichas getBolsaFichas() {
        return bolsaFichas;
    }

    /**
     * Obtiene el turno actual.
     *
     * @return El turno actual.
     */
    public int getTurnoActual() {
        return turnoActual;
    }

    /**
     * Establece el turno actual.
     *
     * @param turnoActual El turno actual.
     */
    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    /**
     * Obtiene los índices de las fichas puestas.
     *
     * @return Lista de índices de las fichas puestas, o null si está vacía.
     */
    public ArrayList<int[]> getIndiceFichasPuestas() {
        indiceFichasPuestas.removeIf(Objects::isNull);
        if (indiceFichasPuestas.isEmpty()) {
            return null;
        }
        return indiceFichasPuestas;
    }

    /**
     * Establece los índices de las fichas puestas.
     *
     * @param indiceFichasPuestas Lista de índices de las fichas puestas.
     */
    public void setIndiceFichasPuestas(ArrayList<int[]> indiceFichasPuestas) {
        this.indiceFichasPuestas = indiceFichasPuestas;
    }

    /**
     * Obtiene el jugador actual.
     *
     * @return El jugador actual.
     */
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    /**
     * Establece el jugador actual.
     *
     * @param jugadorActual El jugador actual.
     */
    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    /**
     * Verifica si es la primera jugada del juego.
     *
     * @return true si es la primera jugada, false en caso contrario.
     */
    public boolean isEsPrimeraJugada() {
        return esPrimeraJugada;
    }

    /**
     * Establece si es la primera jugada del juego.
     *
     * @param esPrimeraJugada true si es la primera jugada, false en caso contrario.
     */
    public void setEsPrimeraJugada(boolean esPrimeraJugada) {
        this.esPrimeraJugada = esPrimeraJugada;
    }

    /**
     * Obtiene el contador de pases consecutivos de turno.
     *
     * @return El contador de pases.
     */
    public int getContadorPases() {
        return contadorPases;
    }

    /**
     * Establece el contador de pases consecutivos de turno.
     *
     * @param contadorPases El contador de pases.
     */
    public void setContadorPases(int contadorPases) {
        this.contadorPases = contadorPases;
    }

    /**
     * Verifica si el juego ha finalizado.
     *
     * @return true si el juego ha finalizado, false en caso contrario.
     */
    public boolean isEstaFinalizada() {
        return estaFinalizada;
    }

    /**
     * Establece si el juego ha finalizado.
     *
     * @param estaFinalizada true si el juego ha finalizado, false en caso contrario.
     */
    public void setEstaFinalizada(boolean estaFinalizada) {
        this.estaFinalizada = estaFinalizada;
    }
}