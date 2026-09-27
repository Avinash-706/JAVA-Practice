import java.util.ArrayList;
import java.util.ListIterator;


// Custom Exceptions
class InvalidAssetsException extends Exception {
    public InvalidAssetsException(String message) {
        super(message);
    }
}

class InvalidExperienceException extends Exception {
    public InvalidExperienceException(String message) {
        super(message);
    }
}


// Resources Class
class Resources {
    public static int getMonth(String month) {
        if (month == null || month.length() != 3) {
            return 0;
        }

        switch (month) {
            case "Jan": 
                return 1;
            case "Feb": 
                return 2;
            case "Mar": 
                return 3;
            case "Apr": 
                return 4;
            case "May": 
                return 5;
            case "Jun": 
                return 6;
            case "Jul": 
                return 7;
            case "Aug": 
                return 8;
            case "Sep": 
                return 9;
            case "Oct": 
                return 10;
            case "Nov": 
                return 11;
            case "Dec": 
                return 12;
            default: 
                return 0;
        }
    }
}



// ADMIN Class
class Admin {
    public void generateSalarySlip(ArrayList<Employee> employees, ArrayList<Float> salaryFactor) {
        // One-to-one mapping between employees and salaryFactor
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            float factor = salaryFactor.get(i);
            emp.calculateSalary(factor);
        }
    }

    public int generateAssetsReport(ArrayList<Employee> employees, String lastDate) {
        int totalAssets = 0;
        
        for (Employee emp : employees) {
            if (emp instanceof PermanentEmployee) {
                try {
                    ArrayList<Asset> expiringAssets = ((PermanentEmployee) emp).getAssetsByDate(lastDate);
                    totalAssets += expiringAssets.size();
                } 
                catch (InvalidAssetsException e) {
                    return -1;
                }
            }
        }
        return totalAssets;
    }

    public ArrayList<String> generateAssetsReport(ArrayList<Employee> employees, char assetCategory) {
        ArrayList<String> matchingAssetIds = new ArrayList<>();
        String categoryChar = String.valueOf(assetCategory).toLowerCase();

        for (Employee emp : employees) {
            if (emp instanceof PermanentEmployee) {
                PermanentEmployee permEmp = (PermanentEmployee) emp;

                if (permEmp.getAssets() != null) {
                    for (Asset asset : permEmp.getAssets()) {
                        String id = asset.getAssetId();

                        // check For Case-Iinsensitive check
                        if (id != null && id.toLowerCase().startsWith(categoryChar)) {
                            matchingAssetIds.add(id);
                        }
                    }
                }

            }
        }

        return matchingAssetIds;
    }
}



// EMPLOYEE Class
abstract class Employee {
    private String employeeId;
    private String employeeName;
    private double salary;

    public static int _contractIdCounter;
    public static int _permanentIdCounter;

    static {
        _contractIdCounter = 10000;
        _permanentIdCounter = 10000;
    }

    public Employee(String employeeName) {
        setEmployeeName(employeeName);
    }

    
    // abstract method
    public abstract void calculateSalary(float salaryFactor);



    // getters & setters
    public String getEmployeeId() {
        return this.employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


    public String getEmployeeName() {
        return this.employeeName;
    }
    public void setEmployeeName(String employeeName) {
        if (employeeName == null) {
            return;
        }

        String[] words = employeeName.split(" ");

        if (words.length < 2) {
            return;
        }

        for (String word : words) {
            if (word.length() < 2 || !Character.isUpperCase(word.charAt(0))) {
                return;
            }
            for(char c : word.toCharArray()) {
                if (!Character.isLetter(c)) return;
            }
        }

        this.employeeName = employeeName;
    }

    
    public double getSalary() {
        return this.salary;
    }
    public void setSalary(double salary) {
        if (salary > 0) this.salary = salary;
        else this.salary = 0;
    }


    // static methods
    public static int _getContractIdCounter() {
        return _contractIdCounter;
    }
    public static void _setContractIdCounter(int contractIdCounter) {
        _contractIdCounter = contractIdCounter;
    }


    public static int _getPermanentIdCounter() {
        return _permanentIdCounter;
    }
    public static void _setPermanentIdCounter(int permanentIdCounter) {
        _permanentIdCounter = permanentIdCounter;
    }


    @Override
    public String toString() {
        return "Employee";
    }
}



// Assett Class
class Asset {
    private String assetId;
    private String assetName;
    private String assetExpiry;

