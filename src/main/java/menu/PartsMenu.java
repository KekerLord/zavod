package menu;

import model.Part;
import util.Database;
import util.IdAlreadyExistsException;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

class PartsMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String newLine = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Список деталей]");

        System.out.println(String.join(newLine,
                "Выберите действие: ",
                "(1) Все детали",
                "(2) Добавить деталь",
                "(3) Удалить деталь",
                "(0) Назад"
        ));
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
            System.out.println("[Ошибка]");
        }
        if (parts == null) throw new AssertionError();
        for (var emp : parts)
            System.out.println(emp);
    }
    private static void addPart() {
        System.out.println("[Добавить деталь]");

        Part part = new Part();

        System.out.println("Номер детали: ");
        String input = scanner.nextLine();
        part.setId(Long.parseLong(input));

        System.out.println("Название детали: ");
        input = scanner.nextLine();
        part.setName(input);

        System.out.println("Количество деталей на складе: ");
        input = scanner.nextLine();
        part.setQuantity(Long.parseLong(input));

        for (int i = 0; i < 3; ++i) {
            try {
                Database.savePart(part);
                System.out.println("Сохранено");
                return;
            } catch (IdAlreadyExistsException e) {
                System.out.println("Такой номер уже существует\n" +
                        "Введите новый номер:");
                Long newId = Long.parseLong(scanner.nextLine());
                part.setId(newId);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Не сохранено");
    }

    private static void deletePart() {
        System.out.println("[Удалить деталь]");

        System.out.println("Номер детали: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            System.out.println(Database.findPartById(id));
            System.out.println("Вы уверены? (да/Нет)");
            String input = scanner.nextLine();
            if ("да".equals(input)) {
                Database.deletePartById(id);
                System.out.printf("%d удален \n", id);
            }
        } catch (Exception e) {
            System.out.println("[Ошибка]");
        }

    }
}
