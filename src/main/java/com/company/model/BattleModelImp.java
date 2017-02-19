package com.company.model;

import com.company.view.BattleObserver;

import java.util.*;


public class BattleModelImp implements Model {
    private WarriorFab warriorFab = new WarriorFab();
    public static ArrayList<Warrior> warriors1 = new ArrayList<>();
    public static ArrayList<Warrior> warriors2 = new ArrayList<>();
    private List<BattleObserver> observers = new ArrayList<>();
    private static StringBuilder out = new StringBuilder();
    private static DateHelper dateHelper = new DateHelper();

    @Override
    public String[] getTypeWarriors() {
        return warriorFab.getTypeWarriors();
    }

    @Override
    public void addWarriors(ArrayList<Warrior> warriors, String item, String name) {
        Warrior warrior = warriorFab.newWarrior(item);
        warrior.setName(name);
        warriors.add(warrior);
        out.append("Warrior " + name + " added" + "\n");
        notifyObservers();
    }

    @Override
    public Squad createSquad(String name, ArrayList<Warrior> warriors) {
        return new Squad(name, warriors);
    }

    @Override
    public void createSquadName(String name) {
        out.append(name + " created" + "\n");
        notifyObservers();
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
    public void start(Squad squad1, Squad squad2) {
        out.append("Start battle: " + dateHelper.getFormattedStartData() + "\n");
        int k = 1;
        try {
            while ((squad1.hasAliveWarriors()) || (squad2.hasAliveWarriors())) {
                Warrior war1 = squad1.getRandomWarrior();
                out.append("War1 " + war1.toString() + "\n");
                Warrior war2 = squad2.getRandomWarrior();
                out.append("War2 " + war2.toString() + "\n");
                if (k % 2 != 0) {
                    died(war2, war1);
                } else {
                    died(war1, war2);
                }
                k++;
                dateHelper.skipTime();
                if (!checkAlive(squad1, squad2))
                    break;
                if (!checkAlive(squad2, squad1))
                    break;
            }
            out.append("\n" + "Duration: " + dateHelper.getFormattedDiff() + "\n" );
        } catch (NullPointerException | IllegalArgumentException e) {
            out.append("Заполните отряды");
        }
        finally {
            notifyObservers();
            out = new StringBuilder();
        }
    }

    public boolean checkAlive(Squad squad1, Squad squad2) {
        if (!squad1.hasAliveWarriors()) {
            out.append("\n" + squad2.toString() + " won" + "\n");
            out.append("Final: " + dateHelper.getFormattedFinalData() + "\n");
            notifyObservers();
            return false;
        }
        return true;
    }

    public  void died(Warrior war1, Warrior war2) {
        war1.takeDamage(war2.attack());
        out.append(war2.getName() + " attacks " + war1.getName() + "\n");
        if (!war1.isAlive()) {
            out.append(war1.toString() + " died" + "\n");
        }
        notifyObservers();
    }
}
