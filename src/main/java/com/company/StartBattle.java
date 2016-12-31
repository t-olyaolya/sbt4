package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 22.11.2016.
 */
public class StartBattle {
    public static List<String> out = new ArrayList<>();
    public static DateHelper dateHelper = new DateHelper();

    public List<String> battle(Squad squad1, Squad squad2) {
        out.add("Start battle: " + dateHelper.getFormattedStartData() + "\n");
        int k = 1;
        try {
            while ((squad1.hasAliveWarriors()) || (squad2.hasAliveWarriors())) {
                Warrior war1 = squad1.getRandomWarrior();
                out.add("War1 " + war1.toString());
                Warrior war2 = squad2.getRandomWarrior();
                out.add("War2 " + war2.toString());
                if (k % 2 != 0) {
                    StartBattle.died(war2, war1);
                } else {
                    StartBattle.died(war1, war2);
                }
                k++;
                dateHelper.skipTime();
                if (!StartBattle.checkAlive(squad1, squad2))
                    break;
                if (!StartBattle.checkAlive(squad2, squad1))
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
