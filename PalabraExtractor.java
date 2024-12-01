import java.util.ArrayList;
import java.util.HashSet;

public class PalabraExtractor {
    public PalabraExtractor() {
    }

    private Palabra extraerPalabraVertical(int fila, int columna, Tablero tablero) {
        Palabra palabraVertical = new Palabra(true);

        // Extraer palabra vertical hacia abajo
        for (int i = fila; i < tablero.getTablero().length; i++) {
            if (!tablero.getTablero()[i][columna].getLetra().equals("  ")) {
                palabraVertical.add(tablero.getTablero()[i][columna]);
            } else break;
        }

        // Extraer palabra vertical hacia arriba
        for (int x = fila - 1; x >= 0; x--) {
            if (!tablero.getTablero()[x][columna].getLetra().equals("  ")) {
                palabraVertical.addFirst(tablero.getTablero()[x][columna]);
            } else break;
        }
        return palabraVertical;
    }

    private Palabra extraerPalabraHorizontal(int fila, int columna, Tablero tablero) {
        Palabra palabraHorizontal = new Palabra(false);

        // Extraer palabra horizontal hacia la derecha
        for (int j = columna; j < tablero.getTablero()[0].length; j++) {
            if (!tablero.getTablero()[fila][j].getLetra().equals("  ")) {
                palabraHorizontal.add(tablero.getTablero()[fila][j]);
            } else {
                break;
            }
        }

        // Extraer palabra horizontal hacia la izquierda
        for (int y = columna - 1; y >= 0; y--) {
            if (!tablero.getTablero()[fila][y].getLetra().equals("  ")) {
                palabraHorizontal.addFirst(tablero.getTablero()[fila][y]);
            } else break;
        }
        return palabraHorizontal;
    }

    private HashSet<Palabra> extraerPalabrasFormadas(ArrayList<int[]> IndiceFichasPuestas, Tablero tablero) {
        HashSet<Palabra> palabrasFormadas = new HashSet<>();
        for(int[] indices : IndiceFichasPuestas){
            Palabra palabraVertical = extraerPalabraVertical(indices[0], indices[1], tablero);
            Palabra palabraHorizontal = extraerPalabraHorizontal(indices[0], indices[1], tablero);

            if (palabraVertical.size() > 1) {
                palabrasFormadas.add(new Palabra(palabraVertical));
            }
            if (palabraHorizontal.size() > 1) {
                palabrasFormadas.add(new Palabra(palabraHorizontal));
            }
        }
        if (!palabrasFormadas.isEmpty()) {
            return palabrasFormadas;
        }else return null;

    }

    public boolean verificarPalabrasFormadas(ArrayList<int[]> IndiceFichasPuestas, Tablero tablero, Jugador jugador){
        HashSet<Palabra> palabrasSet = extraerPalabrasFormadas(IndiceFichasPuestas, tablero);
        int puntosPalabras = 0;
        int cantidadPalabras = 0;

        if (palabrasSet == null) {
            return false;
        } else {

            for (Palabra palabra : palabrasSet) {
               if (!RaeVerificador.verificarPalabra(palabra.toString().toLowerCase())){
                   System.out.println("Palabra invalida: " + palabra);
                   return false;
               }
               puntosPalabras += palabra.getPuntaje();
               cantidadPalabras++;
            }

        }
        jugador.addToScore(puntosPalabras);
        jugador.addToCantidadPalabrasColocadas(cantidadPalabras);

        System.out.print("Palabras validas: ");
        for (Palabra palabra : palabrasSet) {
            System.out.print(palabra.toString() + ", ");
        }

        System.out.println("\nHas ganado: " + puntosPalabras + " puntos!!!");
        return true;
    }
}
