package gui;

import gui.animationandshowing.showing.ShowFlatsWindow;

import javax.swing.*;

public class MainFrameManager extends JFrame {
    private ShowFlatsWindow showFlatsWindow = new ShowFlatsWindow();

    private static MainFrameManager isInstance;

    private MainFrameManager() {
    }

    // the Singleton realization
    public static MainFrameManager getInstance() {
        if (isInstance == null) {
            isInstance = new MainFrameManager();
        }
        return isInstance;
    }

    public void initialization(JPanel startMenuPanel) {
        this.setSize(1800, 1000);
        this.setContentPane(startMenuPanel);
        this.setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        this.setVisible(false);
        try {
            Thread.sleep(1000);
            this.setContentPane(panel);
            this.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ShowFlatsWindow getShowFlatsWindow() {
        return showFlatsWindow;
    }
}
