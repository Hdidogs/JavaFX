package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Type;
import repository.TypeRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TypeViewController implements Initializable {
    @FXML
    private TableView<Type> tableauType;
    private Type typeSelectionne;

    @FXML
    private Label titre;

    String[][] colonnes = {
            {"Id Type", "idType"},
            {"Nom", "nom"},
            {"Code Couleur", "codeCouleur"}
    };

    @FXML
    protected void onAddTypeClick() throws IOException {
        StartApplication.changeScene("accueil/addtypeview", new AjoutTypeController());
    }

    @FXML
    protected void onSupprimerTypeClick() throws SQLException {
        if (typeSelectionne != null) {
            TypeRepository.supprimerType(typeSelectionne);
            tableauType.getItems().remove(typeSelectionne);
            typeSelectionne = null;
        }
    }

    @FXML
    protected void onBackTypeClick() throws IOException {
        StartApplication.changeScene("accueil/accueilview.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titre.setText("Gestion des types");

        for (String[] colonne : colonnes) {
            TableColumn<Type, String> maColonne = new TableColumn<>(colonne[0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonne[1]));
            tableauType.getColumns().add(maColonne);
        }

        try {
            List<Type> types = TypeRepository.getAllTypes();
            tableauType.getItems().addAll(types);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onTypeSelection(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            TablePosition cell = tableauType.getSelectionModel().getSelectedCells().get(0);
            int row = cell.getRow();
            TableColumn column = cell.getTableColumn();
            typeSelectionne = tableauType.getItems().get(row);
            StartApplication.changeScene("accueil/addtypeview", new EditerTypeController(typeSelectionne));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            TablePosition cell = tableauType.getSelectionModel().getSelectedCells().get(0);
            int row = cell.getRow();
            TableColumn column = cell.getTableColumn();
            typeSelectionne = tableauType.getItems().get(row);
        }
    }
}
