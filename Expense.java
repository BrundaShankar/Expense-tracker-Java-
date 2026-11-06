import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Expense implements Serializable {
    private String category;
    private double amount;
    private List<SubExpense> subExpenses;

    // Constructor
    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.subExpenses = new ArrayList<>();
    }

    // Getter and Setter Methods
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<SubExpense> getSubExpenses() {
        return subExpenses;
    }

    public void addSubExpense(SubExpense subExpense) {
        subExpenses.add(subExpense);
    }

    // Method to get the total amount of the expense (including sub-expenses)
    public double getTotalAmount() {
        double total = amount;
        for (SubExpense subExpense : subExpenses) {
            total += subExpense.getAmount();
        }
        return total;
    }

    // Override toString() method to display expense with sub-expenses properly indented
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(category).append(" - ₹").append(amount);

        // If there are sub-expenses, add them with indentation
        if (!subExpenses.isEmpty()) {
            sb.append("\n  Sub-Expenses:"); // Sub-expenses heading
            for (SubExpense subExpense : subExpenses) {
                sb.append("\n    ").append(subExpense.getDescription()) // Indented description
                  .append(" - ₹").append(subExpense.getAmount()); // Indented amount
            }
        }
        return sb.toString();
    }
}