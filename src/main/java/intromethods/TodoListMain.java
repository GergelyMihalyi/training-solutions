package intromethods;

import java.util.Arrays;

public class TodoListMain {

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        todoList.addTodo("learn Java");
        todoList.addTodo("learn Python");
        todoList.addTodo("learn Php");
        todoList.addTodo("learn JavaScript");
        System.out.println(todoList.numberOfFinishedTodos());
        System.out.println(todoList.todosToFinish());
        todoList.finishTodos("learn Php");
        System.out.println(todoList.numberOfFinishedTodos());
        System.out.println(todoList.todosToFinish());
        todoList.finishAllTodos(Arrays.asList(new String[]{"learn Python", "learn JavaScript"}));
        System.out.println(todoList.numberOfFinishedTodos());
        System.out.println(todoList.todosToFinish());

    }
}
