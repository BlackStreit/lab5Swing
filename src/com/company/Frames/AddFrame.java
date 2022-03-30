package com.company.Frames;

import com.company.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JFrame {
    JButton add;
    JButton close;
    JTextField title;
    JLabel label;
    JLabel error;
    public AddFrame(){
        super("Добавить зал");
        init();
        initListener();
        setSize(300, 500);
        setLayout(null);
        setVisible(true);
    }
    private void init(){
        error = new JLabel();
        error.setBounds(0, 50, 150, 30);
        add(error);

        add = new JButton("Добавить");
        add.setBounds(75, 250, 150, 75);
        add(add);

        close = new JButton("Закрыть");
        close.setBounds(75, 350, 150, 75);
        add(close);

        title = new JTextField();
        title.setBounds(75, 150, 150, 30);
        add(title);


        label = new JLabel("Введите название зала");
        label.setBounds(75, 100, 150, 30);
        add(label);

        error = new JLabel();
        error.setBounds(0, 50, 150, 30);
        add(error);
    }

    private void initListener(){
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(title.getText().length() == 0){
                    error.setText("Введите название зала");
                    return;
                }
                DataBase.addHall(title.getText());
                error.setText("Успешно добавлено!");
                title.setText("");
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });
    }
}
