package ve.edu.ucab.models;

import javafx.scene.image.Image;

import java.util.Objects;

public class Casilla {
    private Ficha ficha;
    private String bonificacion;
    private Image imagen;

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
}
