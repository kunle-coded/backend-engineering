package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import exception.ValidationException;
import model.Role;
import model.User;
import service.UserService;

public class UserConsoleUI {
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserConsoleUI(UserService userService){
        this.userService = userService;
    }

    public void start(){
        boolean running = true;
        
        while (running) {
        System.out.println("\n--- User Management System ---");
        System.out.println("1. Register Admin | 2. Register User | 3. Guest | 4. Search by ID | 5. Search by Email | 6. List All | 7. Delete User | 8. Exit");
        

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> handleRegistration(Role.ADMIN);
            case "2" -> handleRegistration(Role.USER);
            case "3" -> handleRegistration(Role.GUEST);
            case "4" -> findUserById();
            case "5" -> findUserByEmail();
            case "6" -> listAllUsers();
            case "7" -> deleteUser();
            case "8" -> running = false;
            default -> System.out.println("Invalid choice!");
        }
        }
        
    }



    private void handleRegistration(Role role){
        System.out.println("--- " + role + " Registration ---");

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        String password = null;

        if(role != Role.GUEST){
            System.out.println("Enter password: ");
            password = scanner.nextLine();
        }

        try {
            User registeredUser = userService.registerUser(email, password, role);
            System.out.println("✅ " + role + " added successfully!");
            System.out.println(registeredUser.getId());
        } catch (ValidationException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    private void findUserById(){
        try {
            System.out.println("Enter user id: ");
            UUID id = UUID.fromString(scanner.nextLine());

            userService.findUserById(id).ifPresentOrElse(
                (u)-> System.out.println("✅ Found: " + u.getEmail() + " [Role: " + u.getRole() + "]"),
                ()->System.out.println("❌ No user exists with that ID."));
            
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid ID format. UUIDs look like: 8-4-4-4-12 hex digits.");
        }
        

        
    }

    private void findUserByEmail(){
        try {
            System.out.println("Enter user email: ");
            String email = scanner.nextLine();

            userService.findUserByEmail(email).ifPresentOrElse(
            (u) -> System.out.println("✅ Found: " + u.getEmail() + " [Role: " + u.getRole() + "]"),
            () -> System.out.println("❌ No user exists with that email."));
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid email format. Emails must contain '@'.");
        }
    }

    private void listAllUsers(){
        List<User> allUsers = userService.findAllUsers();

        if(allUsers.isEmpty()){
            System.out.println("No users in the system.");
            return;
        }

        System.out.println("--- System Users ---");
        allUsers.forEach(System.out::println);
        // allUsers.forEach(u -> System.out.println("ID: " + u.getId() + " | Email: " + u.getEmail()));

    }

    private void deleteUser(){
        try {
            System.out.println("Enter ID to delete: ");
        UUID id = UUID.fromString(scanner.nextLine());

        if(userService.findUserById(id).isPresent()){
            userService.deleteUser(id);
            System.out.println("🗑️  User deleted successfully.");
        }else{
            System.out.println("❌ Cannot delete: User not found.");
        }
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid ID format.");
        }
    }
    
}
