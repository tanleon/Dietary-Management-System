import java.util.ArrayList;
import java.util.Scanner;

public class DietaryManagementProgram {
  private Scanner scanner;
  private UserData userData;
  private UserDataFileManager dataFileManager;

  public DietaryManagementProgram() {
    scanner = new Scanner(System.in);
    dataFileManager = new UserDataFileManager("userData.txt");
    userData = dataFileManager.loadUserData();
  }

  public static void main(String[] args) {
    DietaryManagementProgram program = new DietaryManagementProgram();
    program.run();
  }

  public void run() {
    while (true) {
      displayMainMenu();
    }
  }

  private void displayMainMenu() {
    // Display the user's profile details and the main menu options
    displayUserProfile();
    System.out.println("=============================================================================================");
    System.out.println("||=================================== MAIN MENU ===========================================||");
    System.out.println("|| 1. Edit Personal Details and Choose Diet                                                ||");
    System.out.println("|| 2. View Diet Descriptions                                                               ||");
    System.out.println("|| 3. View Chosen Diet Recommended Foods and Drinks                                        ||");
    System.out.println("|| 4. Grocery List                                                                         ||");
    System.out.println("|| 5. Quick Meal Info/Instructions Reference                                               ||");
    System.out.println("|| 6. Meal Weekly Plan                                                                     ||");
    System.out.println("|| 7. Meal Nutrient Categories                                                             ||");
    System.out.println("||=========================================================================================||");
    System.out.println("=============================================================================================");
    System.out.println("0. Exit");
    System.out.println("=============================================================================================");
 
    // Get the user's choice
    int option = getInputOption();
    // Initialize Meal object 
    Meal currentMeal;
    currentMeal = new Meal(null, null, null, 0, 0.0, 0.0, 0.0);
    
    switch (option) {
    case 1:
      System.out.println();
      displayUserProfile();
      enterPersonalDetails();
      break;
    case 2:
      displayDietDescriptionsAndChooseDiet();
      break;
    case 3:
      displayChosenDietFoodsAndDrinks();
      System.out.println("Press '=' to return to the main menu...");
      while (true) {
        char input = scanner.next().charAt(0);
        if (input == '=') {
          break; // Return to the main menu
        }
      }
      break;
    case 4:
      GroceryList.handleGroceryList();
      break;
    case 5:
      Meal.displayMealInstructions();
      break;
    case 6:
      currentMeal.mealPlan();
      break;
    case 7:
      currentMeal.categorizeMealsByCriteria();
      System.out.println("Press '=' to return to the main menu...");
      while (true) {
        char input = scanner.next().charAt(0);
        if (input == '=') {
          break; // Return to the main menu
        }
      }
      break;
    case 0:
      // Exit the program 
      System.exit(0);
      break;
    default:
      System.out.println("Invalid option. Please try again.");
    }
  }

  private int getInputOption() {
    System.out.print("Enter option: ");
    return scanner.nextInt();
  }

  private void displayUserProfile() {
    // Display the user's profile details
    System.out.println("\n======================================================================================================================================================================================");
    System.out.println("|YOUR PROFILE|");
    System.out.println("|NAME:| " + userData.getName());
    System.out.println("|AGE:| " + userData.getAge());
    System.out.println("|WEIGHT:| " + userData.getWeight() + " kg");
    System.out.println("|HEIGHT:| " + userData.getHeight() + " cm");
    System.out.println("|TARGET WEIGHT:| " + userData.getTargetWeight() + " kg");
    System.out.println("|HEALTH GOALS:| " + userData.getHealthGoals());
    System.out.println("|DIET:| " + userData.getDiet());
    System.out.println("======================================================================================================================================================================================");
    System.out.println();
  }

