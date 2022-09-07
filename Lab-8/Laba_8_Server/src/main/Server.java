package main;

import collectionofflats.MyTreeMap;
import collectionofflats.StartWorkWithCollection;
import commands.Execute;
import commands.exceptions.ExitException;
import data.dao.DBManager;
import data.netdata.Report;
import data.netdata.Request;
import data.workwithrequest.ExecuteRequest;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

public class Server {
    private int PORT;
    private InetAddress address;
    private DatagramSocket socket;

    private Scanner scanner;
    private MyTreeMap myMap;

    //for helios
    //private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
    //for localhost
    private static final String DB_URL = "jdbc:postgresql://localhost:9999/studs";

    public static DBManager databaseManager;

    private ExecutorService readTheRequest;
    private ForkJoinPool executeRequest;
    private ForkJoinPool answerGet;

    public Server(int port, Scanner scanner) {
        PORT = port;
        this.scanner = scanner;
        initDAO();

        readTheRequest = Executors.newCachedThreadPool();
        executeRequest = ForkJoinPool.commonPool();
        answerGet = ForkJoinPool.commonPool();
    }

    private void initDAO() {
        Console autorization = System.console();
        if (autorization != null) {
            autorization.printf("Enter the username: ");
            String usernameServer = autorization.readLine();
            autorization.printf("Enter the password: ");
            String passwordServer = new String(autorization.readPassword());
            databaseManager = DBManager.getInstance(DB_URL, passwordServer, usernameServer);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the username: ");
            String usernameServer = scanner.nextLine();
            System.out.println("Enter the password: ");
            String passwordServer = scanner.nextLine();
            databaseManager = DBManager.getInstance(DB_URL, passwordServer, usernameServer);
        }
    }

    public void run() {
        // Trying to access to database
        try {
            databaseManager.connectToDatabase();
            System.out.println("Connection to database was successful");
        } catch (SQLException e) {
            System.out.println("Error with connecting to database, exiting from app");
            System.exit(-2);
        }

        // Start working with collection
        try {
            myMap = StartWorkWithCollection.initialization();
            System.out.println("Now you can work with collection");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load all collection from DB to this app
        try {
            databaseManager.loadFullCollection(myMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Start working with requests
        try {
            socket = new DatagramSocket(2467);

            // Console thread to shut down the server
            Runnable userInput = () -> {
                try {
                    while (true) {
                        String[] userCommand = (scanner.nextLine()).split(" ", 2);
                        System.out.println(Arrays.toString(userCommand));
                        if (userCommand[0].equals("save") || userCommand[0].equals("exit")) {
                            if (userCommand[0].equals("save") || userCommand.length == 2) {
                                Execute.execute(true, myMap,
                                        new Scanner("save\n" + userCommand[1] + "\nexit"),
                                        null, null);
                            }
                            if (userCommand[0].equals("exit")) {
                                Execute.execute(true, myMap, new Scanner("exit"),
                                        null, null);
                            }
                        } else {
                            System.out.println("main.Server has command save and command exit as well!");
                        }

                    }
                } catch (Exception e) {
                }
            };
            Thread thread = new Thread(userInput);
            thread.start();

            // executing request
            while (true) {
                clientRequest();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public void clientRequest()
            throws ExitException {
        Report report = null;
        try {
            Callable<Request> taskToRead = this::readRequest;
            Future<Request> requestFuture = readTheRequest.submit(taskToRead);
            while (!requestFuture.isDone()) {

            }

            Runnable taskToExec = () -> {
                try {
                    ExecuteRequest.doingRequest(requestFuture.get(), myMap);   //invoke the command
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            };
            executeRequest.invoke(ForkJoinTask.adapt(taskToExec));

            Callable<Report> response = ExecuteRequest::makeReport;

            report = answerGet.invoke(ForkJoinTask.adapt(response));
        } catch (ExitException e) {
            throw e;
        } finally {
            //Sending a report to client
            byte[] sendBuffer;
            try {
                sendBuffer = serialize(report);
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, PORT);
                socket.send(sendPacket);
                System.out.println("Sending to " + sendPacket.getAddress() + ", message: " +
                        (report == null ? "ERROR" : report.getReportBody()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private Request readRequest() {
        try {
            byte[] accept = new byte[16384];
            DatagramPacket getPacket = new DatagramPacket(accept, accept.length);

            socket.receive(getPacket);          //Getting a new request from client
            address = getPacket.getAddress();   //Save path to client
            PORT = getPacket.getPort();
            return deserialize(getPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Request deserialize(DatagramPacket getPacket) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }

    private <T> byte[] serialize(T toSerialize) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(toSerialize);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }
}
