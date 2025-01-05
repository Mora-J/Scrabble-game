package ve.edu.ucab.models;

import javafx.scene.image.Image;

import java.util.Objects;

public class Casilla implements Cloneable {
    private Ficha ficha;
    private String bonificacion;
    private Image imagen;
    private boolean isMovable = false;

    public Casilla(Ficha ficha, String bonificacion) {
        this.ficha = ficha;
        this.bonificacion = bonificacion;
    }

    public Casilla() {
        this.ficha = new Ficha();
        this.bonificacion = "";
        String casillaVaciaPath = "/images/casillaVacia.png";
        this.imagen = new Image(Objects.requireNonNull(getClass().getResource(casillaVaciaPath)).toString());
    }


    public String getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(String bonificacion) {
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
}
