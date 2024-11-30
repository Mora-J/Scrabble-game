import java.util.ArrayList;
import java.util.HashSet;

public class PalabraExtractor {
    public PalabraExtractor() {
    }

    private ListaFichas extraerPalabraVertical(int fila, int columna, Tablero tablero) {
        ListaFichas palabraVertical = new ListaFichas(true);

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

    private ListaFichas extraerPalabraHorizontal(int fila, int columna, Tablero tablero) {
        ListaFichas palabraHorizontal = new ListaFichas(false);

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

    private HashSet<ListaFichas> extraerPalabrasFormadas(ArrayList<int[]> IndiceFichasPuestas, Tablero tablero) {
        HashSet<ListaFichas> palabrasFormadas = new HashSet<>();
        for(int[] indices : IndiceFichasPuestas){
            ListaFichas palabraVertical = extraerPalabraVertical(indices[0], indices[1], tablero);
            ListaFichas palabraHorizontal = extraerPalabraHorizontal(indices[0], indices[1], tablero);

            if (palabraVertical.size() > 1) {
                palabrasFormadas.add(new ListaFichas(palabraVertical));
            }
            if (palabraHorizontal.size() > 1) {
                palabrasFormadas.add(new ListaFichas(palabraHorizontal));
            }
        }
        if (!palabrasFormadas.isEmpty()) {
            return palabrasFormadas;
        }else return null;

    }

    public boolean verificarPalabrasFormadas(ArrayList<int[]> IndiceFichasPuestas, Tablero tablero, Jugador jugador){
        HashSet<ListaFichas> palabrasSet = extraerPalabrasFormadas(IndiceFichasPuestas, tablero);
        int puntosPalabras = 0;

        if (palabrasSet == null) {
            return false;
        } else {

            for (ListaFichas palabra : palabrasSet) {
               if (!RaeVerificador.verificarPalabra(palabra.toString())){
                   return false;
               }
               puntosPalabras += palabra.getPuntaje();
            }

        }
        jugador.addToScoreTotal(puntosPalabras);
        return true;
    }
}
