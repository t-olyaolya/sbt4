package com.company;

import org.reflections.Reflections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.*;


public class Battle {
    public static List<String> items = new ArrayList<>();
    public static ArrayList<Warrior> warriors1 = new ArrayList<>();
    public static ArrayList<Warrior> warriors2 = new ArrayList<>();
//лишняя пустая строка

    public void addWarriors(ArrayList<Warrior> warriors, String item, String name) { //список бойцов мы получили с помощью рефлексии. но какой в этом смысл, если создать можно только Viking и Archer?
        Warrior warrior = null;
        if (item.equals("Viking")) {
            warrior = new Viking(name);
        }
        if (item.equals("Archer")) {
            warrior = new Archer(name);
        }
        warriors.add(warrior);

    }

    public Squad createSquad(String name, ArrayList<Warrior> warriors) {
        return new Squad(name, warriors);
    }

    public List<String> startBattle(Squad squad1, Squad squad2) {
        return new Battle2().battle(squad1, squad2);
    }

    public String [] getClasses() {
        Reflections reflections = new Reflections("com.company");
        Set <Class<? extends Warrior>> classes =
                reflections.getSubTypesOf(Warrior.class);
        String [] cls = new String[classes.size()];
        String setString = "";
        Iterator<Class<? extends Warrior>> iterator = classes.iterator();
        for (int i = 0; i < classes.size(); i++) {
            setString = iterator.next().toString();
            String [] s = setString.split("\\.");
            cls [i] = s[s.length -1 ];

        }
        return cls;

    }
}
