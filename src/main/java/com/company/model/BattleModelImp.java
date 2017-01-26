package com.company.model;

import com.company.view.BattleObserver;

import java.util.*;


public class BattleModel implements Model {
    public static ArrayList<Warrior> warriors1 = new ArrayList<>();
    public static ArrayList<Warrior> warriors2 = new ArrayList<>();
    private List<BattleObserver> observers = new ArrayList<>();
    private static List<String> out = new ArrayList<>();
    private static DateHelper dateHelper = new DateHelper();
    private String[] typeWarriors = {"Viking", "Archer"};

    public String [] getTypeWarriors () {
        return typeWarriors;
    }


    @Override
    public void addWarriors(ArrayList<Warrior> warriors, String item, String name) {
        Warrior warrior = null;
        if (item.equals(typeWarriors[0])) {
            warrior = new Viking(name);
        }
        if (item.equals(typeWarriors[1])) {
            warrior = new Archer(name);
        }
        warriors.add(warrior);
    }

    public Squad createSquad(String name, ArrayList<Warrior> warriors) {
        return new Squad(name, warriors);
    }

    @Override
    public void registerObserver(BattleObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(BattleObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach((battleObserver) -> battleObserver.update(out.toString()));
    }

    @Override
    public List<String> start(Squad squad1, Squad squad2) {
        out.add("Start battle: " + dateHelper.getFormattedStartData() + "\n");
        int k = 1;
        try {
            while ((squad1.hasAliveWarriors()) || (squad2.hasAliveWarriors())) {
                Warrior war1 = squad1.getRandomWarrior();
                out.add("War1 " + war1.toString());
                Warrior war2 = squad2.getRandomWarrior();
                out.add("War2 " + war2.toString());
                if (k % 2 != 0) {
                    BattleModel.died(war2, war1);
                } else {
                    BattleModel.died(war1, war2);
                }
                k++;
                dateHelper.skipTime();
                if (!BattleModel.checkAlive(squad1, squad2))
                    break;
                if (!BattleModel.checkAlive(squad2, squad1))
                    break;
            }
            out.add("\n" + "Duration: " + dateHelper.getFormattedDiff());
        } catch (NullPointerException | IllegalArgumentException e) {
            out.add("Заполните отряды");
        }
        return out;
    }

    public static boolean checkAlive(Squad squad1, Squad squad2) {
        if (!squad1.hasAliveWarriors()) {
            out.add("\n" + squad2.toString() + " won");
            out.add("Final: " + dateHelper.getFormattedFinalData());
            return false;
        }
        return true;
    }

    public static void died(Warrior war1, Warrior war2) {
        war1.takeDamage(war2.attack());
        out.add(war2.getName() + " attacks " + war1.getName());
        if (!war1.isAlive()) {
            out.add(war1.toString() + " died");
        }
    }

}
