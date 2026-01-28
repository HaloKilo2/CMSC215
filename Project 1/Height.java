/**
 * Height class represents a person's height in feet and inches.
 * This class is immutable - once created, the values cannot be changed.
 * 
 * @author Lexie
 * @date November 10, 2025
 */
public class Height {
    // Instance variables
    private final int feet;
    private final int inches;
    
    /**
     * Constructor that creates a Height object with the given feet and inches.
     * Automatically normalizes the height so that inches is less than 12.
     * 
     * @param feet The number of feet
     * @param inches The number of inches
     */
    public Height(int feet, int inches) {
        // Normalize the height: convert excess inches to feet
        int totalInches = feet * 12 + inches;
        this.feet = totalInches / 12;
        this.inches = totalInches % 12;
    }
    
    /**
     * Converts the height to total inches.
     * 
     * @return The total height in inches
     */
    public int toInches() {
        return feet * 12 + inches;
    }
    
    /**
     * Returns a string representation of the height in the format: feet' inches"
     * For example: 5' 9"
     * 
     * @return String representation of the height
     */
    @Override
    public String toString() {
        return feet + "' " + inches + "\"";
    }
}
