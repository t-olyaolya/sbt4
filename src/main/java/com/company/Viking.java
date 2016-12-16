package com.company;

/**
 * Created by tyuly on 21.11.2016.
 * Viking warrior class
 */
public class Viking implements Warrior, Cloneable {
    private String name;
    private String squadName;
    private int health;
    private int damage;

    Viking(String name){
        this.name = name;
        health = 100;
        damage = 50;
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
    public Viking clone() {
        try {
            return (Viking)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
