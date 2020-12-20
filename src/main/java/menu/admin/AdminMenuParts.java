package menu.admin;

import model.Part;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import db.Database;
import db.IdAlreadyExistsException;

class AdminMenuParts {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Список деталей]");

        System.out.println(String.join(NEWLINE, "Выберите действие: ", "(1) Все детали", "(2) Добавить деталь",
                "(3) Удалить деталь", "(0) Назад", "(-) Выход"));
        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                allParts();
                break;
            case "2":
                addPart();
                break;
            case "3":
                deletePart();
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

    private static void allParts() {
        System.out.println("[Все детали]");

        Collection<Part> parts = null;
        try {
            parts = Database.getAllParts();
        } catch (IOException e) {
            System.out.println("[Ошибка]\n");
        }
        if (parts == null)
            throw new AssertionError();
        for (var emp : parts)
            System.out.println(emp);
        System.out.println();
    }

    private static void addPart() {
        System.out.println("[Добавить деталь]");

        Part part = new Part();

        System.out.println("Номер детали: ");
        try {
            String input = scanner.nextLine();
            part.setId(Long.parseLong(input));

            System.out.println("Название детали: ");
            input = scanner.nextLine();
            part.setName(input);

            System.out.println("Количество деталей на складе: ");
            input = scanner.nextLine();
            part.setQuantity(Long.parseLong(input));
        } catch (Exception e) {
            return;
        }

        for (int i = 0; i < 3; ++i) {
            try {
                Database.savePart(part);
                System.out.println("[Сохранено]\n");
                return;
            } catch (IdAlreadyExistsException e) {
                System.out.println("Такой номер уже существует\n" + "Введите новый номер:");
                try {
                    Long newId = Long.parseLong(scanner.nextLine());
                    part.setId(newId);
                } catch (Exception ignored) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("[Не сохранено]\n");
    }

    private static void deletePart() {
        System.out.println("[Удалить деталь]");

        System.out.println("Номер детали: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            Part part = Database.findPartById(id);
            if (part == null) {
                System.out.println("[Не найдено]\n");
                return;
            }

            System.out.println(part);
            System.out.println("Для удаления введите номер (" + part.getId() + ")");
            Long input = Long.parseLong(scanner.nextLine());
            if (input.equals(id)) {
                Database.deletePartById(id);
                System.out.printf("%d удален %n", id);
            } else {
                System.out.printf("%d Не удален %n", id);
            }
        } catch (Exception e) {
            System.out.println("[Ошибка]\n");
        }

    }

    private AdminMenuParts() {
    }
}
