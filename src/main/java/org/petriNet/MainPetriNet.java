package org.petriNet;

public class MainPetriNet {

    public static void main(String[] args) {
        // Create a Petri net
        ReseauPerti reseauPerti = new ReseauPerti();

        // Create places
        Place p1 = new Place(1,3);
        Place p2 = new Place(2,3);
        Place p3 = new Place(3,3);

        // Create transitions with empty Arcs_ENTRANT and Arcs_SORTANT
        Transition t1 = new Transition(1, new ArrayList<Arc_ENTRANT>(), new ArrayList<Arc_SORTANT>());
        Transition t2 = new Transition(2, new ArrayList<Arc_ENTRANT>(), new ArrayList<Arc_SORTANT>());

        // Create arcs
        Arc_ENTRANT a1 = new Arc_ENTRANT(p1, t1, 1);
        Arc_SORTANT a2 = new Arc_SORTANT(p2, t1, 1);
        Arc_ENTRANT a3 = new Arc_ENTRANT(p2, t2, 1);
        Arc_SORTANT a4 = new Arc_SORTANT(p3, t2, 1);

        // Add arcs to transitions
        t1.ajouterArc_ENTRANT(a1);
        t1.ajouterArc_SORTANT(a2);
        t2.ajouterArc_ENTRANT(a3);
        t2.ajouterArc_SORTANT(a4);

        // Add arcs to places
        //p1.addArc(a1);
        //p2.addArc(a2);
        //p2.addArc(a3);
        //p3.addArc(a4);

        // Add places and transitions to the Petri net
        reseauPerti.ajouterPlace(p1);
        reseauPerti.ajouterPlace(p2);
        reseauPerti.ajouterPlace(p3);
        reseauPerti.ajouterTransition(t1);
        reseauPerti.ajouterTransition(t2);

        // Display the Petri net
        reseauPerti.afficherEtat();

        // Test the est_tirable method
        System.out.println("t1 est tirable : " + t1.est_tirable());
        System.out.println("t2 est tirable : " + t2.est_tirable());

        // Test the tirer method
        if (t1.est_tirable()) {
            reseauPerti.tirerTransition(t1);
        }
        if (t2.est_tirable()) {
            reseauPerti.tirerTransition(t2);
        }

        // Display the Petri net again
        reseauPerti.afficherEtat();
    }
}