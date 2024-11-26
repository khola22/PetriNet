package org.pneditor.petrinet.adapters.Bouzazi;

import org.petriNet.*;
import org.pneditor.petrinet.*;

public class PetriNetAdapter extends PetriNetInterface {

    private PetriNet pn = new PetriNet();

    @Override
    public AbstractPlace addPlace() {
        Place newPlace = new Place(0, pn.generateId(1)); // Initializing with 0 tokens
        pn.addPlace(newPlace); // Adding to the underlying PetriNet
        return new PlaceAdapter(newPlace);
    }

    @Override
    public AbstractTransition addTransition() {
        Transition t = new Transition("Transition", pn.generateId(2));
        pn.addTransition(t); // Adding to the underlying PetriNet
        return new TransitionAdapter(pn, t);
    }

    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) {
        if (source instanceof PlaceAdapter && destination instanceof TransitionAdapter) {
            Place place = ((PlaceAdapter) source).getPlace();
            Transition transition = ((TransitionAdapter) destination).getTransition();
            Arc arc = new IncomingArc_Simple(transition, place, 1, pn.generateId(3));
            pn.addArc(arc); // Add to the PetriNet
            return new ArcAdapter(arc);
        } else if (source instanceof TransitionAdapter && destination instanceof PlaceAdapter) {
            Transition transition = ((TransitionAdapter) source).getTransition();
            Place place = ((PlaceAdapter) destination).getPlace();
            Arc arc = new OutgoingArc(transition, place, 1, pn.generateId(3));
            pn.addArc(arc); // Add to the PetriNet
            return new ArcAdapter(arc);
        } else {
            // Handle invalid case without throwing exception, just return null or log
            System.out.println("Invalid source and destination for regular arc.");
            return null;
        }
    }

    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) {
        if (place instanceof PlaceAdapter && transition instanceof TransitionAdapter) {
            Place p = ((PlaceAdapter) place).getPlace();
            Transition t = ((TransitionAdapter) transition).getTransition();
            Arc arc = new IncomingArc_Zero(t, p, 1, pn.generateId(3));
            pn.addArc(arc); // Add to the PetriNet
            return new ArcAdapter(arc);
        } else {
            System.out.println("Invalid place or transition for inhibitory arc.");
            return null;
        }
    }

    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) {
        if (place instanceof PlaceAdapter && transition instanceof TransitionAdapter) {
            Place p = ((PlaceAdapter) place).getPlace();
            Transition t = ((TransitionAdapter) transition).getTransition();
            Arc arc = new IncomingArc_Videur(t, p, 1, pn.generateId(3));
            pn.addArc(arc); // Add to the PetriNet
            return new ArcAdapter(arc);
        } else {
            System.out.println("Invalid place or transition for reset arc.");
            return null;
        }
    }

    @Override
    public void removePlace(AbstractPlace place) {
        if (place instanceof PlaceAdapter) {
            Place p = ((PlaceAdapter) place).getPlace();
            pn.removePlace(p); // Remove the place from the PetriNet
        }
    }

    @Override
    public void removeTransition(AbstractTransition transition) {
        if (transition instanceof TransitionAdapter) {
            Transition t = ((TransitionAdapter) transition).getTransition();
            pn.removeTransition(t); // Remove the transition from the PetriNet
        }
    }

    @Override
    public void removeArc(AbstractArc arc) {
        if (arc instanceof ArcAdapter) {
            Arc a = ((ArcAdapter) arc).getArc();
            pn.removeArc(a); // Remove the arc from the PetriNet
        }
    }

    @Override
    public boolean isEnabled(AbstractTransition transition) {
        if (transition instanceof TransitionAdapter) {
            Transition t = ((TransitionAdapter) transition).getTransition();
            return t.canFire();
        }
        return false;
    }

    @Override
    public void fire(AbstractTransition transition) {
        if (transition instanceof TransitionAdapter) {
            Transition t = ((TransitionAdapter) transition).getTransition();
            t.fire(); // Execute the transition in the underlying PetriNet
        }
    }
}
