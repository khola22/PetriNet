package org.petriNet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetriNetStructureTest {

    private PetriNet reseauPetri;
    private Place place;
    private Transition transition;

    @BeforeEach
    public void setUp() {
        reseauPetri = new PetriNet();
        place = new Place(2, reseauPetri.generateId(1));
        transition = new Transition("transition", reseauPetri.generateId(2));
    }

    @Test
    public void testAddToken() {
        place.addTokens(-2);
        place.addTokens(2);
        assertEquals(4, place.getTokenCount(), "CAJ0, CAJ1");
    }

    @Test
    public void testRemoveToken() {
        place.removeTokens(-2);
        place.removeTokens(2);
        assertEquals(0, place.getTokenCount(), "CEJ0, CEJ1");
    }

    @Test
    public void testAddPlace() {
        reseauPetri.addPlace(place);

        // A test for an existing place
        reseauPetri.addPlace(place);
        assertEquals(1, reseauPetri.getPlaces().size(), "CAP0, CAP1");
    }

    @Test
    public void testAddTransition() {
        reseauPetri.addTransition(transition);
        // A test for an existing transition
        reseauPetri.addTransition(transition);
        assertEquals(1, reseauPetri.getTransitions().size(), "CAT0, CAT1");
    }

    @Test
    public void testAddArc() {
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
        OutgoingArc arc = new OutgoingArc(transition, place, 1, reseauPetri.generateId(0));
        arc.setWeight(2);
        assertEquals(2, arc.getWeight(), "CSW0, CSW1");
    }

    @Test
    public void testSetTokenCount() {
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
}


