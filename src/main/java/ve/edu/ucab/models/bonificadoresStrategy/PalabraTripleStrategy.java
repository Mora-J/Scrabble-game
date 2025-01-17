package ve.edu.ucab.models.bonificadoresStrategy;

/**
 * Estrategia de bonificación que triplica el valor de la palabra.
 */
public class PalabraTripleStrategy extends BonificacionStrategy {


    /**
     * Constructor que establece la bonificación como no individual.
     */
    public PalabraTripleStrategy() {
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
     * Obtiene el multiplicador de bonificación por palabra, que es 3 en este caso.
     *
     * @return El multiplicador por palabra.
     */
    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 3;
    }

    /**
     * Obtiene el path de la imagen asociada a la bonificación.
     *
     * @return El path de la imagen.
     */
    @Override
    public String obtenerPathImagen() {
        return "casillaPT.png";
    }
}
