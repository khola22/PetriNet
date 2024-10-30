package org.petriNet;

public class Arc_SORTANT extends Arc {

    private Place place;
    private Transition transition;
    private int poids;
    private int id;

    public Arc_SORTANT( Transition transition, Place place, int poids, int id) {
        super(transition, place, poids, id);
    }


    @Override
    public void valider() {
        // On ajoute le nombre de jetons du poids de l'arc
        this.place.ajouter_jeton(this.poids);
    }

}