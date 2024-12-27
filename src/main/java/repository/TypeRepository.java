package repository;

import database.Database;
import javafx.scene.paint.Color;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRepository {
    public static boolean ajoutType(String nom, String couleur) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("INSERT INTO type (nom, code_couleur) VALUES (?, ?)");
        req.setString(1, nom);
        req.setString(2, couleur);

        if (req.executeUpdate() == 1) {
            return true;
        }
        return false;
    }

    public static boolean supprimerType(Type type) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("DELETE FROM type WHERE id_type = ?");
        req.setInt(1, type.getIdType());

        if (req.executeUpdate() == 1) {
            return true;
        }
        return false;
    }

    public static boolean modifierType(Type type, String nom, String couleur) throws SQLException {
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("UPDATE type SET nom = ?, code_couleur = ? WHERE id_type = ?");
        req.setString(1, nom);
        req.setString(2, couleur);
        req.setInt(3, type.getIdType());

        if (req.executeUpdate() == 1) {
            return true;
        }
        return false;
    }

    public static List<Type> getAllTypes() throws SQLException {
        List<Type> types = new ArrayList<>();
        Database db = new Database();
        Connection cnx = db.getConnection();

        PreparedStatement req = cnx.prepareStatement("SELECT id_type, nom, code_couleur FROM type");
        ResultSet rs = req.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_type");
            String nom = rs.getString("nom");
            String codeCouleur = rs.getString("code_couleur");
            types.add(new Type(id, nom, codeCouleur));
        }

        return types;
    }
}
