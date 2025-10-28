public class ShoppingItem {
  private Ingredient ingredient; // The ingredient associated with this shopping item
  private int quantity; // The quantity of the ingredient to purchase

  // Constructor to create a ShoppingItem with an ingredient and quantity
  public ShoppingItem(Ingredient ingredient, int quantity) {
    this.ingredient = ingredient;
    this.quantity = quantity;
  }

  // Getter for retrieving the ingredient of this shopping item
  public Ingredient getIngredient() {
    return ingredient;
  }

  // Setter for updating the ingredient of this shopping item
  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  // Getter for retrieving the quantity of the ingredient to purchase
  public int getQuantity() {
    return quantity;
  }

  // Setter for updating the quantity of the ingredient to purchase
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  // Customized string representation of a ShoppingItem
  @Override
  public String toString() {
    return "ShoppingItem: " + ingredient.getName() + " (" + quantity + ")";
  }
}
