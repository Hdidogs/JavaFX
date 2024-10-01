package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Liste;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilViewController implements Initializable {
    @FXML
    private TableView<Liste> tableauListe;

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
        System.out.println("Ajouter une liste");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Liste, String> maColonne = new TableColumn<>(colonnes[i][0]);

            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauListe.getColumns().add(maColonne);
        }
    }
}
