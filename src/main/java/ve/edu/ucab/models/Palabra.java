package ve.edu.ucab.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * La clase Palabra representa una palabra formada por fichas en un juego de palabras,
 * que puede estar dispuesta de manera vertical u horizontal.
 */
public class Palabra {

    /**
     * Lista de casillas que forman la palabra.
     */
    private ArrayList<Casilla> palabra = new ArrayList<>();


    /**
     * Indica si la palabra está dispuesta de manera vertical.
     */
    private final boolean esVertical;

    /**
     * Constructor que inicializa la palabra con la orientación especificada.
     *
     * @param esVertical {@code true} si la palabra está dispuesta de manera vertical, {@code false} si es horizontal.
     */
    public Palabra(boolean esVertical) {
        this.esVertical = esVertical;
    }

    /**
     * Constructor de copia que inicializa la palabra con otra palabra.
     *
     * @param palabra la palabra a copiar.
     */
    public Palabra(Palabra palabra) {
        this.esVertical = palabra.esVertical();
        this.palabra = new ArrayList<>(palabra.getPalabra());
    }

    /**
     * Obtiene el tamaño de la palabra.
     *
     * @return el tamaño de la palabra.
     */
    public int size() {
        return palabra.size();
    }

    /**
     * Añade una casilla al final de la palabra.
     *
     * @param casilla la ficha a añadir.
     */
    public void add(Casilla casilla) {
        palabra.add(casilla);
    }

    /**
     * Añade una casilla al inicio de la palabra.
     *
     * @param casilla la ficha a añadir.
     */
    public void addFirst(Casilla casilla) {
        palabra.addFirst(casilla);
    }

    /**
     * Indica si la palabra está dispuesta de manera vertical.
     *
     * @return {@code true} si la palabra está dispuesta de manera vertical, {@code false} si es horizontal.
     */
    public boolean esVertical() {
        return esVertical;
    }

    /**
     * Obtiene la lista de fichas que forman la palabra.
     *
     * @return la lista de fichas.
     */
    public ArrayList<Casilla> getPalabra() {
        return palabra;
    }

    /**
     * Obtiene el puntaje total de las fichas en la palabra.
     *
     * @return el puntaje total de las fichas.
     */
    public int getPuntaje() {
        int puntaje = 0;
        for (Casilla casilla : palabra) {
            puntaje += casilla.getPuntaje();
        }
        return puntaje;
    }

    /**
     * Devuelve una representación en cadena de la palabra.
     *
     * @return una representación en cadena de la palabra.
     */
    @Override
    public String toString() {
        StringBuilder palabraTexto = new StringBuilder();
        for (Casilla casilla : palabra) {
            palabraTexto.append(casilla.getFicha().getLetra());
        }
        return palabraTexto.toString();
    }


    public int getMultiplicador(){
        int multiplicador = 1;
        for (Casilla casilla : palabra) {
            if (casilla.getBonificacion().obtenerMultiplicadorPorPalabra() > 1){
                multiplicador += casilla.getBonificacion().obtenerMultiplicadorPorPalabra();
            }
        }
        return multiplicador;
    }

    /**
     * Compara este objeto con el objeto especificado para determinar si son iguales.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palabra that = (Palabra) o;
        return esVertical == that.esVertical && Objects.equals(palabra, that.palabra);
    }

    /**
     * Devuelve un código hash para el objeto.
     *
     * @return un código hash para el objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(palabra, esVertical);
    }
}
