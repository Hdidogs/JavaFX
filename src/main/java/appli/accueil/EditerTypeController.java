package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Liste;
import model.Type;
import repository.TypeRepository;

import java.io.IOException;
import java.sql.SQLException;

public class EditerTypeController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField codeCouleurField;

    @FXML
    private Label erreurText;

    @FXML
    private Label label;

    private Type type;

    public EditerTypeController(Type type) {

        this.type = type;
    }

    @FXML
    public void initialize() {
        label.setText("Modifier un type :");
        nomField.setText(type.getNom());
        codeCouleurField.setText(type.getCodeCouleur());
    }

    @FXML
    protected void onAddListeClick() throws SQLException {
        if (nomField.getText().isEmpty() || codeCouleurField.getText().isEmpty()) {
            erreurText.setText("Le nom et le code couleur du type ne peuvent pas être vide");
        } else {
            if (codeCouleurField.getText().length() < 7) {
                erreurText.setText("Le code couleur doit être composé de 7 caractères au maximum");
                return;
            }
            if (TypeRepository.modifierType(type, nomField.getText(), codeCouleurField.getText())) {
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
