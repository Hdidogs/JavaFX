package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Tache;
import repository.TacheRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TacheViewController implements Initializable {

    @FXML
    private TableView<Tache> tableauTache;
    private Tache item;
    String[][] colonnes = {{"Id Tache", "idTache"},
            {"Nom", "nom"},
            {"Etat", "etat"},
            {"RefType", "refType"},
            {"RefListe", "refListe"},
    };

    private Tache selectedTache;

    @FXML
    protected void onAddTacheClick() throws IOException {
        StartApplication.changeScene("accueil/tacheformview", new AjoutTacheController());
    }

    @FXML
    protected void onSupprimerTacheClick() throws SQLException {
        if (selectedTache != null) {
            TacheRepository.supprimerTache(selectedTache.getIdTache());
            tableauTache.getItems().remove(selectedTache);
            selectedTache = null;
        }
    }

    @FXML
    protected void onBackTacheClick() throws IOException {
        StartApplication.changeScene("accueil/accueilview.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Tache, String> maColonne = new TableColumn<>(colonnes[i][0]);

            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauTache.getColumns().add(maColonne);
        }

        try {
            List<Tache> listes = TacheRepository.getAllTache();
            tableauTache.getItems().addAll(listes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onTacheSelection(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            TablePosition cell = tableauTache.getSelectionModel().getSelectedCells().get(0);
            int row = cell.getRow();
            TableColumn column = cell.getTableColumn();
            item = tableauTache.getItems().get(row);
            StartApplication.changeScene("accueil/tacheformview", new ModifierTacheController(item));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            TablePosition cell = tableauTache.getSelectionModel().getSelectedCells().get(0);
            int row = cell.getRow();
            TableColumn column = cell.getTableColumn();
            item = tableauTache.getItems().get(row);
        }
    }
}