  private void enterPersonalDetails() {
    // Prompt user to enter personal details
    System.out.println("Edit Personal Details");

    while (true) {
      System.out.println("=========================================================");
      System.out.println("|| Select the attribute to edit by entering a number1: ||");
      System.out.println("|| 1. Name                                             ||");
      System.out.println("|| 2. Age                                              ||");
      System.out.println("|| 3. Weight (in kg)                                   ||");
      System.out.println("|| 4. Height (in cm)                                   ||");
      System.out.println("|| 5. Target Weight (in kg)                            ||");
      System.out.println("|| 6. Health Goals                                     ||");
      System.out.println("|| 7. Diet (choose from popular Diets)                 ||");
      System.out.println("|| 0. Return to Main Menu                              ||");
      System.out.println("=========================================================");

      int attributeChoice = getInputOption();

      switch (attributeChoice) {
      case 1:
        // Consume the newline character before reading the name
        scanner.nextLine();
        System.out.print("Enter new Name: ");
        String newName = scanner.nextLine();
        userData.setName(newName);
        break;
      case 2:
        System.out.print("Enter new Age: ");
        int newAge = scanner.nextInt();
        userData.setAge(newAge);
        break;
      case 3:
        System.out.print("Enter new Weight (in kg): ");
        double newWeight = scanner.nextDouble();
        userData.setWeight(newWeight);
        break;
      case 4:
        System.out.print("Enter new Height (in cm): ");
        double newHeight = scanner.nextDouble();
        userData.setHeight(newHeight);
        break;
      case 5:
        System.out.print("Enter new Target Weight (in kg): ");
        double newTargetWeight = scanner.nextDouble();
        userData.setTargetWeight(newTargetWeight);
        break;
      case 6:
        // Consume the newline character before reading health goals
        scanner.nextLine();
        System.out.print("Enter new Health Goals: ");
        String newHealthGoals = scanner.nextLine();
        userData.setHealthGoals(newHealthGoals);
        break;
      case 7:
        // Choose from predefined diets
        System.out.println("***************************************");
        System.out.println("* Choose a Diet by entering a number: *");
        System.out.println("* 1. Intermittent Fasting             *");
        System.out.println("* 2. Mediterranean Diet               *");
        System.out.println("* 3. DASH Diet                        *");
        System.out.println("* 4. Ketogenic Diet                   *");
        System.out.println("* 5. Paleolithic Diet                 *");
        System.out.println("* 6. Plant-based diet                 *");
        System.out.println("***************************************");
        System.out.println("Enter Option:");

        int dietChoice = scanner.nextInt();

        switch (dietChoice) {
        case 1:
          userData.setDiet("Intermittent Fasting");
          dataFileManager.saveChosenDiet("Intermittent Fasting");
          break;
        case 2:
          userData.setDiet("Mediterranean Diet");
          dataFileManager.saveChosenDiet("Mediterranean Diet");
          break;
        case 3:
          userData.setDiet("DASH Diet");
          dataFileManager.saveChosenDiet("DASH Diet");
          break;
        case 4:
          userData.setDiet("Ketogenic Diet");
          dataFileManager.saveChosenDiet("Ketogenic Diet");
          break;
        case 5:
          userData.setDiet("Paleolithic Diet");
          dataFileManager.saveChosenDiet("Paleolithic Diet");
          break;
        case 6:
          userData.setDiet("Plant-based diet");
          dataFileManager.saveChosenDiet("Plant-based diet");
          break;
        default:
          System.out.println("Invalid diet choice.");
          break;
        }
        break;
      case 0:
        // Save the updated user data to a file and exit the loop
        dataFileManager.saveUserData(userData);
        System.out.println("Personal details updated successfully.");
        return;
      default:
        System.out.println("Invalid choice. Please try again.");
        break;
      }
    }
  }
  //Display diet description options 
  private void displayDietDescriptionsAndChooseDiet() {
    while (true) {
      System.out.println("\n|Diet Descriptions|");
      System.out.println("***************************************");
      System.out.println("* 1. Intermittent Fasting             *");
      System.out.println("* 2. Mediterranean Diet               *");
      System.out.println("* 3. DASH Diet                        *");
      System.out.println("* 4. Ketogenic Diet                   *");
      System.out.println("* 5. Paleolithic Diet                 *");
      System.out.println("* 6. Plant-based diet                 *");
      System.out.println("***************************************");
      System.out.println("0. Go back to Main Menu");

      int option = getInputOption();

      switch (option) {
      case 1:
        displayDietInfo("Intermittent Fasting");
        break;
      case 2:
        displayDietInfo("Mediterranean Diet");
        break;
      case 3:
        displayDietInfo("DASH Diet");
        break;
      case 4:
        displayDietInfo("Ketogenic Diet");
        break;
      case 5:
        displayDietInfo("Paleolithic Diet");
        break;
      case 6:
        displayDietInfo("Plant-based Diet");
        break;
      case 0:
        return; // Go back to main menu
      default:
        System.out.println("Invalid choice.");
      }
    }
  }
  // Display Info for each diet 
  private void displayDietInfo(String dietName) {
    switch (dietName) {
    case "Intermittent Fasting":
      System.out.println("\n**Intermittent Fasting**");
      System.out.println("\n**Description and How it Works:**");
      System.out.println("Intermittent fasting is a dietary approach where you alternate between eating and fasting on specific schedules.");
      System.out.println("The three main methods include Alternate Day Fasting (ADF), Time-Restricted Feeding (TRF), and the 5:2 method.");
      System.out.println("ADF involves alternating normal eating days with fasting days, either completely or with minimal caloric intake.");
      System.out.println("TRF restricts eating to a specific time window, such as 16:8, where you eat within an 8-hour period daily.");
      System.out.println("The 5:2 method includes regular eating for five days and fasting for two non-consecutive days each week.");
      System.out.println("Intermittent fasting aims to reduce overall calorie intake by limiting the time available for eating.\n");

      System.out.println("**Benefits:**");
      System.out.println("Intermittent fasting can aid in weight loss by naturally reducing daily calorie consumption.");
      System.out.println("It simplifies dieting by focusing on when you eat rather than counting calories, making it easier to maintain.\n");

      System.out.println("**Disadvantages:**");
      System.out.println("Research suggests that intermittent fasting is not significantly more effective than calorie-restricted diets for weight loss.");
      System.out.println("Individuals with diabetes or those taking antidiabetic medications should be cautious due to the risk of hypoglycemia.\n");

      System.out.println("**What to Avoid:**");
      System.out.println("During eating periods, avoid binge-eating and opt for nutritious foods like whole grains, lean proteins, and high-fiber fruits and vegetables.");
      System.out.println("Stay hydrated throughout the day.\n");
      break;

    case "Mediterranean Diet":
      System.out.println("\n**Mediterranean Diet**");
      System.out.println("\n**Description and How it Works:**");
      System.out.println("The Mediterranean diet is based on the eating patterns of Mediterranean countries.");
      System.out.println("It promotes a diet rich in fruits, vegetables, nuts, legumes, whole grains, and monounsaturated fats (e.g., olive oil),");
      System.out.println("with moderate consumption of lean meats and dairy.");
      System.out.println("Red meat is limited in this diet, focusing on foods that provide satiety and potentially reducing calorie intake.\n");

      System.out.println("**Benefits:**");
      System.out.println("The Mediterranean diet may improve heart health, lower cholesterol, reduce blood pressure, and stabilize blood sugar levels.");
      System.out.println("It can also play a role in cancer prevention.\n");

      System.out.println("**Disadvantages:**");
      System.out.println("While effective for weight loss, it may not be superior to other diets for individuals with obesity.");
      System.out.println("Adhering to the diet consistently can be challenging for some.\n");

      System.out.println("**What to Avoid:**");
      System.out.println("Moderation is key. Prioritize vegetables, fruits, and whole grains,");
      System.out.println("but be mindful not to overconsume fats, nuts, and alcohol.");
      System.out.println("Avoid excessive red wine consumption, especially if pregnant or with liver disease.\n");
      break;

    case "DASH Diet":
      System.out.println("\n**DASH Diet**");
      System.out.println("\n**Description and How it Works:**");
      System.out.println("The DASH diet aims to lower high blood pressure by emphasizing fruits, vegetables, whole grains,");
      System.out.println("low-fat dairy, and lean proteins while limiting red meat, refined sugars, saturated fats, and sodium.");
      System.out.println("Reduced sodium intake helps reduce water retention and lower blood pressure.\n");

      System.out.println("**Benefits:**");
      System.out.println("The DASH diet not only lowers blood pressure but can also improve blood sugar, triglycerides,");
      System.out.println("and cholesterol levels. It may reduce the risk of colon cancer and benefit other health conditions.\n");

      System.out.println("**Disadvantages:**");
      System.out.println("Limiting sodium can make food taste bland, and the effort to prepare low-sodium meals can be a barrier.");
      System.out.println("People with specific medical conditions may need to modify the DASH diet.\n");

      System.out.println("**What to Avoid:**");
      System.out.println("Minimize prepackaged and restaurant foods high in sodium.");
      System.out.println("Limit dining out and be cautious about processed foods.\n");
      break;

    case "Ketogenic Diet":
      System.out.println("\n**Ketogenic Diet (Keto)**");
      System.out.println("\n**Description and How it Works:**");
      System.out.println("The ketogenic diet restricts carbohydrates to 20–50 grams daily and promotes high-fat and protein intake.");
      System.out.println("It induces ketosis, where the body burns fat for energy.");
      System.out.println("Ketosis may reduce appetite, leading to calorie reduction.\n");

      System.out.println("**Benefits:**");
      System.out.println("Keto can lead to rapid weight loss initially.");
      System.out.println("It may also have short-term benefits for blood sugar control and lipid profiles.\n");

      System.out.println("**Disadvantages:**");
      System.out.println("Sustainability is challenging due to the restrictive nature of the diet.");
      System.out.println("The \"keto flu\" and potential long-term side effects like fatty liver and nutrient deficiencies are concerns.");
      System.out.println("It may not be suitable for certain medical conditions.\n");

      System.out.println("**What to Avoid:**");
      System.out.println("Stay hydrated to alleviate keto flu symptoms.");
      System.out.println("Avoid high-carb foods and sugary items.\n");
      break;

    case "Paleolithic Diet":
      System.out.println("\n**Paleolithic Diet (Paleo)**");
      System.out.println("\n**Description and How it Works:**");
      System.out.println("The paleo diet mimics the eating habits of ancient hunter-gatherers, emphasizing lean meats, fish, eggs, fruits, vegetables, nuts, and seeds");
      System.out.println("while excluding processed foods and grains. The focus on high protein and fiber helps control appetite.\n");

      System.out.println("**Benefits:**");
      System.out.println("Paleo can lead to short-term weight loss and potentially lower blood pressure, blood glucose, and cholesterol levels.\n");

      System.out.println("**Disadvantages:**");
      System.out.println("Long-term adherence can be challenging and expensive due to organic produce.");
      System.out.println("Deficiencies in vitamins and minerals are possible.\n");

      System.out.println("**What to Avoid:**");
      System.out.println("Exclude dairy, grains, refined sugars, and processed foods.");
      System.out.println("Opt for organic, grass-fed meats if possible.\n");
      break;

    case "Plant-based Diet":
      System.out.println("\n**Plant-Based Diet**");
      System.out.println("**Description and How it Works:**");
      System.out.println("Plant-based diets promote increased consumption of fruits, vegetables, legumes, nuts, and grains");
      System.out.println("while limiting or excluding animal products. High-fiber foods induce satiety, reducing calorie intake.\n");

      System.out.println("**Benefits:**");
      System.out.println("Plant-based diets may aid in weight loss and offer potential benefits for heart health and diabetes management.\n");

      System.out.println("**Disadvantages:**");
      System.out.println("Avoiding all animal products can lead to nutrient deficiencies, especially in protein, iron, zinc, calcium, vitamin D, and B12.\n");

      System.out.println("**What to Avoid:**");
      System.out.println("The foods to avoid depend on the specific type of plant-based diet chosen, whether vegetarian, vegan, or another variation.");
      System.out.println("Ensure proper nutrient intake through dietary planning or supplements if needed.\n");
      break;

    default:
      System.out.println("Invalid diet choice. Please try again.");
    }
  }

