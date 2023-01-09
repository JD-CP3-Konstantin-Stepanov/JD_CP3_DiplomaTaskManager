package UtilClasses;

import Client.TodoClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.LinkedList;
import java.util.List;

public class TodoServer {
    private final List<TodoClient> restoreSet = new LinkedList<>();

    public TodoServer() {
    }

    public String processClientJson(TodoList todoList, String jsonFile) {
        JSONParser parser = new JSONParser();
        String type;
        String task;
        try {
            Object obj = parser.parse(jsonFile);
            JSONObject ParsedJson = (JSONObject) obj;

            type = (String) ParsedJson.get("type");
            task = (String) ParsedJson.get("task");

        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }

        switch (type) {
            case "ADD" -> {
                todoList.addTask(task);
                restoreSet.add(new TodoClient(type, task));
            }
            case "REMOVE" -> {
                todoList.removeTask(task);
                restoreSet.add(new TodoClient(type, task));
            }
            case "RESTORE" -> {
                if (restoreSet.size() != 0) {
                    int index = restoreSet.size() - 1;
                    TodoClient todoClient = restoreSet.get(index);
                    todoList.restoreTask(todoClient.getType(), todoClient.getTask());
                    restoreSet.remove(index);
                }
            }
        }

        return todoList.getAllTasks();
    }

}
