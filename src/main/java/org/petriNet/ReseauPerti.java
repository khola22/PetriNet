package org.petriNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReseauPerti implements PetriNetService {

	// Creat attributes as Lists of Places, Transitions and Arcs

	private List<Place> places;
	private List<Transition> transitions;
	private List<Arc> arcs;
	private String etat_reseau = "Pas de transition tirée";

	// Create a constructor
	public ReseauPerti() {
		// initialiser les listes vide
		this.places = new ArrayList<>();
		this.transitions = new ArrayList<>();
		this.arcs = new ArrayList<>();
	}

	// Create getters and setters
	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public  List<Arc> getArcs() {
		return this.arcs;
	}

	public void setArcs(List<Arc> arcs) {
		this.arcs = arcs;
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
	public void tirer_transition(String id) {

		// set the state of the network to "Transition en cours de verification"
		this.etat_reseau = "Transition en cours de validation";

		// Find the transition with the id
		Transition transition_choisie = null;
		for (Transition transition : this.transitions) {
			if (transition.getId() == Integer.parseInt(id)) {
				transition_choisie = transition;
			}
		}


		// Check if the transition id is valid
		if (transition_choisie == null) {
			System.out.println("L'id de la transition n'est pas valide");
			return;
		} else {
			// Check if the transition is tirable
			transition_choisie.est_tirable();
		}

		// Ask if the user wants to validate the firing of the transition
		Scanner scanner = new Scanner(System.in);
		System.out.println("Voulez-vous valider le tirage de la transition? (O/N)");
		String reponse = scanner.nextLine();
		if (reponse.equals("O")) {
			transition_choisie.tirer();
		}

		// set the state of the network to "Pas de transition tirée"
		this.etat_reseau = "Pas de transition tirée";

	}

	@Override
	public void afficherEtat() {
		// TODO Auto-generated method stub
		if (this.etat_reseau.equals("Pas de transition tirée")) {
			System.out.println("Pas de transition tirée");
			// Ask the user if they want to fire a transition
			Scanner scanner = new Scanner(System.in);
			System.out.println("Voulez-vous tirer une transition? (O/N)");
			String reponse = scanner.nextLine();
			if (reponse.equals("O")) {
				// Show the Petri Net
				afficherReseau();
				// Ask the user for the id of the transition to fire
				System.out.println("Saisir l'id de la transition à tirer: ");
				String id = scanner.nextLine();
				tirer_transition(id);
			}
		} else {
			System.out.println("Transition en cours de validation");
		}
	}


	@Override
	public void afficherReseau() {
		// TODO Auto-generated method stub

		System.out.println(this.etat_reseau);

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

}
