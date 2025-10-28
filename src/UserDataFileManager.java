import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class UserDataFileManager {
  private String filePath; // The path to the user data file

  private FileWriter userDataWriter; // FileWriter for user data
  private FileReader chosenDietReader; // FileReader for chosen diet

  // Constructor to initialize the UserDataFileManager with a file path
  public UserDataFileManager(String filePath) {
    this.filePath = filePath;
  }

  // Save user data to the file
  public void saveUserData(UserData userData) {
    try {
      userDataWriter = new FileWriter(filePath);
      // Convert the UserData object to a string and save it to the file
      String userDataString = userDataToString(userData);
      userDataWriter.write(userDataString);
    } catch (IOException e) {
      System.out.println("Failed to save user data: " + e.getMessage());
    } finally {
      close();
    }
  }

  // Load user data from the file
  public UserData loadUserData() {
    UserData userData = null;

    try (FileReader reader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(reader)) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] userDataFields = line.split("\\|");
        if (userDataFields.length == 7) {
          // Parse the fields and populate the UserData object
          String name = userDataFields[0];
          int age = Integer.parseInt(userDataFields[1]);
          double weight = Double.parseDouble(userDataFields[2]);
          double height = Double.parseDouble(userDataFields[3]);
          double targetWeight = Double.parseDouble(userDataFields[4]);
          String healthGoals = userDataFields[5];
          String diet = userDataFields[6];

          userData = new UserData(name, age, weight, height, targetWeight, healthGoals, diet);
          break; // Exit the loop after the first valid entry is found
        }
      }
    } catch (IOException e) {
      System.out.println("Failed to load user data: " + e.getMessage());
    }

    return userData;
  }

  // Convert UserData object to a formatted string for saving
  private String userDataToString(UserData userData) {
    return userData.getName() + "|" + userData.getAge() + "|" + userData.getWeight() + "|" +
      userData.getHeight() + "|" + userData.getTargetWeight() + "|" + userData.getHealthGoals() + "|" +
      userData.getDiet();
  }

  // Save the chosen diet to a separate file
  public void saveChosenDiet(String chosenDiet) {
    try {
      userDataWriter = new FileWriter(filePath + "_diet");
      userDataWriter.write(chosenDiet);
    } catch (IOException e) {
      System.out.println("Failed to save chosen diet: " + e.getMessage());
    } finally {
      close();
    }
  }

  // Load the chosen diet from a separate file
  public String loadChosenDiet() {
    try {
      chosenDietReader = new FileReader(filePath + "_diet");
      BufferedReader bufferedReader = new BufferedReader(chosenDietReader);
      return bufferedReader.readLine();
    } catch (IOException e) {
      System.out.println("Failed to load chosen diet: " + e.getMessage());
    } finally {
      close();
    }
    return null;
  }

  // Close file resources
  public void close() {
    try {
      if (userDataWriter != null) {
        userDataWriter.close();
      }
      if (chosenDietReader != null) {
        chosenDietReader.close();
      }
    } catch (IOException e) {
      System.out.println("Error while closing file: " + e.getMessage());
    }
  }
}
