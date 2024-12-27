package repository;

import database.Database;
import model.Liste;
import model.UtilisateurConnecte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListeRepository {
    public static Liste ajouterListe(String nom) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("INSERT INTO liste (nom) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
        req.setString(1, nom);

        if (req.executeUpdate() == 1) {
            ResultSet generatedKeys = req.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id_liste = generatedKeys.getInt(1);

                PreparedStatement reqRelation = cnx.prepareStatement("INSERT INTO utilisateur_liste (ref_liste, ref_utilisateur) VALUES (?, ?)");
                reqRelation.setInt(1, id_liste);
                reqRelation.setInt(2, UtilisateurConnecte.getInstance().getId());
                reqRelation.executeUpdate();

                return new Liste(nom, id_liste);
            }
        }
        return null;
    }


    public static List<Liste> getAllListes() throws SQLException {
        List<Liste> listes = new ArrayList<>();
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("SELECT id_liste, nom FROM liste INNER JOIN utilisateur_liste ON liste.id_liste = utilisateur_liste.ref_liste WHERE ref_utilisateur = ?");
        req.setInt(1, UtilisateurConnecte.getInstance().getId());
        ResultSet rs = req.executeQuery();

        while (rs.next()) {
            int idListe = rs.getInt("id_liste");
            String nom = rs.getString("nom");
            listes.add(new Liste(nom, idListe));
        }

        return listes;
    }

    public static void supprimerListe(int idListe) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("DELETE FROM liste WHERE id_liste = ?");
        req.setInt(1, idListe);
        req.executeUpdate();
    }

    public static Liste modifierListe(Liste liste) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("UPDATE liste SET nom = ? WHERE id_liste = ?");
        req.setString(1, liste.getNom());
        req.setInt(2, liste.getIdListe());

        if (req.executeUpdate() == 1) {
            return liste;
        } else {
            return null;
        }
    }
}

