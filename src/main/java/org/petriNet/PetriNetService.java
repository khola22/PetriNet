package org.petriNet;

public interface PetriNetService {
	
	public void ajouterPlace(Place place);
	
	public void ajouterTransition(Transition transition);
	
	public void ajouterArc(Arc arc);
	
	public void tirerTransition(Transition transition);
	
	public void afficherEtat();

}
