package commands;

import main.MyTreeMap;

/**
 * Class with 'show' command. Output all flats.
 */
public class CommandShow {
    public static void showCommand(MyTreeMap map) {
        if (map.getMyMap().size() == 0) {
            System.out.println("Your collection is empty, use the insert command");
        } else {
            System.out.println("Display everyone elements in collection");
            for (Integer key : map.getMyMap().keySet()) {
                System.out.println(map.getMyMap().get(key));
            }
        }

        HistoryCommand.addHistory("Show");
    }
}
