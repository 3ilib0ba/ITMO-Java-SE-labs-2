package commands;

import collectionofflats.MyTreeMap;
import data.workwithrequest.ExecuteRequest;

import static collectionofflats.StartWorkWithCollection.dateOfInit;

/**
 * Class with 'info' command. Output main information about MAP.
 */
public class CommandInfo {
    public static void infoCommand(MyTreeMap map) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecuteRequest.answer.append("Info of map:" +
                "\n\tType of map:" + map.getMyMap().getClass() +
                "\n\tDate of create:" + dateOfInit +
                "\n\tsize:" + map.getMyMap().size() +
                "\n\tto show all elements -> show");

        HistoryCommand.addHistory("info");
    }
}
