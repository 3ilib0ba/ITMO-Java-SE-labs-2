package commands;

import collectionofflats.MyTreeMap;
import data.workwithrequest.ExecuteRequest;

/**
 * Class with 'show' command. Output all flats.
 */
public class CommandShow {
    public static void showCommand(MyTreeMap map) {
        if (map.getMyMap().size() == 0) {
            ExecuteRequest.answer.append("Your collection is empty, use the insert command");
        } else {
            ExecuteRequest.answer.append("Display everyone elements in collection");
            for (Integer key : map.getMyMap().keySet()) {
                ExecuteRequest.answer.append(map.getMyMap().get(key));
            }
        }

        HistoryCommand.addHistory("Show");
    }
}
