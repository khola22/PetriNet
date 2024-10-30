package org.petriNet;

import java.util.*;

public class ReseauPetri implements PetriNetService {

	// Creat attributes as Lists of Places, Transitions and Arcs

	private LinkedList<Integer> Id_couters;
	private List<Place> places;
	private List<Transition> transitions;
	// create a LinkedHashSet to store the arcs in order to have unique ids
	private LinkedList<Arc> arcs;
	private String etat_reseau = "Pas de transition tirée";

	// Create a constructor
	public ReseauPetri() {
		// initialiser les listes vide
		this.places = new ArrayList<>();
		this.transitions = new ArrayList<>();
		this.arcs = new LinkedList<>();
		Id_couters = new LinkedList<>();

		// initialiser les compteurs des ids pour Arcs, Places et Transitions
		Id_couters.add(0);
		Id_couters.add(0);
		Id_couters.add(0);
	}

	// Create a method to generate unique ids
	public int generateId(int index) {
		int id = Id_couters.get(index);
		Id_couters.set(index, id + 1);
		return id;
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

	public  LinkedList<Arc> getArcs() {
		return this.arcs;
	}

	public void setArcs(LinkedList arcs) {
		this.arcs = arcs;
	}


	@Override
	public void ajouterPlace(Place place) {
		// TODO Auto-generated method stub
		// verify that there is no similar place in the list
		for (Place place1 : this.places) {
			if (place1.getId() == place.getId()) {
				System.out.println("Place already exists");
				return;
			}
		}
		this.places.add(place);
	}

	@Override
	public void ajouterTransition(Transition transition) {
		// TODO Auto-generated method stub
		// verify that there is no similar transition in the list
		for (Transition transition1 : this.transitions) {
			if (transition1.getId() == transition.getId()) {
				System.out.println("Transition already exists");
				return;
			}
		}
		this.transitions.add(transition);
	}

	@Override
	public void ajouterArc(Arc arc) {
		// TODO Auto-generated method stub
		// verify that there is no similar arc in the list
		for (Arc arc1 : this.arcs) {
			if (arc1.getPlace().getId() == arc.getPlace().getId() &&
					arc1.getTransition().getId() == arc.getTransition().getId() &&
					arc1.getClass() == arc.getClass()) {

				System.out.println("Arc already exists");
				return;
			}
		}
		this.arcs.add(arc);
	}

	/**
	 * Supprimer une place entraîne la suppression de tous les arcs qui y sont liés.
	 * Supprimer une transition entraîne la suppression de tous les arcs qui y sont liés.
	 */

	@Override
	public void supprimerPlace(Place place) {
		this.places.remove(place);
		// remove all arcs linked to the place
        this.arcs.removeIf(arc -> arc.getPlace().getId() == place.getId());
	}

	@Override
	public void supprimerTransition(Transition transition) {
		this.transitions.remove(transition);
		// remove all arcs linked to the transition
		this.arcs.removeIf(arc -> arc.getTransition().getId() == transition.getId());
	}

	@Override
	public void supprimerArc(Arc arc) {
		this.arcs.remove(arc);
		// remove it from the list of arcs of the transition
		if (arc instanceof Arc_ENTRANT) {
			arc.getTransition().getArcs_ENTRANTS().remove(arc);
		} else if (arc instanceof Arc_SORTANT) {
			arc.getTransition().getArcs_SORTANTS().remove(arc);
		}
	}

	@Override
	public void tirer_transition(String id) {

		// set the state of the network to "Transition en cours de verification"
		this.etat_reseau = "Transition en cours de validation";

		// Find the transition with the id
		Transition transition_choisie = null;
		for (Transition transition : this.transitions) {
			if (String.valueOf(transition.getId()).equals(id)) {
				transition_choisie = transition;
			}
		}

		// Check if the transition id is valid
		if (transition_choisie == null) {
			System.out.println("L'id de la transition n'est pas valide");
			return;
		} else {
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

		/**
		 * L'affichage est sous la forme suivante:
		 * Réseau de Petri
		 * 2 places
		 * 1 transition
		 * 2 arcs
		 * Liste des places :
		 * 1 : place avec 4 jetons, 1 arc simple sortant, 0 arc simple entrant
		 * 2 : place avec 0 jetons, 0 arc simple sortant, 1 arc simple entrant
		 * Liste des transitions
		 * 1 : transition, 1 arc entrant, 1 arc sortant
		 * Liste des arcs :
		 * 1 : arc simple poids 1 (place avec 4 jetons vers transition)
		 * 2 : arc simple poids 1 (transition vers place avec 0 jetons)
		 */

		System.out.println("Réseau de Petri");
		System.out.println(this.places.size() + " places");
		System.out.println(this.transitions.size() + " transitions");
		System.out.println(this.arcs.size() + " arcs");

		System.out.println("Liste des places :");
			// On parcourt la liste des arcs pour afficher les places
			// une liste pur les arcs entrants et une liste pour les arcs sortants
		for (Place place : this.places) {
			List<Arc> arcs_ENTRANTS = new ArrayList<>();
			List<Arc> arcs_SORTANTS = new ArrayList<>();
			for (Arc arc : this.arcs) {
				// we verify getting arc.getPlace() == place
				if (arc.getPlace().getId() == place.getId()) {
					// we verify if it's an arc entrant
					if (arc instanceof Arc_ENTRANT) {
						arcs_ENTRANTS.add(arc);
					} else if (arc instanceof Arc_SORTANT) {
						arcs_SORTANTS.add(arc);
					}
				}
			}
			// entrants / sortants à la place
			System.out.println(place.getId() + " : place avec " + place.get_nombre_jetons() + " jetons, "
					+ arcs_ENTRANTS.size() + " arc simple sortant, " + arcs_SORTANTS.size() + " arc simple entrant");
		}

		System.out.println("Liste des transitions :");
		for (Transition transition : this.transitions) {
			System.out.println(transition.getId() + " : transition " + transition.getNom() + " " + transition.getArcs_ENTRANTS().size()
					+ " arc entrant, " + transition.getArcs_SORTANTS().size() + " arc sortant");
		}

		System.out.println("Liste des arcs :");
		for (Arc arc : this.arcs) {
			if (arc instanceof Arc_ENTRANT) {
				System.out.println(arc.getId() + " : arc simple poids " + arc.getPoids() + " ("
						+ "Place d'Id " + arc.getPlace().getId() + " vers " + arc.getTransition().getNom() + ")");
			} else {
				System.out.println(arc.getId() + " : arc simple poids " + arc.getPoids() + " ("
						+ arc.getTransition().getNom()  + " vers " + "Place d'Id " + arc.getPlace().getId()  + ")");
			}
		}
	}

}
