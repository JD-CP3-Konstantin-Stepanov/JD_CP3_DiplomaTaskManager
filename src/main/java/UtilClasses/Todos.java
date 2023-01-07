package UtilClasses;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private String task;

    public String getTask() {
        return task;
    }

    public Todos(String task) {
        this.task = task;
    }

    public Todos() {
    }

    public void addTask(String task, HashSet<String> uniqueSet, HashSet<Todos> setTodos) {
        if (uniqueSet.add(task) && uniqueSet.size() <= 7) {
            setTodos.add(new Todos(task));
        }
    }

    public void removeTask(String task, HashSet<Todos> setTodos) {
        Iterator<Todos> iterator = setTodos.iterator();
        while (iterator.hasNext()) {
            Todos todos = iterator.next();
            if (todos.getTask().equals(task)) {
                iterator.remove();
                break;
            }
        }
    }

    public String getAllTasks(HashSet<Todos> setTodos) {
        return setTodos.stream()
                .sorted(Comparator.comparing(Todos::getTask))
                .map(Todos::getTask)
                .collect(Collectors.joining(" "));
    }

    public void restoreTask(String task, String type, HashSet<Todos> setTodos, HashSet<String> uniqueSet) {
        if (type.equals("ADD")) {
            removeTask(task, setTodos);
        } else if (type.equals("REMOVE")) {
            addTask(task, uniqueSet, setTodos);
        }
    }

    @Override
    public String toString() {
        return task;
    }
}