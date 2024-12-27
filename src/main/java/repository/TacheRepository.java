package repository;

import database.Database;
import model.Liste;
import model.Tache;
import model.UtilisateurConnecte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TacheRepository {
    public static boolean ajouterTache(String nom, boolean etat, int refListe, int refType) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("INSERT INTO tache (nom, etat, ref_liste, ref_type) VALUES (?, ?, ?, ?)");
        req.setString(1, nom);
        req.setBoolean(2, etat);
        req.setInt(3, refListe);
        req.setInt(4, refType);

        if (req.executeUpdate() == 1) {
            return true;
        }

        return false;
    }

    public static boolean supprimerTache(int idTache) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("DELETE FROM tache WHERE id_tache = ?");
        req.setInt(1, idTache);

        if (req.executeUpdate() == 1) {
            return true;
        }

        return false;
    }

    public static boolean modifierTache(int idTache, String nom, boolean etat, int refListe, int refType) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("UPDATE tache SET nom = ?, etat = ?, ref_liste = ?, ref_type = ? WHERE id_tache = ?");
        req.setString(1, nom);
        req.setBoolean(2, etat);
        req.setInt(3, refListe);
        req.setInt(4, refType);
        req.setInt(5, idTache);

        if (req.executeUpdate() == 1) {
            return true;
        }

        return false;
    }

    public static List<Tache> getAllTache() throws SQLException {
        List<Tache> taches = new ArrayList<>();
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("SELECT * FROM tache");
        ResultSet rs = req.executeQuery();

        while (rs.next()) {
            int idListe = rs.getInt("id_tache");
            String nom = rs.getString("nom");
            int etat = rs.getInt("etat");
            int refType = rs.getInt("ref_type");
            int refListe = rs.getInt("ref_liste");

            taches.add(new Tache(idListe, nom, etat, refType, refListe));
        }

        return taches;
    }
}
