package ve.edu.ucab.models.bonificadoresStrategy;

public class PalabraDobleStrategy extends BonificacionStrategy {


    public PalabraDobleStrategy() {
        individual = false;
    }

    @Override
    public int obtenerbonificacion(int valor) {
        return valor;
    }

    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 2;
    }

    @Override
    public String obtenerPathImagen() {
        return "casillaPD.png";
    }
}
