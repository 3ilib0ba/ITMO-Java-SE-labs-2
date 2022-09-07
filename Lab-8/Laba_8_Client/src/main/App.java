package main;

import gui.MainFrameManager;
import gui.generalmenuframe.createnewobjectframe.InsertAndUpdateMenu;
import gui.startmenuframe.StartMenu;

import java.util.Scanner;

public class App {
    public static StartMenu startMenu;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("localhost", 2467, scanner);

        MainFrameManager mainFrameManager = MainFrameManager.getInstance();
        startMenu = new StartMenu(client);
        //mainFrameManager.initialization(new InsertAndUpdateMenu());
        mainFrameManager.initialization(startMenu.getStartMenuPanel());

        System.out.println("Client is running");

        client.run();

    }
}
