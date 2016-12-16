package com.company;

import java.util.function.BinaryOperator;

/**
 * Created by tyuly on 21.11.2016.
 * Archer warrior class
 */
public class Archer implements Warrior, Cloneable {
    private String name;
    private String squadName;
    private int health;
    private int damage;

    Archer(String name){
        this.name = name;
        health = 80;
        damage = 80;
    }

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        health = health - damage;
    }

    @Override
    public boolean isAlive() {
       return health > 0;
    }

    @Override
    public void setSquatName(String name) {
        squadName = name;
    }

    @Override
    public String toString() {
        return getClass().getName() +" " + name + " from " + squadName;
    }

    @Override
    public Archer clone() {
        try {
            return (Archer) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
