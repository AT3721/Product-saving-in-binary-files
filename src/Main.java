package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String path = "products.txt";
        List<Product> products = readDataFromFile(path);

        while (true) {
            System.out.println("-----------------------");
            System.out.println("1-Add new product.");
            System.out.println("2-View product list.");
            System.out.println("3-Find product.");
            System.out.println("4-Remove product.");
            System.out.println("5-Exit.");
            System.out.print("Choose action: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter product brand: ");
                    String brand = scanner.nextLine();

                    System.out.print("Enter product description: ");
                    String description = scanner.nextLine();

                    Product p = new Product(id, name, price, brand, description);

                    products.add(p);

                    writeDataToFile(path, products);
                    System.out.println("Added successfully!");

                    break;
                case 2:
                    if (products.isEmpty()) {
                        System.out.println("No product available!");
                    } else {
                        for (Product product : products) {
                            System.out.println(product);
                        }
                    }
                    break;
                case 3:
                    if (products.isEmpty()) {
                        System.out.println("No product available!");
                    } else {

                        boolean isFound = false;

                        System.out.print("Enter product name: ");
                        String searchName = scanner.nextLine();

                        for (Product product : products) {
                            if (product.getName().toLowerCase().contains(searchName.toLowerCase())) {
                                System.out.println(product);
                                isFound = true;
                            }
                        }
                        if (!isFound) System.out.println("Not found!");
                    }
                    break;
                case 4:
                    boolean isDeleted = false;

                    System.out.print("Enter product ID to remove: ");
                    int removeID = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < products.size(); i++) {
                        if (removeID == products.get(i).getId()) {
                            products.remove(i);
                            isDeleted = true;
                            break;
                        }
                    }
                    if (isDeleted) {
                        writeDataToFile(path, products);
                        System.out.println("Deleted successfully!");
                    } else {
                        System.out.println("ID not found!");
                    }

                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Product> readDataFromFile(String path) {
        List<Product> products = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            products = (List<Product>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return products;
    }

    public static void writeDataToFile(String path, List<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(products);
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