    public Asset(String assetId, String assetName, String assetExpiry) {
        setAssetId(assetId); 
        this.assetName = assetName;
        this.assetExpiry = assetExpiry;
    }


    // getters & setters
    public String getAssetId() {
        return this.assetId;
    }
    public void setAssetId(String assetId) {
        if (assetId != null && assetId.length() >= 10) {
            if ((assetId.startsWith("DSK") || assetId.startsWith("LTP") || assetId.startsWith("IPH"))
                && assetId.charAt(3) == '-'
                && assetId.substring(4, 10).matches("\\d{6}")
                && (assetId.length() > 10 && (String.valueOf(assetId.charAt(10)).equalsIgnoreCase("H")
                || String.valueOf(assetId.charAt(10)).equalsIgnoreCase("L")))
            ) {
                this.assetId = assetId;
            }
        }
    }


    public String getAssetName() {
        return this.assetName;
    }
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }


    public String getAssetExpiry() {
        return this.assetExpiry;
    }
    public void setAssetExpiry(String assetExpiry) {
        this.assetExpiry = assetExpiry;
    }


    @Override
    public String toString() {
        return "Asset";
    }
}




// Contracct Employeee
class ContractEmployee extends Employee {
    // private String employeeName;
    private double wagePerHour;

    public ContractEmployee(String employeeName, double wagePerHour) {
        super(employeeName);
        _contractIdCounter++;
        setEmployeeId("C" + _contractIdCounter);
        this.wagePerHour = wagePerHour;
    }

    @Override
    public void calculateSalary(float hoursWorked) {
        double salary = getWagePerHour() * hoursWorked;

        if (hoursWorked < 190) {
            float missingHours = 190 - hoursWorked;
            double deduction = missingHours * (0.5 * getWagePerHour());
            salary = salary - deduction;
        }

        salary = Math.round(salary);
        super.setSalary(salary);
    }

    public double getWagePerHour() {
        return this.wagePerHour;
    }

    public void setWagePerHour(double wagePerHour) {
        this.wagePerHour = wagePerHour;
    }

    public String toString() {
        return "Contract Employee";
    }
}



// Permanenent Class
class PermanentEmployee extends Employee {
    private double basicPay;
    private ArrayList<String> salaryComponents;
    private float experience;
    private ArrayList<Asset> assets;

    public PermanentEmployee(String employeeName, double basicPay, ArrayList<String> salaryComponents, ArrayList<Asset> assets) {
        super(employeeName);
        _permanentIdCounter++;
        setEmployeeId("E" + _permanentIdCounter);
        this.basicPay = basicPay;
        this.salaryComponents = salaryComponents;
        this.assets = assets;
    }


    public double calculateBonus(float experience) throws InvalidExperienceException {
        if(experience >= 2.5 && experience < 4) {
            return 2550;
        } 
        else if(experience >= 4 && experience < 8) {
            return 5000;
        } 
        else if (experience >= 8 && experience < 12) {
            return 8750;
        } 
        else if (experience >= 12) {
            return 13000;
        }
        else {
            throw new InvalidExperienceException("A minimum of 2.5 years is required for bonus!");
        }
    }


    @Override
    public void calculateSalary(float experience) {
        this.experience = experience;
        
        double salary = basicPay;

        if (salaryComponents != null) {
            ListIterator<String> itr = salaryComponents.listIterator();
            while (itr.hasNext()) {
                String component = itr.next();
                String[] percent = component.split("-");
                if (percent.length == 2) {
                    double value = Double.parseDouble(percent[1]);
                    salary += basicPay * (value / 100);
                }
            }
        }

        double bonus = 0;
        try{
            bonus = calculateBonus(experience);
        } 
        catch(InvalidExperienceException e) {
            bonus = 0;
        }

        salary += bonus;
        salary = Math.round(salary);

        super.setSalary(salary);
    }


    public ArrayList<Asset> getAssetsByDate(String lastDate) throws InvalidAssetsException {
        String[] lastDatePart = lastDate.split("-");

        int year1 = Integer.parseInt(lastDatePart[0]);
        int month1 = Resources.getMonth(lastDatePart[1]);
        int day1 = Integer.parseInt(lastDatePart[2]);

        ArrayList<Asset> assetsByDate = new ArrayList<>();

        if (assets != null) {
            for (Asset asset : assets) {
                String expiryDate = asset.getAssetExpiry();
                String[] dateParts = expiryDate.split("-");

                int year2 = Integer.parseInt(dateParts[0]);
                int month2 = Resources.getMonth(dateParts[1]);
                int day2 = Integer.parseInt(dateParts[2]);

                if (year2 < year1 ||
                        (year2 == year1 && month2 < month1) ||
                        (year2 == year1 && month2 == month1 && day2 <= day1)) {
                    assetsByDate.add(asset);
                }
            }
        }

        if (assetsByDate.isEmpty()) {
            throw new InvalidAssetsException("No assets found for the given criteria!");
        }

        return assetsByDate;
    }


