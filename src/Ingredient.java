import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ingredient {
  private String name;
  private String isFood; 
  private int calories;
  private double protein;
  private double carbohydrates;
  private double fat;

  // Constructor to initialize ingredient attributes
  public Ingredient(String name, String isFood, int calories, double protein, double carbohydrates, double fat) {
    this.name = name;
    this.isFood = isFood;
    this.calories = calories;
    this.protein = protein;
    this.carbohydrates = carbohydrates;
    this.fat = fat;
  }

  // Getters and setters for each attribute (name, isFood, calories, protein, carbohydrates, fat)

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIsFood() {
    return isFood;
  }

  public void setIsFood(String isFood) {
    this.isFood = isFood;
  }

  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public double getProtein() {
    return protein;
  }

  public void setProtein(double protein) {
    this.protein = protein;
  }

  public double getCarbohydrates() {
    return carbohydrates;
  }

  public void setCarbohydrates(double carbohydrates) {
    this.carbohydrates = carbohydrates;
  }

  public double getFat() {
    return fat;
  }

  public void setFat(double fat) {
    this.fat = fat;
  }

  // Method to load ingredients from a file
  public static ArrayList<Ingredient> loadIngredientsFromFile() {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    String filePath = "ingredients.txt"; // Updated file path

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] ingredientInfo = line.split("\\|");

        if (ingredientInfo.length >= 6) {
          String name = ingredientInfo[0].trim();
          String isFood = ingredientInfo[1].trim();
          int calories = Integer.parseInt(ingredientInfo[2].trim());
          double protein = Double.parseDouble(ingredientInfo[3].trim());
          double carbohydrates = Double.parseDouble(ingredientInfo[4].trim());
          double fat = Double.parseDouble(ingredientInfo[5].trim());

          // Create Ingredient objects and add them to the list
          Ingredient ingredient = new Ingredient(name, isFood, calories, protein, carbohydrates, fat);
          ingredients.add(ingredient);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ingredients;
  }

  // Override toString method to display ingredient details
  @Override
  public String toString() {
    return "Ingredient: " + name + "\nIs Food (N for drink): " + isFood + "\nCalories: " + calories +
      "\nProtein: " + protein + "g\nCarbohydrates: " + carbohydrates + "g\nFat: " + fat + "g";
  }
}
