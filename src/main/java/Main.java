import UtilClasses.TodoList;
import UtilClasses.TodoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8989)) {
            System.out.println("Сервер запущен");
            TodoServer todoServer = new TodoServer();
            TodoList todoList = new TodoList();
            while (true) {
                try (Socket clientSrv = server.accept();
                     PrintWriter writer = new PrintWriter(clientSrv.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSrv.getInputStream()))) {

                    writer.println("Произведено подключение к серверу");
                    writer.flush();

                    String jsonRequest = reader.readLine();
                    if (jsonRequest == null || jsonRequest.equals("")) {
                        continue;
                    } else {
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("JSON клиента:");
                        System.out.println(jsonRequest);
                    }

                    writer.print(todoServer.processClientJson(todoList, jsonRequest));
                    writer.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}