package menu.worker;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import model.Employee;

@AllArgsConstructor
public class WorkerMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    private Employee loggedInEmployee;

    public void run() {

        System.out.println("[Администратор]");

        System.out.println(String.join(NEWLINE, "(0) Назад", "(-) Выход"));
        System.out.printf("%s-%d> ", loggedInEmployee.getPosition().getName().charAt(0), loggedInEmployee.getId());
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "0":
                return;
            case "-":
                System.exit(0);
                break;
            default:
        }
        run();
    }
}
