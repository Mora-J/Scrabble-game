package ve.edu.ucab.models;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * La clase Ficha representa una fecha de juego con un valor, una letra y un simbolo.
 * Esta clase implementa la interfaz {@code Cloneable}, lo que permite clonar objetos de esta clase.
 */

public class Ficha implements Cloneable {
    /**
     * Valor de la ficha.
     */

    private int valor;
    /**
     * Letra representada en la ficha.
     */
    private String letra;
    private transient Label letraLabel;
    private transient Image imagenFicha;

    /**
     * Constructor que inicializa el valor y la letra de la ficha.
     * Genera el simbolo correspondiente.
     * @param valor el valor de la ficha
     * @param letra la letra representada en la ficha
     */
    public Ficha(int valor, String letra, Image imagenFicha) {
        this.valor = valor;
        this.letra = letra;
        this.letraLabel = new Label(letra);
        this.imagenFicha = imagenFicha;
    }

    /**
     * Constructor por defecto que inicializa la ficha con un valor de 0 y una letra vacia
     */
    public Ficha() {
        this.imagenFicha = null;
        this.valor = 0;
        this.letra = " ";
        this.letraLabel = new Label(" ");
    }

    /**
     * Establece la letra de la ficha y actualiza el simbolo correspondiente.
     * @param letra la nueva letra de la ficha
     */
    public void setLetra(String letra) {
        this.letra = letra;
    }

    /**
     * Crea y devuelve una copia de esta ficha.
     * Implementa el metodo clone() de la interfaz {@code Cloneable}.
     *
     * @return una copia de esta ficha.
     */
    @Override
    public Ficha clone() {
        try {
            return (Ficha) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage() + " - Se jodio el programa, te debo un helado, Joselito :c");
            return null;
        }
    }

    /**
     * Obtiene la letra de la ficha.
     *
     * @return la letra de la ficha.
     */
    public String getLetra() {
        return letra;
    }

    /**
     * Establece el valor de la ficha.
     *
     * @param valor el nuevo valor de la ficha.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el valor de la ficha.
     *
     * @return el valor de la ficha.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Devuelve una representación en forma de cadena del símbolo de la ficha.
     *
     * @return la representación en cadena del símbolo de la ficha.
     */
    @Override
    public String toString() {
        return letra;
    }

    public Label getLetraLabel() {
        return letraLabel;
    }

    public void setLetraLabel(Label letraLabel) {
        this.letraLabel = letraLabel;
    }

    public Image getImagen() {
        return imagenFicha;
    }

    public void setImagen(Image imagenFicha) {
        this.imagenFicha = imagenFicha;
    }

    public boolean isEmpty() {
        return (letra.equals(" "));
    }
}