  private void displayChosenDietFoodsAndDrinks() {
    // Load user data
    UserData userData = dataFileManager.loadUserData();
    if (userData == null) {
      System.out.println("Failed to load user data. Please ensure you have saved your data first.");
      return;
    }

    // Load the user's chosen diet
    String chosenDiet = dataFileManager.loadChosenDiet();
    if (chosenDiet == null || chosenDiet.isEmpty()) {
      System.out.println("You haven't chosen a diet yet. Please select a diet first.");
      return;
    }

    // Display information about the chosen diet
    System.out.println("Chosen Diet: " + chosenDiet);

    // Generate and display the list of foods and drinks based on the chosen diet
    switch (chosenDiet) {
    case "Intermittent Fasting":
      // Display Intermittent Fasting foods and drinks
      displayIntermittentFastingFoodsAndDrinks();
      break;
    case "Mediterranean Diet":
      // Display Mediterranean Diet foods and drinks
      displayMediterraneanDietFoodsAndDrinks();
      break;
    case "DASH Diet":
      // Display DASH Diet foods and drinks
      displayDASHDietFoodsAndDrinks();
      break;
    case "Ketogenic Diet":
      // Display Ketogenic Diet foods and drinks
      displayKetogenicDietFoodsAndDrinks();
      break;
    case "Paleolithic Diet":
      // Display Paleolithic Diet foods and drinks
      displayPaleolithicDietFoodsAndDrinks();
      break;
    case "Plant-based diet":
      // Display Plant-based diet foods and drinks
      displayPlantBasedDietFoodsAndDrinks();
      break;
    default:
      System.out.println("Invalid diet choice.");
    }
  }

