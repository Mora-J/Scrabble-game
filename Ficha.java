public class Ficha {
    private int valor;
    private String letra;
    private String simbolo;

    public Ficha(int valor, String letra) {
        this.valor = valor;
        this.letra = letra;
        this.simbolo = "\033[1;30;107m["+letra+"]\033[0m";
    }

    public Ficha() {
        this.valor = 0;
        this.letra = "  ";
        this.simbolo = "["+letra+"]";
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setLetra(String letra) {
        this.letra = letra;
        this.simbolo = "\033[1;30;107m["+letra+"]\033[0m";
    }

    public String getLetra() {
        return letra;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return simbolo;
    }
}
