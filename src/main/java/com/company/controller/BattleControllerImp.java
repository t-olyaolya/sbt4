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

    public BattleControllerImp(Model model) {
        this.model = model;
        new Ui(model, this);
    }

    @Override
    public String[] getTypesWarriors() {
        return model.getTypeWarriors();
    }

    @Override
    public void addWarrior(String nameWarrior,String item, ArrayList<Warrior> warriors) {
        model.addWarriors(warriors, item, nameWarrior);
    }

    @Override
    public Squad createSquad(String sqName, ArrayList<Warrior> warriors) {
        return  model.createSquad(sqName, warriors);
    }

    @Override
    public String createNameSquad(String name, String sqName) {
        if(sqName.equals("")) {
            sqName = name;
        }
        model.createSquadName(sqName);
        return sqName;
    }

    @Override
    public void start(Squad squad1, Squad squad2) {
        model.start(squad1,squad2);
    }
}
