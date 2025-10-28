# Dietary Management System

## 1. Introduction

### 1.1 Purpose
The **Dietary Management System** helps individuals manage their diets more efficiently by offering access to popular, science-backed diets aimed at improving health and supporting weight loss. It empowers users to make informed dietary decisions aligned with their goals.

### 1.2 Scope
The system is designed for end users who aim to lose weight, improve eating habits, or explore diet plans. It operates as a **single-user, offline system** without registration or login.

---

## 2. System Features and Functionalities

### 2.1 Profile Building
**Purpose:** Personalize the dietary experience.  
**Tasks:**
- Edit user details (e.g., name, weight, target weight).  
- Display profile above the main menu.

### 2.2 Diet Selection & Personalization
**Purpose:** Allow users to choose from multiple diet plans based on preferences and goals.  
**Tasks:**
- Choose and view descriptions for six diets.  
- Select a diet plan during profile editing.  
- Display recommended foods and drinks for the selected diet.

### 2.3 Grocery List
**Purpose:** Simplify grocery management for diet ingredients.  
**Tasks:**
- Add or remove ingredients with specified quantities.  
- Clear or view the grocery list.  
- Display ingredient information.

### 2.4 Meal Instructions
**Purpose:** Provide meal preparation guidance.  
**Tasks:**
- Display meals and their details.  
- Show preparation instructions above the user profile.

### 2.5 Meal Plan
**Purpose:** Customize a weekly meal schedule.  
**Tasks:**
- Display a weekly meal plan.  
- Allow editing of meals by day and meal period (e.g., Monday dinner).

### 2.6 Meal Categorization
**Purpose:** Categorize meals based on nutritional content (e.g., high fat, high protein).  
**Tasks:**
- Categorize and list meals by macronutrient content.  
- Display categorized lists to inform dietary choices.

---

## 3. System Constraints
- Single-user, offline system  
- No registration or login functionality  
- Operates locally without internet dependency  
- Focused on meal and diet management (not medical advice)

---

## 4. Conclusion
The **Dietary Management System** provides an integrated approach to dietary management by combining diet selection, grocery tracking, meal planning, and nutritional analysis. Its simple interface and offline design make it practical and accessible for users seeking structured dietary guidance.

---

## 5. References & Terminology

### Reference Source
The diet information used in this system is adapted from the article:  
**["Weight Loss Diets: Which Are Most Effective?"](https://ro.co/health-guide/weight-loss-diets-which-are-most-effective/)**

### Supported Diet Types
1. **Mediterranean Diet** – Emphasizes fruits, vegetables, nuts, and olive oil; promotes heart health.  
2. **DASH Diet** – Focuses on reducing sodium and improving heart health through whole foods.  
3. **Plant-Based Diet** – Encourages plant consumption; supports disease prevention.  
4. **Intermittent Fasting** – Alternates eating and fasting periods to manage calorie intake.  
5. **Ketogenic (Keto) Diet** – Low-carb, high-fat plan promoting ketosis for fat burning.  
6. **Paleo Diet** – Mimics ancestral eating with lean meats, vegetables, and natural foods.

### Terminology
- **Ingredient:** Basic component of a meal (e.g., chicken).  
- **Meal:** Dish made from one or more ingredients.  
- **Shopping Item:** Ingredient with a specified quantity (e.g., 3 apples).

---

## 6. Sample Output Overview

### Profile & Main Menu
- Displays user profile upon startup.
- Prompts user to select system features.

### Core Functionalities
- **Edit Personal Details & Choose Diet** – Modify user profile and select preferred diet.  
- **View Diet Descriptions** – Display information on six available diets.  
- **Recommended Foods & Drinks** – Show items based on the chosen diet.  
- **Grocery List** – Add, remove, or clear ingredients; view preloaded ingredient data.  
- **Meal Instructions** – Select a meal to view preparation instructions.  
- **Meal Weekly Plan** – Build and view custom weekly meal schedules.  
- **Meal Nutrient Categories** – View high/low macronutrient meal lists for better planning.

---

## Example Meal Plan Display
```

6. SATURDAY
   |Breakfast| Empty
   |Lunch| Empty
   |Dinner| Empty

7. SUNDAY
   |Breakfast| bananas with a cup of milk
   |Lunch| Empty
   |Dinner| Empty

Enter the day (1 for MONDAY...7 for SUNDAY) or '=' to exit to the main menu:

