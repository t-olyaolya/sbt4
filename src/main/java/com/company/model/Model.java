package com.company.model;

import com.company.view.BattleObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 26.01.2017.
 */
public interface Model {

    String[] getTypeWarriors();

    StringBuilder start(Squad squad1, Squad squad2);

    public Squad createSquad(String name, ArrayList<Warrior> warriors);

    void addWarriors(ArrayList<Warrior> warriors, String item, String name);

    void registerObserver(BattleObserver o);

    void removeObserver(BattleObserver o);

    void notifyObservers();
}
