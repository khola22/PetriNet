package org.petriNet;

public interface PetriNetService {

	void addPlace(Place place);

	void addTransition(Transition transition);

	void addArc(Arc arc);

	void removePlace(Place place);

	void removeTransition(Transition transition);

	void removeArc(Arc arc);

	Place FindPlaceById(int id);

	Arc FindArcById(int id);

	void displayState();

	/**
	 * Display the Petri net
	 * We found that this function is useful for visualizing
	 * the Petri net
	 */
	void displayNetwork();

	void fireTransition(String id);
}
