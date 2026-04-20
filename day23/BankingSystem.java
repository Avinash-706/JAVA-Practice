import java.util.ArrayList;
import java.util.Iterator;

interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}

abstract class Account implements BankOperations {
    private final int accountNo;
    private final String holderName;
    protected double balance;

    public Account(int accountNo, String holderName, double balance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public abstract String getAccountType();

    @Override
    public void checkBalance() {
        System.out.println("Balance: " + balance);
    }

    @Override
    public String toString() {
        return "AccountNo=" + accountNo +", Name=" + holderName + 
        ", Type=" + getAccountType() + ", Balance=" + balance;
    }
}

class SavingsAccount extends Account {

    public SavingsAccount(int accountNo, String name, double balance) {
        super(accountNo, name, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance < amount) {
            System.out.println("Insufficient Balance");
            return;
        }
        balance -= amount;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}

class CurrentAccount extends Account {

    public CurrentAccount(int accountNo, String name, double balance) {
        super(accountNo, name, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}

public class BankingSystem {

    private ArrayList<Account> accounts = new ArrayList<>();

    // Add account
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Remove account
    public void removeAccount(int accNo) {
        Iterator<Account> it = accounts.iterator();
        while (it.hasNext()) {
            if (it.next().getAccountNo() == accNo) {
                it.remove();
                System.out.println("Account removed");
                return;
            }
        }
        System.out.println("Account not found");
    }

    // Search account
    public Account searchAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNo() == accNo)
                return acc;
        }
        return null;
    }

    // Display all accounts
    public void displayAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    public static void main(String[] args) {

        BankingSystem bank = new BankingSystem();

        Account a1 = new SavingsAccount(1, "Kshitij", 1000);
        Account a2 = new CurrentAccount(2, "John", 2000);

        bank.addAccount(a1);
        bank.addAccount(a2);

        bank.displayAccounts();

        Account acc = bank.searchAccount(1);
        if (acc != null) {
            acc.deposit(500);
            acc.withdraw(300);
            acc.checkBalance();
        }

        bank.removeAccount(2);
        bank.displayAccounts();
    }
}
