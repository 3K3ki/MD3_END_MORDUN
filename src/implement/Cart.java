//package implement;
//
//import data.DataURL;
//import interface1.ICartService;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Cart implements ICartService,Serializable {
//    private static Cart cartImp = new Cart();
//
//    private static User userImp = new User();
//
//    private User user = new User().getCurrentLogin();
//    private List<Cart> listCart = cartImp.readFormFileCart();
//    private List<CartItem> listCartItem;
//
//
//    private List<Bread> listBread;
//
//    public Cart() {
//    }
//
//    public Cart(User user, List<Bread> listBread, List<CartItem> listCartItem) {
//        this.user = user;
//        this.listBread = listBread;
//        this.listCartItem = listCartItem;
//    }
//
//    public static User getUserImp() {
//        return userImp;
//    }
//
//    public static void setUserImp(User userImp) {
//        Cart.userImp = userImp;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//    public List<CartItem> getListCartItem() {
//        return listCartItem;
//    }
//
//    public void setListCartItem(List<CartItem> listCartItem) {
//        this.listCartItem = listCartItem;
//    }
//    public List<Bread> getListBread() {
//        return listBread;
//    }
//
//    public void setListBread(List<Bread> listBread) {
//        this.listBread = listBread;
//    }
//    public void save(Cart cart) {
//        boolean check = false;
//        for (int i = 0; i < listCart.size() ; i++) {
//            if (listCart.get(i).getUser().equals(cart.getUser())){
//                listCart.set(i,cart);
//                check = true;
//                break;
//            }
//        }
//        if (!check) {
//            listCart.add(cart);
//        }
//        cartImp.writeFormFileCart(listCart);
//
//    }
//    public Cart findById(int id) {
//        for (Cart cart : listCart) {
//            if(cart.getUser().getUserId()== id){
//                return cart;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean addToCart() {
//        Cart carts = findById(user.getUserId());
//        CartItem cartItem = new CartItem();
//        if (carts != null) {
//            for (CartItem cart : carts.getListCartItem()) {
//                if (cart.getBread().getBreadId() == cartItem.getBread().getBreadId()) {
//                    cart.setQuantity(cart.getQuantity() + cartItem.getQuantity());
//                    save(carts);
//                    return true;
//                }
//            }
//            List<CartItem> cartItemList = carts.getListCartItem();
//            cartItemList.add(cartItem);
//            carts.setListCartItem(cartItemList);
//            save(carts);
//            return true;
//        }else {
//            List<CartItem>  listCartItems= new ArrayList<>();
//            listCartItems.add(cartItem);
//            Cart newCart = new Cart();
//            save(newCart);
//            return true;
//        }
//    }
//    public void addProduct(Bread bread) {
//        listBread.add(bread);
//    }
//
//    public void removeProduct(Bread bread) {
//        listBread.remove(bread);
//    }
//
//    public int getNumOfProducts() {
//        return listBread.size();
//    }
//
//    public double getTotalCost() {
//        double totalCost = 0;
//        for (Bread bread : listBread) {
//            totalCost += bread.getExportPrice();
//        }
//        return totalCost;
//    }
//    @Override
//    public User getUserLogin() {
//        return null;
//    }
//    @Override
//    public List<Cart> readFormFileCart() {
//        List<Cart> listCart = new ArrayList<>();
//        File file = null;
//        FileInputStream fis = null;
//        ObjectInputStream ois = null;
//        try {
//            file = new File(DataURL.URL_CART_FILE);
//            if (file.exists()) {
//                fis = new FileInputStream(file);
//                ois = new ObjectInputStream(fis);
//                listCart = (List<Cart>) ois.readObject();
//            } else {
//                return listCart;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ois != null) {
//                    ois.close();
//                }
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (IOException ex2) {
//                ex2.printStackTrace();
//            }
//        }
//        return listCart;
//    }
//
//    @Override
//    public boolean writeFormFileCart(List<Cart> list) {
//        File file = null;
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
//        boolean returnData = true;
//        try {
//            file = new File(DataURL.URL_CART_FILE);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            fos = new FileOutputStream(file);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(list);
//        } catch (Exception ex) {
//            returnData = false;
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (oos != null) {
//                    oos.close();
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException ex2) {
//                ex2.printStackTrace();
//            }
//        }
//        return returnData;
//    }
//    @Override
//    public void displayData() {
//
//    }
//
//    @Override
//    public void inputData1(Scanner sc, List<Topping> list) {
//
//    }
//
//    @Override
//    public boolean create1(Topping topping) {
//        return false;
//    }
//
//    @Override
//    public List<Topping> readFromFileTopping() {
//        return null;
//    }
//
//    @Override
//    public boolean writeToFileTopping(List<Topping> list) {
//        return false;
//    }
//
//    @Override
//    public List<Bread> readFormFileBread() {
//        return null;
//    }
//
//    @Override
//    public boolean writeFormFileBread(List<Bread> list) {
//        return false;
//    }
//
//}
