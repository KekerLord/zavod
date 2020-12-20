package menu.engineer;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import db.Database;
import lombok.AllArgsConstructor;
import model.Employee;
import model.Product;

@AllArgsConstructor
class EngineerMenuProducts {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String NEWLINE = System.getProperty("line.separator");

	private Employee loggedInEmployee;

	public void run() {
		System.out.println("[Список изделий]");

		System.out.println(String.join(NEWLINE, "Выберите действие: ", "(1) Все изделия", "(2) Изменить детали изделия",
				"(0) Назад", "(-) Выход"));
		System.out.printf("%s-%d> ", loggedInEmployee.getPosition().getName().charAt(0), loggedInEmployee.getId());
		String input = scanner.nextLine();
		System.out.println();
		switch (input) {
			case "1":
				allProducts();
				break;
			case "2":
				System.out.println("editProduct()\n");
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
}
