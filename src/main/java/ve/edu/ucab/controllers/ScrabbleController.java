package ve.edu.ucab.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ve.edu.ucab.models.Casilla;
import ve.edu.ucab.models.Game;
import ve.edu.ucab.models.Jugador;

public class ScrabbleController {
    @FXML
    private GridPane scrabbleBoard;

    @FXML
    private Label ficha1;

    @FXML
    private Label ficha2;

    @FXML
    private Label ficha3;

    @FXML
    private Label ficha4;

    @FXML
    private Label ficha5;

    @FXML
    private Label ficha6;

    @FXML
    private Label ficha7;

    private Label[] atril;

    private Game game;

    @FXML
    void initialize() {
        atril = new Label[]{ficha1, ficha2, ficha3, ficha4, ficha5, ficha6, ficha7};

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
            atril[i].setText(game.getJugadores()[0].getAtril()[i].toString());
        }
    }

    private void configureBoard() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                Casilla casilla = game.getBoard().getCasillas()[row][col];
                if (casilla != null && casilla.getFicha() != null) {
                    Label letraLabel = casilla.getFicha().getLetraLabel();
                    if (letraLabel != null) {
                        styleCell(letraLabel);
                        placeCellInGrid(letraLabel, col, row);
                        addClickEvent(letraLabel, row, col);
                    }
                }
            }
        }
    }

    private void styleCell(Label cell) {
        cell.setStyle("-fx-border-color: black; -fx-pref-width: 35; -fx-pref-height: 35;");
    }

    private void placeCellInGrid(Label cell, int col, int row) {
        GridPane.setColumnIndex(cell, col);
        GridPane.setRowIndex(cell, row);
        scrabbleBoard.getChildren().add(cell);
    }

    private void addClickEvent(Label cell, int row, int col) {
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleCellClick(row, col));
    }

    private void handleCellClick(int row, int col) {
        System.out.println("Clic en la celda [" + row + "][" + col + "]");
        // Lógica adicional aquí
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
