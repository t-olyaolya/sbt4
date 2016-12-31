package com.company;

import java.util.function.BinaryOperator;

/**
 * Created by tyuly on 21.11.2016.
 */
public interface Warrior{
    /**
     * method to return damage caused by warrior
     * @return int damage
     */
    int attack ();

    /**
     * method to robs damage
     * @param damage the number of health
     */
    void takeDamage (int damage);

    /**
     * method to check alive warrior or not
     * @return boolean
     */
    boolean isAlive();

    void setSquatName(String name);

    void setName(String name);

    String getName();

    Warrior clone();
}
