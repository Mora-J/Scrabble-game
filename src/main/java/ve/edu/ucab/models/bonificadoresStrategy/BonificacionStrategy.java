package ve.edu.ucab.models.bonificadoresStrategy;

public abstract class BonificacionStrategy {



    boolean individual;

    public boolean isIndividual() {
        return individual;
    }

    public void setIndividual(boolean individual) {
        this.individual = individual;
    }

    abstract public int obtenerbonificacion(int valor);

    abstract public int obtenerMultiplicadorPorPalabra();

    abstract public String obtenerPathImagen();
}
