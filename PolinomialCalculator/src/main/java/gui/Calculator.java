package gui;

import polinoame.Polinom;
import polinoame.RezultatImpartire;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.UIManager.setLookAndFeel;

public class Calculator extends JFrame{
    private JPanel calculator;
    private JTextField polinom1;
    private JButton substractButtom;
    private JButton multiplyButton;
    private JButton integrareButton;
    private JButton addButton;
    private JButton derivareButton;
    private JButton divideButton;
    private JTextField polinom2;
    private JTextField rezultat;
    private JTextField rest;
    private JLabel rezultatLabel;
    private JLabel restLabel;


    public Calculator() {
        setContentPane(calculator);
        setTitle("Polinomial Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        //polinom1.setText("5x^2+35x^2-x+1");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRez();
                Polinom p1=new Polinom(polinom1.getText());
                Polinom p2=new Polinom(polinom2.getText());
                rezultat.setText((p1.add(p2)).polinomToString());
            }
        });
        substractButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRez();
                Polinom p1=new Polinom(polinom1.getText());
                Polinom p2=new Polinom(polinom2.getText());
                rezultat.setText(p1.substract(p2).polinomToString());
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRez();
                Polinom p1=new Polinom(polinom1.getText());
                Polinom p2=new Polinom(polinom2.getText());
                rezultat.setText(p1.multiply(p2).polinomToString());
            }
        });
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRez();
                rezultatLabel.setText("Cât:");
                restLabel.setText("Rest:");
                Polinom p1=new Polinom(polinom1.getText());
                Polinom p2=new Polinom(polinom2.getText());
                if(p2.polinomToString().equals("") || p2.polinomToString().equals("0")){
                    rezultat.setText("err");
                }
                else {
                    RezultatImpartire rez = p1.divide(p2);
                    rezultat.setText(rez.getCat().polinomToString());
                    rest.setText(rez.getRest().polinomToString());
                }
            }
        });
        integrareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRez();
                Polinom p1=new Polinom(polinom1.getText());
                rezultat.setText((p1.integreaza()).polinomToString());
            }
        });
        derivareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRez();
                Polinom p1=new Polinom(polinom1.getText());
                rezultat.setText((p1.deriveaza()).polinomToString());
            }
        });

    }

    public JTextField getPolinom1() {
        return polinom1;
    }

    public JTextField getPolinom2() {
        return polinom2;
    }

    public void clearRez(){
        rezultat.setText("");
        rest.setText("");
        rezultatLabel.setText("Rezultat:");
        restLabel.setText("");
    }
}
