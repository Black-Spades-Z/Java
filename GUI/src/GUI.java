/*
* Name : Azizbek Muminjonov
* ID : U2110207
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class GUI extends JFrame {

    private String[] products = {"Tomato", "Potato", "Meat", "Milk", "Sausage", "Carrot" };
    static boolean isOpen = false;
    static boolean isTrue = false;
    static boolean isLight = false;
    JTextField jTextField = new JTextField("Samsung");
    JButton jButton = new JButton("Open the fridge");
    JCheckBox jCheckbox = new JCheckBox("Electricity");
    JRadioButton radioButton = new JRadioButton("Light");
    JComboBox jComboBox = new JComboBox(products);

    public GUI()
    {
        JPanel mainPanel = new JPanel();

        jButton.addActionListener(new FridgeOpenerClass());
        jCheckbox.addActionListener(new ElectricityClass());
        jCheckbox.addItemListener(new ElectricityClass());
        radioButton.addActionListener(new RadioClass());
        radioButton.addItemListener(new RadioClass());
        jComboBox.addActionListener(new ComboBoxClass());
        jComboBox.addItemListener(new ComboBoxClass());
        jTextField.addActionListener(new TextClass());

        mainPanel.add(jButton);
        mainPanel.add(jCheckbox);
        mainPanel.add(radioButton);
        mainPanel.add(jTextField);
        mainPanel.add(jComboBox);
        add(mainPanel);
    }


        public static void main(String[] args)
    {
        JFrame frame = new GUI();
        frame.setTitle("U2110207 GUI");
        frame.setSize(1000, 500);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class FridgeOpenerClass implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (!isOpen)
            {
                System.out.println("Fridge is opened");
                isOpen = true;
            }
            else
            {
                System.out.println("Fridge is closed");
                isOpen = false;
            }
        }
    }

    private class ElectricityClass implements ActionListener, ItemListener
    {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Electricity is pressed");
        }

        public void itemStateChanged(ItemEvent e)
        {
            if(!isTrue)
            {
                System.out.println("Electricity is turned on");
                isTrue = true;
                radioButton.setEnabled(true);
            }
            else
            {
                System.out.println("Electricity is turned off");
                isTrue = false;
                radioButton.setEnabled(false);
            }

        }
    }

    private class RadioClass implements ActionListener, ItemListener
    {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Light is Pressed");
        }

        public void itemStateChanged(ItemEvent e)
        {
            if(!isLight)
            {
                System.out.println("Light is turned on");
                isLight = true;
            }
            else
            {
                System.out.println("Light is turned off");
                isLight = false;
            }
        }
    }

    private class ComboBoxClass implements ActionListener, ItemListener
    {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Combo box is pressed");

        }

        public void itemStateChanged(ItemEvent e)
        {
            System.out.println("Selected " + e.getItem());
        }
    }

    private class TextClass implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Text was pressed");
            System.out.println("Input " + jTextField.getText());
        }


    }

}

