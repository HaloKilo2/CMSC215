# CMSC 215 Intermediate Programming
## Programming Project 1 - Documentation
### Author: Lexie Thach
### Date: January 27, 2026

---

## 1. Discussion of Approach

### Project Design Overview
This project implements a basketball player management system that finds the tallest player whose age is less than or equal to the average age of all players. The design follows object-oriented programming principles with three distinct classes, each serving a specific purpose.

### Development Process

#### Stage 1: Height Class Implementation
The first stage focused on creating the `Height` class as an immutable data structure. Key design decisions included:
- **Normalization Strategy**: The constructor handles height normalization by converting all measurements to total inches, then computing feet and inches to ensure inches < 12. This approach was chosen to normalize once during object creation rather than repeatedly during string conversion.
- **Immutability**: The class uses `final` instance variables with no setter methods, ensuring that once created, Height objects cannot be modified. This design choice prevents accidental data corruption and makes the class thread-safe.

#### Stage 2: Player Class Implementation
The second stage created the `Player` class with an aggregation relationship to `Height`:
- **Aggregation Relationship**: Player "has-a" Height, not "is-a" Height. This relationship is implemented by storing a Height object as an instance variable.
- **Immutability**: Like Height, Player is immutable with all instance variables marked as `final`.
- **toString Implementation**: The Player's toString method delegates to Height's toString method, demonstrating proper encapsulation and code reuse.

#### Stage 3: Project1 Main Class Implementation
The final stage implemented the main program logic:
- **Input Processing**: Used a Scanner with a while loop to continuously read player data until an empty string is entered. The input parsing uses `split("\\s+")` to handle multiple spaces between inputs.
- **Data Storage**: An ArrayList<Player> stores all player objects, providing dynamic sizing and easy iteration.
- **Average Calculation**: Total age is accumulated during input, then divided by the number of players to compute the average.
- **Search Algorithm**: A linear search iterates through all players, tracking the tallest player whose age meets the criteria.

### Class Design and Relationships

**Height Class**
- Encapsulates height data with feet and inches
- Provides conversion to total inches for comparison
- Handles normalization automatically

**Player Class**  
- Aggregates a Height object (has-a relationship)
- Stores player name and age
- Provides getters for all attributes
- Implements formatted string representation

**Project1 Class**
- Contains main program logic
- Manages user interaction and input validation
- Coordinates between Player and Height objects
- Implements the search algorithm

### Implementation Steps

1. **Implemented Height class**: Created constructor with normalization, toInches() method, and toString() method
2. **Tested Height normalization**: Verified that inputs like 6'15" correctly become 7'3"
3. **Implemented Player class**: Created constructor, getters, and toString() with proper delegation
4. **Tested Player creation**: Verified Player objects correctly store and display information
5. **Implemented Project1 input loop**: Created user input handling with validation
6. **Implemented search algorithm**: Developed logic to find tallest player meeting age criteria
7. **Added edge case handling**: Handled empty input and no qualifying players scenarios
8. **Final integration testing**: Tested complete program with multiple test cases

---

## 2. UML Class Diagram

### Height Class
| **Height** |
|---|
| **Attributes** |
| - feet: int |
| - inches: int |
| **Methods** |
| + Height(int feet, int inches) |
| + toInches(): int |
| + toString(): String |

### Player Class
| **Player** |
|---|
| **Attributes** |
| - name: String |
| - height: Height |
| - age: int |
| **Methods** |
| + Player(String name, Height height, int age) |
| + getName(): String |
| + getHeight(): Height |
| + getAge(): int |
| + toString(): String |

### Project1 Class
| **Project1** |
|---|
| **Attributes** |
| (no instance variables) |
| **Methods** |
| + main(String[] args): void |

### Class Relationships

| **From Class** | **To Class** | **Relationship Type** | **Description** |
|---|---|---|---|
| Player | Height | Aggregation (has-a) | Player has a Height object. If Player is destroyed, Height can exist independently. Represented by a hollow diamond. |
| Project1 | Player | Dependency (uses) | Project1 uses Player objects but doesn't store them as instance variables (they're local to main method). Represented by a dashed arrow. |

---

## 3. Test Plan

### Test Case 1: Normal Operation with Multiple Players

**Purpose**: Verify the program correctly calculates average age and finds the tallest qualifying player.

**Input**:
```
Pavel 25 7 2
Lou 47 6 8
Kwan 37 6 2
Amir 60 6 0
Dai 20 7 4
[empty line]
```

