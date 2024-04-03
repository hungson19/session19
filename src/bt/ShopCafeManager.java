package bt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Categories {
    private static int nextId = 1;

    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories(String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = nextId++;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void displayData() {
        System.out.println("Catalog ID: " + catalogId);
        System.out.println("Catalog Name: " + catalogName);
        System.out.println("Descriptions: " + descriptions);
        System.out.println("Catalog Status: " + (catalogStatus ? "Active" : "Inactive"));
    }
}

class Product {
    private static int nextId = 1;

    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product(String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = generateProductId(catalogId);
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    private String generateProductId(int catalogId) {
        char prefix;
        if (catalogId == 1) {
            prefix = 'C'; // Cafe
        } else if (catalogId == 2) {
            prefix = 'S'; // Sinh tố
        } else {
            prefix = 'A'; // Đồ ăn nhanh
        }
        return String.format("%c%03d", prefix, nextId++);
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void displayData() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Price: " + price);
        System.out.println("Description: " + description);
        System.out.println("Created Date: " + created);
        System.out.println("Catalog ID: " + catalogId);
        System.out.println("Product Status: " + (productStatus == 0 ? "Available" : productStatus == 1 ? "Out of Stock" : "Not for Sale"));
    }
}

public class ShopCafeManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Categories> categoriesList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        int choice;
        do {
            System.out.println("\n***************SHOP MENU****************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    categoriesMenu(categoriesList);
                    break;
                case 2:
                    productMenu(productList, categoriesList);
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 3);
    }

    private static void categoriesMenu(List<Categories> categoriesList) {
        int choice;
        do {
            System.out.println("\n************CATEGORIES MENU************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    inputCategories(categoriesList);
                    break;
                case 2:
                    displayCategories(categoriesList);
                    break;
                case 3:
                    updateCategory(categoriesList);
                    break;
                case 4:
                    deleteCategory(categoriesList);
                    break;
                case 5:
                    updateCategoryStatus(categoriesList);
                    break;
                case 6:
                    System.out.println("Quay lại menu SHOP MANAGEMENT");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 6);
    }

    private static void inputCategories(List<Categories> categoriesList) {
        System.out.print("Nhập số danh mục cần thêm: ");
        int numCategories = scanner.nextInt();
        scanner.nextLine(); // Clear newline
        for (int i = 0; i < numCategories; i++) {
            System.out.println("\nNhập thông tin danh mục thứ " + (i + 1) + ":");
            System.out.print("Tên danh mục: ");
            String name = scanner.nextLine();
            System.out.print("Mô tả: ");
            String description = scanner.nextLine();
            System.out.print("Trạng thái (true/false): ");
            boolean status = scanner.nextBoolean();
            categoriesList.add(new Categories(name, description, status));
        }
    }

    private static void displayCategories(List<Categories> categoriesList) {
        System.out.println("\n************DANH SÁCH DANH MỤC************");
        for (Categories category : categoriesList) {
            category.displayData();
            System.out.println();
        }
    }

    private static void updateCategory(List<Categories> categoriesList) {
        System.out.print("Nhập mã danh mục cần cập nhật: ");
        int catalogId = scanner.nextInt();
        boolean found = false;
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == catalogId) {
                found = true;
                scanner.nextLine(); // Clear newline
                System.out.println("\nNhập thông tin mới cho danh mục:");
                System.out.print("Tên danh mục: ");
                category.setCatalogName(scanner.nextLine());
                System.out.print("Mô tả: ");
                category.setDescriptions(scanner.nextLine());
                System.out.print("Trạng thái (true/false): ");
                category.setCatalogStatus(scanner.nextBoolean());
                System.out.println("Danh mục đã được cập nhật thành công.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy mã danh mục này.");
        }
    }

    private static void deleteCategory(List<Categories> categoriesList) {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int catalogId = scanner.nextInt();
        boolean found = false;
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == catalogId) {
                found = true;
                categoriesList.remove(category);
                System.out.println("Danh mục đã được xóa thành công.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy mã danh mục này.");
        }
    }

    private static void updateCategoryStatus(List<Categories> categoriesList) {
        System.out.print("Nhập mã danh mục cần cập nhật trạng thái: ");
        int catalogId = scanner.nextInt();
        boolean found = false;
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == catalogId) {
                found = true;
                category.setCatalogStatus(!category.isCatalogStatus());
                System.out.println("Trạng thái của danh mục đã được cập nhật thành công.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy mã danh mục này.");
        }
    }

    private static void productMenu(List<Product> productList, List<Categories> categoriesList) {
        int choice;
        do {
            System.out.println("\n************PRODUCT MANAGEMENT************");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá");
            System.out.println("8. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    inputProducts(productList, categoriesList);
                    break;
                case 2:
                    displayProducts(productList);
                    break;
                case 3:
                    sortProductsByPrice(productList);
                    break;
                case 4:
                    updateProduct(productList);
                    break;
                case 5:
                    deleteProduct(productList);
                    break;
                case 6:
                    searchProductsByName(productList);
                    break;
                case 7:
                    searchProductsByPriceRange(productList);
                    break;
                case 8:
                    System.out.println("Quay lại menu SHOP MANAGEMENT");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 8);
    }

    private static void inputProducts(List<Product> productList, List<Categories> categoriesList) {
        if (categoriesList.isEmpty()) {
            System.out.println("Vui lòng thêm ít nhất một danh mục trước khi thêm sản phẩm.");
            return;
        }
        System.out.print("Nhập số lượng sản phẩm cần thêm: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); // Clear newline
        for (int i = 0; i < numProducts; i++) {
            System.out.println("\nNhập thông tin sản phẩm thứ " + (i + 1) + ":");
            System.out.print("Tên sản phẩm: ");
            String name = scanner.nextLine();
            System.out.print("Giá: ");
            float price = scanner.nextFloat();
            scanner.nextLine(); // Clear newline
            System.out.print("Mô tả: ");
            String description = scanner.nextLine();
            System.out.print("Ngày nhập (dd/mm/yyyy): ");
            String dateString = scanner.nextLine();
            Date created = parseDate(dateString);
            System.out.println("Chọn danh mục cho sản phẩm:");
            displayCategories(categoriesList);
            System.out.print("Nhập mã danh mục: ");
            int categoryId = scanner.nextInt();
            if (!isValidCategoryId(categoriesList, categoryId)) {
                System.out.println("Mã danh mục không hợp lệ.");
                continue;
            }
            System.out.println("Chọn trạng thái cho sản phẩm:");
            System.out.println("0: Đang bán");
            System.out.println("1: Hết hàng");
            System.out.println("2: Không bán");
            System.out.print("Nhập trạng thái: ");
            int status = scanner.nextInt();
            productList.add(new Product(name, price, description, created, categoryId, status));
        }
    }

    private static boolean isValidCategoryId(List<Categories> categoriesList, int categoryId) {
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == categoryId) {
                return true;
            }
        }
        return false;
    }

    private static Date parseDate(String dateString) {
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            date = new Date();
            System.out.println("Đã xảy ra lỗi khi chuyển đổi ngày, sử dụng ngày hiện tại thay thế.");
        }
        return date;
    }

    private static void displayProducts(List<Product> productList) {
        System.out.println("\n************DANH SÁCH SẢN PHẨM************");
        for (Product product : productList) {
            product.displayData();
            System.out.println();
        }
    }

    private static void sortProductsByPrice(List<Product> productList) {
        productList.sort(Comparator.comparing(Product::getPrice));
        displayProducts(productList);
    }

    private static void updateProduct(List<Product> productList) {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String productId = scanner.next();
        boolean found = false;
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                found = true;
                scanner.nextLine(); // Clear newline
                System.out.println("\nNhập thông tin mới cho sản phẩm:");
                System.out.print("Tên sản phẩm: ");
                product.setProductName(scanner.nextLine());
                System.out.print("Giá: ");
                product.setPrice(scanner.nextFloat());
                scanner.nextLine(); // Clear newline
                System.out.print("Mô tả: ");
                product.setDescription(scanner.nextLine());
                System.out.print("Ngày nhập (dd/mm/yyyy): ");
                String dateString = scanner.nextLine();
                product.setCreated(parseDate(dateString));
                System.out.println("Sản phẩm đã được cập nhật thành công.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy mã sản phẩm này.");
        }
    }

    private static void deleteProduct(List<Product> productList) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.next();
        boolean found = false;
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                found = true;
                productList.remove(product);
                System.out.println("Sản phẩm đã được xóa thành công.");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy mã sản phẩm này.");
        }
    }

    private static void searchProductsByName(List<Product> productList) {
        scanner.nextLine(); // Clear newline
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String searchName = scanner.nextLine().toLowerCase();
        boolean found = false;
        System.out.println("\n************KẾT QUẢ TÌM KIẾM************");
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchName)) {
                product.displayData();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm nào phù hợp.");
        }
    }

    private static void searchProductsByPriceRange(List<Product> productList) {
        System.out.print("Nhập khoảng giá (a - b): ");
        float a = scanner.nextFloat();
        float b = scanner.nextFloat();
        boolean found = false;
        System.out.println("\n************KẾT QUẢ TÌM KIẾM************");
        for (Product product : productList) {
            if (product.getPrice() >= a && product.getPrice() <= b) {
                product.displayData();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá này.");
        }
    }
}

