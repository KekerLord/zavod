package menu.admin;

import java.util.Scanner;

public class AdminMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String NEWLINE = System.getProperty("line.separator");

    public static void run() {
        System.out.println("[Администратор]");

        System.out.println(
                String.join(NEWLINE, "(1) Список изделий", "(2) Список деталей", "(3) Список рабочих", "(0) Назад", "(-) Выход"));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                ProductsMenu.run();
                break;
            case "2":
                PartsMenu.run();
                break;
            case "3":
                EmployeesMenu.run();
                break;
            case "0":
                return;
            case "-":
                System.exit(0);
                break;
            default:
        }
        run();
    }

    private AdminMenu() {
    }
}
