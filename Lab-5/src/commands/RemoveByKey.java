package commands;

import typesfiles.Flat;

import java.util.TreeMap;

/**
 * Class with 'remove_key' command. Removing flat with given key.
 */
public class RemoveByKey {
    public RemoveByKey(TreeMap<Integer, Flat> map, Integer keyRemoved) {
        if (map.containsKey(keyRemoved)) {
            Flat removed = map.remove(keyRemoved);
            System.out.println("Remove object " + removed);
        } else {
            System.out.println("Element with this key not found");
        }

        HistoryCommand.addHistory("remove key <...>");
    }
}
