package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;

import java.io.IOException;

public class AccueilViewController {
    @FXML
    protected void onDeconnexionClick() throws IOException {
        StartApplication.changeScene("accueil/loginview.fxml");
    }
}
