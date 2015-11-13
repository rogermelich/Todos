package com.iesebre.dam2.roger.todos;

/**
 * Created by roger on 13/11/15.
 */
public class TodoItem {

    private String name;
    private boolean done;
    private int priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
