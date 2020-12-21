package menu.engineer;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import db.Database;
import lombok.AllArgsConstructor;
import model.*;

@AllArgsConstructor
class EngineerMenuProducts {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    private Employee loggedInEmployee;

    public void run() throws IOException {
        System.out.println("[Список изделий]");

        System.out.println(String.join(NEWLINE, "Выберите действие: ", "(1) Все изделия", "(2) Изменить детали изделия",
                "(3) Состояние изделия", "(4) Приём изделий",
                "(0) Назад", "(-) Выход"));
        System.out.printf("%d> ", loggedInEmployee.getId());
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                allProducts();
                break;
            case "2":
                editProduct();
                break;
            case "3":
                statusProduct();
                break;
            case "4":
                checkProduct();
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

    private static void allProducts() {
        System.out.println("[Все изделия]");
        Collection<Product> products = null;
        try {
            products = Database.getAllProducts();
        } catch (IOException e) {
            System.out.println("[Ошибка]\n");
        }
        if (products == null)
            throw new AssertionError();
        for (var p : products)
            System.out.println(p);
        System.out.println();
    }

    private static void editProduct() throws IOException {
        System.out.println("Введите номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = Database.findProductById(id);
        System.out.println("Номера деталей (не число, чтобы сохранить): ");
        List<ProductPart> parts = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            try {
                Long partId = Long.parseLong(scanner.nextLine());
                parts.add(new ProductPart(partId));
            } catch (Exception e) {
                break;
            }
        }
        product.setParts(parts);
        System.out.println(product);
    }

    private static void statusProduct() throws IOException {
        System.out.println("Введите номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = Database.findProductById(id);

    }

    private static void checkProduct() throws IOException {
        System.out.println("Введите номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = Database.findProductById(id);
        System.out.println("Статус \n(1) готово \n(2) брак ");
        String status = scanner.nextLine();
        switch (status) {
            case "1":
                product.setStatus(ProductStatus.ACCEPTED);
                break;
            case "2":
                product.setStatus(ProductStatus.REJECTED);
                break;
            default:
                System.out.println("[Неверный статус]\n");
                return;
        }
        System.out.println(product);
    }
}


