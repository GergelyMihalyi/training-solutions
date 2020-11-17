package intromethods;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Todo> todos = new ArrayList<>();

    public void addTodo(String caption) {
        Todo todo = new Todo(caption);
        todos.add(todo);
    }

    public void finishTodos(String caption) {
        for (Todo todo : todos) {
            if (todo.getCaption().equals(caption)) {
                todo.finish();
            }
        }
    }

    public void finishAllTodos(List<String> todosToFinish) {
        for (String caption : todosToFinish) {
            finishTodos(caption);
        }

    }

    public List<String> todosToFinish() {
        List<String> unfinishedTodos = new ArrayList<>();
        for (Todo todo : todos) {
            if (!todo.isFinished()) {
                unfinishedTodos.add(todo.getCaption());
            }
        }
        return unfinishedTodos;
    }

    public int numberOfFinishedTodos() {
        int i = 0;
        for (Todo todo : todos) {
            if (todo.isFinished()) {
                i++;
            }
        }
        return i;
    }
}
