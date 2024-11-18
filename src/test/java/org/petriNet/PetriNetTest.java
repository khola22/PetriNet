package org.petriNet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void testSetWeight() {
        Place place = new Place(2, reseauPetri.generateId(1));
        Transition transition = new Transition("transition", reseauPetri.generateId(2));
        OutgoingArc arc = new OutgoingArc(transition, place, 1, reseauPetri.generateId(0));
        arc.setWeight(2);
        assertEquals(2, arc.getWeight(), "CSW0, CSW1");
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
    @DisplayName("Assemble Petri")
    public void testAssemblePetri(){

        // CR1
        PetriNet Mutex = new PetriNet();
        Arc arc;

        // CP1
        Place P1 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P1);
        Mutex.addPlace(P1);
        assertEquals(1, Mutex.getPlaces().size(), "Number of places"); ;

        // CT1
        Transition T1 = new Transition("T1", Mutex.generateId(2));
        Mutex.addTransition(T1);
        Mutex.addTransition(T1);
        assertEquals(1, Mutex.getTransitions().size(), "Number of transitions");

        // CP2
        Place P2 = new Place(1, Mutex.generateId(1));
        Mutex.addPlace(P2);
        assertEquals(2, Mutex.getPlaces().size(), "Number of places");

        // CT2
        Transition T2 = new Transition("T2", Mutex.generateId(2));
        Mutex.addTransition(T2);
        assertEquals(2, Mutex.getTransitions().size(), "Number of transitions");

        // CP5
        Place P3 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P3);
        assertEquals(3, Mutex.getPlaces().size(), "Number of places");

        // CT3
        Transition T3 = new Transition("T3", Mutex.generateId(2));
        Mutex.addTransition(T3);
        assertEquals(3, Mutex.getTransitions().size(), "Number of transitions");

        // CP4
        Place P4 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P4);
        assertEquals(4, Mutex.getPlaces().size(), "Number of places");

        // CT4
        Transition T4 = new Transition("T4", Mutex.generateId(2));
        Mutex.addTransition(T4);
        assertEquals(4, Mutex.getTransitions().size(), "Number of transitions");

        // CP5
        Place P5 = new Place(1, Mutex.generateId(1));
        Mutex.addPlace(P5);
        assertEquals(5, Mutex.getPlaces().size(), "Number of places");

        // CPT1
        // Create the simple incoming arc to T1 from P1
        arc = new IncomingArc_Simple(T1, P1, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);
        T1.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T1.getIncomingArcs().size(), "Number of incoming arcs in T1");

        // CTP2
        // Create the simple outgoing arc from T2 to P1
        arc = new OutgoingArc(T2, P1, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T2.getOutgoingArcs().size(), "Number of outgoing arcs in T2");

        // CTP1
        // Create the simple outgoing arc from T1 to P2
        arc = new OutgoingArc(T1, P2, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T1.getOutgoingArcs().size(), "Number of outgoing arcsin T1");

        // CPT2
        // Create the simple incoming arc to T2 from P2
        arc = new IncomingArc_Simple(T2, P2, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T2.getIncomingArcs().size(), "Number of incoming arcs in T2");

        // CPT5
        // Create the simple incoming arc to T1 from P3
        arc = new IncomingArc_Simple(T1, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);
        assertEquals(2, T1.getIncomingArcs().size(), "Number of incoming arcs in T1");

        // CTP5
        // Create the simple outgoing arc from T2 to P3
        arc = new OutgoingArc(T2, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);
        assertEquals(2, T2.getOutgoingArcs().size(), "Number of outgoing arcs in T2");

        // CPT6
        // Create the simple incoming arc to T3 from P3
        arc = new IncomingArc_Simple(T3, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T3.getIncomingArcs().size(), "Number of incoming arcs in T3");

        // CTP6
        // Create the simple outgoing arc from T4 to P3
        arc = new OutgoingArc(T4, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T4.getOutgoingArcs().size(), "Number of outgoing arcs in T4");

        // CTP3
        // Create the simple outgoing arc from T3 to P4
        arc = new OutgoingArc(T3, P4, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T3.getOutgoingArcs().size(), "Number of outgoing arcs in T3");

        // CPT4
        // Create the simple incoming arc to T4 from P4
        arc = new IncomingArc_Simple(T4, P4, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T4.getIncomingArcs().size(), "Number of incoming arcs in T4");

        // CPT3
        // Create the simple incoming arc to T3 from P5
        arc = new IncomingArc_Simple(T3, P5, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);
        assertEquals(2, T3.getIncomingArcs().size(), "Number of incoming arcs in T3");

        // CTP4
        // Create the simple outgoing arc from T4 to P5
        arc = new OutgoingArc(T4, P5, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);
        assertEquals(2, T4.getOutgoingArcs().size(), "Number of outgoing arcs in T4");

        assertEquals(12, Mutex.getArcs().size(), "Total number of arcs");
        assertEquals(5, Mutex.getPlaces().size(), "Total number of places");
        assertEquals(4, Mutex.getTransitions().size(), "Total number of transitions");

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

        assertEquals(1, Active.getTransitions().size(), "Number of transitions");
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

        assertEquals(0, p1.getTokenCount(), "Tokens in P1");
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

        assertEquals(1, p1.getTokenCount(), "Tokens in P1");
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

        assertEquals(2, p1.getTokenCount(), "Tokens in P1");
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

            assertEquals(2, p1.getTokenCount(), "Tokens in P1");
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

        assertEquals(0, p1.getTokenCount(), "Tokens in P1");
        assertEquals(2, p2.getTokenCount(), "Tokens in P2");
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

        assertEquals(2, p1.getTokenCount(), "Tokens in P1");
        assertEquals(1, p2.getTokenCount(), "Tokens in P2");
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

        assertEquals(1, p1.getTokenCount(), "Tokens in P1");
        assertEquals(0, p2.getTokenCount(), "Tokens in P2");
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

        assertEquals(1, p1.getTokenCount(), "Tokens in P1");
        assertEquals(2, p2.getTokenCount(), "Tokens in P2");
    }

    @Test
    @DisplayName("RDGM0")
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

        assertEquals(0, p1.getTokenCount(), "Tokens in P1");
        assertEquals(0, p2.getTokenCount(), "Tokens in P2");
        assertEquals(1, p3.getTokenCount(), "Tokens in P3");
        assertEquals(1, p4.getTokenCount(), "Tokens in P4");
    }

    @Test
    @DisplayName("RDGM1")
    public void testActivatePetri_11(){
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        /* We test PetriNet.setPlaces(List<Place> places),
          PetriNet.setArcs(List<Arc> arcs),
          Transition.setIncomingArcs(List<IncomingArc> incomingArcs),
          Transition.setOutgoingArcs(List<OutgoingArc> outgoingArcs),
          PetriNet.setTransitions(List<Transition> transitions)
         */

        // Active has 4 Places with 1 and 1 tokens and 0 and 0 tokens
        Place p1 = new Place(1, active.generateId(1));
        Place p2 = new Place(1, active.generateId(1));
        Place p3 = new Place(0, active.generateId(1));
        Place p4 = new Place(0, active.generateId(1));
        // Add places through a list
        List<Place> places = Arrays.asList(p1, p2, p3, p4);
        active.setPlaces(places);

        // Create the simple incoming arc to T1 from P1 and P2
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, active.generateId(0));
        IncomingArc arc1 = new IncomingArc_Simple(t1, p2, 1, active.generateId(0));

        // Create the outgoing arc from T1 to P3 and P4
        OutgoingArc arc2 = new OutgoingArc(t1, p3, 1, active.generateId(0));
        OutgoingArc arc3 = new OutgoingArc(t1, p4, 1, active.generateId(0));

        // Add arcs to Petri through a list
        List<Arc> arcs = Arrays.asList(arc, arc1, arc2, arc3);
        active.setArcs(new LinkedList<>(arcs));

        // Add arcs to a transition through a list
        LinkedList<IncomingArc> incomingArcs = new LinkedList<>(Arrays.asList(arc, arc1));
        LinkedList<OutgoingArc> outgoingArcs = new LinkedList<>(Arrays.asList(arc2, arc3));
        t1.setIncomingArcs(incomingArcs);
        t1.setOutgoingArcs(outgoingArcs);

        // Create other transitions to test PetriNet.setTransitions
        Transition t2 = new Transition("T2", active.generateId(2));
        Transition t3 = new Transition("T3", active.generateId(2));
        Transition t4 = new Transition("T4", active.generateId(2));

        // Add transitions through a list
        List<Transition> transitions = Arrays.asList(t1, t2, t3, t4);

        active.setTransitions(transitions);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(4, active.getTransitions().size(), "Number of transitions");
        assertEquals(4, active.getPlaces().size(), "Number of places");
        assertEquals(4, active.getArcs().size(), "Number of arcs");
        assertEquals(0, p1.getTokenCount(), "Tokens in P1");
        assertEquals(0, p2.getTokenCount(), "Tokens in P2");
        assertEquals(1, p3.getTokenCount(), "Tokens in P3");
        assertEquals(1, p4.getTokenCount(), "Tokens in P4");
    }

    @Test
    @DisplayName("RDGM2")
    public void testActivatePetri_12(){
        PetriNet active = new PetriNet();

        Transition t1 = new Transition("T1", active.generateId(2));

        // Active has 4 Places with 1 and 1 tokens and 0 and 0 tokens
        // Create places using the PetriNet.createPlaces(int tokenCount) method
        // The list of number of tokens is {1, 1, 0, 0}
        List<Integer> tokenCounts = Arrays.asList(1, 1, 0, 0);
        List<Place> places = active.createPlaces(4, tokenCounts);
        active.setPlaces(places);

        // Create the simple incoming arcs to T1 from P1 and P2
        // Create arcs using the PetriNet.createIncomingArcs(int numberOfArcs, List<String> types_of_arcs, List<Integer> weights,
        //                                                   List<Place> places, List<Transition> transitions)
        // The list of types of arcs is {"simple", "simple"}
        // The list of weights is {1, 1}
        // The list of places is {P1, P2}
        // The list of transitions is {T1, T1}
        List<String> types_of_arcs = Arrays.asList("Simple", "Simple");
        List<Integer> weights = Arrays.asList(1, 1);
        LinkedList<Arc> incomingArcs = active.createIncomingArcs(2, types_of_arcs, weights, places.subList(0, 2), Arrays.asList(t1, t1));

        // Create the outgoing arc from T1 to P3 and P4
        // Create arcs using the PetriNet.createOutgoingArcs(int numberOfArcs, List<Integer> weights, List<Place> places, List<Transition> transitions)
        // The list of weights is {1, 1}
        // The list of places is {P3, P4}
        // The list of transitions is {T1, T1}
        LinkedList<Arc> outgoingArcs = active.createOutgoingArcs(2, weights, places.subList(2, 4), Arrays.asList(t1, t1));

        // Merge the incoming and outgoing arcs
        LinkedList<Arc> arcs = new LinkedList<>(incomingArcs);
        arcs.addAll(outgoingArcs);
        active.setArcs(arcs);

        LinkedList<IncomingArc> incomingArcsList = incomingArcs.stream().map(arc -> (IncomingArc) arc).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<OutgoingArc> outgoingArcsList = outgoingArcs.stream().map(arc -> (OutgoingArc) arc).collect(Collectors.toCollection(LinkedList::new));
        t1.setIncomingArcs(incomingArcsList);
        t1.setOutgoingArcs(outgoingArcsList);

        // Create other transitions to test PetriNet.setTransitions
        LinkedList<Transition> transitions;
        transitions = active.createTransitions(3);
        transitions.add(t1);

        active.setTransitions(transitions);

        // Fire T1
        active.fireTransition(String.valueOf(t1.getId()));
        active.displayNetwork();

        assertEquals(4, active.getTransitions().size(), "Number of transitions");
        assertEquals(4, active.getPlaces().size(), "Number of places");
        assertEquals(4, active.getArcs().size(), "Number of arcs");
        assertEquals(0, places.get(0).getTokenCount(), "Tokens in P1");
        assertEquals(0, places.get(1).getTokenCount(), "Tokens in P2");
        assertEquals(1, places.get(2).getTokenCount(), "Tokens in P3");
        assertEquals(1, places.get(3).getTokenCount(), "Tokens in P4");
    }

    @Test
    @DisplayName("ST0")
    public void testRemoveTransition_0() {
        PetriNet supp = new PetriNet();

        Transition t1 = new Transition("T1", supp.generateId(2));
        supp.addTransition(t1);
        supp.removeTransition(t1);
        supp.displayNetwork();

        assertEquals(0, supp.getTransitions().size(), "number of transitions");
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

        assertEquals(0, destruction.getPlaces().size(), "Number of places");
        assertEquals(1, destruction.getTransitions().size(), "Number of transitions");
        assertEquals(0, destruction.getArcs().size(), "Number of arcs");
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

        assertEquals(1, destruction.getPlaces().size(), "Number of places");
        assertEquals(0, destruction.getTransitions().size(), "Number of transitions");
        assertEquals(0, destruction.getArcs().size(), "Number of arcs");
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

        assertEquals(1, destruction.getPlaces().size(), "Number of places");
        assertEquals(1, destruction.getTransitions().size(), "Number of transitions");
        assertEquals(0, destruction.getArcs().size(), "Number of arcs");
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
        assertEquals(4, place1.getTokenCount(), "Tokens in Place 1");
        assertEquals(0, place2.getTokenCount(), "Tokens in Place 2");
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
        assertEquals(3, place1.getTokenCount(), "Tokens in Place 1");
        assertEquals(0, place2.getTokenCount(), "Tokens in Place 2");
    }

}


