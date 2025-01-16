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
import java.util.Objects;

public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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
        stage.setTitle("Menu!");

        stage.setScene(scene);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

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
}