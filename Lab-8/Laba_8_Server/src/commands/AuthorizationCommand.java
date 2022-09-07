package commands;

import data.dao.exceptions.InvalidPasswordException;
import data.netdata.ClientIdentificate;
import data.netdata.ReportState;
import data.workwithrequest.ExecuteRequest;

import java.sql.SQLException;

import static main.Server.databaseManager;

public class AuthorizationCommand {
    public AuthorizationCommand(ClientIdentificate aboutClient) {
        try {
            aboutClient.setIdOfClient(databaseManager.findUser(aboutClient.getLogin(), aboutClient.getPassword()));
            if (aboutClient.idOfClient == null) {
                ExecuteRequest.answer.append("User with this name not found");
                ExecuteRequest.setStateAnswer(ReportState.SIGN_IN_ERR);
            } else {
                ExecuteRequest.answer.append("Correct enter");
                ExecuteRequest.setStateAnswer(ReportState.OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            ExecuteRequest.answer.append("Invalid password, please try again");
            ExecuteRequest.setStateAnswer(ReportState.SIGN_IN_ERR);
        }
    }
}

