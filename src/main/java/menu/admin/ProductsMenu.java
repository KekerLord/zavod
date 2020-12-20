package menu.admin;

import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import db.Database;
import db.IdAlreadyExistsException;

class ProductsMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String NEWLINE = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Список изделий]");

        System.out.println(String.join(NEWLINE, "Выберите действие: ", "(1) Все изделия", "(2) Добавить изделие",
                "(3) Удалить изделие", "(0) Назад", "(-) Выход"));
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

    private static void addProduct() {
        System.out.println("[Добавить изделие]");
        Product product = new Product();
        try {
            System.out.println("Номер: ");
            Long id = Long.parseLong(scanner.nextLine());
            product.setId(id);

            System.out.println("Название изделия: ");
            String input = scanner.nextLine();
            product.setName(input);

            System.out.println("Номера деталей (не число, чтобы сохранить): ");

        } catch (Exception e) {
            return;
        }
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
                System.out.println("[Сохранено]\n");
                return;
            } catch (IdAlreadyExistsException e) {
                System.out.println("Такой номер уже существует\n" + "Введите новый номер:");
                try {
                    Long newId = Long.parseLong(scanner.nextLine());
                    product.setId(newId);
                } catch (Exception ignored) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("[Не сохранено]\n");
    }

    private static void deleteProduct() {
        System.out.println("[Удалить изделие]");

        System.out.println("Номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            Product product = Database.findProductById(id);
            if (product == null) {
                System.out.println("[Не найдено]\n");
                return;
            }

            System.out.println(product);
            System.out.println("Для удаления введите номер (" + product.getId() + ")");
            Long input = Long.parseLong(scanner.nextLine());
            if (input.equals(id)) {
                Database.deleteProductById(id);
                System.out.printf("%d удален %n", id);
            } else {
                System.out.printf("%d Не удален %n", id);
            }
        } catch (Exception e) {
            System.out.println("[Ошибка]\n");
        }
    }

    private ProductsMenu() {
    }
}
