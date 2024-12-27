package appli.accueil;

import appli.StartApplication;
import model.UtilisateurConnecte;
import repository.UtilisateurRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private Label erreurText;

    @FXML
    protected void onConnexionButtonClick() throws SQLException, IOException {
        if (!emailField.getText().isEmpty() || !passwordField.getText().isEmpty()) {
            if (UtilisateurRepository.connexion(emailField.getText(), passwordField.getText()) != null) {
                UtilisateurConnecte.initInstance(UtilisateurRepository.connexion(emailField.getText(), passwordField.getText()));
                erreurText.setTextFill(Color.GREEN);
                erreurText.setText("Connexion réussie.");
                StartApplication.changeScene("accueil/accueilview.fxml");
            } else {
                erreurText.setTextFill(javafx.scene.paint.Color.RED);
                erreurText.setText("Email ou mot de passe incorrect.");
            }
        } else {
            erreurText.setTextFill(javafx.scene.paint.Color.RED);
            erreurText.setText("Veuillez remplir tous les champs.");
        }
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