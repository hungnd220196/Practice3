package ra.imp;

import ra.entity.Product;

import java.util.Scanner;

public class ProductImp {
    static Product[] arrProduct = new Product[100];

    static {
        arrProduct[0] = new Product("P001", " Ao1", 200000F, 500000f, 300000f, 10, "Mô tả sản phẩm 1", true);
        arrProduct[1] = new Product("P002", " ao2", 110000f, 180000f, 70000f, 15, "Mô tả sản phẩm 2", true);
        arrProduct[2] = new Product("P003", " ao3", 150000f, 200000f, 50000f, 20, "Mô tả sản phẩm 3", false);
    }

    static int countProduct = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********************MENU**************************\n" +
                    "1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Tính lợi nhuận các sản phẩm\n" +
                    "4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần\n" +
                    "5. Thống kê các sản phẩm theo giá\n" +
                    "6. Tìm các sản phẩm theo tên sản phẩm\n" +
                    "7. Nhập sản phẩm\n" +
                    "8. Bán sản phẩm\n" +
                    "9. Cập nhật trạng thái sản phẩm\n" +
                    "10. Thoát\n");
            System.out.println("mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    calProfit();
                    break;
                case 4:
                    sortProfitdescending();
                    break;
                case 5:
                    exportPriceRange();
                    break;
                case 6:
                    searchProductByName();
                    break;
                case 7:
                    importProduct();
                    break;
                case 8:
                    sellProduct();
                    break;
                case 9:
                    updateStatusProduct();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("số không hợp lệ, mời nhập lại");
                    break;
            }
        } while (true);
    }

    private static void updateStatusProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời nhập vào mã id");
        String idStatusUpdate = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < countProduct; i++) {
            if (arrProduct[i].getProductId().equals(idStatusUpdate)) {
                check = true;
                arrProduct[i].setStatus(!arrProduct[i].getStatus());
                System.out.println("sản phẩm đã cập nhật trạng thái");
                break;
            }
        }
        if (!check){
            System.err.println("mã danh mục k tồn tại");
        }
    }

    private static void sellProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phầm :");
        String inputProductName = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < countProduct; i++) {
            if (arrProduct[i].getProductName().equals(inputProductName)) {
                check = true;
                if (arrProduct[i].getStatus()) {
                    System.out.println("Nhập số lượng sản phẩm cần bán:");
                    int soldQuantity = Integer.parseInt(scanner.nextLine());
                    if (soldQuantity <= arrProduct[i].getQuantity()) {
                        arrProduct[i].setQuantity(arrProduct[i].getQuantity() - soldQuantity);
                        System.out.println("Sản phẩm dã bán thành công");
                    } else {
                        System.out.println("Số lượng sp bán vuot qua số lượng sp hiện có");
                    }
                } else {
                    System.out.println("Sản phẩm không còn bán");
                }
            }
        }
        if (!check) {
            System.out.println("Không có tên sản phẩm ");
        }
    }

    private static void importProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã sản phầm :");
        String inputProductID = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < countProduct; i++) {
            if (arrProduct[i].getProductId().equals(inputProductID)) {
                check = true;
                System.out.println("Nhập số lượng cần them:");
                int addQuantity = Integer.parseInt(scanner.nextLine());
                arrProduct[i].setQuantity(arrProduct[i].getQuantity() + addQuantity);
                System.out.println("Số lượng sản phẩm đã được cập nhật thành công.");
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy sản phẩm với mã sản phẩm đã nhập.");
        }
    }

    private static void searchProductByName() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập tên sản phầm cần tìm:");
        String inputName = scanner.nextLine();
        boolean checkName = false;
        for (int i = 0; i < countProduct; i++) {
            if (arrProduct[i].getProductName().equals(inputName)) {
                arrProduct[i].displayData();
                checkName = true;
            }
        }
        if (!checkName) {
            System.out.println("Không co sản phẩm với tên cần tìm");
        }
    }

    private static void exportPriceRange() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập giá thấp nhất trong khoảng giá bán");
        float fromExportPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập giá cao nhất trong khoảng giá bán");
        float toExportPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Sản phẩm trong khoảng giá bán từ " + fromExportPrice + " đến " + toExportPrice + " là:");
        boolean checkExportPrice = false;
        for (int i = 0; i < countProduct; i++) {
            if (arrProduct[i].getExportPrice() >= fromExportPrice && arrProduct[i].getImportPrice() <= toExportPrice) {
                arrProduct[i].displayData();
                checkExportPrice = true;
            }
        }
        if (!checkExportPrice) {
            System.out.println("Không có sản phẩm trong khoảng giá bán từ " + fromExportPrice + " đến " + toExportPrice);
        }
    }

    private static void sortProfitdescending() {
        System.out.println("mảng trước sắp xếp");
        for (int i = 0; i < countProduct; i++) {
            arrProduct[i].displayData();
        }
        for (int i = 0; i < countProduct - 1; i++) {
            for (int j = i + 1; j < countProduct; j++) {
                if (arrProduct[i].getProfit() < arrProduct[j].getProfit()) {
                    Product temp = arrProduct[i];
                    arrProduct[i] = arrProduct[j];
                    arrProduct[j] = temp;
                }
            }
        }
        System.out.println("mảng sau sắp xếp");
        for (int i = 0; i < countProduct; i++) {
            arrProduct[i].displayData();
        }
    }

    private static void calProfit() {
        for (int i = 0; i < countProduct; i++) {

            arrProduct[i].calProfit();
            System.out.println("Sản phẩm " + arrProduct[i].getProductName() + " có lợi nhuận là " + arrProduct[i].getProfit());
        }

    }

    private static void displayProduct() {
        for (int i = 0; i < countProduct; i++) {
            arrProduct[i].displayData();
        }
    }

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("bạn muốn thêm mấy sản phẩm");
        int n = Integer.parseInt(new Scanner(System.in).nextLine());
        for (int i = 0; i < n; i++) {
            arrProduct[countProduct] = new Product();
            arrProduct[countProduct].inputData(scanner, arrProduct);
            countProduct++;
        }

    }
}
