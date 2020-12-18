package menu;

import model.Employee;
import model.Position;
import util.Database;
import util.IdAlreadyExistsException;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

class EmployeesMenu {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String newLine = System.getProperty("line.separator");

    static void run() {
        System.out.println("[Список рабочих]");

        System.out.println(String.join(newLine,
                "Выберите действие: ",
                "(1) Все сотрудники",
                "(2) Добавить сотрудника",
                "(3) Удалить сотрудника",
                "(0) Назад"
        ));

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
            System.out.println("[Ошибка]");
        }
        if (employees == null) throw new AssertionError();
        for (var emp : employees)
            System.out.println(emp);
    }

    private static void addEmployee() {
        System.out.println("[Добавить сотрудника]");

        Employee employee = new Employee();

        System.out.println("Логин: ");
        String input = scanner.nextLine();
        employee.setLogin(input);

        System.out.println("Фамилия: ");
        input = scanner.nextLine();
        employee.setLastname(input);

        System.out.println("Имя: ");
        input = scanner.nextLine();
        employee.setFirstname(input);

        System.out.println("Отчество: ");
        input = scanner.nextLine();
        employee.setPatronymic(input);

        System.out.println("Роль (рабочий, инженер): ");
        input = scanner.nextLine();
        switch (input) {
            case "рабочий":
                employee.setPosition(Position.WORKER);
                break;
            case "инженер":
                employee.setPosition(Position.ENGINEER);
                break;
            default:
                System.out.println("Неверная роль");
                return;
        }

        for (int i = 0; i < 3; ++i) {
            try {
                Database.saveEmployee(employee);
                System.out.println("Сохранено");
                return;
            } catch (IdAlreadyExistsException e) {
                System.out.println("Такой логин уже существует\n" +
                        "Введите новый логин:");
                String newLogin = scanner.nextLine();
                employee.setLogin(newLogin);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Не сохранено");
    }

    private static void deleteEmployee() {
        System.out.println("[Удалить сотрудника]");

        System.out.println("Номер сотрудника: ");
        String login = scanner.nextLine();
        try {
            System.out.println(Database.findEmployeeByLogin(login));
            System.out.println("Вы уверены? (да/Нет)");
            String input = scanner.nextLine();
            if ("да".equals(input)) {
                Database.deleteEmployeeByLogin(login);
                System.out.printf("%s удален \n", login);
            }
        } catch (Exception e) {
            System.out.println("[Ошибка]");
        }
    }
}
