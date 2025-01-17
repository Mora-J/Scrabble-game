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

/**
 * Controlador para la vista del menú principal del juego.
 */
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

    /**
     * Lista de jugadores.
     */
    private ArrayList<Jugador> jugadores = new ArrayList<>();

    /**
     * Maneja el evento para continuar una partida guardada.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @SuppressWarnings("DuplicatedCode")
    @FXML
    void continuarPartida(ActionEvent event) throws IOException {
        Jugador[] players = new Jugador[jugadores.size()];
        jugadores.toArray(players);
        Game game = new Game(players);

        if(cargarGame(game).isEstaFinalizada()){
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/scrabble-view.fxml"));

        // Obtener la ventana (stage) desde cualquier nodo de la escena actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        ScrabbleController scrabbleController = fxmlLoader.getController();
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

    /**
     * Carga una partida guardada desde el archivo JSON.
     *
     * @param game El juego a cargar.
     * @return El juego cargado.
     */
    private Game cargarGame(Game game) {
        game = JsonUtil.cargarPartidaPendiente(game.getClaveJugadores());
        game.getBoard().recargarBoard();

        return game;
    }

    /**
     * Inicializa el controlador y carga la fuente personalizada.
     */
    @FXML
    void initialize() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 12);
        if (font != null) {
            System.out.println("Font loaded successfully");
            continuarPartidaBtn.setFont(font);
            nuevaPartidaBtn.setFont(font);
            salirBtn.setFont(font);
            verScoreBtn.setFont(font);

        } else {
            System.out.println("Font not found");
        }

    }

    /**
     * Maneja el evento para iniciar una nueva partida.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
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
        for (Jugador jugador : players){
            jugador.reiniciarJugador();
        }

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

    /**
     * Maneja el evento para salir del juego.
     *
     * @param event El evento de acción.
     */
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

    /**
     * Obtiene la lista de jugadores.
     *
     * @return La lista de jugadores.
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Establece la lista de jugadores.
     *
     * @param jugadores La nueva lista de jugadores.
     */
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @FXML
    void verScore(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/score-view.fxml"));

        // Obtener la ventana (stage) desde cualquier nodo de la escena actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        ScoreViewController scoreViewController = fxmlLoader.getController();


        Jugador[] players = new Jugador[jugadores.size()];
        jugadores.toArray(players);
        scoreViewController.setJugadores(players);

        stage.setScene(scene);
        stage.setFullScreen(wasFullScreen);
        stage.setTitle("Score!");

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });


        stage.show();
    }
}
