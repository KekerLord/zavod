package db;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Employee;
import model.Entity;
import model.Part;
import model.Product;

public class Database {
    private static final Gson gson = new Gson();

    private static File getDatabaseFile(DatabasePaths path) throws IOException {
        File file = new File(path.getFilePath());
        boolean created = file.createNewFile();
        if (created) {
            try (FileWriter fw = new FileWriter(file)) {
                gson.toJson(new ArrayList<>(), fw);
            }
        }
        return file;
    }

    public static <T extends Entity> void saveEntity(Type collectionType, T object, File file)
            throws IOException, IdAlreadyExistsException {
        try (FileReader fr = new FileReader(file)) {
            List<T> objects = gson.fromJson(fr, collectionType);
            try (FileWriter fw = new FileWriter(file)) {
                for (T o : objects) {
                    if (o.getId().equals(object.getId())) {
                        gson.toJson(objects, fw);
                        throw new IdAlreadyExistsException();
                    } 
                }
                objects.add(object);

                gson.toJson(objects, fw);
            }
        }
    }

    public static <T extends Entity> void deleteEntityById(Type collectionType, Long id, File file) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            List<T> objects = gson.fromJson(fr, collectionType);
            objects.removeIf(object -> object.getId().equals(id));

            try (FileWriter fw = new FileWriter(file)) {
                gson.toJson(objects, fw);
            }
        }
    }

    public static <T extends Entity> T findEntityById(Long id, File file, Type collectionType) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            List<T> products = gson.fromJson(fr, collectionType);
            for (var product : products) {
                if (product.getId().equals(id))
                    return product;
            }
        }
        return null;
    }

    public static <T extends Entity> List<T> getAllEntities(File file, Type collectionType) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            return gson.fromJson(fr, collectionType);
        }
    }

    public static void saveEmployee(Employee employee) throws IOException, IdAlreadyExistsException {
        File file = getDatabaseFile(DatabasePaths.EMPLOYEES);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();

        saveEntity(collectionType, employee, file);
    }

    public static List<Employee> getAllEmployees() throws IOException {
        File file = getDatabaseFile(DatabasePaths.EMPLOYEES);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();

        return getAllEntities(file, collectionType);
    }

    public static Employee findEmployeeById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.EMPLOYEES);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();

        return findEntityById(id, file, collectionType);
    }

    public static void deleteEmployeeByLogin(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.EMPLOYEES);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();

        deleteEntityById(collectionType, id, file);
    }

    public static void savePart(Part part) throws IOException, IdAlreadyExistsException {
        File file = getDatabaseFile(DatabasePaths.PARTS);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();

        saveEntity(collectionType, part, file);
    }

    public static List<Part> getAllParts() throws IOException {
        File file = getDatabaseFile(DatabasePaths.PARTS);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();

        return getAllEntities(file, collectionType);
    }

    public static Part findPartById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PARTS);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();

        return findEntityById(id, file, collectionType);
    }

    public static void deletePartById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PARTS);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();

        deleteEntityById(collectionType, id, file);
    }

    public static List<Product> getAllProducts() throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        return getAllEntities(file, collectionType);
    }

    public static Product findProductById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();
        return findEntityById(id, file, collectionType);
    }

    public static void deleteProductById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        deleteEntityById(collectionType, id, file);
    }

    public static void saveProduct(Product product) throws IOException, IdAlreadyExistsException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        saveEntity(collectionType, product, file);
    }

    private Database() {
    }
}
