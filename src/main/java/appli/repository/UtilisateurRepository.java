package appli.repository;

import appli.database.Database;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRepository {
    public static boolean inscription(String nom, String prenom, String email, String motDePasse) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement statement = cnx.prepareStatement("INSERT INTO user (nom, prenom, mail, mdp) VALUES (?, ?, ?, ?)");
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, email);
        statement.setString(4, motDePasse);

        if (statement.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static User getUtilisateurParEmail(String email) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement statement = cnx.prepareStatement("SELECT * FROM user WHERE mail = ?");
        statement.setString(1, email);

        if (statement.executeQuery().next()) {
            ResultSet result = statement.executeQuery();

            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String mail = result.getString("mail");
            String mdp = result.getString("mdp");

            return new User(nom, prenom, mail, mdp);
        } else {
            return null;
        }
    }
}
