package implement;

import data.DataURL;
import interface1.IBread;
import interface1.ICartService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart implements ICartService,Serializable {

    private static User userImp = new User();

    private User user;
    private List<CartItem> listCartItems;

    public Cart() {
    }

    public Cart(User user, List<CartItem> listCartItems) {
        this.user = user;
        this.listCartItems = listCartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getListCartItems() {
        return listCartItems;
    }

    public void setListCartItems(List<CartItem> listCartItems) {
        this.listCartItems = listCartItems;
    }


    @Override
    public boolean addToCart() {
        return false;
    }

    @Override
    public User getUserLogin() {
        return null;
    }
    @Override
    public List<Cart> readFormFileCart() {
        List<Cart> listCart = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_CART_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listCart = (List<Cart>) ois.readObject();
            } else {
                return listCart;
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
        return listCart;
    }

    @Override
    public boolean writeFormFileCart(List<Cart> list) {
        return false;
    }
    @Override
    public void displayData() {

    }

    @Override
    public void inputData1(Scanner sc, List<Topping> list) {

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

}
