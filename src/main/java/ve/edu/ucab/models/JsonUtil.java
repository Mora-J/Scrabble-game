package ve.edu.ucab.models;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;



/**
 * Utilidades para guardar y cargar partidas pendientes en formato JSON.
 */
public class JsonUtil {
    private static final String ruta = "C:\\Users\\yeshu\\OneDrive\\Escritorio\\Curso_de_Java\\Proyecto-POO-Grupo1-RegistroDeUsuario-Grafico-master\\";

    /**
     * Nombre del archivo donde se guardan los usuarios.
     */
    private static final String archivoUsuarios = ruta + "usuarios.json";

    /**
     * Instancia de Gson para convertir objetos a formato JSON y viceversa.
     * Configurada para imprimir de forma legible.
     */
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    /**
     * Nombre del archivo donde se guardan las partidas pendientes.
     */
    private static final String archivoPartidasPendientes = "partidas_pendientes.json";

    /**
     * Nombre del archivo donde se guardan las partidas finalizadas.
     */
    private static final String archivoPartidasTerminadas = "partidas_terminadas.json";
    /**
     * Guarda una partida pendiente en el archivo JSON.
     *
     * @param juego La partida a guardar.
     */
    public static void guardarPartidaPendiente(Game juego) {
        Map<String, Game> partidasPendientes = cargarPartidasPendientes();
        String claveJugadores = juego.getClaveJugadores();
        partidasPendientes.put(claveJugadores, juego);
        guardarPartidasPendientes(partidasPendientes);
    }

    /**
     * Carga las partidas pendientes desde el archivo JSON.
     *
     * @return Un mapa con las partidas pendientes cargadas.
     */
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

    /**
     * Carga una partida pendiente específica desde el archivo JSON.
     *
     * @param claveJugadores La clave que identifica la partida pendiente.
     * @return La partida pendiente, o null si no se encuentra.
     */
    public static Game cargarPartidaPendiente(String claveJugadores) {
        Map<String, Game> partidasPendientes = cargarPartidasPendientes();
        return partidasPendientes.getOrDefault(claveJugadores, null);
    }

    /**
     * Guarda todas las partidas pendientes en el archivo JSON.
     *
     * @param partidasPendientes El mapa de partidas pendientes a guardar.
     */
    private static void guardarPartidasPendientes(Map<String, Game> partidasPendientes) {
        try (FileWriter writer = new FileWriter(archivoPartidasPendientes)) {
            gson.toJson(partidasPendientes, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo " + archivoPartidasPendientes);
        }
    }


    /**
     * carga los usuarios en el archivo JSON.
     *
     */
    public static List<Usuario> cargarUsuariosDesdeJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(archivoUsuarios)) {
            Type listType = new TypeToken<List<Usuario>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios desde el archivo JSON.");
            return null;
        }
    }

    /**
     * Actualiza los usuarios
     *
     * @param usuarios lista de los usuarios que seran guardados;
     */
    public static void guardarUsuariosEnJson(List<Usuario> usuarios) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(archivoUsuarios)) {
            gson.toJson(usuarios, writer);
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios en el archivo JSON.");
        }
    }

    public static void guardarPartidaTerminada(Game juego) {
        Map<String, Game> partidasTerminadas = cargarPartidasTerminadas();
        String claveJugadores = juego.getClaveJugadores();
        partidasTerminadas.put(claveJugadores, juego);
        guardarPartidasTerminadas(partidasTerminadas);
    }

    private static Map<String, Game> cargarPartidasTerminadas() {
        File file = new File(archivoPartidasTerminadas);
        if (file.exists() && file.length() > 0) {
            try (FileReader reader = new FileReader(archivoPartidasTerminadas)) {
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

    private static void guardarPartidasTerminadas(Map<String, Game> partidasTerminadas) {
        try (FileWriter writer = new FileWriter(archivoPartidasTerminadas)) {
            gson.toJson(partidasTerminadas, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo " + archivoPartidasTerminadas);
        }
    }


}
