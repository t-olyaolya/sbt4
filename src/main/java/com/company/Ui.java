package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by tyuly on 11.12.2016.
 */
public class Ui extends JFrame {
    private final int jTextSize = 25; //private static final int TEXT_SIZE
    private String [] items = {"Viking", "Archer"};
    private Battle battle = new Battle();
    private String squad1Name = ""; //дать имена по умолчанию
    private String squad2Name = "";
    private String item ="";
    private String nameWarrior = "";
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
        JTextField nameSquad1 = new TextF();
        JTextField nameSquad2 = new TextF();
        JButton okButton1 = new AddButton();
        okButton1.addActionListener(e -> {
            squad1Name = nameSquad1.getText();

        });
        JButton okButton2 = new AddButton();
        okButton2.addActionListener(e -> {
            squad2Name = nameSquad2.getText();

        });
        JTextField nameWarrior1 = new TextF();
        JTextField nameWarrior2 = new TextF();
        JComboBox comboBox1 = new Combo(); //неинформативные имена
        JComboBox comboBox2 = new Combo();
        JButton add1 = new AddButton();
        add1.addActionListener(e -> {
            item = (String) comboBox1.getSelectedItem();
            nameWarrior = nameWarrior1.getText();
            battle.addWarriors(Battle.warriors1, item, nameWarrior); //пользователь не видит, добавился боец или нет
            nameWarrior1.setText("");
        });
        JButton add2 = new AddButton();
        add2.addActionListener(e -> { //дублирование
            item = (String) comboBox2.getSelectedItem();
            nameWarrior = nameWarrior2.getText();
            battle.addWarriors(Battle.warriors2, item, nameWarrior);
            nameWarrior2.setText("");
        });
        JTextArea textArea = new JTextArea(50,50);
        JButton start = new JButton("START");
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
        panel1.add(new SqLabel(),constraints);
        constraints.gridx++;
        panel1.add(nameSquad1,constraints);
        constraints.gridx++;
        panel1.add(okButton1,constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        panel1.add(comboBox1,constraints);
        constraints.gridx++;
        panel1.add(nameWarrior1,constraints);
        constraints.gridx++;
        panel1.add(add1,constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        panel1.add(new SqLabel(),constraints);
        constraints.gridx++;
        panel1.add(nameSquad2,constraints);
        constraints.gridx++;
        panel1.add(okButton2,constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        panel1.add(comboBox2,constraints);
        constraints.gridx++;
        panel1.add(nameWarrior2,constraints);
        constraints.gridx++;
        panel1.add(add2,constraints);
        constraints.gridy++;
        constraints.gridx = 1;
        panel1.add(start,constraints);
        panel2.setLayout(new FlowLayout());
        panel2.add(textArea);
        add(panel1,BorderLayout.WEST);
        add(panel2,BorderLayout.EAST);
        pack();

    }

    private class TextF extends JTextField {
        public TextF() {
            super(jTextSize);
        }

    }

    private class AddButton extends JButton {
        public AddButton() {
            super("ADD"); //разные по смыслу кнопки называются ADD
            //setBackground(Color.cyan);
        }
    }

    private class SqLabel extends JLabel {
        public SqLabel () {
            super("Введите имя отряда");
        }
    }

    private class Combo extends JComboBox {
        public Combo () {
            super (battle.getClasses());
        }
    }

}
