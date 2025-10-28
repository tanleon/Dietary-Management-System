public class UserData {
  private String name;
  private int age;
  private double weight;
  private double height;
  private double targetWeight;
  private String healthGoals;
  private String diet;

  // Constructor
  public UserData(String name, int age, double weight, double height, double targetWeight, String healthGoals, String diet) {
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.height = height;
    this.targetWeight = targetWeight;
    this.healthGoals = healthGoals;
    this.diet = diet;
  }

  // Getter and Setter methods for user data fields

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getTargetWeight() {
    return targetWeight;
  }

  public void setTargetWeight(double targetWeight) {
    this.targetWeight = targetWeight;
  }

  public String getHealthGoals() {
    return healthGoals;
  }

  public void setHealthGoals(String healthGoals) {
    this.healthGoals = healthGoals;
  }

  public String getDiet() {
    return diet;
  }

  public void setDiet(String diet) {
    this.diet = diet;
  }

  @Override
  public String toString() {
    // Use "|" as the delimiter to separate the fields
    return name + "|" + age + "|" + weight + "|" + height + "|" + targetWeight + "|" + healthGoals + "|" + diet;
  }
}