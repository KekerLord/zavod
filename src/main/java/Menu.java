import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static String newLine = System.getProperty("line.separator");

    static void adminLogin() {
        System.out.println("Логин: ");
        String login = scanner.nextLine();
        System.out.println("Пароль: ");
        String password = scanner.nextLine();
        if (login.equals("admin") && password.equals("admin")) {
            System.out.println("Данные введены верно");
            AdminMenu.run();
        } else {
            System.out.println("Неверный логин или пароль");
            adminLogin();
        }

    }

    static void employeeLogin() {
        System.out.println("Логин: ");
        String login = scanner.nextLine();
        if (login.equals("e-0001")) {
            System.out.println("Вы инженер 🥰🥰🥰");
        } else if (login.equals("w-0001")){
            System.out.println("Вы работяга 🥰🥰🥰");
        } else {
            System.out.println("Неверные данные");
            employeeLogin();
        }
    }

    public static void run() {
        System.out.println(String.join(newLine,
                "Войти в учетную запись: ",
                "(1) Администратор",
                "(2) Сотрудник"
        ));
        Integer input = Integer.parseInt(scanner.nextLine());
        System.out.println(input);
        switch (input) {
            case 1:
                adminLogin();
                break;
            case 2:
                employeeLogin();
                break;
            default:
                run();
        }
    }
}
