import java.util.Scanner;

public class LoanPayableAmountCalculation {

    //functions
    public static double loan(double principal){
        return  principal * 0.08;
    }

    public static double loan(double principal, int years){
        double rate = 10; // 10% annual rate
        double amount = principal * Math.pow(1 + rate / 100, years);
        return amount;
    }
    
    public static double loan(double principal, int years, String loanType){
        if(loanType.equals("HOME"))             return principal * Math.pow(1 + 7.0 / 100, years);
        else if(loanType.equals("AUTO"))        return principal * Math.pow(1 + 9.0 / 100, years);
        else if(loanType.equals("PERSONAL"))    return principal * Math.pow(1 + 12.0 / 100, years);
        else                                             return 0;
    }

    public static void printOutput(double amount, double rate) {
        System.out.println("Default Interest : " + rate);
        System.out.print("Amount : " + Math.round(amount));
    }
    
    public static void printOutput(double amount, double rate, String loanType) {
        System.out.println(loanType + " Loan Compound Rate : " + rate);
        System.out.print("Amount : " + Math.round(amount));
    }


    //main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double principal, amount = 0, rate = 0; 
        int years = 1;
        String loanType = null;      

        System.out.print("Enter Principal Amount : ");
        principal = sc.nextDouble();
        
        System.out.print("Do you want to enter Years ? (true/false)");
        boolean check_Years = sc.nextBoolean();
        if(check_Years){
            System.out.print("Enter Years : ");
            years = sc.nextInt();
        }

        System.out.print("Do You Know Your Loan Type ? (true/false)");
        boolean check_Type = sc.nextBoolean();
        if(check_Type){
            System.out.print("Enter Loan Type : (HOME / AUTO / PERSONAL) ");
            loanType = sc.next();
        }

        // Function Overloading Check
        if      ((check_Years ==  false)&& (check_Type == false)){
            rate = 8;
            amount = loan(principal);
            printOutput(amount, rate);
            return ;
        }
        else if ((check_Years == true)  && (check_Type == false)){
            rate = 10;
            amount = loan(principal, years);
            printOutput(amount, rate);
            return ;
        }
        else if ((check_Type == true)){
            amount = loan(principal, years, loanType);
            if(loanType.equals("HOME"))             rate = 7;
            else if(loanType.equals("AUTO"))        rate = 9;
            else if(loanType.equals("PERSONAL"))    rate = 12;
            printOutput(amount, rate, loanType);
            return ;
        }

        sc.close();
    }   
}