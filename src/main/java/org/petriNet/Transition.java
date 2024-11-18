package org.petriNet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Transition {

    private final int id;
    String name;
    private LinkedList<OutgoingArc> outgoingArcs;
    private LinkedList<IncomingArc> incomingArcs;

    public Transition(String name, int id) {
        this.id = id;
        this.name = name;
        this.outgoingArcs = new LinkedList<>();
        this.incomingArcs = new LinkedList<>();
    }

    /**
     * We add two methods to be able to add incoming and outgoing arcs to the transition.
     * This is a difference from the submitted class diagram.
     */

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<OutgoingArc> getOutgoingArcs() {
        return outgoingArcs;
    }

    public List<IncomingArc> getIncomingArcs() {
        return incomingArcs;
    }

    public void setOutgoingArcs(LinkedList<OutgoingArc> outgoingArcs) {
        this.outgoingArcs = outgoingArcs;
    }

    public void setIncomingArcs(LinkedList<IncomingArc> incomingArcs) {
        this.incomingArcs = incomingArcs;
    }

    public void addOutgoingArc(OutgoingArc outgoingArc) {
        // verify that the arc doesn't already exist
        for (OutgoingArc existingArc : outgoingArcs) {
            if (existingArc.getPlace().getId() == outgoingArc.getPlace().getId() &&
                    existingArc.getTransition().getId() == outgoingArc.getTransition().getId() &&
                    existingArc.getClass() == outgoingArc.getClass()) {
                System.out.println("The arc already exists.");
                return;
            }
        }
        this.outgoingArcs.add(outgoingArc);
    }

    public void addIncomingArc(IncomingArc incomingArc) {
        // verify that the arc doesn't already exist
        for (IncomingArc existingArc : incomingArcs) {
            if (existingArc.getPlace().getId() == incomingArc.getPlace().getId() &&
                    existingArc.getTransition().getId() == incomingArc.getTransition().getId() &&
                    existingArc.getClass() == incomingArc.getClass()) {
                System.out.println("The arc already exists.");
                return;
            }
        }
        this.incomingArcs.add(incomingArc);
    }

    public boolean canFire() {
        for (IncomingArc incomingArc : this.incomingArcs) {
            if (!incomingArc.canFire()) {
                System.out.println("The transition cannot fire");
                return false;
            }
        }
        System.out.println("The transition can fire");
        return true;
    }

    public void fire() {
        boolean canFire = canFire();

        if (canFire) {
            for (IncomingArc incomingArc : this.getIncomingArcs()) {
                incomingArc.validate();
            }

            for (OutgoingArc outgoingArc : this.getOutgoingArcs()) {
                outgoingArc.validate();
            }
            System.out.println("Transition fired successfully");
        }
    }
}
