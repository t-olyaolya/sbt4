package com.company.controller;

import com.company.model.BattleModel;
import com.company.model.Squad;
import com.company.model.Warrior;
import com.company.view.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 27.01.2017.
 */
public class BattleController {

    private BattleModel battleModel;
    private Ui battleView;
    //private Squad squad1, squad2;

    public BattleController(BattleModel battleModel) {
        this.battleModel = battleModel;
        battleView = new Ui(battleModel, this);

    }

    public String[] getTypesWarriors() {
        return battleModel.getTypeWarriors();
    }

    public void addWarrior(String nameWarrior,String item, ArrayList<Warrior> warriors) {
        String upd = "";
        battleModel.addWarriors(warriors, item, nameWarrior);
        upd = "Warrior " + nameWarrior + " added" + "\n";
        battleView.update(upd);
    }

    public static String createNameSquad(Ui battleView, String name, String sqName) {
        String upd = "";
        if(sqName.equals(null)) {
            sqName = name;
        }
        upd =  sqName + " created" + "\n";
        battleView.update(upd);
        return sqName;
    }

    public Squad createSquad(String sqName, ArrayList<Warrior> warriors) {
        return  battleModel.createSquad(sqName, warriors);
    }

    public void start(Squad squad1, Squad squad2) {
        battleView.update("");
        List<String> out = battleModel.start(squad1,squad2);
        for (String o:out) {
            battleView.update(o);
        }
        
    }
}
