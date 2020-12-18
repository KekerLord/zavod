package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Employee;
import model.Part;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class Database {
    private static final Gson gson = new Gson();

    private static final String EMPLOYEE_PATH = "employee.json";
    private static final String PART_PATH = "part.json";
    private static final String PRODUCT_PATH = "product.json";

    public static void saveEmployee(Employee employee) throws IOException, IdAlreadyExistsException {
        FileReader fr = new FileReader(EMPLOYEE_PATH);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();
        Collection<Employee> employees = gson.fromJson(fr, collectionType);
        for (Employee e : employees) {
            if (e.getLogin().equals(employee.getLogin()))
                throw new IdAlreadyExistsException();
        }
        employees.add(employee);
        FileWriter fw = new FileWriter(EMPLOYEE_PATH);
        gson.toJson(employees, fw);
        fw.close();
    }

    public static List<Employee> getAllEmployees() throws IOException {
        FileReader fr = new FileReader(EMPLOYEE_PATH);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();
        return gson.fromJson(fr, collectionType);
    }

    public static Employee findEmployeeByLogin(String login) throws FileNotFoundException {
        FileReader fr = new FileReader(EMPLOYEE_PATH);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();
        List<Employee> employees = gson.fromJson(fr, collectionType);
        for (var employee : employees) {
            if (employee.getLogin().equals(login))
                return employee;
        }
        return null;
    }

    public static void deleteEmployeeByLogin(String login) throws IOException {
        FileReader fr = new FileReader(EMPLOYEE_PATH);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();
        List<Employee> employees = gson.fromJson(fr, collectionType);
        employees.removeIf(employee -> employee.getLogin().equals(login));
        FileWriter fw = new FileWriter(EMPLOYEE_PATH);
        gson.toJson(employees, fw);
        fw.close();
    }

    public static void savePart(Part part) throws IOException, IdAlreadyExistsException {
        FileReader fr = new FileReader(PART_PATH);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();
        List<Part> parts = gson.fromJson(fr, collectionType);
        for (Part p : parts) {
            if (p.getId().equals(part.getId()))
                throw new IdAlreadyExistsException();
        }
        parts.add(part);
        FileWriter fw = new FileWriter(PART_PATH);
        gson.toJson(parts, fw);
        fw.close();
    }


    public static List<Part> getAllParts() throws IOException {
        FileReader fr = new FileReader(PART_PATH);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();
        return gson.fromJson(fr, collectionType);
    }

    public static Part findPartById(Long id) throws FileNotFoundException {
        FileReader fr = new FileReader(PART_PATH);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();
        List<Part> parts = gson.fromJson(fr, collectionType);
        for (var part : parts) {
            if (part.getId().equals(id))
                return part;
        }
        return null;
    }

    public static void deletePartById(Long id) throws IOException {
        FileReader fr = new FileReader(PART_PATH);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();
        List<Part> parts = gson.fromJson(fr, collectionType);
        parts.removeIf(part -> part.getId().equals(id));
        FileWriter fw = new FileWriter(PART_PATH);
        gson.toJson(parts, fw);
        fw.close();
    }

    public static List<Product> getAllProducts() throws IOException {
        FileReader fr = new FileReader(PRODUCT_PATH);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();
        return gson.fromJson(fr, collectionType);
    }

    public static Product findProductById(Long id) throws FileNotFoundException {
        FileReader fr = new FileReader(PRODUCT_PATH);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();
        List<Product> products = gson.fromJson(fr, collectionType);
        for (var product : products) {
            if (product.getId().equals(id))
                return product;
        }
        return null;
    }

    public static void deleteProductById(Long id) throws IOException {
        FileReader fr = new FileReader(PRODUCT_PATH);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();
        List<Product> products = gson.fromJson(fr, collectionType);
        products.removeIf(product -> product.getId().equals(id));
        FileWriter fw = new FileWriter(PRODUCT_PATH);
        gson.toJson(products, fw);
        fw.close();
    }

    public static void saveProduct(Product product) throws IOException, IdAlreadyExistsException {
        FileReader fr = new FileReader(PRODUCT_PATH);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();
        List<Product> products = gson.fromJson(fr, collectionType);
        for (Product p : products) {
            if (p.getId().equals(product.getId()))
                throw new IdAlreadyExistsException();
        }
        products.add(product);
        FileWriter fw = new FileWriter(PRODUCT_PATH);
        gson.toJson(products, fw);
        fw.close();
    }


}

