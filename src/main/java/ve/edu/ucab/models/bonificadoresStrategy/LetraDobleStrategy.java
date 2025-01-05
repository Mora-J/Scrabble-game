package ve.edu.ucab.models.bonificadoresStrategy;

public class LetraDobleStrategy extends BonificacionStrategy {

    @Override
    public int obtenerbonificacion(int valor) {
        return valor * 2;
    }

    public LetraDobleStrategy() {
        individual = true;
    }

    @Override
    public String obtenerPathImagen() {
        return "casillaLD.png";
    }

    @Override
    public int obtenerMultiplicadorPorPalabra() {
        return 1;
    }
}
