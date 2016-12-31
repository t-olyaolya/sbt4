package com.company;

import org.reflections.Reflections;

import java.util.*;


public class Battle {
    public static ArrayList<Warrior> warriors1 = new ArrayList<>();
    public static ArrayList<Warrior> warriors2 = new ArrayList<>();

    public void addWarriors(ArrayList<Warrior> warriors, String item, String name) {
        Warrior warrior = null;
        String[] squadsName = Battle.getClasses();
        for (String s : squadsName) {
            String className = "com.company." + s;
            try {
                if (item.equals(s)) {
                    Class cl = Class.forName(className);
                    warrior = (Warrior) cl.newInstance();
                    warrior.setName(name);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        warriors.add(warrior);
    }

    public Squad createSquad(String name, ArrayList<Warrior> warriors) {
        return new Squad(name, warriors);
    }

    public List<String> startBattle(Squad squad1, Squad squad2) {
        return new StartBattle().battle(squad1, squad2);
    }

    public static String[] getClasses() {
        Reflections reflections = new Reflections("com.company");
        Set<Class<? extends WarriorState>> classes =
                reflections.getSubTypesOf(WarriorState.class);
        String[] cls = new String[classes.size()];
        String setString = "";
        Iterator<Class<? extends WarriorState>> iterator = classes.iterator();
        for (int i = 0; i < classes.size(); i++) {
            setString = iterator.next().toString();
            String[] s = setString.split("\\.");
            cls[i] = s[s.length - 1];
        }
        return cls;
    }
}
