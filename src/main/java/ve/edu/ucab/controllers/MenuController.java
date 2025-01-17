package ve.edu.ucab.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ve.edu.ucab.models.Game;
import ve.edu.ucab.models.JsonUtil;
import ve.edu.ucab.models.Jugador;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {

    @FXML
    private Button continuarPartidaBtn;

    @FXML
    private Button nuevaPartidaBtn;

    @FXML
    private Button salirBtn;

    @FXML
    private Button opcionesBtn;


    @FXML
    private Button verScoreBtn;

    private ArrayList<Jugador> jugadores = new ArrayList<>();


    @SuppressWarnings("DuplicatedCode")
    @FXML
    void continuarPartida(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scrabble-view.fxml"));

        // Obtener la ventana (stage) desde cualquier nodo de la escena actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        ScrabbleController scrabbleController = fxmlLoader.getController();

        Jugador[] players = new Jugador[jugadores.size()];
        jugadores.toArray(players);
        Game game = new Game(players);


        scrabbleController.setGame(cargarGame(game));

        stage.setScene(scene);
        stage.setFullScreen(wasFullScreen);
        stage.setTitle("Game!");

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }

    private Game cargarGame(Game game) {
        game = JsonUtil.cargarPartidaPendiente(game.getClaveJugadores());
        game.getBoard().recargarBoard();
        for (Jugador jugador : game.getJugadores()) {
            jugador.recargarAtril();
        }
        return game;
    }

    @FXML
    void initialize() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 12);
        if (font != null) {
            System.out.println("Font loaded successfully");
            continuarPartidaBtn.setFont(font);
            nuevaPartidaBtn.setFont(font);
            salirBtn.setFont(font);
            opcionesBtn.setFont(font);
            verScoreBtn.setFont(font);

        } else {
            System.out.println("Font not found");
        }

    }

    @SuppressWarnings("DuplicatedCode")
    @FXML
    void iniciarNuevaPartida(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scrabble-view.fxml"));

        // Obtener la ventana (stage) desde cualquier nodo de la escena actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        ScrabbleController scrabbleController = fxmlLoader.getController();

        Jugador[] players = new Jugador[jugadores.size()];
        jugadores.toArray(players);
        Game game = new Game(players);

        scrabbleController.setGame(game);

        stage.setScene(scene);
        stage.setFullScreen(wasFullScreen);
        stage.setTitle("Game!");

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

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
