package ve.edu.ucab.models;

public class Casilla {
    private Ficha ficha;
    private String bonificacion;

    public Casilla(Ficha ficha, String bonificacion) {
        this.ficha = ficha;
        this.bonificacion = bonificacion;
    }

    public Casilla() {
        this.ficha = new Ficha();
        this.bonificacion = "";
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
}
