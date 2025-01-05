package ve.edu.ucab.models.bonificadoresStrategy;

public class SinBonificacionStrategy extends BonificacionStrategy {

    @Override
    public int obtenerbonificacion(int valor) {
        return valor;
    }

    public SinBonificacionStrategy() {
        individual = true;
    }

    @Override
    public String obtenerPathImagen() {
        return "casillaVacia.png";
    }

    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 1;
    }
}
