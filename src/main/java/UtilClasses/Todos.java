package UtilClasses;

public class Todos {
    private final String task;

    public Todos(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return task;
    }
}