import java.io.IOException;

public record Controlador(Tablero tablero) {

    public int capturarEntrada() throws IOException {
        int key = System.in.read();

        if (key == '\n' || key == '\r') {
            return capturarEntrada();
        }
        return key;
    }


    private int[] actualizarPosicion(int i, int j, int key) {
        if ((key == 'w' || key == 'W') && i > 0) {
            i--;
        } else if ((key == 's' || key == 'S') && i < tablero.getTablero().length - 1) {
            i++;
        } else if ((key == 'a' || key == 'A') && j > 0) {
            j--;
        } else if ((key == 'd' || key == 'D') && j < tablero.getTablero()[0].length - 1) {
            j++;
        }
        return new int[]{i, j};
    }

    public int[] moverJugador(int i, int j, int key) {

        int[] nuevaPosicion = actualizarPosicion(i, j, key);

        if (i != nuevaPosicion[0] || j != nuevaPosicion[1]) {
            this.tablero.resaltarPosicion(nuevaPosicion[0], nuevaPosicion[1]);
            this.tablero.desresaltarPosicion(i, j);
        }

        return nuevaPosicion;
    }
}

