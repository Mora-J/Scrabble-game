package ve.edu.ucab.models;

import javafx.scene.image.Image;
import kotlin.jvm.Transient;
import ve.edu.ucab.models.bonificadoresStrategy.BonificacionStrategy;
import ve.edu.ucab.models.bonificadoresStrategy.SinBonificacionStrategy;

import java.util.Objects;

/**
 * Representa una casilla en el tablero del juego.
 * Implementa la interfaz Cloneable para permitir la clonación de objetos Casilla.
 */
public class Casilla implements Cloneable {

    /**
     * La ficha presente en la casilla.
     */
    private Ficha ficha;

    /**
     * La estrategia de bonificación asociada a la casilla.
     * No se serializa debido a la palabra clave 'transient'.
     */
    private transient BonificacionStrategy bonificacion;

    /**
     * Indica si la bonificación individual está activada en esta casilla.
     */
    private boolean bonificacionIndividualActivada;

    /**
     * Indica si la bonificación por palabra está activada en esta casilla.
     */
    private boolean bonificacionPorPalabraActivada;

    /**
     * La imagen asociada a la casilla.
     * No se serializa debido a la palabra clave 'transient'.
     */
    private transient Image imagen;

    /**
     * Indica si la casilla es movible.
     * Valor por defecto: true.
     */
    private boolean isMovable = true;

    /**
     * Constructor para crear una Casilla con una estrategia de bonificación específica.
     *
     * @param bonificacion La estrategia de bonificación asociada a esta casilla.
     */
    public Casilla(BonificacionStrategy bonificacion) {
        this.bonificacion = bonificacion;
        this.ficha = new Ficha();
        String casillaPath = "/images/"+this.bonificacion.obtenerPathImagen();
        this.imagen = new Image(Objects.requireNonNull(getClass().getResource(casillaPath)).toString());
        this.bonificacionIndividualActivada = bonificacion.isIndividual();
        this.bonificacionPorPalabraActivada = !bonificacion.isIndividual();
    }

    /**
     * Calcula el puntaje de la casilla en función de la ficha y la bonificación.
     *
     * @return El puntaje obtenido en la casilla.
     */
    public int getPuntaje() {
        if (!this.bonificacionIndividualActivada) {
            return ficha.getValor();
        } else{
            bonificacionIndividualActivada = false;
            return bonificacion.obtenerbonificacion(ficha.getValor());
        }
    }


    /**
     * Constructor por defecto para crear una Casilla sin bonificación específica.
     */
    public Casilla() {
        this.ficha = new Ficha();
        this.bonificacion = new SinBonificacionStrategy();
        String casillaPath = "/images/"+this.bonificacion.obtenerPathImagen();
        this.imagen = new Image(Objects.requireNonNull(getClass().getResource(casillaPath)).toString());
        this.bonificacionIndividualActivada = bonificacion.isIndividual();
        this.bonificacionPorPalabraActivada = !bonificacion.isIndividual();
    }

    /**
     * Obtiene la estrategia de bonificación asociada a esta casilla.
     *
     * @return La estrategia de bonificación.
     */
    public BonificacionStrategy getBonificacion() {
        return bonificacion;
    }

    /**
     * Establece la estrategia de bonificación para esta casilla.
     *
     * @param bonificacion La nueva estrategia de bonificación.
     */
    public void setBonificacion(BonificacionStrategy bonificacion) {
        this.bonificacion = bonificacion;
    }

    /**
     * Obtiene la ficha presente en esta casilla.
     *
     * @return La ficha presente en la casilla.
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * Establece la ficha para esta casilla.
     *
     * @param ficha La nueva ficha.
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * Obtiene la imagen asociada a esta casilla.
     *
     * @return La imagen de la casilla, o la imagen de la ficha si está presente.
     */
    public Image getImagen() {
        if(ficha.getImagen() == null) {
            return imagen;
        }else{
            return ficha.getImagen();
        }
    }

    /**
     * Establece la imagen para esta casilla.
     *
     * @param imagen La nueva imagen.
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Crea y retorna una copia de esta casilla.
     *
     * @return Una copia de esta casilla.
     */
    @Override
    public Casilla clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Casilla) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Verifica si la casilla es movible.
     *
     * @return true si la casilla es movible, false en caso contrario.
     */
    public boolean isMovable() {
        return isMovable;
    }

    /**
     * Establece si la casilla es movible.
     *
     * @param movable true para hacer la casilla movible, false en caso contrario.
     */
    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    /**
     * Verifica si la bonificación individual está activada.
     *
     * @return true si la bonificación individual está activada, false en caso contrario.
     */
    public boolean isBonificacionIndividualActivada() {
        return bonificacionIndividualActivada;
    }

    /**
     * Establece si la bonificación individual está activada.
     *
     * @param bonificacionIndividualActivada true para activar la bonificación individual, false en caso contrario.
     */
    public void setBonificacionIndividualActivada(boolean bonificacionIndividualActivada) {
        this.bonificacionIndividualActivada = bonificacionIndividualActivada;
    }

    /**
     * Verifica si la bonificación por palabra está activada.
     *
     * @return true si la bonificación por palabra está activada, false en caso contrario.
     */
    public boolean isBonificacionPorPalabraActivada() {
        return bonificacionPorPalabraActivada;
    }

    /**
     * Establece si la bonificación por palabra está activada.
     *
     * @param bonificacionPorPalabraActivada true para activar la bonificación por palabra, false en caso contrario.
     */
    public void setBonificacionPorPalabraActivada(boolean bonificacionPorPalabraActivada) {
        this.bonificacionPorPalabraActivada = bonificacionPorPalabraActivada;
    }
}
