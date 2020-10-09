import codingcompetition2019.CodingCompCSVUtil;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class gui {
    private JPanel panel_window;
    private JPanel panel_option;
    private JPanel panel_display;
    private JPanel panel_choice1;
    private JComboBox combo_choice1;
    private JLabel label_choice1;
    private JPanel panel_choice2;
    private JLabel label_choice2;
    private JComboBox combo_choice2;
    private JLabel label_display;

    public String combo1Choice;
    public String combo2Choice;

    public gui() {

        initApp();

    }

    public void initApp() {

        // Populate Combo Boxes
        ArrayList<String> countryList = CodingCompCSVUtil.getCountryList("src/main/resources/significant-earthquakes.csv");
        for(int i = 0; i < countryList.size(); i++) {
            combo_choice1.addItem(countryList.get(i));
        }

        // Creates ComboBox1 listener
        ItemListener countryListener = itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                combo1Choice = itemEvent.getItem().toString();
                System.out.println(combo1Choice);
                changeOperation(combo1Choice);
            }
        };
        combo_choice1.addItemListener(countryListener);

        // Creates ComboBox2 listener
        ItemListener disasterListener = itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                combo2Choice = itemEvent.getItem().toString();
                System.out.println(combo2Choice);
                label_display.setText(CodingCompCSVUtil.getReqInfo(combo1Choice, combo2Choice));
            }
        };
        combo_choice2.addItemListener(disasterListener);

    }

    public void changeOperation(String op) {
        if (op != "World") {
            combo_choice2.removeAllItems();
            combo_choice2.addItem("Earthquakes");
            combo_choice2.addItem("Volcanic Eruptions");
        } else {
            combo_choice2.removeAllItems();
            ArrayList<String> disasterList = CodingCompCSVUtil.getDisasterList("src/main/resources/natural-disasters-by-type.csv");
            for (int i = 0; i < disasterList.size(); i++) {
                combo_choice2.addItem(disasterList.get(i));
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Data Display");
        frame.setContentPane(new gui().panel_window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // Sets dimensions, makes visible
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }




}