  // Implement methods to display foods and drinks for each diet
  private void displayIntermittentFastingFoodsAndDrinks() {
    System.out.println("\n=======================================");
    System.out.println("Foods and Drinks for Intermittent Fasting");
    System.out.println("=======================================\n");

    System.out.println("Foods for Intermittent Fasting:\n");
    System.out.println("1. Lean proteins (e.g., chicken, fish): These provide essential protein while minimizing calorie intake.");
    System.out.println("2. Whole grains (e.g., brown rice, quinoa): Rich in nutrients and fiber to keep you full.");
    System.out.println("3. High-fiber vegetables (e.g., broccoli, spinach): Low in calories and high in nutrients.");
    System.out.println("4. Fruits (in moderation): Choose fruits with lower sugar content.");
    System.out.println("5. Nuts and seeds (in moderation): Good sources of healthy fats and protein.");

    System.out.println("\nDrinks for Intermittent Fasting:\n");
    System.out.println("1. Water (stay hydrated): Drink plenty of water throughout the day.");
    System.out.println("2. Herbal tea (non-caloric): A soothing option with no calories.");
    System.out.println("3. Black coffee (in moderation): Provides a caffeine boost but avoid excessive consumption.\n");

  }

  private void displayMediterraneanDietFoodsAndDrinks() {
    System.out.println("\n=============================================");
    System.out.println("Foods and Drinks for Mediterranean Diet");
    System.out.println("=============================================\n");

    System.out.println("Foods for Mediterranean Diet:\n");
    System.out.println("1. Fruits (e.g., olives, figs): Rich in antioxidants and natural sweetness.");
    System.out.println("2. Vegetables (e.g., tomatoes, cucumbers): Abundant in vitamins and minerals.");
    System.out.println("3. Whole grains (e.g., whole wheat bread, pasta): High in fiber and energy.");
    System.out.println("4. Olive oil (as the primary fat source): A healthy source of monounsaturated fats.");
    System.out.println("5. Lean proteins (e.g., fish, poultry): Provides essential protein without excess fat.");

    System.out.println("\nDrinks for Mediterranean Diet:\n");
    System.out.println("1. Red wine (in moderation, if desired): A glass of red wine may be enjoyed occasionally.");
    System.out.println("2. Water: Stay well-hydrated with clean and refreshing water.\n");

  }