**Expected Output**:
- Average age: 37.80
- Tallest player whose age <= average: Dai (age 20, height 7'4")

**Actual Output**:
```
Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: The average age of all players is 37.80
Tallest player whose age is less than the average is:
    Name: Dai Age: 20 Height: 7' 4"
```

**Result**: PASS

**Analysis**: 
- Average calculation: (25+47+37+60+20)/5 = 37.80 ✓
- Players <= 37.80: Pavel (25), Kwan (37), Dai (20)
- Heights in inches: Pavel=86", Kwan=74", Dai=88"
- Tallest: Dai at 88" (7'4") ✓

---

### Test Case 2: Height Normalization

**Purpose**: Demonstrate that height normalization works correctly when user enters inches >= 12.

**Input**:
```
Aza 27 6 15
Ami 45 7 8
Bal 28 6 1
[empty line]
```

**Expected Output**:
- User enters "6 15" (6 feet, 15 inches)
- Output should display "7' 3"" (normalized)
- Average age: 33.33
- Tallest player whose age <= average: Aza (age 27, height 7'3")

**Actual Output**:
```
Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: Enter player's name, age, and height in feet and inches: The average age of all players is 33.33
Tallest player whose age is less than the average is:
    Name: Aza Age: 27 Height: 7' 3"
```

**Result**: PASS

**Analysis**:
- Input: 6 feet, 15 inches = 6 × 12 + 15 = 87 total inches
- Normalized: 87 ÷ 12 = 7 feet, 87 % 12 = 3 inches
- Display: 7' 3" ✓
- The normalization is working correctly!

---

### Test Case 3: Empty Input (No Player Data)

**Purpose**: Verify the program handles the case where no player data is entered.

**Input**:
```
[empty line immediately]
```

**Expected Output**:
```
Enter player's name, age, and height in feet and inches: No player data entered.
```

**Actual Output**:
```
Enter player's name, age, and height in feet and inches: No player data entered.
```

**Result**: PASS

**Analysis**: Program correctly detects empty ArrayList and displays appropriate message instead of attempting to calculate with no data.

---

### Test Case 4: All Players Older Than Average

**Purpose**: Verify behavior when no players qualify (all ages > average).

**Test**: Manual test could be performed with input like:
```
Young 20 6 0
Old1 50 6 5
Old2 60 7 0
```
Average would be 43.33, and only "Young" qualifies, so Young would be selected.

---

### Test Case 5: Ties in Height

**Purpose**: Verify behavior when multiple players have the same maximum height.

**Expected Behavior**: The first player encountered with the maximum height should be returned (based on insertion order in ArrayList).

---

## 4. Lessons Learned

This project provided valuable insights into object-oriented design and Java programming:

1. **Immutability Benefits**: Creating immutable classes (Height and Player) prevented accidental data modification and made the code more reliable. The use of `final` keywords enforced this design at compile time, catching potential errors early.

2. **Separation of Concerns**: Dividing functionality into three distinct classes (Height for data representation, Player for player modeling, Project1 for program logic) made the code more maintainable and testable. Each class had a single, clear responsibility.

3. **Constructor Logic for Normalization**: Implementing height normalization in the Height constructor rather than in the toString() method was more efficient. This demonstrates the importance of considering where computations should occur - once at creation versus every time a method is called.

4. **Input Validation Challenges**: Handling user input required careful consideration of edge cases - empty strings, invalid numbers, and insufficient data. Using split() with regex and try-catch blocks provided robust error handling.

5. **Aggregation vs. Inheritance**: The project reinforced understanding of when to use composition (has-a) versus inheritance (is-a). A Player has a Height, but is not a type of Height, making aggregation the correct choice.

6. **Testing Strategy**: Creating multiple test cases with different scenarios (normal operation, edge cases, normalization) was essential for verifying correctness. The test cases helped catch issues that weren't apparent from the requirements alone.

7. **Documentation Value**: Writing comprehensive Javadoc comments and inline comments made the code self-documenting and easier to understand when revisiting it later. Good documentation is as important as good code.

8. **ArrayList Advantages**: Using ArrayList instead of arrays provided flexibility in handling an unknown number of players without pre-allocating space. The dynamic resizing and built-in methods (isEmpty(), size()) simplified the implementation.

Overall, this project demonstrated how proper object-oriented design principles lead to clean, maintainable, and extensible code. The experience of implementing immutable classes, aggregation relationships, and comprehensive error handling will be valuable for future programming projects.

---

## Appendix: Complete Source Code Files

### Height.java
See Height.java in the zip file.

### Player.java  
See Player.java in the zip file.

### Project1.java
See Project1.java in the zip file.

---
