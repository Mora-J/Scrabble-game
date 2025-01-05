package ve.edu.ucab.models.bonificadoresStrategy;

public class LetraTripleStrategy extends BonificacionStrategy {
    @Override
    public int obtenerbonificacion(int valor) {
        return valor * 3;
    }

    public LetraTripleStrategy() {
        individual = true;
    }

    @Override
    public String obtenerPathImagen() {
        return "casillaLT.png";
    }

    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 1;
    }
}


