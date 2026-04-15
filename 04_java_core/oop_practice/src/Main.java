import repository.InMemoryUserRepository;
import repository.UserRepository;
import service.UserService;
import ui.UserConsoleUI;

public class Main {
    public static void main(String[] args) {
        UserRepository repo = new InMemoryUserRepository();
        
        UserService userService = new UserService(repo);
        
        UserConsoleUI ui = new UserConsoleUI(userService);

        ui.start();
    }
}
