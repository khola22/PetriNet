package org.petriNet;

public class Arc {
    protected int poid;  // weight
    protected int id;

    public Arc(int poid,int id) {
        this.poid = poid;
        this.id = id;
    }

    public void modifier_poid(int nouveauPoid) {
        this.poid = nouveauPoid;
    }

    // Validation method (abstract in derived classes)
    public boolean valider() {
        // Implement validation logic if needed
        return true;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }
}

}
