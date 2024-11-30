import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class RaeVerificador {
    private static final String RAE_URL = "https://dle.rae.es/";

    public static boolean verificarPalabra(String palabra) {
        ArrayList<String>variantes = VariantesPalabra.generarVariantes(palabra);
        for (String variante : variantes) {
            if(verificarVariante(variante)){
                return true;
            }
        }
        return false;
    }

    private static boolean verificarVariante(String variante) {
        String url = RAE_URL + variante;
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements elementos = doc.select("#resultados");
            return !elementos.text().startsWith("Aviso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
