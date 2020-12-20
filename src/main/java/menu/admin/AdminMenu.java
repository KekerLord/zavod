package menu.admin;

import java.util.Scanner;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void run() {
        System.out.println("[Администратор]");

        System.out.println(String.join(NEWLINE, "(1) Список изделий", "(2) Список деталей", "(3) Список рабочих",
                "(0) Назад", "(-) Выход"));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                AdminMenuProducts.run();
                break;
            case "2":
                AdminMenuParts.run();
                break;
            case "3":
                AdminMenuEmployees.run();
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
