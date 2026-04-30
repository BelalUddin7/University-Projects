import java.util.*;

class Transaction {
    private String type;
    private double amount;
    private Date date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Amount: %.2f, Date: %s", type, amount, date);
    }
}

class CircularBuffer {
    private final Transaction[] buffer;
    private int start = 0;
    private int count = 0;

    public CircularBuffer(int size) {
        buffer = new Transaction[size];
    }

    public void add(Transaction transaction) {
        buffer[(start + count) % buffer.length] = transaction;
        if (count < buffer.length) {
            count++;
        } else {
            start = (start + 1) % buffer.length;
        }
    }

    public List<Transaction> toList() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            transactions.add(buffer[(start + i) % buffer.length]);
        }
        return transactions;
    }

    public void sortTransactionsByAmount() {
        List<Transaction> transactions = toList();
        for (int i = 1; i < transactions.size(); i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;

            while (j >= 0 && transactions.get(j).getAmount() < key.getAmount()) {
                transactions.set(j + 1, transactions.get(j));
                j--;
            }
            transactions.set(j + 1, key);
        }

        // Update buffer with sorted transactions
        for (int i = 0; i < transactions.size(); i++) {
            buffer[(start + i) % buffer.length] = transactions.get(i);
        }
    }
}

class BankAccount {
    private String accountNumber;
    private String holderName;
    private String address;
    private Date openingDate;
    private double balance;
    private CircularBuffer transactions;

    public BankAccount(String accountNumber, String holderName, String address, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.address = address;
        this.openingDate = new Date();
        this.balance = initialBalance;
        this.transactions = new CircularBuffer(4);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(String type, double amount) {
        if (type.equalsIgnoreCase("withdrawal") && amount > balance) {
            System.out.println("Insufficient balance! Transaction failed.");
            return;
        }

        if (type.equalsIgnoreCase("withdrawal")) {
            balance -= amount;
        } else if (type.equalsIgnoreCase("deposit")) {
            balance += amount;
        }

        transactions.add(new Transaction(type, amount));
    }

    public List<Transaction> getTransactionsSortedByAmount() {
        transactions.sortTransactionsByAmount();
        return transactions.toList();
    }

    @Override
    public String toString() {
        return String.format("Account Number: %s, Name: %s, Address: %s, Opening Date: %s, Balance: %.2f",
                accountNumber, holderName, address, openingDate, balance);
    }
}

class BankManagementSystem {
    private List<BankAccount> accounts;

    public BankManagementSystem() {
        accounts = new ArrayList<>();
    }

    public void createAccount(String accountNumber, String holderName, String address, double initialBalance) {
        if (!accountNumber.matches("\\d{8}")) {
            System.out.println("Error: Account number must be exactly 8 digits!");
            return;
        }
        if (holderName.trim().isEmpty()) {
            System.out.println("Error: Account holder name cannot be empty!");
            return;
        }
        if (findAccountByBinarySearch(accountNumber) != null) {
            System.out.println("Error: Account with this number already exists!");
            return;
        }

        accounts.add(new BankAccount(accountNumber, holderName, address, initialBalance));
        accounts.sort(Comparator.comparing(BankAccount::getAccountNumber));
        System.out.println("Account created successfully!");
    }

    public void deleteAccount(String accountNumber) {
        BankAccount account = findAccountByBinarySearch(accountNumber);
        if (account != null) {
            accounts.remove(account);
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Error: Account not found!");
        }
    }

    public BankAccount findAccountByBinarySearch(String accountNumber) {
        int low = 0, high = accounts.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            BankAccount account = accounts.get(mid);
            int comparison = account.getAccountNumber().compareTo(accountNumber);

            if (comparison == 0) {
                return account;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("\n--- List of Accounts ---");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    public void displayPerformanceMetrics() {
        System.out.println("\n--- Performance Metrics ---");
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("System ready to handle up to 10,000 accounts.");
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BankManagementSystem system = new BankManagementSystem();

            while (true) {
                System.out.println("\n--- Bank Management System ---");
                System.out.println("1. Create Account");
                System.out.println("2. Display Accounts");
                System.out.println("3. Delete Account");
                System.out.println("4. Display Performance Metrics");
                System.out.println("5. Exit");
                System.out.print("Choose an option (1-5): ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Number (8 digits): ");
                        String accountNumber = scanner.nextLine();
                        System.out.print("Enter Holder Name: ");
                        String holderName = scanner.nextLine();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double initialBalance = scanner.nextDouble();
                        system.createAccount(accountNumber, holderName, address, initialBalance);
                        break;

                    case 2:
                        system.displayAccounts();
                        break;

                    case 3:
                        System.out.print("Enter Account Number to Delete: ");
                        String accNumberToDelete = scanner.nextLine();
                        system.deleteAccount(accNumberToDelete);
                        break;

                    case 4:
                        system.displayPerformanceMetrics();
                        break;

                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }
}
