package gui.generalmenuframe.createnewobjectframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import static java.awt.GridBagConstraints.BOTH;

public class InsertAndUpdateMenu extends JPanel {
    private JLabel idForUpdateLabel;
    private JLabel nameLabel;
    private JLabel coordinateXLabel;
    private JLabel coordinateYLabel;
    private JLabel areaLabel;
    private JLabel numberOfRoomsLabel;
    private JLabel numberOfBathsLabel;
    private JLabel timeToMetroOnFootLabel;
    private JLabel furnishLabel;
    private JLabel nameHouseLabel;
    private JLabel yearOfBuildHouseLabel;
    private JLabel numbersOfLiftsLabel;
    private JLabel numbersOfFlatsLabel;

    private JTextField idForUpdateInput;
    private JTextField nameInput;
    private JTextField coordinateXInput;
    private JTextField coordinateYInput;
    private JTextField areaInput;
    private JTextField numberOfRoomsInput;
    private JTextField numberOfBathsInput;
    private JTextField timeToMetroOnFootInput;
    private JTextField furnishInput;
    private JTextField nameHouseInput;
    private JTextField yearOfBuildHouseInput;
    private JTextField numbersOfLiftsInput;
    private JTextField numbersOfFlatsInput;

    private JButton backToGeneralMenuButton;
    private JButton insertButton;
    private JButton updateButton;

    private void initialization() {
        idForUpdateLabel = new JLabel();
        nameLabel = new JLabel();
        coordinateXLabel = new JLabel();
        coordinateYLabel = new JLabel();
        areaLabel = new JLabel();
        numberOfRoomsLabel = new JLabel();
        numberOfBathsLabel = new JLabel();
        timeToMetroOnFootLabel = new JLabel();
        furnishLabel = new JLabel();
        nameHouseLabel = new JLabel();
        yearOfBuildHouseLabel = new JLabel();
        numbersOfLiftsLabel = new JLabel();
        numbersOfFlatsLabel = new JLabel();

        idForUpdateInput = new JTextField();
        nameInput = new JTextField();
        coordinateXInput = new JTextField();
        coordinateYInput = new JTextField();
        areaInput = new JTextField();
        numberOfRoomsInput = new JTextField();
        numberOfBathsInput = new JTextField();
        timeToMetroOnFootInput = new JTextField();
        furnishInput = new JTextField();
        nameHouseInput = new JTextField();
        yearOfBuildHouseInput = new JTextField();
        numbersOfLiftsInput = new JTextField();
        numbersOfFlatsInput = new JTextField();

        backToGeneralMenuButton = new JButton();
        insertButton = new JButton();
        updateButton = new JButton();
    }

