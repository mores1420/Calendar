package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener{
    private String date;
    private int year = 2023;
    private int month = 5;
    private final String weekTitle[] = {"日", "一", "二", "三", "四", "五", "六"};
    private final JTextField textFieldYear,textFieldMonth;
    private final JButton nextMonth, lastMonth, determineButton;
    CalendarA calendar;
    JLabel labelDay[] = new JLabel[42];
    JLabel jLabelData;

    public GUI() {
        super("日历");
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        textFieldYear = new JTextField(10);
        textFieldMonth = new JTextField(10);
        JPanel panelYear = new JPanel();

        Icon iconForInput, iconYear, iconMonth, iconLast, iconNext;
        iconForInput = new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/calendar.png"))));
        iconYear = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/cat.png")));
        iconMonth = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/doge.png")));
        iconLast = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/last.png")));
        iconNext = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/next.png")));

        JLabel jLabelInput = new JLabel("请输入日期", iconForInput, JLabel.LEFT);
        panelYear.add(new JLabel("年份", iconYear, JLabel.LEFT));
        panelYear.add(textFieldYear);

        JPanel panelMonth = new JPanel();
        panelMonth.add(new JLabel("月份", iconMonth, JLabel.LEFT));
        panelMonth.add(textFieldMonth);

        JPanel panelDetermine = new JPanel();
        determineButton = new JButton("确定");
        panelDetermine.add(determineButton);

        JPanel panelLastMonth = new JPanel();
        lastMonth = new JButton(iconLast);
        panelLastMonth.add(lastMonth);

        JPanel panelNextMonth = new JPanel();
        nextMonth = new JButton(iconNext);
        panelNextMonth.add(nextMonth);

        date = year + "年" + month + "月";
        jLabelData = new JLabel(date);
        JPanel panelData = new JPanel();
        panelData.add(jLabelData);

        JButton titleName[] = new JButton[7];
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 7; i++) {
            titleName[i] = new JButton(weekTitle[i]);
            pCenter.add(titleName[i]);
        }
        for (int i = 0; i < 42; i++) {
            labelDay[i] = new JLabel("", JLabel.CENTER);
            pCenter.add(labelDay[i]);
        }

        textFieldYear.addActionListener(this);
        calendar = new CalendarA();
        calendar.setYear(year);
        calendar.setMonth(month);
        updateCalendar();

        nextMonth.addActionListener(this);
        lastMonth.addActionListener(this);
        determineButton.addActionListener(this);

        container.add(jLabelInput);
        container.add(panelYear);
        container.add(panelMonth);
        container.add(panelDetermine);
        container.add(panelLastMonth);
        container.add(panelNextMonth);
        container.add(panelData);
        getContentPane().add(pCenter, BorderLayout.CENTER);
        setSize(800, 700);
        setVisible(true);
    }

    //监听器
    @Override
    public void actionPerformed(ActionEvent e) {
        //点击下个月按钮
        if (e.getSource() == nextMonth) {
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
        //点击上个月按钮
        else if (e.getSource() == lastMonth) {
            month--;
            if (month < 1){
                month = 12;
                year--;
            }
        }
        //点击确定按钮
        else if (e.getSource() == determineButton) {
            try {
                int inputYear = Integer.parseInt(textFieldYear.getText());
                int inputMonth = Integer.parseInt(textFieldMonth.getText());
                //判断输入是否正确
                if (inputYear <= 1900 || inputYear > 2300) {
                    JOptionPane.showMessageDialog(null, "请输入正确的年份(1900-2300)");
                    return;
                }
                if (inputMonth <= 0 || inputMonth > 12) {
                    JOptionPane.showMessageDialog(null, "请输入正确的月份(1-12)");
                    return;
                }
                year = inputYear;
                month = inputMonth;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "请输入正确的年份和月份");
                return;
            }
        }
        updateCalendar();
    }

    //更新日历
    private void updateCalendar() {
        calendar.setYear(year);
        calendar.setMonth(month);
        String day[] = calendar.daysOfMonth();

        for (int i = 0; i < 42; i++) {
            labelDay[i].setText(day[i]);
        }
        date=year+"年"+month+"月";
        jLabelData.setText(date);
    }
}
