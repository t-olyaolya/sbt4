package com.company.view;

import com.company.controller.BattleController;
import com.company.controller.BattleControllerImp;
import com.company.model.BattleModelImp;
import com.company.model.Model;
import com.company.model.Squad;
import com.company.model.Warrior;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tyuly on 11.12.2016.
 */
public class Ui extends JFrame implements BattleObserver{
    private final int TEXT_SIZE = 25;
    private BattleController battleController;
    private Squad squad1;
    private Squad squad2;
    private JTextArea textArea;
    private String nameSq1, nameSq2 = "";



    public Ui(Model model, BattleController battleController) throws HeadlessException {
        this.battleController = battleController;
        model.registerObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        create();
    }

    public void update(String upd) {
        textArea.setText(upd.toString());
    }

    public void create() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JTextField nameSquad1 = new TextFieldName();
        JTextField nameSquad2 = new TextFieldName();
        JButton createButton1 = new CreateSquadButton();
        textArea = new JTextArea(50, 50);
        createButton1.addActionListener(e -> {
            nameSq1 = BattleController.createNameSquad(this,"Squad1", nameSquad1.getText());

        });
        JButton createButton2 = new CreateSquadButton();
        createButton2.addActionListener(e -> {
            nameSq2 = BattleController.createNameSquad(this,"Squad2", nameSquad2.getText());
        });
        JTextField nameWarrior1 = new TextFieldName();
        JTextField nameWarrior2 = new TextFieldName();
        JComboBox comboBox1 = new ComboSquadName();
        JComboBox comboBox2 = new ComboSquadName();
        JButton start = new JButton("START");
        JButton add1 = new AddButton();
        add1.addActionListener(e -> {
            Ui.addWarrior(battleController, nameWarrior1, comboBox1, BattleModelImp.warriors1);
        });
        JButton add2 = new AddButton();
        add2.addActionListener(e -> {
            Ui.addWarrior(battleController, nameWarrior2, comboBox2, BattleModelImp.warriors2);
        });
        start.addActionListener(e -> {
            //update("");
            squad1 = battleController.createSquad(nameSq1, BattleModelImp.warriors1);
            squad2 = battleController.createSquad(nameSq2, BattleModelImp.warriors2);
            battleController.start(squad1, squad2);
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
            super(battleController.getTypesWarriors());
        }
    }

    public static void addWarrior(BattleController battleController, JTextField nameWarrior, JComboBox comboBox, ArrayList<Warrior> warriors) {
        battleController.addWarrior(nameWarrior.getText(),(String) comboBox.getSelectedItem(), warriors);
        nameWarrior.setText("");
    }
}
