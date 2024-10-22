package org.petriNet;

import java.util.ArrayList;

public class MainPetriNet {

    public static void main(String[] args) {
        // Create a Petri net
        ReseauPerti reseauPerti = new ReseauPerti();

        // Create places
        Place p1 = new Place(1,3);
        Place p2 = new Place(2,3);
        Place p3 = new Place(3,3);

        // Create transitions with empty Arcs_ENTRANT and Arcs_SORTANT
        Transition t1 = new Transition(1, new ArrayList<Arc_SORTANT>(), new ArrayList<Arc_ENTRANT>());
        Transition t2 = new Transition(2, new ArrayList<Arc_SORTANT>(), new ArrayList<Arc_ENTRANT>());

        // Create arcs
        Arc_ENTRANT a1 = new Arc_entrant_simple(p1, t1, 1, 1);
        Arc_SORTANT a2 = new Arc_SORTANT(p2, t1, 1, 2);
        Arc_ENTRANT a3 = new Arc_entrant_simple(p2, t2, 1, 3);
        Arc_SORTANT a4 = new Arc_SORTANT(p3, t2, 1, 4);

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
        reseauPerti.ajouterPlace(p1);
        reseauPerti.ajouterPlace(p2);
        reseauPerti.ajouterPlace(p3);
        reseauPerti.ajouterTransition(t1);
        reseauPerti.ajouterTransition(t2);

        // Display the Petri net
        reseauPerti.afficherEtat();

        // Test the tirer method
//        if (t1.est_tirable()) {
//            reseauPerti.tirerTransition(t1);
//        }
//        if (t2.est_tirable()) {
//            reseauPerti.tirerTransition(t2);
//        }

    }
}