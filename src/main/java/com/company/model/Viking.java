package com.company.model;

/**
 * Created by tyuly on 21.11.2016.
 * Viking warrior class
 */
public class Viking extends WarriorState implements Cloneable {

    Viking() {
        health = 100;
        damage = 50;
    }

    Viking(String name){
        this.name = name;
        health = 100;
        damage = 50;
    }

    @Override
    public Viking clone() {
        return (Viking)super.clone();
    }
}
