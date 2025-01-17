package ve.edu.ucab.models;

import javafx.scene.image.Image;
import ve.edu.ucab.models.bonificadoresStrategy.*;

import java.util.Objects;

/**
 * La clase Board representa un tablero de juego compuesto por una matriz de casillas.
 */
public class Board {
    private Casilla[][] casillas;

    /**
     * Devuelve la matriz de casillas del tablero.
     *
     * @return casillas La matriz de casillas.
     */
    public Casilla[][] getCasillas() {
        return casillas;
    }

    /**
     * Establece la matriz de casillas del tablero.
     *
     * @param casillas La matriz de casillas a establecer.
     */
    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }


    /**
     * Constructor de la clase Board. Inicializa el tablero de juego con una
     * matriz de casillas de 15x15 y asigna estrategias de bonificación a cada
     * casilla según las reglas del juego.
     */
    public Board() {
        this.casillas = new Casilla[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                BonificacionStrategy bonificador = new SinBonificacionStrategy(); // Estrategia por defecto

                // Bonificación de Palabra Triple
                if ((i == 0 || i == 14) && (j == 0 || j == 7 || j == 14) ||
                        (i == 7) && (j == 0 || j == 14)) {
                    bonificador = new PalabraTripleStrategy();
                }
                // Bonificación de Palabra Doble
                else if (i == j || i + j == 14) {
                    bonificador = new PalabraDobleStrategy();
                }
                // Bonificación de Letra Triple
                else if ((i == 5 || i == 9) && (j == 1 || j == 5 || j == 9 || j == 13) ||
                        (i == 1 || i == 13) && (j == 5 || j == 9)) {
                    bonificador = new LetraTripleStrategy();
                }
                // Bonificación de Letra Doble
                else if ((i == 3 || i == 11) && (j == 0 || j == 7 || j == 14) ||
                        (i == 0 || i == 7 || i == 14) && (j == 3 || j == 11) ||
                        (i == 2 || i == 12) && (j == 6 || j == 8) ||
                        (i == 6 || i == 8) && (j == 2 || j == 6 || j == 8 || j == 12) ||
                        (i == 3 || i == 11) && (j == 3 || j == 11)) {
                    bonificador = new LetraDobleStrategy();
                }
                // Bonificaciones centrales manuales
                if ((i == 6 && j == 6) || (i == 8 && j == 8) || (i == 6 && j == 8) || (i == 8 && j == 6)) {
                    bonificador = new LetraDobleStrategy();
                }
                if ((i == 5 && j == 5) || (i == 5 && j == 9) || (i == 9 && j == 5) || (i == 9 && j == 9)) {
                    bonificador = new LetraTripleStrategy();
                }

                this.casillas[i][j] = new Casilla(bonificador);
            }
        }
        //this.casillas[7][7].setImagen(new Image(String.valueOf(getClass().getResource("/images/casillaInicial.png"))));
    }

    /**
     * Constructor que crea una copia profunda de un objeto de tipo Board.
     *
     * @param board el objeto Board a copiar.
     */
    public Board(Board board) {
        this.casillas = new Casilla[board.getCasillas().length][board.getCasillas().length];
        for (int i = 0; i < board.getCasillas().length; i++) {
            for (int j = 0; j < board.getCasillas().length; j++) {
                this.casillas[i][j] = board.getCasillas()[i][j].clone();
            }
        }

    }

    /**
     * Recarga el tablero de juego, estableciendo las bonificaciones y las imágenes de las casillas.
     * Este metodo inicializa cada casilla del tablero con una bonificación específica y la imagen
     * correspondiente a esa bonificación. Además, si una casilla contiene una ficha, también establece
     * la imagen de la ficha.
     */
    public void recargarBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                BonificacionStrategy bonificador = new SinBonificacionStrategy(); // Estrategia por defecto

                // Bonificación de Palabra Triple
                if ((i == 0 || i == 14) && (j == 0 || j == 7 || j == 14) ||
                        (i == 7) && (j == 0 || j == 14)) {
                    bonificador = new PalabraTripleStrategy();
                }
                // Bonificación de Palabra Doble
                else if (i == j || i + j == 14) {
                    bonificador = new PalabraDobleStrategy();
                }
                // Bonificación de Letra Triple
                else if ((i == 5 || i == 9) && (j == 1 || j == 5 || j == 9 || j == 13) ||
                        (i == 1 || i == 13) && (j == 5 || j == 9)) {
                    bonificador = new LetraTripleStrategy();
                }
                // Bonificación de Letra Doble
                else if ((i == 3 || i == 11) && (j == 0 || j == 7 || j == 14) ||
                        (i == 0 || i == 7 || i == 14) && (j == 3 || j == 11) ||
                        (i == 2 || i == 12) && (j == 6 || j == 8) ||
                        (i == 6 || i == 8) && (j == 2 || j == 6 || j == 8 || j == 12) ||
                        (i == 3 || i == 11) && (j == 3 || j == 11)) {
                    bonificador = new LetraDobleStrategy();
                }
                // Bonificaciones centrales manuales
                if ((i == 6 && j == 6) || (i == 8 && j == 8) || (i == 6 && j == 8) || (i == 8 && j == 6)) {
                    bonificador = new LetraDobleStrategy();
                }
                if ((i == 5 && j == 5) || (i == 5 && j == 9) || (i == 9 && j == 5) || (i == 9 && j == 9)) {
                    bonificador = new LetraTripleStrategy();
                }


                String casillaPath = "/images/"+bonificador.obtenerPathImagen();
                Image imagen;
                imagen = new Image(Objects.requireNonNull(getClass().getResource(casillaPath)).toString());
                this.casillas[i][j].setBonificacion(bonificador);
                this.casillas[i][j].setImagen(imagen);

                if (!this.casillas[i][j].getFicha().isEmpty()) {
                    String fichaPath = "/images/fichas/ficha"+this.casillas[i][j].getFicha().getLetra()+".png";
                    imagen = new Image(Objects.requireNonNull(getClass().getResource(fichaPath)).toString());
                    this.casillas[i][j].getFicha().setImagen(imagen);
                }

            }
        }
        //this.casillas[7][7].setImagen(new Image(String.valueOf(getClass().getResource("/images/casillaInicial.png"))));
    }

    /**
     * Muestra el estado actual del tablero en la consola.
     *
     * Recorre todas las casillas del tablero y utiliza la representación en cadena
     * de la ficha en cada casilla para imprimir el tablero en un formato legible.
     *
     * Por ejemplo:
     *     [X][O][X]
     *     [O][X][O]
     *     [X][O][X]
     */
    public void mostrarTableroEnConsola() {
        for (Casilla[] casilla : casillas) {
            System.out.print("\t");
            for (Casilla value : casilla) {
                System.out.print("["+value.getFicha().toString()+"]");
            }
            System.out.print("\n");
        }
    }
}
