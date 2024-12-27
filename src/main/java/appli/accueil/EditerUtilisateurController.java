package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.UtilisateurConnecte;
import repository.UtilisateurRepository;

import java.io.IOException;
import java.sql.SQLException;

public class EditerUtilisateurController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField motDePasseField;

    @FXML
    protected void onSaveClick() throws IOException, SQLException {
        if (motDePasseField.getText().isEmpty()) {
            if (UtilisateurRepository.modifierUtilisateur(UtilisateurConnecte.getInstance().getEmail(), nomField.getText().isEmpty() ? UtilisateurConnecte.getInstance().getNom() : nomField.getText(), prenomField.getText().isEmpty() ? UtilisateurConnecte.getInstance().getPrenom() : prenomField.getText(), emailField.getText().isEmpty() ? UtilisateurConnecte.getInstance().getEmail() : emailField.getText())) StartApplication.changeScene("accueil/accueilview.fxml");
        } else {
            if (UtilisateurRepository.modifierUtilisateur(UtilisateurConnecte.getInstance().getEmail(), nomField.getText().isEmpty() ? UtilisateurConnecte.getInstance().getNom() : nomField.getText(), prenomField.getText().isEmpty() ? UtilisateurConnecte.getInstance().getPrenom() : prenomField.getText(), emailField.getText().isEmpty() ? UtilisateurConnecte.getInstance().getEmail() : emailField.getText(), motDePasseField.getText())) StartApplication.changeScene("accueil/accueilview.fxml");;
        }
    }

    @FXML
    protected void onCancelClick() throws IOException {
        StartApplication.changeScene("accueil/accueilview.fxml");
    }
}
