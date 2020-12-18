package menu;

import java.util.Scanner;

class AdminMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String newLine = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Администратор]");

        System.out.println(String.join(newLine,
                "(1) Список изделий",
                "(2) Список деталей",
                "(3) Список рабочих",
                "(0) Назад"
        ));
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
            default:
        }
        run();
    }
}
