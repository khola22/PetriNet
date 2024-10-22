package org.petriNet;

public abstract class Arc_ENTRANT extends Arc {

    /**
     * Les Arc_ENTRANT sont entrants à une transition et sortants d'une place
     * Ils permettent de retirer des jetons d'une place
     * Ils sont donc la classe abstraite mère des Arc_sortant_simple, Arc_videur et Arc_zero
     * C'est une différence par rapport au diagramme de classe soumis
     */

    private Place place;
    private Transition transition;
    private int poids;
    private int id;

    public Arc_ENTRANT(Place place, Transition transition, int poids, int id) {
        super(place, transition, poids, id);
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
        return this.poids;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setPoid(int poids) {
        this.poids = poids;
    }

    @Override
    public Place getPlace() {
        return this.place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public Transition getTransition() {
        return this.transition;
    }

}