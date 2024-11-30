import java.util.ArrayList;

public class VariantesPalabra {
    private static final char[][] acentos = new char[][]{{'a', 'á'}, {'e', 'é'}, {'i', 'í'}, {'o', 'ó'}, {'u', 'ú'}};

    public static ArrayList<String> generarVariantes(String palabra) {
        palabra = palabra.toLowerCase();
        ArrayList<String> variantes = new ArrayList<>();
        variantes.add(palabra);
        char[] palabraArray = palabra.toCharArray();

        for (int i = 0; i < palabraArray.length; i++) {
            for (char[] vocalAcento : acentos) {
                if (palabraArray[i] == vocalAcento[0]) {
                    char[] palabraAcento = palabraArray.clone();
                    palabraAcento[i] = vocalAcento[1];
                    variantes.add(new String(palabraAcento));
                }
            }
        } return variantes;
    }
}

