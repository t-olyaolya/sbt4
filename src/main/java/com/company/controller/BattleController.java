package com.company.controller;

import com.company.model.Squad;
import com.company.model.Warrior;
import com.company.view.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 27.01.2017.
 */
public interface BattleController {
    String[] getTypesWarriors();

    void addWarrior(String nameWarrior,String item, ArrayList<Warrior> warriors);

    static String createNameSquad(Ui battleView, String name, String sqName) {
        String upd = "";
        if(sqName.equals(null)) {
            sqName = name;
        }
        upd =  sqName + " created" + "\n";
        battleView.update(upd);
        return sqName;
    }

    Squad createSquad(String sqName, ArrayList<Warrior> warriors);

    void start(Squad squad1, Squad squad2);

}
