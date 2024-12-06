package org.petriNet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetriNetRemoveTest {

    private PetriNet reseauPetri;

    @BeforeEach
    public void setUp() {
        reseauPetri = new PetriNet();
    }

    @Test
    @DisplayName("ST0")
    public void testRemoveTransition_0() {

        Transition t1 = new Transition("T1", reseauPetri.generateId(2));
        reseauPetri.addTransition(t1);
        reseauPetri.removeTransition(t1);
        reseauPetri.displayNetwork();

        assertEquals(0, reseauPetri.getTransitions().size(), "number of transitions");
    }

    @Test
    @DisplayName("SP1")
    public void testRemovePlace() {

        // Create a Petri net similar to Active in testActivatePetri_3
        Place p1 = new Place(2, reseauPetri.generateId(1));
        reseauPetri.addPlace(p1);

        Transition t1 = new Transition("T1", reseauPetri.generateId(2));

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);

        t1.addIncomingArc(arc);
        reseauPetri.addTransition(t1);

        // Remove place P1
        reseauPetri.removePlace(p1);
        reseauPetri.displayNetwork();

        assertEquals(0, reseauPetri.getPlaces().size(), "Number of places");
        assertEquals(1, reseauPetri.getTransitions().size(), "Number of transitions");
        assertEquals(0, reseauPetri.getArcs().size(), "Number of arcs");
    }

    @Test
    @DisplayName("ST1")
    public void testRemoveTransition_1() {

        // Create a Petri net similar to Active in testActivatePetri_3
        Place p1 = new Place(2, reseauPetri.generateId(1));
        reseauPetri.addPlace(p1);

        Transition t1 = new Transition("T1", reseauPetri.generateId(2));

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);

        t1.addIncomingArc(arc);
        reseauPetri.addTransition(t1);

        // Remove transition T1
        reseauPetri.removeTransition(t1);
        reseauPetri.displayNetwork();

        assertEquals(1, reseauPetri.getPlaces().size(), "Number of places");
        assertEquals(0, reseauPetri.getTransitions().size(), "Number of transitions");
        assertEquals(0, reseauPetri.getArcs().size(), "Number of arcs");
    }

    @Test
    @DisplayName("SA1")
    public void testRemoveArc() {

        // Create a Petri net similar to Active in testActivatePetri_3
        Place p1 = new Place(2, reseauPetri.generateId(1));
        reseauPetri.addPlace(p1);

        Transition t1 = new Transition("T1", reseauPetri.generateId(2));

        // Create the simple incoming arc to T1 from P1
        IncomingArc arc = new IncomingArc_Simple(t1, p1, 1, reseauPetri.generateId(0));
        reseauPetri.addArc(arc);

        t1.addIncomingArc(arc);
        reseauPetri.addTransition(t1);

        // Remove the arc
        reseauPetri.removeArc(arc);
        reseauPetri.displayNetwork();

        assertEquals(1, reseauPetri.getPlaces().size(), "Number of places");
        assertEquals(1, reseauPetri.getTransitions().size(), "Number of transitions");
        assertEquals(0, reseauPetri.getArcs().size(), "Number of arcs");
    }

}
