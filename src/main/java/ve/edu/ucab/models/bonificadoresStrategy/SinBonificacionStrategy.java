package ve.edu.ucab.models.bonificadoresStrategy;

/**
 * Estrategia sin bonificación que mantiene el valor original de la letra.
 */
public class SinBonificacionStrategy extends BonificacionStrategy {

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
     * Constructor que establece la bonificación como individual.
     */
    public SinBonificacionStrategy() {
        individual = true;
    }

    /**
     * Obtiene el path de la imagen asociada a la bonificación.
     *
     * @return El path de la imagen.
     */
    @Override
    public String obtenerPathImagen() {
        return "casillaVacia.png";
    }

    /**
     * Obtiene el multiplicador de bonificación por palabra, que es 1 en este caso.
     *
     * @return El multiplicador por palabra.
     */
    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 1;
    }
}
