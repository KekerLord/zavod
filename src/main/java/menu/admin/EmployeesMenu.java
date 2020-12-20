package menu.admin;

import model.Employee;
import model.Position;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import db.Database;
import db.IdAlreadyExistsException;

class EmployeesMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String NEWLINE = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Список рабочих]");

        System.out.println(String.join(NEWLINE, "Выберите действие: ", "(1) Все сотрудники", "(2) Добавить сотрудника",
                "(3) Удалить сотрудника", "(0) Назад", "(-) Выход"));

        String input = scanner.nextLine();
        System.out.println();
        switch (input) {
            case "1":
                allEmployees();
                break;
            case "2":
                addEmployee();
                break;
            case "3":
                deleteEmployee();
                break;
            case "-":
                System.exit(0);
                break;
            case "0":
                return;
            default:
        }

        run();
    }

    private static void allEmployees() {
        System.out.println("[Все сотрудники]");
        Collection<Employee> employees = null;
        try {
            employees = Database.getAllEmployees();
        } catch (IOException e) {
            System.out.println("[Ошибка]\n");
        }
        if (employees == null)
            throw new AssertionError();
        for (var emp : employees)
            System.out.println(emp);
        System.out.println();
    }

    private static void addEmployee() {
        System.out.println("[Добавить сотрудника]");

        Employee employee = new Employee();

        try {
            System.out.println("Логин: ");
            Long id = Long.parseLong(scanner.nextLine());
            employee.setId(id);

            System.out.println("Фамилия: ");
            String lastName = scanner.nextLine();
            employee.setLastName(lastName);

            System.out.println("Имя: ");
            String firstName = scanner.nextLine();
            employee.setFirstName(firstName);

            System.out.println("Отчество: ");
            String patronymic = scanner.nextLine();
            employee.setPatronymic(patronymic);

            System.out.println("Роль \n(1) рабочий, \n(2) инженер: ");
            String position = scanner.nextLine();
            switch (position) {
                case "1":
                    employee.setPosition(Position.WORKER);
                    break;
                case "2":
                    employee.setPosition(Position.ENGINEER);
                    break;
                default:
                    System.out.println("[Неверная роль]\n");
                    return;
            }
        } catch (Exception e) {
            return;
        }

        for (int i = 0; i < 3; ++i) {
            try {
                Database.saveEmployee(employee);
                System.out.println("[Сохранено]\n");
                return;
            } catch (IdAlreadyExistsException e) {
                System.out.println("Такой номер уже существует\n" + "Введите новый номер:");
                try {
                    Long newId = Long.parseLong(scanner.nextLine());
                    employee.setId(newId);
                } catch (Exception ignored) {
                    return;
                }
            } catch (Exception e) {
                return;
            }
        }
        System.out.println("[Не сохранено]\n");
    }

    private static void deleteEmployee() {
        System.out.println("[Удалить сотрудника]");

        System.out.println("Номер сотрудника: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            Employee employee = Database.findEmployeeById(id);
            if (employee == null) {
                System.out.println("[Не найдено]\n");
                return;
            }
            System.out.println(Database.findEmployeeById(id));
            System.out.println("Для удаления введите номер (" + employee.getId() + ")");
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

    private EmployeesMenu() {
    }
}
