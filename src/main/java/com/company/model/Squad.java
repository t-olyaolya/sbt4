package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tyuly on 21.11.2016.
 * class squad of warriors
 */
public class Squad implements Cloneable {
    List<Warrior> warriors = new ArrayList<>();
    private String name;

    public Squad(String name, List<Warrior> warriors) {
        this.warriors = warriors;
        this.name = name;
        warriors.forEach((Warrior warrior) -> warrior.setSquatName(name));
    }

    public Warrior getWarrior(int i) {
        return warriors.get(i);
    }

    public Warrior getRandomWarrior() {
        Random random = new Random();
        int k;
        do {
            k = random.nextInt(warriors.size());
        }
        while (!warriors.get(k).isAlive());
        return warriors.get(k);
    }

    /**
     * method to check has alive warriors
     *
     * @return boolean
     */
    public boolean hasAliveWarriors() {
        return warriors.stream().anyMatch(Warrior::isAlive);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Squad clone() {
        try {
            Squad clone = (Squad) super.clone();
            clone.warriors = new ArrayList<Warrior>(warriors.size());
            for (Warrior w : warriors) {
                clone.warriors.add(w.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
