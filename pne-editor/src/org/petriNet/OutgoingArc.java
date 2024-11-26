package org.petriNet;

public class OutgoingArc extends Arc {

    public OutgoingArc(Transition transition, Place place, int weight, int id) {
        super(transition, place, weight, id);
    }


    @Override
    public void validate() {
        this.getPlace().addTokens(this.getWeight());
    }

}