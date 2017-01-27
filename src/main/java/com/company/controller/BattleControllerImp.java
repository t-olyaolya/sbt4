package com.company.controller;

import com.company.model.BattleModelImp;
import com.company.model.Model;
import com.company.model.Squad;
import com.company.model.Warrior;
import com.company.view.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 27.01.2017.
 */
public class BattleControllerImp implements BattleController {

    private Model model;
    private Ui battleView;

    public BattleControllerImp(Model model) {
        this.model = model;
        battleView = new Ui(model, this);

    }

    @Override
    public String[] getTypesWarriors() {
        return model.getTypeWarriors();
    }

    @Override
    public void addWarrior(String nameWarrior,String item, ArrayList<Warrior> warriors) {
        String upd = "";
        model.addWarriors(warriors, item, nameWarrior);
        upd = "Warrior " + nameWarrior + " added" + "\n";
        battleView.update(upd);
    }

    @Override
    public Squad createSquad(String sqName, ArrayList<Warrior> warriors) {
        return  model.createSquad(sqName, warriors);
    }

    @Override
    public void start(Squad squad1, Squad squad2) {
        battleView.update("");
        StringBuilder out = model.start(squad1,squad2);
        battleView.update(out.toString());
    }
}
