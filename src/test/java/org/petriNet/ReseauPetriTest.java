package org.petriNet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReseauPetriTest {

    private ReseauPetri reseauPetri;
    private List<Place> places;
    private List<Transition> transitions;
    private LinkedList<Arc> arcs;

    @BeforeEach
    public void setUp() {
        reseauPetri = new ReseauPetri();
    }

    @Test
    public void testAjouterJeton() {
        Place place = new Place(2, reseauPetri.generateId(1));
        place.ajouter_jeton(-2);
        place.ajouter_jeton(2);
        assertEquals(4, place.get_nombre_jetons(), "CAJ0, CAJ1");
    }

    @Test
    public void testEnleverJeton() {
        Place place = new Place(2, reseauPetri.generateId(1));
        place.enlever_jeton(-2);
        place.enlever_jeton(2);
        assertEquals(0, place.get_nombre_jetons(), "CEJ0, CEJ1");
    }

    @Test
    public void testAjouterPlace() {
        Place place = new Place(2,  reseauPetri.generateId(1));
        reseauPetri.ajouterPlace(place);

        // A test for an existing place
        reseauPetri.ajouterPlace(place);
        assertEquals(1, reseauPetri.getPlaces().size(), "CAP0, CAP1");
    }

    @Test
    public void testAjouterTransition() {
        Transition transition = new Transition("transition", reseauPetri.generateId(2));
        reseauPetri.ajouterTransition(transition);
        // A test for an existing transition
        reseauPetri.ajouterTransition(transition);
        assertEquals(1, reseauPetri.getTransitions().size(), "CAT0, CAT1");
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
        assertEquals(1, reseauPetri.getArcs().size(), "CAA0, CAA1");
    }

    @Test
    @DisplayName("Assembler un réseau de petri")
    public void testAssemblerPetri(){

        // CR1
        ReseauPetri Mutex = new ReseauPetri();
        Arc arc;

        // CP1
        Place P1 = new Place(0, Mutex.generateId(1));
        Mutex.ajouterPlace(P1);
        Mutex.ajouterPlace(P1);
        assertEquals(1, Mutex.getPlaces().size(), "CP1") ;

        // CT1
        Transition T1 = new Transition("T1", Mutex.generateId(2));
        Mutex.ajouterTransition(T1);
        Mutex.ajouterTransition(T1);
        assertEquals(1, Mutex.getTransitions().size(), "CT1");

        // CP2
        Place P2 = new Place(1, Mutex.generateId(1));
        Mutex.ajouterPlace(P2);
        assertEquals(2, Mutex.getPlaces().size(), "CP2");

        // CT2
        Transition T2 = new Transition("T2", Mutex.generateId(2));
        Mutex.ajouterTransition(T2);
        assertEquals(2, Mutex.getTransitions().size(), "CT2");

        // CP5
        Place P3 = new Place(0, Mutex.generateId(1));
        Mutex.ajouterPlace(P3);
        assertEquals(3, Mutex.getPlaces().size(), "CP5");

        // CT3
        Transition T3 = new Transition("T3", Mutex.generateId(2));
        Mutex.ajouterTransition(T3);
        assertEquals(3, Mutex.getTransitions().size(), "CT3");

        // CP4
        Place P4 = new Place(0, Mutex.generateId(1));
        Mutex.ajouterPlace(P4);
        assertEquals(4, Mutex.getPlaces().size(), "CP4");

        // CT4
        Transition T4 = new Transition("T4", Mutex.generateId(2));
        Mutex.ajouterTransition(T4);
        assertEquals(4, Mutex.getTransitions().size(), "CT4");

        // CP5
        Place P5 = new Place(1, Mutex.generateId(1));
        Mutex.ajouterPlace(P5);
        assertEquals(5, Mutex.getPlaces().size(), "CP5");

        // CPT1
        // Creer l'arc entrant simple à T1 de P1
        arc = new Arc_entrant_simple(T1, P1, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T1.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        T1.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        assertEquals(1, T1.getArcs_ENTRANTS().size(), "CPT1");

        // CTP2
        // Creer l'arc sortant de T2 vers P1
        arc = new Arc_SORTANT(T2, P1, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T2.ajouterArc_SORTANT((Arc_SORTANT) arc);
        assertEquals(1, T2.getArcs_SORTANTS().size(), "CTP2");

        // CTP1
        // Creer l'arc sortant de T1 vers P2
        arc = new Arc_SORTANT(T1, P2, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T1.ajouterArc_SORTANT((Arc_SORTANT) arc);
        assertEquals(1, T1.getArcs_SORTANTS().size(), "CTP1");

        // CPT2
        // Creer l'arc entrant simple à T2 de P2
        arc = new Arc_entrant_simple(T2, P2, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T2.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        assertEquals(1, T2.getArcs_ENTRANTS().size(), "CPT2");

        // CPT5
        // Creer l'arc entrant simple à T1 de P3
        arc = new Arc_entrant_simple(T1, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T1.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        assertEquals(2, T1.getArcs_ENTRANTS().size(), "CPT5");

        // CTP5
        // Creer l'arc sortant de T2 vers P3
        arc = new Arc_SORTANT(T2, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T2.ajouterArc_SORTANT((Arc_SORTANT) arc);
        assertEquals(2, T2.getArcs_SORTANTS().size(), "CTP5");

        // CPT6
        // Creer l'arc entrant simple à T3 de P3
        arc = new Arc_entrant_simple(T3, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T3.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        assertEquals(1, T3.getArcs_ENTRANTS().size(), "CPT6");

        // CTP6
        // Creer l'arc sortant de T4 vers P3
        arc = new Arc_SORTANT(T4, P3, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T4.ajouterArc_SORTANT((Arc_SORTANT) arc);
        assertEquals(1, T4.getArcs_SORTANTS().size(), "CTP6");

        // CTP3
        // Creer l'arc sortant de T3 à P4
        arc = new Arc_SORTANT(T3, P4, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T3.ajouterArc_SORTANT((Arc_SORTANT) arc);
        assertEquals(1, T3.getArcs_SORTANTS().size(), "CTP3");

        // CPT4
        // Creer l'arc entrant à T4 de P4
        arc = new Arc_entrant_simple(T4, P4, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T4.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        assertEquals(1, T4.getArcs_ENTRANTS().size(), "CPT4");

        // CPT3
        // Creer l'arc entrant à T3 de P5
        arc = new Arc_entrant_simple(T3, P5, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T3.ajouterArc_ENTRANT((Arc_ENTRANT) arc);
        assertEquals(2, T3.getArcs_ENTRANTS().size(), "CPT3");

        // CTP4
        // Creer l'arc sortant de T4 vers P5
        arc = new Arc_SORTANT(T4, P5, 1, Mutex.generateId(0));
        Mutex.ajouterArc(arc);
        T4.ajouterArc_SORTANT((Arc_SORTANT) arc);
        assertEquals(2, T4.getArcs_SORTANTS().size(), "CTP4");

        assertEquals(12, Mutex.getArcs().size(), "There should be 12 unique arcs in Mutex");
        assertEquals(5, Mutex.getPlaces().size(), "There should be 5 unique places in Mutex");
        assertEquals(4, Mutex.getTransitions().size(), "There should be 4 unique transitions in Mutex");

        Mutex.afficherReseau();

    }

    @Test
    @DisplayName("RI")
    public void testActiverPetri_1() {
        ReseauPetri Active = new ReseauPetri();

        // Active has only one Transition
        Transition T1 = new Transition("T1", Active.generateId(2));
        Active.ajouterTransition(T1);

        // Tirer T1
        Active.tirer_transition(String.valueOf(T1.getId()));
        Active.afficherReseau();

        assertEquals(1, Active.getTransitions().size(), "RI");
    }

    @Test
    @DisplayName("RD0")
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

        assertEquals(0, P1.get_nombre_jetons(), "RD0");
    }

    @Test
    @DisplayName("RD1")
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

        assertEquals(1, P1.get_nombre_jetons(), "RD1");
    }

    @Test
    @DisplayName("RD2")
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

        assertEquals(2, P1.get_nombre_jetons(), "RD2");
    }

    @Test
    @DisplayName("RG0")
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

        assertEquals(2, P1.get_nombre_jetons(), "RG0");

    }

    @Test
    @DisplayName("RM0")
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

        assertEquals(0, P1.get_nombre_jetons(), "RM0");
        assertEquals(2, P2.get_nombre_jetons(), "RM0");
    }

    @Test
    @DisplayName("RM1")
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

        assertEquals(2, P1.get_nombre_jetons(), "RM1");
        assertEquals(1, P2.get_nombre_jetons(), "RM1");

    }

    @Test
    @DisplayName("RGM")
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

        assertEquals(1, P1.get_nombre_jetons(), "RGM");
        assertEquals(0, P2.get_nombre_jetons(), "RGM");
    }

    @Test
    @DisplayName("RDM")
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

        assertEquals(1, P1.get_nombre_jetons(), "RDM");
        assertEquals(2, P2.get_nombre_jetons(), "RDM");

    }

    @Test
    @DisplayName("RDGM")
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

        assertEquals(0, P1.get_nombre_jetons(), "RDGM");
        assertEquals(0, P2.get_nombre_jetons(), "RDGM");
        assertEquals(1, P3.get_nombre_jetons(), "RDGM");
        assertEquals(1, P4.get_nombre_jetons(), "RDGM");

    }

    // Tests de supression
    @Test
    @DisplayName("ST0")
    public void testSupprimerTransition() {
        ReseauPetri Supp = new ReseauPetri();

        Transition T1 = new Transition("T1", Supp.generateId(2));
        Supp.ajouterTransition(T1);
        Supp.supprimerTransition(T1);
        Supp.afficherReseau();

        assertEquals(0, Supp.getTransitions().size(), "ST0");
    }

    @Test
    @DisplayName("SP1")
    public void testDestruction_Place(){
        ReseauPetri Destruction = new ReseauPetri();

        // créer un réseau de petri similaire à Active dans testActiverPetri_3
        Place P1 = new Place(2, Destruction.generateId(1));
        Destruction.ajouterPlace(P1);

        Transition T1 = new Transition("T1", Destruction.generateId(2));

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Destruction.generateId(0));
        Destruction.ajouterArc(arc);

        T1.ajouterArc_ENTRANT(arc);
        Destruction.ajouterTransition(T1);

        // Supprimer la place P1
        Destruction.supprimerPlace(P1);
        Destruction.afficherReseau();

        assertEquals(0, Destruction.getPlaces().size(), "SP1");
        assertEquals(1, Destruction.getTransitions().size(), "SP1");
        assertEquals(0, Destruction.getArcs().size(), "SP1");

    }

    @Test
    @DisplayName("ST1")
    public void testDestruction_Transition() {
        ReseauPetri Destruction = new ReseauPetri();

        // créer un réseau de petri similaire à Active dans testActiverPetri_3
        Place P1 = new Place(2, Destruction.generateId(1));
        Destruction.ajouterPlace(P1);

        Transition T1 = new Transition("T1", Destruction.generateId(2));

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Destruction.generateId(0));
        Destruction.ajouterArc(arc);

        T1.ajouterArc_ENTRANT(arc);
        Destruction.ajouterTransition(T1);

        // Supprimer la transition T1
        Destruction.supprimerTransition(T1);
        Destruction.afficherReseau();

        assertEquals(1, Destruction.getPlaces().size(), "ST1");
        assertEquals(0, Destruction.getTransitions().size(), "ST1");
        assertEquals(0, Destruction.getArcs().size(), "ST1");
    }

    @Test
    @DisplayName("SA1")
    public void testDestruction_Arc() {
        ReseauPetri Destruction = new ReseauPetri();

        // créer un réseau de petri similaire à Active dans testActiverPetri_3
        Place P1 = new Place(2, Destruction.generateId(1));
        Destruction.ajouterPlace(P1);

        Transition T1 = new Transition("T1", Destruction.generateId(2));

        // Creer l'arc entrant simple à T1 de P1
        Arc_ENTRANT arc = new Arc_entrant_simple(T1, P1, 1, Destruction.generateId(0));
        Destruction.ajouterArc(arc);

        T1.ajouterArc_ENTRANT(arc);
        Destruction.ajouterTransition(T1);

        // Supprimer l'arc
        Destruction.supprimerArc(arc);
        Destruction.afficherReseau();

        assertEquals(1, Destruction.getPlaces().size(), "SA1");
        assertEquals(1, Destruction.getTransitions().size(), "SA1");
        assertEquals(0, Destruction.getArcs().size(), "SA1");
    }

    @Test
    @DisplayName("ARC ZERO")
    public void testArczero() {
        // Initialize the Petri network
        ReseauPetri petriNetwork = new ReseauPetri();

        // Create places
        Place place1 = new Place(3, petriNetwork.generateId(1));
        Place place2 = new Place(0, petriNetwork.generateId(1));

        // Add places to the network
        petriNetwork.ajouterPlace(place1);
        petriNetwork.ajouterPlace(place2);

        // Create a transition
        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));

        // Add the transition to the network
        petriNetwork.ajouterTransition(transition1);

        // Create arcs and add them to the network
        // Incoming arc from place2 to transition1 with weight 1
        Arc_zero incomingArc_zero = new Arc_zero(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.ajouterArc(incomingArc_zero);
        transition1.ajouterArc_ENTRANT(incomingArc_zero);

        // Outgoing arc from transition1 to place1 with weight 1
        Arc_SORTANT outgoingArc = new Arc_SORTANT(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.ajouterArc(outgoingArc);
        transition1.ajouterArc_SORTANT(outgoingArc);

        // Display the initial state of the Petri network
        System.out.println("Initial State of Petri Network:");
        petriNetwork.afficherReseau();

        // Activate the transition
        System.out.println("\nActivating transition T1...");
        petriNetwork.tirer_transition(String.valueOf(transition1.getId()));

        // Display the state of the Petri network after activation
        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.afficherReseau();

        // Assertions to verify the correct state of the Petri network
        assertEquals(4, place1.get_nombre_jetons(), "Tokens in Place 1 should be 4");
        assertEquals(0, place2.get_nombre_jetons(), "Tokens in Place 2 should be 0");
    }
    @Test
    @DisplayName("ARC Videur")
    public void testArcvideur() {
        // Initialize the Petri network
        ReseauPetri petriNetwork = new ReseauPetri();

        // Create places
        Place place1 = new Place(2, petriNetwork.generateId(1));
        Place place2 = new Place(4, petriNetwork.generateId(1));

        // Add places to the network
        petriNetwork.ajouterPlace(place1);
        petriNetwork.ajouterPlace(place2);

        // Create a transition
        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));

        // Add the transition to the network
        petriNetwork.ajouterTransition(transition1);

        // Create arcs and add them to the network
        // Incoming arc from place2 to transition1 with weight 1
        Arc_videur incomingArc_videur = new Arc_videur(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.ajouterArc(incomingArc_videur);
        transition1.ajouterArc_ENTRANT(incomingArc_videur);

        // Outgoing arc from transition1 to place1 with weight 1
        Arc_SORTANT outgoingArc = new Arc_SORTANT(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.ajouterArc(outgoingArc);
        transition1.ajouterArc_SORTANT(outgoingArc);

        // Display the initial state of the Petri network
        System.out.println("Initial State of Petri Network:");
        petriNetwork.afficherReseau();

        // Activate the transition
        System.out.println("\nActivating transition T1...");
        petriNetwork.tirer_transition(String.valueOf(transition1.getId()));

        // Display the state of the Petri network after activation
        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.afficherReseau();

        // Assertions to verify the correct state of the Petri network
        assertEquals(3, place1.get_nombre_jetons(), "Tokens in Place 1 should be 3");
        assertEquals(0, place2.get_nombre_jetons(), "Tokens in Place 2 should be 0");
    }

}


