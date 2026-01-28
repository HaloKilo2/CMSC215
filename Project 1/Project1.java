import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project1 is the main program that finds the tallest basketball player
 * whose age is less than or equal to the average age of all players.
 * 
 * @author Lexie
 * @date November 10, 2025
 * Project: CMSC 215 Programming Project 1
 */
public class Project1 {
    
    /**
     * Main method that runs the program.
     * Prompts user for player information, calculates average age,
     * and finds the tallest player whose age is <= average.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        int totalAge = 0;
        
        // Input loop - read player information until empty string
        while (true) {
            System.out.print("Enter player's name, age, and height in feet and inches: ");
            String input = scanner.nextLine().trim();
            
            // Check if user entered empty string to stop
            if (input.isEmpty()) {
                break;
            }
            
            // Parse the input
            String[] parts = input.split("\\s+");
            
            // Validate input has at least 4 parts (name, age, feet, inches)
            if (parts.length < 4) {
                System.out.println("Invalid input. Please enter: name age feet inches");
                continue;
            }
            
            try {
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                int feet = Integer.parseInt(parts[2]);
                int inches = Integer.parseInt(parts[3]);
                
                // Create Height and Player objects
                Height height = new Height(feet, inches);
                Player player = new Player(name, height, age);
                
                // Add player to list and update total age
                players.add(player);
                totalAge += age;
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Age, feet, and inches must be numbers.");
            }
        }
        
        // Check if any players were entered
        if (players.isEmpty()) {
            System.out.println("No player data entered.");
            scanner.close();
            return;
        }
        
        // Calculate average age
        double averageAge = (double) totalAge / players.size();
        System.out.printf("The average age of all players is %.2f%n", averageAge);
        
        // Find the tallest player whose age is <= average
        Player tallestPlayer = null;
        int maxHeight = 0;
        
        for (Player player : players) {
            if (player.getAge() <= averageAge) {
                int playerHeight = player.getHeight().toInches();
                if (playerHeight > maxHeight) {
                    maxHeight = playerHeight;
                    tallestPlayer = player;
                }
            }
        }
        
        // Display the result
        if (tallestPlayer != null) {
            System.out.println("Tallest player whose age is less than the average is:");
            System.out.println("    " + tallestPlayer.toString());
        } else {
            System.out.println("No player found whose age is less than or equal to the average.");
        }
        
        scanner.close();
    }
}
