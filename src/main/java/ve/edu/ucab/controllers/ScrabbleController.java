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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ve.edu.ucab.models.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Controlador para la vista del juego de Scrabble.
 */
public class ScrabbleController {
    @FXML
    private AnchorPane capaGris;

    @FXML
    private Label confirmError;

    @FXML
    private AnchorPane estadisticas;


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
    private AnchorPane finPartida;

    @FXML
    private Label finPartidaLabel;

    @FXML
    private HBox hboxAtril;

    @FXML
    private Label indicadorBolsa;

    @FXML
    private VBox jugador1;

    @FXML
    private Label jugador1Final;

    @FXML
    private VBox jugador2;

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
    private Button passButton;

    @FXML
    private Button passButton1;

    @FXML
    private Button playButton;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    @FXML
    private Label puntos1;

    @FXML
    private Label puntos2;

    @FXML
    private Label puntosFinal1;

    @FXML
    private Label puntosFinal2;

    @FXML
    private Label puntosNumeros1;

    @FXML
    private Label puntosNumeros2;

    @FXML
    private Button redoButton;

    @FXML
    private ImageView resultado1;

    @FXML
    private ImageView resultado2;

    @FXML
    private Button salirBtn;


    @FXML
    private GridPane scrabbleBoard;

    @FXML
    private Label tiempo1;

    @FXML
    private Label tiempo2;

    @FXML
    private Label tiempoJugado1;

    @FXML
    private Label tiempoJugado2;

    @FXML
    private Label palabrasTotales1;

    @FXML
    private Label palabrasTotales2;

    @FXML
    private Label jugador2Final1;

    @FXML
    private Label jugador1Final1;

    @FXML
    private Label score1;

    @FXML
    private Label score2;

    @FXML
    private Label scoreTotal1;

    @FXML
    private Label scoreTotal2;

    @FXML
    private Label palabras1;

    @FXML
    private Label palabras2;

    @FXML
    private Label estadisticasLabel;

    @FXML
    private Label tiempoLabel;

    private ImageView ImagefichaSeleccionada;
    private ImageView ImagefichaSeleccionadaAux = new ImageView();
    private Ficha fichaSeleccionada;

    private ImageView[] atril;

    private Game game;

    @FXML
    private GridPane comodinPane;

    /**
     * Configura las estadísticas del jugador en la interfaz.
     */
    @SuppressWarnings("DuplicatedCode")
    private void configureStats(){
        Jugador jugador1 = game.getJugadores()[0];
        Jugador jugador2 = game.getJugadores()[1];

        jugador1Final1.setText(jugador1.getAlias());
        jugador2Final1.setText(jugador2.getAlias());
        score1.setText(String.valueOf(jugador1.getScoreTotal()));
        score2.setText(String.valueOf(jugador2.getScoreTotal()));
        tiempo1.setText(jugador1.getTiempoJugado());
        tiempo2.setText(jugador1.getTiempoJugado());
        palabras1.setText(String.valueOf(jugador1.getCantidadDePalabras()));
        palabras2.setText(String.valueOf(jugador2.getCantidadDePalabras()));
    }

    /**
     * Inicializa configuraciones adicionales después de la inicialización básica.
     */
    private void postInitialize(){
        configureBoard();
        rellenarAtrilJugadores();
        configureAtrilJugadores();
        configureStats();
        player1.setText(game.getJugadores()[0].getAlias());
        player2.setText(game.getJugadores()[1].getAlias());
        jugador1Final.setText(game.getJugadores()[0].getAlias());
        jugador2Final.setText(game.getJugadores()[1].getAlias());
        actualizarPuntajeVista();

        cambiarOpacidad();
        actualizarDatosBolsa(game.getBolsaFichas(), indicadorBolsa);
    }

    /**
     * Metodo llamado automáticamente después de la carga del archivo FXML.
     */
    @FXML
    void initialize() {
        confirmError.setVisible(false);
        atril = new ImageView[]{ficha1, ficha2, ficha3, ficha4, ficha5, ficha6, ficha7};

        /*for (ImageView imageView : atril) {
            imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png"))));
        }*/

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 20);
        Font font15 = Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.otf"), 15);

