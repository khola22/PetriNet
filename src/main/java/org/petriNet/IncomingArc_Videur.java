package org.petriNet;

public class IncomingArc_Videur extends IncomingArc {

    public IncomingArc_Videur(Transition transition, Place place, int poids, int id) {
        super(transition, place, poids, id);
    }

    // Les arcs «videurs» qui sont actifs dès qu’il y a un jeton dans la place source et qui enlèvent
    // tous les jetons présents lorsqu’ils sont activés.

    @Override
    public boolean canFire() {
        return this.getPlace().getTokenCount() > 0;
    }

    @Override
    public void validate() {
        // On retire le nombre de jetons du poids de l'arc
        // prendre en cond la place choisie
        this.getPlace().removeTokens(this.getPlace().getTokenCount());
    }


}
