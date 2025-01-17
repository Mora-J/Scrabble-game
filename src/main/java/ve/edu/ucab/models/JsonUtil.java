package ve.edu.ucab.models;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;



public class JsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String archivoPartidasPendientes = "partidas_pendientes.json";

    public static void guardarPartidaPendiente(Game juego) {
        Map<String, Game> partidasPendientes = cargarPartidasPendientes();
        String claveJugadores = juego.getClaveJugadores();
        partidasPendientes.put(claveJugadores, juego);
        guardarPartidasPendientes(partidasPendientes);
    }


    private static Map<String, Game> cargarPartidasPendientes() {
        File file = new File(archivoPartidasPendientes);
        if (file.exists() && file.length() > 0) {
            try (FileReader reader = new FileReader(archivoPartidasPendientes)) {
                Type mapType = new TypeToken<HashMap<String, Game>>() {}.getType();
                return gson.fromJson(reader, mapType);
            } catch (Exception e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
                return new HashMap<>();
            }
        } else {
            return new HashMap<>(); // Retorna un mapa vacío si el archivo no existe o está vacío
        }
    }


    public static Game cargarPartidaPendiente(String claveJugadores) {
        Map<String, Game> partidasPendientes = cargarPartidasPendientes();
        return partidasPendientes.getOrDefault(claveJugadores, null);
    }

    private static void guardarPartidasPendientes(Map<String, Game> partidasPendientes) {
        try (FileWriter writer = new FileWriter(archivoPartidasPendientes)) {
            gson.toJson(partidasPendientes, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo " + archivoPartidasPendientes);
        }
    }



}
