package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Liste;
import repository.ListeRepository;

import java.io.IOException;
import java.sql.SQLException;

public class EditerListeController {
    @FXML
    private TextField listeField;

    @FXML
    private Label erreurText;

    private Liste liste;

    public EditerListeController(Liste liste) {
        this.liste = liste;
    }

    @FXML
    public void initialize() {
        listeField.setText(liste.getNom());
    }

    @FXML
    protected void onModifierListeClick() throws SQLException {
        if (listeField.getText().isEmpty()) {
            erreurText.setText("Le nom de la liste ne peut pas être vide");
        } else {
            liste.setNom(listeField.getText());
            if (ListeRepository.modifierListe(liste) == null) {
                erreurText.setText("Erreur lors de la modification de la liste");
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
