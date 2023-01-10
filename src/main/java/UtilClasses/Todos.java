package UtilClasses;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    final int MAX_TASK_SIZE = 7;
    private final Set<String> setTodos;

    public Todos() {
        this.setTodos = new TreeSet<>();
    }

    public void addTask(String task) {
        if (setTodos.size() < MAX_TASK_SIZE) {
            setTodos.add(task);
        }
    }

    public void removeTask(String task) {
        Iterator<String> iterator = setTodos.iterator();
        while (iterator.hasNext()) {
            String todos = iterator.next();
            if (todos.equals(task)
            ) {
                iterator.remove();
                break;
            }
        }
    }

    public String getAllTasks() {
        return setTodos
                .stream()
                .collect(Collectors.joining(" "));
    }

    public void restoreTask(String task, String type) {
        if (type.equals("ADD")) {
            removeTask(task);
        } else if (type.equals("REMOVE")) {
            addTask(task);
        }
    }
}
