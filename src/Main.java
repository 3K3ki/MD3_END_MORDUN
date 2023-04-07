import implement.User;

public class Main {
    public static void main(String[] args) {
        User userImpl = new User();
        User admin = new User(1,"danghung090@gmail.com","admin123","123456","",true,true);
        userImpl.create(admin);
    }
}