package org.petriNet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetriNetActivationTest {

    private PetriNet reseauPetri;

    @BeforeEach
    public void setUp() {
        reseauPetri = new PetriNet();
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

}
