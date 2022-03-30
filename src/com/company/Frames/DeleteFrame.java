package com.company.Frames;

import com.company.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFrame extends JFrame {
    JButton delete;
    JButton close;
    JLabel error;
    JComboBox<Integer> ids;
    public DeleteFrame(){
        super("Удалить зал");
        setSize(300, 500);
        init();
        initListeners();
        setLayout(null);
        setVisible(true);
    }
    private void init(){
        close = new JButton("Закрыть");
        close.setBounds(75, 350, 150, 75);
        add(close);

        delete = new JButton("Удалить");
        delete.setBounds(75, 250, 150, 75);
        add(delete);

        error = new JLabel();
        error.setBounds(0, 50, 150, 30);
        add(error);

        updateCombo();
    }
    private void updateCombo(){
        ids = new JComboBox(DataBase.getHallId());
        ids.setBounds(75, 150, 150, 30);
        add(ids);
    }
    private void initListeners(){
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainFrame();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ids.getItemCount()==0){
                    error.setText("Все записи удалены");
                    return;
                }
                DataBase.deleteHall(Integer.parseInt(ids.getSelectedItem().toString()));
                error.setText("Успешно удалено");
                remove(ids);
                updateCombo();
            }
        });
    }
}
