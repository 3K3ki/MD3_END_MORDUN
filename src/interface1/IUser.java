package interface1;

import implement.User;

import java.util.List;
import java.util.Scanner;

public interface IUser extends IBread{
    User inputData(Scanner sc);
    User getCurrentLogin();

    boolean create(User user);

    List<User> readFromFile();
    boolean writeToFile(List<User> list);
}
