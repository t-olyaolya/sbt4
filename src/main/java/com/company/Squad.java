package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Created by tyuly on 21.11.2016.
 * class squad of warriors
 */
public class Squad implements Cloneable {
    List<Warrior> warriors = new ArrayList<>();
    private String name;

    Squad (String name, List<Warrior> warriors) {
        this.warriors = warriors;
        this.name = name;
        warriors.forEach((Warrior warrior) -> warrior.setSquatName(name));
    }

    /**
     *
     * @param i numberof element warriors
     * @return warrior
     */
    public Warrior getWarrior(int i) {
        return warriors.get(i);
    }

    /**
     * method to return random warrior from squad
     * @return Warrior
     */
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
     * @return boolean
     */
    public boolean hasAliveWarriors() {
       /* for (Warrior w:warriors) {
            if (w.isAlive()) {
                return true;
            }
        }
        return false; */
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
            warriors.forEach((Warrior warrior) -> {
                clone.warriors.add(warrior.clone());
            });
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
