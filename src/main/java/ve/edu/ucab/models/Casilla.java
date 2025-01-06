package ve.edu.ucab.models;

import javafx.scene.image.Image;
import ve.edu.ucab.models.bonificadoresStrategy.BonificacionStrategy;
import ve.edu.ucab.models.bonificadoresStrategy.SinBonificacionStrategy;

import java.util.Objects;

public class Casilla implements Cloneable {
    private Ficha ficha;
    private BonificacionStrategy bonificacion;
    private boolean bonificacionIndividualActivada;
    private boolean bonificacionPorPalabraActivada;
    private Image imagen;
    private boolean isMovable = true;
    private int puntaje;

    public Casilla(BonificacionStrategy bonificacion) {
        this.bonificacion = bonificacion;
        this.ficha = new Ficha();
        this.puntaje = ficha.getValor();
        String casillaPath = "/images/"+this.bonificacion.obtenerPathImagen();
        this.imagen = new Image(Objects.requireNonNull(getClass().getResource(casillaPath)).toString());
        this.bonificacionIndividualActivada = bonificacion.isIndividual();
        this.bonificacionPorPalabraActivada = !bonificacion.isIndividual();
    }

    public int getPuntaje() {
        if (!this.bonificacionIndividualActivada) {
            return this.puntaje;
        } else{
            bonificacionIndividualActivada = false;
            return bonificacion.obtenerbonificacion(ficha.getValor());
        }
    }


    public Casilla() {
        this.ficha = new Ficha();
        this.puntaje = ficha.getValor();
        this.bonificacion = new SinBonificacionStrategy();
        String casillaPath = "/images/"+this.bonificacion.obtenerPathImagen();
        this.imagen = new Image(Objects.requireNonNull(getClass().getResource(casillaPath)).toString());
        this.bonificacionIndividualActivada = bonificacion.isIndividual();
        this.bonificacionPorPalabraActivada = !bonificacion.isIndividual();
    }


    public BonificacionStrategy getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(BonificacionStrategy bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Image getImagen() {
        if(ficha.getImagen() == null) {
            return imagen;
        }else{
            return ficha.getImagen();
        }
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    @Override
    public Casilla clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Casilla) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public boolean isBonificacionIndividualActivada() {
        return bonificacionIndividualActivada;
    }

    public void setBonificacionIndividualActivada(boolean bonificacionIndividualActivada) {
        this.bonificacionIndividualActivada = bonificacionIndividualActivada;
    }

    public boolean isBonificacionPorPalabraActivada() {
        return bonificacionPorPalabraActivada;
    }

    public void setBonificacionPorPalabraActivada(boolean bonificacionPorPalabraActivada) {
        this.bonificacionPorPalabraActivada = bonificacionPorPalabraActivada;
    }
}
