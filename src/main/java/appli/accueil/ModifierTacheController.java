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
import model.Tache;
import model.Type;
import repository.ListeRepository;
import repository.TacheRepository;
import repository.TypeRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModifierTacheController {
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

    private Tache tache;

    public ModifierTacheController(Tache tache) {
        this.tache = tache;
    }

    @FXML
    public void initialize() {
        nomField.setText(tache.getNom());
        etatCheckBox.setSelected(tache.getEtat() == 1);

        try {
            List<Liste> listes = ListeRepository.getAllListes();
            ObservableList<Liste> listeOptions = FXCollections.observableArrayList(listes);
            listeComboBox.setItems(listeOptions);

            Liste selectedListe = listes.stream()
                    .filter(liste -> liste.getIdListe() == tache.getRefListe())
                    .findFirst()
                    .orElse(null);
            listeComboBox.setValue(selectedListe);

            List<Type> types = TypeRepository.getAllTypes();
            ObservableList<Type> typeOptions = FXCollections.observableArrayList(types);
            typeComboBox.setItems(typeOptions);

            Type selectedType = types.stream()
                    .filter(type -> type.getIdType() == tache.getRefType())
                    .findFirst()
                    .orElse(null);
            typeComboBox.setValue(selectedType);
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
            if (TacheRepository.modifierTache(tache.getIdTache(), nom, etat, selectedListe.getIdListe(), selectedType.getIdType())) {
                StartApplication.changeScene("accueil/tacheview.fxml");
            } else {
                errorLabel.setText("Erreur lors de la modification de la tâche");
            }
        }
    }

    @FXML
    protected void onCancelClick() throws IOException {
        StartApplication.changeScene("accueil/tacheview.fxml");
    }
}
