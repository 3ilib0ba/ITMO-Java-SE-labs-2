package commands;

import data.dao.exceptions.InvalidPasswordException;
import data.netdata.ClientIdentificate;
import data.netdata.ReportState;
import data.workwithrequest.ExecuteRequest;

import java.sql.SQLException;

import static main.Server.databaseManager;

public class RegisterCommand {
    public RegisterCommand(ClientIdentificate aboutClient) {
        try {
            aboutClient.setIdOfClient(databaseManager.findUser(aboutClient.getLogin(), aboutClient.getPassword()));
            if (aboutClient.idOfClient == null) {
                databaseManager.insertNewClient(aboutClient);
                ExecuteRequest.answer.append("User with this name has been created");
                ExecuteRequest.setStateAnswer(ReportState.OK);
            } else {
                ExecuteRequest.answer.append("User with this nickname has been found");
                ExecuteRequest.setStateAnswer(ReportState.REG_ERR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            ExecuteRequest.answer.append("User with this nickname has been found");
            ExecuteRequest.setStateAnswer(ReportState.REG_ERR);
        }
    }
}