        capaGris.setVisible(false);
        finPartida.setVisible(false);
        estadisticas.setVisible(false);
        comodinPane.setVisible(false);

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
    }

    /**
     * Establece el juego actual y realiza la configuración posterior a la inicialización.
     *
     * @param game El juego a establecer.
     */
    public void setGame(Game game) {
        this.game = game;
        game.calcularTiempo();
        postInitialize();
    }

    /**
     * Cambia la opacidad de los elementos de la interfaz según el turno actual del juego.
     */
    private void cambiarOpacidad(){
        if (game.getTurnoActual() == 0) {
            jugador1.setOpacity(1.0);
            jugador2.setOpacity(0.5);
        }else if (game.getTurnoActual() == 1) {
            jugador2.setOpacity(1.0);
            jugador1.setOpacity(0.5);
        }
    }

    /**
     * Procede al siguiente turno del juego, actualizando las vistas y guardando la partida pendiente.
     */
    private void siguienteTurno(){
        confirmError.setVisible(false);
        if (game.confirmarJugada()){
            terminarPartida();
            rellenarAtrilJugadores();
            configureAtrilJugadores();
            actualizarPuntajeVista();
            actualizarDatosBolsa(game.getBolsaFichas(), indicadorBolsa);
            cambiarOpacidad();
            game.endTime();
            JsonUtil.guardarPartidaPendiente(game);
        }else {
            System.out.println("Jugada no confirmada");
            confirmError.setVisible(true);
            confirmError.setText("Usted hizo una jugada inválida! o su palabra no es Valida!");
        }
    }

    /**
     * Actualiza la vista del puntaje de los jugadores.
     */
    private void actualizarPuntajeVista(){
        puntos1.setText(String.valueOf(game.getJugadores()[0].getScoreInGame()));
        puntos2.setText(String.valueOf(game.getJugadores()[1].getScoreInGame()));
    }

    /**
     * Termina la partida, mostrando los resultados y las estadísticas finales.
     */
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
            game.endTime();
            game.calculateTotalTime();
            tiempoLabel.setText(game.getFormattedDuration());
            game.sumarTiempoJugadores();
            actualizarUsuarios();
            JsonUtil.guardarPartidaTerminada(game);
        }
    }


    /**
     * Actualiza los datos de la bolsa de fichas en la interfaz.
     *
     * @param bolsaFichas La bolsa de fichas.
     * @param label       El Label donde se mostrarán los datos.
     */
    private void actualizarDatosBolsa(BolsaFichas bolsaFichas, Label label){
        label.setText(String.valueOf(bolsaFichas.getListaFichas().size()));
    }

    /**
     * Rellena el atril de los jugadores con fichas de la bolsa.
     */
    private void rellenarAtrilJugadores() {
        game.actualizarAtrilJugadores();
    }

    /**
     * Configura el atril de los jugadores en la interfaz, actualizando las imágenes de las fichas.
     */
    private void configureAtrilJugadores() {
        Image imagen;
        for (int i = 0; i < 7; i++){
            Ficha ficha = game.getJugadorActual().getAtril()[i];
            String fichaPath = "/images/fichas/ficha"+ficha.getLetra()+".png";
            if(game.getJugadorActual().getAtril()[i] == null) {
                atril[i].setImage(new Image(String.valueOf(ScrabbleController.class.getResource("/images/atrilVacio.png"))));
            } else {
                imagen = new Image(Objects.requireNonNull(getClass().getResource(fichaPath)).toString());
                game.getJugadorActual().getAtril()[i].setImagen(imagen);
                System.out.println("Actualizando casilla en posición: " + i + " con imagen: " + game.getJugadorActual().getAtril()[i].getImagen());
                atril[i].setImage(imagen);}
        }
    }

    /**
     * Configura el tablero del juego, colocando las casillas y las fichas en la interfaz gráfica.
     */
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

    /**
     * Configura el tablero del juego, colocando las casillas y las fichas en la interfaz gráfica.
     */
    private void configureBoardComodin(int index) {
        BolsaFichas bolsaFichas = new BolsaFichas("sinShuffle");
        int indexCounter = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Ficha ficha = bolsaFichas.getListaFichas().get(indexCounter);
                if (ficha != null && !ficha.isEmpty() && !ficha.getLetra().equals("#")) {
                    Image image = ficha.getImagen();
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    if (image != null) {
                        styleCell(imageView);
                        placeCellInGridComodin(imageView, col, row);
                        addClickEventComodin(imageView, row, index, ficha);
                    }
                }
                indexCounter++;
            }
        }
    }

    private void placeCellInGridComodin(ImageView cell, int col, int row) {
        GridPane.setColumnIndex(cell, col);
        GridPane.setRowIndex(cell, row);
        comodinPane.getChildren().add(cell);
    }

    /**
     * Maneja los eventos de clic en el tablero.
     *
     * @param row  La fila de la celda clicada.
     * @param index  La ficha a cambiar.
     * @param cell La celda clicada.
     */
    private void eventoDeMouseEnTableroComodin(int row, int index, ImageView cell, Ficha ficha) {
        game.getJugadorActual().getAtril()[index] = ficha.clone();
        game.getJugadorActual().getAtril()[index].setLetra(ficha.getLetra());
        game.getJugadorActual().getAtril()[index].setValor(0);
        comodinPane.setVisible(false);
        capaGris.setVisible(false);
        confirmError.setVisible(true);
        confirmError.setText("Ficha seleccionada: " + ficha.getLetra());
    }

    /**
     * Añade un evento de clic a la celda especificada.
     *
     * @param cell La celda a la que añadir el evento.
     * @param row  La fila de la celda.
     * @param index  La ficha a cambiar.
     */
    private void addClickEventComodin(ImageView cell, int row, int index, Ficha ficha) {
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> eventoDeMouseEnTableroComodin(row, index, cell, ficha));
    }

    /**
     * Aplica un estilo a la celda especificada.
     *
     * @param cell La celda a estilizar.
     */
    private void styleCell(ImageView cell) {
        cell.setStyle("-fx-border-color: black; -fx-pref-width: 35; -fx-pref-height: 35;");
    }

    /**
     * Coloca la celda en la posición especificada del GridPane.
     *
     * @param cell La celda a colocar.
     * @param col  La columna en la que colocar la celda.
     * @param row  La fila en la que colocar la celda.
     */
    private void placeCellInGrid(ImageView cell, int col, int row) {
        GridPane.setColumnIndex(cell, col);
        GridPane.setRowIndex(cell, row);
        scrabbleBoard.getChildren().add(cell);
    }

    /**
     * Maneja los eventos de clic en el tablero.
     *
     * @param row  La fila de la celda clicada.
     * @param col  La columna de la celda clicada.
     * @param cell La celda clicada.
     */
    private void eventoDeMouseEnTablero(int row, int col, ImageView cell){

        if(game.getBoard().getCasillas()[row][col].isMovable() && !game.getBoard().getCasillas()[row][col].getFicha().isEmpty()){
            recogerFichaEnCasilla(row, col, cell);
        }else if (game.getBoard().getCasillas()[row][col].getFicha().isEmpty()){
            colocarFichaEnCasilla(row, col, cell);
        }
        game.getJugadorActual().mostrarAtrilEnConsola();
        game.getBoard().mostrarTableroEnConsola();

    }

    /**
     * Maneja la selección de una ficha desde el atril.
     *
     * @param event El evento de mouse.
     */
    @FXML
    private void seleccionarFicha(MouseEvent event) {
        Image atrilVacio = new Image(String.valueOf(getClass().getResource("/images/atrilVacio.png")));
        Image comodin = new Image(String.valueOf(getClass().getResource("/images/fichas/ficha#.png")));
        String comodinlUrl = comodin.getUrl();
        String atrilVacioUrl = atrilVacio.getUrl();
        ImageView sourceImageView = (ImageView) event.getSource();
        String urlImage = sourceImageView.getImage().getUrl();
        int index = hboxAtril.getChildren().indexOf(sourceImageView);

        if (urlImage.equals(atrilVacioUrl)) {
            return;
        } else if (urlImage.equals(comodinlUrl)) {
            this.comodinPane.setVisible(true);
            capaGris.setVisible(true);
            configureBoardComodin(index);

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

    /**
     * Coloca una ficha en una casilla del tablero.
     *
     * @param row  La fila de la casilla.
     * @param col  La columna de la casilla.
     * @param cell La celda donde colocar la ficha.
     */
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

    /**
     * Recoge una ficha de una casilla del tablero.
     *
     * @param row  La fila de la casilla.
     * @param col  La columna de la casilla.
     * @param cell La celda de la que recoger la ficha.
     */
    private void recogerFichaEnCasilla(int row, int col, ImageView cell) {
        devolverFichaAlAtril(row, col, cell);
        game.quitarFicha(row, col);
        cell.setImage(game.getBoard().getCasillas()[row][col].getImagen());
    }

    /**
     * Devuelve una ficha al atril del jugador.
     *
     * @param row  La fila de la casilla.
     * @param col  La columna de la casilla.
     * @param cell La celda desde la que devolver la ficha.
     */
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

    /**
     * Maneja el evento de jugar una palabra.
     */
    @FXML
    private void playPalabra(){
        siguienteTurno();
    }

    /**
     * Maneja el evento de rehacer la última acción.
     */
    @FXML
    private void redoPress(){
        confirmError.setVisible(false);
        ImageView imagen;

        if (game.getIndiceFichasPuestas() != null) {
            int [] indices = game.getIndiceFichasPuestas().getLast();
            imagen = obtenerNodoEnGridPane(scrabbleBoard, indices[0], indices[1]);
            recogerFichaEnCasilla(indices[0], indices[1], imagen);
        }
    }

    /**
     * Obtiene un nodo del GridPane en la posición especificada.
     *
     * @param gridPane El GridPane.
     * @param row      La fila del nodo.
     * @param col      La columna del nodo.
     * @return El nodo en la posición especificada, o null si no se encuentra.
     */
    private ImageView obtenerNodoEnGridPane(GridPane gridPane, int row, int col) {
        for (Node imagen : gridPane.getChildren()){
            if(imagen instanceof ImageView && GridPane.getRowIndex(imagen) == row && GridPane.getColumnIndex(imagen) == col){
                return (ImageView) imagen;
            }
        }
        return null;
    }

    /**
     * Maneja el evento de pasar el turno.
     */
    @SuppressWarnings("DuplicatedCode")
    @FXML
    private void passPress(){
        confirmError.setVisible(false);
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

    /**
     * Añade un evento de clic a la celda especificada.
     *
     * @param cell La celda a la que añadir el evento.
     * @param row  La fila de la celda.
     * @param col  La columna de la celda.
     */
    private void addClickEvent(ImageView cell, int row, int col) {
        cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> eventoDeMouseEnTablero(row, col, cell));
    }


    /**
     * Maneja el evento de salir del juego.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar la vista del menú.
     */
    @SuppressWarnings("DuplicatedCode")
    @FXML
    void salir(ActionEvent event) throws IOException {
        game.endTime();
        game.calculateTotalTime();
        JsonUtil.guardarPartidaPendiente(game);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        stage.setTitle("Menu!");
        stage.setScene(scene);
        stage.setFullScreen(wasFullScreen);

        MenuController menuController = fxmlLoader.getController();
        ArrayList<Jugador> jugadores = new ArrayList<>(Arrays.asList(game.getJugadores()));
        menuController.setJugadores(jugadores);


        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        stage.show();
    }

    /**
     * Muestra las estadísticas cuando se presiona la tecla ESC.
     *
     * @param keyEvent El evento de tecla.
     */
    @FXML
    void mostrarEstadisticasEsc(KeyEvent keyEvent) {
        confirmError.setVisible(false);
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            mostrarEstadisticas();
        }
    }

    /**
     * Muestra u oculta las estadísticas del juego.
     */
    @FXML
    void mostrarEstadisticas(){
        if (!estadisticas.isVisible()) {
            estadisticas.setVisible(true);
            capaGris.setVisible(true);
        }else {
            estadisticas.setVisible(false);
            capaGris.setVisible(false);
        }

    }

    /**
     * Reemplaza las fichas del atril del jugador.
     */
    @SuppressWarnings("DuplicatedCode")
    @FXML
    void reemplazarFichas(){
        confirmError.setVisible(false);
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
        game.reemplazarFichas();
        game.pasarTurno();
        rellenarAtrilJugadores();
        configureAtrilJugadores();
        cambiarOpacidad();
        actualizarDatosBolsa(game.getBolsaFichas(), indicadorBolsa);

    }

    /**
     * Obtiene el juego actual.
     *
     * @return El juego actual.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Obtiene la ficha seleccionada.
     *
     * @return La ficha seleccionada.
     */
    public Ficha getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    /**
     * Establece la ficha seleccionada.
     *
     * @param fichaSeleccionada La ficha a establecer como seleccionada.
     */
    public void setFichaSeleccionada(Ficha fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }


    /**
     * Actualiza los usuarios que terminaron la partida
     *
     *
     */
    public void actualizarUsuarios(){
        for (Jugador jugador : game.getJugadores()) {
            jugador.ajustarTiempo();
            actualizarUsuarioYGuardar(jugador.getAlias(),jugador.getScoreInGame(), jugador.getHorasJugadas(), jugador.getMinutosJugados(), jugador.getSegundosJugados(), jugador.getCantidadPalabrasColocadas());
        }
    }


    /**
     * Actualiza el usuario que se le pase
     *
     *
     */
    public void actualizarUsuarioYGuardar(String alias, int score, int horas, int minutos, int segundos, int palabras) {
        List<Usuario> usuarios = JsonUtil.cargarUsuariosDesdeJson();
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                if (usuario.getAlias().equals(alias)) {
                    usuario.actualizarEstadisticas(score, horas, minutos, segundos, palabras);
                    System.out.println("Estadísticas actualizadas para el usuario: " + usuario.getAlias());
                    JsonUtil.guardarUsuariosEnJson(usuarios);
                    return;
                }
            }
        }
        System.out.println("El usuario con alias " + alias + " no existe.");
    }

}
