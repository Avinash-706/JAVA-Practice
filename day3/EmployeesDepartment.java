import java.util.*;

public class EmployeesDepartment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Total Departments : " + "\n1.\tIT \n2.\tHR \n3.\tFinance \n4.\tOperations ");
        System.out.print("Enter the Department Id : ");
        int dept = sc.nextInt(); 
        
        switch (dept) {
            case 1:
                System.out.println("Department  :  IT "+ "\n\tDeveloper\n\tTester\n\tDevOps Engineer");
                break;
            case 2:
                System.out.println("Department  :  HR" + "\n\tRecruiter\n\tHR executive\n\tPayroll Officer");
                break;
            case 3:
                System.out.println("Department  :  Finance"+ dept + "\n\tAccountant\n\tFinancial Analyst\n\tAuditor");
                break;
            case 4:
                System.out.println("Department  :  Operations"+ dept + "\n\tOperation Executive\n\tTeam Lead\n\tManager");
                break;
            default:
                System.out.println("Invalid Department!!");
                break;
        }

    }
}
