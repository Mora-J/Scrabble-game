package ve.edu.ucab.models.bonificadoresStrategy;

/**
 * Estrategia de bonificación que duplica el valor de la palabra.
 */
public class PalabraDobleStrategy extends BonificacionStrategy {

    /**
     * Constructor que establece la bonificación como no individual.
     */
    public PalabraDobleStrategy() {
        individual = false;
    }

    /**
     * Obtiene el valor de la bonificación, que en este caso es el mismo valor de la letra.
     *
     * @param valor El valor de la letra.
     * @return El valor de la bonificación.
     */
    @Override
    public int obtenerbonificacion(int valor) {
        return valor;
    }

    /**
     * Obtiene el multiplicador de bonificación por palabra, que es 2 en este caso.
     *
     * @return El multiplicador por palabra.
     */
    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 2;
    }

    /**
     * Obtiene el path de la imagen asociada a la bonificación.
     *
     * @return El path de la imagen.
     */
    @Override
    public String obtenerPathImagen() {
        return "casillaPD.png";
    }
}
