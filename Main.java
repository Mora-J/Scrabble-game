import java.io.IOException;

public class Main {

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

   public static void main(String[] args) {
       Jugador jugador = new Jugador("qhgdyug");
       BolsaFichas bf = new BolsaFichas();
       Tablero tablero = new Tablero();
       Controlador controlador = new Controlador(tablero);
       jugador.rellenarFichas(bf);

       int i = 7; int j = 7;

       try {
          tablero.resaltarPosicion(i, j);
          while (true) {
             limpiarPantalla();
             tablero.mostrarTablero();
             jugador.mostrarFichas();

             // Captura la entrada del usuario
             int key = controlador.capturarEntrada();

             if (key == 'p') {
                System.out.println("¡Has presionado 'p'! Colocando la Ficha...");
                break;
             }

             // Mueve el jugador y actualiza la posición
             int[] nuevaPosicion = controlador.moverJugador(i, j, key);
             i = nuevaPosicion[0];
             j = nuevaPosicion[1];


             System.out.println("\n");

          }
          limpiarPantalla();
          jugador.jugarFichas(tablero, i, j, 0);
          tablero.mostrarTablero();
          jugador.mostrarFichas();



       } catch (IOException e) {
          throw new RuntimeException(e);

       }


    }
}
