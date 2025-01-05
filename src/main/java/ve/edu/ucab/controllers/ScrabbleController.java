package ve.edu.ucab.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ve.edu.ucab.models.Casilla;
import ve.edu.ucab.models.Game;
import ve.edu.ucab.models.Jugador;

public class ScrabbleController {
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

    private ImageView fichaSeleccionada;
    private ImageView fichaSeleccionadaAux = new ImageView();

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

    void rellenarAtrilJugadores() {
        game.actualizarAtrilJugadores();
    }

    private void configureAtrilJugadores() {
        for (int i = 0; i < 7; i++){
            atril[i].setImage(game.getJugadores()[0].getAtril()[i].getImagen());
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

    private void colocarFichaEnCasilla(int row, int col, ImageView cell) {
        if (fichaSeleccionada != null) {
            cell.setImage(fichaSeleccionada.getImage());
            fichaSeleccionada = null; // Restablecer la ficha seleccionada
            fichaSeleccionadaAux.setImage(new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png"))));
            System.out.println("Ficha colocada en [" + row + "][" + col + "]");
        } else {
            System.out.println("No hay ficha seleccionada");
        }
    }

    @FXML
    private void seleccionarFicha(MouseEvent event) {
        Image atrilVacio = new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png")));
        String atrilVacioUrl = atrilVacio.getUrl();
        String urlImage = ((ImageView) event.getSource()).getImage().getUrl();

        if(((ImageView) event.getSource()).getImage().getUrl().equals(atrilVacioUrl)) {
            return;

        }else if (fichaSeleccionada == null || !fichaSeleccionada.getImage().getUrl().equals(urlImage)) {
            fichaSeleccionada = new ImageView();
            fichaSeleccionada.setImage(((ImageView) event.getSource()).getImage());
            fichaSeleccionadaAux.setFitHeight(75);
            fichaSeleccionadaAux.setFitWidth(75);
            fichaSeleccionadaAux = (ImageView) event.getSource();
            ((ImageView) event.getSource()).setFitHeight(95);
            ((ImageView) event.getSource()).setFitWidth(95);
            System.out.println("Ficha seleccionada: " + fichaSeleccionada.getImage().toString());

        }else if(fichaSeleccionada.getImage().getUrl().equals(urlImage)) {
            ((ImageView) event.getSource()).setFitHeight(75);
            ((ImageView) event.getSource()).setFitWidth(75);
            fichaSeleccionada = null;
            return;
        }

    }

    private void addClickEvent(ImageView cell, int row, int col) {
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> colocarFichaEnCasilla(row, col, cell));
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
