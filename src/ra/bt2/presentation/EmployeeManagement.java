package ra.bt2.presentation;
import ra.bt2.business.IEmployee;
import ra.bt2.businessImp.Employee;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeManagement {
    public Employee[] employees = new Employee[100];
    private int countEmployee = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManagement employeeManagement = new EmployeeManagement();

        int choice;
        do {
            System.out.println("********************MENU********************");
            System.out.println("1. Nhập thông tin cho n nhân viên");
            System.out.println("2. Hiển thị thông tin nhân viên");
            System.out.println("3. Tính lương cho các nhân viên");
            System.out.println("4. Tìm kiếm nhân viên theo tên nhân viên");
            System.out.println("5. Cập nhật thông tin nhân viên");
            System.out.println("6. Xóa nhân viên theo mã nhân viên");
            System.out.println("7. Sắp xếp nhân viên theo lương tăng dần (Comparable)");
            System.out.println("8. Sắp xếp nhân viên theo tên nhân viên giảm dần (Comparator)");
            System.out.println("9. Sắp xếp nhân vên theo năm sinh tăng dần (Comparator)");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    employeeManagement.inputEmployeeData(scanner);
                    break;
                case 2:
                    employeeManagement.displayEmployeeData();
                    break;
                case 3:
                    employeeManagement.calculateSalary();
                    break;
                case 4:
                    employeeManagement.searchEmployeeByName(scanner);
                    break;
                case 5:
                    employeeManagement.updateEmployee(scanner);
                    break;
                case 6:
                    employeeManagement.deleteEmployee(scanner);
                    break;
                case 7:
                    employeeManagement.sortEmployeesBySalary();
                    break;
                case 8:
                    employeeManagement.sortEmployeesByNameDesc();
                    break;
                case 9:
                    employeeManagement.sortEmployeesByYearOfBirthAsc();
                    break;
                case 10:
                    System.out.println("Đã chọn thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 10);
    }

    public void inputEmployeeData(Scanner scanner) {
        System.out.print("Nhập số nhân viên cần nhập thông tin: ");
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Nhập thông tin cho nhân viên " + (i + 1) + ":");
            employees[countEmployee] = new Employee();
            employees[countEmployee].inputData(scanner);
            countEmployee++;
        }
    }
    public void displayEmployeeData() {
        if (countEmployee == 0) {
            System.out.println("Không có nhân viên nào.");
            return;
        }
        System.out.println("Thông tin nhân viên:");
        for (int i = 0; i < countEmployee; i++) {
            System.out.println("Nhân viên " + (i + 1) + ":");
            employees[i].displayData();
        }
    }
    public void calculateSalary() {
        for (int i = 0; i < countEmployee; i++) {
            float salary = employees[i].calSalary();
            System.out.println("Lương của nhân viên " + employees[i].getName() + " là: " + salary);
        }
    }

    public void searchEmployeeByName(Scanner scanner) {
        System.out.print("Nhập tên nhân viên cần tìm: ");
        String nameInput = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < countEmployee; i++) {
            if (employees[i].getName().equals(nameInput)) {
                System.out.println("Thông tin của nhân viên:");
                employees[i].displayData();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên có tên " + nameInput);
        }
    }

    public void updateEmployee(Scanner scanner) {
        System.out.print("Nhập mã nhân viên cần cập nhật: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < countEmployee; i++) {
            if (employees[i].getId().equalsIgnoreCase(id)) {
                employees[i].inputData(scanner);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên có mã " + id);
        }
    }

    public void deleteEmployee(Scanner scanner) {
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < countEmployee; i++) {
            if (employees[i].getId().equalsIgnoreCase(id)) {
                // Xóa nhân viên bằng cách dịch chuyển các phần tử phía sau lên trước
                for (int j = i; j < countEmployee - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                countEmployee--;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên có mã " + id);
        } else {
            System.out.println("Đã xóa nhân viên có mã " + id);
        }
    }

    public void sortEmployeesBySalary() {
        Arrays.sort(employees, 0, countEmployee, new Comparator<IEmployee>() {
            @Override
            public int compare(IEmployee o1, IEmployee o2) {
                return Float.compare(((Employee) o1).getSalary(), ((Employee) o2).getSalary());
            }
        });
        System.out.println("Đã sắp xếp nhân viên theo lương tăng dần.");
        displayEmployeeData();
    }

    public void sortEmployeesByNameDesc() {
        Arrays.sort(employees, 0, countEmployee, new Comparator<IEmployee>() {
            @Override
            public int compare(IEmployee o1, IEmployee o2) {
                return ((Employee) o2).getName().compareToIgnoreCase(((Employee) o1).getName());
            }
        });
        System.out.println("Đã sắp xếp nhân viên theo tên giảm dần.");
        displayEmployeeData();
    }

    public void sortEmployeesByYearOfBirthAsc() {
        Arrays.sort(employees, 0, countEmployee, new Comparator<IEmployee>() {
            @Override
            public int compare(IEmployee o1, IEmployee o2) {
                return Integer.compare(((Employee) o1).getYear(), ((Employee) o2).getYear());
            }
        });
        System.out.println("Đã sắp xếp nhân viên theo năm sinh tăng dần.");
        displayEmployeeData();
    }

}