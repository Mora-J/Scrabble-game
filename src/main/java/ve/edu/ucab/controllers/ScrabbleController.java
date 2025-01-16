package ve.edu.ucab.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ve.edu.ucab.models.BolsaFichas;
import ve.edu.ucab.models.Casilla;
import ve.edu.ucab.models.Ficha;
import ve.edu.ucab.models.Game;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ScrabbleController {
    @FXML
    private Button playButton;

    @FXML
    private GridPane scrabbleBoard;

    @FXML
    private ImageView ficha1;

    @FXML
    private ImageView ficha2;

    @FXML
    private ImageView ficha3;

    @FXML
    private ImageView ficha4;

    @FXML
    private ImageView ficha5;

    @FXML
    private ImageView ficha6;

    @FXML
    private ImageView ficha7;

    @FXML
    private Label indicadorBolsa;

    @FXML
    private HBox hboxAtril;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label puntos1;

    @FXML
    private Label puntos2;

    @FXML
    private VBox jugador1;

    @FXML
    private VBox jugador2;

    @FXML
    private Button redoButton;

    @FXML
    private Button passButton;

    @FXML
    private AnchorPane capaGris;

    @FXML
    private AnchorPane finPartida;

    @FXML
    private Label jugador1Final;

    @FXML
    private Label jugador2Final;

    @FXML
    private Label palabrasColocadas1;

    @FXML
    private Label palabrasColocadas2;

    @FXML
    private Label palabrasNumeros1;

    @FXML
    private Label palabrasNumeros2;

    @FXML
    private Label puntosFinal1;

    @FXML
    private Label puntosFinal2;

    @FXML
    private Label puntosNumeros1;

    @FXML
    private Label puntosNumeros2;

    @FXML
    private ImageView resultado1;

    @FXML
    private ImageView resultado2;

    @FXML
    private Button salirBtn;

    @FXML
    private Label finPartidaLabel;


    private ImageView ImagefichaSeleccionada;
    private ImageView ImagefichaSeleccionadaAux = new ImageView();
    private Ficha fichaSeleccionada;

    private ImageView[] atril;

    private Game game;

    @FXML
    void initialize() {
        atril = new ImageView[]{ficha1, ficha2, ficha3, ficha4, ficha5, ficha6, ficha7};

        game = new Game();
        configureBoard();
        rellenarAtrilJugadores();
        configureAtrilJugadores();
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 20);
        Font font15 = Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 15);

        player1.setText(game.getJugadores()[0].getAlias());
        player2.setText(game.getJugadores()[1].getAlias());
        jugador1Final.setText(game.getJugadores()[0].getAlias());
        jugador2Final.setText(game.getJugadores()[1].getAlias());

        capaGris.setVisible(false);
        finPartida.setVisible(false);

        if (font != null) {
            System.out.println("Font loaded successfully");
            indicadorBolsa.setFont(font);
            player1.setFont(font);
            player2.setFont(font);
            puntos1.setFont(font);
            puntos2.setFont(font);
            puntosFinal1.setFont(font15);
            puntosFinal2.setFont(font15);
            finPartidaLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 30));
            puntosNumeros1.setFont(font15);
            puntosNumeros2.setFont(font15);
            palabrasColocadas1.setFont(font15);
            palabrasColocadas2.setFont(font15);
            palabrasNumeros1.setFont(font15);
            palabrasNumeros2.setFont(font15);
            jugador1Final.setFont(font);
            jugador2Final.setFont(font);
            salirBtn.setFont(font);

        }
        cambiarOpacidad();
        actualizarDatosBolsa(game.getBolsaFichas(), indicadorBolsa);

    }

    private void cambiarOpacidad(){
        if (game.getTurnoActual() == 0) {
            jugador1.setOpacity(1.0);
            jugador2.setOpacity(0.25);
        }else if (game.getTurnoActual() == 1) {
            jugador2.setOpacity(1.0);
            jugador1.setOpacity(0.25);
        }
    }

    private void siguienteTurno(){
        if (game.confirmarJugada()){
            terminarPartida();
            rellenarAtrilJugadores();
            configureAtrilJugadores();
            actualizarPuntajeVista();
            actualizarDatosBolsa(game.getBolsaFichas(), indicadorBolsa);
            cambiarOpacidad();
        }else System.out.println("Jugada no confirmada");
    }

    private void actualizarPuntajeVista(){
        puntos1.setText(String.valueOf(game.getJugadores()[0].getScoreInGame()));
        puntos2.setText(String.valueOf(game.getJugadores()[1].getScoreInGame()));
    }

    private void terminarPartida(){
        Image victoria = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/victoria.png")));
        Image derrota = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/derrota.png")));
        if(game.esPartidaTerminada()){
            puntosNumeros1.setText(String.valueOf(game.getJugadores()[0].getScoreInGame()));
            puntosNumeros2.setText(String.valueOf(game.getJugadores()[1].getScoreInGame()));
            palabrasNumeros1.setText(String.valueOf(game.getJugadores()[0].getCantidadPalabrasColocadas()));
            palabrasNumeros2.setText(String.valueOf(game.getJugadores()[1].getCantidadPalabrasColocadas()));
            capaGris.setVisible(true);
            finPartida.setVisible(true);
            int index = game.calcularGanador();
            if(index == 0){
                resultado1.setImage(victoria);
                resultado2.setImage(derrota);
            }else if(index == 1){
                resultado1.setImage(derrota);
                resultado2.setImage(victoria);
            }
        }
    }

    private void actualizarDatosBolsa(BolsaFichas bolsaFichas, Label label){
        label.setText(String.valueOf(bolsaFichas.getListaFichas().size()));
    }

    private void rellenarAtrilJugadores() {
        game.actualizarAtrilJugadores();
    }

    private void configureAtrilJugadores() {
        for (int i = 0; i < 7; i++){
            if(game.getJugadorActual().getAtril()[i] == null){
                atril[i].setImage(new Image(String.valueOf(ScrabbleController.class.getResource("/images/atrilVacio.png"))));
            }else {
                System.out.println("Actualizando casilla en posición: " + i + " con imagen: " + game.getJugadorActual().getAtril()[i].getImagen());
                atril[i].setImage(game.getJugadorActual().getAtril()[i].getImagen());
            }
        }
    }

    private void configureBoard() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                Casilla casilla = game.getBoard().getCasillas()[row][col];
                if (casilla != null && casilla.getFicha() != null) {
                    Image image = casilla.getImagen();
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(35);
                    imageView.setFitWidth(35);
                    if (image != null) {
                        styleCell(imageView);
                        placeCellInGrid(imageView, col, row);
                        addClickEvent(imageView, row, col);
                    }
                }
            }
        }
    }

    private void styleCell(ImageView cell) {
        cell.setStyle("-fx-border-color: black; -fx-pref-width: 35; -fx-pref-height: 35;");
    }

    private void placeCellInGrid(ImageView cell, int col, int row) {
        GridPane.setColumnIndex(cell, col);
        GridPane.setRowIndex(cell, row);
        scrabbleBoard.getChildren().add(cell);
    }

    private void eventoDeMouseEnTablero(int row, int col, ImageView cell){

        if(game.getBoard().getCasillas()[row][col].isMovable() && !game.getBoard().getCasillas()[row][col].getFicha().isEmpty()){
            recogerFichaEnCasilla(row, col, cell);
        }else if (game.getBoard().getCasillas()[row][col].getFicha().isEmpty()){
            colocarFichaEnCasilla(row, col, cell);
        }
        game.getJugadorActual().mostrarAtrilEnConsola();
        game.getBoard().mostrarTableroEnConsola();

    }

    @FXML
    private void seleccionarFicha(MouseEvent event) {
        Image atrilVacio = new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png")));
        String atrilVacioUrl = atrilVacio.getUrl();
        ImageView sourceImageView = (ImageView) event.getSource();
        String urlImage = sourceImageView.getImage().getUrl();
        int index = hboxAtril.getChildren().indexOf(sourceImageView);

        if (urlImage.equals(atrilVacioUrl)) {
            return;
        }
        if (ImagefichaSeleccionada == null || !ImagefichaSeleccionada.getImage().getUrl().equals(urlImage)) {
            ImagefichaSeleccionada = new ImageView();

            fichaSeleccionada = game.getJugadorActual().getAtril()[index];
            ImagefichaSeleccionada.setImage(sourceImageView.getImage());

            ImagefichaSeleccionadaAux.setFitHeight(75);
            ImagefichaSeleccionadaAux.setFitWidth(75);
            ImagefichaSeleccionadaAux = sourceImageView;
            sourceImageView.setFitHeight(95);
            sourceImageView.setFitWidth(95);

            System.out.println("Ficha seleccionada: " + fichaSeleccionada);

        } else if (ImagefichaSeleccionada.getImage().getUrl().equals(urlImage)) {
            sourceImageView.setFitHeight(75);
            sourceImageView.setFitWidth(75);
            ImagefichaSeleccionada = null;
            fichaSeleccionada = null;
        }
    }

    private void colocarFichaEnCasilla(int row, int col, ImageView cell) {
        if (ImagefichaSeleccionada != null) {
            int index = hboxAtril.getChildren().indexOf(ImagefichaSeleccionadaAux);
            cell.setImage(ImagefichaSeleccionada.getImage());

            game.ponerFicha(row, col, fichaSeleccionada);
            ImagefichaSeleccionada = null; // Restablecer la ficha seleccionada
            fichaSeleccionada = null;
            game.getJugadorActual().getAtril()[index] = null;

            ImagefichaSeleccionadaAux.setFitHeight(75);
            ImagefichaSeleccionadaAux.setFitWidth(75);
            ImagefichaSeleccionadaAux.setImage(new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png"))));

            System.out.println(Arrays.toString(game.getJugadorActual().getAtril()));
            System.out.println("Ficha colocada en [" + row + "][" + col + "]");
        } else {
            System.out.println("No hay ficha seleccionada");
        }
    }

    private void recogerFichaEnCasilla(int row, int col, ImageView cell) {
        devolverFichaAlAtril(row, col, cell);
        game.quitarFicha(row, col);
        cell.setImage(game.getBoard().getCasillas()[row][col].getImagen());
    }

    private void devolverFichaAlAtril(int row, int col, ImageView cell) {
        Image atrilVacio = new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png")));
        String atrilVacioUrl = atrilVacio.getUrl();

        for (ImageView imageView : atril) {
            if (imageView.getImage().getUrl().equals(atrilVacioUrl)) {
                imageView.setImage(cell.getImage());
                break;
            }
        }
    }

    @FXML
    private void playPalabra(){
        siguienteTurno();
    }

    @FXML
    private void redoPress(){
        ImageView imagen;

        if (game.getIndiceFichasPuestas() != null) {
            int [] indices = game.getIndiceFichasPuestas().getLast();
            imagen = obtenerNodoEnGridPane(scrabbleBoard, indices[0], indices[1]);
            recogerFichaEnCasilla(indices[0], indices[1], imagen);
        }
    }

    private ImageView obtenerNodoEnGridPane(GridPane gridPane, int row, int col) {
        for (Node imagen : gridPane.getChildren()){
            if(imagen instanceof ImageView && GridPane.getRowIndex(imagen) == row && GridPane.getColumnIndex(imagen) == col){
                return (ImageView) imagen;
            }
        }
        return null;
    }

    @FXML
    private void passPress(){
        terminarPartida();
        if (game.isEsPrimeraJugada()) return;
        ImageView imagen;
        ArrayList<int[]> indices = game.getIndiceFichasPuestas();

        if (indices != null) {
            ArrayList<int[]> indicesAux = new ArrayList<>(indices);
            for (int[]indice : indicesAux) {
                imagen = obtenerNodoEnGridPane(scrabbleBoard, indice[0], indice[1]);
                recogerFichaEnCasilla(indice[0], indice[1], imagen);
            }
        }
        game.pasarTurno();
        rellenarAtrilJugadores();
        configureAtrilJugadores();
        cambiarOpacidad();
        actualizarDatosBolsa(game.getBolsaFichas(), indicadorBolsa);
    }

    private void addClickEvent(ImageView cell, int row, int col) {
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> eventoDeMouseEnTablero(row, col, cell));
    }

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


        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Ficha getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(Ficha fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }
}
