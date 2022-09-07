package main;

import commands.exceptions.ExitException;
import data.netdata.Report;
import data.netdata.ReportState;
import data.netdata.Request;
import data.netdata.exceptions.CreateUserException;
import data.netdata.exceptions.FindUserException;
import data.workwithrequest.ExecuteRequest;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Scanner;
import java.util.Set;

public class Client {
    private static Client client;
    private boolean instanceClient = false;

    private String host;
    private int port;
    private DatagramChannel channel;
    private Selector selector;
    private SocketAddress address;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(16384);
    private Scanner scanner;

    private String loginClient;
    private String passwordClient;

    public Client(String host, int port, Scanner scanner) {
        this.host = host;
        this.port = port;
        this.scanner = scanner;
        client = this;
        //initClientFromConsole();
    }

    public static Client getClient() {
        return client;
    }

    public void initClientFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username: ");
        String username = scanner.nextLine();
        System.out.println("Enter the password: ");
        String password = scanner.nextLine();

        loginClient = username;
        passwordClient = ExecuteRequest.hashPassword(password);

        instanceClient = true;
    }

    public void initClientFromApp(String login, String password)
            throws FindUserException {
        loginClient = login;
        passwordClient = ExecuteRequest.hashPassword(password);

        System.out.println(login);
        System.out.println(password);
        System.out.println(passwordClient);

        instanceClient = true;
        try {
            authorizationRequest(true);
        } catch (FindUserException e) {
            instanceClient = false;
            throw e;
        } catch (Exception e) {
            System.out.println("Экстренный выход (непонятная причина)");
            e.printStackTrace();
            System.exit(-2);
        }
    }

    public void registerClientFromApp(String login, String password)
            throws CreateUserException {
        loginClient = login;
        passwordClient = ExecuteRequest.hashPassword(password);

        System.out.println(login);
        System.out.println(password);
        System.out.println(passwordClient);

        instanceClient = true;
        try {
            authorizationRequest(false);
        } catch (CreateUserException e) {
            instanceClient = false;
            throw e;
        } catch (Exception e) {
            System.out.println("Экстренный выход (непонятная причина)");
            System.exit(-2);
        }
    }

    private void authorizationRequest(boolean signInOrUp)
            throws FindUserException, CreateUserException, IOException, ClassNotFoundException {
        if (signInOrUp) {
            System.out.println("Starting SIGN_IN process");
            sendRequest(new Request("sign_in", ""));
            Report answer = getAnswer();
            System.out.println("\nReceive from server: " + answer.getReportBody());
            ReportState state = answer.getReportState();
            if (state == ReportState.SIGN_IN_ERR) {
                throw new FindUserException(answer.getReportBody());
            } else if (state == ReportState.ERROR) {
                System.out.println("Экстренный выход");
                System.exit(-2);
            } else if (state == ReportState.OK) {
                System.out.println("nice authorization");
            }
        } else {
            System.out.println("Starting SIGN_UP process");
            sendRequest(new Request("create_a_client", ""));
            Report answer = getAnswer();
            System.out.println("\nReceive from server: " + answer.getReportBody());
            ReportState state = answer.getReportState();
            if (state == ReportState.REG_ERR) {
                throw new CreateUserException(answer.getReportBody());
            } else if (state == ReportState.ERROR) {
                System.out.println("Экстренный выход");
                System.exit(-2);
            } else if (state == ReportState.OK) {
                System.out.println("first time entering successful");
            }
        }
    }

    public void run() {
        Request request;
        Report answer;
        try {
            channel = DatagramChannel.open();
            address = new InetSocketAddress("localhost", port);
            channel.connect(address);
            channel.configureBlocking(false);
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_WRITE);


            while (true) {
//                request = new Request(); // new initialization
//                try {
//                    sendRequest(request);
//                } catch (ExitException e) {
//                    System.out.println("End of client life...");
//                    return;
//                }
//                answer = null; // new initialization
//                try {
//                    answer = getAnswer();
//                    System.out.println("Received from server: " + answer.getReportBody());
//                } catch (PortUnreachableException e) {
//                    System.out.println("Server isn't working now");
//                    sendRequest(request);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
            } catch (IOException e) {
                System.out.println("Selector can't be closing");
            }
        }
    }

    public void sendRequest(Request request)
            throws IOException {
        if (request.getCommandName().equals("")) {
            try {
                // Создание запроса с консоли
                request = ExecuteRequest.doingRequest();
            } catch (ExitException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                sendRequest(request);
            }
        }

        request.setLoginClient(loginClient);        // with login
        request.setPasswordClient(passwordClient);  // and password
        byteBuffer = ByteBuffer.wrap(serialize(request));

        // sending from selector with unblocking configuration
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey key : selectionKeys) {
            if (key.isWritable()) {
                DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                datagramChannel.write(byteBuffer);
                datagramChannel.register(selector, SelectionKey.OP_READ);
                break;
            }
        }
        byteBuffer.clear();

    }

    public Report getAnswer()
            throws IOException, ClassNotFoundException {
        byteBuffer = ByteBuffer.allocate(16384);

        DatagramChannel datagramChannel = null;
        while (datagramChannel == null) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                if (key.isReadable()) {
                    datagramChannel = (DatagramChannel) key.channel();
                    datagramChannel.read(byteBuffer);
                    byteBuffer.flip();
                    datagramChannel.register(selector, SelectionKey.OP_WRITE);
                    break;
                }
            }
        }
        return deserialize();
    }

    private byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

    private Report deserialize() throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Report response = (Report) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }

    public String getLoginClient() {
        return loginClient;
    }

    public static Client getInstance() {
        return client;
    }
}
