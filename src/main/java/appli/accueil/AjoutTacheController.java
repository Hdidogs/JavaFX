package appli.accueil;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Liste;
import model.Type;
import repository.ListeRepository;
import repository.TacheRepository;
import repository.TypeRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AjoutTacheController {
    @FXML
    private TextField nomField;
    @FXML
    private CheckBox etatCheckBox;
    @FXML
    private ComboBox<Type> typeComboBox;

    @FXML
    private ComboBox<Liste> listeComboBox;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        try {
            // Charger les listes dans le ComboBox listeComboBox
            List<Liste> listes = ListeRepository.getAllListes();
            ObservableList<Liste> listeOptions = FXCollections.observableArrayList(listes);
            listeComboBox.setItems(listeOptions);

            // Charger les types dans le ComboBox typeComboBox
            List<Type> types = TypeRepository.getAllTypes();
            ObservableList<Type> typeOptions = FXCollections.observableArrayList(types);
            typeComboBox.setItems(typeOptions);
        } catch (SQLException e) {
            errorLabel.setText("Erreur lors du chargement des données : " + e.getMessage());
        }
    }


    @FXML
    protected void onSaveClick() throws SQLException, IOException {
        String nom = nomField.getText();
        boolean etat = etatCheckBox.isSelected();
        Liste selectedListe = listeComboBox.getValue();
        Type selectedType = typeComboBox.getValue();

        if (nom.isEmpty() || selectedListe == null || selectedType == null) {
            errorLabel.setText("Veuillez remplir tous les champs");
        } else {
            if (TacheRepository.ajouterTache(nom, etat, selectedListe.getIdListe(), selectedType.getIdType())) {
                StartApplication.changeScene("accueil/tacheview.fxml");
            } else {
                errorLabel.setText("Erreur lors de l'ajout de la tâche");
            }
        }
    }

    @FXML
    protected void onCancelClick() throws IOException {
        StartApplication.changeScene("accueil/tacheview.fxml");
    }
}
