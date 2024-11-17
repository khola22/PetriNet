package org.petriNet;

public class MainPetriNet {

    public static void main(String[] args) {
        // Create a Petri net
        PetriNet reseauPetri = new PetriNet();

        // Create places
        Place p1 = new Place(1, reseauPetri.generateId(1));
        Place p2 = new Place(2, reseauPetri.generateId(2));
        Place p3 = new Place(3, reseauPetri.generateId(3));

        // Create transitions with empty Arcs_ENTRANT and Arcs_SORTANT
        Transition t1 = new Transition("t1", reseauPetri.generateId(2));
        Transition t2 = new Transition("t2", reseauPetri.generateId(2));

        // Create arcs
        IncomingArc a1 = new IncomingArc_Simple(t1, p1, 1, reseauPetri.generateId(0));
        OutgoingArc a2 = new OutgoingArc(t1, p2, 1, reseauPetri.generateId(0));
        IncomingArc a3 = new IncomingArc_Simple(t2, p2, 1, reseauPetri.generateId(0));
        OutgoingArc a4 = new OutgoingArc(t2, p3, 1, reseauPetri.generateId(0));

        // Add arcs to transitions
        t1.addIncomingArc(a1);
        t1.addOutgoingArc(a2);
        t2.addIncomingArc(a3);
        t2.addOutgoingArc(a4);

        // Add a place to each arc
        a1.setPlace(p1);
        a2.setPlace(p2);
        a3.setPlace(p2);
        a4.setPlace(p3);

        // Add places and transitions to the Petri net
        reseauPetri.addPlace(p1);
        reseauPetri.addPlace(p2);
        reseauPetri.addPlace(p3);
        reseauPetri.addTransition(t1);
        reseauPetri.addTransition(t2);

        // Display the Petri net
        reseauPetri.displayState();
    }
}