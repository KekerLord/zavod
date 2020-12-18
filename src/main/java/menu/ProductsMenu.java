package menu;

import model.Product;
import util.Database;
import util.IdAlreadyExistsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

class ProductsMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String newLine = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Список изделий]");

        System.out.println(String.join(newLine,
                "Выберите действие: ",
                "(1) Все изделия",
                "(2) Добавить изделие",
                "(3) Удалить изделие",
                "(0) Назад"
        ));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                allProducts();
                break;
            case "2":
                addProduct();
                break;
            case "3":
                deleteProduct();
                break;
            case "0":
                return;
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
            System.out.println("[Ошибка]");
        }
        if (products == null) throw new AssertionError();
        for (var emp : products)
            System.out.println(emp);
    }

    private static void addProduct() {
        System.out.println("[Добавить изделие]");
        Product product = new Product();

        System.out.println("Номер: ");
        Long id = Long.parseLong(scanner.nextLine());
        product.setId(id);

        System.out.println("Название изделия: ");
        String input = scanner.nextLine();
        product.setName(input);


        System.out.println("Номера деталей (не число, чтобы сохранить): ");
        List<Long> parts = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            try {
                Long partId = Long.parseLong(scanner.nextLine());
                parts.add(partId);
            } catch (Exception e) {
                break;
            }
        }
        product.setParts(parts);

        for (int i = 0; i < 3; ++i) {
            try {
                Database.saveProduct(product);
                System.out.println("Сохранено");
                return;
            } catch (IdAlreadyExistsException e) {
                System.out.println("Такой номер уже существует\n" +
                        "Введите новый номер:");
                Long newId = Long.parseLong(scanner.nextLine());
                product.setId(newId);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Не сохранено");
    }

    private static void deleteProduct() {
        System.out.println("[Удалить изделие]");

        System.out.println("Номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            System.out.println(Database.findProductById(id));
            System.out.println("Вы уверены? (да/Нет)");
            String input = scanner.nextLine();
            if ("да".equals(input)) {
                Database.deleteProductById(id);
                System.out.printf("%d удален \n", id);
            }
        } catch (Exception e) {
            System.out.println("[Ошибка]");
        }
    }
}
