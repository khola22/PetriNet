package org.pneditor.petrinet.adapters.Bouzazi;

import org.petriNet.Place;
import org.pneditor.petrinet.AbstractPlace;

public class PlaceAdapter extends AbstractPlace {

    private Place place;

    public PlaceAdapter(Place place) {
        super("Place " + place.getId()); // Using the ID for a unique name
        this.place = place;
    }

    @Override
    public void addToken() {
        place.addTokens(1); // Add a single token to the place
    }

    @Override
    public void removeToken() {
        place.removeTokens(1); // Remove a single token from the place
    }

    @Override
    public int getTokens() {
        return place.getTokenCount(); // Return the current token count
    }

    @Override
    public void setTokens(int tokens) {
        place.setTokenCount(tokens); // Update the token count in the place
    }

    public Place getPlace() {
        return place; // Expose the adapted Place object
    }
}
