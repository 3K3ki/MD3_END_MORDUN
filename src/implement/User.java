package implement;

import data.DataURL;
import interface1.IUser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable, IUser {
    private static final long serialVersionUID = -6719393978838908084L;

    private int userId;
    private String email;

    private String userName;
    private String password;
    private String fullName;
    private boolean permission;
    private boolean userStatus;
    private List<CartItem> cart = new ArrayList<>();


    public User() {
    }

    public User(int userId, String email, String userName, String password, String fullName, boolean permission, boolean userStatus) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.permission = permission;
        this.userStatus = userStatus;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }


    @Override
    public boolean create(User user) {
        List<User> listUser = readFromFile();
        if (listUser==null){
            listUser= new ArrayList<>();
        }
        listUser.add(user);
        boolean check = writeToFile(listUser);
        return check;
    }
//    public void addToCart(Bread bread) {
//        cart.add(bread);
//    }
    @Override
    public User inputData(Scanner sc) {
        User newUser = new User();
        List<User> listUser = readFromFile();
        if (listUser==null) {
            newUser.setUserId(1);
        }else {
            newUser.setUserId(listUser.get(listUser.size()-1).getUserId()+1);
        }
        System.out.println("Nhập email: ");
        newUser.setEmail(sc.nextLine());
        System.out.println("Nhập username :");
        newUser.setUserName(sc.nextLine());
        System.out.println("nhập password :");
        newUser.setPassword(sc.nextLine());
        System.out.println("Nhập tên đầy đủ");
        newUser.setFullName(sc.nextLine());
        newUser.setPermission(false);
        newUser.setUserStatus(true);
        return newUser;
    }

    @Override
    public User getCurrentLogin() {
        List<User> listUser = readFromFile();
        if (listUser.size()==0){
            return null;
        }else {
            User  currentUser = listUser.get(0);
            return currentUser;
        }
    }

    @Override
    public void inputData1(Scanner sc, List<Topping> list) {

    }

    @Override
    public void displayData() {
        System.out.println("--------------------------------");
        System.out.printf("Email: %s\n", email);
        System.out.printf("Tài khoản: %s\n", userName);
        System.out.printf("Mật khẩu: %s\n", password);
        System.out.printf("Tên người dùng: %s\n", fullName);
        System.out.printf("Permission: " + ((permission) ? "Admin"+ "\n" : "User" + "\n"));
        System.out.println(cart);
    }



    @Override
    public List<User> readFromFile() {
        List<User> listUser = null;
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_USER_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listUser = (List<User>) ois.readObject();
            }else {
                return listUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return listUser;
    }

    @Override
    public boolean writeToFile(List<User> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_USER_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception ex) {
            returnData = false;
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return returnData;
    }

    @Override
    public boolean create1(Topping topping) {
        return false;
    }

    @Override
    public List<Topping> readFromFileTopping() {
        return null;
    }

    @Override
    public boolean writeToFileTopping(List<Topping> list) {
        return false;
    }

    @Override
    public List<Bread> readFormFileBread() {
        return null;
    }

    @Override
    public boolean writeFormFileBread(List<Bread> list) {
        return false;
    }

    @Override
    public List<Cart> readFormFileCart() {
        return null;
    }

    @Override
    public boolean writeFormFileCart(List<Cart> list) {
        return false;
    }

    public User checkLogin(String userName, String password) {
        List<User> listUser = readFromFile();
        if (listUser == null) {
            listUser = new ArrayList<User>();
        }
        for (User user : listUser) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
