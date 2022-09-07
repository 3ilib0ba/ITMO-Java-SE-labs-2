package commands;

import commands.exceptions.InvalidArg;
import main.MyTreeMap;
import typesfiles.Flat;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class with 'update' command. Search flat with given id and replace it to new flat.
 */
public class CommandUpdate {
    public CommandUpdate(int idUpd, MyTreeMap map, Scanner scanner){
        try {
            Integer keyOfUpd = checkId(idUpd, map.getMyMap());
            System.out.println(keyOfUpd);
            new CommandInsert(keyOfUpd, map, true, scanner);
        } catch (InvalidArg e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * function for checking given id to existing in the MAP.
     * @param searchId - given id
     * @param map - given MAP to search
     * @return
     * @throws InvalidArg
     */
    private Integer checkId(int searchId, TreeMap<Integer, Flat> map)
            throws InvalidArg{
        for (Map.Entry<Integer, Flat> entry : map.entrySet()) {
            int id = entry.getValue().getId();
            if (id == searchId)
                return entry.getKey();
        }
        throw new InvalidArg("the given id was not found in the collection, there is nothing to update");
    }
}
