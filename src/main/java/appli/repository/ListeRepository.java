package appli.repository;

import appli.database.Database;
import model.Liste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListeRepository {
    public static Liste ajouterListe(String nom) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("INSERT INTO liste (nom) VALUES (?)");
        req.setString(1, nom);

        if (req.executeUpdate() == 1) {
            return new Liste(nom);
        } else {
            return null;
        }
    }
}
