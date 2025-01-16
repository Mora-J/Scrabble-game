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

import java.io.IOException;

public class LoginViewController {

    protected boolean user1Logged = false;
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

    @FXML
    boolean login(Label userLabel, TextField userTextField, Label confirmationLabel) {
        confirmError.setVisible(false);
        String user = userTextField.getText();
        if (validarUsuario(user)) {
            userLabel.setText(user);
            confirmationLabel.setText("Bienvenido, su usuario esta registrado");
            return true;
        }else{
            userLabel.setText("Su usuario no existe");
            confirmationLabel.setText("Su usuario no esta registrado, porfavor registrese");
            return false;
        }
    }

    @FXML
    void login1(ActionEvent event) {
        user1Logged = login(user1, campo1, confirmacion1);
    }

    @FXML
    void login2(ActionEvent event) {
        user2Logged = login(user2, campo2, confirmacion2);
    }

    private boolean validarUsuario(String usuario) {
        return usuario != null && !usuario.isEmpty();
    }

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


    @FXML
    void confirmar(ActionEvent event) throws IOException {
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

    public boolean isUser1Logged() {
        return user1Logged;
    }

    public void setUser1Logged(boolean user1Logged) {
        this.user1Logged = user1Logged;
    }

    public boolean isUser2Logged() {
        return user2Logged;
    }

    public void setUser2Logged(boolean user2Logged) {
        this.user2Logged = user2Logged;
    }
}
