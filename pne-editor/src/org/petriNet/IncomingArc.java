package org.petriNet;

public abstract class IncomingArc extends Arc {

    /**
     * IncomingArc are incoming to a transition and outgoing from a place.
     * They allow tokens to be removed from a place.
     * They are therefore the abstract parent class of OutgoingArc_Simple, IncomingArc_Videur, and IncomingArc_Zero.
     * This is a difference from the submitted class diagram.
     */

    public IncomingArc(Transition transition, Place place, int weight, int id) {
        super(transition, place, weight, id);
    }

    /**
     * In the IncomingArc class, tokens are only removed from the place,
     * there are no tokens to add.
     * This justifies the change from the submitted class diagram.
     */

    public boolean canFire() {
        return this.getPlace().getTokenCount() >= this.getWeight();
    }

    @Override
    public void validate() {
        // Remove the number of tokens equal to the weight of the arc
        this.getPlace().removeTokens(this.getWeight());
    }
}