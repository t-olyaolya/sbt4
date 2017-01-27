package com.company.model;

/**
 * Created by tyuly on 27.01.2017.
 */
public class WarriorFab {
    private String[] typeWarriors = {"Viking", "Archer"};

    public String [] getTypeWarriors () {
        return typeWarriors;
    }

    public Warrior newWarrior(String type) {
        if (type.equals(typeWarriors[0])) {
            return new Viking();
        }
        if (type.equals(typeWarriors[1])) {
            return new Archer();
        }
        return null;
    }
}
