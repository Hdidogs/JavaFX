package model;

public class Type {
    private int idType;
    private String nom;
    private String codeCouleur;

    public Type(int id_type, String nom, String code_couleur) {
        this.idType = id_type;
        this.nom = nom;
        this.codeCouleur = code_couleur;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int id_type) {
        this.idType = id_type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String code_couleur) {
        this.codeCouleur = code_couleur;
    }

    @Override
    public String toString() {
        return nom;
    }
}