  private void displayDASHDietFoodsAndDrinks() {
    System.out.println("\n=============================================");
    System.out.println("Foods and Drinks for DASH Diet");
    System.out.println("=============================================\n");

    System.out.println("Foods for DASH Diet:\n");
    System.out.println("1. Fruits (e.g., berries, oranges): Rich in vitamins and antioxidants.");
    System.out.println("2. Vegetables (e.g., carrots, broccoli): High in fiber and essential nutrients.");
    System.out.println("3. Whole grains (e.g., oatmeal, whole wheat bread): Excellent source of energy.");
    System.out.println("4. Low-fat dairy (e.g., yogurt, milk): Provides calcium without excess fat.");
    System.out.println("5. Lean proteins (e.g., chicken, beans): Essential for muscle maintenance.");

    System.out.println("\nDrinks for DASH Diet:\n");
    System.out.println("1. Water: Hydrate with plain, refreshing water.");
    System.out.println("2. Low-fat milk (in moderation): A calcium-rich beverage for added nutrition.\n");

  }

  private void displayKetogenicDietFoodsAndDrinks() {
    System.out.println("\n=============================================");
    System.out.println("Foods and Drinks for Ketogenic Diet");
    System.out.println("=============================================\n");

    System.out.println("Foods for Ketogenic Diet:\n");
    System.out.println("1. Fatty meats (e.g., bacon, steak): High in fat and protein.");
    System.out.println("2. High-fat dairy (e.g., butter, cream): Provides essential fats.");
    System.out.println("3. Non-starchy vegetables (e.g., spinach, cauliflower): Low in carbs.");
    System.out.println("4. Avocado and nuts (in moderation): Healthy fats and fiber.");
    System.out.println("5. Oils (e.g., coconut oil, olive oil): Used for cooking and added fats.");

    System.out.println("\nDrinks for Ketogenic Diet:\n");
    System.out.println("1. Water: Stay well-hydrated throughout the day.");
    System.out.println("2. Black coffee (in moderation): Coffee without added sugars or cream.");
    System.out.println("3. Tea (unsweetened): Herbal or green tea can be a good choice.\n");

  }

