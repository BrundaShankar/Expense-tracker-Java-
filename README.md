# 💰 Expense Tracker – Java

A simple and interactive desktop-based **Expense Tracker application** developed using Java Swing. The application helps users record, organize, update, and monitor their expenses through a graphical user interface.

It also supports sub-expense management, automatic expense calculations, and local data persistence, making it easier to keep track of everyday spending.

## ✨ Features

- **Add Expenses:** Record expenses by entering an amount and selecting a category.
- **Edit Expenses:** Modify the amount or category of an existing expense.
- **Delete Expenses:** Remove expenses that are no longer required.
- **Sub-Expense Management:** Add, edit, and delete smaller expenses under a main expense.
- **Expense Validation:** Prevents sub-expenses from exceeding the main expense amount.
- **Automatic Total Calculation:** Displays the total expense amount.
- **Category-Based Tracking:** Organize expenses into categories such as:
  - Food
  - Groceries
  - Travelling
  - Transport
  - Health
  - Entertainment
  - Bills
  - Education
  - Rent
  - Utilities
  - Miscellaneous
- **Local Data Persistence:** Saves expense information locally so that records can be loaded when the application is reopened.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core application development |
| Java Swing | Graphical User Interface (GUI) |
| AWT | GUI components and layouts |
| Java Collections | Managing expense records |
| File Handling | Reading and writing local data |
| Object Serialization | Saving and loading expense objects |

## 🖥️ Application Overview

The application provides a graphical interface with:

1. A total expense display.
2. An expense list.
3. Input fields for expense amount and category.
4. Buttons to add, edit, and delete expenses.
5. Input fields and controls for managing sub-expenses.

## 📂 Project Structure

```text
Expense-tracker-Java/
│
├── ExpenseTrackerApp.java
├── Expense.java
├── SubExpense.java
├── expenses.txt
│
└── README.md
```

**File descriptions:**

- `ExpenseTrackerApp.java` – Main application containing the Swing interface and expense management operations.
- `Expense.java` – Represents an expense, including its category, amount, and associated sub-expenses.
- `SubExpense.java` – Represents individual sub-expense records.
- `expenses.txt` – Local data file used for storing serialized expense information.

## ⚙️ How to Run the Project

### Prerequisites

- Java Development Kit (JDK) installed.
- Any Java IDE such as IntelliJ IDEA, Eclipse, or VS Code, or a terminal.

### Step 1: Clone the Repository

```bash
git clone https://github.com/BrundaShankar/Expense-tracker-Java-.git
```

### Step 2: Navigate to the Project Folder

```bash
cd Expense-tracker-Java-
```

### Step 3: Compile the Java Files

```bash
javac Expense.java SubExpense.java ExpenseTrackerApp.java
```

### Step 4: Run the Application

```bash
java ExpenseTrackerApp
```

The Expense Tracker desktop application will launch.

## 🔄 How It Works

1. Launch the application.
2. Enter an expense amount and select a category.
3. Click **Add Expense** to create a new record.
4. Select an existing expense to edit or delete it.
5. Add sub-expenses by entering a description and amount.
6. View the updated expense list and total.
7. Expense records are saved locally and loaded when the application starts again.

## 🧠 Concepts Demonstrated

This project demonstrates practical implementation of:

- Object-Oriented Programming (OOP)
- Classes and Objects
- Encapsulation
- Inheritance and object relationships
- Java Swing event handling
- ArrayList and Java Collections
- Exception Handling
- File Handling
- Object Serialization
- GUI Layout Management

## 🚀 Future Enhancements

- Add monthly and yearly expense reports.
- Introduce graphical charts for spending analysis.
- Add search and category-based filtering.
- Implement budget limits and spending alerts.
- Export expense reports to CSV or PDF.
- Add database integration for more scalable storage.

## 🎯 Project Objective

The objective of this project is to develop a simple and user-friendly expense management application while applying Java programming concepts, object-oriented design, GUI development, and file handling.

## 👩‍💻 Author

**Brunda Shankar**

GitHub: [BrundaShankar](https://github.com/BrundaShankar)

---

⭐ If you find this project useful, feel free to explore the repository and share your feedback!
