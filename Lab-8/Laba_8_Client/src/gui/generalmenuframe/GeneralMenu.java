package gui.generalmenuframe;

import gui.generalmenuframe.createnewobjectframe.AllLogic;
import gui.properties.LocaleBundle;
import main.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import static java.awt.GridBagConstraints.*;

public class GeneralMenu extends JFrame {
    private JPanel generalMenuPanel;
    public JPanel logPanel;
    public JLabel clientHello;
    public JTextArea logText;
    public JTextField logInput;
    public JButton helpButton;
    public JButton infoButton;
    public JButton showButton;
    public JButton insertAndUpdateButton;
    //public JButton updateButton;
    public JButton removeKeyButton;
    public JButton clearButton;
    public JButton executeScriptButton;
    public JButton removeLowerAreaButton;
    public JButton historyButton;
    public JButton replaceIfLowerButton;
    public JButton minByIdButton;
    public JButton countLessNumberBathButton;
    public JButton filterStartsByNameButton;
    public JButton backToRegistationButton;
    public JButton exitButton;
    public JComboBox languageBox;

    private Client client;

    public GeneralMenu(Client client) {
        this.client = client;
        ButtonListeners.client = client;
        AllLogic.client = client;

        initWindow();
        setPosition();
        setActions();
        generalMenuPanel.setVisible(true);
    }

    private void initWindow() {
        generalMenuPanel = new JPanel();
        logPanel = new JPanel();
        clientHello = new JLabel();
        logText = new JTextArea();
        logInput = new JTextField();
        helpButton = new JButton();
        infoButton = new JButton();
        showButton = new JButton();
        insertAndUpdateButton = new JButton();
        //updateButton = new JButton();
        removeKeyButton = new JButton();
        clearButton = new JButton();
        executeScriptButton = new JButton();
        removeLowerAreaButton = new JButton();
        historyButton = new JButton();
        replaceIfLowerButton = new JButton();
        minByIdButton = new JButton();
        countLessNumberBathButton = new JButton();
        filterStartsByNameButton = new JButton();
        backToRegistationButton = new JButton();
        exitButton = new JButton();
        languageBox = new JComboBox();
    }

