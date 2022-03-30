package com.company.Frames;

import com.company.Classes.Hall;
import com.company.DataBase.DataBase;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFrame extends JFrame {

    private JComboBox<Integer> ids;
    private JLabel error;
    private JTextField title;
    private JButton edit;
    private JButton found;
    private JButton close;

    public EditFrame(){
        super("Редактировать зал");
        init();
        initListeners();
        setSize(400, 600);
        setLayout(null);
        setVisible(true);
    }
    private void init(){

        error = new JLabel();
        error.setBounds(0, 0, 600, 30);
        add(error);
        ids = new JComboBox<>(DataBase.getHallId());
        ids.setBounds(150, 30, 100, 30);
        add(ids);
        found = new JButton("Найти");
        found.setBounds(125,80, 150, 75);
        add(found);
        title = new JTextField();
        title.setBounds(125, 250, 150, 30);
        add(title);
        title.setVisible(false);
        edit = new JButton("Изменить");
        edit.setBounds(125,300, 150, 75);
        add(edit);
        edit.setVisible(false);
        close = new JButton("Закрыть");
        close.setBounds(125, 400, 150, 75);
        add(close);
    }

    private void initListeners(){
        found.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ans = DataBase.foundHall((Integer.parseInt(ids.getSelectedItem().toString())));
                title.setText(ans.getHallName());
                ids.setEnabled(false);
                found.setEnabled(false);
                title.setVisible(true);
                edit.setVisible(true);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(title.getText().length()==0){
                    error.setText("Вы не ввели значение");
                    return;
                }
                DataBase.editHall(new Hall((Integer.parseInt(ids.getSelectedItem().toString())), title.getText()));
                error.setText("Запись успешно изменена");
                title.setText("");
                ids.setEnabled(true);
                found.setEnabled(true);
                title.setVisible(false);
                edit.setVisible(false);
            }
        });
    }
}
