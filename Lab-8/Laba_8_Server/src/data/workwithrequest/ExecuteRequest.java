package data.workwithrequest;

import collectionofflats.MyTreeMap;
import commands.Execute;
import commands.exceptions.ExitException;
import data.netdata.ClientIdentificate;
import data.netdata.Report;
import data.netdata.ReportState;
import data.netdata.Request;
import typesfiles.Flat;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class ExecuteRequest {
    private static Scanner scannerOfCommands;
    public static StringBuilder answer = new StringBuilder();
    private static ReportState stateAnswer;
    private static TreeMap<Integer, Flat> localMap = new TreeMap<>();

    public static void doingRequest(Request request, MyTreeMap myMap) {
        answer = new StringBuilder("");
        stateAnswer = ReportState.OK;
        System.out.println("Entering the command: " + request.getCommandName() +
                ", FROM: " + request.getLoginClient());

        // Получение полного запроса в виде сканера
        StringBuilder fullRequest = new StringBuilder(request.getCommandName() + " " + request.getArgument());
        scannerOfCommands = new Scanner(fullRequest.toString());
        ClientIdentificate aboutClient = new ClientIdentificate(request.getLoginClient(), request.getPasswordClient());

        answer = new StringBuilder();

        try {
            Execute.execute(false, myMap, scannerOfCommands,
                    request.getObjectArgument() == "null" ? null : (Flat) request.getObjectArgument(), aboutClient);
            // Проверка на ошибку, если нет, то стабильное состояние ответа
            if (stateAnswer != ReportState.ERROR
                    && stateAnswer != ReportState.REG_ERR
                    && stateAnswer != ReportState.SIGN_IN_ERR) {
                stateAnswer = ReportState.OK;
            }
        } catch (ExitException e) {
            answer.append("main.Server isn't worked now");
            throw e;
        } catch (Exception e) {
            stateAnswer = ReportState.ERROR;
            answer.append(e.getMessage());
        } finally {
            localMap = myMap.getMyMap();
        }

    }

    public static Report makeReport() {
        return new Report(stateAnswer, answer.toString(), localMap);
    }

    public static void setStateAnswer(ReportState state) {
        stateAnswer = state;
    }
}