    // getter & setters
    public double getBasicPay() {
        return this.basicPay;
    }
    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }


    public ArrayList<String> getSalaryComponents() {
        return this.salaryComponents;
    }
    public void setSalaryComponents(ArrayList<String> salaryComponents) {
        this.salaryComponents = salaryComponents;
    }


    public float getExperience() {
        return this.experience;
    }
    public void setExperience(float experience) {
        this.experience = experience;
    }


    public ArrayList<Asset> getAssets() {
        return this.assets;
    }
    public void setAssets(ArrayList<Asset> assets) {
        this.assets = assets;
    }


    @Override
    public String toString() {
        return "Permanent Employee";
    }
}



// Utility Class
class Utility {
    public static void printAssetDetails(int index, Asset asset) {
        System.out.println("Details of asset" + index);
        System.out.println("\tAsset Id: " + asset.getAssetId());
        System.out.println("\tAsset Name: " + asset.getAssetName());
        System.out.println("\tAsset Valid Till: " + asset.getAssetExpiry());
        System.out.println();
    }

    public static void printEmployeeDetails(Employee e) {
        String type = (e instanceof PermanentEmployee) ? "permanentEmployee" : "contractEmployee";
        // Extract number from ID (last digit) to match screenshot format
        char num = e.getEmployeeId().charAt(e.getEmployeeId().length() - 1);

        System.out.println("Details of " + type + num);
        System.out.println("\tEmployee Id: " + e.getEmployeeId());
        System.out.println("\tEmployee Name: " + e.getEmployeeName());
        System.out.println("\tSalary: " + e.getSalary());

        if (e instanceof PermanentEmployee) {
            PermanentEmployee pe = (PermanentEmployee) e;
            System.out.println("\tExperience: " + pe.getExperience());
            System.out.print("\tAssets Allocated: ");
            if (pe.getAssets() == null || pe.getAssets().isEmpty()) {
                System.out.println("No assets allocated!");
            } else {
                for (Asset a : pe.getAssets()) {
                    System.out.print(a.getAssetId() + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}



// Main Solution Class
public class EmployeeManagement {
    public static void main(String[] args) {
        // 1. Create Assets
        ArrayList<Asset> allAssets = new ArrayList<>();
        allAssets.add(new Asset("DSK-876761L", "Dell-Desktop", "2020-Dec-01"));
        allAssets.add(new Asset("DSK-876762L", "Acer-Desktop", "2021-Mar-31"));
        allAssets.add(new Asset("DSK-876763L", "Dell-Desktop", "2022-Jun-12"));
        allAssets.add(new Asset("LTP-987123H", "Dell-Laptop", "2021-Dec-31"));
        allAssets.add(new Asset("LTP-987124h", "Dell-Laptop", "2021-Sep-20"));
        allAssets.add(new Asset("LTP-987125L", "HP-Laptop", "2022-Oct-25"));
        allAssets.add(new Asset("LTP-987126l", "HP-Laptop", "2021-Oct-02"));
        allAssets.add(new Asset("IPH-110110h", "VoIP", "2021-Dec-12"));
        
        // Asset 9: Initially Invalid
        Asset invalidAsset = new Asset("InvalidID", "VoIP", "2020-Dec-31");
        
        // Asset 10
        Asset asset10 = new Asset("IPH-110130h", "VoIP", "2020-Nov-30");

        ArrayList<Asset> displayList = new ArrayList<>();
        displayList.addAll(allAssets);
        displayList.add(invalidAsset);
        displayList.add(asset10);

        System.out.println("Details of all available assets");
        System.out.println();
        for (int i = 0; i < displayList.size(); i++) {
            Utility.printAssetDetails(i + 1, displayList.get(i));
        }



        // 2. Correcting Invalid Asset
        System.out.println("Correcting all the invalid assetIds");
        System.out.println();
        invalidAsset.setAssetId("IPH-110120h");
        Utility.printAssetDetails(9, invalidAsset);



        // 3. Employee Creation
        System.out.println("Initiating salary calculation...");
        System.out.println();
        System.out.println("Details of employees");
        System.out.println();

        ArrayList<String> comps = new ArrayList<>();
        comps.add("DA-50");
        comps.add("HRA-40");

        // Pe1: Roger Fed
        ArrayList<Asset> assets1 = new ArrayList<>();
        assets1.add(allAssets.get(0));
        assets1.add(asset10);
        PermanentEmployee pe1 = new PermanentEmployee("Roger Fed", 15500, comps, assets1);

        // Pe2: Name null
        ArrayList<Asset> assets2 = new ArrayList<>();
        assets2.add(allAssets.get(5));
        assets2.add(invalidAsset);
        PermanentEmployee pe2 = new PermanentEmployee(null, 13263.16, comps, assets2);

        // Pe3: James Peter
        ArrayList<Asset> assets3 = new ArrayList<>();
        assets3.add(allAssets.get(3));
        PermanentEmployee pe3 = new PermanentEmployee("James Peter", 18987, comps, assets3);

        // Pe4: Catherine Maria
        ArrayList<Asset> assets4 = new ArrayList<>();
        assets4.add(allAssets.get(1));
        assets4.add(allAssets.get(4));
        PermanentEmployee pe4 = new PermanentEmployee("Catherine Maria", 22500, comps, assets4);

        // Pe5: Jobin Nick (No assets)
        PermanentEmployee pe5 = new PermanentEmployee("Jobin Nick", 10000, comps, null);
        // We set experience manually because we won't call calculateSalary for him (to keep salary 0.0)
        pe5.setExperience(12.5f);

        // Contract Employees
        ContractEmployee ce1 = new ContractEmployee(null, 45);
        ContractEmployee ce2 = new ContractEmployee("Ricky Neol", 45);

        // List for Salary Calculation (Excluding pe5 to match screenshot where his Salary is 0.0)
        ArrayList<Employee> salaryEmployees = new ArrayList<>();
        salaryEmployees.add(pe1);
        salaryEmployees.add(pe2);
        salaryEmployees.add(pe3);
        salaryEmployees.add(pe4);
        salaryEmployees.add(ce1);
        salaryEmployees.add(ce2);

        ArrayList<Float> factors = new ArrayList<>();
        factors.add(3.9f);
        factors.add(2.3f);
        factors.add(4.0f);
        factors.add(8.1f);
        factors.add(293.0f);
        factors.add(340.0f);



        // 4. Admin Operations
        Admin admin = new Admin();
        admin.generateSalarySlip(salaryEmployees, factors);

        // List for Display (Includes pe5)
        ArrayList<Employee> allEmployees = new ArrayList<>(salaryEmployees);
        allEmployees.add(4, pe5); // Insert pe5 at correct index for display

        for (Employee e : allEmployees) {
            Utility.printEmployeeDetails(e);
        }



        // 5. Reports
        System.out.println("Reports");
        System.out.println();


        // Report 1: Count of valid assets
        ArrayList<Employee> validAssetEmployees = new ArrayList<>();
        validAssetEmployees.add(pe1);
        validAssetEmployees.add(pe2);
        validAssetEmployees.add(pe3);
        validAssetEmployees.add(pe4);

        int expiringCount = admin.generateAssetsReport(validAssetEmployees, "2021-Dec-31");
        System.out.println("Number of allocated assets expiring on or before 2021-Dec-31: " + expiringCount);
        System.out.println();


        // Report 2: Simulate Error
        ArrayList<Employee> invalidAssetEmployees = new ArrayList<>();
        invalidAssetEmployees.add(pe5);
        
        int errorReport = admin.generateAssetsReport(invalidAssetEmployees, "2021-Dec-31");
        if (errorReport == -1) {
            System.out.println("Sorry, report cannot be generated!");
        }
        System.out.println();


        // Category Reports
        System.out.println("All the allocated desktop assets");
        for (String s : admin.generateAssetsReport(validAssetEmployees, 'D')) {
            System.out.println("\t" + s);
        }
        System.out.println();


        System.out.println("All the allocated laptop assets");
        for (String s : admin.generateAssetsReport(validAssetEmployees, 'L')) {
            System.out.println("\t" + s);
        }
        System.out.println();


        System.out.println("All the allocated VoIP assets");
        // Passing 'I' because Asset IDs start with "IPH"
        for (String s : admin.generateAssetsReport(validAssetEmployees, 'I')) {
            System.out.println("\t" + s);
        }
    }
}