package org.petriNet;

public class Arc_videur extends Arc_ENTRANT {

    Place place;
    Transition transition;
    int poids;
    int id;

    public Arc_videur(Place place, Transition transition, int poids, int id) {
        super(place, transition, poids, id);
    }

    // Les arcs «videurs» qui sont actifs dès qu’il y a un jeton dans la place source et qui enlèvent
    // tous les jetons présents lorsqu’ils sont activés.

    @Override
    public boolean verifier_tirable() {
        if (this.place.get_nombre_jetons() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void valider() {
        // On retire le nombre de jetons du poids de l'arc
        // prendre en cond la place choisie
        this.place.enlever_jeton(this.place.get_nombre_jetons());
    }



}
