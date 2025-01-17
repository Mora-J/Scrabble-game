package ve.edu.ucab.models.bonificadoresStrategy;

/**
 * Estrategia de bonificación que duplica el valor de la letra.
 */
public class LetraDobleStrategy extends BonificacionStrategy {

    /**
     * Obtiene el valor de la bonificación duplicando el valor de la letra.
     *
     * @param valor El valor de la letra.
     * @return El valor de la bonificación.
     */
    @Override
    public int obtenerbonificacion(int valor) {
        return valor * 2;
    }

    /**
     * Constructor que establece la bonificación como individual.
     */
    public LetraDobleStrategy() {
        individual = true;
    }

    /**
     * Obtiene el path de la imagen asociada a la bonificación.
     *
     * @return El path de la imagen.
     */
    @Override
    public String obtenerPathImagen() {
        return "casillaLD.png";
    }

    /**
     * Obtiene el multiplicador de bonificación por palabra.
     *
     * @return El multiplicador por palabra, que es 1 en este caso.
     */
    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 1;
    }
}
