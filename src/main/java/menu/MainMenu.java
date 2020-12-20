package menu;

import java.util.Scanner;

import db.Database;
import menu.admin.AdminMenu;
import menu.engineer.EngineerMenu;
import menu.worker.WorkerMenu;
import model.Employee;
import model.Position;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void run() {
        System.out.println(
                String.join(NEWLINE, "Войти в учетную запись: ", "(1) Администратор", "(2) Сотрудник", "(-) Выход"));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                adminLogin();
                break;
            case "2":
                employeeLogin();
                break;
            case "-":
                System.exit(0);
                break;
            default:
        }
        run();
    }

    private static void adminLogin() {
        System.out.println("Логин: ");
        String login = scanner.nextLine();
        System.out.println("Пароль: ");
        String password = scanner.nextLine();
        if (login.equals("123") && password.equals("123")) {
            System.out.println("Данные введены верно\n");
            AdminMenu.run();
        } else {
            System.out.println("Неверный логин или пароль\n");
        }
    }

    private static void employeeLogin() {
        System.out.println("Логин: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Employee employee = Database.findEmployeeById(id);

            if (employee == null)
                throw new Exception();

            System.out.printf("Вы вошли как %s %n%n", employee);
            if (employee.getPosition().equals(Position.ENGINEER)) {
                EngineerMenu engineerMenu = new EngineerMenu(employee);
                engineerMenu.run();
            } else if (employee.getPosition().equals(Position.WORKER)) {
                WorkerMenu workerMenu = new WorkerMenu(employee);
                workerMenu.run();
            }

        } catch (Exception ignored) {
            System.out.println("Неверные данные\n");
        }
    }

    private MainMenu() {
    }
}
