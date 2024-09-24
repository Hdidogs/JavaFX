package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.lang.reflect.Field;

public class LoginController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private Field emailField;

    @FXML
    private Label erreurText;

    @FXML
    protected void onConnexionButtonClick() {
        System.out.println("Connexion");
    }

    @FXML
    protected void onInscriptionButtonClick() throws IOException {
        StartApplication.changeScene("accueil/inscriptionview.fxml");
    }

    @FXML
    protected void onMDPButtonClick() {
        System.out.println("Mot de passe oublié");
    }
}