package bt2;

import java.util.*;

class Category {
    private int id;
    private String name;
    private boolean status;

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Book {
    private int id;
    private String name;
    private double price;
    private String author;
    private int categoryId;
    private boolean status;

    public Book(int id, String name, double price, String author, int categoryId, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.categoryId = categoryId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

public class BookManager {
    private static List<Category> categories = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageCategories();
                    break;
                case 2:
                    manageBooks();
                    break;
                case 0:
                    System.out.println("Đang thoát...");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("*************** MAIN MENU ***************");
        System.out.println("1. Quản lý thể loại");
        System.out.println("2. Quản lý sách");
        System.out.println("0. Thoát");
        System.out.println("******************************************");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void manageCategories() {
        while (true) {
            System.out.println("*************** CATEGORY MANAGEMENT ***************");
            System.out.println("1. Hiển thị tất cả thể loại");
            System.out.println("2. Thêm thể loại mới");
            System.out.println("3. Cập nhật thể loại");
            System.out.println("4. Thay đổi trạng thái thể loại");
            System.out.println("5. Trở lại menu chính");
            System.out.println("*****************************************************");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showAllCategories();
                    break;
                case 2:
                    addNewCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    changeCategoryStatus();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    private static void showAllCategories() {
        System.out.println("*************** ALL CATEGORIES ***************");
        for (Category category : categories) {
            System.out.println("ID: " + category.getId() + ", Name: " + category.getName() + ", Status: " + category.getStatus());
        }
        System.out.println("***********************************************");
    }

    private static void addNewCategory() {
        System.out.print("Nhập tên thể loại: ");
        String name = scanner.nextLine();
        System.out.print("Nhập trạng thái thể loại (true/false): ");
        boolean status = scanner.nextBoolean();
        int id = categories.size() + 1;
        Category newCategory = new Category(id, name, status);
        categories.add(newCategory);
        System.out.println("Đã thêm thể loại thành công!");
    }

    private static void updateCategory() {
        System.out.print("Nhập ID thể loại để cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Category category : categories) {
            if (category.getId() == id) {
                System.out.print("Nhập tên thể loại mới: ");
                String newName = scanner.nextLine();
                System.out.print("Nhập trạng thái thể loại mới (true/false): ");
                boolean newStatus = scanner.nextBoolean();
                category.setName(newName);
                category.setStatus(newStatus);
                System.out.println("Thể loại được cập nhật thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy ID thể loại!");
    }

    private static void changeCategoryStatus() {
        System.out.print("Nhập ID thể loại để thay đổi trạng thái: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Category category : categories) {
            if (category.getId() == id) {
                category.setStatus(!category.getStatus());
                System.out.println("Trạng thái thể loại đã thay đổi thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy ID thể loại!");
    }

    private static void manageBooks() {
        while (true) {
            System.out.println("*************** BOOK MANAGEMENT ***************");
            System.out.println("1. Hiển thị tất cả sách");
            System.out.println("2. Thêm sách mới");
            System.out.println("3. Cập nhật sách");
            System.out.println("4. Thay đổi trạng thái sách");
            System.out.println("5. Tìm kiếm sách theo tên thể loại");
            System.out.println("6. Sắp xếp sách theo giá (Giảm dần)");
            System.out.println("0. Trở lại menu chính");
            System.out.println("*************************************************");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showAllBooks();
                    break;
                case 2:
                    addNewBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    changeBookStatus();
                    break;
                    case 5:
                    searchBooksByCategory();
                    break;
                case 6:
                    sortBooksByPriceDescending();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    private static void showAllBooks() {
        System.out.println("*************** ALL BOOKS ***************");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Name: " + book.getName() + ", Price: " + book.getPrice() + ", Author: " + book.getAuthor() + ", CategoryID: " + book.getCategoryId() + ", Status: " + book.getStatus());
        }
        System.out.println("*****************************************");
    }

    private static void addNewBook() {
        System.out.print("Nhập tên sách: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá sách: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tác giả sách: ");
        String author = scanner.nextLine();
        System.out.print("Nhập ID thể loại sách: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập trạng thái sách (true/false): ");
        boolean status = scanner.nextBoolean();
        int id = books.size() + 1;
        Book newBook = new Book(id, name, price, author, categoryId, status);
        books.add(newBook);
        System.out.println("Đã thêm sách thành công!");
    }

    private static void updateBook() {
        System.out.print("Nhập ID sách để cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Book book : books) {
            if (book.getId() == id) {
                System.out.print("Nhập tên sách mới: ");
                String newName = scanner.nextLine();
                System.out.print("Nhập giá sách mới: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                System.out.print("Nhập tác giả sách mới: ");
                String newAuthor = scanner.nextLine();
                System.out.print("Nhập ID thể loại sách mới: ");
                int newCategoryId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Nhập trạng thái sách mới (true/false): ");
                boolean newStatus = scanner.nextBoolean();

                book.setName(newName);
                book.setPrice(newPrice);
                book.setAuthor(newAuthor);
                book.setCategoryId(newCategoryId);
                book.setStatus(newStatus);
                System.out.println("Sách được cập nhật thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy ID sách!");
    }

    private static void changeBookStatus() {
        System.out.print("Nhập ID sách để thay đổi trạng thái: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Book book : books) {
            if (book.getId() == id) {
                book.setStatus(!book.getStatus());
                System.out.println("Trạng thái sách đã được thay đổi thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy ID sách!");
    }

    private static void searchBooksByCategory() {
        System.out.print("Nhập tên thể loại để tìm kiếm: ");
        String categoryName = scanner.nextLine();
        boolean found = false;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                System.out.println("*************** BOOKS IN CATEGORY " + categoryName.toUpperCase() + " ***************");
                for (Book book : books) {
                    if (book.getCategoryId() == category.getId()) {
                        System.out.println("ID: " + book.getId() + ", Name: " + book.getName() + ", Price: " + book.getPrice() + ", Author: " + book.getAuthor() + ", Status: " + book.getStatus());
                    }
                }
                System.out.println("************************************************");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy thể loại!");
        }
    }

    private static void sortBooksByPriceDescending() {
        Collections.sort(books, Comparator.comparing(Book::getPrice).reversed());
        System.out.println("*************** BOOKS SORTED BY PRICE (DESCENDING) ***************");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Name: " + book.getName() + ", Price: " + book.getPrice() + ", Author: " + book.getAuthor() + ", Status: " + book.getStatus());
        }
        System.out.println("******************************************************************");
    }
}

