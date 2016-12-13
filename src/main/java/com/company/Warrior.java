package com.company;

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

    /**
     *method to set squad name
     * @param name name squad
     */
    void setSquatName(String name);

    //@Override
    Warrior clone();
}
