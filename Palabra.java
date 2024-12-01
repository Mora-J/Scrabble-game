import java.util.ArrayList;
import java.util.Objects;

public class Palabra {
    private ArrayList<Ficha> palabra = new ArrayList<>();
    private final boolean esVertical;

    public Palabra(boolean esVertical) {
        this.esVertical = esVertical;
    }

    public Palabra(Palabra palabra) {
        this.esVertical = palabra.esVertical();
        this.palabra = new ArrayList<>(palabra.getPalabra());
    }

    public int size() {
        return palabra.size();
    }

    public void add(Ficha ficha){
        palabra.add(ficha);
    }

    public void addFirst(Ficha ficha){
        palabra.addFirst(ficha);
    }

    public boolean esVertical() {
        return esVertical;
    }

    public ArrayList<Ficha> getPalabra() {
        return palabra;
    }

    public int getPuntaje(){
        int puntaje = 0;
        for(Ficha ficha : palabra){
            puntaje += ficha.getValor();
        }
        return puntaje;
    }

    @Override
    public String toString() {
        StringBuilder palabraTexto = new StringBuilder();
        for (Ficha ficha : palabra) {
            palabraTexto.append(ficha.getLetra());
        }
        return palabraTexto.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palabra that = (Palabra) o;
        return esVertical == that.esVertical && Objects.equals(palabra, that.palabra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(palabra, esVertical);
    }
}
