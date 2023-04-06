package interface1;

import implement.Bread;
import implement.Topping;
import implement.User;

import java.util.List;
import java.util.Scanner;

public interface IBread {
    User inputData(Scanner sc);
    void inputData1(Scanner sc, List<Topping> list);

    void displayData();

    boolean create(User user);

    List<User> readFromFile();
    boolean writeToFile(List<User> list);
    boolean create1(Topping topping);
    List<Topping> readFromFileTopping();
    boolean writeToFileTopping(List<Topping> list);
    List<Bread> readFormFileBread();
    boolean writeFormFileBread(List<Bread> list);

}
