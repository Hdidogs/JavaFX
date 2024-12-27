package repository;

import database.Database;
import model.Utilisateur;
import model.UtilisateurConnecte;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRepository {
    public static boolean inscription(String nom, String prenom, String email, String motDePasse) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        String mdp = BCrypt.hashpw(motDePasse, BCrypt.gensalt());

        PreparedStatement statement = cnx.prepareStatement("INSERT INTO utilisateur (nom, prenom, email, mot_de_passe) VALUES (?, ?, ?, ?)");
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, email);
        statement.setString(4, mdp);

        if (statement.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Utilisateur connexion(String email, String motDePasse) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement statement = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int id = result.getInt("id_utilisateur");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String mail = result.getString("email");
            String mdp = result.getString("mot_de_passe");

            if (BCrypt.checkpw(motDePasse, mdp)) {
                return new Utilisateur(id, nom, prenom, mail, mdp);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Utilisateur getUtilisateurParEmail(String email) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement statement = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
        statement.setString(1, email);

        if (statement.executeQuery().next()) {
            ResultSet result = statement.executeQuery();

            int id = result.getInt("id_utilisateur");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String mail = result.getString("email");
            String mdp = result.getString("mot_de_passe");

            return new Utilisateur(id, nom, prenom, mail, mdp);
        } else {
            return null;
        }
    }

    public static boolean modifierUtilisateur(String ancienEmail, String nom, String prenom, String email, String motDePasse) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();
        String mdp = BCrypt.hashpw(motDePasse, BCrypt.gensalt());

        String requete = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, mot_de_passe = ? WHERE email = ?";
        PreparedStatement statement = cnx.prepareStatement(requete);
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, email);
        statement.setString(4, mdp);
        statement.setString(5, ancienEmail);

        int id = UtilisateurConnecte.getInstance().getId();
        UtilisateurConnecte.clearInstance();
        UtilisateurConnecte.initInstance(new Utilisateur(id, nom, prenom, email, mdp));

        return statement.executeUpdate() > 0;
    }

    public static boolean modifierUtilisateur(String ancienEmail, String nom, String prenom, String email) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        String requete = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ? WHERE email = ?";
        PreparedStatement statement = cnx.prepareStatement(requete);
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, email);
        statement.setString(4, ancienEmail);

        String mdp = UtilisateurConnecte.getInstance().getMotDePasse();
        int id = UtilisateurConnecte.getInstance().getId();
        UtilisateurConnecte.clearInstance();
        UtilisateurConnecte.initInstance(new Utilisateur(id, nom, prenom, email, mdp));

        return statement.executeUpdate() > 0;
    }

    public static boolean supprimerUtilisateur(String email) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        String requete = "DELETE FROM utilisateur WHERE email = ?";
        PreparedStatement statement = cnx.prepareStatement(requete);
        statement.setString(1, email);

        return statement.executeUpdate() > 0;
    }
}
