package org.petriNet;

    public class Arc_zero extends Arc_ENTRANT {

    Place place;
    Transition transition;
    int poids;
    int id;
    boolean etat;


    public Arc_zero(Place place, Transition transition, int poids, int id) {
        super(place, transition, poids, id);
    }

    // Les arcs « zéro » qui ne sont actifs que quand la place source est vide.

    @Override
    public boolean verifier_tirable() {
        if (this.place.get_nombre_jetons() == 0) {
            return true;
        }
        return false;
    }
}
