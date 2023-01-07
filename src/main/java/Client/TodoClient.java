package Client;

import UtilClasses.Todos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TodoClient extends Todos {
    protected String type;

    public TodoClient(String task, String type) {
        super(task);
        this.type = type;
    }

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(reader.readLine());

            System.out.println("Введите тип операции (ADD, REMOVE, RESTORE):");
            String typeJson = scanner.nextLine().toUpperCase();

            String taskJson = null;
            if (!typeJson.equals("RESTORE")) {
                System.out.println("Введите задачу:");
                taskJson = scanner.nextLine();
                taskJson = taskJson.substring(0, 1).toUpperCase() + taskJson.substring(1).toLowerCase();
            }

            String jsonRequest = formJson(typeJson, taskJson);
            writer.println(jsonRequest);
            writer.flush();

            String jsonAnswer = reader.readLine();
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Результат:");
            System.out.println(jsonAnswer);
            System.out.println("---------------------------------------------------------------------------");

        } catch (IOException ex) {
            System.out.println("Не могу подключиться к серверу");
            ex.printStackTrace();
        }

    }

    public static String formJson(String type, String task) {
        TodoClient gsonClient = new TodoClient(task, type);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(gsonClient);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getTask() + " " + type;
    }
}