package org.petriNet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetriNetAssemblyTest {

    private PetriNet reseauPetri;
    Arc arc;

    @BeforeEach
    public void setUp() {
        reseauPetri = new PetriNet();
    }

    @Test
    @DisplayName("Assemble Petri")
    public void testAssemblePetri(){
        // CP1
        Place P1 = new Place(0, reseauPetri.generateId(1));
        reseauPetri.addPlace(P1);
        reseauPetri.addPlace(P1);
        assertEquals(1, reseauPetri.getPlaces().size(), "Number of places");

        // CT1
        Transition T1 = new Transition("T1", reseauPetri.generateId(2));
        reseauPetri.addTransition(T1);
        reseauPetri.addTransition(T1);
        assertEquals(1, reseauPetri.getTransitions().size(), "Number of transitions");

        // CP2
        Place P2 = new Place(1, reseauPetri.generateId(1));
        reseauPetri.addPlace(P2);
        assertEquals(2, reseauPetri.getPlaces().size(), "Number of places");

        // CT2
        Transition T2 = new Transition("T2", reseauPetri.generateId(2));
        reseauPetri.addTransition(T2);
        assertEquals(2, reseauPetri.getTransitions().size(), "Number of transitions");

        // CP5
        Place P3 = new Place(0, reseauPetri.generateId(1));
        reseauPetri.addPlace(P3);
        assertEquals(3, reseauPetri.getPlaces().size(), "Number of places");

        // CT3
        Transition T3 = new Transition("T3", reseauPetri.generateId(2));
        reseauPetri.addTransition(T3);
        assertEquals(3, reseauPetri.getTransitions().size(), "Number of transitions");

        // CP4
        Place P4 = new Place(0, reseauPetri.generateId(1));
        reseauPetri.addPlace(P4);
        assertEquals(4, reseauPetri.getPlaces().size(), "Number of places");

        // CT4
        Transition T4 = new Transition("T4", reseauPetri.generateId(2));
        reseauPetri.addTransition(T4);
        assertEquals(4, reseauPetri.getTransitions().size(), "Number of transitions");

        // CP5
        Place P5 = new Place(1, reseauPetri.generateId(1));
        reseauPetri.addPlace(P5);
        assertEquals(5, reseauPetri.getPlaces().size(), "Number of places");

        // CPT1
        // Create the simple incoming arc to T1 from P1
        arc = new IncomingArc_Simple(T1, P1, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);
        T1.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T1.getIncomingArcs().size(), "Number of incoming arcs in T1");

        // CTP2
        // Create the simple outgoing arc from T2 to P1
        arc = new OutgoingArc(T2, P1, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T2.getOutgoingArcs().size(), "Number of outgoing arcs in T2");

        // CTP1
        // Create the simple outgoing arc from T1 to P2
        arc = new OutgoingArc(T1, P2, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T1.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T1.getOutgoingArcs().size(), "Number of outgoing arcsin T1");

        // CPT2
        // Create the simple incoming arc to T2 from P2
        arc = new IncomingArc_Simple(T2, P2, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T2.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T2.getIncomingArcs().size(), "Number of incoming arcs in T2");

        // CPT5
        // Create the simple incoming arc to T1 from P3
        arc = new IncomingArc_Simple(T1, P3, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);
        assertEquals(2, T1.getIncomingArcs().size(), "Number of incoming arcs in T1");

        // CTP5
        // Create the simple outgoing arc from T2 to P3
        arc = new OutgoingArc(T2, P3, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);
        assertEquals(2, T2.getOutgoingArcs().size(), "Number of outgoing arcs in T2");

        // CPT6
        // Create the simple incoming arc to T3 from P3
        arc = new IncomingArc_Simple(T3, P3, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T3.getIncomingArcs().size(), "Number of incoming arcs in T3");

        // CTP6
        // Create the simple outgoing arc from T4 to P3
        arc = new OutgoingArc(T4, P3, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T4.getOutgoingArcs().size(), "Number of outgoing arcs in T4");

        // CTP3
        // Create the simple outgoing arc from T3 to P4
        arc = new OutgoingArc(T3, P4, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T3.addOutgoingArc((OutgoingArc) arc);
        assertEquals(1, T3.getOutgoingArcs().size(), "Number of outgoing arcs in T3");

        // CPT4
        // Create the simple incoming arc to T4 from P4
        arc = new IncomingArc_Simple(T4, P4, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T4.addIncomingArc((IncomingArc) arc);
        assertEquals(1, T4.getIncomingArcs().size(), "Number of incoming arcs in T4");

        // CPT3
        // Create the simple incoming arc to T3 from P5
        arc = new IncomingArc_Simple(T3, P5, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);
        assertEquals(2, T3.getIncomingArcs().size(), "Number of incoming arcs in T3");

        // CTP4
        // Create the simple outgoing arc from T4 to P5
        arc = new OutgoingArc(T4, P5, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);
        assertEquals(2, T4.getOutgoingArcs().size(), "Number of outgoing arcs in T4");

        assertEquals(12, reseauPetri.getArcs().size(), "Total number of arcs");
        assertEquals(5, reseauPetri.getPlaces().size(), "Total number of places");
        assertEquals(4, reseauPetri.getTransitions().size(), "Total number of transitions");

        reseauPetri.displayNetwork();

    }


}
