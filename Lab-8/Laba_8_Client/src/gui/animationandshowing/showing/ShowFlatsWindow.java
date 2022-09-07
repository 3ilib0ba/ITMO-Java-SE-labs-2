package gui.animationandshowing.showing;

import data.netdata.Report;
import data.netdata.Request;
import gui.MainFrameManager;
import gui.animationandshowing.animation.VizualizeWindow;
import gui.generalmenuframe.GeneralMenu;
import gui.properties.LocaleBundle;
import main.Client;
import typesfiles.Flat;

import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;

import static java.awt.GridBagConstraints.*;

public class ShowFlatsWindow extends JPanel {
    private GridBagLayout layout;
    private JLabel flatShowCaseLabel;
    private JScrollPane scrollPanel;
    private JTable flatTable;
    private JButton backButton;
    private JTextField filterText;
    private JButton filterButton;
    private JComboBox filterKey;
    private JComboBox<String> filterOperation;
    private JButton goToAnimationButton;
    private TreeMap<Integer, Flat> mapOfObjects;

    public ShowFlatsWindow() {
        initWindow();
        setPositions();
        setActions();
    }


    private void initWindow() {
        layout = new GridBagLayout();
        flatShowCaseLabel = new JLabel();
        scrollPanel = new JScrollPane();
        flatTable = new JTable();
        initTable();
        filterKey = new JComboBox<>();
        goToAnimationButton = new JButton();
        backButton = new JButton();
        filterText = new JTextField();
        filterOperation = new JComboBox<>();
        filterButton = new JButton();
    }

    public void initTable() {
        flatTable.setModel(new FlatTableModel(
                new Object[][]{

                }, new String[]{
                "id",
                "name",
                "date of create",
                "area",
                "number of rooms",
                "number of baths",
                "time to metro",
                "X coordinate",
                "Y coordinate",
                "Furnish",
                "House->name",
                "House->year",
                "House->number of lifts",
                "House->number of flats"
        }));

        initFilterTextListener();
    }

    private void setPositions() {
        this.setLayout(layout);
        GridBagConstraints table;

        table = new GridBagConstraints(); // new init components state
        // init a label
        flatShowCaseLabel.setText("Flats");
        flatShowCaseLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        table.gridx = 0;
        table.gridy = 0;
        table.gridwidth = 3;
        table.weightx = 1.0;
        table.weighty = 0.5;
        this.add(flatShowCaseLabel, table);

        table = new GridBagConstraints(); // new init components state
        // init a scroll panel with table of flats
        flatTable.setShowHorizontalLines(false);
        scrollPanel.setViewportView(flatTable);
        table.gridx = 0;
        table.gridy = 2;
        table.gridwidth = 3;
        table.weighty = 3.0;
        table.fill = BOTH;
        this.add(scrollPanel, table);

        table = new GridBagConstraints(); // new init components state
        // init filter text 2 field
        table.gridx = 2;
        table.gridy = 3;
        table.fill = BOTH;
        table.weighty = 0.2;
        this.add(filterText, table);

        table = new GridBagConstraints(); // new init components state
        // init a keys combo box
        table.gridx = 0;
        table.gridy = 3;
        table.weighty = 0.2;
        filterKey.setModel(new DefaultComboBoxModel<>(new String[]{
                "id",
                "name",
                "date of create",
                "area",
                "number of rooms",
                "number of baths",
                "time to metro",
                "X coordinate",
                "Y coordinate",
                "Furnish",
                "House->name",
                "House->year",
                "House->number of lifts",
                "House->number of flats"
        }));
        this.add(filterKey, table);

        table = new GridBagConstraints(); // new init components state
        // init a oparation combo box
        table.gridx = 1;
        table.gridy = 3;
        table.weighty = 0.2;
        filterOperation.setModel(new DefaultComboBoxModel<>(new String[]{
                ">",
                "<"
        }));
        this.add(filterOperation, table);

        table = new GridBagConstraints(); // new init components state
        // init a button to filter
        filterButton.setText("FILTER");
        filterButton.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        table.gridx = 0;
        table.gridy = 4;
        table.weighty = 0.2;
        //table.weightx = 0.1;
        this.add(filterButton, table);

        table = new GridBagConstraints(); // new init components state
        // init a button to animation
        goToAnimationButton.setText("ANIME");
        goToAnimationButton.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        table.gridx = 1;
        table.gridy = 4;
        table.weighty = 0.2;
        //table.weightx = 0.1;
        this.add(goToAnimationButton, table);

        table = new GridBagConstraints(); // new init components state
        // init a back button
        backButton.setText(LocaleBundle.getValue("backButton"));
        backButton.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        table.gridx = 2;
        table.gridy = 4;
        table.weighty = 0.2;
        table.insets = new Insets(100, 300, 0, 0);
        table.anchor = PAGE_END;
        //table.weightx = 0.1;
        this.add(backButton, table);
    }

