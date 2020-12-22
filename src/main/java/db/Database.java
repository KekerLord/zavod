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

    private static <T extends Entity> void saveEntity(Type collectionType, T entity, File file)
            throws IOException, IdAlreadyExistsException {
        try (FileReader fr = new FileReader(file)) {
            List<T> entities = gson.fromJson(fr, collectionType);
            try (FileWriter fw = new FileWriter(file)) {
                for (T o : entities) {
                    if (o.getId().equals(entity.getId())) {
                        gson.toJson(entities, fw);
                        throw new IdAlreadyExistsException();
                    }
                }
                entities.add(entity);

                gson.toJson(entities, fw);
            }
        }
    }

    private static <T extends Entity> T findEntityById(Long id, File file, Type collectionType) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            List<T> products = gson.fromJson(fr, collectionType);
            for (var product : products) {
                if (product.getId().equals(id))
                    return product;
            }
        }
        return null;
    }

    private static <T extends Entity> void updateEntity(T entity, File file, Type collectionType) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            List<T> entities = gson.fromJson(fr, collectionType);
            T foundEntity = null;
            for (T e : entities) {
                if (e.getId().equals(entity.getId()))
                    foundEntity = e;
            }
            int index = entities.indexOf(foundEntity);
            entities.set(index, entity);

            try (FileWriter fw = new FileWriter(file)) {
                gson.toJson(entities, fw);
            }
        }
    }

    private static <T extends Entity> void deleteEntityById(Type collectionType, Long id, File file) throws IOException {
        try (FileReader fr = new FileReader(file)) {
            List<T> entities = gson.fromJson(fr, collectionType);
            entities.removeIf(entity -> entity.getId().equals(id));

            try (FileWriter fw = new FileWriter(file)) {
                gson.toJson(entities, fw);
            }
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

        try (FileReader fr = new FileReader(file)) {
            return gson.fromJson(fr, collectionType);
        }
    }

    public static Employee findEmployeeById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.EMPLOYEES);
        Type collectionType = new TypeToken<Collection<Employee>>() {
        }.getType();

        return findEntityById(id, file, collectionType);
    }

    // TODO update employee

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

        try (FileReader fr = new FileReader(file)) {
            return gson.fromJson(fr, collectionType);
        }
    }

    public static Part findPartById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PARTS);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();

        return findEntityById(id, file, collectionType);
    }

    // TODO update part

    public static void deletePartById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PARTS);
        Type collectionType = new TypeToken<Collection<Part>>() {
        }.getType();

        deleteEntityById(collectionType, id, file);
    }

    public static void saveProduct(Product product) throws IOException, IdAlreadyExistsException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        saveEntity(collectionType, product, file);
    }

    public static List<Product> getAllProducts() throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        try (FileReader fr = new FileReader(file)) {
            return gson.fromJson(fr, collectionType);
        }
    }

    public static Product findProductById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();
        return findEntityById(id, file, collectionType);
    }

    // TODO update product
    public static void updateProduct(Product product) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        updateEntity(product, file, collectionType);
    }

    public static void deleteProductById(Long id) throws IOException {
        File file = getDatabaseFile(DatabasePaths.PRODUCTS);
        Type collectionType = new TypeToken<Collection<Product>>() {
        }.getType();

        deleteEntityById(collectionType, id, file);
    }

    private Database() {
    }
}
