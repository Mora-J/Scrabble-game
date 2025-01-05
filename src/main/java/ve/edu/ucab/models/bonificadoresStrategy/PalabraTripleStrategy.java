package ve.edu.ucab.models.bonificadoresStrategy;

public class PalabraTripleStrategy extends BonificacionStrategy {


    public PalabraTripleStrategy() {
        individual = false;
    }

    @Override
    public int obtenerbonificacion(int valor) {
        return valor;
    }

    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 3;
    }

    @Override
    public String obtenerPathImagen() {
        return "casillaPT.png";
    }
}
