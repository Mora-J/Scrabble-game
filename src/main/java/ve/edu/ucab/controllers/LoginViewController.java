package ve.edu.ucab.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ve.edu.ucab.models.Jugador;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controlador para la vista de inicio de sesión de los jugadores.
 */
public class LoginViewController {

    /**
     * Indica si el usuario 1 ha iniciado sesión.
     */
    protected boolean user1Logged = false;

    /**
     * Indica si el usuario 2 ha iniciado sesión.
     */
    private boolean user2Logged = false;

    @FXML
    private Label confirmError;

    @FXML
    private TextField campo1;

    @FXML
    private TextField campo2;

    @FXML
    private Label confirmacion1;

    @FXML
    private Label confirmacion2;

    @FXML
    private Button login1;

    @FXML
    private Button login2;

    @FXML
    private Label user1;

    @FXML
    private Label user2;

    @FXML
    private Button play;

    /**
     * Lista de jugadores.
     */
    private ArrayList<Jugador> jugadores = new ArrayList<>();

    /**
     * Método de inicio de sesión para un usuario.
     *
     * @param userLabel          El Label donde se mostrará el nombre del usuario.
     * @param userTextField      El campo de texto donde el usuario ingresa su nombre.
     * @param confirmationLabel  El Label donde se mostrará el mensaje de confirmación.
     * @return true si el usuario es válido y se carga correctamente, false en caso contrario.
     */
    @FXML
    boolean login(Label userLabel, TextField userTextField, Label confirmationLabel) {
        confirmError.setVisible(false);
        String user = userTextField.getText();
        if (validarUsuario(user)) {
            cargarJugador(user.toLowerCase());
            userLabel.setText(user);
            confirmationLabel.setText("Bienvenido, su usuario esta registrado");
            return true;
        }else{
            userLabel.setText("Su usuario no existe");
            confirmationLabel.setText("Su usuario no esta registrado, porfavor registrese");
            return false;
        }
    }

    /**
     * Carga un jugador en la lista de jugadores.
     *
     * @param nombre El nombre del jugador.
     */
    private void cargarJugador(String nombre){
        jugadores.add(new Jugador(nombre));
    }

    /**
     * Maneja el evento de inicio de sesión para el usuario 1.
     *
     * @param event El evento de acción.
     */
    @FXML
    void login1(ActionEvent event) {
        user1Logged = login(user1, campo1, confirmacion1);
    }

    /**
     * Maneja el evento de inicio de sesión para el usuario 2.
     *
     * @param event El evento de acción.
     */
    @FXML
    void login2(ActionEvent event) {
        user2Logged = login(user2, campo2, confirmacion2);
    }

    /**
     * Valida el nombre de usuario.
     *
     * @param usuario El nombre de usuario a validar.
     * @return true si el nombre de usuario no es nulo y no está vacío, false en caso contrario.
     */
    private boolean validarUsuario(String usuario) {
        return usuario != null && !usuario.isEmpty();
    }

    /**
     * Maneja el evento de inicio del juego.
     *
     * @param event El evento de acción.
     */
    @FXML
    void play(ActionEvent event) {
        if (!user1Logged || !user2Logged) {
            confirmError.setVisible(true);
        }else{
            try {
                confirmar(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Confirma el inicio del juego y carga la vista del menú.
     *
     * @param event El evento de acción.
     * @throws IOException Si ocurre un error al cargar la vista del menú.
     */
    @SuppressWarnings("DuplicatedCode")
    @FXML
    void confirmar(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean wasFullScreen = stage.isFullScreen();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        MenuController menuController = fxmlLoader.getController();
        menuController.setJugadores(jugadores);

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

    /**
     * Verifica si el usuario 1 ha iniciado sesión.
     *
     * @return true si el usuario 1 ha iniciado sesión, false en caso contrario.
     */
    public boolean isUser1Logged() {
        return user1Logged;
    }

        /**
     * Establece si el usuario 1 ha iniciado sesión.
     *
     * @param user1Logged true si el usuario 1 ha iniciado sesión, false en caso contrario.
     */
    public void setUser1Logged(boolean user1Logged) {
        this.user1Logged = user1Logged;
    }

    /**
     * Verifica si el usuario 2 ha iniciado sesión.
     *
     * @return true si el usuario 2 ha iniciado sesión, false en caso contrario.
     */
    public boolean isUser2Logged() {
        return user2Logged;
    }

    /**
     * Establece si el usuario 2 ha iniciado sesión.
     *
     * @param user2Logged true si el usuario 2 ha iniciado sesión, false en caso contrario.
     */
    public void setUser2Logged(boolean user2Logged) {
        this.user2Logged = user2Logged;
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
}
