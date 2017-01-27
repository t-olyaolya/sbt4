package com.company.model;

import com.company.model.Warrior;

/**
 * Created by tyuly on 31.12.2016.
 */
abstract public class WarriorState implements Warrior, Cloneable {
    protected String name;
    protected String squadName;
    protected int health;
    protected int damage;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
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
        return getClass().getName() + " " + name + " from " + squadName;
    }

    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }


}
