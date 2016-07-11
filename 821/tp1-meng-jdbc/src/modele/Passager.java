/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author jason
 */
public class Passager {
    private long codePassager;
    private String nom;
    private String prenom ;
    private String adresse;
    private String telephone ;
    private String ville;
    private String pays;
    private String statut;

    public long getCodePassager() {
        return codePassager;
    }

    public void setCodePassager(long codePassager) {
        this.codePassager = codePassager;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
    
    public void saisirPassager(String[] p){
        if(p.length <7){
            throw new IllegalArgumentException();
        }
        setNom(p[0]);
        setPrenom(p[1]);
        setAdresse(p[2]);
        setTelephone(p[3]);
        setVille(p[4]);
        setPays(p[5]);
        setStatut(p[6]);
    }

    @Override
    public String toString() {
        return "Passager{" + "codePassager=" + codePassager + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", ville=" + ville + ", pays=" + pays + ", statut=" + statut + '}';
    }
    
}
