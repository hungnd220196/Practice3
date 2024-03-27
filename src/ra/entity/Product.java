package ra.entity;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private Float importPrice;
    private Float exportPrice;
    private Float profit;
    private int quantity;
    private String description;
    private Boolean status;

    public Product() {
    }

    public Product(String productId, String productName, Float importPrice, Float exportPrice, Float profit, int quantity, String description, Boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Float importPrice) {
        this.importPrice = importPrice;
    }

    public Float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(Float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct) {
        boolean checkID = false;
        do {
            System.out.println("Nhập mã sản phẩm (độ dài 4 ký tự, bắt đầu bằng 'P'): ");
            String inputProductId = scanner.nextLine();
            if (inputProductId.length() == 4 && inputProductId.charAt(0) == 'P') {
                checkID = true;
                productId = inputProductId;
            } else {
                System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
            }
        } while (!checkID);
        boolean check = false;
        do {
            System.out.println("mời bạn nhập tên sản phẩm");
            String inputProductId = scanner.nextLine();
            if (inputProductId.length() >= 6 && inputProductId.length() <= 50) {
                check = true;
                productName = inputProductId;
            } else {
                System.err.println("ten san pham phai tu 6 den 50 ki tu, vui long nhap lai");
            }


        } while (!check);
        System.out.println("Nhập giá nhập sản phẩm :");
        while (true) {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.err.println("số lượng phải lớn hơn 0, vui lòng nhập lại");
            }
        }
        System.out.println("Nhập giá xuất sản phẩm :");
        while (true) {
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if (this.exportPrice >= (this.importPrice * 120 / 100)) {
                break;
            } else {
                System.err.println("giá xuất phải lớn hơn ít nhất 20% gía nhập,vui lòng nhập lại");
            }
        }
        System.out.println("Nhâp số lượng sản phẩm :");
        while (true) {
            this.quantity = Integer.parseInt(scanner.nextLine());
            if (this.quantity > 0) {
                break;
            } else {
                System.err.println("số lượng phải lớn hơn 0, vui lòng nhập lại");
            }
        }
        System.out.println("Mô tả sản phẩm");
        this.description = scanner.nextLine();
        System.out.println("Trạng thái sản phẩm :");
        this.status = Boolean.parseBoolean(scanner.nextLine());


    }

    public void displayData() {
        System.out.println("Product{" +
                "productID='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", importPrice=" + importPrice +
                ", exportPrice=" + exportPrice +
                ", profit=" + getProfit() +
                ", quantity=" + quantity +
                ", descriptions='" + description + '\'' +
                ", status=" + (status ? "Đang bán" : "Không bán") +
                '}');
        System.out.println("=========================================");
    }

    public void calProfit() {
        profit = exportPrice - importPrice;
    }
}
