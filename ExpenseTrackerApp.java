import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp extends JFrame {
    private static final String FILE_NAME = "expenses.txt";
    private List<Expense> expenses;
    private JTextField amountField, subExpenseAmountField, subExpenseDescriptionField;
    private JComboBox<String> categoryComboBox;
    private JLabel totalExpenseLabel;
    private JList<String> expenseList;
    private DefaultListModel<String> expenseListModel;
    private int selectedExpenseIndex = -1;
    private int selectedSubExpenseIndex = -1;

    public ExpenseTrackerApp() {
        expenses = loadExpenses();
        createUI();
    }

    // Loading and saving expenses logic
    @SuppressWarnings("unchecked")
    private List<Expense> loadExpenses() {
        List<Expense> loadedExpenses = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            loadedExpenses = (List<Expense>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, first run
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedExpenses;
    }

    private void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUI() {
        setTitle("Expense Tracker");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.decode("#f5f5f5"));

        // Total Expense Panel
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout());
        totalPanel.setBackground(Color.decode("#f5f5f5"));
        totalExpenseLabel = new JLabel("Total Expense: ₹0.00");
        totalExpenseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalExpenseLabel);
        add(totalPanel, BorderLayout.NORTH);

        // Expense List
        expenseListModel = new DefaultListModel<>();
        expenseList = new JList<>(expenseListModel);
        expenseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseList.setBackground(Color.decode("#ffffff"));
        expenseList.setFont(new Font("Arial", Font.PLAIN, 14));
        expenseList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onExpenseSelected();
            }
        });

        JScrollPane listScrollPane = new JScrollPane(expenseList);
        listScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(listScrollPane, BorderLayout.CENTER);

        // User Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.decode("#f5f5f5"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>(new String[]{
                "Travelling", "Groceries", "Miscellaneous", "Health", "Entertainment", "Bills", "Food",
                "Transport", "Education", "Rent", "Utilities"
        });

        JLabel subExpenseDescriptionLabel = new JLabel("Sub-Exp:");
        subExpenseDescriptionField = new JTextField(11);
        subExpenseAmountField = new JTextField(10);

        // Buttons for Add, Edit, Delete Expense
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBackground(Color.decode("#f5f5f5"));

        JButton addButton = new JButton("Add Expense");
        addButton.setBackground(Color.decode("#4CAF50"));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addExpense());

        JButton editButton = new JButton("Edit Expense");
        editButton.setBackground(Color.decode("#2196F3"));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> editExpense());

        JButton deleteButton = new JButton("Delete Expense");
        deleteButton.setBackground(Color.decode("#F44336"));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> deleteExpense());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Sub-Expense Button Panel
        JPanel subExpenseButtonPanel = new JPanel();
        subExpenseButtonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        subExpenseButtonPanel.setBackground(Color.decode("#f5f5f5"));

        JButton addSubExpenseButton = new JButton("Add Sub-Expense");
        addSubExpenseButton.setBackground(Color.decode("#8BC34A"));
        addSubExpenseButton.setForeground(Color.WHITE);
        addSubExpenseButton.setFocusPainted(false);
        addSubExpenseButton.addActionListener(e -> addSubExpense());

        JButton editSubExpenseButton = new JButton("Edit Sub-Expense");
        editSubExpenseButton.setBackground(Color.decode("#03A9F4"));
        editSubExpenseButton.setForeground(Color.WHITE);
        editSubExpenseButton.setFocusPainted(false);
        editSubExpenseButton.addActionListener(e -> editSubExpense());

        JButton deleteSubExpenseButton = new JButton("Delete Sub-Expense");
        deleteSubExpenseButton.setBackground(Color.decode("#FF5722"));
        deleteSubExpenseButton.setForeground(Color.WHITE);
        deleteSubExpenseButton.setFocusPainted(false);
        deleteSubExpenseButton.addActionListener(e -> deleteSubExpense());

        subExpenseButtonPanel.add(addSubExpenseButton);
        subExpenseButtonPanel.add(editSubExpenseButton);
        subExpenseButtonPanel.add(deleteSubExpenseButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(amountField, gbc);
        gbc.gridx = 2;
        inputPanel.add(categoryLabel, gbc);
        gbc.gridx = 3;
        inputPanel.add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        inputPanel.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(subExpenseDescriptionLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(subExpenseDescriptionField, gbc);
        gbc.gridx = 3;
        inputPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 4;
        inputPanel.add(subExpenseAmountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        inputPanel.add(subExpenseButtonPanel, gbc);

        add(inputPanel, BorderLayout.SOUTH);
        updateExpenseArea();
    }

    private void addExpense() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = (String) categoryComboBox.getSelectedItem();
            expenses.add(new Expense(category, amount));
            saveExpenses();
            updateExpenseArea();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editExpense() {
        if (selectedExpenseIndex < 0 || selectedExpenseIndex >= expenses.size()) {
            JOptionPane.showMessageDialog(this, "Select a valid expense.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = (String) categoryComboBox.getSelectedItem();
            Expense expense = expenses.get(selectedExpenseIndex);
            expense.setCategory(category);
            expense.setAmount(amount);
            saveExpenses();
            updateExpenseArea();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteExpense() {
        if (selectedExpenseIndex < 0 || selectedExpenseIndex >= expenses.size()) {
            JOptionPane.showMessageDialog(this, "Select a valid expense.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        expenses.remove(selectedExpenseIndex);
        selectedExpenseIndex = -1;
        saveExpenses();
        updateExpenseArea();
    }

    private void addSubExpense() {
        if (selectedExpenseIndex < 0 || selectedExpenseIndex >= expenses.size()) {
            JOptionPane.showMessageDialog(this, "Select a valid expense.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double amount = Double.parseDouble(subExpenseAmountField.getText());
            String description = subExpenseDescriptionField.getText();
            Expense selectedExpense = expenses.get(selectedExpenseIndex);
            double totalSubExpenses = selectedExpense.getSubExpenses().stream().mapToDouble(SubExpense::getAmount).sum();

            if (totalSubExpenses + amount > selectedExpense.getAmount()) {
                JOptionPane.showMessageDialog(this, "Sub-expenses total exceeds the main expense amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            selectedExpense.addSubExpense(new SubExpense(description, amount));
            saveExpenses();
            updateExpenseArea();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editSubExpense() {
        if (selectedExpenseIndex < 0 || selectedExpenseIndex >= expenses.size() || selectedSubExpenseIndex < 0) {
            JOptionPane.showMessageDialog(this, "Select a valid sub-expense.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double amount = Double.parseDouble(subExpenseAmountField.getText());
            String description = subExpenseDescriptionField.getText();
            Expense selectedExpense = expenses.get(selectedExpenseIndex);
            SubExpense subExpense = selectedExpense.getSubExpenses().get(selectedSubExpenseIndex);
            double totalSubExpenses = selectedExpense.getSubExpenses().stream()
                    .filter(se -> se != subExpense)
                    .mapToDouble(SubExpense::getAmount)
                    .sum();

            if (totalSubExpenses + amount > selectedExpense.getAmount()) {
                JOptionPane.showMessageDialog(this, "Sub-expenses total exceeds the main expense amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            subExpense.setDescription(description);
            subExpense.setAmount(amount);
            saveExpenses();
            updateExpenseArea();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSubExpense() {
        if (selectedExpenseIndex < 0 || selectedExpenseIndex >= expenses.size() || selectedSubExpenseIndex < 0) {
            JOptionPane.showMessageDialog(this, "Select a valid sub-expense.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        expenses.get(selectedExpenseIndex).getSubExpenses().remove(selectedSubExpenseIndex);
        selectedSubExpenseIndex = -1;
        saveExpenses();
        updateExpenseArea();
    }

    private void updateExpenseArea() {
        expenseListModel.clear();
        double total = 0;
        for (Expense expense : expenses) {
            expenseListModel.addElement(expense.getCategory() + " - ₹" + expense.getAmount());
            for (SubExpense subExpense : expense.getSubExpenses()) {
                expenseListModel.addElement("    " + subExpense.getDescription() + ": ₹" + subExpense.getAmount());
            }
            total += expense.getAmount();
        }
        totalExpenseLabel.setText("Total Expense: ₹" + total);
    }

    private void onExpenseSelected() {
        int selectedIndex = expenseList.getSelectedIndex();
        if (selectedIndex < 0) return;

        int expenseIndex = 0;
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            if (selectedIndex == expenseIndex) {
                selectedExpenseIndex = i;
                selectedSubExpenseIndex = -1;
                amountField.setText(String.valueOf(expense.getAmount()));
                categoryComboBox.setSelectedItem(expense.getCategory());
                return;
            }
            expenseIndex++;
            for (int j = 0; j < expense.getSubExpenses().size(); j++) {
                if (selectedIndex == expenseIndex) {
                    selectedExpenseIndex = i;
                    selectedSubExpenseIndex = j;
                    SubExpense subExpense = expense.getSubExpenses().get(j);
                    subExpenseDescriptionField.setText(subExpense.getDescription());
                    subExpenseAmountField.setText(String.valueOf(subExpense.getAmount()));
                    return;
                }
                expenseIndex++;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerApp().setVisible(true));
    }
}
