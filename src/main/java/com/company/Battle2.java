package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 22.11.2016.
 */
public class Battle2 {
    private Warrior war1;
    private Warrior war2;
    private List<String> out = new ArrayList<>();

    public List<String> battle (Squad squad1, Squad squad2) {
        try {
            DateHelper dateHelper = new DateHelper();
            out.add("Start battle: " + dateHelper.getFormattedStartData() + "\n");
            int k = 1;
            if ((squad1.warriors.isEmpty()) || (squad2.warriors.isEmpty())) {
                throw new NullPointerException();
            }
            while ((squad1.hasAliveWarriors()) || (squad2.hasAliveWarriors())) {
                war1 = squad1.getRandomWarrior();
                out.add("War1 " + war1.toString());
                war2 = squad2.getRandomWarrior();
                out.add("War2 " + war2.toString());
                if (k % 2 != 0) {
                    war2.takeDamage(war1.attack());
                    out.add("War1 attacks War2");
                    if (!war2.isAlive()) {
                        out.add(war2.toString() + " died");
                    }
                } else {
                    war1.takeDamage(war2.attack());
                    out.add("War2 attacks War1");
                    if (!war1.isAlive()) {
                        out.add(war1.toString() + " died");
                    }
                }
                k++;
                dateHelper.skipTime();
                if (!squad1.hasAliveWarriors()) {
                    out.add("\n" + squad2.toString() + " won");
                    out.add("Final: " + dateHelper.getFormattedFinalData());
                    break;
                }

                if (!squad2.hasAliveWarriors()) {
                    out.add("\n" + squad1.toString() + " won");
                    out.add("Final: " + dateHelper.getFormattedFinalData());
                    break;
                }
            }
            out.add("\n" + "Duration: " + dateHelper.getFormattedDiff());
        }
        catch (NullPointerException e) {
            out.add("Заполните отряды");
        }
        finally {
            return out;
        }
    }


}
