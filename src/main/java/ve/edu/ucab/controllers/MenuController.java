package ve.edu.ucab.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button continuarPartidaBtn;

    @FXML
    private Button nuevaPartidaBtn;

    @FXML
    private Button salirBtn;

    @FXML
    void continuarPartida(ActionEvent event) {

    }

    @FXML
    void iniciarNuevaPartida(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scrabble-view.fxml"));

        // Obtener la ventana (stage) desde cualquier nodo de la escena actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Game!");
        stage.setFullScreen(wasFullScreen);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }


    @FXML
    void salir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("Estas apunto de salir del juego!");
        alert.setContentText("Estas seguro de salir del juego?");


        //noinspection OptionalGetWithoutIsPresent
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

}
