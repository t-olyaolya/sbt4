package com.company;

import com.company.model.Battle;
import com.company.model.Squad;
import com.company.model.Warrior;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tyuly on 11.12.2016.
 */
public class Ui extends JFrame {
    private final int TEXT_SIZE = 25;
    private static String[] items = {"Viking", "Archer"};
    private static Battle battle = new Battle();
    private String squad1Name = "squad1";
    private String squad2Name = "squad2";
    private String item = "";
    private static String nameWarrior = "";
    private Squad squad1;
    private Squad squad2;
    private List<String> list = new ArrayList<>();

    public Ui() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        create();
    }

    public void create() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JTextField nameSquad1 = new TextFieldName();
        JTextField nameSquad2 = new TextFieldName();
        JButton createButton1 = new CreateSquadButton();
        JTextArea textArea = new JTextArea(50, 50);
        createButton1.addActionListener(e -> {
            squad1Name = Ui.createSquad(squad1Name, nameSquad1, textArea);

        });
        JButton createButton2 = new CreateSquadButton();
        createButton2.addActionListener(e -> {
            squad2Name = Ui.createSquad(squad2Name, nameSquad2, textArea);
        });
        JTextField nameWarrior1 = new TextFieldName();
        JTextField nameWarrior2 = new TextFieldName();
        JComboBox comboBox1 = new ComboSquadName();
        JComboBox comboBox2 = new ComboSquadName();
        JButton start = new JButton("START");
        JButton add1 = new AddButton();
        add1.addActionListener(e -> {
            item = (String) comboBox1.getSelectedItem();
            Ui.addWarrior(item, Battle.warriors1, nameWarrior1, textArea);
        });
        JButton add2 = new AddButton();
        add2.addActionListener(e -> {
            item = (String) comboBox2.getSelectedItem();
            Ui.addWarrior(item, Battle.warriors2, nameWarrior2, textArea);
        });
        start.addActionListener(e -> {
            textArea.setText("");
            squad1 = battle.createSquad(squad1Name, Battle.warriors1);
            squad2 = battle.createSquad(squad2Name, Battle.warriors2);
            list = battle.startBattle(squad1, squad2);
            list.forEach((String s) -> textArea.append("\n" + s));
            nameSquad1.setText("");
            nameSquad2.setText("");
        });
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        panel1.add(new SquadNameLabel(), constraints);
        constraints.gridx++;
        panel1.add(nameSquad1, constraints);
        constraints.gridx++;
        panel1.add(createButton1, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        panel1.add(comboBox1, constraints);
        constraints.gridx++;
        panel1.add(nameWarrior1, constraints);
        constraints.gridx++;
        panel1.add(add1, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        panel1.add(new SquadNameLabel(), constraints);
        constraints.gridx++;
        panel1.add(nameSquad2, constraints);
        constraints.gridx++;
        panel1.add(createButton2, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        panel1.add(comboBox2, constraints);
        constraints.gridx++;
        panel1.add(nameWarrior2, constraints);
        constraints.gridx++;
        panel1.add(add2, constraints);
        constraints.gridy++;
        constraints.gridx = 1;
        panel1.add(start, constraints);
        panel2.setLayout(new FlowLayout());
        panel2.add(textArea);
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);
        pack();
    }

    private class TextFieldName extends JTextField {
        public TextFieldName() {
            super(TEXT_SIZE);
        }
    }

    private class AddButton extends JButton {
        public AddButton() {
            super("Add");
        }
    }

    private class CreateSquadButton extends JButton {
        public CreateSquadButton() {
            super("Create");
        }
    }

    private class SquadNameLabel extends JLabel {
        public SquadNameLabel() {
            super("Введите имя отряда");
        }
    }

    private class ComboSquadName extends JComboBox {
        public ComboSquadName() {
            super(battle.getClasses());
        }
    }

    public static void addWarrior(String item, ArrayList<Warrior> warriors, JTextField nameWar, JTextArea textArea) {
        nameWarrior = nameWar.getText();
        battle.addWarriors(warriors, item, nameWarrior);
        textArea.append("Warrior " + nameWarrior + " added" + "\n");
        nameWar.setText("");
    }

    public static String createSquad(String name, JTextField nameSquad, JTextArea textArea) {
        String sqName = nameSquad.getText();
        if(sqName.equals(null)) {
            sqName = name;
        }
        textArea.append(sqName + " created" + "\n");
        return sqName;
    }
}
