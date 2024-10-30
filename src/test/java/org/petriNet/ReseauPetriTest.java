package org.petriNet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReseauPetriTest {

    /**
     * Les fonctions à tester suivantes n’aﬃchent rien. Elles créent des objets et les retournent à la
     * fonction de test. C’est la fonction de test qui aﬃche afin que les testeurs puissent observer les
     * résultats
     */

    private ReseauPetri reseauPetri;
    private List<Place> places;
    private List<Transition> transitions;
    private LinkedList<Arc> arcs;

    /**
     * Créer un reseauPetri vide sans entrée. Résultat attendu : un RdP est créé vide
     * Créer une transition. Résultat attendu : une transition est créée (sans lien)
     * Créer une place avec nombres de jetons. Les cas : jetons ≥0, jetons < 0.
     * Résultat attendu : une place est créée (sans lien), aucune place n’est créée ; Erreur
     */

    @BeforeEach
    public void setUp() {
        reseauPetri = new ReseauPetri();
    }

    @Test
    public void testAjouterJeton() {
        Place place = new Place(2, reseauPetri.generateId(1));
        place.ajouter_jeton(-2);
        place.ajouter_jeton(2);
        assertEquals(4, place.get_nombre_jetons());
    }

    @Test
    public void testEnleverJeton() {
        Place place = new Place(2, reseauPetri.generateId(1));
        place.enlever_jeton(-2);
        place.enlever_jeton(2);
        assertEquals(0, place.get_nombre_jetons());
    }

    @Test
    public void testAjouterPlace() {
        Place place = new Place(2,  reseauPetri.generateId(1));
        reseauPetri.ajouterPlace(place);

        // A test for an existing place
        reseauPetri.ajouterPlace(place);
        assertEquals(1, reseauPetri.getPlaces().size());
    }

    @Test
    public void testAjouterTransition() {
        Transition transition = new Transition("transition", reseauPetri.generateId(2));
        reseauPetri.ajouterTransition(transition);
        // A test for an existing transition
        reseauPetri.ajouterTransition(transition);
        assertEquals(1, reseauPetri.getTransitions().size());
    }

    @Test
    public void testAjouterArc() {
        Place place = new Place(2, reseauPetri.generateId(1));
        Transition transition = new Transition("transition", reseauPetri.generateId(2));
        Arc_SORTANT arc_0 = new Arc_SORTANT(transition, place, 1, reseauPetri.generateId(0));

        // A test for a negative weight
        Arc_SORTANT arc_1 = new Arc_SORTANT(transition, place, -1, reseauPetri.generateId(0));
        reseauPetri.ajouterArc(arc_0);

        // A test for an existing arc
        reseauPetri.ajouterArc(arc_0);
        assertEquals(1, reseauPetri.getArcs().size());
    }

    @Test
    public void testAssemblerPetri(){

        ReseauPetri Mutex = new ReseauPetri();
        Arc arc;

        Place P1 = new Place(0, Mutex.generateId(1));
        Mutex.ajouterPlace(P1);
        Mutex.ajouterPlace(P1);

        Transition T1 = new Transition("T1", Mutex.generateId(2));
        Mutex.ajouterTransition(T1);
        Mutex.ajouterTransition(T1);

        Place P2 = new Place(1, Mutex.generateId(1));
        Mutex.ajouterPlace(P2);

        Transition T2 = new Transition("T2", Mutex.generateId(2));
        Mutex.ajouterTransition(T2);

        Place P3 = new Place(0, Mutex.generateId(1));
        Mutex.ajouterPlace(P3);

        Transition T3 = new Transition("T3", Mutex.generateId(2));
        Mutex.ajouterTransition(T3);

        Place P4 = new Place(0, Mutex.generateId(1));
        Mutex.ajouterPlace(P4);

        Transition T4 = new Transition("T4", Mutex.generateId(2));
        Mutex.ajouterTransition(T4);

        Place P5 = new Place(1, Mutex.generateId(1));
        Mutex.ajouterPlace(P5);

        // Creer l'arc entrant simple à T1 de P1
        arc = new Arc_entrant_simple(T1, P1, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T1.ajouterArc_ENTRANT((Arc_ENTRANT) arc);

        // Creer l'arc sortant de T2 vers P1
        arc = new Arc_SORTANT(T2, P1, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T2.ajouterArc_SORTANT((Arc_SORTANT) arc);

        // Creer l'arc sortant de T1 vers P2
        arc = new Arc_SORTANT(T1, P2, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T1.ajouterArc_SORTANT((Arc_SORTANT) arc);

        // Creer l'arc entrant simple à T2 de P2
        arc = new Arc_entrant_simple(T2, P2, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T2.ajouterArc_ENTRANT((Arc_ENTRANT) arc);

        // Creer l'arc entrant simple à T1 de P3
        arc = new Arc_entrant_simple(T1, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T1.ajouterArc_ENTRANT((Arc_ENTRANT) arc);

        // Creer l'arc sortant de T2 vers P3
        arc = new Arc_SORTANT(T2, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T2.ajouterArc_SORTANT((Arc_SORTANT) arc);

        // Creer l'arc entrant simple à T3 de P3
        arc = new Arc_entrant_simple(T3, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T3.ajouterArc_ENTRANT((Arc_ENTRANT) arc);

        // Creer l'arc sortant de T4 vers P3
        arc = new Arc_SORTANT(T4, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T4.ajouterArc_SORTANT((Arc_SORTANT) arc);

        // Creer l'arc sortant de T3 à P4
        arc = new Arc_SORTANT(T3, P4, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T3.ajouterArc_SORTANT((Arc_SORTANT) arc);

        // Creer l'arc entrant à T4 de P4
        arc = new Arc_entrant_simple(T4, P4, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T4.ajouterArc_ENTRANT((Arc_ENTRANT) arc);

        // Creer l'arc entrant à T3 de P5
        arc = new Arc_entrant_simple(T3, P5, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T3.ajouterArc_ENTRANT((Arc_ENTRANT) arc);

        // Creer l'arc sortant de T4 vers P5
        arc = new Arc_SORTANT(T4, P5, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T4.ajouterArc_SORTANT((Arc_SORTANT) arc);

        Mutex.afficherReseau();

    }

    @Test
    public void testActiverPetri_1() {
        ReseauPetri Active = new ReseauPetri();

        // Active has only one Transition
        Transition T1 = new Transition("T1", Active.generateId(2));
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_2() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has only one Place with 0 jetons
        Place P1 = new Place(0, Active.generateId(1));
        Active.ajouterPlace(P1);

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);

        T1.ajouterArc_ENTRANT(arc);
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_3() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has only one Place with 2 jeton
        Place P1 = new Place(2, Active.generateId(1));
        Active.ajouterPlace(P1);

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);

        T1.ajouterArc_ENTRANT(arc);
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_4() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has only one Place with 5 jetons
        Place P1 = new Place(5, Active.generateId(1));
        Active.ajouterPlace(P1);

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 3, Active.generateId(0));
        Active.ajouterArc(arc);

        T1.ajouterArc_ENTRANT(arc);
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_5() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has one Place with 1 jeton
        Place P1 = new Place(1, Active.generateId(1));
        Active.ajouterPlace(P1);

        // Creer l'arc sortant de T1 vers P1
        Arc_SORTANT arc = new Arc_SORTANT(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);

        T1.ajouterArc_SORTANT(arc);
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();

    }

    @Test
    public void testActiverPetri_6() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has two Places with 0 and 2 jetons
        Place P1 = new Place(0, Active.generateId(1));
        Place P2 = new Place(2, Active.generateId(1));
        Active.ajouterPlace(P1);
        Active.ajouterPlace(P2);

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);
        Arc_SORTANT arc_1 = new Arc_SORTANT(T1, P2, 1, Active.generateId(0));
        Active.ajouterArc(arc_1);

        T1.ajouterArc_ENTRANT(arc);
        T1.ajouterArc_SORTANT(arc_1);
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_7() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has two Places with 5 and 0 jetons
        Place P1 = new Place(5, Active.generateId(1));
        Place P2 = new Place(0, Active.generateId(1));
        Active.ajouterPlace(P1);
        Active.ajouterPlace(P2);

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 3, Active.generateId(0));
        Active.ajouterArc(arc);
        Arc_SORTANT arc_1 = new Arc_SORTANT(T1, P2, 1, Active.generateId(0));
        Active.ajouterArc(arc_1);

        T1.ajouterArc_ENTRANT(arc);
        T1.ajouterArc_SORTANT(arc_1);
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_8() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has two Places with 2 and 1 jetons
        Place P1 = new Place(2, Active.generateId(1));
        Place P2 = new Place(1, Active.generateId(1));
        Active.ajouterPlace(P1);
        Active.ajouterPlace(P2);

        // Creer l'arc entrant simple à T1 de P1 et de P2
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);
        Arc_ENTRANT arc_1 = new Arc_entrant_simple(T1, P2, 1, Active.generateId(0));
        Active.ajouterArc(arc_1);

        T1.ajouterArc_ENTRANT(arc);
        T1.ajouterArc_ENTRANT(arc_1);

        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();
    }

    @Test
    public void testActiverPetri_9() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has two Places with 0 and 1 jetons
        Place P1 = new Place(0, Active.generateId(1));
        Place P2 = new Place(1, Active.generateId(1));
        Active.ajouterPlace(P1);
        Active.ajouterPlace(P2);

        // Creer l'arc sortant de T1 vers P1 et de P2
        Arc_SORTANT arc = new Arc_SORTANT(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);
        Arc_SORTANT arc_1 = new Arc_SORTANT(T1, P2, 1, Active.generateId(0));
        Active.ajouterArc(arc_1);

        T1.ajouterArc_SORTANT(arc);
        T1.ajouterArc_SORTANT(arc_1);

        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();

    }

    @Test
    public void testActiverPetri_10() {
        ReseauPetri Active = new ReseauPetri();

        Transition T1 = new Transition("T1", Active.generateId(2));

        // Active has 4 Places with 1 and 1 jetons and 0 and 0 jetons
        Place P1 = new Place(1, Active.generateId(1));
        Place P2 = new Place(1, Active.generateId(1));
        Place P3 = new Place(0, Active.generateId(1));
        Place P4 = new Place(0, Active.generateId(1));
        Active.ajouterPlace(P1);
        Active.ajouterPlace(P2);
        Active.ajouterPlace(P3);
        Active.ajouterPlace(P4);

        // Creer l'arc entrant simple à T1 de P1 et de P2
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Active.generateId(0));
        Active.ajouterArc(arc);
        Arc_ENTRANT arc_1 = new Arc_entrant_simple(T1, P2, 1, Active.generateId(0));
        Active.ajouterArc(arc_1);

        // Creer l'arc sortant de T1 vers P3 et de P4
        Arc_SORTANT arc_2 = new Arc_SORTANT(T1, P3, 1, Active.generateId(0));
        Active.ajouterArc(arc_2);
        Arc_SORTANT arc_3 = new Arc_SORTANT(T1, P4, 1, Active.generateId(0));
        Active.ajouterArc(arc_3);

        T1.ajouterArc_ENTRANT(arc);
        T1.ajouterArc_ENTRANT(arc_1);
        T1.ajouterArc_SORTANT(arc_2);
        T1.ajouterArc_SORTANT(arc_3);

        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();

    }

}
