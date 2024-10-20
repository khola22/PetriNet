package org.petriNet;

public class ReseauPerti implements PetriNetService {

	// Creat attributes as Lists of Places, Transitions and Arcs

	private List<Place> places;
	private List<Transition> transitions;
	private List<Arc> arcs;

	// Create a constructor
	public ReseauPerti() {
		// initialiser les listes vide
		this.places = new ArrayList<Place>();
		this.transitions = new ArrayList<Transition>();
		this.arcs = new ArrayList<Arc>();
	}



	@Override
	public void ajouterPlace(Place place) {
		// TODO Auto-generated method stub
		this.places.add(place);
	}

	@Override
	public void ajouterTransition(Transition transition) {
		// TODO Auto-generated method stub
		this.transitions.add(transition);
	}

	@Override
	public void ajouterArc(Arc arc) {
		// TODO Auto-generated method stub
		this.arcs.add(arc);
	}

	@Override
	public void tirerTransition(Transition transition) {
		// TODO Auto-generated method stub
		// si la transition est tirable
		if (transition.est_tirable()) {
			// tirer la transition
			transition.tirer();
		}
	}

	@Override
	public void afficherEtat() {
		// TODO Auto-generated method stub
		
	}
	
	
}
