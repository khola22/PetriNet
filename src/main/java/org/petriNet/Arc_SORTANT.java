package org.petriNet;

public abstract class Arc_SORTANT extends Arc {

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