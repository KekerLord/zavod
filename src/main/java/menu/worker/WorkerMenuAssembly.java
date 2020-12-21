package menu.worker;

import db.Database;
import model.Product;


import java.io.IOException;
import java.util.Scanner;

public class WorkerMenuAssembly {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    static void run() throws IOException {
        System.out.println("[Список изделий]");

        System.out.println(String.join(NEWLINE, "Выберите действие: ", "(1) Сборка изделия", "(0) Назад", "(-) Выход"));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                assemblyProduct();
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

    private static void  assemblyProduct() throws IOException {
        System.out.println("Введите номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = Database.findProductById(id);
        System.out.println("Деталь \n(1) установить \n(2) извлечь ");
        String status = scanner.nextLine();
        switch (status) {
            case "1":
                break;
            case "2":
                break;
            default:
                return;
        }
        System.out.println(product);
    }
}



