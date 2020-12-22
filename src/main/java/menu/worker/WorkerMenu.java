package menu.worker;

import java.io.IOException;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import model.Employee;

@AllArgsConstructor
public class WorkerMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    private Employee loggedInEmployee;

    public void run() throws IOException {

        System.out.println("[Рабочий]");

        System.out.println(String.join(NEWLINE, "(1) Список изделий", "(0) Назад", "(-) Выход"));
        System.out.printf("%d> ", loggedInEmployee.getId());
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                WorkerMenuAssembly workerMenuAssembly = new WorkerMenuAssembly(loggedInEmployee);
                workerMenuAssembly.run();
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
}