  private void displayPaleolithicDietFoodsAndDrinks() {
    System.out.println("\n===============================================");
    System.out.println("Foods and Drinks for Paleolithic Diet");
    System.out.println("===============================================\n");

    System.out.println("Foods for Paleolithic Diet:\n");
    System.out.println("1. Lean meats (e.g., chicken, beef): Excellent source of protein.");
    System.out.println("2. Fish and seafood (e.g., salmon, shrimp): Rich in healthy fats.");
    System.out.println("3. Eggs: A versatile protein source.");
    System.out.println("4. Fruits (e.g., berries, apples): Natural sweetness and nutrients.");
    System.out.println("5. Vegetables (e.g., broccoli, kale): Packed with vitamins and minerals.");

    System.out.println("\nDrinks for Paleolithic Diet:\n");
    System.out.println("1. Water: The best choice for staying hydrated.");
    System.out.println("2. Herbal tea (non-caloric): A caffeine-free option for variety.\n");

  }

  private void displayPlantBasedDietFoodsAndDrinks() {
    System.out.println("\n===============================================");
    System.out.println("Foods and Drinks for Plant-Based Diet");
    System.out.println("===============================================\n");

    System.out.println("Foods for Plant-Based Diet:\n");
    System.out.println("1. Fruits (e.g., apples, bananas): A variety of natural sweetness.");
    System.out.println("2. Vegetables (e.g., broccoli, carrots): Rich in vitamins and fiber.");
    System.out.println("3. Legumes (e.g., beans, lentils): Excellent plant-based protein.");
    System.out.println("4. Whole grains (e.g., brown rice, quinoa): Satisfying and nutritious.");
    System.out.println("5. Nuts and seeds (in moderation): Healthy fats and protein sources.");

    System.out.println("\nDrinks for Plant-Based Diet:\n");
    System.out.println("1. Water: The best choice for staying hydrated.");
    System.out.println("2. Herbal tea (non-caloric): A caffeine-free option for variety.");
    System.out.println("3. Plant-based milk (e.g., almond milk): A dairy-free alternative.\n");

  }

}