package org.petriNet;

public class MainPetriNet {

    public static void main(String[] args) {
        // Create a Petri net
        ReseauPetri reseauPetri = new ReseauPetri();

        // Create places
        Place p1 = new Place(1, reseauPetri.generateId(1));
        Place p2 = new Place(2, reseauPetri.generateId(2));
        Place p3 = new Place(3, reseauPetri.generateId(3));

        // Create transitions with empty Arcs_ENTRANT and Arcs_SORTANT
        Transition t1 = new Transition("t1", reseauPetri.generateId(2));
        Transition t2 = new Transition("t2", reseauPetri.generateId(2));

        // Create arcs
        Arc_ENTRANT a1 = new Arc_entrant_simple(t1, p1, 1, reseauPetri.generateId(0));
        Arc_SORTANT a2 = new Arc_SORTANT(t1, p2, 1, reseauPetri.generateId(0));
        Arc_ENTRANT a3 = new Arc_entrant_simple(t2, p2, 1, reseauPetri.generateId(0));
        Arc_SORTANT a4 = new Arc_SORTANT(t2, p3, 1, reseauPetri.generateId(0));

        // Add arcs to transitions
        t1.ajouterArc_ENTRANT(a1);
        t1.ajouterArc_SORTANT(a2);
        t2.ajouterArc_ENTRANT(a3);
        t2.ajouterArc_SORTANT(a4);

        // Add a place to each arc
        a1.setPlace(p1);
        a2.setPlace(p2);
        a3.setPlace(p2);
        a4.setPlace(p3);

        // Add places and transitions to the Petri net
        reseauPetri.ajouterPlace(p1);
        reseauPetri.ajouterPlace(p2);
        reseauPetri.ajouterPlace(p3);
        reseauPetri.ajouterTransition(t1);
        reseauPetri.ajouterTransition(t2);

        // Display the Petri net
        reseauPetri.afficherEtat();
    }
}