package commands;

import typesfiles.Flat;

import java.util.TreeMap;

/**
 * Class with 'clear' command. Clear all MAP.
 */
public class ClearCommand {
    public ClearCommand(TreeMap<Integer, Flat> map) {
        map.clear();
        System.out.println("Now collection is empty, use 'insert <key>' to add new element");

        HistoryCommand.addHistory("Clear");
    }
}
