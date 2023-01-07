import UtilClasses.TodoServer;
import UtilClasses.Todos;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;

public class UnitTest {
    protected final HashSet<Todos> expectedSet = new HashSet<>();

    @Test
    public void seven_task_added() {
        System.out.println("------------------------Seven task added------------------------");
        expectedSet.add(new Todos("Уборка"));
        expectedSet.add(new Todos("Плаванье"));
        expectedSet.add(new Todos("Борьба"));
        expectedSet.add(new Todos("Учёба"));
        expectedSet.add(new Todos("Зарядка"));
        expectedSet.add(new Todos("Акробатика"));
        expectedSet.add(new Todos("Пробежка"));
        String expected = new Todos().getAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        TodoServer todoServer = new TodoServer();
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        String result = todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Пробежка\"}");
        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void task_two_and_five_removed() {
        System.out.println("------------------------Task two and five removed------------------------");
        expectedSet.add(new Todos("Уборка"));
        expectedSet.add(new Todos("Плаванье"));
        expectedSet.add(new Todos("Борьба"));
        expectedSet.add(new Todos("Учёба"));
        expectedSet.add(new Todos("Зарядка"));
        expectedSet.add(new Todos("Акробатика"));
        expectedSet.add(new Todos("Пробежка"));

        expectedSet.removeIf(todos -> todos.getTask().equals("Плаванье") || todos.getTask().equals("Зарядка"));

        String expected = new Todos().getAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        TodoServer todoServer = new TodoServer();
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Пробежка\"}");

        todoServer.processClientJson(new Todos(),"{\"type\":\"REMOVE\",\"task\":\"Плаванье\"}");
        String result = todoServer.processClientJson(new Todos(),"{\"type\":\"REMOVE\",\"task\":\"Зарядка\"}");

        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void add_eight_task() {
        System.out.println("------------------------Add eight task------------------------");
        expectedSet.add(new Todos("Уборка"));
        expectedSet.add(new Todos("Плаванье"));
        expectedSet.add(new Todos("Борьба"));
        expectedSet.add(new Todos("Учёба"));
        expectedSet.add(new Todos("Зарядка"));
        expectedSet.add(new Todos("Акробатика"));
        expectedSet.add(new Todos("Пробежка"));
        String expected = new Todos().getAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        TodoServer todoServer = new TodoServer();
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Пробежка\"}");
        String result = todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Поход по магазинам\"}");
        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void remove_absent_task() {
        System.out.println("------------------------Remove absent task------------------------");
        expectedSet.add(new Todos("Уборка"));
        expectedSet.add(new Todos("Плаванье"));
        expectedSet.add(new Todos("Борьба"));
        expectedSet.add(new Todos("Учёба"));
        expectedSet.add(new Todos("Зарядка"));
        expectedSet.add(new Todos("Акробатика"));
        expectedSet.add(new Todos("Пробежка"));

        expectedSet.removeIf(todos -> todos.getTask().equals("Теннис"));
        String expected = new Todos().getAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        TodoServer todoServer = new TodoServer();
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Уборка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Плаванье\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Борьба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Учёба\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Зарядка\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Акробатика\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Пробежка\"}");

        String result = todoServer.processClientJson(new Todos(),"{\"type\":\"REMOVE\",\"task\":\"Теннис\"}");
        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void restore_test() {
        System.out.println("------------------------RESTORE test------------------------");
        expectedSet.add(new Todos("Вторая"));
        expectedSet.add(new Todos("Первая"));

        String expected = new Todos().getAllTasks(expectedSet);
        System.out.println("Expected: " + expected);

        TodoServer todoServer = new TodoServer();
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Первая\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Вторая\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"REMOVE\",\"task\":\"Первая\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"ADD\",\"task\":\"Третья\"}");
        todoServer.processClientJson(new Todos(),"{\"type\":\"RESTORE\"}");
        String result = todoServer.processClientJson(new Todos(),"{\"type\":\"RESTORE\"}");

        System.out.println("Result: " + result);
        Assertions.assertEquals(expected, result);
    }
}