    private void setPosition() {
        GridBagLayout general = new GridBagLayout();
        GridBagConstraints table;

        generalMenuPanel.setLayout(general);

        Color c1 = new Color(125, 183, 144);
        Color c2 = new Color(150, 80, 120);
        Font typeText = new Font("Arial Black", Font.PLAIN, 30);
        Font titleText = new Font("Arial Black", Font.PLAIN, 60);


        // init JPanel (general window)
        generalMenuPanel.setBackground(new Color(50, 70, 110));

        table = new GridBagConstraints(); // reset Constraints settings
        // init label about client
        clientHello.setText("Nice to see you, " + client.getLoginClient());
        clientHello.setBackground(c2);
        clientHello.setForeground(c1);
        clientHello.setFont(titleText);
        clientHello.setVerticalTextPosition(SwingConstants.TOP);
        table.gridx = 0;
        table.gridy = 0;
        table.gridwidth = 5;
        table.weightx = 1.0;
        table.weighty = 0.2;
        generalMenuPanel.add(clientHello, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init help button
        helpButton.setText("help");
        helpButton.setBackground(c2);
        helpButton.setForeground(c1);
        helpButton.setFont(typeText);
        table.gridx = 0;
        table.gridy = 1;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(helpButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init info button
        infoButton.setText("info");
        infoButton.setBackground(c2);
        infoButton.setForeground(c1);
        infoButton.setFont(typeText);
        table.gridx = 1;
        table.gridy = 1;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(infoButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init show button
        showButton.setText("show");
        showButton.setBackground(c2);
        showButton.setForeground(c1);
        showButton.setFont(typeText);
        table.gridx = 2;
        table.gridy = 1;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(showButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init insert button
        insertAndUpdateButton.setText("insert OR update");
        insertAndUpdateButton.setBackground(c2);
        insertAndUpdateButton.setForeground(c1);
        insertAndUpdateButton.setFont(typeText);
        table.gridx = 3;
        table.gridy = 1;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.gridwidth = 2;
        table.fill = BOTH;
        generalMenuPanel.add(insertAndUpdateButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init removeKeyButton button
        removeKeyButton.setText("remove by key");
        removeKeyButton.setBackground(c2);
        removeKeyButton.setForeground(c1);
        removeKeyButton.setFont(typeText);
        table.gridx = 0;
        table.gridy = 2;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(removeKeyButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init clear button
        clearButton.setText("clear");
        clearButton.setBackground(c2);
        clearButton.setForeground(c1);
        clearButton.setFont(typeText);
        table.gridx = 1;
        table.gridy = 2;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(clearButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init execute script button
        executeScriptButton.setText("execute script");
        executeScriptButton.setBackground(c2);
        executeScriptButton.setForeground(c1);
        executeScriptButton.setFont(typeText);
        table.gridx = 2;
        table.gridy = 2;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(executeScriptButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init remove lower area button
        removeLowerAreaButton.setText("remove lower (AREA)");
        removeLowerAreaButton.setBackground(c2);
        removeLowerAreaButton.setForeground(c1);
        removeLowerAreaButton.setFont(typeText);
        table.gridx = 3;
        table.gridy = 2;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(removeLowerAreaButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init history button
        historyButton.setText("history");
        historyButton.setBackground(c2);
        historyButton.setForeground(c1);
        historyButton.setFont(typeText);
        table.gridx = 4;
        table.gridy = 2;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(historyButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init replace if lower button
        replaceIfLowerButton.setText("replace if lower");
        replaceIfLowerButton.setBackground(c2);
        replaceIfLowerButton.setForeground(c1);
        replaceIfLowerButton.setFont(typeText);
        table.gridx = 0;
        table.gridy = 3;
        table.weightx = 1.0;
        table.weighty = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(replaceIfLowerButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init min by id button
        minByIdButton.setText("min by id");
        minByIdButton.setBackground(c2);
        minByIdButton.setForeground(c1);
        minByIdButton.setFont(typeText);
        table.gridx = 1;
        table.gridy = 3;
        table.weightx = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(minByIdButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init count less than number of bathrooms button
        countLessNumberBathButton.setText("count less than baths");
        countLessNumberBathButton.setBackground(c2);
        countLessNumberBathButton.setForeground(c1);
        countLessNumberBathButton.setFont(typeText);
        table.gridx = 2;
        table.gridy = 3;
        table.weightx = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(countLessNumberBathButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init filter starts by name button
        filterStartsByNameButton.setText("filter of name(starts by)");
        filterStartsByNameButton.setBackground(c2);
        filterStartsByNameButton.setForeground(c1);
        filterStartsByNameButton.setFont(typeText);
        table.gridx = 3;
        table.gridy = 3;
        table.weightx = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(filterStartsByNameButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init back to registration button
        backToRegistationButton.setText("Back");
        backToRegistationButton.setBackground(c2);
        backToRegistationButton.setForeground(c1);
        backToRegistationButton.setFont(typeText);
        table.gridx = 4;
        table.gridy = 3;
        table.weightx = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(backToRegistationButton, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init back to registration button
        languageBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "English",
                "Русский",
                "Česko",
                "Hrvatski"
        }));
        table.gridx = 0;
        table.gridy = 5;
        table.weightx = 1.0;
        table.fill = BOTH;
        generalMenuPanel.add(languageBox, table);

        table = new GridBagConstraints(); // reset Constraints settings
        // init exit button
        exitButton.setText("Exit");
        exitButton.setBackground(c2);
        exitButton.setForeground(c1);
        exitButton.setFont(typeText);
        table.gridx = 4;
        table.gridy = 5;
        table.weightx = 1.0;
        table.fill = BOTH;
        table.anchor = PAGE_END;
        table.insets = new Insets(10, 0, 0, 0);
        generalMenuPanel.add(exitButton, table);
    }

    public void setActions() {
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeHelpCommand();
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeInfoCommand();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeShowCommand();
            }
        });

        insertAndUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeInsertNewObjectCommand();
            }
        });

        removeKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeRemoveKeyCommand();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeClearCommand();
            }
        });

        executeScriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeExecuteScriptCommand();
            }
        });

        removeLowerAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeRemoveLowerAreaCommand();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeHistoryCommand();
            }
        });

        replaceIfLowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeReplaceByKeyLowerCommand();
            }
        });

        minByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeMinByIdCommand();
            }
        });

        countLessNumberBathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeCountLessThenBathCommand();
            }
        });

        filterStartsByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeFilterStartsByNameCommand();
            }
        });

        backToRegistationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonListeners.invokeBackToRegisterWindowCommand();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });

        languageBox.addItemListener(e -> languageBoxItemListener(e));
    }

    public JPanel getPanel() {
        return generalMenuPanel;
    }

    public void languageBoxItemListener(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String item = (String) event.getItem();
            localize(item);
        }
    }

    public void localize(String language) {
        LocaleBundle.setCurrentLanguage(language);
        this.helpButton.setText(LocaleBundle.getValue("helpButton"));
        this.infoButton.setText(LocaleBundle.getValue("infoButton"));
        this.showButton.setText(LocaleBundle.getValue("showButton"));
        this.insertAndUpdateButton.setText(LocaleBundle.getValue("insertupdateButton"));
        this.removeKeyButton.setText(LocaleBundle.getValue("removebykeyButton"));
        this.clearButton.setText(LocaleBundle.getValue("clearButton"));
        this.executeScriptButton.setText(LocaleBundle.getValue("executescriptButton"));
        this.removeLowerAreaButton.setText(LocaleBundle.getValue("removelowerareaButton"));
        this.historyButton.setText(LocaleBundle.getValue("historyButton"));
        this.replaceIfLowerButton.setText(LocaleBundle.getValue("replaceiflowerButton"));
        this.minByIdButton.setText(LocaleBundle.getValue("minbyidButton"));
        this.countLessNumberBathButton.setText(LocaleBundle.getValue("countlessButton"));
        this.filterStartsByNameButton.setText(LocaleBundle.getValue("filterstartsbynameButton"));
        this.backToRegistationButton.setText(LocaleBundle.getValue("backButton"));
        this.exitButton.setText(LocaleBundle.getValue("exitButton"));
    }
}
