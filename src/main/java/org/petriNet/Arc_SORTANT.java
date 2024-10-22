package org.petriNet;

public class Arc_SORTANT extends Arc {

    private Place place;
    private Transition transition;
    private int poids;
    private int id;

    public Arc_SORTANT(Place place, Transition transition, int poids, int id) {
        super(place, transition, poids, id);
    }

    @Override
    public void modifierPoids(int poids) {
        this.poids = poids;
    }



    @Override
    public void valider() {
        // On ajoute le nombre de jetons du poids de l'arc
        this.place.ajouter_jeton(this.poids);
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

    /**
     * Dans la classe Arc_SORTANT, on ajoute les jetons à la place seulement,
     *  il n'y a pas de jetons à enlever
     *  ceci pour justifier le cahngement par rapport au diagramme de classe soumis
     */


    public void ajouterJetons() {
        // On ajoute le nombre de jetons du poids de l'arc
        this.place.ajouter_jeton(this.poids);
    }


}