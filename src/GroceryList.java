import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The GroceryList class represents a system for managing a grocery shopping list.
 * It provides features to add, remove, display, and save shopping items.
 */
public class GroceryList {
  List < ShoppingItem > shoppingItems;

  /**
   * Constructs a new GroceryList object with an empty list of shopping items.
   */
  public GroceryList() {
    this.shoppingItems = new ArrayList < > ();
  }

  // Add an item to the grocery list
  public void addItem(ShoppingItem item) {
    shoppingItems.add(item);
  }

  // Remove an item from the grocery list
  public void removeItem(ShoppingItem item) {
    shoppingItems.remove(item);
  }

  // Display the items in the grocery list
  public void displayGroceryList() {
    System.out.println("Grocery List:");
    for (ShoppingItem item: shoppingItems) {
      System.out.println(item);
    }
  }

  // Method to manually add an item to the grocery list
  public void manuallyAddItem(String ingredientName, int quantity) {
    // Load the ingredient details from the Ingredients.txt file or any other source
    // In this example, we create a placeholder Ingredient object with hardcoded values
    Ingredient ingredient = new Ingredient(ingredientName, "Y", 100, 10.0, 20.0, 5.0);
    ShoppingItem item = new ShoppingItem(ingredient, quantity);
    addItem(item);
  }

  // Display the grocery list with item numbers
  public void displayGroceryListWithNumbers() {
    System.out.println("Grocery List:");
    for (int i = 0; i < shoppingItems.size(); i++) {
      System.out.println((i + 1) + ". " + shoppingItems.get(i));
    }
  }

  // Clear the entire grocery list
  public void clearGroceryList() {
    shoppingItems.clear();
    System.out.println("Grocery list cleared.");
  }

  // Method to display all ingredients from the Ingredients.txt file
  public static void displayAllIngredients() {
    List < Ingredient > ingredients = Ingredient.loadIngredientsFromFile();
    System.out.println("All Ingredients:");
    for (Ingredient ingredient: ingredients) {
      System.out.println(ingredient + "\n");
    }
  }

  /**
   * Handles the main menu for the Grocery List application, allowing users to interact with the list.
   */
  public static void handleGroceryList() {
    // Create an instance of GroceryList
    GroceryList groceryList = new GroceryList();

    // Load the grocery list from a file if it exists
    groceryList.loadGroceryListFromFile();

    // Display menu options for the user
    Scanner scanner = new Scanner(System.in);
    int choice;

    while (true) { // Infinite loop
      // Display the current grocery list at the top of the menu
      groceryList.displayGroceryListWithNumbers();

      System.out.println("\nGrocery List Menu:");
      System.out.println("1. Add Item to Grocery List");
      System.out.println("2. Remove Item from Grocery List");
      System.out.println("3. Clear Entire Grocery List");
      System.out.println("4. Display All Ingredients");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");

      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
      } else {
        // Handle non-integer input
        String input = scanner.next();
        if ("=".equals(input)) {
          // Exit the loop if '=' is entered
          System.out.println("Exiting the Grocery List menu.");
          break;
        } else {
          System.out.println("Invalid input. Please enter a valid option.");
          continue; // Continue to the next iteration of the loop
        }
      }

      scanner.nextLine(); // Consume the newline character

      switch (choice) {
      case 1:
        // Add Item to Grocery List
        System.out.println("Enter items to add (Item added to the grocery list - Enter '=' to exit): ");
        while (true) { // Allow continuous input until '=' is entered
          GroceryList.displayAllIngredients(); // Display all ingredients
          System.out.print("Enter the name of the ingredient to add (or '=' to exit): ");

          String ingredientName = scanner.nextLine();

          if ("=".equals(ingredientName)) {
            // Exit the loop if '=' is entered
            break;
          }

          System.out.print("Enter the quantity: ");
          int quantity = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character
          groceryList.manuallyAddItem(ingredientName, quantity);
          System.out.println("Item added to the grocery list (Enter '=' to exit).");
        }
        break;

      case 2:
        // Remove Item from Grocery List
        groceryList.displayGroceryListWithNumbers(); // Display grocery list with numbers
        System.out.print("Enter the number of the item to remove: ");
        int itemNumber = scanner.nextInt();
        if (itemNumber >= 1 && itemNumber <= groceryList.shoppingItems.size()) {
          ShoppingItem itemToRemove = groceryList.shoppingItems.get(itemNumber - 1);
          groceryList.removeItem(itemToRemove);
          System.out.println("Item removed from the grocery list.");
        } else {
          System.out.println("Invalid item number.");
        }
        break;

      case 3:
        // Clear Entire Grocery List
        groceryList.clearGroceryList();
        System.out.println("Entire grocery list cleared.");
        break;

      case 4:
        // Display All Ingredients
        GroceryList.displayAllIngredients();
        break;

      case 5:
        // Save the grocery list to a file and exit
        groceryList.saveGroceryListToFile();
        System.out.println("Exiting the Grocery List menu.");
        return; // Exit the method

      default:
        System.out.println("Invalid choice. Please enter a valid option.");
        break;
      }
    }
  }

  // Load the grocery list from a file
  public void loadGroceryListFromFile() {
    try {
      File file = new File("groceryList.txt");
      if (file.exists()) {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
          // Parse the line to extract ingredient name and quantity
          String[] parts = line.split(",");
          if (parts.length == 2) {
            String ingredientName = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            manuallyAddItem(ingredientName, quantity);
          }
        }
        reader.close();
      }
    } catch (IOException e) {
      System.err.println("Error loading grocery list from file: " + e.getMessage());
    }
  }

  // Save the grocery list to a file
  public void saveGroceryListToFile() {
    try {
      File file = new File("groceryList.txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));

      for (ShoppingItem item: shoppingItems) {
          String line = item.getIngredient().getName() + "," + item.getQuantity();
          writer.write(line);
          writer.newLine();
        }

        writer.close();
        System.out.println("Grocery list saved to 'groceryList.txt'.");
      } catch (IOException e) {
        System.err.println("Error saving grocery list to file: " + e.getMessage());
      }
    }
  }

