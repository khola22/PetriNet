package org.petriNet;

public class Place {

    private final int id;
    private int tokenCount;

    public Place(int tokenCount, int id) {
        this.id = id;
        // VERIFY THAT THE NUMBER OF TOKENS IS NOT NEGATIVE
        if (tokenCount >= 0) {
            this.tokenCount = tokenCount;
        } else {
            System.out.println("The number of tokens cannot be negative.");
        }
    }

    public void addTokens(int tokens) {
        // verify that the number of tokens is not negative
        if (tokens >= 0) {
            this.tokenCount += tokens;
        } else {
            System.out.println("The number of tokens cannot be negative.");
        }
    }

    public void removeTokens(int tokens) {
        // verify that the number of tokens is not negative
        if (tokens >= 0) {
            this.tokenCount = Math.max(0, this.tokenCount - tokens);
        } else {
            System.out.println("The number of tokens cannot be negative.");
        }
    }

    public int getTokenCount() {
        return this.tokenCount;
    }

    public int getId() {
        return id;
    }

    public void setTokenCount(int tokenCount) {
        if (tokenCount < 0) {
            System.out.println("Token count cannot be negative.");
            this.tokenCount = 0;
        } else {
            this.tokenCount = tokenCount;
        }
    }

}
