package org.petriNet;

    public class Arc_zero extends Arc_ENTRANT {

    Place place;
    Transition transition;
    int poids;
    boolean etat;
    int id;


    public Arc_zero( Transition transition, Place place, int poids, int id) {
        super(transition, place, poids, id);
    }

        // Les arcs « zéro » qui ne sont actifs que quand la place source est vide.

    @Override
    public boolean verifier_tirable() {
        return this.place.get_nombre_jetons() == 0;
    }
}
