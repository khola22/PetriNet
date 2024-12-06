package org.pneditor.petrinet.adapters.Bouzazi;

import org.petriNet.PetriNet;
import org.petriNet.Transition;
import org.pneditor.petrinet.AbstractTransition;

public class TransitionAdapter extends AbstractTransition {
	
	private final Transition transition;

	public TransitionAdapter(PetriNet pn, Transition t) {
		super(t.getName()); // Initialize the AbstractTransition with the name from Transition
		this.transition = t;
	}

	// Expose the wrapped Transition
	public Transition getTransition() {
		return transition;
	}

	@Override
	public String getLabel() {
		return transition.getName();
	}

	@Override
	public void setLabel(String label) {
		transition.setName(label);
	}
}
