package ve.edu.ucab.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import ve.edu.ucab.models.Casilla;
import ve.edu.ucab.models.Ficha;
import ve.edu.ucab.models.Game;

import java.util.Arrays;

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
    private HBox hboxAtril;

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
    }

    private void siguienteTurno(){
        if (game.confirmarJugada()){
            rellenarAtrilJugadores();
            configureAtrilJugadores();
        }else System.out.println("Jugada no confirmada");
    }

    void rellenarAtrilJugadores() {
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

    private void addClickEvent(ImageView cell, int row, int col) {
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> eventoDeMouseEnTablero(row, col, cell));
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
