package appli.accueil;

import appli.StartApplication;
import appli.repository.ListeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ListeController {
    @FXML
    private TextField listeField;

    @FXML
    private Label erreurText;

    @FXML
    protected void onAddListeClick() throws SQLException {
        if (listeField.getText().isEmpty()) {
            erreurText.setText("Le nom de la liste ne peut pas être vide");
        } else {
            if (ListeRepository.ajouterListe(listeField.getText()) == null) {
                erreurText.setText("Erreur lors de l'ajout de la liste");
            } else {
                try {
                    StartApplication.changeScene("accueil/accueilview.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    protected void onRetourClick() throws IOException {
        StartApplication.changeScene("accueil/accueilview.fxml");
    }
}
