package interface1;

import implement.Bread;
import implement.Cart;
import implement.Topping;
import implement.User;

import java.util.List;
import java.util.Scanner;

public interface IBread {

    void inputData1(Scanner sc, List<Topping> list);
    void displayData();
    boolean create1(Topping topping);
    List<Topping> readFromFileTopping();
    boolean writeToFileTopping(List<Topping> list);
    List<Bread> readFormFileBread();
    boolean writeFormFileBread(List<Bread> list);
    List<Cart> readFormFileCart();
    boolean writeFormFileCart(List<Cart> list);

}
