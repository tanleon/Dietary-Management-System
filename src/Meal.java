import java.io.*;
import java.util.*;

public class Meal  {
    private String name;
    private String isFood;
    private String instructions;
    private int calories;
    private double protein;
    private double carbohydrates;
    private double fat;
    
    // Constructor to initialize meal attributes
    public Meal(String name, String isFood, String instructions, int calories, double protein, double carbohydrates, double fat) {
        this.name = name;
        this.isFood = isFood;
        this.instructions = instructions;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }
    // Display instructions for a selected meal
    public static void displayMealInstructions() {
        String filePath = "meals.txt";
        String line;
        ArrayList<String> mealNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int mealNumber = 1;

            while ((line = reader.readLine()) != null) {
                String[] mealInfo = line.split("\\|");

                if (mealInfo.length >= 3) {
                    String mealName = mealInfo[0].trim();
                    mealNames.add(mealName);
                    System.out.println(mealNumber + ". " + mealName);
                    mealNumber++;
                }
            }

            // Create a Scanner to read user input
            Scanner scanner = new Scanner(System.in);

            // Ask the user to select a meal by number
            System.out.print("Select the meal by entering the meal number\n (the info will be shown above your profile): ");
            int selectedMealNumber = scanner.nextInt();

            // Check if the selected meal number is valid
            if (selectedMealNumber >= 1 && selectedMealNumber <= mealNames.size()) {
                String selectedMealName = mealNames.get(selectedMealNumber - 1);

                // Now, find and display the instructions for the selected meal
                try (BufferedReader selectedReader = new BufferedReader(new FileReader(filePath))) {
                    while ((line = selectedReader.readLine()) != null) {
                        String[] mealInfo = line.split("\\|");

                        if (mealInfo.length >= 3 && mealInfo[0].trim().equals(selectedMealName)) {
                            String instructions = mealInfo[2].trim();

                            // Create a box of asterisks around the instructions
                            int boxWidth = instructions.length() + selectedMealName.length() + 4; // Add 4 for asterisks, spaces, and meal name
                            String asterisksLine = "*".repeat(boxWidth);

                            System.out.println(asterisksLine); // Top asterisk line
                            System.out.println("\n " + selectedMealName + ": " + instructions + " \n"); // Meal name, instructions, spaces, and asterisks
                            System.out.println(asterisksLine); // Bottom asterisk line
                            return; // Found the meal, no need to continue searching
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid meal number. Please select a valid meal number.");
            }

            // Close the scanner
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Plan and manage meals for a week
    public void mealPlan() {
        Scanner scanner = new Scanner(System.in);
        String[][] schedule = loadMealPlanFromFile("mealPlan.txt");

        if (schedule == null) {
            System.out.println("Failed to load meal plan. Creating a new one.");
            schedule = createEmptySchedule();
        }

        while (true) {
            System.out.println("\n|CURRENT WEEKLY MEAL PLAN|");
            System.out.println("========================================================================");
            displayMealPlan(schedule);

            System.out.print("\nEnter the day (1 for MONDAY...7 for SUNDAY) or '=' to exit to the main menu: ");
            String input = scanner.nextLine();

            if (input.equals("=")) {
                break; // Exit to the main menu
            }

            try {
                int dayIndex = Integer.parseInt(input) - 1; // Convert to 0-based index

                if (dayIndex >= 0 && dayIndex < 7) {
                    System.out.print("Enter the meal slot (1 for Breakfast...3 for Lunch): ");
                    int mealSlotIndex = Integer.parseInt(scanner.nextLine()) - 1; // Convert to 0-based index

                    if (mealSlotIndex >= 0 && mealSlotIndex < 3) {
                        System.out.print("Enter the meal description (anything can be written): ");
                        String mealDescription = scanner.nextLine();

                        schedule[dayIndex][mealSlotIndex] = mealDescription;
                        saveMealPlanToFile(schedule, "mealPlan.txt");
                        System.out.println("Meal plan updated successfully!");
                    } else {
                        System.out.println("Invalid meal slot.");
                    }
                } else {
                    System.out.println("Invalid day.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or '='.");
            }
        }
    }

    // Load the meal plan from a text file
    private String[][] loadMealPlanFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[][] schedule = new String[7][3]; // 7 days x 3 meal slots

            for (int i = 0; i < 7; i++) {
                String line = reader.readLine();
                String[] meals = line.split("\\|");
                if (meals.length == 3) {
                    schedule[i] = meals;
                }
            }

            return schedule;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Save the meal plan to a text file
    private void saveMealPlanToFile(String[][] schedule, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String[] meals : schedule) {
                writer.println(String.join("|", meals));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create an empty schedule with placeholders
    private String[][] createEmptySchedule() {
        String[][] schedule = new String[7][3]; // 7 days x 3 meal slots

        for (String[] meals : schedule) {
            Arrays.fill(meals, "Empty");
        }

        saveMealPlanToFile(schedule, "mealPlan.txt");
        return schedule;
    }

    // Display the meal plan without table formatting
    private void displayMealPlan(String[][] schedule) {
        for (int i = 0; i < 7; i++) {
            System.out.println("\n----------");
            String day = getDayOfWeek(i);
            System.out.println(day);
            System.out.println("----------");

            for (int j = 0; j < 3; j++) {
                String mealSlot = getMealSlot(j);
                String mealDescription = schedule[i][j];

                System.out.println(mealSlot + "\n" + mealDescription + "\n");
            }

            System.out.println("========================================================================");
        }
    }

    // Helper method to get the day of the week based on its index
    private String getDayOfWeek(int index) {
        String[] daysOfWeek = {
            "1.MONDAY",
            "2.TUESDAY",
            "3.WEDNESDAY",
            "4.THURSDAY",
            "5.FRIDAY",
            "6.SATURDAY",
            "7.SUNDAY"
        };
        return daysOfWeek[index];
    }

    // Helper method to get the meal slot based on its index
    private String getMealSlot(int index) {
        String[] mealSlots = {
            "|Breakfast|",
            "|Lunch|",
            "|Dinner|"
        };
        return mealSlots[index];
    }

    public void categorizeMealsByCriteria() {
        String filePath = "meals.txt";
        String line;

        // Initialize lists to hold categorized meals
        List<String> highCaloriesMeals = new ArrayList<>();
        List<String> highFatMeals = new ArrayList<>();
        List<String> highProteinMeals = new ArrayList<>();
        List<String> highCarbsMeals = new ArrayList<>();
        List<String> lowCaloriesMeals = new ArrayList<>();
        List<String> lowCarbsMeals = new ArrayList<>();
        List<String> lowFatMeals = new ArrayList<>();
        List<String> lowProteinMeals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] mealInfo = line.split("\\|");

                if (mealInfo.length >= 7) {
                    String mealName = mealInfo[0].trim();
                    int calories = Integer.parseInt(mealInfo[3].trim());
                    double fat = Double.parseDouble(mealInfo[6].trim());
                    double protein = Double.parseDouble(mealInfo[4].trim());
                    double carbs = Double.parseDouble(mealInfo[5].trim());

                    // Categorize meals based on criteria
                    if (calories > 200) {
                        highCaloriesMeals.add(mealName);
                    }
                    if (fat > 10) {
                        highFatMeals.add(mealName);
                    }
                    if ((protein / calories) > 0.15) { // Adjusted protein criterion
                        highProteinMeals.add(mealName);
                    }
                    if (carbs > 30) {
                        highCarbsMeals.add(mealName);
                    }

                    // Categorize meals as low in specific nutrients
                    if (calories < 150) { // Added criterion for low calories
                        lowCaloriesMeals.add(mealName);
                    }
                    if (carbs < 10) { // Added criterion for low carbs
                        lowCarbsMeals.add(mealName);
                    }
                    if (fat < 5) {
                        lowFatMeals.add(mealName);
                    }
                    if ((protein / calories) < 0.1) {
                        lowProteinMeals.add(mealName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print categorized lists with headings and separators
        System.out.println("High in Calories:");
        printMealList(highCaloriesMeals);
        System.out.println("Low in Calories:"); 
        printMealList(lowCaloriesMeals);
        System.out.println("High in Fats:");
        printMealList(highFatMeals);
        System.out.println("High in Protein:");
        printMealList(highProteinMeals);
        System.out.println("High in Carbs:");
        printMealList(highCarbsMeals);
        System.out.println("Low in Carbs:"); 
        printMealList(lowCarbsMeals);
        System.out.println("Low in Fats:");
        printMealList(lowFatMeals);
        System.out.println("Low in Protein:");
        printMealList(lowProteinMeals);
    }

    // Helper method to print a list of meals
    private void printMealList(List<String> mealList) {
        if (mealList.isEmpty()) {
            System.out.println("None");
        } else {
            for (String meal : mealList) {
                System.out.println("- " + meal);
            }
        }
        System.out.println("====================");
    }
}

