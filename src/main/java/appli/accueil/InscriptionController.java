package appli.accueil;

import appli.StartApplication;
import appli.repository.UtilisateurRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class InscriptionController {
    @FXML
    private Label errorLabel;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private PasswordField mdpConfirmField;

    @FXML
    protected void  onConnexionButtonClick() throws IOException {
        StartApplication.changeScene("accueil/loginview.fxml");
    }

    @FXML
    protected void onInscriptionButtonClick() throws SQLException {
        String email = emailField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String mdp = mdpField.getText();
        String mdpConfirm = mdpConfirmField.getText();

        if (!email.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !mdp.isEmpty() && !mdpConfirm.isEmpty()) {
            if (mdp.equals(mdpConfirm)) {
                if (UtilisateurRepository.getUtilisateurParEmail(email) == null) {
                    errorLabel.setTextFill(javafx.scene.paint.Color.RED);
                    errorLabel.setText("Cet email est déjà utilisé.");
                } else {
                    if (UtilisateurRepository.inscription(nom, prenom, email, mdp)) {
                        User user = new User(nom, prenom, email, mdp);
                        errorLabel.setTextFill(javafx.scene.paint.Color.GREEN);
                        errorLabel.setText("Inscription réussie.");
                        emailField.setText("");
                        nomField.setText("");
                        prenomField.setText("");
                        mdpField.setText("");
                        mdpConfirmField.setText("");
                    } else {
                        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
                        errorLabel.setText("Erreur lors de l'inscription.");
                    }
                }
            } else {
                errorLabel.setTextFill(javafx.scene.paint.Color.RED);
                errorLabel.setText("Les mots de passe ne correspondent pas.");
            }
        } else {
            errorLabel.setTextFill(javafx.scene.paint.Color.RED);
            errorLabel.setText("Veuillez remplir tous les champs.");
        }
    }
}
