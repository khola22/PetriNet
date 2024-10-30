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

    public Arc_ENTRANT( Transition transition, Place place, int poids, int id) {
        super(transition, place, poids, id);
    }

    /**
     * Dans la classe Arc_ENTRANT, on enlève les jetons à la place seulement,
     *  il n'y a pas de jetons à ajouter
     *  ceci pour justifier le cahngement par rapport au diagramme de classe soumis
     */

    public boolean verifier_tirable() {
        return this.getPlace().get_nombre_jetons() >= this.getPoids();
    }

    @Override
    public void valider() {
        // On retire le nombre de jetons du poids de l'arc
        this.getPlace().enlever_jeton(this.getPoids());
    }

}