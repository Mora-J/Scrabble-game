package ve.edu.ucab.application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * Clase principal de la aplicación que maneja la vista del menú.
 */
public class MenuApplication extends Application {
    /**
     * Inicia la aplicación y configura el escenario principal.
     *
     * @param stage El escenario principal.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        descargarFuente();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/images/icon.png")).toString());
        stage.getIcons().add(icon);
        stage.setOnCloseRequest(event -> {
            event.consume();
            salir(stage);
        });

        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle("Login!");

        stage.setScene(scene);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }

    /**
     * Metodo principal que lanza la aplicación.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Maneja el evento de salir del juego.
     *
     * @param stage El escenario principal.
     */
    public void salir(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("Estas apunto de salir del juego!");
        alert.setContentText("Estas seguro de salir del juego?");

        //noinspection OptionalGetWithoutIsPresent
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }

    }

    /**
     * Descarga la fuente pixel art necesaria para el juego, utiliza un script de powershell
     */

    public void descargarFuente(){
        // Ruta al script de PowerShell
        String scriptPath = Objects.requireNonNull(getClass().getResource("/scripts/fontInstaller.ps1")).getPath();
        scriptPath = URLDecoder.decode(scriptPath, StandardCharsets.UTF_8);


        if (scriptPath.startsWith("/")) {
            scriptPath = scriptPath.substring(1);
        }

        scriptPath = scriptPath.replace("/", "\\");
        scriptPath = "\"" + scriptPath + "\"";

        // Ejecutar el script de PowerShell
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", "-ExecutionPolicy", "Bypass", "-File", scriptPath);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Continuar con la ejecución del programa
        System.out.println("Fuente instalada, continuando con el programa...");
    }

}