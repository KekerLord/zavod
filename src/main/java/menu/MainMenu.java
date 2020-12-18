package menu;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String newLine = System.getProperty("line.separator");

    public static void run() {
        System.out.println(String.join(newLine,
                "Войти в учетную запись: ",
                "(1) Администратор",
                "(2) Сотрудник",
                "(0) Выход"
        ));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                adminLogin();
                break;
            case "2":
                employeeLogin();
                break;
            case "0":
                System.out.println("[Выход]");
                return;
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
}
