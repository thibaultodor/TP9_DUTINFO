package fr.umontpellier.iut;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Employe implements Comparable<Employe>{
    private String nrINSEE;
    private String nom;
    private double base;
    private LocalDate dateEmbauche;

    private double bonus; // pour exo3

    private String adresse; // pour exo4

    public Employe(String nrINSEE, String nom, double base) {
        this.nrINSEE = nrINSEE;
        this.nom = nom;
        this.base = base;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    @Override
    public String toString() {
        return "\nnrINSEE='" + nrINSEE + '\'' +
                ", nom='" + nom + '\'' +
                ", base=" + base +
                ", dateEmbauche=" + dateEmbauche +
                ", BONUS=" + bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getIndemniteTransport() {
        return GestionDistances.getDistance(getAdresse()) * base;
    }

    public int getMoisAnciennete() {
        return (int) ChronoUnit.MONTHS.between(dateEmbauche, LocalDate.now());
    }

    public int compareTo(Employe employe) {
        if (this.equals(employe)){return 0;}
        if(this.nom.compareTo(employe.nom) != 0){ return this.nom.compareTo(employe.nom); }
        else{ return this.nrINSEE.compareTo(employe.nrINSEE); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(nrINSEE, employe.nrINSEE) &&
                Objects.equals(nom, employe.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrINSEE, nom);
    }
}
