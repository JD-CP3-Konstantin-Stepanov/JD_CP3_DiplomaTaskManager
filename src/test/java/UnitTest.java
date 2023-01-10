import UtilClasses.Todos;
import UtilClasses.TodoServer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Set;
import java.util.TreeSet;

public class UnitTest {
    Set<String> expectedSet = new TreeSet<>();
    TodoServer todoServer = new TodoServer();
    Todos todos = new Todos();

    public String expectedGetAllTasks(Set<String> expectedSet) {
        return String.join(" ", expectedSet);
    }

    @Test
    public void seven_task_added() {
        System.out.println("------------------------Seven task added------------------------");

        expectedSet.add("Уборка");
        expectedSet.add("Плаванье");
        expectedSet.add("Борьба");
        expectedSet.add("Учёба");
        expectedSet.add("Зарядка");
        expectedSet.add("Акробатика");
        expectedSet.add("Пробежка");
        String expected = expectedGetAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        String result = todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Пробежка\"}");
        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void task_two_and_five_removed() {
        System.out.println("------------------------Task two and five removed------------------------");
        expectedSet.add("Уборка");
        expectedSet.add("Плаванье");
        expectedSet.add("Борьба");
        expectedSet.add("Учёба");
        expectedSet.add("Зарядка");
        expectedSet.add("Акробатика");
        expectedSet.add("Пробежка");

        expectedSet.removeIf(todos -> todos.equals("Плаванье") || todos.equals("Зарядка"));

        String expected = expectedGetAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Пробежка\"}");

        todoServer.processClientJson(todos, "{\"type\":\"REMOVE\",\"task\":\"Плаванье\"}");
        String result = todoServer.processClientJson(todos, "{\"type\":\"REMOVE\",\"task\":\"Зарядка\"}");

        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void add_eight_task() {
        System.out.println("------------------------Add eight task------------------------");
        expectedSet.add("Уборка");
        expectedSet.add("Плаванье");
        expectedSet.add("Борьба");
        expectedSet.add("Учёба");
        expectedSet.add("Зарядка");
        expectedSet.add("Акробатика");
        expectedSet.add("Пробежка");
        String expected = expectedGetAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Пробежка\"}");
        String result = todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Поход по магазинам\"}");
        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void remove_absent_task() {
        System.out.println("------------------------Remove absent task------------------------");
        expectedSet.add("Уборка");
        expectedSet.add("Плаванье");
        expectedSet.add("Борьба");
        expectedSet.add("Учёба");
        expectedSet.add("Зарядка");
        expectedSet.add("Акробатика");
        expectedSet.add("Пробежка");

        expectedSet.removeIf(todos -> todos.equals("Теннис"));
        String expected = expectedGetAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Пробежка\"}");

        String result = todoServer.processClientJson(todos, "{\"type\":\"REMOVE\",\"task\":\"Теннис\"}");
        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void restore_test() {
        System.out.println("------------------------RESTORE test------------------------");
        expectedSet.add("Вторая");
        expectedSet.add("Первая");

        String expected = expectedGetAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Первая\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Вторая\"}");
        todoServer.processClientJson(todos, "{\"type\":\"REMOVE\",\"task\":\"Первая\"}");
        todoServer.processClientJson(todos, "{\"type\":\"ADD\",\"task\":\"Третья\"}");
        todoServer.processClientJson(todos, "{\"type\":\"RESTORE\"}");
        String result = todoServer.processClientJson(todos, "{\"type\":\"RESTORE\"}");

        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }
}
