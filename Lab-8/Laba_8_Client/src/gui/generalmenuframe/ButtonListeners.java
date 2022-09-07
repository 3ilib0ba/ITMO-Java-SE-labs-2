package gui.generalmenuframe;

import data.netdata.Report;
import data.netdata.Request;
import gui.MainFrameManager;
import gui.animationandshowing.showing.ShowFlatsWindow;
import gui.generalmenuframe.createnewobjectframe.InsertAndUpdateMenu;
import main.App;
import main.Client;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.Scanner;
import java.util.Set;

public abstract class ButtonListeners {
    public static Client client;
    public static Scanner scanner = new Scanner(System.in);

    public static void invokeHelpCommand() {
        String answer = "";
        try {
            client.sendRequest(new Request("help", ""));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = "Возникла ошибка" + e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeInfoCommand() {
        String answer = "";
        try {
            client.sendRequest(new Request("info", ""));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = "Возникла ошибка" + e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeClearCommand() {
        String answer = "";
        try {
            client.sendRequest(new Request("clear", ""));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = "Возникла ошибка" + e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeShowCommand() {
        String errorAnswer = "";
        try {
            System.out.println("Выполняю команду show");

            client.sendRequest(new Request("show", ""));
            Report answer = client.getAnswer();

            MainFrameManager.getInstance().getShowFlatsWindow().clearTable();
            ShowFlatsWindow showWindow = MainFrameManager.getInstance().getShowFlatsWindow();

            Set<Integer> keySet = answer.getMyMap().keySet();
            for (Integer key : keySet) {
                showWindow.addFlatToTable(answer.getMyMap().get(key));
            }

            MainFrameManager.getInstance().switchPanel(MainFrameManager.getInstance().getShowFlatsWindow());
        } catch (Exception e) {
            errorAnswer = "Возникла ошибка";
            JOptionPane.showMessageDialog(null, errorAnswer);
            e.printStackTrace();
        }
    }

    public static void invokeRemoveKeyCommand() {
        String answer = "";
        try {
            Integer key = Integer.parseInt(JOptionPane.showInputDialog(null, "Input key(id)"));

            client.sendRequest(new Request("remove_key", key.toString()));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }  
    }

    public static void invokeRemoveLowerAreaCommand() {
        String answer = "";
        try {
            Long area = Long.parseLong(JOptionPane.showInputDialog(null, "Input area"));

            client.sendRequest(new Request("remove_lower", area.toString()));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeHistoryCommand() {
        String answer = "";
        try {
            client.sendRequest(new Request("history", ""));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeMinByIdCommand() {
        String answer = "";
        try {
            client.sendRequest(new Request("min_by_id", ""));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeReplaceByKeyLowerCommand() {
        String answer = "";
        try {
            Integer newKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Input key"));
            Long newArea = Long.parseLong(JOptionPane.showInputDialog(null, "Input area"));

            client.sendRequest(new Request("replace_if_lowe", newKey.toString() + " " + newArea));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = "ERROR " + e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeCountLessThenBathCommand() {
        String answer = "";
        try {
            Integer numOfBaths = Integer.parseInt(JOptionPane.showInputDialog(null, "Input number of baths:"));

            client.sendRequest(new Request("count_less_than_number_of_bathrooms", numOfBaths.toString()));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeFilterStartsByNameCommand() {
        StringBuilder answer = new StringBuilder("");
        try {
            String startOfName = JOptionPane.showInputDialog(null, "Input first part of name:");

            client.sendRequest(new Request("filter_starts_by_name", startOfName));
            Report report = client.getAnswer();
            answer.append(report.getReportBody());
        } catch (Exception e) {
            answer.append(e.getMessage());
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeExecuteScriptCommand() {
        String answer = "";
        try {
            String nameScript = JOptionPane.showInputDialog(null, "Input name of script:");

            client.sendRequest(new Request("execute_script", nameScript));
            answer = client.getAnswer().getReportBody();
        } catch (Exception e) {
            answer = e.getMessage();
        } finally {
            JOptionPane.showMessageDialog(null, answer);
        }
    }

    public static void invokeInsertNewObjectCommand() {
        MainFrameManager.getInstance().switchPanel(new InsertAndUpdateMenu());
    }

    public static void invokeBackToRegisterWindowCommand() {
        MainFrameManager.getInstance().switchPanel(App.startMenu.getStartMenuPanel());
    }
}
