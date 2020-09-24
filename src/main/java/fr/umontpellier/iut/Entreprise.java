package fr.umontpellier.iut;
import java.time.LocalDate;
import java.util.*;

public class Entreprise {
    private double bonusTotal;
    private Collection<Employe> lePersonnel;

    public Entreprise() { lePersonnel = new ArrayList<>(); }

    public Collection<Employe> getEmployesOrdonnes() { return new TreeSet<>(lePersonnel); }

    public Collection<Employe> getEmployesDansDesordre() { return new HashSet<>(lePersonnel); }

    public void setBonusTotal(double bonusTotal) { this.bonusTotal = bonusTotal; }

    public void distribuerBonus() {
        PriorityQueue<Employe> tousLesEmployes = new PriorityQueue<>(Comparator.comparing(Employe::getDateEmbauche));
        tousLesEmployes.addAll(lePersonnel);
        double restBonus = bonusTotal;
        while (!tousLesEmployes.isEmpty() && restBonus > 0){ Employe e = tousLesEmployes.poll();
            if((restBonus - (3 * e.getMoisAnciennete())) <= 0) {
                e.setBonus(restBonus); }
            else {
                e.setBonus(3*e.getMoisAnciennete()); }
            restBonus -= 3*e.getMoisAnciennete();
        }
    }

    public void remercier(int n){
        PriorityQueue<Employe> tousLesEmployes = new PriorityQueue<>(((emploie, t1) -> t1.getDateEmbauche().compareTo(emploie.getDateEmbauche())));
        tousLesEmployes.addAll(lePersonnel);
        int i = 0;
        while(!tousLesEmployes.isEmpty() && n>i){
            i++;
            Employe tmp = tousLesEmployes.poll();
            lePersonnel.removeIf(t -> t == tmp);
        }
    }

    void embaucher(Employe e, LocalDate dateEmbauche){
        e.setDateEmbauche(dateEmbauche);
        lePersonnel.add(e);
    }

    void licencier(Employe e){ lePersonnel.remove(e); }

    @Override
    public String toString() {
        return "bonusTotal=" + bonusTotal +
                "\nlePersonnel=" + lePersonnel + "\n";
    }}
