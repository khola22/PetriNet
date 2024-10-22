package org.petriNet;

public class Arc_ENTRANT extends Arc {

    private Place place;
    private Transition transition;
    private int poids;

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
        this.place.enlever_jeton(this.poids);
    }

    public boolean verifier_tirable() {
        if (this.place.get_nombre_jetons() >= this.poids) {
            return true;
        }
        return false;
    }


    @Override
    public void valider() {
        // On retire le nombre de jetons du poids de l'arc
        // prendre en cond la place choisie
        this.place.enlever_jeton(this.poids);
    }



    @Override
    public int getPoids() {
        return 0;
    }

    @Override
    public void setPoid(int poids) {

    }

    @Override
    public Place getPlace() {
        return null;
    }

    @Override
    public void setPlace(Place place) {

    }

    @Override
    public Transition getTransition() {
        return null;
    }

}