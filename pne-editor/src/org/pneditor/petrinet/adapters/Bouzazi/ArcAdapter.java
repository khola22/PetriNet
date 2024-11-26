package org.pneditor.petrinet.adapters.Bouzazi;

import org.petriNet.Arc;
import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;

public class ArcAdapter extends AbstractArc {

    private Arc arc;

    public ArcAdapter(Arc arc) {
        this.arc = arc;
    }

    @Override
    public AbstractNode getSource() {
        return new PlaceAdapter(arc.getPlace());
    }

    @Override
    public AbstractNode getDestination() {
        return new TransitionAdapter(null, arc.getTransition());
    }

    @Override
    public boolean isReset() {
        return arc instanceof org.petriNet.IncomingArc_Videur;
    }

    @Override
    public boolean isRegular() {
        return arc instanceof org.petriNet.IncomingArc_Simple;
    }

    @Override
    public boolean isInhibitory() {
        return arc instanceof org.petriNet.IncomingArc_Zero;
    }

    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
        if (isReset()) {
            throw new ResetArcMultiplicityException();
        }
        return arc.getWeight();
    }

    @Override
    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        if (isReset()) {
            throw new ResetArcMultiplicityException();
        }
        arc.setWeight(multiplicity);
    }

    public Arc getArc() {
        return arc;
    }
}
