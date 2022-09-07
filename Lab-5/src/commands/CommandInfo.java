package commands;

import main.MyTreeMap;

import static main.Main.dateOfInit;

/**
 * Class with 'info' command. Output main information about MAP.
 */
public class CommandInfo {
    public static void infoCommand(MyTreeMap map) {
        System.out.println("Info of map:" +
                "\n\tType of map:" + map.getMyMap().getClass() +
                "\n\tDate of create:" + dateOfInit +
                "\n\tsize:" + map.getMyMap().size() +
                "\n\tto show all elements -> show");

        HistoryCommand.addHistory("info");
    }
}
