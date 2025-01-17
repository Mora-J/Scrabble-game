package ve.edu.ucab.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ve.edu.ucab.models.JsonUtil;
import ve.edu.ucab.models.Jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreViewController {
    @FXML
    private Label estadisticasLabel;

    @FXML
    private Label jugador1Final1;

    @FXML
    private Label jugador2Final1;

    @FXML
    private Label palabras1;

    @FXML
    private Label palabras2;

    @FXML
    private Label palabrasTotales1;

    @FXML
    private Label score1;

    @FXML
    private Label score2;

    @FXML
    private Label scoreTotal1;

    @FXML
    private Label tiempo1;

    @FXML
    private Label tiempo2;

    @FXML
    private Label tiempoJugado1;

    private Jugador[] jugadores;

    @SuppressWarnings("DuplicatedCode")
    @FXML
    void salir(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        stage.setTitle("Menu!");
        stage.setScene(scene);
        stage.setFullScreen(wasFullScreen);

        MenuController menuController = fxmlLoader.getController();
        ArrayList<Jugador> jugadores = new ArrayList<>(Arrays.asList(getJugadores()));
        menuController.setJugadores(jugadores);


        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
        configureStats();
    }

    @SuppressWarnings("DuplicatedCode")
    private void configureStats(){
        Jugador jugador1 = jugadores[0];
        Jugador jugador2 = jugadores[1];

        jugador1Final1.setText(jugador1.getAlias());
        jugador2Final1.setText(jugador2.getAlias());
        score1.setText(String.valueOf(jugador1.getScoreTotal()));
        score2.setText(String.valueOf(jugador2.getScoreTotal()));
        tiempo1.setText(jugador1.getTiempoJugado());
        tiempo2.setText(jugador1.getTiempoJugado());
        palabras1.setText(String.valueOf(jugador1.getCantidadDePalabras()));
        palabras2.setText(String.valueOf(jugador2.getCantidadDePalabras()));
    }


}
