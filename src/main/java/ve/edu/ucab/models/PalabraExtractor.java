package ve.edu.ucab.models;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Clase PalabraExtractor.
 * Esta clase se utiliza para extraer y verificar palabras formadas en un tablero de juego.
 */
public class PalabraExtractor {

    /**
     * Constructor de la clase PalabraExtractor.
     */
    public PalabraExtractor() {
    }

    /**
     * Extrae una palabra vertical en el tablero a partir de una posición dada.
     *
     * @param fila    La fila inicial de la extracción.
     * @param columna La columna inicial de la extracción.
     * @param board   El tablero del juego.
     * @return Una instancia de Palabra que representa la palabra vertical extraída.
     */
    private Palabra extraerPalabraVertical(int fila, int columna, Board board) {
        Palabra palabraVertical = new Palabra(true);

        // Extraer palabra vertical hacia abajo
        for (int i = fila; i < board.getCasillas().length; i++) {
            if (!board.getCasillas()[i][columna].getFicha().isEmpty()) {
                palabraVertical.add(board.getCasillas()[i][columna]);
            } else break;
        }

        // Extraer palabra vertical hacia arriba
        for (int x = fila - 1; x >= 0; x--) {
            if (!board.getCasillas()[x][columna].getFicha().isEmpty()) {
                palabraVertical.addFirst(board.getCasillas()[x][columna]);
            } else break;
        }
        return palabraVertical;
    }

    /**
     * Extrae una palabra horizontal en el tablero a partir de una posición dada.
     *
     * @param fila    La fila inicial de la extracción.
     * @param columna La columna inicial de la extracción.
     * @param board   El tablero del juego.
     * @return Una instancia de Palabra que representa la palabra horizontal extraída.
     */
    private Palabra extraerPalabraHorizontal(int fila, int columna, Board board) {
        Palabra palabraHorizontal = new Palabra(false);

        // Extraer palabra horizontal hacia la derecha
        for (int j = columna; j < board.getCasillas()[0].length; j++) {
            if (!board.getCasillas()[fila][j].getFicha().isEmpty()) {
                palabraHorizontal.add(board.getCasillas()[fila][j]);
            } else {
                break;
            }
        }

        // Extraer palabra horizontal hacia la izquierda
        for (int y = columna - 1; y >= 0; y--) {
            if (!board.getCasillas()[fila][y].getFicha().isEmpty()) {
                palabraHorizontal.addFirst(board.getCasillas()[fila][y]);
            } else break;
        }
        return palabraHorizontal;
    }

    /**
     * Extrae las palabras formadas en el tablero a partir de los índices de las fichas puestas.
     *
     * @param IndiceFichasPuestas Lista de los índices de las fichas puestas.
     * @param board               El tablero del juego.
     * @return Un conjunto de instancias de Palabra que representan las palabras formadas.
     */
    private HashSet<Palabra> extraerPalabrasFormadas(ArrayList<int[]> IndiceFichasPuestas, Board board) {
        HashSet<Palabra> palabrasFormadas = new HashSet<>();
        for (int[] indices : IndiceFichasPuestas) {
            Palabra palabraVertical = extraerPalabraVertical(indices[0], indices[1], board);
            Palabra palabraHorizontal = extraerPalabraHorizontal(indices[0], indices[1], board);

            if (palabraVertical.size() > 1) {
                palabrasFormadas.add(new Palabra(palabraVertical));
            }
            if (palabraHorizontal.size() > 1) {
                palabrasFormadas.add(new Palabra(palabraHorizontal));
            }
        }
        if (!palabrasFormadas.isEmpty()) {
            return palabrasFormadas;
        } else return null;
    }

    /**
     * Verifica las palabras formadas en el tablero y actualiza el puntaje del jugador.
     *
     * @param IndiceFichasPuestas Lista de los índices de las fichas puestas.
     * @param board               El tablero del juego.
     * @param jugador             El jugador que ha formado las palabras.
     * @return true si todas las palabras formadas son válidas, false en caso contrario.
     */
    public boolean verificarPalabrasFormadas(ArrayList<int[]> IndiceFichasPuestas, Board board, Jugador jugador) {
        HashSet<Palabra> palabrasSet = extraerPalabrasFormadas(IndiceFichasPuestas, board);
        int cantidadPalabras = 0;

        if (palabrasSet == null) {
            return false;
        } else {
            for (Palabra palabra : palabrasSet) {
                if (!RaeVerificador.verificarPalabra(palabra.toString().toLowerCase())) {
                    System.out.println("Palabra invalida: " + palabra);
                    return false;
                }
                cantidadPalabras++;
            }
        }

        jugador.addToScore(obtenerPuntajePalabras(palabrasSet));
        jugador.addToCantidadPalabrasColocadas(cantidadPalabras);

        System.out.print("Palabras validas: ");
        for (Palabra palabra : palabrasSet) {
            System.out.print(palabra.toString() + ", ");
        }

        return true;
    }

    /**
     * Calcula el puntaje total de un conjunto de palabras, considerando sus multiplicadores.
     *
     * @param palabrasSet El conjunto de palabras a evaluar.
     * @return El puntaje total de todas las palabras en el conjunto.
     */
    private int obtenerPuntajePalabras(HashSet<Palabra> palabrasSet){
        int puntajeTotal = 0;
        int puntajePorPalabra = 0;

        for (Palabra palabra : palabrasSet) {
            puntajePorPalabra += (palabra.getPuntaje() * palabra.getMultiplicador());
            puntajeTotal += puntajePorPalabra;
            System.out.println("Puntaje total: " + puntajePorPalabra);
            puntajePorPalabra = 0;
        }

        return puntajeTotal;
    }
}

