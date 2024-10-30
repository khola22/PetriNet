package org.petriNet;

public interface PetriNetService {
	
	public void ajouterPlace(Place place);
	
	public void ajouterTransition(Transition transition);
	
	public void ajouterArc(Arc arc);

	public void supprimerPlace(Place place);

	public void supprimerTransition(Transition transition);

	public void supprimerArc(Arc arc);
	
	public void afficherEtat();

	/**
	 * afficher le réseau de petri
	 * On a trouver que cette fonction et utile pour visualiser
	 * le réseau de petri
	 */
	public void afficherReseau();

	public void tirer_transition(String id);

}
