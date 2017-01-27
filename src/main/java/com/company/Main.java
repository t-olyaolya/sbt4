package com.company;

import com.company.controller.BattleController;
import com.company.controller.BattleControllerImp;
import com.company.model.BattleModelImp;
import com.company.model.Model;

/**
 * Created by tyuly on 12.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        Model battleModel = new BattleModelImp();
        BattleController controller = new BattleControllerImp(battleModel);
    }
}
