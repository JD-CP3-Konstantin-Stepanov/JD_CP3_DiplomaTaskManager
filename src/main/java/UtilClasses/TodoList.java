package UtilClasses;

import java.util.*;
import java.util.stream.Collectors;

public class TodoList {
    private final HashSet<String> uniqueSet;
    private final HashSet<Todos> setTodos;

    public TodoList() {
        this.uniqueSet = new HashSet<>();
        this.setTodos = new HashSet<>();
    }


    public void addTask(String task) {
        if (uniqueSet.add(task) && uniqueSet.size() <= 7) {
            setTodos.add(new Todos(task));
        }
    }

    public void removeTask(String task) {
        Iterator<Todos> iterator = setTodos.iterator();
        while (iterator.hasNext()) {
            Todos todos = iterator.next();
            if (todos.getTask().equals(task)) {
                iterator.remove();
                break;
            }
        }
        uniqueSet.remove(task);
    }

    public String getAllTasks() {
        return setTodos.stream()
                .sorted(Comparator.comparing(Todos::getTask))
                .map(Todos::getTask)
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
