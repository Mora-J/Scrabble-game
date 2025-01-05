package ve.edu.ucab.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * La clase RaeVerificador se encarga de verificar palabras en el diccionario de la Real Academia Española (RAE).
 */
public class RaeVerificador {

    /**
     * URL base del diccionario de la RAE.
     */
    private static final String RAE_URL = "https://dle.rae.es/";

    /**
     * Verifica si una palabra es válida según el diccionario de la RAE.
     *
     * @param palabra la palabra a verificar.
     * @return {@code true} si la palabra es válida, {@code false} en caso contrario.
     */
    public static boolean verificarPalabra(String palabra) {
        if (palabra.contains("#")) {
            return false;
        }

        ArrayList<String> variantes = VariantesPalabra.generarVariantes(palabra);
        for (String variante : variantes) {
            if (verificarVariante(variante)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica una variante de una palabra en el diccionario de la RAE.
     *
     * @param variante la variante de la palabra a verificar.
     * @return {@code true} si la variante es válida, {@code false} en caso contrario.
     */
    private static boolean verificarVariante(String variante) {
        String url = RAE_URL + variante;
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elementos = doc.select("#resultados");
            return !elementos.text().startsWith("La palabra «");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}