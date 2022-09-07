package gui.generalmenuframe.createnewobjectframe;

import com.sun.deploy.panel.GeneralPanel;
import commands.CommandInsert;
import data.netdata.Report;
import data.netdata.Request;
import gui.MainFrameManager;
import gui.generalmenuframe.GeneralMenu;
import main.Client;
import typesfiles.Flat;

import javax.swing.*;
import java.util.Scanner;

public abstract class AllLogic {
    public static Client client;

    static void insertButtonRealization(Scanner dataToInsert) {
        System.out.println("Start update from gui");
        try {
            Integer id = Integer.parseInt(dataToInsert.nextLine());
            Flat updating = new CommandInsert().execute(id, dataToInsert, true);

            client.sendRequest(new Request("insert", id.toString(), updating));
            Report answer = client.getAnswer();
            JOptionPane.showMessageDialog(null, answer.getReportBody());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    static void updateButtonRealization(Scanner dataToUpdate) {
        System.out.println("Start update from gui");
        try {
            Integer id = Integer.parseInt(dataToUpdate.nextLine());
            Flat updating = new CommandInsert().execute(id, dataToUpdate, true);

            client.sendRequest(new Request("update", id.toString(), updating));
            Report answer = client.getAnswer();
            JOptionPane.showMessageDialog(null, answer.getReportBody());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    static void goBackToGeneralFrame() {
        MainFrameManager.getInstance().switchPanel(new GeneralMenu(client).getPanel());
    }
}
