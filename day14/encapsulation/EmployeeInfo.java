public class EmployeeInfo {

    private int employee_id;
    private String employee_name;
    private double employee_salary;

    public EmployeeInfo() {
        System.out.println("-- Welcome to Employee Management System --");
    }

    // SETTER FOR ID
    public void setId(int employee_id) {
        this.employee_id = employee_id;
    }

    // GETTER FOR ID
    public int getId() {
        return employee_id;
    }

    // SETTER FOR NAME
    public void setName(String employee_name) {
        this.employee_name = employee_name;
    }

    // GETTER FOR NAME
    public String getName() {
        return employee_name;
    }

    // GETTER FOR SALARY (FORMATTED STRING)
    public String getSalary() {
        return String.format("%.2f", employee_salary);
    }

    // SETTER FOR SALARY
    public void setSalary(double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public static void main(String[] args) {
        EmployeeInfo emp = new EmployeeInfo();

        emp.setId(101);
        emp.setName("Avinash");
        emp.setSalary(55000.5);

        System.out.println("Employee ID     : " + emp.getId());
        System.out.println("Employee Name   : " + emp.getName());
        System.out.println("Employee Salary : " + emp.getSalary());
    }
}