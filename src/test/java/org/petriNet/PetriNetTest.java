package org.petriNet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PetriNetTest {

    private PetriNet reseauPetri;

    @BeforeEach
    public void setUp() {
        reseauPetri = new PetriNet();
    }

    @Test
    public void testAddToken() {
        Place place = new Place(2, reseauPetri.generateId(1));
        place.addTokens(-2);
        place.addTokens(2);
        assertEquals(4, place.getTokenCount(), "CAJ0, CAJ1");
    }

    @Test
    public void testRemoveToken() {
        Place place = new Place(2, reseauPetri.generateId(1));
        place.removeTokens(-2);
        place.removeTokens(2);
        assertEquals(0, place.getTokenCount(), "CEJ0, CEJ1");
    }

    @Test
    public void testAddPlace() {
        Place place = new Place(2,  reseauPetri.generateId(1));
        reseauPetri.addPlace(place);

        // A test for an existing place
        reseauPetri.addPlace(place);
        assertEquals(1, reseauPetri.getPlaces().size(), "CAP0, CAP1");
    }

    @Test
    public void testAddTransition() {
        Transition transition = new Transition("transition", reseauPetri.generateId(2));
        reseauPetri.addTransition(transition);
        // A test for an existing transition
        reseauPetri.addTransition(transition);
        assertEquals(1, reseauPetri.getTransitions().size(), "CAT0, CAT1");
    }

    @Test
    public void testAddArc() {
        Place place = new Place(2, reseauPetri.generateId(1));
        Transition transition = new Transition("transition", reseauPetri.generateId(2));
        OutgoingArc arc_0 = new OutgoingArc(transition, place, 1, reseauPetri.generateId(0));

        // A test for a negative weight
        OutgoingArc arc_1 = new OutgoingArc(transition, place, -1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc_0);

        // A test for an existing arc
        reseauPetri.addArc(arc_0);
        assertEquals(1, reseauPetri.getArcs().size(), "CAA0, CAA1");
    }

    @Test
    @DisplayName("Assemble Petri")
    public void testAssemblePetri(){

        // CR1
        PetriNet Mutex = new PetriNet();
        Arc arc;

        // CP1
        Place P1 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P1);
        Mutex.addPlace(P1);
        assertEquals(1, Mutex.getPlaces().size(), "CP1") ;

        // CT1
        Transition T1 = new Transition("T1", Mutex.generateId(2));
        Mutex.addTransition(T1);
        Mutex.addTransition(T1);
        assertEquals(1, Mutex.getTransitions().size(), "CT1");

        // CP2
        Place P2 = new Place(1, Mutex.generateId(1));
        Mutex.addPlace(P2);
        assertEquals(2, Mutex.getPlaces().size(), "CP2");

        // CT2
        Transition T2 = new Transition("T2", Mutex.generateId(2));
        Mutex.addTransition(T2);
        assertEquals(2, Mutex.getTransitions().size(), "CT2");

        // CP5
        Place P3 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P3);
        assertEquals(3, Mutex.getPlaces().size(), "CP5");

        // CT3
        Transition T3 = new Transition("T3", Mutex.generateId(2));
        Mutex.addTransition(T3);
        assertEquals(3, Mutex.getTransitions().size(), "CT3");

        // CP4
        Place P4 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P4);
        assertEquals(4, Mutex.getPlaces().size(), "CP4");

        // CT4
        Transition T4 = new Transition("T4", Mutex.generateId(2));
        Mutex.addTransition(T4);
        assertEquals(4, Mutex.getTransitions().size(), "CT4");

        // CP5
        Place P5 = new Place(1, Mutex.generateId(1));
        Mutex.addPlace(P5);
        assertEquals(5, Mutex.getPlaces().size(), "CP5");

        // CPT1
        // Create the simple incoming arc to T1 from P1
        arc = new IncomingArc_Simple(T1, P1, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);
        T1.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T1.getIncomingArcs().size(), "CPT1");

        // CTP2
        // Create the simple outgoing arc from T2 to P1
        arc = new OutgoingArc(T2, P1, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T2.getOutgoingArcs().size(), "CTP2");

        // CTP1
        // Create the simple outgoing arc from T1 to P2
        arc = new OutgoingArc(T1, P2, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T1.getOutgoingArcs().size(), "CTP1");

        // CPT2
        // Create the simple incoming arc to T2 from P2
        arc = new IncomingArc_Simple(T2, P2, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T2.getIncomingArcs().size(), "CPT2");

        // CPT5
        // Create the simple incoming arc to T1 from P3
        arc = new IncomingArc_Simple(T1, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);
        assertEquals(2, T1.getIncomingArcs().size(), "CPT5");

        // CTP5
        // Create the simple outgoing arc from T2 to P3
        arc = new OutgoingArc(T2, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);
        assertEquals(2, T2.getOutgoingArcs().size(), "CTP5");

        // CPT6
        // Create the simple incoming arc to T3 from P3
        arc = new IncomingArc_Simple(T3, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T3.getIncomingArcs().size(), "CPT6");

        // CTP6
        // Create the simple outgoing arc from T4 to P3
        arc = new OutgoingArc(T4, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T4.getOutgoingArcs().size(), "CTP6");

        // CTP3
        // Create the simple outgoing arc from T3 to P4
        arc = new OutgoingArc(T3, P4, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T3.getOutgoingArcs().size(), "CTP3");

        // CPT4
        // Create the simple incoming arc to T4 from P4
        arc = new IncomingArc_Simple(T4, P4, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T4.getIncomingArcs().size(), "CPT4");

        // CPT3
        // Create the simple incoming arc to T3 from P5
        arc = new IncomingArc_Simple(T3, P5, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);
        assertEquals(2, T3.getIncomingArcs().size(), "CPT3");

        // CTP4
        // Create the simple outgoing arc from T4 to P5
        arc = new OutgoingArc(T4, P5, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);
        assertEquals(2, T4.getOutgoingArcs().size(), "CTP4");

        assertEquals(12, Mutex.getArcs().size(), "There should be 12 unique arcs in Mutex");
        assertEquals(5, Mutex.getPlaces().size(), "There should be 5 unique places in Mutex");
        assertEquals(4, Mutex.getTransitions().size(), "There should be 4 unique transitions in Mutex");

        Mutex.displayNetwork();

    }

    @Test
    @DisplayName("RI")
    public void testActivatePetri_1() {
        PetriNet Active = new PetriNet();

        // Active has only one Transition
        Transition T1 = new Transition("T1", Active.generateId(2));
        Active.addTransition(T1);

        // Fire T1
        Active.fireTransition(String.valueOf(T1.getId()));
        Active.displayNetwork();

        assertEquals(1, Active.getTransitions().size(), "RI");
    }

    @Test
    @DisplayName("RD0")
    public void testActivatePetri_2() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has only one Place with 0 tokens
        Place p1 = new Place(0, active.generateId(1));
        active.addPlace(p1);

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, active.generateId(0));
        active.addArc(arc);

        t1.addIncomingArc(arc);
        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(0, p1.getTokenCount(), "RD0");
    }

    @Test
    @DisplayName("RD1")
    public void testActivatePetri_3() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has only one Place with 2 tokens
        Place p1 = new Place(2, active.generateId(1));
        active.addPlace(p1);

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, active.generateId(0));
        active.addArc(arc);

        t1.addIncomingArc(arc);
        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(1, p1.getTokenCount(), "RD1");
    }

    @Test
    @DisplayName("RD2")
    public void testActivatePetri_4() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has only one Place with 5 tokens
        Place p1 = new Place(5, active.generateId(1));
        active.addPlace(p1);

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 3, active.generateId(0));
        active.addArc(arc);

        t1.addIncomingArc(arc);
        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(2, p1.getTokenCount(), "RD2");
    }

    @Test
    @DisplayName("RG0")
    public void testActivatePetri_5() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has one Place with 1 token
        Place p1 = new Place(1, active.generateId(1));
        active.addPlace(p1);

        // Create the outgoing arc from T1 to P1
        OutgoingArc arc = new OutgoingArc(t1, p1, 1, active.generateId(0));
        active.addArc(arc);

        t1.addOutgoingArc(arc);
        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(2, p1.getTokenCount(), "RG0");
    }

    @Test
    @DisplayName("RM0")
    public void testActivatePetri_6() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has two Places with 0 and 2 tokens
        Place p1 = new Place(0, active.generateId(1));
        Place p2 = new Place(2, active.generateId(1));
        active.addPlace(p1);
        active.addPlace(p2);

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, active.generateId(0));
        active.addArc(arc);
        OutgoingArc arc1 = new OutgoingArc(t1, p2, 1, active.generateId(0));
        active.addArc(arc1);

        t1.addIncomingArc(arc);
        t1.addOutgoingArc(arc1);
        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(0, p1.getTokenCount(), "RM0");
        assertEquals(2, p2.getTokenCount(), "RM0");
    }

    @Test
    @DisplayName("RM1")
    public void testActivatePetri_7() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has two Places with 5 and 0 tokens
        Place p1 = new Place(5, active.generateId(1));
        Place p2 = new Place(0, active.generateId(1));
        active.addPlace(p1);
        active.addPlace(p2);

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 3, active.generateId(0));
        active.addArc(arc);
        OutgoingArc arc1 = new OutgoingArc(t1, p2, 1, active.generateId(0));
        active.addArc(arc1);

        t1.addIncomingArc(arc);
        t1.addOutgoingArc(arc1);
        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(2, p1.getTokenCount(), "RM1");
        assertEquals(1, p2.getTokenCount(), "RM1");
    }

    @Test
    @DisplayName("RGM")
    public void testActivatePetri_8() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has two Places with 2 and 1 tokens
        Place p1 = new Place(2, active.generateId(1));
        Place p2 = new Place(1, active.generateId(1));
        active.addPlace(p1);
        active.addPlace(p2);

        // Create the simple incoming arc to T1 from P1 and P2
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, active.generateId(0));
        active.addArc(arc);
        IncomingArc arc1 = new IncomingArc_Simple(t1, p2, 1, active.generateId(0));
        active.addArc(arc1);

        t1.addIncomingArc(arc);
        t1.addIncomingArc(arc1);

        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(1, p1.getTokenCount(), "RGM");
        assertEquals(0, p2.getTokenCount(), "RGM");
    }

    @Test
    @DisplayName("RDM")
    public void testActivatePetri_9() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has two Places with 0 and 1 tokens
        Place p1 = new Place(0, active.generateId(1));
        Place p2 = new Place(1, active.generateId(1));
        active.addPlace(p1);
        active.addPlace(p2);

        // Create the outgoing arc from T1 to P1 and P2
        OutgoingArc arc = new OutgoingArc(t1, p1, 1, active.generateId(0));
        active.addArc(arc);
        OutgoingArc arc1 = new OutgoingArc(t1, p2, 1, active.generateId(0));
        active.addArc(arc1);

        t1.addOutgoingArc(arc);
        t1.addOutgoingArc(arc1);

        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(1, p1.getTokenCount(), "RDM");
        assertEquals(2, p2.getTokenCount(), "RDM");
    }

    @Test
    @DisplayName("RDGM")
    public void testActivatePetri_10() {
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has 4 Places with 1 and 1 tokens and 0 and 0 tokens
        Place p1 = new Place(1, active.generateId(1));
        Place p2 = new Place(1, active.generateId(1));
        Place p3 = new Place(0, active.generateId(1));
        Place p4 = new Place(0, active.generateId(1));
        active.addPlace(p1);
        active.addPlace(p2);
        active.addPlace(p3);
        active.addPlace(p4);

        // Create the simple incoming arc to T1 from P1 and P2
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, active.generateId(0));
        active.addArc(arc);
        IncomingArc arc1 = new IncomingArc_Simple(t1, p2, 1, active.generateId(0));
        active.addArc(arc1);

        // Create the outgoing arc from T1 to P3 and P4
        OutgoingArc arc2 = new OutgoingArc(t1, p3, 1, active.generateId(0));
        active.addArc(arc2);
        OutgoingArc arc3 = new OutgoingArc(t1, p4, 1, active.generateId(0));
        active.addArc(arc3);

        t1.addIncomingArc(arc);
        t1.addIncomingArc(arc1);
        t1.addOutgoingArc(arc2);
        t1.addOutgoingArc(arc3);

        active.addTransition(t1);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(0, p1.getTokenCount(), "RDGM");
        assertEquals(0, p2.getTokenCount(), "RDGM");
        assertEquals(1, p3.getTokenCount(), "RDGM");
        assertEquals(1, p4.getTokenCount(), "RDGM");
    }

    @Test
    @DisplayName("ST0")
    public void testRemoveTransition_0() {
        PetriNet supp = new PetriNet();

        Transition t1 = new Transition("T1", supp.generateId(2));
        supp.addTransition(t1);
        supp.removeTransition(t1);
        supp.displayNetwork();

        assertEquals(0, supp.getTransitions().size(), "ST0");
    }

    @Test
    @DisplayName("SP1")
    public void testRemovePlace() {
        PetriNet destruction = new PetriNet();

        // Create a Petri net similar to Active in testActivatePetri_3
        Place p1 = new Place(2, destruction.generateId(1));
        destruction.addPlace(p1);

        Transition t1 = new Transition("T1", destruction.generateId(2));

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, destruction.generateId(0));
        destruction.addArc(arc);

        t1.addIncomingArc(arc);
        destruction.addTransition(t1);

        // Remove place P1
        destruction.removePlace(p1);
        destruction.displayNetwork();

        assertEquals(0, destruction.getPlaces().size(), "SP1");
        assertEquals(1, destruction.getTransitions().size(), "SP1");
        assertEquals(0, destruction.getArcs().size(), "SP1");
    }

    @Test
    @DisplayName("ST1")
    public void testRemoveTransition_1() {
        PetriNet destruction = new PetriNet();

        // Create a Petri net similar to Active in testActivatePetri_3
        Place p1 = new Place(2, destruction.generateId(1));
        destruction.addPlace(p1);

        Transition t1 = new Transition("T1", destruction.generateId(2));

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, destruction.generateId(0));
        destruction.addArc(arc);

        t1.addIncomingArc(arc);
        destruction.addTransition(t1);

        // Remove transition T1
        destruction.removeTransition(t1);
        destruction.displayNetwork();

        assertEquals(1, destruction.getPlaces().size(), "ST1");
        assertEquals(0, destruction.getTransitions().size(), "ST1");
        assertEquals(0, destruction.getArcs().size(), "ST1");
    }

    @Test
    @DisplayName("SA1")
    public void testRemoveArc() {
        PetriNet destruction = new PetriNet();

        // Create a Petri net similar to Active in testActivatePetri_3
        Place p1 = new Place(2, destruction.generateId(1));
        destruction.addPlace(p1);

        Transition t1 = new Transition("T1", destruction.generateId(2));

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, destruction.generateId(0));
        destruction.addArc(arc);

        t1.addIncomingArc(arc);
        destruction.addTransition(t1);

        // Remove the arc
        destruction.removeArc(arc);
        destruction.displayNetwork();

        assertEquals(1, destruction.getPlaces().size(), "SA1");
        assertEquals(1, destruction.getTransitions().size(), "SA1");
        assertEquals(0, destruction.getArcs().size(), "SA1");
    }

    @Test
    @DisplayName("SAV")
    public void testIncomingArcVideur() {
        // Initialize the Petri network
        PetriNet petriNetwork = new PetriNet();

        // Create places
        Place place1 = new Place(3, petriNetwork.generateId(1));
        Place place2 = new Place(0, petriNetwork.generateId(1));

        // Add places to the network
        petriNetwork.addPlace(place1);
        petriNetwork.addPlace(place2);

        // Create a transition
        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));

        // Add the transition to the network
        petriNetwork.addTransition(transition1);

        // Create arcs and add them to the network
        // Incoming arc from place2 to transition1 with weight 1
        IncomingArc_Zero incomingArcZero = new IncomingArc_Zero(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(incomingArcZero);
        transition1.addIncomingArc(incomingArcZero);

        // Outgoing arc from transition1 to place1 with weight 1
        OutgoingArc outgoingArc = new OutgoingArc(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(outgoingArc);
        transition1.addOutgoingArc(outgoingArc);

        // Display the initial state of the Petri network
        System.out.println("Initial State of Petri Network:");
        petriNetwork.displayNetwork();

        // Activate the transition
        System.out.println("\nActivating transition T1...");
        petriNetwork.fireTransition(String.valueOf(transition1.getId()));

        // Display the state of the Petri network after activation
        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.displayNetwork();

        // Assertions to verify the correct state of the Petri network
        assertEquals(4, place1.getTokenCount(), "Tokens in Place 1 should be 4");
        assertEquals(0, place2.getTokenCount(), "Tokens in Place 2 should be 0");
    }

    @Test
    @DisplayName("SAZ")
    public void testIncomingArcZero() {
        // Initialize the Petri network
        PetriNet petriNetwork = new PetriNet();

        // Create places
        Place place1 = new Place(2, petriNetwork.generateId(1));
        Place place2 = new Place(4, petriNetwork.generateId(1));

        // Add places to the network
        petriNetwork.addPlace(place1);
        petriNetwork.addPlace(place2);

        // Create a transition
        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));

        // Add the transition to the network
        petriNetwork.addTransition(transition1);

        // Create arcs and add them to the network
        // Incoming arc from place2 to transition1 with weight 1
        IncomingArc_Videur incomingArcVideur = new IncomingArc_Videur(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(incomingArcVideur);
        transition1.addIncomingArc(incomingArcVideur);

        // Outgoing arc from transition1 to place1 with weight 1
        OutgoingArc outgoingArc = new OutgoingArc(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(outgoingArc);
        transition1.addOutgoingArc(outgoingArc);

        // Display the initial state of the Petri network
        System.out.println("Initial State of Petri Network:");
        petriNetwork.displayNetwork();

        // Activate the transition
        System.out.println("\nActivating transition T1...");
        petriNetwork.fireTransition(String.valueOf(transition1.getId()));

        // Display the state of the Petri network after activation
        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.displayNetwork();

        // Assertions to verify the correct state of the Petri network
        assertEquals(3, place1.getTokenCount(), "Tokens in Place 1 should be 3");
        assertEquals(0, place2.getTokenCount(), "Tokens in Place 2 should be 0");
    }

    @Test
    public void testSetTokenCount() {
        Place place = new Place(0, 1);

        // Set the token count to a positive value
        place.setTokenCount(5);
        assertEquals(5, place.getTokenCount());

        // Set the token count to zero
        place.setTokenCount(0);
        assertEquals(0, place.getTokenCount());

        // Set the token count to a negative value (should be clamped to 0)
        place.setTokenCount(-2);
        assertEquals(0, place.getTokenCount());
    }
    @Test
    public void testAddTokens() {
        Place place = new Place(0, reseauPetri.generateId(1));

        // Test with positive tokens
        place.addTokens(5);
        assertEquals(5, place.getTokenCount());

        // Test with zero tokens
        place.addTokens(0);
        assertEquals(5, place.getTokenCount());

        // Test with negative tokens (should not change token count)
        place.addTokens(-2);
        assertEquals(5, place.getTokenCount());
    }
    @Test
    public void testRemoveTokens() {
        Place place = new Place(10, reseauPetri.generateId(1));

        // Test with positive tokens less than the current count
        place.removeTokens(5);
        assertEquals(5, place.getTokenCount());

        // Test with positive tokens equal to the current count
        place.removeTokens(5);
        assertEquals(0, place.getTokenCount());

        // Test with positive tokens greater than the current count (should not go below 0)
        place.removeTokens(10);
        assertEquals(0, place.getTokenCount());

        // Test with negative tokens (should not change token count)
        place.removeTokens(-2);
        assertEquals(0, place.getTokenCount());
    }
    @Test
    public void testSetPlaces() {
        PetriNet petriNet = new PetriNet();

        // Create a list of places
        List<Place> places = new ArrayList<>();
        Place place1 = new Place(2, petriNet.generateId(1));
        Place place2 = new Place(3, petriNet.generateId(2));
        places.add(place1);
        places.add(place2);

        // Set the places in the Petri net
        petriNet.setPlaces(places);

        // Assert that the places were correctly set
        assertEquals(2, petriNet.getPlaces().size());
        assertEquals(place1, petriNet.getPlaces().get(0));
        assertEquals(place2, petriNet.getPlaces().get(1));
    }
    @Test
    public void testSetTransitions() {
        PetriNet petriNet = new PetriNet();

        // Create a list of transitions
        List<Transition> transitions = new ArrayList<>();
        Transition transition1 = new Transition("T1", petriNet.generateId(1));
        Transition transition2 = new Transition("T2", petriNet.generateId(2));
        transitions.add(transition1);
        transitions.add(transition2);

        // Set the transitions in the Petri net
        petriNet.setTransitions(transitions);

        // Assert that the transitions were correctly set
        assertEquals(2, petriNet.getTransitions().size());
        assertEquals(transition1, petriNet.getTransitions().get(0));
        assertEquals(transition2, petriNet.getTransitions().get(1));
    }
    @Test
    public void testTransitionMethods() {
        Transition transition = new Transition("T1", 1);

        // Test setOutgoingArcs
        List<OutgoingArc> outgoingArcs = new ArrayList<>();
        Place place1 = new Place(2, 2);
        Place place2 = new Place(3, 3);
        OutgoingArc arc1 = new OutgoingArc(transition, place1, 1, 4);
        OutgoingArc arc2 = new OutgoingArc(transition, place2, 2, 5);
        outgoingArcs.add(arc1);
        outgoingArcs.add(arc2);

        transition.setOutgoingArcs(outgoingArcs);
        assertEquals(2, transition.getOutgoingArcs().size());
        assertEquals(arc1, transition.getOutgoingArcs().get(0));
        assertEquals(arc2, transition.getOutgoingArcs().get(1));

        // Test addOutgoingArc (duplicate)
        OutgoingArc duplicateArc = new OutgoingArc(transition, place1, 1, 6); // Same place and weight
        transition.addOutgoingArc(duplicateArc);
        assertEquals(2, transition.getOutgoingArcs().size());  // Should not add duplicate

        // Test addOutgoingArc (unique)
        OutgoingArc uniqueArc = new OutgoingArc(transition, new Place(4, 7), 3, 8);
        transition.addOutgoingArc(uniqueArc);
        assertEquals(3, transition.getOutgoingArcs().size());
        assertTrue(transition.getOutgoingArcs().contains(uniqueArc));
    }
    @Test
    public void testSetPlace() {
        Transition transition = new Transition("T1", 1);
        Place initialPlace = new Place(2, 2);
        Place newPlace = new Place(3, 3);
        int weight = 1;
        int id = 4;

        // Create an OutgoingArc
        OutgoingArc arc = new OutgoingArc(transition, initialPlace, weight, id);

        // Assert initial place
        assertEquals(initialPlace, arc.getPlace());

        // Set a new place
        arc.setPlace(newPlace);

        // Assert the place is updated
        assertEquals(newPlace, arc.getPlace());
    }





}


