package org.petriNet;

public abstract class Arc_SORTANT extends Arc {
    public Arc_SORTANT(Place place, Transition transition, int poids) {
        super(place, transition, poids);
    }

    @Override
    public void modifierPoids(int poids) {
        this.poids = poids;
    }

    @Override
    public void valider() {
        // On ajoute le nombre de jetons du poids de l'arc
        this.place.ajouterJetons(this.poids);
    }

    /**
     * Dans la classe Arc_SORTANT, on ajoute les jetons à la place seulement,
     *  il n'y a pas de jetons à enlever
     *  ceci pour justifier le cahngement par rapport au diagramme de classe soumis
     */


    public void ajouterJetons() {
        // On ajoute le nombre de jetons du poids de l'arc
        this.place.ajouterJetons(this.poids);
    }


}