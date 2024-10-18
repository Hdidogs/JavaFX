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
import model.Liste;
import repository.ListeRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AccueilViewController implements Initializable {
    @FXML
    private TableView<Liste> tableauListe;
    private Liste item;

    String[][] colonnes = {{"Id liste", "idListe"},
            {"Nom", "nom"}
    };

    @FXML
    protected void onDeconnexionClick() throws IOException {
        StartApplication.changeScene("accueil/loginview.fxml");
    }

    @FXML
    protected void onTypeClick() throws IOException {
        System.out.println("Les types");
    }

    @FXML
    protected void onAddListClick() throws IOException {
        StartApplication.changeScene("accueil/addlisteview.fxml");
    }

    @FXML
    protected void onSupprimerClick() throws IOException, SQLException {
        if (item != null) {
            ListeRepository.supprimerListe(item.getIdListe());
            tableauListe.getItems().remove(item);
            item = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Liste, String> maColonne = new TableColumn<>(colonnes[i][0]);

            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauListe.getColumns().add(maColonne);
        }

        try {
            List<Liste> listes = ListeRepository.getAllListes();
            tableauListe.getItems().addAll(listes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TableView<Liste> getTableauListe() {
        return tableauListe;
    }

    @FXML
    void onListeSelection(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            TablePosition cell = tableauListe.getSelectionModel().getSelectedCells().get(0);
            int row = cell.getRow();
            TableColumn column = cell.getTableColumn();
            item = tableauListe.getItems().get(row);
            StartApplication.changeScene("accueil/editlisteview", new EditerListeController(item));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            TablePosition cell = tableauListe.getSelectionModel().getSelectedCells().get(0);
            int row = cell.getRow();
            TableColumn column = cell.getTableColumn();
            item = tableauListe.getItems().get(row);
        }
    }
}
