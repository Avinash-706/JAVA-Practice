
public class Banking{
    public static void main(String[] args) {
        BankAccount b1 = new BankAccount(101, "John", 5000);
        BankService service = new BankService();

        try {
            service.withdraw(b1, 101, 7000);
        } catch (InsufficientbalanceException | InvalidAccountException e){
            System.out.println("Transaction Failed!");
            System.out.println(e.getMessage());
        }
    }
}

class BankAccount {
    int accNO;
    String name;
    double bal;

    BankAccount(int accNO, String name, double bal) {
        this.accNO = accNO;
        this.name = name;
        this.bal = bal;
    }
    
}

class BankService  {
    public void withdraw(BankAccount account, int accNO, double amt)
        throws InvalidAccountException, InsufficientbalanceException
    {
        if(accNO != 101){
            throw new InvalidAccountException();
        }
        if(amt > account.bal){
            throw new InsufficientbalanceException(account.bal);
        }

        account.bal -= amt;
        System.out.println("Transation Successfull.");
    }
}

class InvalidAccountException extends Exception {
    public InvalidAccountException() {
        super("Invalid account number");
    }
}

class InsufficientbalanceException extends Exception {
    public InsufficientbalanceException(double bal) {
        super("Reason: Insufficient balance. Available balance: "+ bal);
    }
}
