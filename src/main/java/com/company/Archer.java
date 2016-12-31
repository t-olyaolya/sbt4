package com.company;

import java.util.function.BinaryOperator;

/**
 * Created by tyuly on 21.11.2016.
 * Archer warrior class
 */
public class Archer extends WarriorState implements Cloneable {

    Archer() {
        health = 80;
        damage = 80;
    }

    Archer(String name) {
        this.name = name;
        health = 80;
        damage = 80;
    }

    @Override
    public Archer clone() {
        return (Archer) super.clone();
    }
}
