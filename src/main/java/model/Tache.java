package model;

public class Tache {
    private int idTache;
    private String nom;
    private int etat;
    private int refType;
    private int refListe;

    public Tache(int idTache, String nom, int etat, int refType, int refListe) {
        this.idTache = idTache;
        this.nom = nom;
        this.etat = etat;
        this.refType = refType;
        this.refListe = refListe;
    }

    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getRefType() {
        return refType;
    }

    public void setRefType(int refType) {
        this.refType = refType;
    }

    public int getRefListe() {
        return refListe;
    }

    public void setRefListe(int refListe) {
        this.refListe = refListe;
    }
}
