package fr.umontpellier.iut;

import java.time.LocalDate;

public class GestionEmployes {

    public static void main(String[] args) {
        Entreprise e = new Entreprise();
        Employe un = new Employe("1","Jean",20.5);
        Employe deux = new Employe("5","Iris",22.5);
        Employe trois = new Employe("1","Adrien",25.5);
        e.embaucher(un, LocalDate.now());
        System.out.println(e.toString());
        e.embaucher(deux, LocalDate.now());
        System.out.println(e.toString());
        e.embaucher(trois, LocalDate.now());
        System.out.println(e.toString());
        System.out.println(e.getEmployesOrdonnes());
        System.out.println(e.getEmployesDansDesordre());
    }
}
