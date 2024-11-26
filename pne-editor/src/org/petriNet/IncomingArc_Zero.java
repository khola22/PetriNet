package org.petriNet;

    public class IncomingArc_Zero extends IncomingArc {

    public IncomingArc_Zero(Transition transition, Place place, int weight, int id) {
        super(transition, place, weight, id);
    }

    @Override
    public boolean canFire() {
        return this.getPlace().getTokenCount() == 0;
    }
}
