package model;

public class Liste {
    private String nom;
    private int idListe;

    public Liste(String nom, int idListe) {
        this.nom = nom;
        this.idListe = idListe;
    }

    public Liste(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }
}
