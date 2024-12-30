package ve.edu.ucab.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ve.edu.ucab.models.Casilla;
import ve.edu.ucab.models.Game;

public class ScrabbleController {
    @FXML
    private GridPane scrabbleBoard;
    private Game game;

    @FXML
    void initialize() {
        game = new Game();
        configureBoard();
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
