package menu.worker;

import db.Database;
import lombok.AllArgsConstructor;
import model.Employee;
import model.Product;
import model.ProductPart;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class WorkerMenuAssembly {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    private Employee loggedInEmployee;

    public void run() throws IOException {
        System.out.println("[Список изделий]");

        System.out.println(
                String.join(NEWLINE, "Выберите действие: ", "(1) Все изделия", "(2) Сборка", "(0) Назад", "(-) Выход"));
        System.out.printf("%d> ", loggedInEmployee.getId());
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                allProducts();
                break;
            case "2":
                assembleProduct();
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
        List<Product> products = null;
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

    private static void assembleProduct() throws IOException {
        System.out.println("Введите номер изделия: ");
        Long id = Long.parseLong(scanner.nextLine());
        Product product = Database.findProductById(id);
        if (product == null) {
            System.out.println("[Не найдено]\n");
            return;
        }
        while (true) {
            System.out.println(product);
            System.out.println("Деталь \n(1) установить \n(2) извлечь \n(0) назад");
            String status = scanner.nextLine();
            try {
                switch (status) {
                    case "1":
                        product = installPart(product);
                        Database.updateProduct(product);
                        break;
                    case "2":
                        product = uninstallPart(product);
                        Database.updateProduct(product);
                        break;
                    case "0":
                        return;
                    default:
                }
            } catch (Exception e) {
                System.out.println("[Ошибка]");
            }

        }
    }

    private static Product installPart(Product product) {
        List<ProductPart> parts = product.getParts();
        for (int i = 0; i < parts.size(); ++i) {
            ProductPart p = parts.get(i);
            if (Boolean.FALSE.equals(p.getInstalled())) {
                p.setInstalled(true);
                parts.set(i, p);
                break;
            }
        }
        product.setParts(parts);
        return product;
    }

    private static Product uninstallPart(Product product) {
        List<ProductPart> parts = product.getParts();
        for (int i = parts.size() - 1; i >= 0; --i) {
            ProductPart p = parts.get(i);
            if (Boolean.TRUE.equals(p.getInstalled())) {
                p.setInstalled(false);
                parts.set(i, p);
                break;
            }
        }
        product.setParts(parts);
        return product;
    }
}