    private void setActions() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrameManager.getInstance().switchPanel(new GeneralMenu(Client.getInstance()).getPanel());
            }
        });

        goToAnimationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrameManager.getInstance().switchPanel(new VizualizeWindow());
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtering();
            }
        });
    }

    public void addFlatToTable(Flat flat) {
        Object[] row = new Object[]{
                flat.getId(),
                flat.getName(),
                flat.getCreationDate(),
                flat.getArea(),
                flat.getNumberOfRooms(),
                flat.getNumberOfBathrooms(),
                flat.getTimeToMetroOnFoot(),
                flat.getCoordinates().getX(),
                flat.getCoordinates().getY(),
                flat.getFurnish(),
                flat.getHouse().getName(),
                flat.getHouse().getYear(),
                flat.getHouse().getNumberOfLifts(),
                flat.getHouse().getNumberOfFlatsOnFloor()
        };
        System.out.println("Добавляю объект" + flat.toString());
        ((FlatTableModel) flatTable.getModel()).addRow(row);
    }

    public void initFilterTextListener() {
        DefaultTableModel model = (DefaultTableModel) flatTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        initSortRules(sorter);
        flatTable.setRowSorter(sorter);
    }

    public void initSortRules(TableRowSorter<DefaultTableModel> sorter) {
        LongComparator comparator = new LongComparator();
        sorter.setComparator(0, comparator);
        sorter.setComparator(3, comparator);
        sorter.setComparator(4, comparator);
        sorter.setComparator(5, comparator);
        sorter.setComparator(6, comparator);
        sorter.setComparator(7, comparator);
        sorter.setComparator(8, comparator);
        sorter.setComparator(11, comparator);
        sorter.setComparator(12, comparator);
        sorter.setComparator(13, comparator);
    }

    public void filtering() {
        try {
            Client.getClient().sendRequest(new Request("show", ""));
            Report answer = Client.getInstance().getAnswer();

            MainFrameManager.getInstance().getShowFlatsWindow().clearTable();

            Set<Integer> keySet = answer.getMyMap().keySet();
            for (Integer key : keySet) {
                addFlatToTable(answer.getMyMap().get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String operand = (String) filterOperation.getSelectedItem();
        int operation;
        if (operand.equals(">")) {
            operation = 1;
        } else if (operand.equals("<")) {
            operation = 0;
        } else {
            return;
        }

        int indexOfColumn = filterKey.getSelectedIndex();
        String value = filterText.getText();
        System.out.println("value = " + value);

        try {
            sorting(operation, indexOfColumn, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sorting(int operation, int index, String value) {
        int size = flatTable.getRowCount();
        Set<Integer> rowsGood = new HashSet<>();
        for (int rowNum = 0; rowNum < size; rowNum++) {
            try {
                Integer valueTable = (Integer) flatTable.getValueAt(rowNum, index);

                System.out.println(valueTable + ", " + value);
                if (operation == 1) {
                    if (valueTable > Integer.parseInt(value))
                        rowsGood.add((Integer) flatTable.getValueAt(rowNum, 0));
                } else {
                    if (valueTable < Integer.parseInt(value))
                        rowsGood.add((Integer) flatTable.getValueAt(rowNum, 0));
                }
            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }

        for (int rowSelected = 0; rowSelected < flatTable.getRowCount(); rowSelected++) {
            if (rowsGood.contains(flatTable.getValueAt(rowSelected, 0))) {
            } else {
                ((FlatTableModel) flatTable.getModel()).removeRow(rowSelected);
            }
        }
    }

    public void clearTable() {
        initTable();
    }

}

class LongComparator implements Comparator<Number> {
    @Override
    public int compare(Number o1, Number o2) {
        return (int) (o1.doubleValue() - o2.doubleValue());
    }
}



