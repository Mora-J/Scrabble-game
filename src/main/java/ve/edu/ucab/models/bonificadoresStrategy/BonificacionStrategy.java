package ve.edu.ucab.models.bonificadoresStrategy;

/**
 * Clase abstracta que define la estrategia de bonificación para una ficha.
 */
public abstract class BonificacionStrategy {


    /**
     * Indica si la bonificación es individual.
     */
    boolean individual;

    /**
     * Verifica si la bonificación es individual.
     *
     * @return true si la bonificación es individual, false en caso contrario.
     */
    public boolean isIndividual() {
        return individual;
    }

    /**
     * Establece si la bonificación es individual.
     *
     * @param individual true para establecer la bonificación como individual, false en caso contrario.
     */
    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    /**
     * Obtiene el valor de la bonificación en función del valor de la ficha.
     *
     * @param valor El valor de la ficha.
     * @return El valor de la bonificación.
     */
    abstract public int obtenerbonificacion(int valor);

    /**
     * Obtiene el multiplicador de bonificación por palabra.
     *
     * @return El multiplicador por palabra.
     */
    abstract public int obtenerMultiplicadorPorPalabra();

    /**
     * Obtiene el path de la imagen asociada a la bonificación.
     *
     * @return El path de la imagen.
     */
    abstract public String obtenerPathImagen();
}
