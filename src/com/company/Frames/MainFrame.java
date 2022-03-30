package com.company.Frames;

import com.company.DataBase.DataBase;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
     JButton addHall;
     JButton editHall;
     JButton deleteHall;
     JTable table;
    private Object[][] array ;
    // Заголовки столбцов
    private Object[] columnsHeader ;
    public MainFrame() throws HeadlessException {
        super("Главное окно");
        setSize(1000, 700);
        init();
        setVisible(true);
    }

    void init(){
        array = DataBase.getHall();
        columnsHeader = new String[] {"Номер", "Название"};
        table = new JTable(array, columnsHeader);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));

        addHall = new JButton("Добавить зал");
        addHall.setBounds(50, getHeight() - 100, 150, 30);
        contents.add(new JScrollPane(addHall));

        editHall = new JButton("Изменить зал");
        editHall.setBounds(300, getHeight() - 100, 150, 30);
        contents.add(new JScrollPane(editHall));

        deleteHall = new JButton("Удалить зал");
        deleteHall.setBounds(550, getHeight() - 100, 150, 30);
        contents.add(new JScrollPane(deleteHall));

        setContentPane(contents);
    }
}
