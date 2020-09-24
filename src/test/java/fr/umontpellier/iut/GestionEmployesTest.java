package fr.umontpellier.iut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GestionEmployesTest {

    Entreprise entreprise1 = new Entreprise();
    Employe employe1_nrINSEE1 = new Employe("1", "Rocher", 10);
    Employe employe2 = new Employe("2", "Jorge", 10);
    Employe employe3 = new Employe("3", "Zapata", 10);
    Employe employe4 = new Employe("4", "Abstract", 10);
    Employe employe5 = new Employe("5", "Abstract", 10);
    Employe employe1_nrINSEE5 = new Employe("5", "Rocher", 10);
    Employe employe5bis = new Employe("5", "Abstract", 10);

    @BeforeEach
    void init() {
        entreprise1.embaucher(employe1_nrINSEE1, LocalDate.now());
        entreprise1.embaucher(employe2, LocalDate.now());
        entreprise1.embaucher(employe3, LocalDate.now());
        entreprise1.embaucher(employe4, LocalDate.now());
        entreprise1.embaucher(employe5, LocalDate.now());
    }

    @Test
    void test_ordre(){
        Collection<Employe> resultatVoulu = new TreeSet<>();
        resultatVoulu.add(employe4);
        resultatVoulu.add(employe5);
        resultatVoulu.add(employe2);
        resultatVoulu.add(employe1_nrINSEE1);
        resultatVoulu.add(employe3);
        Collection<Employe> calcul = entreprise1.getEmployesOrdonnes();
        assertEquals(resultatVoulu,calcul);
    }

    @Test
    void test_ordre_doublon_nom(){
        entreprise1.embaucher(employe1_nrINSEE5,LocalDate.now());

        Collection<Employe> resultatVoulu = new TreeSet<>();
        resultatVoulu.add(employe4);
        resultatVoulu.add(employe5);
        resultatVoulu.add(employe2);
        resultatVoulu.add(employe1_nrINSEE1);
        resultatVoulu.add(employe1_nrINSEE5);
        resultatVoulu.add(employe3);
        Collection<Employe> calcul = entreprise1.getEmployesOrdonnes();
        assertEquals(resultatVoulu,calcul);
    }

    @Test
    void test_ordre_doublon_vrai(){
        entreprise1.embaucher(employe1_nrINSEE5,LocalDate.now());
        entreprise1.embaucher(employe5bis,LocalDate.now());

        Collection<Employe> resultatVoulu = new TreeSet<>();
        resultatVoulu.add(employe4);
        resultatVoulu.add(employe5);
        resultatVoulu.add(employe2);
        resultatVoulu.add(employe1_nrINSEE1);
        resultatVoulu.add(employe1_nrINSEE5);
        resultatVoulu.add(employe3);
        Collection<Employe> calcul = entreprise1.getEmployesOrdonnes();
        assertEquals(resultatVoulu,calcul);
    }

    @Test
    void test_desordre(){
        Collection<Employe> resultatVoulu = new HashSet<>();
        resultatVoulu.add(employe4);
        resultatVoulu.add(employe5);
        resultatVoulu.add(employe2);
        resultatVoulu.add(employe1_nrINSEE1);
        resultatVoulu.add(employe3);
        Collection<Employe> calcul = entreprise1.getEmployesDansDesordre();
        assertEquals(resultatVoulu,calcul);
    }

    @Test
    void test_desordre_doublon(){
        entreprise1.embaucher(employe1_nrINSEE5,LocalDate.now());

        Collection<Employe> resultatVoulu = new HashSet<>();
        resultatVoulu.add(employe4);
        resultatVoulu.add(employe5);
        resultatVoulu.add(employe2);
        resultatVoulu.add(employe1_nrINSEE1);
        resultatVoulu.add(employe1_nrINSEE5);
        resultatVoulu.add(employe3);
        Collection<Employe> calcul = entreprise1.getEmployesDansDesordre();
        assertEquals(resultatVoulu,calcul);
    }

    @Test
    void test_desordre_deux_doublon(){
        entreprise1.embaucher(employe1_nrINSEE5,LocalDate.now());
        entreprise1.embaucher(employe5bis,LocalDate.now());

        Collection<Employe> resultatVoulu = new HashSet<>();
        resultatVoulu.add(employe4);
        resultatVoulu.add(employe5);
        resultatVoulu.add(employe2);
        resultatVoulu.add(employe1_nrINSEE1);
        resultatVoulu.add(employe1_nrINSEE5);
        resultatVoulu.add(employe3);
        Collection<Employe> calcul = entreprise1.getEmployesDansDesordre();
        assertEquals(resultatVoulu,calcul);
    }

    @Test
    void test_taille(){
        Collection<Employe> testOrdre = entreprise1.getEmployesOrdonnes();
        Collection<Employe> testDesordre = entreprise1.getEmployesDansDesordre();
        assertTrue(testDesordre.size() == testOrdre.size() && (testDesordre.containsAll(testOrdre) && testOrdre.containsAll(testOrdre)));
    }

    @Test
    void test_anciennete(){
        Employe employe6 = new Employe("9", "Jorge", 10);
        Employe employe7 = new Employe("8", "Abstract", 10);

        entreprise1.embaucher(employe6,LocalDate.of(2010,11,20));
        entreprise1.embaucher(employe7,LocalDate.of(2010,11,10));

        assertEquals(employe6.getMoisAnciennete(),employe7.getMoisAnciennete());
    }

    @Test
    void test_anciennete_mois(){
        Employe employe6 = new Employe("9", "Jorge", 10);
        entreprise1.embaucher(employe6,LocalDate.of(2020,1,15));
        assertEquals(3,employe6.getMoisAnciennete());
    }

    @Test
    void test_distribuerBonus(){
        entreprise1.setBonusTotal(200);
        entreprise1.embaucher(employe1_nrINSEE5,LocalDate.of(2018,Month.APRIL,22));
        entreprise1.distribuerBonus();
        System.out.println(entreprise1.toString());
    }

    @Test
    void test_remerciement(){
        entreprise1.embaucher(employe1_nrINSEE5,LocalDate.of(2018,Month.APRIL,22));
        entreprise1.remercier(4);
        System.out.println(entreprise1.toString());
    }

    @Test
    void test_indemnite(){
        employe1_nrINSEE5.setAdresse("Lunel");
        System.out.println(employe1_nrINSEE5.getIndemniteTransport());
    }

    @Test
    void test_indemnite_v2(){
        employe1_nrINSEE5.setAdresse("Montpellier");
        System.out.println(employe1_nrINSEE5.getIndemniteTransport());
    }

    @Test
    void test_indemnite_v3(){
        employe1_nrINSEE5.setAdresse("Béziers");
        System.out.println(employe1_nrINSEE5.getIndemniteTransport());
    }

    @Test
    void test_indemnite_ville_non_existe(){
        employe1_nrINSEE5.setAdresse("Bézirs");
        System.out.println(employe1_nrINSEE5.getIndemniteTransport());
    }

    @Test
    void test_remerciement_v2(){
        Entreprise entreprise2 = new Entreprise();
        Employe fifi = new Employe("1", "Jorge", 10);
        Employe loulou = new Employe("1", "Jorge", 15);
        Employe toto = new Employe("2", "Seb", 20);
        entreprise2.embaucher(toto,LocalDate.of(1980,Month.JANUARY,1));
        entreprise2.embaucher(fifi,LocalDate.of(2000,Month.JANUARY,1));
        entreprise2.embaucher(loulou,LocalDate.now());
        entreprise2.remercier(1);
        System.out.println(entreprise2.toString());
    }

    @Test
    void test_remerciement_v3(){
        Entreprise entreprise2 = new Entreprise();
        Employe fifi = new Employe("1", "Jorge", 10);
        Employe toto = new Employe("2", "Seb", 20);
        entreprise2.embaucher(toto,LocalDate.of(1980,Month.JANUARY,1));
        entreprise2.embaucher(fifi,LocalDate.of(1980,Month.JANUARY,2));
        entreprise2.remercier(1);
        System.out.println(entreprise2.toString());
    }

    @Test
    void test_remerciement_v4(){
        Entreprise entreprise2 = new Entreprise();
        Employe fifi = new Employe("1", "Jorge", 10);
        Employe riri = new Employe("1", "Jorge", 5);
        Employe loulou = new Employe("1", "Jorge", 15);
        Employe toto = new Employe("2", "Seb", 20);
        entreprise2.embaucher(toto,LocalDate.of(1980,Month.JANUARY,1));
        entreprise2.embaucher(fifi,LocalDate.of(2000,Month.JANUARY,1));
        entreprise2.embaucher(riri,LocalDate.of(2010,Month.JANUARY,1));
        entreprise2.embaucher(loulou,LocalDate.now());
        entreprise2.remercier(2);
        System.out.println(entreprise2.toString());
    }
}