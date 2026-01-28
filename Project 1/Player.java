/**
 * Player class represents a basketball player with a name, height, and age.
 * This class is immutable - once created, the values cannot be changed.
 * 
 * @author Lexie
 * @date November 10, 2025
 */
public class Player {
    // Instance variables
    private final String name;
    private final Height height;
    private final int age;
    
    /**
     * Constructor that creates a Player object with the given name, height, and age.
     * 
     * @param name The player's name
     * @param height The player's height (as a Height object)
     * @param age The player's age
     */
    public Player(String name, Height height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }
    
    /**
     * Gets the player's name.
     * 
     * @return The player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the player's height.
     * 
     * @return The player's height as a Height object
     */
    public Height getHeight() {
        return height;
    }
    
    /**
     * Gets the player's age.
     * 
     * @return The player's age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Returns a string representation of the player with labeled fields.
     * 
     * @return String representation of the player
     */
    @Override
    public String toString() {
        return "Name: " + name + " Age: " + age + " Height: " + height.toString();
    }
}
