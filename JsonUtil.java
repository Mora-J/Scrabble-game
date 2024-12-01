import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String archivoPartidasPendientes = "partidas_pendientes.json";

    public static void guardarPartidaPendiente(Juego juego) {
        Map<String, Juego> partidasPendientes = cargarPartidasPendientes();
        String claveJugadores = juego.getClaveJugadores();
        partidasPendientes.put(claveJugadores, juego);
        guardarPartidasPendientes(partidasPendientes);
    }

    public static Juego cargarPartidaPendiente(String claveJugadores) {
        Map<String, Juego> partidasPendientes = cargarPartidasPendientes();
        return partidasPendientes.getOrDefault(claveJugadores, null);
    }

    private static Map<String, Juego> cargarPartidasPendientes() {
        try (FileReader reader = new FileReader(archivoPartidasPendientes)) {
            Type mapType = new TypeToken<HashMap<String, Juego>>() {}.getType();
            return gson.fromJson(reader, mapType);
        } catch (IOException e) {
            return new HashMap<>(); // Retorna un mapa vacío si el archivo no existe
        }
    }

    private static void guardarPartidasPendientes(Map<String, Juego> partidasPendientes) {
        try (FileWriter writer = new FileWriter(archivoPartidasPendientes)) {
            gson.toJson(partidasPendientes, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo " + archivoPartidasPendientes);
        }
    }
}
