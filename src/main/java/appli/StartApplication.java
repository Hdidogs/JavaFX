package appli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    private static Stage main;

    @Override
    public void start(Stage stage) throws IOException {
        main = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("accueil/loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 640);
        main.setTitle("Hello!");
        main.setScene(scene);
        main.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String nomDuFichierFxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(nomDuFichierFxml));
        Scene scene = new Scene(fxmlLoader.load(), 320, 640);
        main.setTitle("Hello!");
        main.setScene(scene);
        main.show();
    }
}