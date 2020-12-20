package menu;

import java.util.Scanner;

import menu.admin.AdminMenu;

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
            System.out.println("Неверный логин или пароль");
        }
    }

    private static void employeeLogin() {
        System.out.println("Логин: ");
        String login = scanner.nextLine();
        if (login.equals("e-0001")) {
            System.out.println("Вы - инженер");
        } else if (login.equals("w-0007")) {
            System.out.println("Вы - рабочий");
        } else {
            System.out.println("Неверные данные");
        }
    }

    private MainMenu() {
    }
}
