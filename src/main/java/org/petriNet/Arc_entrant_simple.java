package org.petriNet;

public class Arc_entrant_simple extends Arc_ENTRANT {

        Place place;
        Transition transition;
        int poids;
        int id;


    public Arc_entrant_simple( Transition transition, Place place, int poids, int id) {
        super(transition, place, poids, id);
    }

}