    private void setPositions() {
        GridBagLayout generalLayout = new GridBagLayout();
        GridBagConstraints table = new GridBagConstraints();

        this.setLayout(generalLayout);

        Color c1 = new Color(95, 240, 74);
        Color c2 = new Color(10, 80, 120);
        Font typeText = new Font("Arial Black", Font.PLAIN, 30);
        Font titleText = new Font("Arial Black", Font.PLAIN, 60);

        // init background of this panel
        this.setBackground(new Color(0, 0, 0));

        // init table for labels
        table.weighty = 1.0;
        table.weightx = 1.0;
        table.fill = BOTH;
        table.gridx = 0;

        // init labels
        table.gridy = 0;
        idForUpdateLabel.setText("Input id/key:");
        idForUpdateLabel.setFont(typeText);
        idForUpdateLabel.setForeground(c1);
        this.add(idForUpdateLabel, table);

        table.gridy = 1;
        nameLabel.setText("Input name of object:");
        nameLabel.setFont(typeText);
        nameLabel.setForeground(c1);
        this.add(nameLabel, table);

        table.gridy = 2;
        coordinateXLabel.setText("Input the X coordinate of object:");
        coordinateXLabel.setFont(typeText);
        coordinateXLabel.setForeground(c1);
        this.add(coordinateXLabel, table);

        table.gridy = 3;
        coordinateYLabel.setText("Input the Y coordinate of object:");
        coordinateYLabel.setFont(typeText);
        coordinateYLabel.setForeground(c1);
        this.add(coordinateYLabel, table);

        table.gridy = 4;
        areaLabel.setText("Input area of object:");
        areaLabel.setFont(typeText);
        areaLabel.setForeground(c1);
        this.add(areaLabel, table);

        table.gridy = 5;
        numberOfRoomsLabel.setText("Input number of rooms:");
        numberOfRoomsLabel.setFont(typeText);
        numberOfRoomsLabel.setForeground(c1);
        this.add(numberOfRoomsLabel, table);

        table.gridy = 6;
        numberOfBathsLabel.setText("Input number of bathrooms:");
        numberOfBathsLabel.setFont(typeText);
        numberOfBathsLabel.setForeground(c1);
        this.add(numberOfBathsLabel, table);

        table.gridy = 7;
        timeToMetroOnFootLabel.setText("Input time to metro on foot:");
        timeToMetroOnFootLabel.setFont(typeText);
        timeToMetroOnFootLabel.setForeground(c1);
        this.add(timeToMetroOnFootLabel, table);

        table.gridy = 8;
        furnishLabel.setText("Input furnish:");
        furnishLabel.setFont(typeText);
        furnishLabel.setForeground(c1);
        this.add(furnishLabel, table);

        table.gridy = 9;
        nameHouseLabel.setText("Input name of house:");
        nameHouseLabel.setFont(typeText);
        nameHouseLabel.setForeground(c1);
        this.add(nameHouseLabel, table);

        table.gridy = 10;
        yearOfBuildHouseLabel.setText("Input year of this building:");
        yearOfBuildHouseLabel.setFont(typeText);
        yearOfBuildHouseLabel.setForeground(c1);
        this.add(yearOfBuildHouseLabel, table);

        table.gridy = 11;
        numbersOfFlatsLabel.setText("Input number of flats per floor:");
        numbersOfFlatsLabel.setFont(typeText);
        numbersOfFlatsLabel.setForeground(c1);
        this.add(numbersOfFlatsLabel, table);

        table.gridy = 12;
        numbersOfLiftsLabel.setText("Input number of lifts:");
        numbersOfLiftsLabel.setFont(typeText);
        numbersOfLiftsLabel.setForeground(c1);
        this.add(numbersOfLiftsLabel, table);

        // init table for input fields
        table.weighty = 1.0;
        table.weightx = 1.5;
        table.fill = BOTH;
        table.gridx = 1;

        // init INPUT fields
        table.gridy = 0;
        idForUpdateInput.setFont(typeText);
        idForUpdateInput.setForeground(c2);
        this.add(idForUpdateInput, table);

        table.gridy = 1;
        nameInput.setFont(typeText);
        nameInput.setForeground(c2);
        this.add(nameInput, table);

        table.gridy = 2;
        coordinateXInput.setFont(typeText);
        coordinateXInput.setForeground(c2);
        this.add(coordinateXInput, table);

        table.gridy = 3;
        coordinateYInput.setFont(typeText);
        coordinateYInput.setForeground(c2);
        this.add(coordinateYInput, table);

        table.gridy = 4;
        areaInput.setFont(typeText);
        areaInput.setForeground(c2);
        this.add(areaInput, table);

        table.gridy = 5;
        numberOfRoomsInput.setFont(typeText);
        numberOfRoomsInput.setForeground(c2);
        this.add(numberOfRoomsInput, table);

        table.gridy = 6;
        numberOfBathsInput.setFont(typeText);
        numberOfBathsInput.setForeground(c2);
        this.add(numberOfBathsInput, table);

        table.gridy = 7;
        timeToMetroOnFootInput.setFont(typeText);
        timeToMetroOnFootInput.setForeground(c2);
        this.add(timeToMetroOnFootInput, table);

        table.gridy = 8;
        furnishInput.setFont(typeText);
        furnishInput.setForeground(c2);
        this.add(furnishInput, table);

        table.gridy = 9;
        nameHouseInput.setFont(typeText);
        nameHouseInput.setForeground(c2);
        this.add(nameHouseInput, table);

        table.gridy = 10;
        yearOfBuildHouseInput.setFont(typeText);
        yearOfBuildHouseInput.setForeground(c2);
        this.add(yearOfBuildHouseInput, table);

        table.gridy = 11;
        numbersOfFlatsInput.setFont(typeText);
        numbersOfFlatsInput.setForeground(c2);
        this.add(numbersOfFlatsInput, table);

        table.gridy = 12;
        numbersOfLiftsInput.setFont(typeText);
        numbersOfLiftsInput.setForeground(c2);
        this.add(numbersOfLiftsInput, table);

        // init buttons for realization all logic on this frame
        table.weightx = 1.0;
        // init insert button
        insertButton.setText("Try to add new flat");
        table.gridy = 13;
        table.gridx = 0;
        this.add(insertButton, table);
        // init update button
        updateButton.setText("Update flat with id");
        table.gridy = 13;
        table.gridx = 1;
        this.add(updateButton, table);
        // init button for back to menu
        backToGeneralMenuButton.setText("Back");
        table.gridy = 14;
        table.gridx = 1;
        table.weighty = 0.4;
        table.insets = new Insets(100, 200, 0, 0);
        this.add(backToGeneralMenuButton, table);
    }

    private void setActions() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scanner = makeScanner();
                AllLogic.insertButtonRealization(scanner);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scanner = makeScanner();
                AllLogic.updateButtonRealization(scanner);
            }
        });

        backToGeneralMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllLogic.goBackToGeneralFrame();
            }
        });
    }

    public InsertAndUpdateMenu() {
        initialization();
        setPositions();
        setActions();
    }

    private Scanner makeScanner() {
        StringBuilder allInfo = new StringBuilder();

        allInfo.append(idForUpdateInput.getText());
        allInfo.append("\n");
        allInfo.append(nameInput.getText());
        allInfo.append("\n");
        allInfo.append(coordinateXInput.getText());
        allInfo.append("\n");
        allInfo.append(coordinateYInput.getText());
        allInfo.append("\n");
        allInfo.append(areaInput.getText());
        allInfo.append("\n");
        allInfo.append(numberOfRoomsInput.getText());
        allInfo.append("\n");
        allInfo.append(numberOfBathsInput.getText());
        allInfo.append("\n");
        allInfo.append(numberOfBathsInput.getText());
        allInfo.append("\n");
        allInfo.append(timeToMetroOnFootInput.getText());
        allInfo.append("\n");
        allInfo.append(furnishInput.getText());
        allInfo.append("\n");
        allInfo.append(nameHouseInput.getText());
        allInfo.append("\n");
        allInfo.append(yearOfBuildHouseInput.getText());
        allInfo.append("\n");
        allInfo.append(numbersOfFlatsInput.getText());
        allInfo.append("\n");
        allInfo.append(numbersOfLiftsInput.getText());
        allInfo.append("\n");

        return new Scanner(allInfo.toString());
    }


}
