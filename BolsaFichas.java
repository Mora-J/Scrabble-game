import java.util.ArrayList;
import java.util.Collections;

public class BolsaFichas {
    private final ArrayList<Ficha> listaFichas;

    public BolsaFichas() {
        String[] letras = new String[]{"A", "B", "C", "CH", "D", "E", "F", "G", "H", "I",
                "J", "L", "LL", "M", "N", "Ñ", "O", "P", "Q", "R", "RR", "S", "T", "U", "V", "X", "Y", "Z", "#"};

        int[] cantidadFichas = new int[]{12, 2, 4, 1, 5, 12, 1, 2, 2, 6, 1, 4, 1, 2, 5, 1,
                9, 2, 1, 5, 1, 6, 4, 5, 1, 1, 1, 1, 2};

        int[] valorFichas = new int[]{1, 4, 3, 8, 3, 1, 5, 3, 5, 1, 10, 2, 8, 3, 2, 10, 1,
                4, 8, 2, 8, 1, 2, 1, 4, 10, 5, 10, 0};

        listaFichas = new ArrayList<>();
        for (int i = 0; i < letras.length; i++) {
            for (int j = 0; j < cantidadFichas[i]; j++) {
                this.listaFichas.add(new Ficha(valorFichas[i], letras[i]));
            }
        }

        Collections.shuffle(this.listaFichas);
    }

    public ArrayList<Ficha> getListaFichas() {
        return listaFichas;
    }
}
