package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.ListeRepository;
import repository.TypeRepository;

import java.io.IOException;
import java.sql.SQLException;

public class AjoutTypeController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField codeCouleurField;

    @FXML
    private Label erreurText;

    @FXML
    protected void onAddListeClick() throws SQLException {
        if (nomField.getText().isEmpty() || codeCouleurField.getText().isEmpty()) {
            erreurText.setText("Le nom et le code couleur du type ne peuvent pas être vide");
        } else {
            if (codeCouleurField.getText().length() < 7) {
                erreurText.setText("Le code couleur doit être composé de 7 caractères au maximum");
                return;
            }
            if (TypeRepository.ajoutType(nomField.getText(), codeCouleurField.getText())) {
                try {
                    StartApplication.changeScene("accueil/typeview.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                erreurText.setText("Erreur lors de l'ajout de la liste");
            }
        }
    }

    @FXML
    protected void onRetourClick() throws IOException {
        StartApplication.changeScene("accueil/typeview.fxml");
    }
}
