package run;

import implement.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BreadManager {
    private static User userImp = new User();
    public static List<User> listUser = userImp.readFromFile();
    private static Bread breadImp = new Bread();
    public static List<Bread> listBread = breadImp.readFormFileBread();
    private static Topping toppingImp = new Topping();
    public static List<Topping> listTopping = toppingImp.readFromFileTopping();


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("****************CUA HANG BANH MI***************");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("3. Thoat");
            System.out.println("****************CUA HANG BANH MI***************");
            System.out.print("Su lua chon cua ban: ");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    login(input);
                    break;
                case 2:
                    register(input);
                    break;
                case 3:
                    input.close();
                    System.exit(0);
                default:
                    System.err.println("Không có lựa chọn này");
            }
        } while (true);
    }

    public static void login(Scanner sc) {
        do {
            System.out.println("-----------Đăng Nhập-------------");
            System.out.print("Ten dang nhap: ");
            String userName = sc.nextLine();
            System.out.print("Mat khau: ");
            String password = sc.nextLine();
            User user = userImp.checkLogin(userName, password);
            if (user != null) {
                //Dang nhap thanh cong
                if (user.isPermission()) {
                    //Tai khoan admin
                    displayMenuShopManagement(sc);
                } else {
                    //Tai khoan user
                    displayMenuUser(sc);
                }
                break;
            } else {
                //Dang nhap that bai
                System.err.println("Đăng nhập thất bại");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("1. Dang nhap lai");
                System.out.println("2. Dang ky tai khoan");
                System.out.println("3. Thoat");
                System.out.print("Su lua chon: ");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 2) {
                    register(sc);
                    //Chuyen den menu dang ky
                } else if (choice == 3) {
                    break;
                }
            }
        } while (true);
    }

    public static void register(Scanner sc) {
        System.out.println("---------Đăng Kí----------------");
        User newUser = userImp.inputData(sc);
        boolean check = userImp.create(newUser);
        if (check) {
            System.out.println("Đăng kí thành công");
        } else {
            System.err.println("Đăng kí thất bại");
        }
    }

    public static void displayMenuUser(Scanner sc) {
        System.out.println("Chào mừng bạn đến với cửa hàng của chúng tôi");
        sc.nextLine();
        boolean exit = true;
        do {
            System.out.println("**********************CỬA HÀNG BÁNH MÌ HÂN HẠNH ĐƯỢC PHỤC VỤ************************");
            System.out.println("1. Thêm sản phẩm vào giỏ hàng ");
            System.out.println("2. Tìm kiếm sản phẩm");
            System.out.println("3. Chi tiết Giỏ hàng");
            System.out.println("4. Đổi mật khẩu");
            System.out.println("5. Phản hồi");
            System.out.println("6. Đăng xuất");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    showProductList(listBread);
                    System.out.print("Nhập vào Id Bánh mì bạn muốn mua: ");
                    int breadId = Integer.parseInt(sc.nextLine());
                    listUser = userImp.readFromFile();
                    for (Bread bread : listBread) {
                        if (bread.getBreadId() == breadId) {
                            System.out.print("Nhập vào số lượng: ");
                            int quantity = Integer.parseInt(sc.nextLine());
                            CartItem newCartItem = new CartItem(bread, quantity);

                            // Thêm đối tượng newCartItem vào giỏ hàng của người dùng hiện tại
                            userImp.getCart().add(newCartItem);
                            boolean check = userImp.writeToFile(listUser);
                            if (check) {
                                System.out.println("Thêm bánh mì thành công!");
                            } else {
                                System.err.println("Thêm thất bại");
                            }

                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhập tên sản phẩm: ");
                    String searchName = sc.nextLine();
                    List<Bread> listSearch = new ArrayList<>();
                    if (listSearch.size() == 0) {
                        System.err.println("Không tìm thấy sản phẩm");
                    } else {
                        for (Bread bread : listSearch) {
                            if (bread.getBreadName().contains(searchName)) {
                                listSearch.add(bread);
                            }
                            System.out.println("danh sách tìm kiếm là: ");
                            showProductList(listSearch);

                        }
                    }
                    break;
                case 3:
                    showCart(sc);
                    break;
                case 4:
                    System.out.println("Nhập vào mật khẩu mới: ");
                    String newPassword = sc.nextLine();
                    for (User user : listUser) {
                            if (user.getPassword().equals(newPassword)) {
                                System.err.println("Mật khẩu mới giống với mật khẩu cũ! Vui lòng nhập lại.");
                                break;
                            }
                            user.setPassword(newPassword); // đổi mật khẩu
                            boolean check = userImp.writeToFile(listUser);
                            if (check) {
                                System.out.println("Đổi mật khẩu thành công.");
                            } else {
                                System.err.println("Đổi mật khẩu không thành công.");
                            }
                            return;
                    }

                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!\n");
                    exit = false;
                    break;
                default:
                    System.err.println("Không có lựa chọn này");
            }
        } while (exit);
    }

    public static void showCart(Scanner sc) {
        boolean exit = true;
        do {
            System.out.println("**********************GIỎ HÀNG CỦA BẠN************************");
            System.out.println("1. Mua thêm sản phẩm ");
            System.out.println("2. Xem Giỏ hàng");
            System.out.println("3. Xóa sản phẩm trong giỏ ");
            System.out.println("4. Hóa đơn");
            System.out.println("5. Quay lại");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("Giỏ hàng của bạn: ");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("|  Bread ID  |  Bread Name  |  Quantity  |  Price  |");
                    System.out.println("-----------------------------------------------------");
                    float total = 0;
                    for (CartItem cartItem : userImp.getCart()) {
                        Bread bread = cartItem.getBread();
                        int quantity = cartItem.getQuantity();
                        float price = bread.getExportPrice();
                        total += price * quantity;
                        System.out.printf("|  %d  |  %s  |  %d  |  %.1f  |\n",
                                bread.getBreadId(), bread.getBreadName(), quantity, price);
                    }
                    System.out.println("-----------------------------------------------------");
                    System.out.printf("Total: %.1f\n", total);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    break;
            }
        } while (exit);
    }

    public static void displayMenuShopManagement(Scanner sc) {
        boolean exit = true;
        do {
            System.out.println("**********************QUAN LY CUA HANG************************");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý Topping");
            System.out.println("3. Quản lý người dùng");
            // hoá đơn , phẩn hồi
            System.out.println("4. Đăng xuất");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    displayMenuProductManager(sc);
                    break;
                case 2:
                    displayMenuTopping(sc);
                    break;
                case 3:
                    displayMenuManagerUser(sc);
                    break;
                case 4:
                    exit = false;
                    break;
                default:
                    System.err.println("Không có lựa chọn này");
            }
        } while (exit);

    }

    public static void displayMenuProductManager(Scanner sc) {
        boolean exit = true;
        do {
            System.out.println("****************ADMIN QUẢN LÝ***************\n" +
                    "1. Nhập số sản phẩm và nhập thông tin các sản phẩm \n" +
                    "2. Xóa sản phẩm\n" +
                    "3. Xem danh sách sản phẩm \n" +
                    "4. Sửa sản phẩm \n" +
                    "5. Quay lại ");
            System.out.println("Mời bạn chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập số sản phẩm: ");
                    int countOfBread = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < countOfBread; i++) {
                        System.out.println("Nhập sản phẩm thứ " + (i + 1));
                        System.out.println("--------------------------");
                        Bread newBread = new Bread();
                        newBread.inputData1(sc, listTopping);
                        listBread = breadImp.readFormFileBread();
                        if (listBread.size() == 0) {
                            newBread.setBreadId(1);
                        } else {
                            int newBreadId = listBread.get(listBread.size() - 1).getBreadId() + 1;
                            newBread.setBreadId(newBreadId);
                        }
                        listBread.add(newBread);
                        boolean check = breadImp.writeFormFileBread(listBread);
                        if (check) {
                            System.out.println("Thêm mới thành công");
                        } else {
                            System.err.println("Thêm mới thất bại");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhập vào  Id sản phẩm muốn xóa: ");
                    int idBread = Integer.parseInt(sc.nextLine());
                    for (Bread bread : listBread) {
                        if (bread.getBreadId() == idBread) {
                            listBread.remove(bread);
                            breadImp.writeFormFileBread(listBread);
                            System.out.println("Xóa thành công!");
                            break;
                        } else {
                            System.out.println("Không có Id !");
                        }
                    }
                    break;
                case 3:
                    listBread = breadImp.readFormFileBread();
                    if (listBread.size() == 0) {
                        System.err.println("Không có sản phẩm nào");
                    } else {
                        for (Bread bread : listBread) {
                            System.out.println("-------------------------");
                            bread.displayData();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Nhập vào id sản phẩm: ");
                    int idEditBread = Integer.parseInt(sc.nextLine());
                    Bread breadToEdit = null;
                    for (Bread bread : listBread) {
                        if (bread.getBreadId() == idEditBread) {
                            breadToEdit = bread;
                            break;
                        }
                    }
                    if (breadToEdit != null) {
                        System.out.println("Nhập tên mới cho sản phẩm: ");
                        String newName = sc.nextLine();
                        System.out.println("Nhập giá mới cho sản phẩm: ");
                        int newPrice = Integer.parseInt(sc.nextLine());
                        System.out.println("Nhập trạng thái mới cho sản phẩm: ");
                        boolean newStatus = Boolean.parseBoolean(sc.nextLine());
                        for (Topping topping : toppingImp.readFromFileTopping()) {
                            System.out.println(topping);
                        }
                        System.out.println("Chọn Topping mới cho sản phẩm: ");
                        int newIdTopping = Integer.parseInt(sc.nextLine());
                        for (Topping topping : listTopping) {
                            if (topping.getToppingId() == newIdTopping) {
                                breadToEdit.setTopping(topping);
                                break;
                            }
                        }

                        breadToEdit.setBreadName(newName);
                        breadToEdit.setExportPrice(newPrice);
                        breadToEdit.setBreadStatus(newStatus);

                        boolean check = breadImp.writeFormFileBread(listBread);
                        if (check) {
                            System.out.println("Sửa thành công sản phẩm ");
                        } else {
                            System.err.println("Sửa không thành công");
                        }
                    } else {
                        System.err.println("Không tìm thấy Topping có id " + idEditBread);
                    }
                    break;
                case 5:
                    System.out.println("Thoát thành công!");
                    exit = false;
                    break;
                default:
                    System.out.println("Sai lựa chọn");
                    break;
            }
        } while (exit);
    }

    public static void displayMenuTopping(Scanner sc) {
        boolean exit = true;
        do {
            System.out.println("****************ADMIN QUẢN LÝ***************\n" +
                    "1. Nhập số Topping và thông tin Topping\n" +
                    "2. Xem danh sách Topping\n" +
                    "3. Sửa Topping \n" +
                    "4. Xóa Topping \n" +
                    "5. Quay lại ");
            System.out.println("Mời bạn chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập số topping: ");
                    int countOfTopping = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < countOfTopping; i++) {
                        System.out.println("Nhập danh mục thứ " + (i + 1));
                        System.out.println("--------------------------");
                        Topping newTopping = new Topping();
                        newTopping.inputData1(sc, listTopping);
                        listTopping = toppingImp.readFromFileTopping();
                        if (listTopping.size() == 0) {
                            newTopping.setToppingId(1);
                        } else {
                            int newToppingId = listTopping.get(listTopping.size() - 1).getToppingId() + 1;
                            newTopping.setToppingId(newToppingId);
                        }
                        listTopping.add(newTopping);
                        boolean check = toppingImp.writeToFileTopping(listTopping);
                        if (check) {
                            System.out.println("Thêm mới thành công");
                        } else {
                            System.err.println("Thêm mới thất bại");
                        }
                    }

                    break;
                case 2:
                    listTopping = toppingImp.readFromFileTopping();
                    if (listTopping.size() == 0) {
                        System.err.println("Không có Topping nào");
                    } else {
                        for (Topping topping : listTopping) {
                            System.out.println("-------------------------");
                            topping.displayData();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Nhập vào id Topping: ");
                    int idEditTopping = Integer.parseInt(sc.nextLine());
                    Topping toppingToEdit = null;
                    for (Topping topping : listTopping) {
                        if (topping.getToppingId() == idEditTopping) {
                            toppingToEdit = topping;
                            break;
                        }
                    }
                    if (toppingToEdit != null) {
                        System.out.println("Nhập tên mới cho Topping: ");
                        String newName = sc.nextLine();
                        System.out.println("Nhập giá mới cho Topping: ");
                        int newPrice = Integer.parseInt(sc.nextLine());
                        System.out.println("Nhập trạng thái mới cho Topping: ");
                        boolean newStatus = Boolean.parseBoolean(sc.nextLine());

                        toppingToEdit.setToppingName(newName);
                        toppingToEdit.setExportPrice(newPrice);
                        toppingToEdit.setToppingStatus(newStatus);

                        boolean check = toppingImp.writeToFileTopping(listTopping);
                        if (check) {
                            System.out.println("Sửa thành công Topping có id " + idEditTopping);
                        } else {
                            System.err.println("Sửa không thành công");
                        }
                    } else {
                        System.err.println("Không tìm thấy Topping có id " + idEditTopping);
                    }
                    break;
                case 4:
                    System.out.println("Nhập vào  Id Topping muốn xóa: ");
                    int idDeleteTopping = Integer.parseInt(sc.nextLine());
                    for (Topping topping : listTopping) {
                        if (topping.getToppingId() == idDeleteTopping) {
                            listTopping.remove(topping);
                            toppingImp.writeToFileTopping(listTopping);
                            System.out.println("Xóa thành công!");
                            break;
                        } else {
                            System.err.println("Không có Id !");
                        }
                    }
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.out.println("Không có lựa chọn này");
                    break;
            }
        } while (exit);
    }

    public static void displayMenuManagerUser(Scanner sc) {
        boolean exit = true;
        do {
            System.out.println("****************ADMIN QUẢN LÝ***************\n" +
                    "1. Danh sách user\n" +
                    "2. Xóa User \n" +
                    "3. Thoát ");
            System.out.println("Mời bạn chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    listUser = userImp.readFromFile();
                    if (listUser.size() == 0) {
                        System.err.println("Không có tài khoản nào");
                    } else {
                        for (User user : listUser) {
                            System.out.println("-------------------------");
                            user.displayData();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhập vào Id tài khoản muốn xóa: ");
                    int idUser = Integer.parseInt(sc.nextLine());
                    for (User user : listUser) {
                        if (user.getUserId() == idUser) {
                            listUser.remove(user);
                            userImp.writeToFile(listUser);
                            System.out.println("Xóa thành công!");
                            break;
                        } else {
                            System.out.println("Không có Id !");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Đã quay lại");
                    exit = false;
                    break;
                default:
                    System.out.println("Không có lựa chọn này");
                    break;
            }
        } while (exit);
    }

    public static void showProductList(List<Bread> list) {
        for (Bread bread : list) {
            bread.displayData();
        }
    }
}
