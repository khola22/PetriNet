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
		// afficher le nombre de jetons dans chaque place
		for (Place place : places) {
			System.out.println("Place " + place.getId() + " : " + place.getNbrJetons());
		}
	}

	@Override
	public void afficherReseauPetri() {
		// TODO Auto-generated method stub

		/**
		 * afficher les places, les transitions et les arcs
		 * comme un dessin du réseau de petri
		 * les places comme des cercles
		 * les transitions comme des rectangles
		 * les arcs comme des flèches
		 * les poinds des arcs à côté des flèches
		 * les jetons des points dans les places
		 * relier les arcs aux places et aux transitions par des flèches
		 */

		// afficher Petri Net
		System.out.println("Petri Net : ");
		// afficher les places, les arcs, le poid et les transitions
		for (Arc arc : arcs) {
			System.out.println("Arc : " + arc.getPlace().getId() + " -> " + arc.getTransition().getId() + " : " + arc.getPoids());
		}



}
