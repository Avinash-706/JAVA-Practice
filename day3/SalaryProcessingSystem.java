import java.util.*;

public class SalaryProcessingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int id, age, dept;
        String name, role = null, access_Level = null, dept_name = null;
        double salary, fin_salary = 0;
    
        
        // Step 1 - Basic Details
        System.out.println("Enter Details : ");
        System.out.print("Enter ID : ");
        id = sc.nextInt();
        System.out.print("Enter Name : ");
        name = sc.next();
        System.out.print("Enter Age : ");
        age = sc.nextInt();

        // Step 2 - Age Verification
        if(age < 21){
                System.out.println("Employee Is Not Eligible to Work");
                return;
        }

        // Step 3 - Department Details
        System.out.print("Enter Basic Salary : ");
        salary = sc.nextDouble();
        System.out.print("Enter Department Choice : \n\t1 - IT \n\t2 - HR \n\t3 - Finance  \nEnter Choice : ");
        dept = sc.nextInt();


        // Step 4 - Role-Based Salary Allowance Details
        switch(dept){
            case 1 : //IT
                dept_name = "IT";
                System.out.print("Select The Role : \n\tDeveloper \n\tTester\nEnter Choice : ");
                role = sc.next();
                if(role.equals("Developer"))         fin_salary = (salary * 0.30) + salary;
                else if (role.equals("Tester"))      fin_salary = (salary * 0.25) + salary;
                else    break;
                break;
            case 2 : //HR
                dept_name = "HR";
                System.out.print("Select The Role : \n\tRecruiter \n\tPayroll\nEnter Choice : ");
                role = sc.next();
                if(role.equals("Recruiter"))         fin_salary = (salary * 0.20) + salary;
                else if (role.equals("Payroll"))     fin_salary = (salary * 0.22) + salary;
                else    break;
                break;
            case 3 : //Finance
                dept_name = "Finance";
                System.out.print("Select The Role : \n\tAccountant \n\tAuditor\nEnter Choice : ");
                role = sc.next();
                if(role.equals("Accountant"))        fin_salary = (salary * 0.28) + salary;
                else if (role.equals("Auditor"))     fin_salary = (salary * 0.26) + salary;
                else    break;
                break;
            default :
                System.out.println("Invalid Choice !!");
                return;
        }

        // Step 5 - Access Level 
        if(fin_salary >= 60000 && dept == 1)        access_Level = "Admin";
        else if(fin_salary >= 60000 && dept != 1)   access_Level = "Manager";
        else                                        access_Level = "Employee";


        // Final Step - All Details
        System.out.println("\n--- Salary Processing System ---");
        System.out.println("Employee Details:");
        System.out.println("\tEmployee ID           : " + id);
        System.out.println("\tEmployee Name         : " + name);
        System.out.println("\tEmployee Age          : " + age);
        System.out.println("\tEmployee Department   : " +  dept_name);
        System.out.println("\tEmployee Role         : " + role);
        System.out.println("\tBasic  Salary         : " + salary);
        System.out.println("\tFinal  Salary         : " + fin_salary);
        System.out.println("\tAccess Level          : " + access_Level);    
    }
}