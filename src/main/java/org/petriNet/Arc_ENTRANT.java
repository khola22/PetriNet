package org.petriNet;

public class Arc_ENTRANT extends Arc {

    public Arc_ENTRANT(Place place, Transition transition, int poids) {
        super(place, transition, poids);
    }

    @Override
    public void modifierPoids(int poids) {
        this.poids = poids;
    }

    /**
     * Dans la classe Arc_ENTRANT, on enlève les jetons à la place seulement,
     *  il n'y a pas de jetons à ajouter
     *  ceci pour justifier le cahngement par rapport au diagramme de classe soumis
     */

    public void enleverJetons() {
        // On retire le nombre de jetons du poids de l'arc
        this.place.enleverJetons(this.poids);
    }

    @Override
    public void valider() {
        // On retire le nombre de jetons du poids de l'arc
        this.place.enleverJetons(this.poids);
    }

}