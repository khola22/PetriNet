package org.petriNet;

public class Arc {
    protected int poid;  
    protected int id;

    public Arc(int poid,int id) {
        this.poid = poid;
        this.id = id;
    }

    public void modifier_poid(int nouveauPoid) {
        this.poid = nouveauPoid;
    }

    public boolean valider() {
        return true;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }
}


