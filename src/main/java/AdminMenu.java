import model.Employee;
import model.Position;

import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);
    static String newLine = System.getProperty("line.separator");

    static void run() {
        System.out.println(String.join(newLine,
                "Выберите действие: ",
                "(1) Список изделий",
                "(2) Список деталей",
                "(3) Список рабочих"
        ));
        Integer input = Integer.parseInt(scanner.nextLine());
        System.out.println(input);
        switch (input) {
            case 1:
                System.out.println("Список изделий");
                break;
            case 2:
                System.out.println("Список деталей");
                break;
            case 3:
                System.out.println("Список рабочих");
                Employee tmp = new Employee();
                tmp.setFirstname("Юрий");
                tmp.setLastname("Кругов");
                tmp.setLogin("w-0007");
                tmp.setPatronymic("Чьме");
                tmp.setPosition(Position.WORKER);
                System.out.println(tmp);
                break;
            default:
                run();
        }
    }
}
